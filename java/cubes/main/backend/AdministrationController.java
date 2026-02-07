package cubes.main.backend;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import cubes.main.dao.AuthorDAO;
import cubes.main.dao.BlogDAO;

import cubes.main.dao.CategoryDAO;
import cubes.main.dao.CommentDAO;
import cubes.main.dao.ContactDAO;
import cubes.main.dao.RoleDAO;
import cubes.main.dao.SliderDAO;
import cubes.main.dao.TagDAO;
import cubes.main.dao.UserDAO;
import cubes.main.entity.Author;
import cubes.main.entity.Blog;
import cubes.main.entity.Comment;
import cubes.main.entity.Category;
import cubes.main.entity.ChangePassword;
import cubes.main.entity.Contact;

import cubes.main.entity.Slider;
import cubes.main.entity.Tag;
import cubes.main.entity.Users;
import jakarta.validation.Valid;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.HashSet;



@Controller
@RequestMapping("/administration")
public class AdministrationController {

	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private TagDAO tagDAO;
	@Autowired
	private BlogDAO blogDAO;
	@Autowired
	private SliderDAO sliderDAO;
	@Autowired
	private AuthorDAO authorDAO;
	@Autowired
	private ContactDAO contactDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private CommentDAO commentDAO;
	@Autowired
	private RoleDAO roleDAO;

	@RequestMapping("/category-list")
	public String getCateogyList( Model  model) {

		List<Category> list = categoryDAO.getCategorylist();

		model.addAttribute("categorylist", list);
		model.addAttribute("contactCount", contactDAO.getUnSeenComment());
		return "category-list";
	}

	@RequestMapping("/category-form")
	public String getCategoryForm(@RequestParam Integer orderCategory , Model model) {

		Category category = new Category();
		model.addAttribute("category", category);
		model.addAttribute("order", categoryDAO.getCategoryByOrder(orderCategory));
		model.addAttribute("contactCount", contactDAO.getUnSeenComment());
		return "category-form";
	}

	@RequestMapping("/category-save")
	public String saveCategory(@Valid @ModelAttribute Category category, BindingResult result ) {

		if (result.hasErrors()) {
			return "category-form";
		}
		
		categoryDAO.saveCategory(category);
		
		return "redirect:/administration/category-list"; 
	}

    @RequestMapping("/category-form-update")
    public String getCategoryUpdateForm(@RequestParam int id,@RequestParam Integer orderCategory,
    		Model model) {
        Category category = categoryDAO.getCategory(id);
        if (category == null) {
            return "redirect:/administration/category-list";
        }
        model.addAttribute("category", category);
        model.addAttribute("contactCount", contactDAO.getUnSeenComment());
        return "category-form";
    }
    

	
	@RequestMapping("/category-form-delete/{id}")
	public String deleteCategoryForm(@PathVariable int id) {
		categoryDAO.deleteCategory(id);
		return "redirect:/administration/category-list";
	}
	// -----------------------------------tag-----------------------------------------

	@RequestMapping("/tag-list")
	public String getTagList(Model model) {

		List<Tag> list = tagDAO.getTagList();
		model.addAttribute("tagList", list);
		model.addAttribute("contactCount", contactDAO.getUnSeenComment());

		return "tag-list";
	}

	@RequestMapping("/tag-form")
	public String getTag(Model model) {
		Tag tag = new Tag();
		model.addAttribute("tag", tag);
		model.addAttribute("contactCount", contactDAO.getUnSeenComment());
		return "tag-form";
	}

	@RequestMapping("/tag-save")
	public String saveTag(@Valid @ModelAttribute Tag tag, BindingResult result) {

		if (result.hasErrors()) {
			return "tag-form";
		}
		tagDAO.saveTag(tag);
		return "redirect:/administration/tag-list";
	}

	@RequestMapping("/tag-delete")
	public String deleteTag(@RequestParam int id, Model model) {
		tagDAO.deleteTag(id);
		return "redirect:/administration/tag-list";
	}

