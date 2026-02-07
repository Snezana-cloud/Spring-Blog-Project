package cubes.main.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cubes.main.exceptions.NotFoundExcepton;

@ControllerAdvice
public class RestExceptionHandler { 

	@ExceptionHandler 
	public ResponseEntity<MessageResponse> handlerException(Exception exception){  

		
		MessageResponse error=new MessageResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis()); 
	
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler  
	public ResponseEntity<MessageResponse> handlerException(NotFoundExcepton exception){  
		
		MessageResponse error=new MessageResponse(HttpStatus.NOT_FOUND.value(),exception.getMessage(),System.currentTimeMillis()); 
		
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
}
	
