package com.gjj.igden.controller;

import com.gjj.igden.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class TestController {
  @RequestMapping(value = "/lazyRowLoad", method = RequestMethod.GET)
  public ModelAndView printWelcome(@ModelAttribute("user") Users user) {
    ModelAndView mav = new ModelAndView("lazyRowLoad");
    mav.addObject("message", "Hello World!!!");
    return mav;
  }


}
