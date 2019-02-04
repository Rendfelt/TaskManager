package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.entity.User;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@ApplicationScoped
public class UserHibernateRepository extends AbstractEntityHibernateRepository<User>
implements UserRepository {

    public UserHibernateRepository() {
        super(User.class);
    }

    @Override
    public User getElementByLogin(String login, @NotNull EntityManager entityManager) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userCriteria = cb.createQuery(User.class);
        Root<User> userRoot = userCriteria.from(User.class);
        userCriteria.select(userRoot);
        userCriteria.where(cb.equal(userRoot.get("login"), login));
        try {
            return entityManager.createQuery(userCriteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
