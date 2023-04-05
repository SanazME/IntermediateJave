package ttl.larku.labs.generics;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * TODO - Generics Lab 3.  Instructions are in TODO comments below.
 *
 * @author whynot
 */
public class GenericsLab3 {

    //TODO - Change this class so that the 'testLocalDateValueHolder'
    // method below compiles and runs successfully.
    class ValueHolder<T> {
        private T value;

        public ValueHolder(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }

    class Pair<T, U> {
        public T first;
        public U second;
        public Pair(T t, U u) {
            this.first = t;
            this.second = u;
        }
    }


    @Test
    public void testStringValueHolder() {
        ValueHolder<String> vh = new ValueHolder<>("Boo");

        Pair<String, Integer> psi = new Pair<>("Boo", 5);


        String s = psi.first;
        int y = psi.second;

        String value = vh.getValue();
        assertEquals("Boo", value);
    }

    //TODO - Uncomment the test below and make changes
    // to the code so it compiles and runs successfully.
    // You will first have to make changes to the ValueHolder
    // class above.
    @Test
    public void testLocalDateValueHolder() {
        LocalDate ld = LocalDate.now();
        ValueHolder<LocalDate> vh = new ValueHolder<LocalDate>(ld);

        LocalDate ld2 = vh.getValue();

        assertEquals(2020, ld2.getYear());
    }
}
