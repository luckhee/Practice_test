package kr.co.testshortenurlservice.presentation;

import kr.co.testshortenurlservice.domain.LackOfShortenUrlkeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LackOfShortenUrlkeyException.class)
    public ResponseEntity<String> handleLackOfShortenUrlKeyException(LackOfShortenUrlkeyException exception) {
        return new ResponseEntity<>("단축 url 자원이 부족합니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
