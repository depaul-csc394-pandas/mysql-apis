package org.pandadevs.restservice.repository;

import org.pandadevs.restservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;


@RestResource(exported=false)
public interface UserRepository extends JpaRepository<User, String> {

}