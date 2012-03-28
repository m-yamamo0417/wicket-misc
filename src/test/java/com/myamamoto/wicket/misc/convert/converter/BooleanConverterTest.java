package com.myamamoto.wicket.misc.convert.converter;

import org.junit.Test;
import java.util.Locale;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.apache.wicket.util.convert.ConversionException;

public class BooleanConverterTest {

    private BooleanConverter converter;
    private Locale locale;
    
    @Before
    public void setUp() {
	converter = new BooleanConverter("TRUE", "FALSE");
	locale = Locale.getDefault();
	
    }

    @Test
    public void testConvertToStringTrue() {
	assertThat(converter.convertToString(Boolean.TRUE, locale),
		   is("TRUE"));
    }
    
    @Test
    public void testConvertToStringFalse() {
	assertThat(converter.convertToString(Boolean.FALSE, locale),
		   is("FALSE"));
    }

    @Test
    public void testConvertToObjectTrue() {
	assertThat(converter.convertToObject("TRUE", locale),
		   is(Boolean.TRUE));
    }

    @Test
    public void testConvertToObjectFalse() {
	assertThat(converter.convertToObject("FALSE", locale),
		   is(Boolean.FALSE));
    }

    @Test(expected=ConversionException.class)
    public void testConvertToObjectIllegal() {
	converter.convertToObject("FARUE", locale);
    }

    @Test(expected=NullPointerException.class)
    public void testConsructorWithNull() {
	new BooleanConverter(null, null);
    }
		   
}
