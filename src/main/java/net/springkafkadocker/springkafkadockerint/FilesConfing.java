package net.springkafkadocker.springkafkadockerint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties("spring.files")
public class FilesConfing {

    private int numRecordsPerFile;
    private String consumerDirectory;
    private String produserDirectory;

    @PostConstruct
    private void init(){
        return;
    }

    public int getNumRecordsPerFile() {
        return this.numRecordsPerFile;
    }
    public void setNumRecordsPerFile(int numRecordsPerFile) {
        this.numRecordsPerFile = numRecordsPerFile;
    }

    public String getConsumerDirectory() {
        return this.consumerDirectory;
    }
    public void setConsumerDirectory(String consumerDirectory) {
        this.consumerDirectory = consumerDirectory;
    }

    public String getProduserDirectory() {
        return this.produserDirectory;
    }
    public void setProduserDirectory(String produserDirectory) {
        this.produserDirectory = produserDirectory;
    }
}