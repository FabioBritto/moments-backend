package br.com.britto.appmoments.service.storage;

import br.com.britto.appmoments.dto.foto.FotoDTO;
import br.com.britto.appmoments.exception.FileStorageException;
import br.com.britto.appmoments.exception.FotoConversionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.UUID;

@Component
public class FileSystemEventoStorage implements IFileStorageService {


    private final String albumFolder;

    public FileSystemEventoStorage(@Value("${appmoments.albumfolder}") String albumFolder) {
        this.albumFolder = albumFolder;
    }

    @Override
    public void createAlbum(String uuid) {
        String newFolder = albumFolder + File.separator + uuid;
        try {
            if(!(new File(newFolder).mkdirs())) {
                throw new FileStorageException("Não foi possível criar o diretório de armazenamento do evento");
            }
        } catch (Exception e) {
            throw new FileStorageException("Erro ao tentar salvar imagem");
        }

    }

    @Override
    public void saveFoto(FotoDTO fotoDTO) {

        /*
        PRECISO CONFIRMAR SE O BASE64 CHEGA COM CABEÇALHO.
        Na verdade, o ideal seria que a minha API barrasse independente do meu FRONT. Mas para isso
        vou precisar depois criar um método pra isso, além de verificar o tipo da imagem
         */

        try {
            byte[] fotoData = Base64.getDecoder().decode(fotoDTO.fotoBase64());
            String fileName = UUID.randomUUID().toString() + ".png";
            FileOutputStream fileOutputStream = new FileOutputStream(albumFolder + File.separator +
                                                                   fotoDTO.uuid() + File.separator + fileName);
            fileOutputStream.write(fotoData);
            fileOutputStream.close();
        } catch (Exception e) {
            throw new FotoConversionException("Erro ao tentar converter arquivo Base64 para imagem");
        }
    }
}
