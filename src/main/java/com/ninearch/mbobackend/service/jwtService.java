package com.ninearch.mbobackend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ninearch.mbobackend.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class jwtService {


    private final String secretKey = "a249=04495==3=490-3-3E-3=49D094--M3-924-134-";
    private final String issuer = "MBO-CORE";

    public String tokenize(UserEntity user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date expireAt = calendar.getTime();
        return JWT.create()
                .withClaim("principal", user.getId())
                .withClaim("role", "USER")
                .withIssuer(issuer)
                .withExpiresAt(expireAt)
                .sign(algorithm());
    }

    public DecodedJWT verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm()).withIssuer(issuer).build();
            return verifier.verify(token);
        } catch (Exception e) {
            return null;
        }

    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secretKey);
    }
}
