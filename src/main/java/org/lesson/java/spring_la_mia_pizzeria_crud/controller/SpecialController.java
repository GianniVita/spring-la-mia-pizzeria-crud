package org.lesson.java.spring_la_mia_pizzeria_crud.controller;

import org.lesson.java.spring_la_mia_pizzeria_crud.model.Special;
import org.lesson.java.spring_la_mia_pizzeria_crud.repository.SpecialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/specials")
public class SpecialController {

    @Autowired
    private SpecialRepository repository;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("specialDay") Special formSpecial, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "specials/create";
        }

        repository.save(formSpecial);
        return "redirect:/pizzas/" + formSpecial.getPizza().getId();

    }
}