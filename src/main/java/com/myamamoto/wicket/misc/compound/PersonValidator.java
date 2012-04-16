/*
 * Copyright 2012 m_yamamo0417
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.myamamoto.wicket.misc.compound;

import org.apache.wicket.validation.CompoundValidator;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.Validatable;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.AbstractValidator;
import org.apache.wicket.validation.validator.MinimumValidator;

class PersonValidator extends AbstractValidator<Person>{
    private CompoundValidator<String> nameValidator;
    private CompoundValidator<Integer> ageValidator;
    
    PersonValidator() {
	nameValidator = new CompoundValidator<String>();
	ageValidator = new CompoundValidator<Integer>();
	ageValidator.add(new MinimumValidator<Integer>(0){
		public void validate(IValidatable<Integer> validatable){
		    Integer value = validatable.getValue();
		    Integer minimum = getMinimum();
		    if (value.compareTo(minimum) < 0) {
			ValidationError error = new ValidationError();
			error.addMessageKey(resourceKey());
			error.setVariable("minimum", minimum);
			error.setVariable("input", value);
			error.setVariable("label", "age");
			validatable.error(error);
		    }
		}
	    });
    }
    
    protected void onValidate(IValidatable<Person> validatable){
	Person p = validatable.getValue();
	if(null == p.getName()){
	    ValidationError error = new ValidationError();
	    error.addMessageKey("Required");
	    error.setVariable("label", "name");
	    validatable.error(error);
	}else{
	    Validatable<String> name = new Validatable<String>(p.getName());
	    nameValidator.validate(name);
	    for(IValidationError error: name.getErrors()){
		validatable.error(error);
	    }
	}

	if(null == p.getAge()){
	    ValidationError error = new ValidationError();
	    error.addMessageKey("Required");
	    error.setVariable("label", "age");
	    validatable.error(error);
	}else{	    
	    Validatable<Integer> age = new Validatable<Integer>(p.getAge());
	    ageValidator.validate(age);
	    for(IValidationError error: age.getErrors()){
		validatable.error(error);
	    }
	}
    }
}
