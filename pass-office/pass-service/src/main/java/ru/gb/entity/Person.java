package ru.gb.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="persons")
@Schema(name = "Физические лица")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate birthday;
//    private Document document;
    private String workplace;
    private String position;
    private Integer weight;
    private Integer pin;
    private String note;
    private String photoURL;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name= "person_document",
            joinColumns = @JoinColumn(name="person_id"),
            inverseJoinColumns = @JoinColumn(name="document_id"))
    private Document document;

    @JsonCreator
    public Person(long id, String surname, String name, String patronymic, LocalDate birthday, Document document,
                  String workplace, String position, Integer weight, Integer pin, String note, String photoURL) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.document = document;
        this.workplace = workplace;
        this.position = position;
        this.weight = weight;
        this.pin = pin;
        this.note = note;
        this.photoURL = photoURL;
    }

}
