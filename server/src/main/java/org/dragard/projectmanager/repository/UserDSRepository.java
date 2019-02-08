package org.dragard.projectmanager.repository;

import org.apache.deltaspike.data.api.Repository;
import org.dragard.projectmanager.api.annotation.Preferred;
import org.dragard.projectmanager.api.repository.IRepository;
import org.dragard.projectmanager.entity.User;

import javax.enterprise.context.ApplicationScoped;

@Repository
@ApplicationScoped
@Preferred
public interface UserDSRepository extends IRepository<User> {

    User findByLogin(String login);

}
