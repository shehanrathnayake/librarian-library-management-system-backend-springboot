package com.shehanrathnayake.api;

import com.shehanrathnayake.service.custom.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/refresh-token")
@CrossOrigin
@RequiredArgsConstructor
public class RefreshTokenHttpController {
    private final RefreshTokenService refreshTokenService;

    @GetMapping
    public void getAccessToken(HttpServletRequest req, HttpServletResponse res) throws IOException {
        refreshTokenService.getAccessToken(req, res);
    }
}
