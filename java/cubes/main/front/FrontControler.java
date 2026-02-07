package cubes.main.front;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cubes.main.dao.AuthorDAO;
import cubes.main.dao.BlogDAO;
import cubes.main.dao.CategoryDAO;
import cubes.main.dao.CommentDAO;
import cubes.main.dao.ContactDAO;
import cubes.main.dao.SliderDAO;
import cubes.main.dao.TagDAO;
import cubes.main.entity.Blog;
import cubes.main.entity.Category;
import cubes.main.entity.Comment;
import cubes.main.entity.Contact;

import cubes.main.entity.Search;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/")
public class FrontControler {
	
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private BlogDAO blogDAO;
	@Autowired
	private TagDAO tagDAO;
	@Autowired
	private SliderDAO sliderDAO;
	@Autowired
	private AuthorDAO authorDAO;
	@Autowired
	private ContactDAO contactDAO;
	@Autowired
	private CommentDAO commentDAO;
	
    @RequestMapping({"/", "/home"})
    public String getIndexPage(@RequestParam(required = false) Integer orderCategory, Model model) {
    	
    	  
        model.addAttribute("categories", categoryDAO.getCategoryOnFrontPage(orderCategory));
        model.addAttribute("important", blogDAO.getLastThreeImportantBlog());
        model.addAttribute("last", blogDAO.getLastThreeBlog());
        model.addAttribute("lastSix", blogDAO.getLastSixBlog());//ovo je metoda za 12 blogova, pogresno sam je nazvala
        model.addAttribute("sliderlist", sliderDAO.getOrderOfSlider());
        
        return "front/index";
    }

	@RequestMapping("/blog")
	public String getBlogPage (@RequestParam(value = "page", defaultValue = "1") int page,			                
			                   @ModelAttribute("search") Search search,
			                   Model model) {

		
		List<Blog> blogList=blogDAO.getBlogListByEnabledStatus();
		model.addAttribute("lastSix", blogDAO.getLastSixBlog());
		model.addAttribute("categoryList",categoryDAO.getCategoryForFilter());
		model.addAttribute("tagList", tagDAO.getTagListWithBlog());
		model.addAttribute("last", blogDAO.getLastThreeBlog());
		model.addAttribute("authors", authorDAO.getAuthorList()); 
	

		String searchText = search.getSearchText();	  
	    model.addAttribute("searchText", searchText);
	    
	  
		 for (Blog blog : blogList) {
	            Integer commentCount = (int) blogDAO.getCommentCount(blog.getId());
	            blog.setCount(commentCount);  
	        }
	    model.addAttribute("blogList", blogList);
	    
//		PAGINACIJA:

		 PagedListHolder<Blog> pagedListHolder = new PagedListHolder<>(blogList);
	    pagedListHolder.setPageSize(4);			            
   
        model.addAttribute("maxPages", pagedListHolder.getPageCount());
        if(page==0 || page < 1 || page > pagedListHolder.getPageCount())page=1;

        model.addAttribute("page", page);
        
        if(page == 0 || page < 1 || page > pagedListHolder.getPageCount()){
        	
            pagedListHolder.setPage(0);
            model.addAttribute("blogList", pagedListHolder.getPageList());
            
        }
        else if(page <= pagedListHolder.getPageCount()) {				        	
            pagedListHolder.setPage(page-1);
            
            model.addAttribute("blogList", pagedListHolder.getPageList());				            
        }		  
		return "front/blog";		
	}


