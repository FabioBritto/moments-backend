package br.com.britto.appmoments.service;

import br.com.britto.appmoments.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileSystemEventoStorage implements IFileStorageService {


    private final String albumFolder;

    public FileSystemEventoStorage(@Value("${appmoments.albumfolder}") String albumFolder) {
        this.albumFolder = albumFolder;
    }

    @Override
    public void createAlbum(String uuid) {
        String newFolder = albumFolder + File.separator + uuid;
        if(new File(newFolder).mkdirs()) {
            throw new FileStorageException("Não foi possível criar o diretório de armazenamento do evento");
        }
    }
}
