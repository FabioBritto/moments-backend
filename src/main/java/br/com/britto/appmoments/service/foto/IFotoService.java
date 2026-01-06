package br.com.britto.appmoments.service.foto;

import br.com.britto.appmoments.dto.foto.FotoDTO;
import br.com.britto.appmoments.dto.foto.FotoResponseDTO;

public interface IFotoService {

    public FotoResponseDTO sendFoto(FotoDTO fotoDTO);
}
