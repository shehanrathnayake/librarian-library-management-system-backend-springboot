package com.shehanrathnayake.service.custom;

import com.shehanrathnayake.service.SuperService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface RefreshTokenService extends SuperService {
    void getAccessToken(HttpServletRequest req, HttpServletResponse res) throws IOException;
}
