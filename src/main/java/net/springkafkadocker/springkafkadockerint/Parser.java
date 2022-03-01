package net.springkafkadocker.springkafkadockerint;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.List;

@Component
public class Parser {
    public List<Stock> parse(File file) {
        List<Stock> stocks = null;

        try {
            // create a reader
            Reader reader = Files.newBufferedReader(file.toPath());

            // create csv bean reader
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Stock.class)
                    .withEscapeChar(' ')
                    .build();

            stocks = csvToBean.parse();

            // close the reader
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return stocks;
    }
}
