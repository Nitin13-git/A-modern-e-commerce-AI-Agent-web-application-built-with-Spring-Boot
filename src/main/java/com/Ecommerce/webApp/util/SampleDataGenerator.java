package com.Ecommerce.webApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.Ecommerce.webApp.model.Product;
import com.Ecommerce.webApp.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class SampleDataGenerator {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void generateSampleData() {
        List<Product> sampleProducts = Arrays.asList(
            new Product("Laptop", "Electronics", "High-performance laptop", 999.99, "Silver", 10),
            new Product("Smartphone", "Electronics", "Latest smartphone model", 699.99, "Black", 15),
            new Product("Headphones", "Electronics", "Noise-cancelling headphones", 199.99, "White", 20),
            new Product("T-shirt", "Clothing", "Cotton t-shirt", 29.99, "Blue", 50),
            new Product("Jeans", "Clothing", "Slim fit jeans", 59.99, "Black", 30)
        );

        productRepository.saveAll(sampleProducts);
    }
}
