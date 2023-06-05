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

    public Industry() {
    }

    public Industry(Long industryId, String industry) {
        this.industryId = industryId;
        this.industry = industry;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Override
    public String toString() {
        return "Industry{" +
                "industryId=" + industryId +
                ", industry='" + industry + '\'' +
                '}';
    }
}
