package com.myworld.car.parking.utils.exceptions;

import com.myworld.car.parking.ExceptionCodes;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CarParkingException.class)
	public ResponseEntity<ErrorDTO> handleCarParkingException(CarParkingException ex, WebRequest request) {
		ErrorDTO errorDTO = new ErrorDTO(ex.getLocalizedMessage(), ex.getExceptionCode(), ex.getFieldErrors());
		return buildResponseEntity(ex.getHttpStatus(), errorDTO);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		List<FieldError> errors = buildFieldErrors(ex);
		ErrorDTO errorDTO = new ErrorDTO(ex.getLocalizedMessage(), ExceptionCodes.VALIDATION_FAILED, errors);
		return buildResponseEntity(HttpStatus.BAD_REQUEST, errorDTO);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldError> errors = buildFieldErrors(ex.getBindingResult());
		ErrorDTO errorDTO = new ErrorDTO("Method argument validation failed", ExceptionCodes.VALIDATION_FAILED, errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
	}

	private List<FieldError> buildFieldErrors(ConstraintViolationException ex) {
		return ex.getConstraintViolations()
				.stream()
				.map(this::buildFieldError)
				.collect(Collectors.toList());
	}

	private FieldError buildFieldError(ConstraintViolation<?> constraintViolation) {
		String path = constraintViolation.getPropertyPath().toString();
		int fieldStartIndex = path.lastIndexOf('.') + 1;
		String field = path.substring(fieldStartIndex);
		return new FieldError(field, constraintViolation.getMessage());
	}

	private List<FieldError> buildFieldErrors(BindingResult bindingResult) {
		return bindingResult.getAllErrors()
				.stream()
				.map(this::buildFieldError)
				.collect(Collectors.toList());
	}

	private FieldError buildFieldError(ObjectError error) {
		String field = ((org.springframework.validation.FieldError) error).getField();
		String errorMessage = Optional.ofNullable(error.getDefaultMessage()).orElse("");
		return new FieldError(field, errorMessage);

	}

	private ResponseEntity<ErrorDTO> buildResponseEntity(HttpStatus httpStatus, ErrorDTO errorDTO) {
		return ResponseEntity
				.status(httpStatus)
				.body(errorDTO);
	}

}
