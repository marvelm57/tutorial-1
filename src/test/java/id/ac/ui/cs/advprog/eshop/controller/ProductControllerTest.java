package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("createProduct", viewName);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = productController.createProductPost(product, model);
        assertEquals("redirect:list", viewName);

        verify(productService).create(product);
    }

    @Test
    void testProductListPage() {
        String viewName = productController.productListPage(model);
        assertEquals("listProduct", viewName);

        verify(productService).findAll();
    }

    @Test
    void testEditProductPage() {
        String viewName = productController.editProductPage("testId", model);
        assertEquals("editProduct", viewName);

        verify(productService).findProduct("testId");
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        product.setProductId("testId");
        String viewName = productController.editProductPost(product);
        assertEquals("redirect:/product/list", viewName);

        verify(productService).editProduct(product.getProductId(), product);
    }

    @Test
    void testDeleteProduct() {
        String viewName = productController.deleteProduct("testId");
        assertEquals("redirect:/product/list", viewName);

        verify(productService).deleteProduct("testId");
    }
}
