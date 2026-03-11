package net.ayoub.ap2springmvc.web;

import jakarta.validation.Valid;
import net.ayoub.ap2springmvc.entities.Product;
import net.ayoub.ap2springmvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    public ProductRepository productRepository;

    @GetMapping("/index")
    public String index(Model model){
        List<Product> products  = productRepository.findAll();
        model.addAttribute("productList",products);
        return "products";
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/index";
    }
    @GetMapping("/newProduct")
    public String newProduct(Model model){
        model.addAttribute("product",new Product());
        return "new-product";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam(name =  "id") Long id){
        productRepository.deleteById(id);
        return "redirect:/index";
    }
    @PostMapping("/saveProduct")
    public String saveProduct(@Valid Product product, BindingResult buindingResults // stocke la liste des erreurs){

    }

}
