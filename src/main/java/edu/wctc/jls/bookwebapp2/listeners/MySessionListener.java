/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jls.bookwebapp2.listeners;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class MySessionListener implements HttpSessionListener {
    private static int hitCount = 0;

    /**
     * Automatically called by server when a user connects to the application.
     * Currently used to keep track of the total number of hits for the 
     * application, where a 'hit' is defined as application access by one user.
     * 
     * @param se - automatically triggered when user connects to application.
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ++hitCount;
        ServletContext ctx = se.getSession().getServletContext();
        
        // Carefull -- Multiple threads can access this.
        synchronized(ctx) {
            ctx.setAttribute("hitCount", hitCount);
        }

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }

    public static int getHitCount() {
        return hitCount;
    }
    
}
