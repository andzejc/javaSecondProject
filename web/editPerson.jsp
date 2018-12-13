<%-- 
    Document   : editPerson
    Created on : Nov 13, 2018, 11:03:24 AM
    Author     : HP
--%>

<%@page import="com.work.PersonsDAO"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="com.work.EMF"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="java.util.List"%>
<%@page import="com.work.Addresses"%>
<%@page import="com.work.Persons"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            a{
                text-decoration: none;
                color: black;
                width: 100%;
                height: 100%;
            }
            h1{
                color: whitesmoke;
                font-size: 45px;
                font-family:sans-serif;
                padding-top: 50px;
            }
            button{
                width: 200px; 
                height: 50px; 
                background-color: whitesmoke;
                font-size: 20px; 
                font-weight: 700; 
                margin: 5px; 
                border-radius: 5px;
            }
            input{
                width: 250px;
                height: 25px;
                margin: 10px;
                border-radius: 5px;
            }
            .button2{
                width: 150px;
                height: 25px;
                background-color: whitesmoke;
                font-size: 16px; 
                font-weight: 700;
                margin: 10px; 
                border-radius: 5px
            }
            form{
                display: inline-block;
            }

        </style>
    </head>
    <body>
        <%
            Persons p = PersonsDAO.getOne(request);
        %> 
        <form action="editPerson" method="POST">
            <input type="hidden" name="id" value="<%=(p == null) ? "" : p.getId()%>">
            <input type="text" name="firstName" placeholder="Name" required="required" value="<%=(p == null) ? "" : p.getFirstName()%>">
            <input type="text" name="lastName" placeholder="Last name" required="required" value="<%=(p == null) ? "" : p.getLastName()%>">
            <input  type='date' name="birthData" placeholder="Birth data" required="required" value="<%=(p == null) ? "" : p.getBirtchDate()%>">
            <input  type='number' name="salary" placeholder="Salary" required="required" value="<%=(p == null) ? "" : p.getSalary()%>">
            <button class="button2" >Save</button>
            <button class="button2" >
                <a href="index.jsp" >Back</a>
            </button>
        </form>
    </body>
</html>
