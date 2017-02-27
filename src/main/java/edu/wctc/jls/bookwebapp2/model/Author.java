/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jls.bookwebapp2.model;

import java.util.Date;
import java.util.Objects;

/**
 * This is a domain object, also called an "entity" object. 
 * 
 * @author jlombardo
 */
public class Author {
    private String auth_Name;
    private Integer auth_ID;
    
    private Date date;

    public Author() {
    }

    public Author(Integer authorId) {
        this.auth_ID = authorId;
    }

    public Author(Integer auth_ID, String auth_Name, Date date) {
        this.auth_ID = auth_ID;
        this.auth_Name = auth_Name;
        this.date = date;
    }

    public final Integer getAuth_ID() {
        return auth_ID;
    }

    public final void setAuth_ID(Integer auth_ID) {
        this.auth_ID = auth_ID;
    }

    public final String getAuth_Name() {
        return auth_Name;
    }

    public final void setAuth_Name(String auth_Name)  {
      
        this.auth_Name = auth_Name;
    }

    public final Date getDate() {
        return date;
    }

    public final void setDate(Date date)  {
         this.date = date;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.auth_ID);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (!Objects.equals(this.auth_ID, other.auth_ID)) {
            return false;
        }
        return true;
    }
}
