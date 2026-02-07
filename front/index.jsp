<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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


    
    <!-- owl carousel 2 stylesheet-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/owl-carousel2/assets/owl.carousel.min.css" id="theme-stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/owl-carousel2/assets/owl.theme.default.min.css" id="theme-stylesheet">
    
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
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
                <form action="blog-search">
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
            <!-- Navbar Brand --><a href="#" class="navbar-brand">Bootstrap Blog</a>
            <!-- Toggle Button-->
            <button type="button" data-toggle="collapse" data-target="#navbarcollapse" aria-controls="navbarcollapse" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler"><span></span><span></span><span></span></button>
          </div>
          <!-- Navbar Menu -->
          <div id="navbarcollapse" class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
              <li class="nav-item"><a href="" class="nav-link active">Home</a> </li>            
              <li class="nav-item"><a href="blog" class="nav-link">Blog</a>  </li>         
              <li class="nav-item"><a href="contact" class="nav-link">Contact</a></li>         
            </ul>
            <div class="navbar-text"><a href="blog-search" class="search-btn"><i class="icon-search-1"></i></a></div>
          </div>
        </div>
      </nav>
    </header>

    <!-- Hero Section-->
    
    <div id="index-slider" class="owl-carousel">
    <c:forEach var="slider" items="${sliderlist}">	
	   <section style="background: url('${slider.image}?id=${slider.id}'); background-size: cover; background-position: center center; height: 600px" class="hero">
        <div class="container">
          <div class="row">
            <div class="col-lg-7">
              <h1 style="color:yellow">${slider.title}</h1>
              <a href="${slider.link}" target="_blank" class="hero-link" ><strong style="color: yellow">${slider.linkTekst}</strong></a>
            </div>
          </div>
        </div>
      </section>
      </c:forEach>
	</div>


    <!-- Intro Section-->
    <section class="intro">
      <div class="container">
        <div class="row">
          <div class="col-lg-8">
            <h2 class="h3">Most interested blogs </h2>
            <p class="text-big">Lets's <strong>introduce</strong> <strong>some of ours blogs</strong>.</p>
          </div>
        </div>
      </div>
    </section>
    <section class="featured-posts no-padding-top">
      <div class="container">
      
  
        <!-- Post                            -->
    
        
