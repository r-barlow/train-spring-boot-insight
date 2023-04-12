package za.co.neslotech.spring.trainspringbootinsight.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date created;

    @Column
    private String name;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}

