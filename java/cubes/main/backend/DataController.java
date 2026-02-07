package cubes.main.backend;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.Date;
import java.time.LocalDate;

public class DataController {  //da postavim datum kroz ceo projekat

	 @ModelAttribute("currentDate")
	    public Date currentDate() {
	        return Date.valueOf(LocalDate.now());
	    }
	
}
