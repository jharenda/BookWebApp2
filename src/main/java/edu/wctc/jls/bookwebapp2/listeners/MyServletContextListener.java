/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jls.bookwebapp2.listeners;

import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class MyServletContextListener implements ServletContextListener {

    /**
     * Automatically called by server when application starts up. Currently
     * used to date stamp the event.
     * 
     * @param event - automatically triggered when application starts
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        Date date = new Date();
        sc.setAttribute("appStarted", date);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // nothing to do here
    }

}