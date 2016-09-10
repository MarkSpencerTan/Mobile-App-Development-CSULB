package edu.csulb.android.temperature;

/**
 * Created by Mark on 9/9/2016.
 */
public class ConverterUtil {

    //converts to farenheit to celsius
    public static float fToCelsius(float fahrenheit){
        return (fahrenheit - 32) * 5 / 9;
    }

    //converts to celsius to fahrenheit
    public static float cToFahrenheit(float celsius){
        return (celsius * 9 / 5) + 32;
    }
}
