<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Bootstrap Blog - B4 Template by Bootstrap Temple</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="vendor/font-awesome/css/font-awesome.min.css">
    <!-- Custom icon font-->
    <link rel="stylesheet" href="css/fontastic.css">
    <!-- Google fonts - Open Sans-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
    <!-- Fancybox-->
    <link rel="stylesheet" href="vendor/@fancyapps/fancybox/jquery.fancybox.min.css">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="favicon.png">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
  </head>
  <body>
    <header class="header">
      <!-- Main Navbar-->
      <nav class="navbar navbar-expand-lg">
        <div class="search-area">
          <div class="search-area-inner d-flex align-items-center justify-content-center">
            <div class="close-btn"><i class="icon-close"></i></div>
            <div class="row d-flex justify-content-center">
              <div class="col-md-8">
                <form action="blog-search.html">
                  <div class="form-group">
                    <input type="search" name="search" id="search" placeholder="What are you looking for?">
                    <button type="submit" class="submit"><i class="icon-search-1"></i></button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <div class="container">
          <!-- Navbar Brand -->
          <div class="navbar-header d-flex align-items-center justify-content-between">
            <!-- Navbar Brand --><a href="index.html" class="navbar-brand">Bootstrap Blog</a>
            <!-- Toggle Button-->
            <button type="button" data-toggle="collapse" data-target="#navbarcollapse" aria-controls="navbarcollapse" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler"><span></span><span></span><span></span></button>
          </div>
          <!-- Navbar Menu -->
            <div id="navbarcollapse" class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a href="home" class="nav-link">Home</a></li>
                    <li class="nav-item"><a href="blog" class="nav-link active">Blog</a></li>
                    <li class="nav-item"><a href="contact" class="nav-link">Contact</a></li>
                </ul>
                 <div class="navbar-text"><a href="blog-search" class="search-btn"><i class="icon-search-1"></i></a></div>
            </div>
        </div>
    </nav>
