<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Cubes School | Confirm Password</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">

      <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/back/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  
   <link rel="stylesheet" href="${pageContext.request.contextPath}/back/plugins/jquery-ui/jquery-ui.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/back/plugins/jquery-ui/jquery-ui.theme.min.css">
   
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="#"><b>Cubes</b>School</a>
  </div>
  <!-- /.login-logo -->
  <div class="card">
    <div class="card-body login-card-body">
      <p class="login-box-msg">Change your password.</p>

      <form:form action="user-change-password-save"  modelAttribute="changePassword" role="form">
     
        <form:label path="confirmPassword">Old Password:</form:label>
        <div class="input-group mb-3">      
          <form:input type="password" path="oldPassword" class="form-control" placeholder="Old Password"/>
          <form:errors path="oldPassword" cssClass="error"/>
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock-open"></span>
            </div>
          </div>
        </div>
        
        <form:label path="confirmPassword">New Password:</form:label>
        <div class="input-group mb-3">
          <form:input type="password" path="newPassword" class="form-control" placeholder="New Password"/>
          <form:errors path="newPassword" cssClass="error"/>
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
        </div>
        
        <form:label path="confirmPassword">Confirm Password:</form:label>
        <div class="input-group mb-3">       
          <form:input type="password" path="confirmPassword" class="form-control" placeholder="Confirm New Password"/>
          <form:errors path="confirmPassword" cssClass="error"/>
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
         
        </div>
        <div class="row">
          <div class="col-12">
            <button type="submit" class="btn btn-primary btn-block">Save</button>
          </div>
          <!-- /.col -->
        </div>
      </form:form>


    <!-- Prikazivanje poruke ako postoji -->
    <c:if test="${not empty message}">
        <div style="color: red;">${message}</div>
    </c:if>
    
    

      <p class="mt-3 mb-1">
        <a href="user-forget-password">I forgot my password</a>
      </p>
    </div>
    <!-- /.login-card-body -->
  </div>
</div>
<!-- /.login-box -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/back/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/back/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/back/dist/js/adminlte.min.js"></script>

</body>
</html>