	@RequestMapping("/blog-search")
	public String search(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(required = false) Integer orderCategory,
			             @ModelAttribute("search") Search search,
			             
			             Model model) {
	    String searchText = search.getSearchText();
	  	   
		model.addAttribute("lastSix", blogDAO.getLastSixBlog());
		model.addAttribute("categories", categoryDAO.getCategoryOnFrontPage(orderCategory));
		model.addAttribute("categoryList",categoryDAO.getCategoryForFilter());
		model.addAttribute("tagList", tagDAO.getTagListWithBlog());
		model.addAttribute("last", blogDAO.getLastThreeBlog());
		model.addAttribute("authors", authorDAO.getAuthorList()); 
		model.addAttribute("searchText", searchText);
	    
 
		 List<Blog> blogList = blogDAO.getBlogListBySearch(searchText);
		 for (Blog blog : blogList) {
	            Integer commentCount = (int) blogDAO.getCommentCount(blog.getId());
	            blog.setCount(commentCount);  
	        }
		    
		 model.addAttribute("blogList", blogList);
  
	    
	    //PAGINACIJA :

			 PagedListHolder<Blog> pagedListHolder = new PagedListHolder<>(blogList);
		    pagedListHolder.setPageSize(4);			      
	             
	        model.addAttribute("maxPages", pagedListHolder.getPageCount());

	        if(page==0 || page < 1 || page > pagedListHolder.getPageCount())page=1;

	        model.addAttribute("page", page);
	        
	        if(page == 0 || page < 1 || page > pagedListHolder.getPageCount()){
	        	
	            pagedListHolder.setPage(0);	        
	            model.addAttribute("results", pagedListHolder.getPageList());
	            
	        }
	        else if(page <= pagedListHolder.getPageCount()) {				        	
	            pagedListHolder.setPage(page-1);
	            model.addAttribute("results", pagedListHolder.getPageList());				            
	        }		
	     
	    return "front/blog-search"; 
	}


	@RequestMapping("/contact")
	public String getContactPage(@RequestParam(required = false) Integer orderCategory,Model model) {
		model.addAttribute("categories", categoryDAO.getCategoryOnFrontPage(orderCategory));
		model.addAttribute("categoryList",categoryDAO.getCategoryForFilter());
		model.addAttribute("last", blogDAO.getLastThreeBlog());
		model.addAttribute("contact",new Contact());
	
		return "front/contact";
	}
	
	@RequestMapping("/contact-save")
	public String saveContact(@Valid @ModelAttribute Contact contact, BindingResult result,Model model) {
		
		if(result.hasErrors())   {	
			model.addAttribute("categoryList",categoryDAO.getCategoryForFilter());
			model.addAttribute("last", blogDAO.getLastThreeBlog());
			return "front/contact";
				  }
		contactDAO.saveContact(contact);
		return "redirect:/contact";
	}
	
	@GetMapping("/blog-filter")
	public String getBlogPageFilter(@RequestParam int id,@RequestParam String type, Model model) 
 { 
		
		if(type.equalsIgnoreCase("category")) {	
			model.addAttribute("categoryList",categoryDAO.getCategoryForFilter());
			model.addAttribute("tagList", tagDAO.getTagListWithBlog());
			
			model.addAttribute("blogList", blogDAO.getBlogListByCategory(id));} 
		
		else if (type.equalsIgnoreCase("tag")) {
			model.addAttribute("categoryList",categoryDAO.getCategoryForFilter());			
			model.addAttribute("tagList", tagDAO.getTagListWithBlog());
			
			model.addAttribute("blogList", blogDAO.getBlogListByCategory(id)); 
			
		}
		return "front/blog";
	}
	
	@RequestMapping("/blog-post") 
	public String getBlogPost(@RequestParam Integer id,			
								@ModelAttribute("search") Search search,
								Model model) {
			
       Blog blog = blogDAO.getBlog(id);
       String searchText = search.getSearchText();	  
	    model.addAttribute("searchText", searchText);
		    
	    model.addAttribute("blog", blog);
		model.addAttribute("categoryList",categoryDAO.getCategoryForFilter());
		model.addAttribute("tagList", tagDAO.getTagsByBlogID(id)); 
		model.addAttribute("tags", tagDAO.getTagListWithBlog()); 
		model.addAttribute("last", blogDAO.getLastThreeBlog());
		model.addAttribute("author", authorDAO.getAuthor(id));
		model.addAttribute("seen", blogDAO.getSeenBlogsCount(id));
		model.addAttribute("formattedDate", blog.getFormattedDate());
		model.addAttribute("comment", new Comment());
		model.addAttribute("commentList", commentDAO.getCommentLIstById(id));
		model.addAttribute("count", blogDAO.getCommentCount(id));
		
		
		model.addAttribute("prevBlog", blogDAO.getPreviousBlogById(id));			
		model.addAttribute("nextBlog", blogDAO.getNextBlogById(id));	
		
		 String[] splitContent = blog.splitContent(blog.getContent2(), 100);
		 model.addAttribute("splitContent", splitContent);
		 
		 String firstThreeWords =blog.getFirstThreeWords(blog.getContent2());
		 model.addAttribute("firstThreeWords", firstThreeWords);
		 
		return "front/blog-post";
	}
	
