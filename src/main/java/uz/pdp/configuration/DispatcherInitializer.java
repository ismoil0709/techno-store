package uz.pdp.configuration;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@NonNullApi
public class DispatcherInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcConfiguration.class};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/media/ismoil_0709/d disk/G30/season8/technoStore/src/main/resources/files/temp");
        registration.setMultipartConfig(multipartConfigElement);
    }
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
