package br.com.britto.appmoments.dto.pagamento;

import java.time.LocalDate;

public record AsaasRequestDTO(
    String name,
    String billingType,
    String chargeType,
    String description,
    LocalDate endDate,
    Integer dueDateLimitDays,
    Double value
) {
}
