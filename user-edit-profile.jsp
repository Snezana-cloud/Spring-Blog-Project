<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="x-ua-compatible" content="ie=edge">

  <title>Cubes school - Blog project</title>

   <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/back/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  
   <link rel="stylesheet" href="${pageContext.request.contextPath}/back/plugins/jquery-ui/jquery-ui.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/back/plugins/jquery-ui/jquery-ui.theme.min.css">
  
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
    </ul>

    
    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
      <!-- Messages Dropdown Menu -->
      <li class="nav-item dropdown">
        <a class="nav-link" data-toggle="dropdown" href="#">
          <i class="far fa-user"></i>
        </a>
        <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
          <a href="user-edit-profile" class="dropdown-item">   <!-- nisam pisala ?id=... vec sam korisitla principle obj -->
            <!-- Message Start -->
            <div class="media align-items-center">
              <img src="${pageContext.request.contextPath}/dist/img/user1-128x128.jpg" alt="User Avatar" class="img-brand-50 mr-3 img-circle">
               <div class="media-body">
                <h3 class="dropdown-item-title">
                  Brad Diesel
                </h3>
              </div>
            </div>
            <!-- Message End -->
          </a>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item">
            <i class="fas fa-user"></i> Your Profile
          </a>
          <div class="dropdown-divider"></div>
           <a href="login.html" class="dropdown-item">
             <form:form action="${pageContext.request.contextPath}/logout">
                <input type="submit" value="LogOut">
             </form:form>
          </a>
        </div>
      </li>
    </ul>
  </nav>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- User Logo -->
   <a href="index3.html" class="brand-link">
      <img src="${pageContext.request.contextPath}/dist/img/AdminLTELogo.png" alt="Cubes School Logo" class="brand-image img-circle elevation-3"
           style="opacity: .8">
      <span class="brand-text font-weight-light">Cubes School</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Your Profile</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Profile</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">Change your profile info</h3>
                <div class="card-tools">
                  <a href="profile-change-password.html" class="btn btn-outline-warning">
                    <i class="fas fa-lock-open"></i>
                    Change Password
                  </a>
                </div>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form:form action="user-edit" modelAttribute="user" role="form">
              <form:hidden path="password"/> <!-- ova dvapolja moram da upisem kao hiden da ih ne izgubim ali ne dozvoljavam sda se menjaju -->
              <form:hidden path="authorities"/>
              <form:hidden path="enabled"/>
               <form:hidden path="email"/>
               <form:hidden path="username"/><!-- moram da ga dodam jer projavi gresku, on mora da se veze, name i telefon su vezani za modelAtribute preko path, -->
                <div class="card-body">    <!-- za sve sto koristim input ,znaci da moze da se menja, onda ne mora da se to polje pise kao hidden -->
                  <div class="row">
                    <div class="col-md-6">
                      
                      <div class="callout callout-success" style="background :#e6ffff;">
                           <h5>Username</h5>
                            <p>${user.username}</p>
                      </div>
                     
                      <div class="form-group">
                        <label>Name</label>
                        <form:input path="name" class="form-control" placeholder="Enter name"/>
                      </div>
                      <div class="form-group">
                        <label>Phone</label>
                        <div class="input-group">
                          <form:input path="telephone" class="form-control" placeholder="Enter phone"/>
                          <div class="input-group-append">
                            <span class="input-group-text">
                              <i class="fas fa-phone"></i>
                            </span>
                          </div>
                        </div>
                      </div>

                      <div class="form-group">
                        <label>Choose New Photo</label>
                        <form:input path="image" class="form-control" placeholder="Enter author's picture"/>
                      </div>
                    </div>
                    <div class="offset-md-3 col-md-3">
                      <div class="row">
                        <div class="col-md-12">
                          <div class="form-group">
                            <label>Photo</label>

                            <div class="text-right">
                              <button type="button" class="btn btn-sm btn-outline-danger">
                                <i class="fas fa-remove"></i>
                                Delete Photo
                              </button>
                            </div>
                            <div class="text-center">
                              <img src="${user.image}" alt="user" class="img-fluid">
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Save</button>
                  <a href="user-list" class="btn btn-outline-secondary">Cancel</a>
                </div>
              </form:form>
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="float-right d-none d-sm-inline">
      PHP Laravel
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2019 <a href="https://cubes.edu.rs">Cubes School</a>.</strong> All rights reserved.
  </footer>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/back/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/back/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/back/dist/js/adminlte.min.js"></script>
</body>
</html>
