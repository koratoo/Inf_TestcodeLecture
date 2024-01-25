package sample.cafekiosk.spring.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //query method
    /**
     * select *
     * from product
     * where selling_status in ('SELLING', 'HOLD');
     */
    List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatuses);

    //왜 테스트 코드를 작성해야 하나요?
    //1. 내가 작성한 코드가 제대로 된 쿼리가 날아갈 것인가?
    //2. 미래에 어떤 형태로 변형될 것인가?


    List<Product> findAllByProductNumberIn(List<String> productNumbers);

    //가장 마지막에 등록된 상품의 번호
    @Query(value = "select p.product_number from product p order by id desc limit 1", nativeQuery = true)
    Product findLatestProduct();
}
