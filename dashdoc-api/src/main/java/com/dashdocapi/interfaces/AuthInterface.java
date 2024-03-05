package com.dashdocapi.interfaces;

import com.dashdocapi.DTO.ProviderDTO;
import com.dashdocapi.DTO.UserSignInRequest;
import com.dashdocapi.DTO.UserSignUpRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthInterface {
    ProviderDTO signUp(UserSignUpRequest userSignUpRequest);
    ProviderDTO signIn(UserSignInRequest userSignInRequest, HttpServletResponse response);
}
