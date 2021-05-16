package com.hsl.controller;

import com.hsl.model.City;
import com.hsl.model.Nation;
import com.hsl.service.CityService;
import com.hsl.service.INationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class NationController {

    @Autowired
    private INationService nationService;

    @Autowired
    private CityService cityService;

    @GetMapping("/nations")
    public ModelAndView listnations() {
        Iterable<Nation> nations = nationService.findAll();
        ModelAndView modelAndView = new ModelAndView("/nation/list");
        modelAndView.addObject("nations", nations);
        return modelAndView;
    }

    @GetMapping("/create-nation")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/nation/create");
        modelAndView.addObject("nation", new Nation());
        return modelAndView;
    }

    @PostMapping("/create-nation")
    public ModelAndView savenation(@ModelAttribute("nation") Nation nation) {
        nationService.save(nation);

        ModelAndView modelAndView = new ModelAndView("/nation/create");
        modelAndView.addObject("nation", new Nation());
        modelAndView.addObject("message", "New nation created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-nation/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Nation> nation = nationService.findById(id);
        if (nation.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/nation/edit");
            modelAndView.addObject("nation", nation.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-nation")
    public ModelAndView updatenation(@ModelAttribute("nation") Nation nation) {
        nationService.save(nation);
        ModelAndView modelAndView = new ModelAndView("/nation/edit");
        modelAndView.addObject("nation", nation);
        modelAndView.addObject("message", "nation updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-nation/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Nation> nation = nationService.findById(id);
        if (nation.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/nation/delete");
            modelAndView.addObject("nation", nation.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-nation")
    public String deletenation(@ModelAttribute("nation") Nation nation) {
        nationService.remove(nation.getId());
        return "redirect:nations";
    }

    @GetMapping("/view-nation/{id}")
    public ModelAndView viewnation(@PathVariable("id") Long id){
        Optional<Nation> nationOptional = nationService.findById(id);
        if(!nationOptional.isPresent()){
            return new ModelAndView("/error.404");
        }

        Iterable<City> cities = cityService.findAllByNation(nationOptional.get());

        ModelAndView modelAndView = new ModelAndView("/nation/view");
        modelAndView.addObject("nation", nationOptional.get());
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }
}