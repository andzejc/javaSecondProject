<%-- 
    Document   : addAddress
    Created on : Nov 13, 2018, 10:13:53 PM
    Author     : HP
--%>

<%@page import="com.work.AddressesDAO"%>
<%@page import="com.work.Addresses"%>
<%@page import="org.apache.tomcat.jni.Address"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="com.work.EMF"%>
<%@page import="javax.persistence.EntityManager"%>
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
            String personIdStr = request.getParameter("personId");
            Addresses a = AddressesDAO.getOne(request);
        %> 
        <form action="addAddress" method="POST">
            <input type="hidden" name="id" value="<%=(a == null) ? "" : a.getId()%>">
            <input type="hidden" name="personId" value="<%=personIdStr%>">
            <input type="text" name="address" placeholder="Address" required="required"  value="<%=(a == null) ? "" : a.getAddress()%>">
            <input type="text" name="city" placeholder="City" required="required"  value="<%=(a == null) ? "" : a.getCity()%>">
            <input  type='text' name="postalCode" placeholder="Postal code" required="required" value="<%=(a == null) ? "" : a.getPostalCode()%>">
            <button class="button2">Save</button>
        </form>
        <button class="button2" >
            <a href="addr.jsp?personId=<%=personIdStr%>">Back</a>
        </button>
    </body>
</html>
