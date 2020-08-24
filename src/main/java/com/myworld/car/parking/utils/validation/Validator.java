package com.myworld.car.parking.utils.validation;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validator {

	private final Map<ValidationObjectType, ConcreteValidator> concreteValidators;

	@Autowired
	public Validator(List<ConcreteValidator> concreteValidators) {
		this.concreteValidators = concreteValidators.stream()
				.collect(Collectors.toMap(ConcreteValidator::getType, Function.identity()));
	}

	@SuppressWarnings("unchecked")
	public void validate(ValidationObject validationObject) {
		ConcreteValidator concreteValidator = chooseValidator(validationObject);
		concreteValidator.validate(validationObject);
	}

	private ConcreteValidator chooseValidator(ValidationObject validationObject) {
		ValidationObjectType type = new ValidationObjectType(validationObject.getClass());
		if (!concreteValidators.containsKey(type)) {
			throw new IllegalArgumentException("Can not find validator for type: " + type);
		}
		return concreteValidators.get(type);
	}

}
