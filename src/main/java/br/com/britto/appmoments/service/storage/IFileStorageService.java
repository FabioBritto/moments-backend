package br.com.britto.appmoments.service.storage;

import br.com.britto.appmoments.dto.foto.FotoDTO;

public interface IFileStorageService {
    public void createAlbum(String uuid);
    public void saveFoto(FotoDTO fotoDTO);
}
