package ttl.larku.labs.reflection.starter;

/**
 * @author whynot
 */
public class TestMyService {

    @TestMethod
    public boolean testEven() {
        MyService ms = new MyService();
        int result = ms.foo(2);

        result = 10;
        return result == -5;
    }

    public boolean noAnnotation() {
        MyService ms = new MyService();
        int result = ms.foo(2);
        result = 10;

        return result == -5;
    }

    @TestMethod
    public void noReturn() {
        MyService ms = new MyService();
        int result = ms.foo(2);

    }

    @TestMethod
    public boolean testOdd() {
        MyService ms = new MyService();
        int result = ms.foo(1);

        return result == 5;
    }
}
