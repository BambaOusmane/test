//package com.school.school.security.Controller.Advice;
//
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.security.SignatureException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ProblemDetail;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.Map;
//
//import static org.springframework.http.HttpStatus.UNAUTHORIZED;
//
//@RestControllerAdvice
//@Slf4j
//public class ApplicationControllerAdvice {
//
//    @ResponseStatus(UNAUTHORIZED)
//    @ExceptionHandler(value = BadCredentialsException.class)
//    public @ResponseBody ProblemDetail dadCredentialsException(BadCredentialsException exception){
//        ApplicationControllerAdvice.log.error(exception.getMessage(),exception);
//   final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(UNAUTHORIZED, "indentifiant");
//     problemDetail.setProperty("erreur","Nous n'avons pas pu vous identifier");
//        return  problemDetail;
//    }
//
//
//    @ResponseStatus(UNAUTHORIZED)
//    @ExceptionHandler(value = {MalformedJwtException.class, SignatureException.class})
//    public @ResponseBody
//    ProblemDetail dadCredentialsException(final Exception exception){
//        ApplicationControllerAdvice.log.error(exception.getMessage(),exception);
//   return ProblemDetail.forStatusAndDetail(
//           UNAUTHORIZED, "Token invalide");
//
//    }
//    @ResponseStatus(UNAUTHORIZED)
//    @ExceptionHandler(value = Exception.class)
//    public Map<String,String > exceptionHandler(){
//        return Map.of("erreur","description");
//    }
//}
