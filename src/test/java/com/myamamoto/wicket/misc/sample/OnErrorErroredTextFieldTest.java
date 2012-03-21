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

package com.myamamoto.wicket.misc.sample;

import com.myamamoto.wicket.misc.behavior.AppendErrorClassOnErrorBehavior;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class OnErrorErroredTextFieldTest {

    @Test
    public void testExistsAppendErrorClassOnErrorBehavior() {
	new WicketTester();
	OnErrorErroredTextField field
	    = new OnErrorErroredTextField("id", Model.of(0));
	assertThat(field.getBehaviors(AppendErrorClassOnErrorBehavior.class).size(),
		   is(1));
    }
}