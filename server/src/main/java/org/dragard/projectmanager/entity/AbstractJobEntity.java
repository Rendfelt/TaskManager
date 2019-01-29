package org.dragard.projectmanager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public abstract class AbstractJobEntity extends AbstractEntity{

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name="userId", nullable=false, updatable=false)
    private User user;

}
