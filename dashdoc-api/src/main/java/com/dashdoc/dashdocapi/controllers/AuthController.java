package com.dashdocnow.controllers;

import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
import com.amazonaws.services.cognitoidp.model.ResendConfirmationCodeResult;
import com.dashdocnow.DTO.*;
import com.dashdocnow.entities.Provider;
import com.dashdocnow.services.AuthService;
import com.dashdocnow.utils.BadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ProviderDTO> signUp(@RequestBody UserSignUpRequest userSignUpRequest) {

        ProviderDTO unconfirmedProvider = authService.signUp(userSignUpRequest);
        return new ResponseEntity<>(
                unconfirmedProvider,
                HttpStatus.OK);
    }

    @PostMapping("/signin")
    @ResponseBody
    public ResponseEntity<ProviderDTO> signIn(@RequestBody UserSignInRequest userSignInRequest, HttpServletResponse response) throws BadRequestException {
        ProviderDTO user = authService.signIn(userSignInRequest, response);

        return new ResponseEntity<>(
                user,
                HttpStatus.OK);
    }

    @PostMapping("/confirmUser")
    @ResponseBody
    public ResponseEntity<ProviderDTO>confirmUser(@RequestBody ConfirmSignUpDTO confirmSignUpDTO) {
        ProviderDTO confirmedUser = authService.confirmSignUpRequest(confirmSignUpDTO);
        return new ResponseEntity<>(confirmedUser, HttpStatus.OK);
    }

    @PostMapping("/resendCode")
    @ResponseBody
    public ResponseEntity<ResendConfirmationCodeResult> resendConfirmationCode (@RequestBody ConfirmSignUpDTO confirmSignUpDTO) {
            ResendConfirmationCodeResult resendConfirmationCode = authService.resendConfirmationCode(confirmSignUpDTO);
            return new ResponseEntity<>(resendConfirmationCode, HttpStatus.OK);
    }

    @GetMapping("/validateUser")
    public ResponseEntity<ValidateUserDTO> validateUser (HttpServletRequest req, HttpServletResponse res) throws ParseException {
        ValidateUserDTO result = authService.validateUserStatus(req, res);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/validateGoogleUser")
    public ResponseEntity<ProviderDTO> validateUser (@RequestBody GoogleAuthRequest googleAuthRequest, HttpServletResponse res) throws Exception {
        ProviderDTO result = authService.validateGoogleUser(googleAuthRequest, res);

        return new ResponseEntity<ProviderDTO>(result, HttpStatus.OK);
    }
}
