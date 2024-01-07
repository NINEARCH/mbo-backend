package com.ninearch.mbobackend.controller;

import com.ninearch.mbobackend.entity.UserEntity;
import com.ninearch.mbobackend.exception.BaseException;
import com.ninearch.mbobackend.exception.UserException;
import com.ninearch.mbobackend.model.LoginRequestModel;
import com.ninearch.mbobackend.model.RegisterRequestModel;
import com.ninearch.mbobackend.model.UserModel;
import com.ninearch.mbobackend.service.UserService;
import com.ninearch.mbobackend.service.jwtService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userController {

    private final UserService userService;
    private final jwtService tokenService;

    public userController(UserService userService, jwtService jwtService) {
        this.userService = userService;
        this.tokenService = jwtService;
    }

    public UserEntity register(RegisterRequestModel request) throws BaseException {
        if (request == null) {
            throw UserException.requestNull();
        }
        UserEntity user = userService.addUser(
                request.getEmail(),
                request.getPassword(),
                request.getName(),
                "user"
        );

        return user;
    }

    public UserEntity addUser(UserModel request) throws BaseException {
        if (request == null) {
            throw UserException.requestNull();
        }
        UserEntity user = userService.addUser(request.getEmail(), request.getPassword(), request.getName(), request.getRoles());
        return user;
    }

    public UserEntity updateUser(UserModel request) throws BaseException {
        if (request == null) {
            throw UserException.requestNull();
        }
            UserEntity user = userService.updateUser(request.getId(), request.getPassword(), request.getName(), request.getRoles());
        return user;
    }

    public boolean deleteUser(String id) throws BaseException {
        if (id == null) {
            throw UserException.idNotFound();
        }
        return userService.deleteUser(id);
    }

    public String login(LoginRequestModel request) throws UserException {
        Optional<UserEntity> opt = userService.findByEmail(request.getEmail());
        if (opt.isEmpty()) {
            throw UserException.emailNull();
        }
        UserEntity user = opt.get();
        if (!userService.matchPassword(request.getPassword(), user.getPassword())) {
            throw UserException.loginFailPasswordIncorrect();
        }

        return tokenService.tokenize(user);

    }


    public UserEntity getUserById(String id) {
        return userService.getUser(id);
    }
    public List<UserEntity> getAllUser() {
        return userService.getAllUser();
    }


}
