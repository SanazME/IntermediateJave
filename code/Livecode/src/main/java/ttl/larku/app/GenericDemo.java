package ttl.larku.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author whynot
 */
public class GenericDemo {
    public static void main(String[] args) {
        GenericDemo gd = new GenericDemo();
        //gd.whatIsAList();
        gd.genericDemo();
    }

    public void whatIsAList() {

        List<Integer> lint = new ArrayList<>();

        //Type Erasure
        //List lint = new ArrayList();

        lint.add(10);

//        lint.add("boo");

        List badList = lint;
        badList.add("boo");

//        for(Integer i : lint) {
//            System.out.println("i: " + i);
//        }

//        List badList = new ArrayList();
//        badList.add(10);
//        badList.add("abc");
//
//        Iterator it = badList.iterator();
//        while(it.hasNext()) {
//            Object o = it.next();
//            Integer i = (Integer)o;
//
//            System.out.println("i: " + i);
//        }

    }

    /*
           Class Hierarchy                                                         Collection

           Object                                                                  List<Object>         ==>  Anything that is an Object, i.e. everything.

           Number                                                                  List<Number>          ==>  Anything that is a number

           Integer, Double                                                         List<Integer>         ==>  Only Integer
     */

    public void genericDemo() {
        List<Number> lNumber = new ArrayList<>();
        lNumber.add(23.3456);
        lNumber.add(10);
        lNumber.add(20);

        double result = sum(lNumber);

        System.out.println("result: " + result);

        List<Integer> lInt = new ArrayList<>();
        lInt.add(10);
        lInt.add(20);

        sum(lInt);

//        List<String> lstr = new ArrayList<>();
//        sum(lstr);

        Integer [] iarr = {3, 5, 777};
        add(lInt, iarr);

        System.out.println("lInt: " + lInt);

        add(lNumber, iarr);
        System.out.println("lNumber: " + lNumber);

        List<Object> lObj = new ArrayList<>();
        add(lObj, iarr);
        System.out.println("lObj: " + lObj);
    }

    //PECS  =>  Producer Extends, Consumer Super

    public void add(List<? super Integer> input, Integer [] arr) {

//        Integer it = input.get(0);

        for(Integer i : arr) {
            input.add(i);
        }
    }

    public double sum(List<? extends Number> input) {
//        input.add(345.678);
        double sum = 0;

        for (Number number : input) {
            sum += number.doubleValue();
        }

        return sum;
    }

    static <T> void copy(List<? super T> dest, List<? extends T> src) {}

    public void foo() {
        List<Integer> li = List.of(0, 1, 0, 3);

        int result = frequency(li, 0);
    }

    static int frequency(Collection<?> coll, Object o)
    {
        int freq = 0;
        for(Object obj : coll) {
            if(obj.equals(o)) {
                freq++;
            }
        }

        return freq;
    }
}
