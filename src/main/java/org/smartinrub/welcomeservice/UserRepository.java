package org.smartinrub.welcomeservice;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> { }
