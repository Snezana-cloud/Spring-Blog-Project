<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %><!-- da stavim ogranicenja sta ne mogu da vide autori -->



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
     <li class="nav-item dropdown">
        <a class="nav-link" href="contact-list" aria-expanded="true">
          <i class="far fa-comments"></i>
          <span class="badge badge-danger navbar-badge">${contactCount}</span>
        </a>
       </li>
      <!-- Messages Dropdown Menu -->
      <li class="nav-item dropdown">
        <a class="nav-link" data-toggle="dropdown" href="#">
          <i class="far fa-user"></i>
        </a>
        <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
          <a href="#" class="dropdown-item">
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
		               
		       <!-- zabrana pristupa -->
		      <sec:authorize access="hasRole('admin')">       
			          <li class="nav-item has-treeview">
			            <a href="#" class="nav-link">
			              <i class="nav-icon far fa-plus-square"></i>
			              <p>
			                Categories
			                <i class="right fas fa-angle-left"></i>
			              </p>
			            </a>
			            <ul class="nav nav-treeview">
			              <li class="nav-item">
			                <a href="category-list" class="nav-link">
			                  <i class="far fa-circle nav-icon"></i>
			                  <p>Categories list</p>
			                </a>
			              </li>
			              <li class="nav-item">
			                <a href="category-form" class="nav-link">
			                  <i class="far fa-circle nav-icon"></i>
			                  <p>Add Category</p>
			                </a>
			              </li>
			            </ul>
			          </li>
		          </sec:authorize> 
		        </ul>
		      </nav>
      
            <nav class="mt-2">
		        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
		          <!-- Add icons to the links using the .nav-icon class
		               with font-awesome or any other icon font library -->
		          <sec:authorize access="hasRole('admin')">       
		          <li class="nav-item has-treeview">
		            <a href="#" class="nav-link">
		              <i class="nav-icon far fa-plus-square"></i>
		              <p>
		                Tag 
		                <i class="right fas fa-angle-left"></i>
		              </p>
		            </a>
		            <ul class="nav nav-treeview">
		              <li class="nav-item">
		                <a href="tag-list" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p> Tag list</p>
		                </a>
		              </li>
		              <li class="nav-item">
		                <a href="tag-form" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p>Add Tag </p>
		                </a>
		              </li>
		            </ul>
		          </li>
		          </sec:authorize>
		        </ul>
     	  </nav>
        
            <nav class="mt-2">
		        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
		          <!-- Add icons to the links using the .nav-icon class
		               with font-awesome or any other icon font library -->
		          <li class="nav-item has-treeview">
		            <a href="#" class="nav-link">
		              <i class="nav-icon far fa-plus-square"></i>
		              <p>
		                Blog Section
		                <i class="right fas fa-angle-left"></i>
		              </p>
		            </a>
		            <ul class="nav nav-treeview">
		              <li class="nav-item">
		                <a href="blog-list" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p>Blog list</p>
		                </a>
		              </li>
		              <li class="nav-item">
		                <a href="blog-form" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p>Add Blog</p>
		                </a>
		              </li>
		            </ul>
		          </li>
		        </ul>
		      </nav>
      
      
		      <nav class="mt-2">
		        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
		          <!-- Add icons to the links using the .nav-icon class
		               with font-awesome or any other icon font library -->
		         <sec:authorize access="hasRole('admin')">      
		          <li class="nav-item has-treeview">
		            <a href="#" class="nav-link">
		              <i class="nav-icon far fa-plus-square"></i>
		              <p>
		                Slider
		                <i class="right fas fa-angle-left"></i>
		              </p>
		            </a>
		            <ul class="nav nav-treeview">
		              <li class="nav-item">
		                <a href="slider-list" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p>Slider list</p>
		                </a>
		              </li>
		              <li class="nav-item">
		                <a href="slider-form" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p>Add Slider</p>
		                </a>
		              </li>
		            </ul>
		          </li>
		         </sec:authorize> 
		        </ul>
		      </nav>
		    
	 	      
		      <nav class="mt-2">
		        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
		          <!-- Add icons to the links using the .nav-icon class
		               with font-awesome or any other icon font library -->
		        <sec:authorize access="hasRole('admin')">
		          <li class="nav-item has-treeview">
		            <a href="#" class="nav-link">
		              <i class="nav-icon far fa-plus-square"></i>
		              <p>
		                Author
		                <i class="right fas fa-angle-left"></i>
		              </p>
		            </a>
		            <ul class="nav nav-treeview">
		              <li class="nav-item">
		                <a href="author-list" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p>Author list</p>
		                </a>
		              </li>
		              <li class="nav-item">
		                <a href="author-form" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p>Add Author</p>
		                </a>
		              </li>
		            </ul>
		          </li>
		       </sec:authorize> 
		        </ul>
		      </nav>
		      
	      
		 <nav class="mt-2">
		        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
		         <sec:authorize access="hasRole('admin')"> 
		          <!-- Add icons to the links using the .nav-icon class
		               with font-awesome or any other icon font library -->
		          <li class="nav-item has-treeview">
		            <a href="#" class="nav-link">
		              <i class="nav-icon far fa-plus-square"></i>
		              <p>
		                Users
		                <i class="right fas fa-angle-left"></i>
		              </p>
		            </a>
		            <ul class="nav nav-treeview">
		              <li class="nav-item">
		                <a href="user-list" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p>Users list</p>
		                </a>
		              </li>
		              <li class="nav-item">
		                <a href="user-form" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p>Add User</p>
		                </a>
		              </li>
		            </ul>
		          </li>
		         </sec:authorize> 
		        </ul>
		      </nav>
		      
		      <nav class="mt-2">
		        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
		          <!-- Add icons to the links using the .nav-icon class
		               with font-awesome or any other icon font library -->
		          <li class="nav-item has-treeview">
		            <a href="#" class="nav-link">
		              <i class="nav-icon far fa-plus-square"></i>
		              <p>
		                Other
		                <i class="right fas fa-angle-left"></i>
		              </p>
		            </a>
		            <ul class="nav nav-treeview">
		              <li class="nav-item">
		                <a href="contact-list" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p>Contact List</p>
		                </a>
		              </li>
		            </ul>
		            <ul class="nav nav-treeview">
		              <li class="nav-item">
		                <a href="comment-list" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p>Comment List</p>
		                </a>
		              </li>
		            </ul>
		          </li>
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
            <h1>Users Form</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="http://localhost:8080/Domaci/">Home</a></li>
              <li class="breadcrumb-item"><a href="#">Users</a></li>
              <li class="breadcrumb-item active">Users Form</li>
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
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">User Form</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form:form role="form" action="user-save" modelAttribute="user"> 
            
                <div class="card-body">
                  <div class="row">
                    <div class="col-md-6">
                      
                      <div class="form-group">
                        <label>Username</label>
                        <form:input path="username" type="text" class="form-control" placeholder="Enter username" />
                        <form:errors path="username" cssClass="text-danger"/>            
                      </div>
                      
                      <div class="form-group">
                        <label>Password</label>
                        <form:input path="password" type="text" class="form-control" placeholder="Enter password" />
                        <form:errors path="password" cssClass="text-danger"/>            
                      </div>
                      
                      <div class="form-group">
                        <label>Name</label>
                        <form:input path="name" class="form-control" placeholder="Enter name"/>
                        <form:errors path="name" cssClass="text-danger"/>                    
                      </div>
                      
                      <div class="form-group">
                        <label>Email</label>
                        <div class="input-group">
                          <form:input  path="email" class="form-control" placeholder="Enter email"/>
                          <form:errors path="email" cssClass="text-danger"/> 
                          <div class="input-group-append">
                            <span class="input-group-text">
                              <i class="fas fa-at"></i>
                            </span>
                          </div>
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <label>Telephone Number</label>
                         <div class="input-group">
                           <form:input path="telephone" class="form-control" placeholder="Enter telephone number"/>
                             <div class="input-group-append">
                              <span class="input-group-text">
                                <i class="fas fa-phone"></i>
                               </span>
                          </div>                  
                       </div>
                      </div>
            
                            
					  <div class="form-group">
                        <label>Roles</label>
                        <form:select path="authorities" items="${listRole}" itemLabel="authority" itemValue="authority" multiple="true" class="form-control" />                     
                      </div>
                      
                       <div class="form-group">
			        <label>Choose New Photo</label>
			        <input type="file" name="image" class="form-control">
			    </div>
                    </div>
                    <div class="offset-md-3 col-md-3">
                      <div class="row">
                        <div class="col-md-12">
                          <div class="form-group">
                            <label>Photo</label>
						

                            <div class="text-right">
                             <button type="button" class="btn btn-info" data-toggle="modal" data-target="#delete-modal-${user.username}">
                                <i class="fas fa-remove"></i>
                                Delete Photo
                              </button>
                            </div>
                            <div class="text-center">
                              <img src="http://localhost/images/slika.jpg" alt="photo" class="img-fluid">
                              
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                        <div class="modal fade" id="delete-modal-${user.username}">
					      <div class="modal-dialog">
					        <div class="modal-content">
					          <div class="modal-header">
					            <h4 class="modal-title">Delete User</h4>
					            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					              <span aria-hidden="true">&times;</span>
					            </button>
					          </div>
					          <div class="modal-body">
					            <p>Are you sure you want to delete user?</p>
					            <strong></strong>
					          </div>
					          <div class="modal-footer justify-content-between">
					            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					            <a href="user-delete?username=${user.username}" class="btn btn-danger">Delete</a>
                             </div>
                          </div>
        <!-- /.modal-content -->
                        </div>
      <!-- /.modal-dialog -->
                  </div>
		
 

                <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Save</button>
                  <a href="user-list" class="btn btn-outline-secondary">Cancel</a>
                </div>
                </div>
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
<script src="${pageContext.request.contextPath}/dist/js/adminlte.min.js"></script>
<script src="${pageContext.request.contextPath}/back/plugins/jquery-ui/jquery-ui.min.js"></script>



</body>
</html>
