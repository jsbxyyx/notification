package com.github.jsbxyyx.notification;

import org.junit.jupiter.api.Test;

/**
 * @author 1.0
 * @since 1.0
 */
public class EscapeTest {

    @Test
    void test_() {
        String escapeJson = Escape.escapeJson("My \"Message\"");
        System.out.println(escapeJson);
    }
    
}
