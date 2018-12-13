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
public class AddressesDAO {

    public static List<Addresses> byClient(HttpServletRequest request) {

        String personIdStr = request.getParameter("personId");
        Integer personId = null;
        try {
            personId = Integer.parseInt(personIdStr);
        } catch (NumberFormatException e) {
        }
        Persons p = null;
        List<Addresses> l = null;
        if (personId != null) {
            EntityManager em = (EntityManager) request.getAttribute("em");
            p = em.find(Persons.class, personId);
            l = p.getAddressesList();
        }
        return l;
    }

    public static Addresses getOne(HttpServletRequest request) {
        String addrIdStr = request.getParameter("id");
        Integer addrId = null;
        try {
            addrId = Integer.parseInt(addrIdStr);
        } catch (NumberFormatException e) {
        }
        Addresses a = null;
        if (addrId != null) {
            EntityManager em = (EntityManager) request.getAttribute("em");
            a = em.find(Addresses.class, addrId);
        }
        return a;
    }

    public static Addresses save(HttpServletRequest request) {
        String persIdString = request.getParameter("personId");
        Integer persId = null;
        try {
            persId = Integer.parseInt(persIdString);
        } catch (NumberFormatException e) {
        }

        String idString = request.getParameter("id");
        System.out.println("idString: " + idString);
        Integer id = null;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
        }

        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String postalCode = request.getParameter("postalCode");

        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx;
        try {
            tx = EMF.getTransaction(em);
        } catch (Exception ex) {
            return null;
        }

        Addresses addr = null;
        if (id != null) {
            addr = em.find(Addresses.class, id);

        }
        if (addr != null) {
            addr.setAddress(address);
            addr.setCity(city);
            addr.setPostalCode(postalCode);
        } else {

            addr = new Addresses();
            Persons p = em.find(Persons.class, persId);
            addr.setPersonId(p);
            addr.setAddress(address);
            addr.setCity(city);
            addr.setPostalCode(postalCode);
            em.persist(addr);
        }
        try {
            EMF.commitTransaction(tx);
        } catch (Exception ex) {
            EMF.rollbackTransaction(tx);
            return null;
        }
        return addr;
    }

    public static Addresses delete(HttpServletRequest request) {

        String addrIdStr = request.getParameter("id");
        Integer addrId = null;
        try {
            addrId = Integer.parseInt(addrIdStr);
        } catch (NumberFormatException e) {
        }
        Addresses a = null;
        if (addrId != null) {
            EntityManager em = (EntityManager) request.getAttribute("em");
            EntityTransaction tx;
            try {
                tx = EMF.getTransaction(em);
            } catch (Exception ex) {
                return null;
            }
            a = em.find(Addresses.class, addrId);
            em.remove(a);
            try {
                EMF.commitTransaction(tx);
            } catch (Exception ex) {
                EMF.rollbackTransaction(tx);
                return null;
            }
        }
        return a;
    }
}
