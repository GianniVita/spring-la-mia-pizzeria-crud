package org.lesson.java.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "NAME must not be null, nor empty or blank")
    @Size(min = 2, max = 120, message = "The name of the pizza must be at list of two caracters")
    @Column(name = "nome_della_pizza", nullable = false)
    private String name;

    @NotBlank(message = "NAME must not be null, nor empty or blank")
    @Size(min = 50, max = 500)
    @Column(name = "pizza_description")
    private String description;

    @NotNull(message = "Price is required")
    @Column(nullable = false)
    private BigDecimal price;

    @Size(max = 500, message = "Photo URL is too long")
    @Column(name = "photo_url")
    private String photo;

    // * aggiunta di una relazione tra UNA PIZZA e 0,1 o più offerte Speciali
    @OneToMany( mappedBy = "pizza", cascade = {CascadeType.REMOVE})
    private List<Special> specials;

    // aggiungo la relazione tra una o piu pizze e gli ingredienti
    @ManyToMany
    @JoinTable(name = "pizza_ingredient",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;


    
    
    public List<Special> getSpecials() {
        return this.specials;
    }
    
    public void setSpecials(List<Special> specials) {
        this.specials = specials;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public String getPhoto() {
        return this.photo;
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return String.format("%s: %s, price: %s, photo: %s", this.name, this.description, this.price, this.photo);
    }
    
}