/*
package com.gjj.igden.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;

@Controller
public class UploadController {
  private static String UPLOADED_FOLDER = "C://temp_upload_spring_test//";

  @GetMapping("/upload")
  public String index() {
    return "upload";
  }

  //@RequestMapping(value = "/upload", method = RequestMethod.POST)
  @PostMapping("/upload") //new annotation since 4.3
  @ResponseStatus(value= HttpStatus.OK)
  public String singleFileUpload(@RequestBody @RequestParam("file") MultipartFile file,
                                 RedirectAttributes redirectAttributes) {
    if (file.isEmpty()) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
      return "redirect:uploadStatus";
    }
    try {
      byte[] bytes = file.getBytes();
      Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
      Files.write(path, bytes);
      redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "redirect:/uploadStatus";
  }

  @PostMapping("/uploadMulti")
  public String multiFileUpload(@RequestParam("files") MultipartFile[] files,
                                RedirectAttributes redirectAttributes) {
    StringJoiner sj = new StringJoiner(" , ");
    for (MultipartFile file : files) {
      if (file.isEmpty()) {
        continue; //next pls
      }
      try {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
        sj.add(file.getOriginalFilename());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    String uploadedFileName = sj.toString();
    if (StringUtils.isEmpty(uploadedFileName)) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
    } else {
      redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + uploadedFileName + "'");
    }
    return "redirect:/uploadStatus";
  }

  @GetMapping("/uploadStatus")
  public String uploadStatus() {
    return "uploadStatus";
  }

  @GetMapping("/uploadMultiPage")
  public String uploadMultiPage() {
    return "uploadMulti";
  }
}*/
