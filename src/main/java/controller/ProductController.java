package controller;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ModelAndView getAllProduct(){
        ModelAndView modelAndView = new ModelAndView("showProduct");
        modelAndView.addObject("products",productService.products);
        return modelAndView;
    }
//    @GetMapping("/create")
//    public ModelAndView showCreateProduct(){
//        ModelAndView modelAndView = new ModelAndView("createProduct");
//        return modelAndView;
//    }
//    @PostMapping("/create")
//    public void create(@ModelAttribute Product product, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        productService.add(product);
//        response.sendRedirect("products");
//    }

    @GetMapping("/create")
    public String getCreate() {
        return "createProduct";
    }

    @ModelAttribute(name = "linh")
    public Product product(){
        return new Product();
    }
@PostMapping("/create")
public String create(Product product,@RequestParam MultipartFile upImg) {
    String name = upImg.getOriginalFilename();
    try {
        upImg.transferTo(new File("/D:/module3/demo-mvc-thymeleaf/src/main/webapp/WEB-INF/img/"+ name));
    } catch (IOException e) {
        e.printStackTrace();
    }
    product.setImg("/img/"+ name);
    productService.add(product);
    return "redirect:/products";
}

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("showEdit");
        Product product = productService.products.get(productService.findIndexById(id));
        modelAndView.addObject("p",product);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@ModelAttribute Product product, @PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        productService.edit(id,product);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id){
        productService.delete(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        return modelAndView;
    }
}
