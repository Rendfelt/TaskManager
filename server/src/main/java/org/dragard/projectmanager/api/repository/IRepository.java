package org.dragard.projectmanager.api.repository;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.dragard.projectmanager.entity.AbstractEntity;

public interface IRepository<E extends AbstractEntity> extends FullEntityRepository<E, String> {

}
