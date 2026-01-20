package br.com.britto.appmoments.service.pagamento;

import br.com.britto.appmoments.dto.pagamento.AsaasRequestDTO;
import br.com.britto.appmoments.dto.pagamento.PaymentLinkDTO;
import br.com.britto.appmoments.model.Evento;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AsaasService implements IPagamentoService{

    private static final String PREFIX_API_KEY = "$aact_hmlg_";
    private final String asaasUrl;
    private String apiKey;

    public AsaasService(@Value("${payment.gateway.url}") String asaasUrl, @Value("${payment.gateway.api-key}") String apiKey) {
        this.asaasUrl = asaasUrl;
        this.apiKey = PREFIX_API_KEY + apiKey;
    }

    @Override
    public PaymentLinkDTO createPaymenteLink(Evento evento) {
        System.out.println("EVENTO: " + evento);
        AsaasRequestDTO requestDTO = new AsaasRequestDTO(
                evento.getTitulo(),"UNDEFINED", "DETACHED",evento.getFrase(),
                evento.getDataHoraFim().toLocalDate(),2, 20.90);

        System.out.println("REQUEST DTO: " + requestDTO);
        RestClient restClient = RestClient.create();

        System.out.println("ASAAS URL: " + asaasUrl);
        System.out.println("API KEY: " + apiKey);
        PaymentLinkDTO paymentLinkDTO = restClient.post().uri(asaasUrl + "/paymentLinks").header("access_token", apiKey)
                .contentType(MediaType.APPLICATION_JSON).body(requestDTO).retrieve().body(PaymentLinkDTO.class);

        System.out.println("PAYMENT LINK: " + paymentLinkDTO);
        return paymentLinkDTO;
    }
}
