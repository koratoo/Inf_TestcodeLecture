package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    // 동시성 이슈
    // UUID
    public ProductResponse createProduct(ProductCreateRequest request) {
        // productNumber
        // 001 002 003 004
        // DB 에서 마지막 저장된 Product의 상품 번호를 읽어와서 +1
        // 009 -> 010

        return ProductResponse.builder()
                .productNumber(createNextProductNumber())
                .type(request.getType())
                .price(request.getPrice())
                .name(request.getName())
                .sellingStatus(request.getSellingStatus())
                .build();
    }

    //판매중, 판매보류 상품 조회
    public List<ProductResponse> getSellingProducts() {

       List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());

    }

    public String createNextProductNumber(){
        String latestProductNumber = productRepository.findLatestProductNumber();
        if (latestProductNumber == null) {
            return "001";
        }

        int latestProductNumberInt = Integer.parseInt(latestProductNumber);
        return String.format("%03d", latestProductNumberInt+1);
    }


}
