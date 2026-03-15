package net.ayoub.ap2springmvc.web;

import groovy.lang.GString;
import jakarta.persistence.PostRemove;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import net.ayoub.ap2springmvc.entities.Product;
import net.ayoub.ap2springmvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    public ProductRepository productRepository;

    @GetMapping("/user/index")
    @PreAuthorize("hasRole('USER')")

    public String index(Model model){
        List<Product> products  = productRepository.findAll();
        model.addAttribute("productList",products);
        return "products";
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/user/index";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/newProduct")
    public String newProduct(Model model){
        model.addAttribute("product",new Product());
        return "new-product";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/delete")
    public String delete(@RequestParam(name =  "id") Long id){
        productRepository.deleteById(id);
        return "redirect:/user/index";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/saveProduct")
    public String saveProduct(@Valid Product product, BindingResult bindingResults,Model model){
        //BindingResults sert a stocker la liste des erreurs
        if(bindingResults.hasErrors()) return "new-product";
        productRepository.save(product);
        return "redirect:/admin/newProduct";
    }

    @GetMapping("/notAuthorized")
    public String notAutorized(){
        return"notAuthorized";
    }

    @GetMapping("/login")
    public String login(){
        return"login";
    }
    @GetMapping("/logout")
        public String logout(HttpSession session){
        session.invalidate(); //detruiser la session
        return "login";
    }

}
