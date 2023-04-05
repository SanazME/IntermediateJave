package ttl.larku.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<String> result = readFirstLinesWithStreamsAndOptional(fileNames);
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
                .collect(Collectors.toList());

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
                .collect(Collectors.toList());

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
                .collect(Collectors.toList());

        return result;
    }

    public void callReadFirstLineWithWrapper(List<String> fileNames) {
        List<Wrapper<String>> result = readFirstLinesWithWrapper(fileNames);

        result.forEach(w -> {
            if(w.value != null) {
                String line = w.value;
            }else {
                Exception e = w.exception;

            }
        });


    }

    public List<Wrapper<String>> readFirstLinesWithWrapper(List<String> fileNames) {
        var result = fileNames.stream()
                .map(fileName -> {
                    try(BufferedReader br = new BufferedReader(new FileReader(fileName));) {
                        String line = br.readLine();
                        return new Wrapper<String>(line, null);
                    }catch(IOException e) {
//                        throw new RuntimeException(e);
                        System.err.println("Exception in readFirstLine" + e);
                        return new Wrapper<String>(null, e);
                    }
                })
                .collect(Collectors.toList());

        return result;
    }


}

class Wrapper<T> {
    public T value;
    public Exception exception;

    public Wrapper(T value, Exception e) {
        this.value = value;
        this.exception = e;
    }
}
