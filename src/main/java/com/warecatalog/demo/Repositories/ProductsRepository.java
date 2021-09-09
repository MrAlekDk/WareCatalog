package com.warecatalog.demo.Repositories;

import com.warecatalog.demo.Models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductsRepository {

    private ConnectionCreator cc;

    public ProductsRepository(){
        cc = new ConnectionCreator();
    }

    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> allProducts = new ArrayList<>();
        Connection con = cc.getCon();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Catalog");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
            Product tmp = new Product(rs.getInt(1),rs.getString(2),rs.getInt(3));
            allProducts.add(tmp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allProducts;
    }

    public void addNewProduct(Product newProduct) {
        Connection con = cc.getCon();
        try {

            PreparedStatement stmt = con.prepareStatement("INSERT INTO Catalog (WareName,WarePrice) VALUES (?,?)");

            stmt.setString(1, newProduct.getName());
            stmt.setInt(2, newProduct.getPrice());
            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateProduct(int productId, String newName, int newPrice){
        Connection con = cc.getCon();
        try {
            PreparedStatement stmt = con.prepareStatement("Update Catalog SET WareName=?, Wareprice=? where id=?");
            stmt.setString(1, newName);
            stmt.setInt(2, newPrice);
            stmt.setInt(3, productId);
            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteProduct(int productId) {
        Connection con = cc.getCon();
        try {
            PreparedStatement stmt = con.prepareStatement("Delete From Catalog where id=?");

            stmt.setInt(1, productId);
            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Product getSpecificProduct(int productId) {
        Product tmpProduct = null;
        Connection con = cc.getCon();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Catalog where id=?");
            stmt.setInt(1 ,productId);
            ResultSet rs = stmt.executeQuery();

            tmpProduct = new Product(rs.getInt(1),rs.getString(2),rs.getInt(3));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tmpProduct;
    }
}
