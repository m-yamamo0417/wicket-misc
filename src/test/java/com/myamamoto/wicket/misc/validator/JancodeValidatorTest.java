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

import org.apache.wicket.validation.Validatable;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class JancodeValidatorTest {

    private JancodeValidator validator;

    @Before
    public void setUp() {
	this.validator = new JancodeValidator();
    }

    @Test
    public void testTooShortError() {
	Validatable<String> validatable = new Validatable<String>("");
	this.validator.validate(validatable);
	assertThat(validatable.isValid(), is(false));
    }

    @Test
    public void testContainsNotDigit() {
	String jancode = "abcdefghijklm";
	Validatable<String> validatable = new Validatable<String>(jancode);
	this.validator.validate(validatable);
	assertThat(validatable.isValid(), is(false));
    }

    @Test
    public void testPotatochips() {
	// 「ポテトチップス　コンソメ味」　５５ｇ
	String jancode = "4522646430318";
	Validatable<String> validatable = new Validatable<String>(jancode);
	this.validator.validate(validatable);
	assertThat(validatable.isValid(), is(true));
    }

    @Test
    public void testInvalidPotatochips() {
	String jancode = "4522646430310";
	Validatable<String> validatable = new Validatable<String>(jancode);
	this.validator.validate(validatable);
	assertThat(validatable.isValid(), is(false));
    }
}

