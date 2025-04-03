package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController  //Le indica a Spring que va a ser un controlador de una api-rest
@RequestMapping("/products")    //Indicamos el path
public class ProductController {
    @Autowired
    private ProductService productService;


    //El GetMapping es para mostrar lo que devuelve un metodo, el "/all" es para indicarle la ruta a la vista del metodo en la web
    @GetMapping("/all")
    public List<Product> getAll(){
        return productService.getAll();
    }

    /*En este caso al ruta va entre corchetes ya que se le va a indicar el id del producto desde "PathVariable"
    * Por ejemplo -> http://localhost:8090/platzi-market/api/products/product/4*/
    @GetMapping("/product/{id}")
    public Optional<Product> getProduct(@PathVariable("id") int productId){
        return productService.getProduct(productId);
    }


    @GetMapping("/category/{id}")
    public Optional<List<Product>> getByCategory(@PathVariable("id") int categoryId){
        return productService.getByCategory(categoryId);
    }

    //Como este metodo es para guardar, el llamado es diferente ("PostMapping")
    @PostMapping("/save")
    public Product save(@RequestBody Product product){  //OJO -> Aqui no es @PathVariable
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int productId){
        return  productService.delete(productId);
    }
}
