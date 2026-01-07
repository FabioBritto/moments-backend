package br.com.britto.appmoments.service.foto;

import br.com.britto.appmoments.dto.foto.FotoDTO;
import br.com.britto.appmoments.dto.foto.FotoResponseDTO;
import br.com.britto.appmoments.exception.StatusFinanceiroException;
import br.com.britto.appmoments.exception.UuidNotFoundException;
import br.com.britto.appmoments.model.Evento;
import br.com.britto.appmoments.model.enums.StatusFinanceiro;
import br.com.britto.appmoments.repository.EventoRepository;
import br.com.britto.appmoments.service.storage.IFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FotoServiceImpl implements IFotoService {

    private final EventoRepository repository;

    private final IFileStorageService service;

    public FotoServiceImpl(EventoRepository repository, IFileStorageService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public FotoResponseDTO sendFoto(FotoDTO fotoDTO) {
        Evento evento = repository.findByUuid(fotoDTO.uuid()).orElseThrow(() -> new UuidNotFoundException("Evento não encontrado"));
        if(evento.getStatusFinanceiro() != StatusFinanceiro.APROVADO) {
            throw new StatusFinanceiroException("O pagamento ainda não foi aprovado");
        }
        if(evento.getDataHoraFim().isBefore(LocalDateTime.now())) {
            evento.setStatusFinanceiro(StatusFinanceiro.EXPIRADO);
            throw new StatusFinanceiroException("O prazo para o pagamento foi expirado");
        }
        service.saveFoto(fotoDTO);
        return new FotoResponseDTO("Foto salva com sucesso");
    }
}
