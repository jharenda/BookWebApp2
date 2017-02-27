/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jls.bookwebapp2.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Jennifer
 */
public class DateHelper {
        private  DateTimeFormatter dateTimeFormatter = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd"); 

   

    public String currentDate() {
        LocalDateTime currentDate;
       currentDate = LocalDateTime.now();
       String formattedDate =  dateTimeFormatter.format(currentDate); 
        return formattedDate;
    }
    
    
}

