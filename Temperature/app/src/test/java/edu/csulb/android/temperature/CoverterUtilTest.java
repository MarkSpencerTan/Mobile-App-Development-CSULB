package edu.csulb.android.temperature;

import junit.framework.TestCase;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Mark on 10/27/2016.
 */

public class CoverterUtilTest extends TestCase{
    @Test
    public void testConvertFahrenheitToCelsius(){
        float actual = ConverterUtil.fToCelsius(100);
        //expected is 212
        float expected = 212;
        assertEquals("Conversion failed", expected, actual, .001);
    }

    @Test
    public void testConvertCelsiusToFahrenheit(){
        float actual = ConverterUtil.cToFahrenheit(212);
        float expected = 100;
        assertEquals("Conversion from C to F failed", expected, actual, .001);
    }
}
