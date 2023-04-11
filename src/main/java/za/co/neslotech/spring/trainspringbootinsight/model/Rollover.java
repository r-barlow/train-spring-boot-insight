package za.co.neslotech.spring.trainspringbootinsight.model;

import jakarta.persistence.*;

@Entity(name = "rollovers")
public class Rollover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rollover_id")
    private Long rolloverId;

    @Column(name = "name")
    private String name;

    public Long getRolloverId() {
        return rolloverId;
    }

    public void setRolloverId(Long rolloverId) {
        this.rolloverId = rolloverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
