/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.work;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author HP
 */
public class ContactsDAO {

    public static List<Contacts> byClient(HttpServletRequest request) {

        String personIdStr = request.getParameter("personId");
        Integer personId = null;
        try {
            personId = Integer.parseInt(personIdStr);
        } catch (NumberFormatException e) {
        }
        Persons p = null;
        List<Contacts> l = null;
        if (personId != null) {
            EntityManager em = (EntityManager) request.getAttribute("em");
            p = em.find(Persons.class, personId);
            l = p.getContactsList();
        }
        return l;
    }

    public static Contacts getOne(HttpServletRequest request) {
        String contIdStr = request.getParameter("id");
        Integer contId = null;
        try {
            contId = Integer.parseInt(contIdStr);
        } catch (NumberFormatException e) {
        }
        Contacts c = null;
        if (contId != null) {
            EntityManager em = (EntityManager) request.getAttribute("em");
            c = em.find(Contacts.class, contId);
        }
        return c;
    }

    public static Contacts save(HttpServletRequest request) {
        String persIdString = request.getParameter("personId");
        Integer persId = null;
        try {
            persId = Integer.parseInt(persIdString);
        } catch (NumberFormatException e) {
        }

        String idString = request.getParameter("id");
        Integer id = null;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
        }

        String contactType = request.getParameter("contactType");
        String contact = request.getParameter("contact");

        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx;
        try {
            tx = EMF.getTransaction(em);
        } catch (Exception ex) {
            return null;
        }

        Contacts cont = null;
        if (id != null) {
            cont = em.find(Contacts.class, id);

        }
        if (cont != null) {
            cont.setContactType(contactType);
            cont.setContact(contact);

        } else {

            cont = new Contacts();
            Persons p = em.find(Persons.class, persId);
            cont.setPersonId(p);
            cont.setContactType(contactType);
            cont.setContact(contact);
            em.persist(cont);
        }
        try {
            EMF.commitTransaction(tx);
        } catch (Exception ex) {
            EMF.rollbackTransaction(tx);
            return null;
        }
        return cont;
    }

    public static Contacts delete(HttpServletRequest request) {

        String contIdStr = request.getParameter("id");
        Integer contId = null;
        try {
            contId = Integer.parseInt(contIdStr);
        } catch (NumberFormatException e) {
        }
        Contacts c = null;
        if (contId != null) {
            EntityManager em = (EntityManager) request.getAttribute("em");
            EntityTransaction tx;
            try {
                tx = EMF.getTransaction(em);
            } catch (Exception ex) {
                return null;
            }
            c = em.find(Contacts.class, contId);
            em.remove(c);
            try {
                EMF.commitTransaction(tx);
            } catch (Exception ex) {
                EMF.rollbackTransaction(tx);
                return null;
            }
        }
        return c;
    }
}
