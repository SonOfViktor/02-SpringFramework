package com.epam.esm.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final String PROFILE_INIT_PARAMETER_KEY = "spring.profiles.active";
    private static final String PROFILE_INIT_PARAMETER_VALUE = "prod";
    private static final String DISPATCHER_SERVLET_MAPPING = "/";

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {DISPATCHER_SERVLET_MAPPING};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.setInitParameter(PROFILE_INIT_PARAMETER_KEY, PROFILE_INIT_PARAMETER_VALUE);
    }

    @Override
    protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return dispatcherServlet;
    }
}

