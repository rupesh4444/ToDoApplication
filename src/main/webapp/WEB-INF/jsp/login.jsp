<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation_login.jspf"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>

    <style>
       .container{
          margin: 0;
            position: absolute;
            top: 40%;
            left: 5%;
            -ms-transform: translateY(-50%);
            transform: translateY(-50%);
        }
     </style>

      <meta charset="utf-8">
      <title>Log in with your account</title>
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" type="text/css"">

  </head>

  <body>

    <div class="container">
     <center> <form method="POST" action="${contextPath}/login" class="form-signin">
        <h4 class="form-heading">Log In</h4>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input width="50%" name="username" type="text" style="width:300px;" class="form-control" placeholder="Email"
                   autofocus="true"/>  <br/>
            <input width="50%" name="password" type="password" style="width:300px;" class="form-control" placeholder="Password"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/><br/>

            <button class="btn btn-success" type="submit">Log In</button>

        </div>
      </form></center>
    </div>
  </body>
</html>
<%@ include file="common/footer_out.jspf"%>