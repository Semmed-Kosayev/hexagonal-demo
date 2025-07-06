package az.semmed.product_service.adapter.in.web.dto;

import java.math.BigDecimal;

public record CreateProductRequest(String name, BigDecimal amount, String currency) {

}
