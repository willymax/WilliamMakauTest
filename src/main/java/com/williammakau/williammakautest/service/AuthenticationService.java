package com.williammakau.williammakautest.service;

public interface AuthenticationService {
    /**
     * Validates the OAuth2 token
     */
    boolean validateToken(String token);

    /**
     * Gets the authenticated user's ID from the token
     */
    String getAuthenticatedUserId(String token);

    /**
     * Checks if the user has the required role/permission
     */
    boolean hasRequiredRole(String token, String requiredRole);
} 