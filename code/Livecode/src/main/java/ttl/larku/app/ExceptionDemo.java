package ttl.larku.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author whynot
 */
public class ExceptionDemo {

    public static void main(String[] args) {
        ExceptionDemo ed = new ExceptionDemo();
        ed.readFileWithTryWithResources("pom.xml");
    }


    public void readFileTheOldWay(String fileName) {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("pom.xml");
            int c = fis.read();

            System.out.println("c: " + c);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readFileWithTryWithResources(String fileName) {

        try(MyClass mc = new MyClass();
            FileInputStream fis = new FileInputStream(fileName);)
        {
            int c = fis.read();

            System.out.println("c: " + c);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MyClass implements AutoCloseable
{
    int i = 0;

    public MyClass() {
        //Do some setup
    }

    @Override
    public void close() {
        //Must be called by programmer. Do important shudown.
        System.out.println("MyClass::close called");
    }
}
