package br.com.adal.erro;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalRestExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public void defaultExceptionHandler() {
        
    }
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ApiErrorResponse handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex){

	     ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
	        .withStatus(HttpStatus.BAD_REQUEST)
	        .withError_code("BAD_REQUEST")
	        .withMessage(ex.getMessage())
	        .build();
	     return response;
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ApiErrorResponse handleHttpMessageNotReadable(HttpMessageNotReadableException ex) { 
		String error = "Malformed JSON request "; 
		ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder() 
				.withError_code("BAD_DATA") 
				.withMessage(ex.getMessage()) 
				.withDetail(error) 
				.build(); 
		return response; 
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ApiErrorResponse handleEntityNotFound(EntityNotFoundException ex) {
		String error = "Produto(s) não localizado na base de dados";
		ApiErrorResponse response = new ApiErrorResponse.ApiErrorResponseBuilder()
				.withError_code("404")
				.withStatus(HttpStatus.NOT_FOUND)
				.withMessage(ex.getMessage())
				.withDetail(error)
				.build();
		return response;
	}
	
}
