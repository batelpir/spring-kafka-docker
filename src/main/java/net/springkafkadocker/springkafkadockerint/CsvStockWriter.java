package net.springkafkadocker.springkafkadockerint;

import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class CsvStockWriter {
    public void saveRecord(String stockName, String dateTime, String stockValue, File file) {

        try{
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(stockName + ", " + dateTime + ", " + stockValue);
            pw.flush();
            pw.close();

        } catch (IOException e) {
            System.out.println("Record not saved");
            e.printStackTrace();
        }

    }
}
