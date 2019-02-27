package com.paralun.app.controller;

import com.paralun.app.model.Product;
import com.paralun.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/products/add")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "edit";
    }

    @PostMapping(path = "products")
    public String saveProduct(Product product) {
        productRepository.save(product);
        return "redirect:/";
    }

    @GetMapping(path = "/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @GetMapping(path = "/products/edit/{id}")
    public String editProduct(Model model, @PathVariable(name = "id") String id) {
        model.addAttribute("product", productRepository.findById(id));
        return "edit";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") String id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
}
