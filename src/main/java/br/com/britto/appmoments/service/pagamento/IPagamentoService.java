package br.com.britto.appmoments.service.pagamento;

import br.com.britto.appmoments.dto.evento.CreateEventoDTO;
import br.com.britto.appmoments.dto.pagamento.PaymentLinkDTO;
import br.com.britto.appmoments.model.Evento;

public interface IPagamentoService {

    public PaymentLinkDTO createPaymenteLink(String uuidEvento);
}
