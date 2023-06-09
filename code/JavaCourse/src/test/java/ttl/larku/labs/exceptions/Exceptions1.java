package ttl.larku.labs.exceptions;


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Exception Lab - TODO comments below have instructions.
 *
 * @author whynot
 */
public class Exceptions1 {

    class ApplicationException extends Exception {
        public ApplicationException(String message) {
            super(message);
        }

        public ApplicationException(Exception cause) {
            super(cause);
        }
    }

    /**
     * TODO - Remove the @Disabled annotation on the test and uncomment the code
     *  in the test and make it compile and run such that the test completes
     *  successfully.  Do not change any of the existing code, or the
     *  signature of the test method
     *
     * @throws ApplicationException
     */
    @Test
    public void testTryWithResources() {
        assertThrows(ApplicationException.class, () -> {
            try {
                URL url = getClass().getClassLoader().getResource("wrds");
                Path path = Paths.get(url.toURI());
                BufferedReader reader = Files.newBufferedReader(path);
                int count = 0;
                while (reader.readLine() != null) {
                    count++;
                }
                assertEquals(99172, count);
            }catch(Exception e) {
                if(e instanceof ApplicationException) {
                    throw(e);
                }
                throw new ApplicationException(e);
            }
        });

    }
}
