/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.work;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "persons")
@NamedQueries({
    @NamedQuery(name = "Persons.findAll", query = "SELECT p FROM Persons p")
    , @NamedQuery(name = "Persons.findById", query = "SELECT p FROM Persons p WHERE p.id = :id")
    , @NamedQuery(name = "Persons.findByFirstName", query = "SELECT p FROM Persons p WHERE p.firstName = :firstName")
    , @NamedQuery(name = "Persons.findByLastName", query = "SELECT p FROM Persons p WHERE p.lastName = :lastName")
    , @NamedQuery(name = "Persons.findByBirtchDate", query = "SELECT p FROM Persons p WHERE p.birtchDate = :birtchDate")
    , @NamedQuery(name = "Persons.findBySalary", query = "SELECT p FROM Persons p WHERE p.salary = :salary")})
public class Persons implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birtch_date")
    @Temporal(TemporalType.DATE)
    private Date birtchDate;
    @Column(name = "salary")
    private BigDecimal salary;
    @OneToMany(mappedBy = "personId")
    private List<Addresses> addressesList;
    @OneToMany(mappedBy = "personId")
    private List<Contacts> contactsList;

    public Persons() {
    }

    public Persons(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirtchDate() {
        return birtchDate;
    }

    public void setBirtchDate(Date birtchDate) {
        this.birtchDate = birtchDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public List<Addresses> getAddressesList() {
        return addressesList;
    }

    public void setAddressesList(List<Addresses> addressesList) {
        this.addressesList = addressesList;
    }

    public List<Contacts> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<Contacts> contactsList) {
        this.contactsList = contactsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persons)) {
            return false;
        }
        Persons other = (Persons) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.work.Persons[ id=" + id + " ]";
    }
    
}
