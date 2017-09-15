package com.gjj.igden.controller;

import com.gjj.igden.model.Account;
import com.gjj.igden.service.accountService.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

@Controller
public class AccountController {
  @Autowired
  private AccountService service;

  @RequestMapping(value = "/admin/list-accounts", method = RequestMethod.GET)
  public String showAccounts(ModelMap model) {
    model.addAttribute("ACCOUNT_LIST", service.getAccountList());
    return "list-accounts";
  }

  @RequestMapping(value = "/edit-account", method = RequestMethod.POST)
  public String putEditedAccountToDb(Account account, RedirectAttributes redirectAttributes) {
    boolean editStatus = service.updateAccount(account);
    if (editStatus) {
      redirectAttributes.addAttribute("success", "true");
    } else {
      System.err.println("editing failed");
    }
    return "redirect:/list-accounts";
  }

  @RequestMapping(value = "/add-account", method = RequestMethod.GET)
  public String createNewAccountGet(ModelMap model) {
    model.addAttribute("account", new Account());
    return "add-account";
  }

  @RequestMapping(value = "/processAddAccount1", method = RequestMethod.POST)
  public String createAccountPost1(@RequestParam(value = "username1", required = false) String username1) {
    Account account = new Account();
    account.setAccountName(username1);
    service.createAccount(account);
    return "redirect:/list-accounts";
  }

  @RequestMapping(value = "/processAddAccount", method = RequestMethod.POST)
  public String createAccountPost2(@RequestParam(value = "username1", required = false) String username1) {
    Account account = new Account();
    account.setAccountName(username1);
    service.createAccount(account);
    return "redirect:/list-accounts";
  }

  @RequestMapping(value = "/add-account", method = RequestMethod.POST)
  public String createAccountPost(Account account, @RequestParam(value = "username1", required = false) String username1) {
    System.out.println(account.getAdditionalInfo());
    service.createAccount(account);
    return "redirect:/list-accounts";
  }

  @RequestMapping(value = "/delete-account", method = RequestMethod.GET)
  public String deleteAccount(@RequestParam int id) {
    if (service.delete(id)) {
      return "redirect:/list-accounts";
    } else {
      return "errorPage";
    }
  }

  @RequestMapping(value = "/edit-account", method = RequestMethod.GET)
  public String getAccountToEditAndPopulateForm(ModelMap model, @RequestParam int id) {
    Account account = service.retrieveAccount(id);
    service.updateAccount(account);
    model.addAttribute("account", account);
    return "edit-account";
  }

  @RequestMapping(value = "/getImage", method = RequestMethod.GET)
  @ResponseBody
  public byte[] showImage(@RequestParam("accId") int itemId/*, HttpServletResponse response*/)
    throws ServletException, IOException {
    byte[] buffer = service.getImage(itemId);
    return buffer;
  }

  @PostMapping("/uploadImage") //new annotation since 4.3 todo make this new annotation everywhere
  public String setNewImage(@RequestParam("image") MultipartFile imageFile,
                            RedirectAttributes redirectAttributes,
                            Account account) {
    if (imageFile.isEmpty()) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
      return "redirect:uploadStatus";
    }
    try {
      byte[] bytes = imageFile.getBytes();
      InputStream imageConvertedToInputStream = new ByteArrayInputStream(bytes);
      service.setImage(account.getId(), imageConvertedToInputStream);
      redirectAttributes.addFlashAttribute("message",
        "You successfully uploaded '" + imageFile.getOriginalFilename() + "'");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "redirect:/uploadStatus";
  }

  @GetMapping("/uploadStatus")
  public String uploadStatus() {
    return "uploadStatus";
  }

  @RequestMapping(value = "/view-account", method = RequestMethod.GET)
  public String viewAccount(ModelMap model, @RequestParam int id) {
    Account account = service.retrieveAccount(id);
    model.addAttribute("watchLists", account.getAttachedWatchedLists());
    model.addAttribute("account", account);
    return "view-account";
  }
}
