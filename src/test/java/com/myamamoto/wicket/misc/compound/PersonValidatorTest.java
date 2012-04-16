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
import org.apache.wicket.validation.Validatable;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class PersonValidatorTest {
    private Validatable<Person> validatable;
    private PersonValidator validator;

    @Before
    public void setUp() {
	Person p = new Person();
	p.setName("");
	p.setAge(0);
	this.validatable = new Validatable<Person>(p);
	this.validator = new PersonValidator();
    }

    @Test
    public void testNameNull() {
	Person p = this.validatable.getValue();
	p.setName(null);
	validator.validate(validatable);
	assertThat(validatable.isValid(), is(false));
    }

    @Test
    public void testNameEmpty() {
	Person p = this.validatable.getValue();
	p.setName("");
	validator.validate(validatable);
	assertThat(validatable.isValid(), is(true));
    }
    
    @Test
    public void testAgeMinusOne() {
	Person p = this.validatable.getValue();
	p.setAge(-1);
	validator.validate(this.validatable);
	assertThat(validatable.isValid(), is(false));
    }

    @Test
    public void testAgeNull() {
	Person p = this.validatable.getValue();
	p.setAge(null);
	validator.validate(this.validatable);
	assertThat(validatable.isValid(), is(false));
    }
    
    @Test
    public void testAgeZero() {
	Person p = this.validatable.getValue();
	p.setAge(0);
	validator.validate(this.validatable);
	assertThat(validatable.isValid(), is(true));
    }

}