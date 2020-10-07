package org.example;

import lombok.extern.slf4j.Slf4j;
import org.beanio.BeanReader;
import org.beanio.StreamFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderHelper<T> {

    private StreamFactory factory;

    public FileReaderHelper(String configFile) throws IOException {
        factory = StreamFactory.newInstance();
        factory.load(this.getClass().getClassLoader().getResourceAsStream(configFile));
    }

    public Stream<T> readEntriesFromFile(String stream, InputStream inputStream){
        List<T> list = new ArrayList<>();
        BeanReader in = null;
        try(InputStreamReader inputStreamReader = new InputStreamReader(inputStream)){
            in = factory.createReader(stream, inputStreamReader);
            T record;
            while ((record = (T) in.read()) != null) {
                list.add(record);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(in != null){
                in.close();
            }
        }
        return list.stream();
    }

    public List<T> readEntriesFromFileAsList(String stream, InputStream inputStream){
        return readEntriesFromFile(stream, inputStream).collect(Collectors.toList());
    }

}
