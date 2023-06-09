package fr.gamedev.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Swan
 */
@Entity
public class Category {
    /**.
     * id long
     */
    @GeneratedValue
    @Id
    private long id;
    /**.
     * name string
     */
    private String name;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param newId
     */
    public void setId(final long newId) {
        this.id = newId;
    }
    /**
     * @return String
     */
    public String getName() {
        return name;
    }
    /**
     * @param  newName
     */
    public void setName(final String newName) {
        this.name = newName;
    }
}
