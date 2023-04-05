package ttl.larku.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author whynot
 */
public class ExceptionLambdas {

    public static void main(String[] args) {
        ExceptionLambdas el = new ExceptionLambdas();
        el.process();
    }

    public void process() {
        List<String> fileNames = List.of("pom.xml", "doesnotexist", "testFile");
//        try {
//            List<String> result = readFirstLinesWithoutStreams(fileNames);
//        List<String> result = readFirstLinesWithStreamsTheBadWay(fileNames);
        //List<String> result = readFirstLinesWithStreamsADifferentWay(fileNames);
        //List<String> result = readFirstLinesWithStreamsAndOptional(fileNames);
        List<String> result = callReadFirstLineWithWrapper(fileNames);
        for(String line : result) {
                System.out.println("line: " + line);
            }
//        } catch (IOException e) {
//            System.out.println("Exception in process: " + e);
//            e.printStackTrace();
//        }

    }

    public List<String>  readFirstLinesWithoutStreams(List<String> fileNames) {
        List<String> result = new ArrayList<>();

        for(String fileName : fileNames) {
            try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line = br.readLine();

                result.add(line);
            }catch(IOException e) {
               //Deal with error
               System.err.println("Exception in readFirstLine" + e);
            }
        }

        return result;
    }

    public List<String>  readFirstLinesWithStreamsTheBadWay(List<String> fileNames) {
        List<String> result = fileNames.stream()
                .map(fileName -> {
                    try(BufferedReader br = new BufferedReader(new FileReader(fileName));) {
                        String line = br.readLine();
                        return line;
                    }catch(IOException e) {
                        throw new RuntimeException(e);
//                        System.err.println("Exception in readFirstLine" + e);
                    }
                })
                .collect(toList());

        return result;
    }

    public List<String> readFirstLinesWithStreamsADifferentWay(List<String> fileNames) {
        List<String> result = fileNames.stream()
                .flatMap(fileName -> {
                    try(BufferedReader br = new BufferedReader(new FileReader(fileName));) {
                        String line = br.readLine();
                        return Stream.of(line);
                    }catch(IOException e) {
//                        throw new RuntimeException(e);
                        System.err.println("Exception in readFirstLine" + e);
                        return Stream.empty();
                    }
                })
                .collect(toList());

        return result;
    }

    public List<String> readFirstLinesWithStreamsAndOptional(List<String> fileNames) {
        var result = fileNames.stream()
                .map(fileName -> {
                    try(BufferedReader br = new BufferedReader(new FileReader(fileName));) {
                        String line = br.readLine();
                        return Optional.of(line);
                    }catch(IOException e) {
//                        throw new RuntimeException(e);
                        System.err.println("Exception in readFirstLine" + e);
                        return Optional.<String>empty();
                    }
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());

        return result;
    }

    public List<String> callReadFirstLineWithWrapper(List<String> fileNames) {
        List<Wrapper<String>> wResult = readFirstLinesWithWrapper(fileNames);

        var result = wResult.stream()
                .flatMap(w -> {
                        if(w.value != null) {
                            String line = w.value;
                            return Stream.of(line);
                        }else {
                            Exception e = w.exception;
                            //Deal with Exception
                            return Stream.empty();
                        }
                    })
                .collect(toList());

        return result;
    }

    public List<Wrapper<String>> readFirstLinesWithWrapper(List<String> fileNames) {
        var result = fileNames.stream()
                .map(fileName -> {
                    try(BufferedReader br = new BufferedReader(new FileReader(fileName));) {
                        String line = br.readLine();
                        //return new Wrapper<String>(line, null);
                        return Wrapper.ofValue(line);
                    }catch(IOException e) {
//                        throw new RuntimeException(e);
//                        System.err.println("Exception in readFirstLine" + e);
                        //return new Wrapper<String>(null, e);
                        return Wrapper.<String>ofError(e);
                    }
                })
                .collect(toList());

        return result;
    }


}

class Wrapper<T> {
    public T value;
    public Exception exception;

    private Wrapper(T value, Exception e) {
        this.value = value;
        this.exception = e;
    }

    public static <X> Wrapper<X> ofValue(X value) {
        return new Wrapper<X>(value, null);
    }

    public static <X> Wrapper<X> ofError(Exception e) {
        return new Wrapper<X>(null, e);
    }
}
