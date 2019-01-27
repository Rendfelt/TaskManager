package org.dragard.projectmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


//@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity
    implements Serializable {

    @Column
    @Id
    private String id;

    @Column
    private String name;

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'';
    }
}
