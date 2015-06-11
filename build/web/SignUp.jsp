<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SignUp - Chit-Chat</title>
        <link href="css/overcast/jquery-ui-1.9.2.custom.css" rel="stylesheet">
        <script src="js/jquery-1.8.3.js"></script>
        <script src="js/jquery-ui-1.9.2.custom.js"></script>
        <script>
        $(function() {	
                $( "#datepicker" ).datepicker({
                        inline: true, dateFormat: "dd-MM-yy", changeYear: true, changeMonth: true, yearRange: "1970:2014"
                });
        });
        </script>
    </head>
    <body>
        <div id="page">            
             <c:if test="${!empty added}">
                 <br><br><br><h3 style="color: #0000cc; margin-left: 4%; font-size: 20px;">A mail has been sent to your mail ID. Login <a href="index.jsp"> here </a> to enjoy chatting.</h3>
            </c:if>
            <c:if test="${! empty changed}" >
                <br><br><br><h3 style="color: #0000cc; margin-left: 4%; font-size: 20px;">Password has been changed successfully. Login again <a href="index.jsp"> here </a> to enjoy chatting.</h3>
            </c:if>   
                <br>
             <c:if test="${empty added && empty changed}">
            <div id="heading">Fill up your details below to enjoy Chatting :- </div><br>
            <form action="AddUser" method="POST" enctype="multipart/form-data">
            <table border="0" cellpadding="10" style="margin-left: 60px;">
                  <tbody>
                        <tr>
                            <td>Name <font color="red"> *</font> : </td>
                            <td><input type="text" required name="name" size="30" value="${name}" /></td>
                            <td><c:if test="${!empty errors}">
                                    <span class="error">${errors['name']}</span>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>Username <font color="red"> *</font> : </td>
                            <td><input type="text" required name="username" size="30"value="${user}" /></td>
                            <td><c:if test="${!empty errors}">
                                    <span class="error">${errors['username']}</span>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>Password <font color="red"> *</font>: </td>
                            <td><input type="password" required name="passwd" size="30" /></td>
                             <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>Mobile Number : </td>
                            <td><input type="text" name="mobile" maxlength="10" size="30" value="${mobile}"/></td>
                            <td><c:if test="${!empty errors}">
                                    <span class="error">${errors['mobile']}</span>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>E-mail <font color="red"> *</font>: </td>
                            <td><input type="text" required name="email"  size="30" value="${email}" /></td>
                            <td><c:if test="${!empty errors}">
                                    <span class="error">${errors['email']}</span>
                                </c:if>
                            </td>
                        </tr>                        
                        <tr>
                            <td>Date Of Birth <font color="red"> *</font> : </td>
                            <td><input type="text" name="dob" size="30" readonly id="datepicker" /></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>City : </td>
                            <td><input type="text"  name="city"  size="30" value="${city}"/></td>
                            <td><c:if test="${!empty errors}">
                                    <span class="error">${errors['city']}</span>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                             <td>Gender <font color="red"> *</font> : </td>
                             <td>
                            <input type="radio" name="gender" value="male" checked="checked" />Male
                            <input type="radio" name="gender" value="female" />Female
                             </td>
                             <td></td>
                     </tr>
                     <tr>
                         <td>Photo :</td>
                         <td><input type="file" name="photo"/></td>
                          <td>&nbsp;</td>
                     </tr>
                     <tr>
                            <td>Nationality : </td>
                            <td><input type="text"  name="nation"  size="30" value="${nation}"/></td>
                            <td><c:if test="${!empty errors}">
                                    <span class="error">${errors['nation']}</span>
                                </c:if>
                            </td>
                        </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Register"/>   &nbsp;&nbsp;                      
                            <input type="reset" value="Reset"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
            <br>
             </c:if>
            <br><br><br><br>
        </div>
    </body>
</html>
