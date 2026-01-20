package br.com.britto.appmoments.service.pagamento;

import br.com.britto.appmoments.dto.pagamento.AsaasRequestDTO;
import br.com.britto.appmoments.dto.pagamento.PaymentLinkDTO;
import br.com.britto.appmoments.exception.EventoNotFoundException;
import br.com.britto.appmoments.exception.PagamentoException;
import br.com.britto.appmoments.model.Evento;
import br.com.britto.appmoments.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class AsaasService implements IPagamentoService{

    private static final String PREFIX_API_KEY = "$aact_hmlg_";
    private final String asaasUrl;
    private final String apiKey;
    private final EventoRepository eventoRepository;

    public AsaasService(@Value("${payment.gateway.url}") String asaasUrl, @Value("${payment.gateway.api-key}") String apiKey, EventoRepository eventoRepository) {
        this.asaasUrl = asaasUrl;
        this.apiKey = PREFIX_API_KEY + apiKey;
        this.eventoRepository = eventoRepository;
    }

    @Override
    public PaymentLinkDTO createPaymenteLink(String uuidEvento) {
        Evento evento = eventoRepository.findByUuid(uuidEvento).orElseThrow(() -> new EventoNotFoundException("Evento com o UUID: " + uuidEvento + " não encontrado na base de dados"));
        AsaasRequestDTO requestDTO = new AsaasRequestDTO(
                evento.getTitulo(),"UNDEFINED", "DETACHED",evento.getFrase(),
                evento.getDataHoraFim().toLocalDate(),2, 20.90);

        RestClient restClient = RestClient.create();

        try {
            return restClient.post().uri(asaasUrl + "/paymentLinks").header("access_token", apiKey)
                    .contentType(MediaType.APPLICATION_JSON).body(requestDTO).retrieve().body(PaymentLinkDTO.class);
        } catch (HttpClientErrorException e) {
            throw new PagamentoException("Erro ao gerar link de pagamento");
        }
    }
}
