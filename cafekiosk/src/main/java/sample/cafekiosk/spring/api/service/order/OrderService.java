package sample.cafekiosk.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.product.ProductRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ProductRepository productRepository;
    public OrderResponse createOrder(OrderCreateRequest request) {

        List<String> productNumbers = request.getProductNumbers();
        //Product
        productRepository.findAllByProductNumberIn(productNumbers);

        //Order
        return null;
    }
}