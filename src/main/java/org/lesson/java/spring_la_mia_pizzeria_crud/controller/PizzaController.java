package org.lesson.java.spring_la_mia_pizzeria_crud.controller;

import org.lesson.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lesson.java.spring_la_mia_pizzeria_crud.model.Special;
import org.lesson.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    private final PizzaRepository pizzaRepository;

    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("pizzas", pizzaRepository.findAll());
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Pizza pizza = pizzaRepository.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "pizzas/create";
        } else {
            // salvare il dato
            pizzaRepository.save(formPizza);
            return "redirect:/pizzas";

        }

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = pizzaRepository.findById(id).orElseThrow();
        model.addAttribute("pizza", pizza);

        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult,
            @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "pizzas/edit";
        }

        formPizza.setId(id);
        pizzaRepository.save(formPizza);
        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        // ! process POST request

        pizzaRepository.deleteById(id);
        return "redirect:/pizzas";
    }

    @GetMapping({ "/{id}/special", "/{id}/specials" })
    public String special(@PathVariable Integer id, Model model) {
        Special special = new Special();
        special.setPizza(pizzaRepository.findById(id).get());
        // ...
        model.addAttribute("specialDay", special);
        return "specials/create-or-edit";
    }

}
