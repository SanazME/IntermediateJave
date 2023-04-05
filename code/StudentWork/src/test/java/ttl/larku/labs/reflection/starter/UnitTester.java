package ttl.larku.labs.reflection.starter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author whynot
 */
public class UnitTester {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        UnitTester tester = new UnitTester();

        Map<String, Boolean> results = tester.runTests(TestMyService.class);

        System.out.println("Results:");
        results.forEach((key, value) -> System.out.println(key + ":" + value));
    }

    public Map<String, Boolean> runTests(Class<?> testCaseClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Map<String, Boolean> results = new HashMap<>();

        Object testInstance = testCaseClass.getDeclaredConstructor().newInstance();

        Method [] methods = testCaseClass.getDeclaredMethods();

        for(Method method : methods) {
            if(method.isAnnotationPresent(TestMethod.class)) {
                if(method.getParameterCount() == 0) {
                    if(method.getReturnType() == boolean.class || method.getReturnType() == Boolean.class) {
                        Boolean b = (Boolean) method.invoke(testInstance);
                        results.put(method.getName(), b);
                    }
                }
            }
        }

        return results;
    }
}
