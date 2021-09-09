package com.warecatalog.demo.Controllers;

import com.warecatalog.demo.Models.Product;
import com.warecatalog.demo.Services.WareCatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WareCatalogController {

    private WareCatalogService service = new WareCatalogService();

    @GetMapping(value = "")
    public String renderIndex() {
        return "index.html";
    }

    @GetMapping(value = "/catalog")
    public String renderCatalog(Model model) {
        model.addAttribute("catalog", service.getAllProducts());
        return "catalog.html";
    }

    @GetMapping(value = "/add-product")
    public String renderCreateProduct() {
        return "createProduct.html";
    }

    @PostMapping(value = "create-product")
    public String createProduct(@RequestParam("productName") String productName,
                                @RequestParam("productPrice") int price) {
        service.createProduct(productName, price);
        return "redirect:/catalog";
    }

    @GetMapping(value = "/update-product/{productId}")
    public String renderUpdateProduct(@PathVariable("productId") int productId, Model model) {
        Product pToEdit = service.getSpecificProduct(productId);
        model.addAttribute("productId", pToEdit.getId());
        model.addAttribute("productName", pToEdit.getName());
        model.addAttribute("productPrice", pToEdit.getPrice());
        return "edit-product.html";
    }

    @PostMapping(value = "/upload-edit")
    public String updateProduct(@RequestParam("productID1") int productID,
                                @RequestParam("productName") String productName,
                                @RequestParam("productPrice") int price) {
        if (productName == null && price == -1) {
            return "redirect:/catalog";
        }
        service.updateProduct(productID, productName, price);
        return "redirect:/catalog";
    }

    @PostMapping(value = "/delete-product")
    public String deleteProduct(@RequestParam("productID") int productID) {
        service.deleteProduct(productID);
        return "redirect:/catalog";
    }
}
