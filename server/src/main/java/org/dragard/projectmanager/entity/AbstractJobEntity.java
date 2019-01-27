package org.dragard.projectmanager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

//@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractJobEntity extends AbstractEntity{

    @Column
    private String description;

    @Column
    private String userId;

    protected AbstractJobEntity(String id, String name, String description, String userId) {
        super(id, name);
        this.description = description;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", description='" + description + '\'';
    }

}
