package org.lesson.java.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    //*pizza da cui dipendo
    @ManyToOne
    @JoinColumn(name = "pizza_id, nullable = false") 
    private Pizza pizza;

    @NotNull(message = "The start of special date can't be null")
    @PastOrPresent(message = "The special date must start in currente day")
    private LocalDate specialDate;

    @NotNull(message = "The end of the special date can't be null")
    @FutureOrPresent(message = "The end of special day must finisch on same day or shortly afther")
    private LocalDate endOfSpecialDate;

    @Lob
    private String notes;


    
}
