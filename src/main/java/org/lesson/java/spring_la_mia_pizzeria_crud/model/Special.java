package org.lesson.java.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "specials")
public class Special {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // *pizza da cui dipendo
    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @NotNull(message = "The start of special date can't be null")
    @PastOrPresent(message = "The special promotion must start on the current day")
    @Column(name = "startspecial_date", nullable = false)
    private LocalDate startSpecialDate;

    @NotNull(message = "The end of the special date can't be null")
    @FutureOrPresent(message = "The end of the special promotion must end on the same day or a few days later")
    private LocalDate endOfSpecialDate;

    @Lob
    private String notes;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public LocalDate getStartSpecialDate() {
        return this.startSpecialDate;
    }

    public void setStartSpecialDate(LocalDate startSpecialDate) {
        this.startSpecialDate = startSpecialDate;
    }

    public LocalDate getEndOfSpecialDate() {
        return this.endOfSpecialDate;
    }

    public void setEndOfSpecialDate(LocalDate endOfSpecialDate) {
        this.endOfSpecialDate = endOfSpecialDate;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
