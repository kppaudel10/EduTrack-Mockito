package com.kul.edutrackmockito.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * author: Kul Paudel
 * createdDate: 2025-04-09
 **/
@ExtendWith(MockitoExtension.class)
public class DateFormatterConfigTest {

    @Test
    void testDateFormatterBean() {
        DateFormatterConfig config = new DateFormatterConfig();
        SimpleDateFormat sdf = config.simpleDateFormat();
        assertNotNull(sdf);
        assertEquals("yyyy-MM-dd", sdf.toPattern());
    }
}
