package ttl.larku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.image.ImagingOpException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author whynot
 */
public class TestExceptions {

    public void someFun(String fileName) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);

    }

    @Test
    public void testExceptionThrowingMethod() {
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            someFun("blah");
        });
    }
}
