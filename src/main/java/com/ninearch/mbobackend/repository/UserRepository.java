package com.ninearch.mbobackend.repository;

import com.ninearch.mbobackend.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    UserEntity getByid(String id);
    boolean existsByEmail(String email);

    @Override
    List<UserEntity> findAll();
}
