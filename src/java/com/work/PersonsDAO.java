/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.work;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author HP
 */
public class PersonsDAO {

    public static List<Persons> getAll(HttpServletRequest request) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        Query q = em.createNamedQuery("Persons.findAll");
        List<Persons> list = q.getResultList();
        return list;
    }

    public static Persons getOne(HttpServletRequest request) {
        String personIdStr = request.getParameter("personId");
        Integer personId = null;
        try {
            personId = Integer.parseInt(personIdStr);
        } catch (NumberFormatException e) {
        }
        Persons p = null;
        if (personId != null) {
            EntityManager em = (EntityManager) request.getAttribute("em");
            p = em.find(Persons.class, personId);
        }
        return p;
    }

    public static Persons save(HttpServletRequest request) {

        String idString = request.getParameter("id");
        Integer id = null;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
        }

        String name = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        String dateStr = request.getParameter("birthData");

        DateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = (Date) formatter.parse(dateStr);
        } catch (ParseException ex) {
        }

        BigDecimal salary = BigDecimal.ZERO;
        try {
            salary = new BigDecimal(request.getParameter("salary"));
        } catch (Exception e) {
        }

        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx;
        try {
            tx = EMF.getTransaction(em);
        } catch (Exception ex) {
            return null;
        }

        Persons per = null;
        if (id != null) {
            per = em.find(Persons.class, id);

        }
        if (per != null) {
            per.setFirstName(name);
            per.setLastName(lastName);
            per.setBirtchDate(date);
            per.setSalary(salary);
        } else {
            per = new Persons();
            per.setFirstName(name);
            per.setLastName(lastName);
            per.setBirtchDate(date);
            per.setSalary(salary);
            em.persist(per);
        }
        try {
            EMF.commitTransaction(tx);
        } catch (Exception ex) {
            EMF.rollbackTransaction(tx);
            return null;
        }
        return per;

    }

    public static Persons delete(HttpServletRequest request) {

        String personIdStr = request.getParameter("personId");
        Integer id = null;
        try {
            id = Integer.parseInt(personIdStr);
        } catch (NumberFormatException e) {
        }
        Persons p = null;
        if (id != null) {
            EntityManager em = (EntityManager) request.getAttribute("em");
            EntityTransaction tx;
            try {
                tx = EMF.getTransaction(em);
            } catch (Exception ex) {
                return null;
            }
            p = em.find(Persons.class, id);
            em.remove(p);
            try {
                EMF.commitTransaction(tx);
            } catch (Exception ex) {
                EMF.rollbackTransaction(tx);
                return null;
            }
        }
        return p;
    }

}
