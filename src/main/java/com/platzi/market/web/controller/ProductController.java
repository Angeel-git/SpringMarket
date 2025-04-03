package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    /*En este caso al ruta va entre corchetes ya que se le va a indicar el id del producto desde "PathVariable"
    * Por ejemplo -> http://localhost:8090/platzi-market/api/products/product/4*/
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId){
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("id") int categoryId){
        return productService.getByCategory(categoryId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Como este metodo es para guardar, el llamado es diferente ("PostMapping")
    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){  //OJO -> Aqui no es @PathVariable
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId){
        if(productService.delete(productId)){
            return new ResponseEntity((HttpStatus.OK));
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
