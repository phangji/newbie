package com.ssg.newbie.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.newbie.controller.ProductController;
import com.ssg.newbie.domain.Product;
import com.ssg.newbie.domain.ProductRequest;
import com.ssg.newbie.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("상품 리스트 조회")
    @Test
    public void test_getProducts() throws Exception {
        given(productService.getProducts()).willReturn(List.of(
                dummyProduct()
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/internal/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("사과"))
                .andExpect(jsonPath("$[0].price").value(1000))
                .andExpect(jsonPath("$[0].deliveryPrice").value(2500))
                .andExpect(jsonPath("$[0].stockCount").value(100));

    }

    @DisplayName("상품 단일 조회")
    @Test
    public void test_getProduct() throws Exception {
        given(productService.getProduct(1L)).willReturn(
                dummyProduct()
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/api/internal/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("사과"))
                .andExpect(jsonPath("$.price").value(1000))
                .andExpect(jsonPath("$.deliveryPrice").value(2500))
                .andExpect(jsonPath("$.stockCount").value(100));
    }

    @DisplayName("상품 생성")
    @Test
    public void test_createProduct() throws Exception {
        ProductRequest request = dummyRequest();
        given(productService.createProduct(request)).willReturn(
                1L
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/internal/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));
    }

    @DisplayName("상품 수정")
    @Test
    public void test_updateProduct() throws Exception {
        ProductRequest request = dummyRequest();
        given(productService.updateProduct(1L, request)).willReturn(
                dummyProduct()
        );

        mockMvc.perform(MockMvcRequestBuilders.put("/api/internal/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("사과"))
                .andExpect(jsonPath("$.price").value(1000))
                .andExpect(jsonPath("$.deliveryPrice").value(2500))
                .andExpect(jsonPath("$.stockCount").value(100));
    }

    @DisplayName("상품 삭제")
    @Test
    public void test_deleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/internal/products/1")
                )
                .andExpect(status().isOk());
    }

    private ProductRequest dummyRequest() {
        ProductRequest request = new ProductRequest();
        request.setName("사과");
        request.setPrice(1000);
        request.setDeliveryPrice(2500);
        request.setStockCount(100);
        return request;
    }
    private Product dummyProduct() {
        return Product.builder()
                .name("사과")
                .price(1000)
                .deliveryPrice(2500)
                .stockCount(100)
                .build();
    }

}
