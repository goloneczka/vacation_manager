package com.vacation.manager.security;

import com.vacation.manager.response.ResponseStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(ResponseStatus.UNAUTHORIZED);
        String message = e.getMessage().equals("Bad credentials") ? "Niepoprawne dane" : e.getMessage();
        httpServletResponse.getOutputStream().println("{ \"errors\": \"" + message + "\" }");

    }

}
