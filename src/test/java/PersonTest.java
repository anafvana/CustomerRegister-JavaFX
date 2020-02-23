import sample.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    @Test
    public void testValidName() {
        new Person("Brian Cragback", "11", "02", "2002", "399 00 927","Thelegend27@gmail.com");
    }

    @Test
    public void testInvalidName(){
        assertThrows(IllegalArgumentException.class, () -> new Person("Brian", "12", "03", "2005", "(47)","brain03.gmail.com"));
        assertThrows(IllegalArgumentException.class, () -> new Person("oyvind987", "59", "07", "2004", "","oyvind987@gmail.com"));
        assertThrows(IllegalArgumentException.class, () -> new Person("Brian", "19", "2", "2008","(47) 333 33 333", "brain2@gmail"));
    }
}