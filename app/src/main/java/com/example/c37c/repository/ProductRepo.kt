package com.example.c37c.repository

import com.example.c37c.model.ProductModel

interface ProductRepo {

//    {
//    "success" : true
//    "message": "Product fetched succesfully"
//}
//
    fun addProduct(model: ProductModel,callback:(Boolean,String) -> Unit)

    fun updateProduct(model: ProductModel,callback: (Boolean, String) -> Unit)

    fun deleteProduct(productId:String,callback: (Boolean, String) -> Unit)

    fun getAllProduct(callback: (Boolean, String, List<ProductModel>?) -> Unit)

    fun getProductById(productId: String,callback: (Boolean, String, ProductModel?) -> Unit)

    fun getProductByCategory(categoryId:String, callback: (Boolean, String, List<ProductModel>?) -> Unit)
}