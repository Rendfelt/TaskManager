package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.entity.Task;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class TaskHibernateRepository extends AbstractJobEntityHibernateRepository<Task>
    implements TaskRepository {

    public TaskHibernateRepository() {
        super(Task.class);
    }

    @Override
    public List<Task> getElementsByProjectId(String id, EntityManager entityManager){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> taskCriteriaQuery = cb.createQuery(Task.class);
        Root<Task> taskRoot = taskCriteriaQuery.from(Task.class);
        taskCriteriaQuery.select(taskRoot);
        taskCriteriaQuery.where(cb.equal(taskRoot.get("project").get("id"), id));
        return entityManager.createQuery(taskCriteriaQuery).getResultList();
    }
}
