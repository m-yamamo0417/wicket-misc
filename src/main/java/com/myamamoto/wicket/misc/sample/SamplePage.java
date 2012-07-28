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
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import com.myamamoto.wicket.misc.enums.RockPaperScissorsDropDownChoice;
import com.myamamoto.wicket.misc.enums.RockPaperScissors;

public class SamplePage extends WebPage {

    public SamplePage() {
    }

    @Override
    protected void onInitialize() {
	super.onInitialize();
	Form<Void> form = new Form<Void>("form1");
	super.add(form);
	form.add(new OnErrorErroredTextField("component1", Model.of(0)));
	form.add(new JancodeTextField("component2", Model.of("4522646430318")));
	form.add(new RockPaperScissorsDropDownChoice("component3",
						     Model.of(RockPaperScissors.ROCK)));
    }

    @Override
    public void renderHead(IHeaderResponse response){
	response.renderJavaScriptReference("https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js");
	response.renderJavaScriptReference("/js/prettify.js");
	response.renderJavaScriptReference("/js/kickstart.js");
	response.renderCSSReference("/css/kickstart.css");
    }
}