<c:forEach var="post" items="${important}" varStatus="status">
    <c:if test="${status.index % 2 == 1}">
        <div class="row d-flex align-items-stretch">
            <div class="image col-lg-5">
                <img src="${post.imageBlog}" alt="...">
            </div>
            <%-- Ovde dodaj ostale elemente posta (naslov, opis...) --%>
        </div>
  


          <div class="text col-lg-7">
            <div class="text-inner d-flex align-items-center">
           
              <div class="content">
                <header class="post-header">
                  <div class="category"><a href="blog-category?id=${important[i].category.id}">${important[i].category.name}</a></div><a href="blog-post?id=${blog.id}">
                    <h2 class="h4">${important[i].title}</h2></a>
                </header>
                <p>${important[i].description}</p>
                <footer class="post-footer d-flex align-items-center"><a href="blog-author?id=${important[i].author.id}" class="author d-flex align-items-center flex-wrap">
                    <div class="avatar"><img src="${important[i].author.image}" alt="picture" class="img-fluid"></div>
                    <div class="title"><span>${important[i].author}</span></div></a>
                  <div class="date"><i class="icon-clock"></i> ${important[i].formattedDate}</div>
                  <div class="comments"><i class="icon-comment"></i>12</div>
                </footer>
              </div>
            </div>
          </div>
        </div>
        </c:if>
        
        <c:if test="${i%2 ==0}">    <!-- prva i treaca slika -->
        <div class="row d-flex align-items-stretch">
          <div class="text col-lg-7">
            <div class="text-inner d-flex align-items-center">
              <div class="content">
                <header class="post-header">
                  <div class="category"><a href="blog-category?id=${important[i].category.id}">${important[i].category.name}</a></div><a href="blog-post?id=${blog.id}">
                    <h2 class="h4">${important[i].title}</h2></a>
                </header>
                <p>${important[i].description}</p>
                <footer class="post-footer d-flex align-items-center">
                    <a href="blog-author?id=${important[i].author.id}" class="author d-flex align-items-center flex-wrap">
                      <div class="avatar"><img src="${important[i].author.image}" alt="picture" class="img-fluid"></div>
                      <div class="title"><span>${important[i].author}</span></div>
                    </a>
                  <div class="date"><i class="icon-clock"></i> ${important[i].formattedDate}</div>
                  <div class="comments"><i class="icon-comment"></i>12</div>
                </footer>
              </div>
            </div>
          
          </div>
            <div class="image col-lg-5"><img src="${important[i].imageBlog}" alt="..."></div>
        </div>
        </c:if>
       
        </c:forEach>
      </div>
    </section>
    <!-- Divider Section-->
    <section style="background: url(https://img.freepik.com/free-photo/cala-mariolu-clear-day-sardinia_258246-7025.jpg); background-size: cover; background-position: center bottom" class="divider">
      <div class="container">
        <div class="row">
          <div class="col-md-7">
            <h2>EASTERN AEGEAN ISLANDS- Descovery the lovely beautiful beaches  </h2>
            <a href="contact" class="hero-link"><strong>Contact Us</strong></a>
          </div>
        </div>
      </div>
    </section>
    
    <!-- Latest Posts -->
    <section class="latest-posts"> 
      <div class="container">
        <header> 
          <h2>Latest from the blog</h2>
          <p class="text-big">Latest blogs</p>
        </header>
     <!-- OVO JE LAST 12 BLOGOVA ALI NISAM PREPRAVILA U NAZIVU  lastSix -->
		  <div id="latest-posts-slider" class="owl-carousel">
			  <c:forEach var="blog" items="${lastSix}">
			    <div class="item">
			      <div class="post-thumbnail"><a href="blog-post?id=${blog.id}"><img src="${blog.imageBlog}" alt="picture" class="img-fluid" style="height: 200px; width: 400px"></a></div>
			      <div class="post-details">
			        <div class="post-meta d-flex justify-content-between">
			          <div class="date">${blog.formattedDateForDisplay}</div>
			          <div class="category">
			             <a href="blog-category?id=${category.id}">${blog.category.name}</a>
			          </div>
			        </div>
			        <a href="blog-post?id=${blog.id}">
			           <h3 class="h4">${blog.title}</h3>
			        </a>
			        <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore.</p>
			      </div>
			    </div>
			  </c:forEach>
			</div>
	 </div>
     
	  </section>
			   
    
  
    
   
   
    <!-- Gallery Section-->
    <section class="gallery no-padding">    
      <div class="row">
        <div class="mix col-lg-3 col-md-3 col-sm-6">
          <div class="item">
            <a href="https://th.bing.com/th/id/R.8adde1bdd1ea69bbba77250cc8b73782?rik=QR8vzEW%2fj7ejjQ&riu=http%3a%2f%2fmedia-cache-ak0.pinimg.com%2f736x%2f54%2fcc%2f3f%2f54cc3fffdc81982a3c8048918746188a.jpg&ehk=YokTKh9kkalDo%2fX3YeEmV9h3O0EKmD86OVpSv5asHX4%3d&risl=&pid=ImgRaw&r=0" data-fancybox="gallery" class="image">
              <img src="https://th.bing.com/th/id/R.8adde1bdd1ea69bbba77250cc8b73782?rik=QR8vzEW%2fj7ejjQ&riu=http%3a%2f%2fmedia-cache-ak0.pinimg.com%2f736x%2f54%2fcc%2f3f%2f54cc3fffdc81982a3c8048918746188a.jpg&ehk=YokTKh9kkalDo%2fX3YeEmV9h3O0EKmD86OVpSv5asHX4%3d&risl=&pid=ImgRaw&r=0" style="height: 200px; width: 300px" alt="garfild1" class="img-fluid" title="gallery image title 1">
              <div class="overlay d-flex align-items-center justify-content-center"><i class="icon-search"></i></div>
            </a>
          </div>
        </div>
        <div class="mix col-lg-3 col-md-3 col-sm-6">
          <div class="item">
            <a href="https://pbs.twimg.com/media/D5fY5upWsAcVxiP.jpg" data-fancybox="gallery" class="image">
              <img src="https://pbs.twimg.com/media/D5fY5upWsAcVxiP.jpg" style="height: 200px; width: 350px" class="car" title="gallery image title 2">
              <div class="overlay d-flex align-items-center justify-content-center"><i class="icon-search"></i></div>
            </a>
          </div>
        </div>
        <div class="mix col-lg-3 col-md-3 col-sm-6">
          <div class="item">
            <a href="https://i.pinimg.com/originals/a0/1b/a6/a01ba694f5d4f1df3a1d4394a96c088e.jpg" data-fancybox="gallery" class="image">
              <img src="https://i.pinimg.com/originals/a0/1b/a6/a01ba694f5d4f1df3a1d4394a96c088e.jpg" class="img-fluid" style="height: 220px; width: 350px" title="gallery image title 3">
              <div class="overlay d-flex align-items-center justify-content-center"><i class="icon-search"></i></div>
            </a>
          </div>
        </div>
        <div class="mix col-lg-3 col-md-3 col-sm-6">
          <div class="item">
            <a href="https://i.pinimg.com/736x/c4/c5/1e/c4c51e2838d3a5f2c84b4b0b6e1a06f4.jpg" data-fancybox="gallery" class="image">
              <img src="https://i.pinimg.com/736x/c4/c5/1e/c4c51e2838d3a5f2c84b4b0b6e1a06f4.jpg" class="img-fluid" style="height: 202px;  width: 350px" title="gallery image title 4">
              <div class="overlay d-flex align-items-center justify-content-center"><i class="icon-search"></i></div>
            </a>
          </div>
        </div>

        
      </div>
    </section>
   
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
                <li> <a href="#">Home</a></li>
                <li> <a href="blog">Blog</a></li>
                <li> <a href="contact">Contact</a></li>
                <li> <a href="/Domaci/loginPage">Login</a></li>
              </ul>
              
              <ul class="list-unstyled">
              <c:forEach var="category" items="${categories}">
                <li> <a href="#${category.name}">${category.name}</a></li>  
                </c:forEach>
              </ul>
              
            </div>
          </div>
          
          <!-- footer last blogs -->
          <div class="col-md-4">
            <div class="latest-posts">

              <c:forEach var="blog" items="${last}">
               <a href="blog-post?id=${blog.id}">
                <div class="post d-flex align-items-center">
               
                  <div class="image">
                       <img src="${blog.imageBlog}" alt="..." class="img-fluid">
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
    <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/popper.js/umd/popper.min.js"> </script>
    <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="${pageContext.request.contextPath}/vendor/@fancyapps/fancybox/jquery.fancybox.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/front.js"></script>


    <script src="${pageContext.request.contextPath}/plugins/owl-carousel2/owl.carousel.min.js"></script>

    <script>
      $("#index-slider").owlCarousel({
        "items": 1,
        "loop": true,
        "autoplay": true,
        "autoplayHoverPause": true
      });

      $("#latest-posts-slider").owlCarousel({
        "items": 3,
        "loop": true,
        "autoplay": true,
        margin: 10,
        "autoplayHoverPause": true
      });
    </script>
    
    




    

    
  </body>
</html>