package com.debuglife.vaadinstudy;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vaadin.server.VaadinServlet;


public class VaadinstudyServlet extends VaadinServlet {

    private static final long serialVersionUID = -488345215180407426L;

    
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("server() is called");
    }



    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("servlet is initing...");
    }



    @Override
    public void destroy() {
        System.out.println("servelt has been destroyed.");
        super.destroy();
    }
   
}
