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

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.INullAcceptingValidator;

public class NotNullValidator<T> implements INullAcceptingValidator<T> {
    private final String label;

    public NotNullValidator(String label) {
	this.label = label;
    }

    public void validate(IValidatable<T> validatable){
	if(null == validatable.getValue()){
	    ValidationError error = new ValidationError();
	    error.addMessageKey("Required");
	    error.setVariable("label", label);
	    validatable.error(error);
	}
    }

}
