<%-- 
    Document   : addr
    Created on : Nov 13, 2018, 10:36:10 AM
    Author     : HP
--%>

<%@page import="com.work.ContactsDAO"%>
<%@page import="com.work.Contacts"%>
<%@page import="com.work.AddressesDAO"%>
<%@page import="com.work.Addresses"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="com.work.EMF"%>
<%@page import="com.work.Persons"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            *{
                color: black;
                text-decoration: none;
            }
            a{
                text-decoration: none;
                color: black;
                width: 100%;
                height: 100%;
            }
            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            td, th {
                text-align: center;
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }

            tr:nth-child(even) {
                background-color: #dddddd;
            }
            .button2{
                /*width: 150px;*/
                height: 25px;
                background-color: whitesmoke;
                font-size: 16px; 
                font-weight: 700;
                border-radius: 5px
            }
        </style>
    </head>
    <body>
        <%
            List<Contacts> l = ContactsDAO.byClient(request);
            String personIdStr = request.getParameter("personId");
        %> 

        <table>
            <tr>
                <th>
                    <button class="button2">
                        <a href="addContacts.jsp?personId=<%=personIdStr%>">Add</a> 
                    </button> 
                </th>

            </tr> 
            <tr>
                <th>
                    Contact Type
                </th>
                <th>
                    Contact
                </th>
            </tr> 
            <%
                if (l != null)
                    for (Contacts c : l) {
            %> <tr>
                <td>
                    <%=c.getContactType()%>
                </td>
                <td>
                    <%=c.getContact()%>
                </td>
                <td>
                    <button class="button2">
                        <a href="addContacts.jsp?id=<%= c.getId()%>&personId=<%=personIdStr%>">Edit</a> 
                    </button> 
                </td>
                <td>
                    <button class="button2">
                        <a href="deleteContact?id=<%= c.getId()%>&personId=<%=personIdStr%>">Delete</a> 
                    </button> 
                </td>
            </tr>
            <%}%>
            <tr>
                <td>
                    <button class="button2">    
                        <a href="index.jsp" >Back</a>
                    </button> 
                </td>
            </tr>
        </table>
    </body>
</html>
