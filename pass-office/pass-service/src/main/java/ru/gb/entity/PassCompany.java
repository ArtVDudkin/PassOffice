package ru.gb.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="passes_company")
@Schema(name = "Пропуск юридических лиц")
public class PassCompany {

    public static long sequence = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name= "pass_company",
            joinColumns = @JoinColumn(name="pass_id"),
            inverseJoinColumns = @JoinColumn(name="company_id"))
    private Company company;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name= "pass_person",
            joinColumns = @JoinColumn(name="pass_id"),
            inverseJoinColumns = @JoinColumn(name="person_id"))
    private Person person;
    private String passGroup;
    private LocalDateTime validUntil;
    private String cardUID;     // cardUID is generated proximity-card ID
    private Integer cardNo;     // cardNo is serial number printed on card
    private String ciphers;
    private String allowedFacilities;
    private LocalDateTime issuedAt;
    private LocalDateTime returnedAt;

    public PassCompany(Company company, Person person, String passGroup, LocalDateTime validUntil, String cardUID,
                       Integer cardNo, String ciphers, String allowedFacilities) {
        this.id = sequence++;
        this.company = company;
        this.person = person;
        this.passGroup = passGroup;
        this.validUntil = validUntil;
        this.cardUID = cardUID;
        this.cardNo = cardNo;
        this.ciphers = ciphers;
        this.allowedFacilities = allowedFacilities;
        this.issuedAt = LocalDateTime.now();
        this.returnedAt = null;
    }

}
