package com.ecom.product_api.api;

import com.ecom.product_api.dto.request.RequestProductDto;
import com.ecom.product_api.service.ProductService;
import com.ecom.product_api.util.StandardResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product-service/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> createProduct(
            @RequestParam("data") String data,
            @RequestParam("image") MultipartFile image
    ) throws JsonProcessingException {
        RequestProductDto dto = objectMapper.readValue(data,RequestProductDto.class);
        productService.createProduct(dto,image);
        return new ResponseEntity<>(new StandardResponse(201,"Product Saved",null), HttpStatus.CREATED);
    }
    @PutMapping("/update/{productId}")
    public ResponseEntity<StandardResponse> updateProduct(@RequestBody RequestProductDto dto,@PathVariable String productId){
        productService.updateProduct(dto,productId);
        return new ResponseEntity<>(new StandardResponse(201,"Product Updated",null), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<StandardResponse> deleteProduct(@PathVariable String productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(new StandardResponse(204,"Product Deleted",null), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find-by-id/{productId}")
    public ResponseEntity<StandardResponse> findProductById(@PathVariable String productId){
        return new ResponseEntity<>(new StandardResponse(200,"Product found",productService.findProductById(productId)),HttpStatus.OK);
    }

    @GetMapping("/search-products")
    public ResponseEntity<StandardResponse> searchAllProducts(
            @RequestParam String searchText, @RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(new StandardResponse(200,"product list..",productService.searchAllProducts(searchText,page,size)),HttpStatus.OK);
    }

    @PutMapping("update-image/{imageId}")
    public ResponseEntity<StandardResponse> updateImage(
            @PathVariable String imageId,
            @RequestParam("image") MultipartFile file
    ){
        productService.updateImage(imageId,file);
        return new ResponseEntity<>(
                new StandardResponse(201, "image updated..",null ),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/delete-image-by-id/{imageId}")
    public ResponseEntity<StandardResponse> deleteImage(
            @PathVariable String imageId
    ){
        productService.deleteImage(imageId);
        return new ResponseEntity<>(
                new StandardResponse(201,  "image updated..",null),
                HttpStatus.CREATED
        );
    }



}
