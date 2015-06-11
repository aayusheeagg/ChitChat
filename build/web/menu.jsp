<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome - Chit-Chat</title>
        <link href="css/overcast/jquery-ui-1.9.2.custom.css" rel="stylesheet">
        <script src="js/jquery-1.8.3.js"></script>
        <script src="js/jquery-ui-1.9.2.custom.js"></script>
        <script>
            $(function() {
                $("#tabs").tabs();
            });
        </script>        
    </head>
    <body>
        <div id="page">
            <br><br>
            <div id="heading">Welcome ${person}, <a href="RequestManager?submit=logout" style="float: right;text-decoration: none; color: white; margin-right: 12%">Logout</a></div><br>
            <div id="tabs"  style="width: 85%; background: #728DCF; margin-left: 4%;">
                <ul>
                    <li><a href="#tabs-1">Edit profile</a></li>
                    <li><a href="#tabs-3">Enter Chatroom</a></li>
                    <li><a href="#tabs-4">Change password</a></li>
                </ul>
                <div id = "tabs-1">
                    <form action="UpdateUser" method="POST" enctype="multipart/form-data">
                        <table cellpadding="15" align="center">
                            <tr>
                                <td>
                                    <table border="0" cellpadding="10">
                                        <tbody>
                                            <tr>
                                                <td>Name : </td>
                                                <td><input type="text" required name="name" size="30" value="${s.name}" />
                                                    <br><c:if test="${!empty errors}">
                                                        <span class="error">${errors['name']}</span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Mobile Number : </td>
                                                <td><input type="text" name="mobile" size="30" value="${s.mobileNumber}" />
                                                    <br><c:if test="${!empty errors}">
                                                        <span class="error">${errors['mobile']}</span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Email : </td>
                                                <td><input type="text" required name="email"  size="30" value="${s.email}"/>
                                                    <br><c:if test="${!empty errors}">
                                                        <span class="error">${errors['email']}</span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>DOB (mm-dd-yyyy) : </td>
                                                <td><input type="text" required name="dob" size="30" value="${s.dob}" /></td>
                                            </tr>
                                            <tr>
                                                <td>City : </td>
                                                <td><input type="text" name="city"  size="30" value="${s.city}" />
                                                    <br><c:if test="${!empty errors}">
                                                        <span class="error">${errors['city']}</span>
                                                    </c:if>
                                                </td>
                                            </tr> 
                                            <tr>
                                                <td colspan="2" align="center">
                                                    &nbsp; &nbsp;
                                                    <input type="submit" value="Update"/> 
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                                <td valign="top">
                                    <img src="uploads/${s.photo}" height="180px" width="200px"/><br>
                                    <input type="file" name="photo"/>
                                </td>
                            </tr>
                        </table>
                        <b><c:if test="${updated}">
                                <h3 style="color:green">Updated successfully.</h3>
                            </c:if>     
                        </b>
                    </form>
                </div>

                <div id = "tabs-3">
                    <form method="post" action="ChatManager">
                        <br>
                        <c:forEach items="${hashmap}" var="iter">
                            <br>
                            <input type="radio" name="enter" value="<c:out value="${iter.key}"/>"/>
                            <font color="white" size="3px">${iter.key}</font>
                            <br>
                        </c:forEach>  <br><br>
                        <input type="submit" name="chat" value="Enter room"/> 
                    </form>
                    <b><c:if test="${notselected}">
                                <h3 style="color:red">Select one room.</h3>
                            </c:if>     
                        </b>
                </div>

                <div id = "tabs-4">

                    <form action="RequestManager" method="POST">
                        <table border="0" cellpadding="10">
                            <tbody>
                                <tr>
                                    <td>Username : </td>
                                    <td><input type="text" required name="username" size="30" /></td>
                                </tr>
                                <tr>
                                    <td>Current Password : </td>
                                    <td><input type="password" required name="currentpass" size="30" /></td>
                                </tr>
                                <tr>
                                    <td>New Password : </td>
                                    <td><input type="password" required name="newpass"  size="30"/></td>
                                </tr>
                                <tr>
                                    <td>Confirm Password : </td>
                                    <td><input type="password" required name="confirmpass"  size="30" /></td>
                                </tr> 
                                <tr>
                                    <td colspan="2" align="center">
                                        &nbsp; &nbsp;
                                        <input type="submit" name="submit" value="changepasswd"/> 
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <b><c:if test="${wrong}">
                                <h3 style="color: red">Sorry,Password does not match.</h3>
                            </c:if>     
                        </b>
                    </form>
                </div>            
            </div> 
            <br><br> <br><br><br>
        </div>
    </body>
</html>