	@RequestMapping("/comment-save")
	public String saveComment(@Valid @ModelAttribute Comment comment, 
			                  @RequestParam int id,
			                  @ModelAttribute("search") Search search,
			                  BindingResult result, Model model) {
	    if (result.hasErrors()) {   
	        Blog blog = blogDAO.getBlog(id);
	        model.addAttribute("blog", blog);
	        model.addAttribute("categoryList", categoryDAO.getCategoryForFilter());
	        model.addAttribute("tagList", tagDAO.getTagListWithBlog());
	        model.addAttribute("last", blogDAO.getLastThreeBlog());
	        model.addAttribute("author", authorDAO.getAuthor(id));
	        model.addAttribute("seen", blogDAO.getSeenBlogsCount(id));
	        model.addAttribute("formattedDate", blog.getFormattedDate());
	        model.addAttribute("count", blogDAO.getCommentCount(id));
	        model.addAttribute("commentList", commentDAO.getCommentLIstById(id));
	        return "front/blog-post";
	    }


	    if (comment.getDate() == null) {
	        comment.setDate(new java.sql.Date(System.currentTimeMillis())); // postavlja tekuci datum
	    }

	    commentDAO.saveComment(comment,id);
	    
	     Blog blog = blogDAO.getBlog(id);
	       String searchText = search.getSearchText();	  
		    model.addAttribute("searchText", searchText);
			 
		    model.addAttribute("blog", blog);
			model.addAttribute("categoryList",categoryDAO.getCategoryForFilter());
			model.addAttribute("tagList", tagDAO.getTagListWithBlog());
			model.addAttribute("last", blogDAO.getLastThreeBlog());
			model.addAttribute("author", authorDAO.getAuthor(id));
			model.addAttribute("seen", blogDAO.getSeenBlogsCount(id));
			model.addAttribute("formattedDate", blog.getFormattedDate());
			model.addAttribute("comment", new Comment());
			model.addAttribute("commentList", commentDAO.getCommentLIstById(id));
			model.addAttribute("count", blogDAO.getCommentCount(id));
			Blog prevBlog = blogDAO.getPreviousBlogById(id);
			model.addAttribute("prevBlog", prevBlog);	
			
			Blog nextBlog = blogDAO.getNextBlogById(id);
			model.addAttribute("nextBlog", nextBlog);
	    return "front/blog-post" ;

	}

	
	
	@RequestMapping("/blog-author")
	public String getAuthorsBlogs(@RequestParam("id") int authorId, 
			                      @RequestParam(value = "page", defaultValue = "1") int page,
			                      @ModelAttribute("search") Search search,
			                      Model model) {
		List<Blog> blogList=blogDAO.getBlogListForAuthor(authorId);
		model.addAttribute("authorsBlogs", blogList);
		
		String searchText = search.getSearchText();	  
	    model.addAttribute("searchText", searchText);
	    	 
	    model.addAttribute("categoryList", categoryDAO.getCategoryForFilter());
	    model.addAttribute("tagList", tagDAO.getTagListWithBlog());
	    model.addAttribute("last", blogDAO.getLastThreeBlog());
	    
	    for (Blog blog : blogList) {
            Integer commentCount = (int) blogDAO.getCommentCount(blog.getId());
            blog.setCount(commentCount);  
        }
	    
//		PAGINACIJA pocetak:

		 PagedListHolder<Blog> pagedListHolder = new PagedListHolder<>(blogList);
	    pagedListHolder.setPageSize(4);			      
    
        model.addAttribute("maxPages", pagedListHolder.getPageCount());

        if(page==0 || page < 1 || page > pagedListHolder.getPageCount())page=1;

        model.addAttribute("page", page);
        
        if(page == 0 || page < 1 || page > pagedListHolder.getPageCount()){
        	
            pagedListHolder.setPage(0);
            model.addAttribute("blogList", pagedListHolder.getPageList());
            
        }
        else if(page <= pagedListHolder.getPageCount()) {				        	
            pagedListHolder.setPage(page-1);
           
            model.addAttribute("blogList", pagedListHolder.getPageList());				            
        }	
		
	    
	    return "front/blog-author";
	}



