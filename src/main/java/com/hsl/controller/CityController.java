package com.hsl.controller;

import com.hsl.model.City;
import com.hsl.model.Nation;
import com.hsl.service.ICityService;
import com.hsl.service.INationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CityController {

    @Autowired
    private ICityService cityService;

    @Autowired
    private INationService nationService;

    @ModelAttribute("nations")
    public Iterable<Nation> nations(){
        return nationService.findAll();
    }

    @GetMapping("/create-city")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/create-city")
    public ModelAndView savecity(@Validated @ModelAttribute("city") City city, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("/city/create");
        }

        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("city", new City());
        modelAndView.addObject("success", "New city created successfully");
        return modelAndView;
    }

    @GetMapping("/cities")
    public ModelAndView listcities(@RequestParam("text") Optional<String> search, @PageableDefault(sort = {"name"}, value = 5) Pageable pageable) {
//        Page<city> cities = cityService.findAll(pageable);
//        Iterable<city> cities = citieservice.findAll();
        Page<City> cities;
        if(search.isPresent()){
            cities = cityService.findAllByNameContaining(search.get(), pageable);
        } else {
            cities = cityService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/city/index");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/edit-city/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/city/edit");
            modelAndView.addObject("city", city.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-city")
    public ModelAndView updateCity(@Validated @ModelAttribute("city") City city, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("/city/edit");
        }

        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("/city/edit");
        modelAndView.addObject("city", city);
        modelAndView.addObject("success", "City updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-city/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/city/delete");
            modelAndView.addObject("city", city.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-city")
    public String deleteCity(@ModelAttribute("city") City city) {
        cityService.remove(city.getId());
        return "redirect:cities";
    }

    @GetMapping("/view-city/{id}")
    public ModelAndView viewCity(@PathVariable("id") Long id){
        Optional<City> city = cityService.findById(id);
        if(!city.isPresent()){
            return new ModelAndView("/error.404");
        }

//        Iterable<City> cities = cityService.findAllByNation(nationOptional.get());
//
//        ModelAndView modelAndView = new ModelAndView("/nation/view");
//        modelAndView.addObject("nation", nationOptional.get());
//        modelAndView.addObject("cities", cities);
//        return modelAndView;

        ModelAndView modelAndView = new ModelAndView("/city/view");
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }
}