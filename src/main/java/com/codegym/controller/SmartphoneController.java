package com.codegym.controller;

import com.codegym.model.Smartphone;
import com.codegym.service.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SmartphoneController {
    @Autowired
    private SmartphoneService smartphoneService;

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView createPhone(){
        ModelAndView modelAndView=new ModelAndView("phones/new-phone");
        modelAndView.addObject("sphone",new Smartphone());
        return modelAndView;
    }
//    @RequestBody Smartphone smartphone thực hiện gán dữ liệu từ json nhận được vào các trường tương ứng của smartphone
    @RequestMapping(value = "/createnewPhone", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Smartphone createPhone(@RequestBody Smartphone smartphone){
        return smartphoneService.save(smartphone);
    }
    @RequestMapping(value = "/abc",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Smartphone> allPhones(){
        return smartphoneService.findAll();

    }
    @GetMapping("/abc")
    public ModelAndView allPhonePage(){
        ModelAndView modelAndView=new ModelAndView(("phones/all-phone"));
        modelAndView.addObject("allphone",allPhones());
        return modelAndView;
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Smartphone deteteSmartphone(@PathVariable Integer id){
       return smartphoneService.remove(id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPhone(@PathVariable Integer id){
        Smartphone smartphone = smartphoneService.findById(id);
        if (smartphone !=null){
            ModelAndView modelAndView= new ModelAndView("phones/edit-phone");
            modelAndView.addObject("sphone",smartphone);
            return modelAndView;
        }
        return null;
    }
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Smartphone editPhone(@PathVariable Integer id,@RequestBody Smartphone smartphone){
        smartphone.setId(id);
        return smartphoneService.save(smartphone);
    }
}
