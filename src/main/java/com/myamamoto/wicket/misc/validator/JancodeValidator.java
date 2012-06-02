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

package com.myamamoto.wicket.misc.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.AbstractValidator;
import org.apache.wicket.validation.validator.PatternValidator;

public class JancodeValidator extends AbstractValidator<String> {
    private static final String REGEX = "\\p{Digit}{13}";

    private final IValidator<String> regexValidator;

    public JancodeValidator() {
	this.regexValidator = new PatternValidator(REGEX);
    }

    @Override
    protected void onValidate(IValidatable<String> validatable){
	this.regexValidator.validate(validatable);
	if(!validatable.isValid()){
	    return;
	}
	String jancode = validatable.getValue();
	if(!this.isValidJancode(jancode)){
	    super.error(validatable);
	}
    }

    private boolean isValidJancode(String jancode){
	if(null == jancode){
	    throw new AssertionError("jancode is null.");
	}
	if(13 != jancode.length()){
	    throw new AssertionError("jancode length is not 13.");
	}
	try{
	    int[] parsed = new int[13];
	    for(int i = 0; i < jancode.length(); i++){
		parsed[i] = Integer.parseInt(jancode.substring(i, i + 1));
	    }
	    int checkDigit = parsed[parsed.length - 1];
	    int evenResult = 0;
	    int oddResult = 0;
	    for(int i = 0; i < 13; i++){
		// get position in jancode
		int position = parsed.length - i;
		if(1 == position){
		    // position 1 is checkdigit
		    continue;
		}
		if(0 == position % 2){
		    evenResult += parsed[i];
		}else{
		    oddResult += parsed[i];
		}
	    }
	    evenResult = evenResult * 3;
	    int expected = (10 - ((oddResult + evenResult) % 10)) % 10;
	    
	    return checkDigit == expected;
	}catch(NumberFormatException e){
	    throw new AssertionError("jancode contains non-digit character.");
	}
    }
}
