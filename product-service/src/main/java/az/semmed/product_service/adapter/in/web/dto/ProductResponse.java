package az.semmed.product_service.adapter.in.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID id, String name, BigDecimal amount, String currency) {

}