	    @RequestMapping(value="/blog-category")	    
	    public String getBlogsForCategory(@RequestParam(value = "page", defaultValue = "1") int page,
	    	                            @RequestParam  Integer id,                       
	    	                            @ModelAttribute("search") Search search,
	    	                             Model model) {

        
        	model.addAttribute("selectedCategory", categoryDAO.getCategory(id));
	    	  
	        List<Blog> blogList = blogDAO.getBlogListByCategory(id);
	        model.addAttribute("blogList", blogList);

	        for (Blog blog : blogList) {
	            Integer commentCount = (int) blogDAO.getCommentCount(blog.getId());
	            blog.setCount(commentCount); 
	        }
 
	        model.addAttribute("categoryList", categoryDAO.getCategoryForFilter());
	        model.addAttribute("tagList", tagDAO.getTagListWithBlog());
	        model.addAttribute("last", blogDAO.getLastThreeBlog());
	        
	        String searchText = search.getSearchText();	  
		    model.addAttribute("searchText", searchText);
			
//			PAGINACIJA :

			 PagedListHolder<Blog> pagedListHolder = new PagedListHolder<>(blogList);
		    pagedListHolder.setPageSize(4);			      
	        
	        model.addAttribute("maxPages", pagedListHolder.getPageCount());

	        if(page==0 || page < 1 || page > pagedListHolder.getPageCount())page=1;

	        
	        model.addAttribute("page", page);
	        
	        if(page == 0 || page < 1 || page > pagedListHolder.getPageCount()){
	        	
	            pagedListHolder.setPage(0);
	            
	            model.addAttribute("blogList", pagedListHolder.getPageList());
	            
	        }
	        else if(page <= pagedListHolder.getPageCount()) {				        	
	            pagedListHolder.setPage(page-1);
	           
	            model.addAttribute("blogList", pagedListHolder.getPageList());				            
	        }	
			
	        return "front/blog-category";
	    }

	    @RequestMapping("/blog-tag")
	   public String getBlogsForTag(@RequestParam(value = "page", defaultValue = "1") int page,
			   						@ModelAttribute("search") Search search,
					                @RequestParam int id,
								    Model model) {
			
		   List<Blog> blogList = blogDAO.getBlogListByTag(id);
	       model.addAttribute("blogList", blogList);
		   model.addAttribute("selectedTag", tagDAO.getTag(id));
		   model.addAttribute("categoryList", categoryDAO.getCategoryForFilter());
		   model.addAttribute("tagBlog", tagDAO.getTagOnFrontPage());
		   model.addAttribute("tagList", tagDAO.getTagListWithBlog());
		   model.addAttribute("last", blogDAO.getLastThreeBlog());
		   String searchText = search.getSearchText();	  
		    model.addAttribute("searchText", searchText);
		    
		    for (Blog blog : blogList) {
	            Integer commentCount = (int) blogDAO.getCommentCount(blog.getId());
	            blog.setCount(commentCount); 
	        }
	
//			PAGINACIJA :

			 PagedListHolder<Blog> pagedListHolder = new PagedListHolder<>(blogList);
		    pagedListHolder.setPageSize(4);			      
	        
	      
	        model.addAttribute("maxPages", pagedListHolder.getPageCount());

	        if(page==0 || page < 1 || page > pagedListHolder.getPageCount())page=1;

	    
	        model.addAttribute("page", page);
	        
	        if(page == 0 || page < 1 || page > pagedListHolder.getPageCount()){
	        	
	            pagedListHolder.setPage(0);
	           
	            model.addAttribute("blogList", pagedListHolder.getPageList());
	            
	        }
	        else if(page <= pagedListHolder.getPageCount()) {				        	
	            pagedListHolder.setPage(page-1);
	            
	            model.addAttribute("blogList", pagedListHolder.getPageList());				            
	        }	
		   
		   return "front/blog-tag";
	   }
	
}







