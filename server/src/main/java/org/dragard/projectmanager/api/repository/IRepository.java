package org.dragard.projectmanager.api.repository;

import org.dragard.projectmanager.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRepository<E extends AbstractEntity> extends JpaRepository<E, String> {

}
