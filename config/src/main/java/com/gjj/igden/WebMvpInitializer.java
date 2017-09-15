package com.gjj.igden;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

public class WebMvpInitializer
  extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[]{WebMvcConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[0];
  }

  @Override
  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    // temp file will be uploaded here
    File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));
    // register a MultipartConfigElement
    int maxUploadSizeInMb = 5 * 1024 * 1024;
    MultipartConfigElement multipartConfigElement =
      new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
        maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);
    registration.setMultipartConfig(multipartConfigElement);
  }
}
