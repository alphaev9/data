package com.alpha.data.jdbcTemplate.controller;

import com.alpha.data.domain.Address;
import com.alpha.data.jdbcTemplate.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping("")
    public String home() {
        return "index";
    }

    @RequestMapping("statistic")
    public String statistic(Model model) {
        Integer amount = addressService.statistic();
        model.addAttribute("backlogAmount", amount);
        return "result";
    }

    @RequestMapping("checkStreet")
    public ModelAndView checkStreet(String street) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("result");
        List<Address> addresses = addressService.checkStreet(street);
        mv.addObject("addressInStreet", addresses);
        return mv;
    }

    @RequestMapping("integrated")
    public String integrated() {
        addressService.integrated();
        return "result";
    }
}
