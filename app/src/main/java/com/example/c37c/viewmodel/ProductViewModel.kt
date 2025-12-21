package com.example.c37c.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.c37c.model.ProductModel
import com.example.c37c.repository.ProductRepo

class ProductViewModel(val repo: ProductRepo) : ViewModel() {

    fun addProduct(model: ProductModel, callback: (Boolean, String) -> Unit) {
        repo.addProduct(model,callback)
    }

    fun updateProduct(model: ProductModel, callback: (Boolean, String) -> Unit) {
        repo.updateProduct(model,callback)
    }

    fun deleteProduct(id: String, callback: (Boolean, String) -> Unit) {
        repo.deleteProduct(id) { success, message ->
            if (success) {
                _allProducts.value = _allProducts.value?.filterNot { it.productId == id }
            }
            callback(success, message)
        }
    }

    private val _products = MutableLiveData<ProductModel?>()
    val products : MutableLiveData<ProductModel?> get() = _products

    private val _allProducts = MutableLiveData<List<ProductModel>?>()
    val allProducts : MutableLiveData<List<ProductModel>?> get() = _allProducts

    private val _loading = MutableLiveData<Boolean>()
    val loading : MutableLiveData<Boolean> get() = _loading

    fun getAllProduct() {
        _loading.postValue(true)
        repo.getAllProduct {
            sucess,message,data->
            if(sucess){
                _loading.postValue(false)
                _allProducts.postValue(data)
            }
        }
    }

    fun getProductById(productId :String) {
        repo.getProductById(productId) {
                sucess,message,data->
            if(sucess){
                Log.d("checkpoint",data!!.productId)
                _products.postValue(data)
            }
        }
    }

//    fun getProductByCategory(
//        categoryId: String,
//        callback: (Boolean, String, List<ProductModel>?) -> Unit
//    ) {
//
//    }
}