import org.junit.jupiter.api.Test;
import sample.TextValidation;

import static org.junit.jupiter.api.Assertions.*;

class TextValidationTest {

    @Test
    public void validName() {
        assertTrue(TextValidation.validName("Jessica Rabbit"));
        assertTrue(TextValidation.validName("Mr Burns"));
        assertTrue(TextValidation.validName("Shut U. Mouth"));
        assertTrue(TextValidation.validName("Devil"));
        assertTrue(TextValidation.validName("Louis C. K."));

        assertFalse(TextValidation.validName("J Snow"));
        assertFalse(TextValidation.validName("Hallo123"));
        assertFalse(TextValidation.validName("krakk"));
        assertFalse(TextValidation.validName("RandyDandy"));
        assertFalse(TextValidation.validName("J."));
        assertFalse(TextValidation.validName("Harry potter"));
        assertFalse(TextValidation.validName("Long Ass Name Is Too Long Dude"));
        assertFalse(TextValidation.validName("@uk"));
        assertFalse(TextValidation.validName(""));
        assertFalse(TextValidation.validName(" "));
    }

    @Test
    public void validEmail() {
        assertTrue(TextValidation.validEmail("henrik.lieng@oslomet.no"));
        assertTrue(TextValidation.validEmail("eksempelorg@eksempel.com"));
        assertTrue(TextValidation.validEmail("unitedkingdom@domain.co.uk"));
        assertTrue(TextValidation.validEmail("alle_har_2@org22.org"));

        assertFalse(TextValidation.validEmail("henrik.lieng"));
        assertFalse(TextValidation.validEmail("@oslomet.no"));
        assertFalse(TextValidation.validEmail("henrik.lieng@invalid"));
        assertFalse(TextValidation.validEmail("test@"));
        assertFalse(TextValidation.validEmail(";bot@evil.com"));
        assertFalse(TextValidation.validEmail("harry@potter.hp."));
        assertFalse(TextValidation.validEmail(""));
        assertFalse(TextValidation.validEmail(" "));
    }

    @Test
    public void validPhone(){
        assertTrue(TextValidation.validPhone("999999999"));
        assertTrue(TextValidation.validPhone("99 99 99 99"));
        assertTrue(TextValidation.validPhone("999 999 999"));
        assertTrue(TextValidation.validPhone("12344321"));
        assertTrue(TextValidation.validPhone("+4712344321"));
        assertTrue(TextValidation.validPhone("+47 12344321"));
        assertTrue(TextValidation.validPhone("(+47)12344321"));
        assertTrue(TextValidation.validPhone("(+47) 12344321"));
        assertTrue(TextValidation.validPhone("(+47) 12 34 43 21"));
        assertTrue(TextValidation.validPhone("07911 987654"));
        assertTrue(TextValidation.validPhone("123-9078"));
        assertTrue(TextValidation.validPhone("+44 3244 987654"));
        assertTrue(TextValidation.validPhone("(503) 123-9078"));
        assertTrue(TextValidation.validPhone("+2-503-123-9078"));
        assertTrue(TextValidation.validPhone("2-503-123-9078"));
        assertTrue(TextValidation.validPhone("002-503-123-9078"));

        assertFalse(TextValidation.validPhone(""));
        assertFalse(TextValidation.validPhone(" "));
        assertFalse(TextValidation.validPhone("This is text you dummy"));
        assertFalse(TextValidation.validPhone("kkkkk"));
        assertFalse(TextValidation.validPhone("-666"));
        assertFalse(TextValidation.validPhone("987-norge"));
        assertFalse(TextValidation.validPhone("2-503-æøå-9078"));
        assertFalse(TextValidation.validPhone("2-503-def-9078"));
        assertFalse(TextValidation.validPhone("€{[]#@"));
        assertFalse(TextValidation.validPhone("987 987     987 98"));
    }
}