package com.dashdocnow.interfaces;

import com.dashdocnow.DTO.ProviderDTO;
import com.dashdocnow.DTO.UserSignInRequest;
import com.dashdocnow.DTO.UserSignUpRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthInterface {
    ProviderDTO signUp(UserSignUpRequest userSignUpRequest);
    ProviderDTO signIn(UserSignInRequest userSignInRequest, HttpServletResponse response);
}
