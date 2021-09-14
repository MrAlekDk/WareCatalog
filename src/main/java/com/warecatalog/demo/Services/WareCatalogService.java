package com.warecatalog.demo.Services;

import com.warecatalog.demo.Models.Product;
import com.warecatalog.demo.Repositories.ProductsRepository;
import java.util.ArrayList;
import java.util.HashMap;

public class WareCatalogService {
    private HashMap<Integer,Product> listOfProductsMap;
    private ArrayList<Product> allProducts;
    private ProductsRepository pr;

    public WareCatalogService(){
        pr = new ProductsRepository();
        listOfProductsMap = new HashMap();
    }

    public ArrayList<Product> getAllProducts(){
        if(allProducts==null){
            updateListOfProducts();
            return allProducts;
        }
        return allProducts;
    }

    public void createProduct(String name, int price) {
        Product newProduct = new Product(name, price);
        pr.addNewProduct(newProduct);
        updateListOfProducts();
    }

    private void updateListOfProducts() {
        allProducts = pr.getAllProducts();
        allProducts.forEach(p -> listOfProductsMap.put(p.getId(),p));
    }

    public void updateProduct(int productID,String productName,int productPrice) {
        if(listOfProductsMap.get(productID)!=null){
            Product tmp = listOfProductsMap.get(productID);
            tmp.setName(productName);
            tmp.setPrice(productPrice);
            pr.updateProduct(tmp.getId(),tmp.getName(),tmp.getPrice());
        }
        updateListOfProducts();
    }

    public void deleteProduct(int productId){
        pr.deleteProduct(productId);
        updateListOfProducts();
    }

    public Product getSpecificProduct(int productId) {
        if(listOfProductsMap.get(productId)!=null){
            return listOfProductsMap.get(productId);
        }
        else{
            updateListOfProducts();
            return listOfProductsMap.get(productId);
        }
    }
}
