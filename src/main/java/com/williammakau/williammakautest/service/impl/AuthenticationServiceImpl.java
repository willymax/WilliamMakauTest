package com.williammakau.williammakautest.service.impl;

import com.williammakau.williammakautest.service.AuthenticationService;
import org.springframework.stereotype.Service;

/**
 * @author : William Makau
 * @created : 3/13/2025, Thursday
 **/
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public boolean validateToken(String token) {
        // validate the token
        return true;
    }

    @Override
    public String getAuthenticatedUserId(String token) {
        // get the authenticated user id
        return "1";
    }

    @Override
    public boolean hasRequiredRole(String token, String requiredRole) {
        // check if the user has the required role
        return true;
    }
}
