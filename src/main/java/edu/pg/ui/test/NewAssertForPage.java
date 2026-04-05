package edu.pg.ui.test;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class NewAssertForPage {

    // Asercja dla tekstu (używana w testach logowania i Google)
    public static void assertTextEquals(String actual, String expected, String message) {
        assertEquals(expected, actual, message);
    }

    // Asercja dla widoczności elementu (używana w isErrorMessageDisplayed)
    public static void assertVisible(WebElement element, String message) {
        assertTrue(element.isDisplayed(), message);
    }

    // Asercja dla wyników matematycznych (używana w testach zniżek)
    public static void assertMathResult(int actual, int expected, String message) {
        assertEquals(expected, actual, message);
    }
}
