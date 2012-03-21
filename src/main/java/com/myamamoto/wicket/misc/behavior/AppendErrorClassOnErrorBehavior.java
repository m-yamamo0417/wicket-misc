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

package com.myamamoto.wicket.misc.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;

public class AppendErrorClassOnErrorBehavior
    extends Behavior {

    public static final String ATTRIBUTE_CLASS = "class";

    private final String errorClass;
    private final String errorClassWithComma;

    public AppendErrorClassOnErrorBehavior() {
	this("error");
    }

    public AppendErrorClassOnErrorBehavior(String errorClass) {
	if(null == errorClass) {
	    throw new NullPointerException("errorClass is null");
	}
	if(errorClass.isEmpty()){
	    throw new IllegalArgumentException("errorClass is empty");
	}
	this.errorClass = errorClass;
	this.errorClassWithComma = ", " + errorClass;
    }

    @Override
    public void onComponentTag(Component arg0, ComponentTag arg1) {
	super.onComponentTag(arg0, arg1);
	String orgClass = arg1.getAttribute(ATTRIBUTE_CLASS);

	if(arg0.hasErrorMessage()){
	    String newClass;
	    boolean existsOrgClass = null != orgClass && !orgClass.isEmpty();
	    if(existsOrgClass){
		newClass = orgClass + this.errorClassWithComma;
	    }else{
		newClass = this.errorClass;
	    }
	    arg1.put(ATTRIBUTE_CLASS, newClass);
	}else{
	    boolean existsOrgClass =
		null != orgClass
		&& !orgClass.isEmpty()
		&& !this.errorClass.equals(orgClass);
	    if(existsOrgClass){
		arg1.put(ATTRIBUTE_CLASS,
			 orgClass.replaceAll(this.errorClassWithComma, ""));
	    }else{
		arg1.remove(ATTRIBUTE_CLASS);
	    }
	}
    }
}
