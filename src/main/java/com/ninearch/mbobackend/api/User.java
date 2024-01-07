package com.ninearch.mbobackend.api;

import com.ninearch.mbobackend.controller.userController;
import com.ninearch.mbobackend.entity.UserEntity;
import com.ninearch.mbobackend.exception.BaseException;
import com.ninearch.mbobackend.model.LoginRequestModel;
import com.ninearch.mbobackend.model.RegisterRequestModel;
import com.ninearch.mbobackend.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class User {


    @Autowired
    private userController user;


    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<UserEntity> userPostRegister(@RequestBody RegisterRequestModel request) throws BaseException {
        UserEntity response;
        response = user.register(request);
        return ResponseEntity.ok(response);

    }

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<String> userPostLogin(@RequestBody LoginRequestModel request) throws BaseException {
        String response;
        response = user.login(request);
        return ResponseEntity.ok(response);

    }

    //USER GET
    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<UserEntity> getUserData(@PathVariable("id") String id) {
        UserEntity response = user.getUserById(id);
        return ResponseEntity.ok(response);
    }

    //USER GET ALL
    @GetMapping
    @RequestMapping("getall")
    public List<UserEntity> getAllUserData() {
        List<UserEntity> response = user.getAllUser();
        return response;
    }

    //USER POST
    @PostMapping
    @RequestMapping("getuser")
    public  ResponseEntity<UserEntity> addUser(@RequestBody UserModel request) throws BaseException {
        UserEntity response = user.addUser(request);
        return ResponseEntity.ok(response);
    }

    //USER PUT
    @PutMapping
    @RequestMapping("update")
    public  ResponseEntity<UserEntity> updateUser(@RequestBody UserModel request) throws BaseException {
        UserEntity response = user.updateUser(request);
        return ResponseEntity.ok(response);
    }

    //USER DELETE
    @DeleteMapping
    @RequestMapping("delete")
    public  ResponseEntity<Boolean> updateUser(@PathVariable("id") String id) throws BaseException {
        boolean response = user.deleteUser(id);
        return ResponseEntity.ok(response);
    }


}
