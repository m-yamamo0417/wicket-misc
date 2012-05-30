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

package com.myamamoto.wicket.misc.convert.converter;

import org.apache.wicket.util.convert.converter.AbstractConverter;
import java.util.Locale;
import org.apache.wicket.util.convert.ConversionException;

public class BooleanConverter extends AbstractConverter<Boolean> {
    private final String trueString;
    private final String falseString;
    
    public BooleanConverter(String trueString){
	this(trueString, "");
    }

    public BooleanConverter(String trueString, String falseString){
	if(null == trueString
	   || null == falseString){
	    throw new NullPointerException();
	}
	this.trueString = trueString;
	this.falseString = falseString;
    }

    @Override
    public Class<Boolean> getTargetType() {
	return Boolean.class;
    }

    @Override
    public Boolean convertToObject(String value, Locale locale){
	if(this.trueString.equals(value)){
	    return Boolean.TRUE;
	}else if(this.falseString.equals(value)){
	    return Boolean.FALSE;
	}else{
	    throw new ConversionException("can't convert: " + value);
	}
    }

    @Override
    public String convertToString(Boolean bool, Locale locale) {
	return bool ? this.trueString: this.falseString;
    }
}
