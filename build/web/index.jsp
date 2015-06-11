<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Chit-Chat Application</title>
    </head>
    <body>
        <div id="page" style="margin-top: -6%"><table border="0"  cellpadding="10" style="margin-left: 40px;">
                <tr>
                    <td height="200px" width="550px" rowspan="3" style="line-height:25px; font-size:18px; font-weight: bold;">
                <u>Chat with anyone you want :-</u><br><br>
                1.) Chat allows meet people who share the same interests.<br>                            
                2.) You can have full control over your information.<br>
                3.) Make new friends and meet experienced teachers learners.<br>
                4.) All chat rooms run very fast, and require no plug-ins.<br>
                5.) Share your knowledge, discuss doubts with no trouble.<br> 
                6.) Chat was, is, and always will be free. Thanks!<br>
                </td>
                <td>
                    <br><br><br>
                    <form method="post" action="RequestManager">
                        <p align="center">Enter Username and password</p>
                        <table align="center" cellpadding="10" bgcolor="#728DCF">
                            <tr>
                                <td><input name="username" size=20 placeholder="Enter Username"></td>
                            </tr>
                            <tr>
                                <td><input name="passwd" type="password" placeholder="Enter password" size=20></td>
                            </tr>
                            <tr><td align="center"><input type="submit" name="submit" value="Login"/> &nbsp; &nbsp; &nbsp;
                                    <input type="submit" name="submit" value="SignUp"/>
                                </td>
                            </tr>
                        </table>
                        <c:if test="${!status && login}">
                            <p align="center" style="color: red">Incorrect Credentials...</p>
                        </c:if> &nbsp;    
                    </form>
                    <br><br><br><br>
                </td>
                </tr>  
                <br><br>
            </table>
        </div>
    </body>
</html>