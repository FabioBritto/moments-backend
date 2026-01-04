package br.com.britto.appmoments.service;

import br.com.britto.appmoments.dto.EventoDTO;
import br.com.britto.appmoments.model.Cliente;
import br.com.britto.appmoments.model.Evento;
import br.com.britto.appmoments.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class EventoServiceImpl implements IEventoService {

    @Value("${appmoments.albumfolder}")
    private String albumFolder;

    @Autowired
    private EventoRepository repository;

    @Override
    public Evento create(Evento evento) {
        evento.setUuid(UUID.randomUUID().toString());
        evento.setDataHoraFim(evento.getDataHoraInicio().plusHours(4)); //Evento sempre com 4hrs de duração
        System.out.println("EVENTO " + evento);
        System.out.println("EVENTO UUIDO " + evento.getUuid());

        //Agora, preciso lidar com o caminho onde os arquivos serão armazenados
        String newFolder = albumFolder + File.separator + evento.getUuid();

        System.out.println("NEW FOLDER : " + newFolder);

        Evento created = repository.save(evento);

        System.out.println("NEW FOLDER: " + newFolder);


        if(created != null) {
            try {
                if(new File(newFolder).mkdirs()) {
                    return created;
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return null;
    }

    @Override
    public EventoDTO findByUuid(String uuid){
        Evento evento = repository.findByUuid(uuid);
        if(evento != null) {
            return new EventoDTO(evento.getUuid(), evento.getTitulo(), evento.getFrase(),
                    evento.getDataHoraInicio(), evento.getDataHoraFim(), evento.getLinkMoldura());
        }
        return null;
    }

    @Override
    public Evento update(Evento evento) {
        return repository.save(evento);
    }

    @Override
    public List<EventoDTO> findByCliente(Integer idCliente) {
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        return repository.findByCliente(cliente);
    }
}
