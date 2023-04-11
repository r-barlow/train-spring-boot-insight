package za.co.neslotech.spring.trainspringbootinsight.model;

import jakarta.persistence.*;

@Entity(name = "rollovers")
public class Rollover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rollover_id")
    private Long RolloverId;

    @Column(name = "name")
    private String name;
}
