package com.incubyte.backend.discount.repository;

import com.incubyte.backend.discount.entity.Discount;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
public class DiscountRepositoryTest {

    @Autowired
    DiscountRepository discountRepository;

    @Test
    public void test(){
        List<Discount> value = discountRepository.findAll();
        assertNotNull(value);
    }
}