	@RequestMapping("/tag-update")
	public String updateTag(@RequestParam int id, Model model) {
		Tag tag = tagDAO.getTag(id);
		model.addAttribute("tag", tag);
		model.addAttribute("contactCount", contactDAO.getUnSeenComment());
		return "tag-form";
	}
	// -------------------------------------blog-------------------------------------------------------
		
	@RequestMapping("/blog-list")
	public String getBlogList(Principal principal,
	        @RequestParam(name = "titleId", required = false) Integer titleId,
	        @RequestParam(name = "categoryId", required = false) Integer categoryId,
	        @RequestParam(name = "authorId", required = false) Integer id,
	        @RequestParam(name = "enabledId", required = false) Boolean enabledId, 
	        @RequestParam(name = "blogId", required = false) Integer blogId,
	        @RequestParam(required = false) Integer page, 
	        Model model) {

	    Users loggedUser = userDAO.getUserByUsername(principal.getName());  
	    List<Blog> list = new ArrayList<>();
	    
	    if (loggedUser.isAdmin()) {
	     
	        if (titleId != null) {
	            list = blogDAO.getBlogListByTitle(titleId);
	        } else if (categoryId != null) {
	            list = blogDAO.getBlogListByCategory(categoryId);
	        } else if (id != null) {
	            list = blogDAO.getBlogListForAuthor(id);
	        } else if (enabledId != null) {
	            list = blogDAO.getBlogListByEnabledStatus(enabledId);
	        } else {
	            list = blogDAO.getBlogList();
	        }

	       
	        List<Category> categoryList = categoryDAO.getCategorylist();
	        Set<Author> uniqueAuthors = new HashSet<>(); // Koristi Set za jedinstvene autore

	        for (Blog blog : list) {
	            uniqueAuthors.add(blog.getAuthor());
	            Integer commentCount = (int) blogDAO.getCommentCount(blog.getId()); // Izračunavanje broja komentara
	            blog.setCount(commentCount);
	        }

	        List<Author> allAuthors = new ArrayList<>(uniqueAuthors);
	        
	        model.addAttribute("blogList", list);
	        model.addAttribute("categories", categoryList);
	        model.addAttribute("authors", allAuthors);
	        model.addAttribute("contactCount", contactDAO.getUnSeenComment());
	        model.addAttribute("important", blogDAO.getLastThreeImportantBlog());
	        model.addAttribute("count", blogDAO.getCommentCount(id));

	        if (blogId != null) {
	            model.addAttribute("seenBlogs", blogDAO.getSeenBlogsCount(blogId));
	        } else {
	            model.addAttribute("seenBlogs", 0L); 
	        }
	        
	    } else {
	        
	        list = blogDAO.getBlogsByUsername(principal);
	        
	        Set<Author> uniqueAuthors = new HashSet<>(); 

	        for (Blog blog : list) {
	            uniqueAuthors.add(blog.getAuthor());
	            Integer commentCount = (int) blogDAO.getCommentCount(blog.getId()); 
	            blog.setCount(commentCount);
	        }

	        List<Author> allAuthors = new ArrayList<>(uniqueAuthors);
	        	     
	        List<Category> categoryList = categoryDAO.getCategorylist();
	        
	        model.addAttribute("blogList", list);
	        model.addAttribute("categories", categoryList);
	        model.addAttribute("authors", allAuthors);
	        model.addAttribute("contactCount", contactDAO.getUnSeenComment());
	        model.addAttribute("important", blogDAO.getLastThreeImportantBlog());
	        model.addAttribute("count", blogDAO.getCommentCount(id));

	        if (blogId != null) {
	            model.addAttribute("seenBlogs", blogDAO.getSeenBlogsCount(blogId));
	        } else {
	            model.addAttribute("seenBlogs", 0L); 
	        }
	    }
  
	    //paginacija
		 PagedListHolder<Blog> pagedListHolder = new PagedListHolder<>(list);
		    pagedListHolder.setPageSize(4);			            
	   
	        model.addAttribute("maxPages", pagedListHolder.getPageCount());
	        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

	        model.addAttribute("page", page);
	        
	        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
	        	
	            pagedListHolder.setPage(0);
	            model.addAttribute("blogList", pagedListHolder.getPageList());
	            
	        }
	        else if(page <= pagedListHolder.getPageCount()) {				        	
	            pagedListHolder.setPage(page-1);
	           
	            model.addAttribute("blogList", pagedListHolder.getPageList());				            
	        }
	    return "blog-list";
	}

	@RequestMapping("/blog-enable")
	public String enabledBlog(@RequestParam int id) {
		blogDAO.enabledBlog(id);
		return "redirect:/administration/blog-list";
	}
	@RequestMapping("/blog-important")
	public String importantBlog(@RequestParam int id) {
		blogDAO.importantBlog(id);
		return "redirect:/administration/blog-list";
	}

	@RequestMapping("/blog-form")
	public String getBlog(Model model) {
		Blog blog = new Blog();

		List<Category> categoryList = categoryDAO.getCategorylist();
		List<Tag> tagList = tagDAO.getTagList();
		List<Author> authorList = authorDAO.getAuthorList();

		model.addAttribute("blog", blog);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("authorList", authorList);
		model.addAttribute("tagList", tagList);
		model.addAttribute("contactCount", contactDAO.getUnSeenComment());
		return "blog-form";
	}

	@RequestMapping("/blog-delete")
	public String deleteBlog(@RequestParam int id) {
		blogDAO.deleteBlog(id);
		return "redirect:/administration/blog-list";
	}
	
	
	@RequestMapping("/blog-save")
	public String saveBlog(@Valid @ModelAttribute Blog blog,@RequestParam int id,  Model model) {

        LocalDate today = LocalDate.now(ZoneId.of("Europe/Belgrade"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(today); 
        blog.setTime(sqlDate);
	
	    Author author = authorDAO.getAuthor(blog.getAuthor().getId());
	    blog.setAuthor(author);

	    Category category = categoryDAO.getCategory(blog.getCategory().getId());
	    blog.setCategory(category);

	    List<Integer> ids=new ArrayList<Integer>();
	    
	    for(Tag tag: blog.getTag()) {
			   ids.add(tag.getId());             
			   
		}
		List<Tag> tags=tagDAO.getTagsByID(ids); 
		
		blog.setTag(tags); 

	    if (blog.getId() == 0) { 
	        blog.setIsSeen(0L); 
	        blog.setImportant(false);
	        blog.setEnable(true);
	    }

	    blogDAO.saveBlog(blog);

	    return "redirect:/administration/blog-list";
	}

	@RequestMapping("/blog-update")  
	public String updateBlog(@RequestParam int id, Model model) {
		Blog blog = blogDAO.getBlogWithTag(id);
		List<Category> categoryList = categoryDAO.getCategorylist();
		List<Tag> tagList = tagDAO.getTagList();
		
		List<Author> allAuthors = authorDAO.getAuthorList();

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("authorList", allAuthors);
		model.addAttribute("tagList", tagList);
		model.addAttribute("blog", blog);
		model.addAttribute("contactCount", contactDAO.getUnSeenComment());
		return "blog-form";
	}


	// ----------------------------------------------slider-----------------------------------------------
	@RequestMapping("/slider-save")
	public String saveSlider(@Valid @ModelAttribute Slider slider, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "slider-form";
		}
		sliderDAO.saveSlider(slider);
		return "redirect:/administration/slider-list";
	}

	@RequestMapping("/slider-list")
	public String getSliderLIst(Model model) {

		List<Slider> listslider = sliderDAO.getSliderList();

		model.addAttribute("sliderlist", listslider);
		model.addAttribute("contactCount", contactDAO.getUnSeenComment());
		return "slider-list";
	}

	@RequestMapping("/slider-update")
	public String updateSlider(@RequestParam int id, Model model) {
		Slider slider = sliderDAO.getSlider(id);
		model.addAttribute("slider", slider);
		model.addAttribute("contactCount", contactDAO.getUnSeenComment());
		return "slider-form";
	}

	@RequestMapping("/slider-delete")
	public String deleteSlider(@RequestParam int id) {
		sliderDAO.deleteSlider(id);
		return "redirect:/administration/slider-list";
	}

	@RequestMapping("/slider-form")
	public String getSlider(Model model) {
		Slider slider = new Slider();
		model.addAttribute("slider", slider);
		model.addAttribute("contactCount", contactDAO.getUnSeenComment());
		return "slider-form";
	}
	// ----------------------------------author-----------------------------------------------------------

	@RequestMapping("/author-list")
	public String getAuthorList(Model model) {
		List<Author> listAuthor = authorDAO.getAuthorList();
		model.addAttribute("authorList", listAuthor);
		model.addAttribute("contactCount", contactDAO.getUnSeenComment());
		return "author-list";
	}

	@RequestMapping("/author-delete")
	private String deleteAuthor(@RequestParam int id) {
		authorDAO.deleteAuthor(id);
		return "redirect:/administration/author-list";
	}

	@RequestMapping("/author-save")
	public String saveAuthor(@Valid @ModelAttribute Author author, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return " author-form";
		}
		authorDAO.saveAuthor(author);
		return "redirect:/administration/author-list";
	}

	@RequestMapping("/author-form")
	public String getAuthor(Model model) {
		Author author = new Author();
		model.addAttribute("author", author);
		model.addAttribute("contactCount", contactDAO.getUnSeenComment());
		return "author-form";
	}

	@RequestMapping("/author-update")
	public String updateAuthor(@RequestParam int id, Model model) {
		Author author = authorDAO.getAuthor(id);
		model.addAttribute("author", author);
		model.addAttribute("contactCount", contactDAO.getUnSeenComment());
		return "author-form";
	}


	// --------------------------------users----------------------------------------------------------------------------
	@RequestMapping("/user-list")
	public String getUsersList( Model model) {  
		
		model.addAttribute("contactCount", contactDAO.getUnSeenComment());
		model.addAttribute("userList", userDAO.getUserList());
	
		return "user-list";
		
	}

	@RequestMapping("/user-enable")
	public String enableUser(@RequestParam String username) {
		userDAO.enabledUser(username);
		return "redirect:/administration/user-list";
	}

	@RequestMapping("/user-form")
	public String getUserForm(Model model) {

		model.addAttribute("user", new Users());
		model.addAttribute("listRole", roleDAO.getRoleList());
															
		return "user-form";
	}

	@RequestMapping("/user-save")
	public String getSaveUser(@Valid @ModelAttribute("user") Users user,BindingResult result,Model model) {

		if(result.hasErrors()) {
			model.addAttribute("role", roleDAO.getRoleList());
			return "user-form";
		}
		
		System.out.print("pasw=" + user.getPassword());

		String paswwordEncode = new BCryptPasswordEncoder().encode(user.getPassword()); 
	

		user.setEnabled(true); 
		user.setPassword("{bcrypt}" + paswwordEncode);
		model.addAttribute("role", roleDAO.getRoleList());
		userDAO.saveUser(user);

		return "redirect:/administration/user-list";
	}

	@RequestMapping("/user-delete")
	public String getDeleteUser(@RequestParam String username) {

		userDAO.deleteUser(username);
		
		return "redirect:/administration/user-list";
		
	}

	@RequestMapping("/user-edit-profile")
	public String getUserEditProfile(Principal principal, Model model) {
	
		Users user = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("user", user);
	
		return "user-edit-profile";
	}

	@RequestMapping("/user-edit")
	public String getEditUser(@ModelAttribute Users user) { 

		userDAO.saveUser(user);

		return "redirect:/administration/user-list";
	}

	@RequestMapping("/user-change-password") 
	public String gerUserChangePAssword(Model model) {
	
		model.addAttribute("changePassword", new ChangePassword());
		return "user-change-password";
	}

	@RequestMapping("/user-change-password-save") 
	public String gerUserChangePAsswordAction(@ModelAttribute ChangePassword changePassword, BindingResult result,
			Principal principal, Model model) {
		
		if (changePassword.getNewPassword().equalsIgnoreCase(changePassword.getConfirmPassword())) {
																									
			Users user = userDAO.getUserByUsername(principal.getName());
		
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			if (encoder.matches(changePassword.getOldPassword(), user.getPassword().replace("{bcrypt}", ""))) {
				user.setPassword("{bcrypt}" + encoder.encode(changePassword.getNewPassword()));
																								
				userDAO.saveUser(user);
			} else {
					model.addAttribute("message","OldPassword is wrong");
					model.addAttribute("changePassword", changePassword); 
					return "user-change-password"; 
				
			}
		} else {
	
			   model.addAttribute("message", "NewPassword and ConfirmPassworm are not a same");
			   model.addAttribute("changePassword", changePassword); 
				return "user-change-password"; 
		
		}

		return "redirect:/administration/user-list";
		
	}
	  
	
	// -------------------------------contact----------------------------------------------------------------------------

	@RequestMapping("/contact-list")
	public String getContactList(Model model) {
		model.addAttribute("contactList", contactDAO.getContactList());
		model.addAttribute("contactCount", contactDAO.getUnSeenComment());
		return "contact-list";
	}

	@RequestMapping("/contact-seen")
	public String getMarkComment(@RequestParam int id) {
		Contact c = contactDAO.getContact(id);
		c.setIsSeen(true);
		contactDAO.saveContact(c);
		return "redirect: contact-list";
	}
	//------------------------- -------comment on blog-post---------------------------------------------
	

	
	@RequestMapping("/comment-list")   
	public String getCommentList(Principal principal,
			                     @RequestParam(required = false) Integer page,
			                     Model model) {
		
		Users loggedUser = userDAO.getUserByUsername(principal.getName());		
		List<Comment> comments = new ArrayList<>();
		if(loggedUser.isAdmin()) {
		
			comments = commentDAO.getCommentList();	
		}else {
			
			 comments = commentDAO.getCommentsByAuthorUsername(loggedUser.getUsername());	
		}	
//paginacija
	    PagedListHolder<Comment> pagedListHolder = new PagedListHolder<>(comments);
	    pagedListHolder.setPageSize(12);

	    if (page == null || page < 1) {
	        page = 1;
	    }
	    
	    int pageCount = pagedListHolder.getPageCount();
	    if (page > pageCount) {
	        page = pageCount;
	    }

	    pagedListHolder.setPage(page - 1); 

	    // Dodavanje atributa za paginaciju u model
	    model.addAttribute("commentList", pagedListHolder.getPageList());
	    model.addAttribute("page", page);
	    model.addAttribute("maxPages", pageCount);

	    model.addAttribute("isAdmin", loggedUser.isAdmin());
	    model.addAttribute("loggedUser", loggedUser);			        
  	
		return "comment-list";
	}
	
	
	@RequestMapping("/comment-enable")
	public String getEnabledComment(@RequestParam int id) {
		commentDAO.enabledComment(id);  
		
		return "redirect: comment-list";
	}

	@RequestMapping("/comment-save")
	public String saveComment(@ModelAttribute Comment comment,@RequestParam int id) {
		
		Blog blog = blogDAO.getBlog(id);
		 comment.setBlog(blog);
	    comment.setDate(new java.sql.Date(System.currentTimeMillis())); 
	    comment.setEnabled(true); 

	    commentDAO.saveComment(comment, id);
	    return "redirect:/administration/comment-list";
		
	}

}