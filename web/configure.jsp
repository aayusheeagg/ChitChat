<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><title>Welcome - Chit-Chat</title>
        <link href="css/overcast/jquery-ui-1.9.2.custom.css" rel="stylesheet">
        <script src="js/jquery-1.8.3.js"></script>
        <script src="js/jquery-ui-1.9.2.custom.js"></script>
        <script>
            $(function() {
                $("#tabs").tabs();
            });
            $(function() {
                $("#datepicker").datepicker({
                    inline: true, dateFormat: "dd-MM-yy", changeYear: true, changeMonth: true, yearRange: "1970:2014"
                });
            });
        </script>        
    </head>
    <body>
        <div id="page">
            <br><br>
            <div id="heading">Welcome ${person}, <a href="RequestManager?submit=logout" style="float: right; text-decoration: none;color: white; margin-right: 12%">Logout</a></div><br>
            <div id="tabs"  style="width: 85%; background: #728DCF; margin-left: 4%;">
                <ul>
                    <li><a href="#tabs-1">View/Delete Users</a></li>
                    <li><a href="#tabs-2">Add Users</a></li>
                    <li><a href="#tabs-3">Configure Chatrooms</a></li>
                    <li><a href="#tabs-5">Enter Chatroom</a></li>
                    <li><a href="#tabs-4">Change password</a></li>
                </ul>
                <div id = "tabs-1">
                    <form method="GET" action="RequestManager">
                        <table align="center" width="100%">
                            <tr bgcolor="#36559E" align="center">
                                <td height="25"></td>
                                <td>Name</td>
                                <td>Username</td>
                                <td>E-Mail</td>
                                <td>Type</td>
                            </tr>
                            <c:forEach items="${list}" var="array" varStatus="loopStatus" >
                                <tr>
                                    <td><input type="checkbox" name="check" value="<c:out value="${array.username}"/>"/></td>
                                    <td><c:out value="${array.name}"/></td>
                                    <td><c:out value="${array.username}"/></td>
                                    <td><c:out value="${array.email}"/></td>
                                    <td><c:out value="${array.rolename}"/></td>  
                                </tr>
                            </c:forEach>
                            <tr>
                                <td>
                                    &nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5" align="center"><input type="submit" name="submit" value="Delete" /><br /><br />
                                    To Delete User(s) select them and click Delete Button.
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>

                <div id = "tabs-2">
                    <form method="post" action="RequestManager">
                        <b>Enter New User Information(All fields are mandatory)</b> <br><br>
                        <table cellpadding="5" align="center">
                            <tr>
                                <td>Name :</td>
                                <td><input type="text" required size="30" name="name"/></td>
                            </tr>
                            <tr>
                                <td>Username : </td>
                                <td><input type="text" required name="username" size="30" /></td>
                            </tr>
                            <tr>
                                <td>Password : </td>
                                <td><input type="password" size="30" name="passwd"/></td>
                            </tr>
                            <tr>
                                <td>E-Mail : </td>
                                <td><input type="text" size="30" name="email"/></td>
                            </tr>
                            <tr>
                                <td>DOB : </td>
                                <td><input type="text" name="dob" size="30" readonly id="datepicker" /></td>
                            </tr>
                            <tr>
                                <td>Gender : </td>
                                <td>
                                    <input type="radio" name="gender" value="male" checked="checked" />Male
                                    <input type="radio" name="gender" value="female" />Female
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Type : </td><td>
                                    <select name="type">
                                        <option value="admin">Admin</option>
                                        <option value="user">User</option>
                                    </select></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <input type="submit" name="submit" value="Add" /> &nbsp;&nbsp;
                                    <input type="reset" name="reset" value="Reset" />
                                </td>
                            </tr>
                        </table><br>
                        <b><c:if test="${add}">
                                <h3 style="color: green">User added successfully.</h3>
                            </c:if>     
                        </b>
                    </form>
                </div>

                <div id = "tabs-3">
                    <form method="post" action="ChatManager">
                        <font color="white">To remove a room, check it & click update</font>
                        <br>
                            <c:forEach items="${hashmap}" var="iter">
                                <br>
                                <input type="checkbox" name="remove" value="<c:out value="${iter.key}"/>"/>
                                 <font color="white" size="3px">${iter.key}</font>
                                <br>
                            </c:forEach>                      
                        <center>
                           <br>
                           <font color="white"> Enter room name for adding a new room </font>
                                <br><br>
                                <input type="text" size="25" name="roomname"/> <br><br>
                                <input type="submit" name="chat" value="Update">
                            </h3>
                        </center>

                    </form>
                </div>
               
                <div id="tabs-5">
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