package com.gmail.eugene.controlwork.web;

import com.gmail.eugene.controlwork.service.BusinessCardService;
import com.gmail.eugene.controlwork.service.UserService;
import com.gmail.eugene.controlwork.service.model.AppUserPrincipal;
import com.gmail.eugene.controlwork.service.model.BusinessCardDTO;
import com.gmail.eugene.controlwork.service.model.ViewBusinessCardDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.eugene.controlwork.service.constant.SecurityConstant.ADMINISTRATOR;
import static com.gmail.eugene.controlwork.service.constant.SecurityConstant.CUSTOMER;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    private final UserService userService;
    private final BusinessCardService businessCardService;

    @Autowired
    public ApiController(
            UserService userService,
            BusinessCardService businessCardService
    ) {
        this.userService = userService;
        this.businessCardService = businessCardService;
    }

    @Secured(value = ADMINISTRATOR)
    @PostMapping(value = "api/v1/cards", consumes = APPLICATION_JSON_VALUE)
    public BusinessCardDTO addCardToUser(@Valid @RequestBody BusinessCardDTO businessCardDTO) {
        return userService.add(businessCardDTO);
    }

    @Secured(value = CUSTOMER)
    @GetMapping(value = "api/v1/users/cards")
    public List<ViewBusinessCardDTO> getBusinessCards() {
        AppUserPrincipal appUserPrincipal =
                (AppUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = appUserPrincipal.getId();
        return userService.getBusinessCards(userId);
    }

    @Secured(value = ADMINISTRATOR)
    @DeleteMapping(value = "api/v1/cards/{id}")
    public ResponseEntity deleteCard(@PathVariable("id") Long id) {
        businessCardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @SuppressWarnings(value = "unchecked")
    public ResponseEntity processValidationError(MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        List<String> errorsList = errors.stream()
                .map(error -> String.format("Field: %s - %s.", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity(errorsList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @SuppressWarnings(value = "unchecked")
    public ResponseEntity defaultError(Exception e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity("Something went wrong!", HttpStatus.BAD_REQUEST);
    }
}
