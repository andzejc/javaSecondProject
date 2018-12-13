
<%@page import="com.work.PersonsDAO"%>
<%@page import="com.work.Persons"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.Query"%>
<%@page import="com.work.EMF"%>
<%@page import="javax.persistence.EntityManager"%>
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
            List<Persons> list = PersonsDAO.getAll(request);
        %>
        <table>
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Birth Date</th>
                <th>Salary</th>
                <th>
                    <button class="button2">
                        <a href="editPerson.jsp" >New Person </a>
                    </button>
                </th>
            </tr>
            <%
                for (Persons p : list) {
            %> 

            <tr>
                <td>
                    <%=p.getFirstName()%>
                </td>
                <td>
                    <%=p.getLastName()%>
                </td>
                <td>
                    <%=p.getBirtchDate()%>
                </td>
                <td>
                    <%=p.getSalary()%>
                </td>
                <td>
                    <button class="button2">
                        <a href="addr.jsp?personId=<%=p.getId()%>">Addresses</a>
                    </button>

                </td>
                <td>
                    <button class="button2">
                        <a href="cont.jsp?personId=<%=p.getId()%>">Contacts</a>
                    </button>

                </td>
                <td>
                    <button class="button2">
                        <a href="editPerson.jsp?personId=<%=p.getId()%>">Edit</a>
                    </button>

                </td>
                <td>
                    <button class="button2">
                        <a href="deletePerson?personId=<%=p.getId()%>">Delete</a>
                    </button>

                </td>
            </tr>
            <%}%>
        </table>

    </body>
</html>
