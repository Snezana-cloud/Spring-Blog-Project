package cubes.main;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource myDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   auth.jdbcAuthentication().dataSource(myDataSource);  
	
		 
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests(requests -> requests
                   
                .antMatchers("/").permitAll()  
                .antMatchers("/administration/category-list").hasRole("admin")
                .antMatchers("/administration/category-form").hasRole("admin")
                .antMatchers("/administration/tag-list").hasRole("admin")
                .antMatchers("/administration/tag-form").hasRole("admin")
                .antMatchers("/administration/blog-list").hasAnyRole("admin,author") 
                .antMatchers("/administration/blog-form").hasAnyRole("admin,author")
                .antMatchers("/administration/slider-list").hasRole("admin")
                .antMatchers("/administration/slider-form").hasRole("admin")
                .antMatchers("/administration/author-list").hasRole("admin")
                .antMatchers("/administration/author-form").hasRole("admin")
                .antMatchers("/administration/user-list").hasRole("admin")
                .antMatchers("/administration/comment-list").hasAnyRole("admin, author") 
                .antMatchers("/administration/**").hasAnyRole("author,admin")).formLogin(login -> login
                .loginPage("/loginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll());

     
        http.cors(withDefaults()).csrf(csrf -> csrf.disable()); 
	}
}


