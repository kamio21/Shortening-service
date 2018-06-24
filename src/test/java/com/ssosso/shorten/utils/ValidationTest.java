package com.ssosso.shorten.utils;

import com.ssosso.shorten.valid.UrlTypeValidation;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationTest {

    @Test
    public void URL_Validation_테스트() {
        UrlTypeValidation urlTypeValidation = new UrlTypeValidation();
        assertTrue(urlTypeValidation.valid("http://naver.com"));
        assertFalse(urlTypeValidation.valid("http://naver.com.."));
        assertFalse(urlTypeValidation.valid("http://naver.com!"));
    }
}
