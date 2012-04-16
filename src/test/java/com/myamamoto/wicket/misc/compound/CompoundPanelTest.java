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

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class CompoundPanelTest {

    private WicketTester tester;
    private FormComponent<Person> panel;
    private FormComponent<String> name;
    private FormComponent<Integer> age;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
	tester = new WicketTester();
	panel = (FormComponent<Person>)tester
	    .startComponentInPage(CompoundPanel.class);
	name = (FormComponent<String>)tester
	    .getComponentFromLastRenderedPage("name");	
	age = (FormComponent<Integer>)tester
	    .getComponentFromLastRenderedPage("age");
    }

    @Test
    public void testComponents() {
	tester.assertComponent("name", TextField.class);
	tester.assertComponent("age", TextField.class);
    }

    @Test
    public void testConvertInput() {
	name.setModelValue(new String[]{"NAME"});
	age.setModelValue(new String[]{"0"});

	panel.processInput();
	assertThat(panel.hasErrorMessage(), is(false));

	Person person = panel.getModelObject();
	assertThat(person.getName(), is("NAME"));
	assertThat(person.getAge(), is(0));
    }

    @Test
    public void testValidate() {
	name.setModelValue(new String[]{"NAME"});
	age.setModelValue(new String[]{"-1"});

	panel.processInput();
	assertThat(panel.hasErrorMessage(), is(true));
    }
}
