package org.smartinrub.welcomeservice.repositories;

import org.smartinrub.welcomeservice.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> { }
