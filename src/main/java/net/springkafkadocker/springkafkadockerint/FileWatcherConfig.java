package net.springkafkadocker.springkafkadockerint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.io.File;
import java.time.Duration;

@Configuration
public class FileWatcherConfig {
    @Autowired
    private FilesConfing filesConfig;

    @Autowired
    MyFileChangeListener myFileChangeListener;

    @Bean
    public FileSystemWatcher fileSystemWatcher() {
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(true, Duration.ofMillis(5000L), Duration.ofMillis(300L));
        fileSystemWatcher.addSourceDirectory(new File(filesConfig.getProduserDirectory()));
        fileSystemWatcher.addListener(myFileChangeListener);
        fileSystemWatcher.start();
        System.out.println("started fileSystemWatcher");
        return fileSystemWatcher;
    }

    @PreDestroy
    public void onDestroy() throws Exception {
        fileSystemWatcher().stop();
    }
}