</header>
<div class="container">
  <div class="row">
        <!-- Latest Posts -->
    <main class="posts-listing col-lg-8">
        <div class="container">
            <h2 class="mb-3 author d-flex align-items-center flex-wrap">
                <div class="avatar"><img src="${authorsBlogs[0].author.image}" alt="picture" style="height: 100px; width: 100px" class="img-fluid rounded-circle"></div>
                <div class="title">
                    <span>Posts by author "${authorsBlogs[0].author.name}"</span>
                </div>
               
                
            </h2>
            <div class="row">
                <c:forEach var="blog" items="${authorsBlogs}">
                    <div class="post col-xl-6">
                        <div class="post-thumbnail">
                            <a href="blog-post?id=${blog.id}">
                                <img src="${blog.imageBlog}" alt="picture" class="img-fluid" style="height: 200px; width: 400px">
                            </a>
                        </div>
                        <div class="post-details">
                            <div class="post-meta d-flex justify-content-between">
                                <div class="date meta-last">${blog.time}</div>
                                <div class="category">
                                    <a href="blog-category?id=${blog.category.id}">${blog.category.name}</a>
                                </div>
                            </div>
                            <a href="blog-post?id=${blog.id}">
                                <h3 class="h4">${blog.title}</h3>
                            </a>
                            <p class="text-muted">${blog.description}</p>
                            <footer class="post-footer d-flex align-items-center">
                                <a href="blog-author?id=${blog.author.id}" class="author d-flex align-items-center flex-wrap">
                                    <div class="avatar">
                                              <img src="${blog.author.image}" alt="picture" class="img-fluid">
                                    </div>
                                    <div class="title"><span>${blog.author.name}</span></div>
                                </a>
                                <div class="date"><i class="fa fa-clock-o"></i> ${blog.time}</div>
                                <div class="comments meta-last"><i class="fa fa-comment"></i> ${blog.count}</div>
                            </footer>
                        </div>
                    </div>
                </c:forEach>
            </div>
          
                   <!-- Pagination -->
      	       	<nav aria-label="Page navigation example">
			    <ul class="pagination d-flex justify-content-center">
			        <!-- Previous Page Link -->
			        <c:if test="${page > 1}">
			            <c:url value="/blog-author" var="prev">
			                <c:param name="page" value="${page - 1}"/>
			            </c:url>
			            <li class="page-item">
			                <a class="page-link" href="${prev}">
			                    <i class="fa fa-angle-left"></i> Prev
			                </a>
			            </li>
			        </c:if>
			
			        <!-- Page Number Links -->
			        <c:forEach var="i" begin="1" end="${maxPages}">
			            <c:choose>
			                <c:when test="${page == i}">
			                    <li class="page-item active"><span class="page-link">${i}</span></li>
			                </c:when>
			                <c:otherwise>
			                    <c:url value="/blog-author" var="url">
			                        <c:param name="page" value="${i}"/>
			                    </c:url>
			                    <li class="page-item"><a class="page-link" href="${url}">${i}</a></li>
			                </c:otherwise>
			            </c:choose>
			        </c:forEach>
			
			        <!-- Next Page Link -->
			        <c:if test="${page < maxPages}">
			            <c:url value="/blog-author" var="next">
			                <c:param name="page" value="${page + 1}"/>
			            </c:url>
			            <li class="page-item">
			                <a class="page-link" href="${next}">
			                    Next <i class="fa fa-angle-right"></i>
			                </a>
			            </li>
			        </c:if>
			    </ul>
			 </nav>
            
        </div>
    </main>
            <aside class="col-lg-4">
          <!-- Widget [Search Bar Widget]-->
          <div class="widget search">
            <header>
              <h3 class="h6">Search the blog</h3>
            </header>
            <form:form action="blog-search" modelAttribute="search" method="get" class="search-form">
              <div class="form-group">
                <form:input path="searchText" placeholder="What are you looking for?"/>
                <button type="submit" class="submit"><i class="icon-search"></i></button>
              </div>
            </form:form>
          </div>
   
             <!-- Widget [Latest Posts Widget]        -->
          <div class="widget latest-posts">
            <header>
              <h3 class="h6">Latest Posts</h3>
            </header>
			<div class="blog-posts">
				<c:forEach var="blog" items="${last}">
		 		   <a href="blog-post?id=${blog.id}">  
		            <div class="item d-flex align-items-center">
		                <div class="image"><img src="${blog.imageBlog}" alt="picture" class="img-fluid"></div>
		                <div class="title">
		                    <strong>${blog.title}</strong>
		                    <div class="d-flex align-items-center">
		                        <div class="views"><i class="icon-eye"></i>${blog.isSeen}</div>
		                        <div class="comments"><i class="icon-comment"></i>${blog.count}</div>
		                    </div>
		                </div>
		            </div>
		     	   </a>
	            </c:forEach>
             </div>
	     </div>
			
         
          <!-- Widget [Categories Widget]-->
       <div class="widget categories">
            <header>
              <h3 class="h6">Categories</h3>
            </header>
          <c:forEach var="category" items="${categoryList}">
	        <div class="item d-flex justify-content-between">
	            <c:choose>
	                <c:when test="${category.name == 'Uncategorized'}">
	                    <p>Uncategorized</p>
	                </c:when>
	                <c:otherwise>
	                    <a href="blog-category?id=${category.id}">${category.name}</a>
	                    <span>${category.count}</span>
	                </c:otherwise>
	            </c:choose>
	          </div>       
	        </c:forEach>
	      </div>
          
          <!-- Widget [Tags Cloud Widget]-->
           <div class="widget tags">       
	            <header>
	              <h3 class="h6">Tags</h3>
	            </header>
	            <c:forEach var="tag" items="${tagList}" >
	               <ul class="list-inline">              
	                   <li class="list-inline-item"><a  href="blog-tag?id=${tag.id}" class="tag">#${tag.name}</a></li>
	               </ul>            
	           </c:forEach>
         	 </div>
        	</aside>
		</div>
		</div>
    

     <!-- Page Footer-->
    <footer class="main-footer">
      <div class="container">
        <div class="row">
          <div class="col-md-4">
            <div class="logo">
              <h6 class="text-white">Bootstrap Blog</h6>
            </div>
            <div class="contact-details">
              <p>53 Broadway, Broklyn, NY 11249</p>
              <p>Phone: (020) 123 456 789</p>
              <p>Email: <a href="mailto:info@company.com">Info@Company.com</a></p>
              <ul class="social-menu">
                <li class="list-inline-item"><a href="#"><i class="fa fa-facebook"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-twitter"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-instagram"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-behance"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-pinterest"></i></a></li>
              </ul>
            </div>
          </div>
          
           <div class="col-md-4">
            <div class="menus d-flex">
              <ul class="list-unstyled">
                <li> <a href="home">Home</a></li>
                <li> <a href="blog">Blog</a></li>
                <li> <a href="contact">Contact</a></li>
                <li> <a href="/Domaci/loginPage">Login</a></li>
              </ul>
              
              <ul class="list-unstyled">
					    <c:forEach var="category" items="${categoryList}">
					        <li><a href="blog-category?id=${category.id}">${category.name}</a></li>
					    </c:forEach>
					</ul>
            </div>
          </div>
          
          <div class="col-md-4">
            <div class="latest-posts">          
              <c:forEach var="blog" items="${last}">
               <a href="blog-post?id=${blog.id}">
                <div class="post d-flex align-items-center">           
                  <div class="image">
                       <img src="${blog.imageBlog}" alt="picture" class="img-fluid">
                  </div>
                  <div class="title">
                       <strong>${blog.title}</strong>
                       <span class="date last-meta">${blog.formattedDateForDisplay}</span>
                  </div>         
                </div>
                 </a>
               </c:forEach>
              </div>
          </div>        
        </div>
      </div>
      <div class="copyrights">
        <div class="container">
          <div class="row">
            <div class="col-md-6">
              <p>&copy; 2017. All rights reserved. Your great site.</p>
            </div>
            <div class="col-md-6 text-right">
              <p>Template By <a href="https://bootstrapious.com/p/bootstrap-carousel" class="text-white">Bootstrapious</a>
                <!-- Please do not remove the backlink to Bootstrap Temple unless you purchase an attribution-free license @ Bootstrap Temple or support us at http://bootstrapious.com/donate. It is part of the license conditions. Thanks for understanding :)                         -->
              </p>
            </div>
          </div>
        </div>
      </div>
    </footer>
    <!-- JavaScript files-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/popper.js/umd/popper.min.js"> </script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="vendor/@fancyapps/fancybox/jquery.fancybox.min.js"></script>
    <script src="js/front.js"></script>
  </body>
</html>