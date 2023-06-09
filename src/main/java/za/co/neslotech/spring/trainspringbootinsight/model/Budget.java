package za.co.neslotech.spring.trainspringbootinsight.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date created;

    @Column
    private String name;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private List<Category> categories;

    @ManyToOne
    @JsonIgnoreProperties("budgets")
    private Rollover rollover;

    @ManyToOne
    @JsonIgnoreProperties("budgets")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
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

    public List<Category> getCategories() {
        return categories == null ? new ArrayList<>() : categories;
    }

    public void setCategories(final List<Category> categories) {
        this.categories = categories;
    }

    public Rollover getRollover() {
        return rollover;
    }

    public void setRollover(final Rollover rollover) {
        this.rollover = rollover;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}

