package net.springkafkadocker.springkafkadockerint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Set;

@Component
public class FileContentHandler {
    @Autowired
    private Parser parser;

    @Autowired
    private KafkaProducer producer;


    public void process(Set<ChangedFiles> changeSet) {
        try  {
            for(ChangedFiles cfiles : changeSet) {
                for(ChangedFile cfile: cfiles.getFiles()) {
                    processFile(cfile);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void processFile(ChangedFile cfile) {
        try  {
            File file = cfile.getFile();

            if(cfile.getType().equals(ChangedFile.Type.ADD) && !isLocked(file.toPath())) {
                System.out.println("Operation: " + cfile.getType()
                        + " On file: "+ file.getName() + " is done");
                System.out.println("File path: " + file);
                List<Stock> stocks = parser.parse(file);
                this.producer.sendMessage(stocks); // message = list of objects
            }
        } catch (Exception e) {
            throw(e);
        }
    }

    private boolean isLocked(Path path) {
        try (FileChannel ch = FileChannel.open(path, StandardOpenOption.WRITE); FileLock lock = ch.tryLock()) {
            return lock == null;
        } catch (IOException e) {
            return true;
        }
    }
}
