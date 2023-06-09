package fr.gamedev.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Skill {
    @Id
    private long id;

    public long id() {
        return id;
    }

    public Skill setId(long id) {
        this.id = id;
        return this;
    }

}
