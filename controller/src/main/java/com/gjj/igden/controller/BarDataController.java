package com.gjj.igden.controller;

import com.gjj.igden.model.Bar;
import com.gjj.igden.service.barService.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BarDataController {
  @Autowired
  private BarService service;

  @RequestMapping(value = "/view-data", method = RequestMethod.GET)
  public String viewAccount(ModelMap model, @RequestParam String stockSymbol) {
    List<Bar> barList = service.getBarList(stockSymbol);
    model.addAttribute("barData", barList);
    return "view-data";
  }

  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public String searchGet1() {
    return "search";
  }

  @RequestMapping(value = "/DataController", method = RequestMethod.GET)
  public String searchGet2(ModelMap model, @RequestParam String searchParam) {
    List<String> tickets = service.searchTickersByChars(searchParam);
    model.addAttribute("THE_SEARCH_RESULT_LIST", tickets);
    return "search";
  }

  @RequestMapping(value = "/search", method = RequestMethod.POST)
  public String searchPost(ModelMap model, @RequestParam String stockSymbol) {
    List<Bar> barList = service.getBarList(stockSymbol);
    model.addAttribute("barData", barList);
    return "view-data";
  }
}
