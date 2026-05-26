package org.lesson.java.spring_la_mia_pizzeria_crud.controller;

import org.lesson.java.spring_la_mia_pizzeria_crud.model.Special;
import org.lesson.java.spring_la_mia_pizzeria_crud.repository.SpecialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/specials")
public class SpecialController {

    @Autowired
    private SpecialRepository repository;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("specialDay") Special formSpecial, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "specials/create-or-edit";
        }

        repository.save(formSpecial);
        return "redirect:/pizzas/" + formSpecial.getPizza().getId();

    }

    // Metodo che restituisce una edit da compilare(con già dati inseriti)
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("specialDay", repository.findById(id).get());
        model.addAttribute("edit", true);
        return "specials/create-or-edit";
    }

    // metodo che effettua una update vera e propria ( con validazione)
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("specialDay") Special formSpecial, BindingResult bindingResult,
            @PathVariable Integer id,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "specials/create-or-edit";

        }
        formSpecial.setId(id);
        repository.save(formSpecial);
        return "redirect:/pizzas/" + formSpecial.getPizza().getId();
    }
}