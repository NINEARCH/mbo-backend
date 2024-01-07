package com.ninearch.mbobackend.service;

import com.ninearch.mbobackend.entity.UserEntity;
import com.ninearch.mbobackend.exception.BaseException;
import com.ninearch.mbobackend.exception.UserException;
import com.ninearch.mbobackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userInfo = repository.findByEmail(email);
        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
    }


    public Optional<UserEntity> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public UserEntity addUser(String email, String password, String name, String roles) throws BaseException {
        if (Objects.isNull(name)) {
            throw UserException.createNameNull();
        }
        if (Objects.isNull(email)) {
            throw UserException.createEmailNull();
        }
        if (Objects.isNull(password)) {
            throw UserException.createPasswordNull();
        }

        if (Objects.isNull(roles)) {
            throw UserException.createRolesNull();
        }
        if (repository.existsByEmail(email)) {
            throw UserException.createEmailExits();
        }
        UserEntity entity = new UserEntity();
        entity.setName(name);
        entity.setEmail(email);
        entity.setRoles(roles);
        entity.setPassword(passwordEncoder.encode(password));

        return repository.save(entity);
    }

    public UserEntity updateUser(String id, String password, String name, String roles) throws BaseException {

        if(Objects.isNull(id)){
            throw UserException.idNotFound();
        }
        if(Objects.isNull(name)){
            throw UserException.nameNotFound();
        }
        if(Objects.isNull(roles)){
            throw UserException.rolesNotFound();
        }

        UserEntity entity = repository.getByid(id);
        if(entity != null){
            entity.setName(name);
            entity.setRoles(roles);
            if(!Objects.isNull(password)){
                entity.setPassword(passwordEncoder.encode(password));
            }
            return repository.save(entity);
        }else {
            return entity;
        }


    }

    public boolean deleteUser(String id) throws BaseException {
        if(Objects.isNull(id)){
            throw UserException.idNotFound();
        }
        try {
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public List<UserEntity> getAllUser() { return repository.findAll(); }

    public UserEntity getUser(String id) {
        return repository.findById(id).get();
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


}
