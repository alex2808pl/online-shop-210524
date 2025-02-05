package de.telran.onlineshop.security.controller;

import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.security.dto.JwtRequestDto;
import de.telran.onlineshop.security.dto.JwtRequestRefreshDto;
import de.telran.onlineshop.security.dto.JwtResponseDto;
import de.telran.onlineshop.security.service.AuthService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    /**
     * The authentication service bean for handling authentication operations.
     */
    private final AuthService authService;

    /**
     * Handles user login and returns a JWT response.
     *
     * @param authRequest the authentication request containing username and password.
     * @return a ResponseEntity containing the JWT response.
     * @throws AuthException if authentication fails.
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody JwtRequestDto authRequest) throws AuthException {
        final JwtResponseDto token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    /**
     * Obtains a new access token using a refresh token.
     *
     * @param request the request containing the refresh token.
     * @return a ResponseEntity containing the JWT response.
     * @throws AuthException if token refresh fails.
     */
    @PostMapping("/token")
    public ResponseEntity<JwtResponseDto> getNewAccessToken(@RequestBody JwtRequestRefreshDto request) throws AuthException {
        final JwtResponseDto token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    /**
     * Obtains a new refresh token using an existing refresh token.
     *
     * @param request the request containing the refresh token.
     * @return a ResponseEntity containing the JWT response.
     * @throws AuthException if token refresh fails.
     */
    @PostMapping("/refresh")
    public ResponseEntity<JwtResponseDto> getNewRefreshToken(@RequestBody JwtRequestRefreshDto request) throws AuthException {
        final JwtResponseDto token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<UserDto> register(@RequestBody UserDto userCredentialsDto) {
        UserDto userDto = authService.createUser(userCredentialsDto);
        return ResponseEntity.ofNullable(userDto);
    }
}
