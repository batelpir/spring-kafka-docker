package net.springkafkadocker.springkafkadockerint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MyFileChangeListener implements FileChangeListener {
    @Autowired
    private FileContentHandler fileContentHandler;

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        fileContentHandler.process(changeSet);
    }
}