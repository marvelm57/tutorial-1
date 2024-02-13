package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Product modifiedProduct = new Product();
        modifiedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        modifiedProduct.setProductName("Sampo Cap ABC");
        modifiedProduct.setProductQuantity(200);
        productRepository.editProduct("eb558e9f-1c39-460e-8860-71af6af63bd6", modifiedProduct);

        Product editedProduct = productRepository.findProduct("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(modifiedProduct.getProductId(), editedProduct.getProductId());
        assertEquals(modifiedProduct.getProductName(), editedProduct.getProductName());
        assertEquals(modifiedProduct.getProductQuantity(), editedProduct.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Product deletedProduct = productRepository.deleteProduct("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(deletedProduct);
        assertEquals(product.getProductId(), deletedProduct.getProductId());
        assertEquals(product.getProductName(), deletedProduct.getProductName());
        assertEquals(product.getProductQuantity(), deletedProduct.getProductQuantity());

        assertNull(productRepository.findProduct("eb558e9f-1c39-460e-8860-71af6af63bd6"));
    }

    @Test
    void testEditProductIfIdDoesNotExist() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Product modifiedProduct = new Product();
        modifiedProduct.setProductId("non_existent_id");
        modifiedProduct.setProductName("Sampo Cap ABC");
        modifiedProduct.setProductQuantity(200);
        productRepository.editProduct("non_existent_id", modifiedProduct);

        Product editedProduct = productRepository.findProduct("non_existent_id");
        assertNotNull(productRepository.findProduct("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        assertNull(editedProduct);
    }

    @Test
    void testDeleteProductIfIdDoesNotExist() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertNotNull(productRepository.findProduct("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        assertNull(productRepository.deleteProduct("non_existent_id"));
    }
}