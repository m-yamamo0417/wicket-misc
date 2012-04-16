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

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

public class CompoundPanel extends FormComponentPanel<Person> {
    private TextField<String> name;
    private TextField<Integer> age;

    public CompoundPanel(String id){
	super(id);
	super.setModel(new CompoundPropertyModel<Person>(new Person()));
    }

    protected void onInitialize() {
	super.onInitialize();
	add(name = new TextField<String>("name", String.class));
	add(age = new TextField<Integer>("age", Integer.class));
	add(new PersonValidator());
    }

    protected void convertInput() {
	Person p = super.getModelObject();
	p.setName(name.getModelObject());
	p.setAge(age.getModelObject());
	super.setConvertedInput(p);
    }
}
