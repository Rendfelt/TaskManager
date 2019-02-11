package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.IRepository;
import org.dragard.projectmanager.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IRepository<User> {

    User findByLogin(String login);

}
