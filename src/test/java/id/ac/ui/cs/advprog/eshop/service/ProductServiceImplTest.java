package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);
        productService.create(product);

        when(productRepository.findAll()).thenReturn(List.of(product).iterator());
        List<Product> productList = productService.findAll();

        Product savedProduct = productList.getFirst();
        assertNotNull(savedProduct);
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        when(productRepository.create(product1)).thenReturn(product1);
        productService.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);

        when(productRepository.create(product2)).thenReturn(product2);
        productService.create(product2);

        when(productRepository.findAll()).thenReturn(List.of(product1, product2).iterator());
        List<Product> productList = productService.findAll();

        assertFalse(productList.isEmpty());
        Product savedProduct = productList.removeFirst();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productList.removeFirst();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertTrue(productList.isEmpty());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);
        productService.create(product);

        Product modifiedProduct = new Product();
        modifiedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        modifiedProduct.setProductName("Sampo Cap ABC");
        modifiedProduct.setProductQuantity(200);

        when(productRepository.editProduct(product.getProductId(), modifiedProduct)).thenReturn(modifiedProduct);
        productService.editProduct(product.getProductId(), modifiedProduct);

        when(productRepository.findProduct(modifiedProduct.getProductId())).thenReturn(modifiedProduct);
        Product editedProduct = productService.findProduct(modifiedProduct.getProductId());

        assertEquals(modifiedProduct.getProductId(), editedProduct.getProductId());
        assertEquals(modifiedProduct.getProductName(), editedProduct.getProductName());
        assertEquals(modifiedProduct.getProductQuantity(), editedProduct.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);
        productService.create(product);

        when(productRepository.deleteProduct(product.getProductId())).thenReturn(product);
        Product deletedProduct = productService.deleteProduct(product.getProductId());

        assertNotNull(deletedProduct);
        assertEquals(product.getProductId(), deletedProduct.getProductId());
        assertEquals(product.getProductName(), deletedProduct.getProductName());
        assertEquals(product.getProductQuantity(), deletedProduct.getProductQuantity());
    }
}
