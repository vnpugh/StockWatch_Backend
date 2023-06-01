package models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="industry")
public class Industry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "industry_id")
    private Long industryId;

    @Column(name = "industry")
    private String industry;

    @OneToMany(mappedBy = "industry")
    private List<Stock> stocks;











}
