package com.example.mvcpattern
import org.springframework.orm.ObjectOptimisticLockingFailureException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductService(private val productRepository: ProductRepository) {

    fun getAllProducts(): Response {
        return try {
            val products = productRepository.findAll()
            Response(true, "Products retrieved successfully", products)
        } catch (e: Exception) {
            Response(false, "Something Went Wrong. Please try again.")
        }
    }

    fun getProductById(id: Long): Response {
        return try {
            val product = productRepository.findById(id)
            if (product.isEmpty) {
                Response(false, "Product with ID $id not found")
            } else {
                Response(true, "Product with ID $id retrieved successfully", product.get())
            }
        } catch (e: Exception) {
            Response(false, "Something Went Wrong. Please try again.")
        }
    }

    fun addProduct(product: ProductModel): Response {
        return try {
            productRepository.save(product)
            Response(true, "Product added successfully", product)
        } catch (e: ObjectOptimisticLockingFailureException) {
            Response(false, "Product with ID ${product.id} already exists")
        } catch (e: Exception) {
            Response(false, "Something Went Wrong. Please try again.")
        }
    }

    fun updateProduct(id: Long, product: ProductModel): Response {
        return try {
            if (!productRepository.existsById(id)) {
                return Response(false, "Product with ID $id not found")
            }
            productRepository.save(product.copy(id = id))
            Response(true, "Product with ID $id updated successfully")
        } catch (e: Exception) {
            Response(false, "Something Went Wrong. Please try again.")
        }
    }

    fun deleteProduct(id: Long): Response {
        return try {
            if (!productRepository.existsById(id)) {
                return Response(false, "Product with ID $id not found")
            }
            productRepository.deleteById(id)
            Response(true, "Product with ID $id deleted successfully")
        } catch (e: Exception) {
            Response(false, "Something Went Wrong. Please try again.")
        }
    }
}
