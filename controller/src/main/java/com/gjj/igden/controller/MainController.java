package com.gjj.igden.controller;

import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
  @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
  public ModelAndView defaultPage() {
    ModelAndView model = new ModelAndView();
    model.addObject("title", "Spring Security Remember Me");
    model.addObject("message", "This is default page!");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth.getPrincipal().equals("anonymousUser")) {
      model.setViewName("login");
    } else {
      model.setViewName("redirect:" + "/admin/list-accounts");
    }
    return model;
  }

  @RequestMapping(value = {"/admin/search-auth"}, method = RequestMethod.GET)
  public String searchPage1() {
    return "search-auth";
  }

  @RequestMapping(value = "/admin**", method = RequestMethod.GET)
  public ModelAndView adminPage() {
    ModelAndView model = new ModelAndView();
    model.addObject("title", "Spring Security Remember Me");
    model.addObject("message", "This page is for ROLE_ADMIN only!");
    model.setViewName("admin");
    return model;
  }

  @RequestMapping(value = "/logout**", method = RequestMethod.GET)
  public String logOut() {
    return "redirect:login";
  }

  @GetMapping(value = "/ajax-stock")
  public String ajaxStock() {
    return "ajax-stock";
  }

  @RequestMapping(value = "/admin/update**", method = RequestMethod.GET)
  public ModelAndView updatePage(HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    if (isRememberMeAuthenticated()) {
      //send login for update
      setRememberMeTargetUrlToSession(request);
      model.addObject("loginUpdate", true);
      model.setViewName("/login");
    } else {
      model.setViewName("update");
    }
    return model;
  }

  @RequestMapping(value = "/login**", method = RequestMethod.GET)
  public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    if (error != null) {
      model.addObject("error", "Invalid username and password!");
      String targetUrl = getRememberMeTargetUrlFromSession(request);
      //System.out.println(targetUrl);
      if (StringUtils.hasText(targetUrl)) {
        model.addObject("targetUrl", targetUrl);
        model.addObject("loginUpdate", true);
      }
    }
    if (logout != null) {
      model.addObject("msg", "You've been logged out successfully.");
    }
    model.setViewName("login");
    return model;
  }

  private boolean isRememberMeAuthenticated() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication != null && RememberMeAuthenticationToken.
      class.isAssignableFrom(authentication.getClass());
  }

  private void setRememberMeTargetUrlToSession(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.setAttribute("targetUrl", "/admin/update");
    }
  }

  private String getRememberMeTargetUrlFromSession(HttpServletRequest request) {
    String targetUrl = "admin";
    HttpSession session = request.getSession(false);
    if (session != null) {
      targetUrl = session.getAttribute("targetUrl") == null
        ? "admin" : session.getAttribute("targetUrl").toString();
    }
    return targetUrl;
  }
}