package net.springkafkadocker.springkafkadockerint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class KafkaConsumer {
    @Autowired
    private final FilesConfing filesConfing = null;
    @Autowired
    private CsvStockWriter csvStockWriter;
    private Integer fileNumber = 1;
    private int recordNumber = 1;
    private File currentFile;


    @PostConstruct
    private void initializer(){
        this.currentFile = new File(filesConfing.getConsumerDirectory() + "/Stocks" + fileNumber.toString() + ".csv");
        this.currentFile.getParentFile().mkdirs();
    }

    @KafkaListener(topics = "stock_topic", groupId = "stock_group_id")
    public void getMessage(Stock stock) {
        System.out.println("######### KafkaConsumer - Stock Name: "+ stock.getStockName());
        // Files.lines(Paths.get(currentFilePath)).count()
        if(recordNumber > filesConfing.getNumRecordsPerFile()){
            this.fileNumber++;
            this.recordNumber = 1; // reset records number
            this.currentFile = new File(filesConfing.getConsumerDirectory() + "/Stocks" + fileNumber.toString() + ".csv");
        }
        // write to csv file
        this.recordNumber++;
        this.csvStockWriter.saveRecord(stock.getStockName(), stock.getDateTime(), stock.getStockValue(), this.currentFile);
    }
}
