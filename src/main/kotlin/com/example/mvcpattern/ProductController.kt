package com.example.mvcpattern

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ProductController(private val productService: ProductService) {

    @GetMapping("/products")
    fun getAllProducts(): Response {
        return productService.getAllProducts()
    }

    @GetMapping("/products/{id}")
    fun getProductById(@PathVariable id: Long): Response {
        return productService.getProductById(id)
    }

    @PostMapping("/products")
    fun addProduct(@RequestBody product: ProductModel): Response {
        return productService.addProduct(product)
    }

    @PutMapping("/products/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody product: ProductModel): Response {
        return productService.updateProduct(id, product)
    }

    @DeleteMapping("/products/{id}")
    fun deleteProduct(@PathVariable id: Long): Response {
        return productService.deleteProduct(id)
    }
}
