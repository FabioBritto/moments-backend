package br.com.britto.appmoments.dto.pagamento;

import java.time.LocalDate;

public record PaymentLinkDTO(
        String id,
        String name,
        Double value,
        Boolean active,
        String chargeType,
        String url,
        String billingType,
        String subscriptionCycle,
        String description,
        LocalDate endDate,
        Boolean deleted,
        Integer viewCount,
        Integer maxInstallmentCount,
        Integer dueDateLimitDays,
        Boolean notificationEnabled
) {
}
