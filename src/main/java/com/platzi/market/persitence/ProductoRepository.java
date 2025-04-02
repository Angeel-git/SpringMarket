package com.platzi.market.persitence;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persitence.crud.ProductoCrudRepository;
import com.platzi.market.persitence.entity.Producto;
import com.platzi.market.persitence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //Indicamops que es una clase que trabaja ocn la bd directamente
public class ProductoRepository implements ProductRepository {
    @Autowired  //Le damos a Spring la capacidad para que pueda crear un objeto de este tipo
    private ProductoCrudRepository productoCrudRepository;


    @Autowired
    private ProductMapper mapper;

    @Override
    //Recibimos una lista de productos y lo devolvemos como una lista de products
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    //Recibimos una lista de productos por categoria y lo devolvemos como una lista de products
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScaseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadEstockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }


    @Override
    //Metodo para eliminar un producto de mi api por el id
    public  void delete(int productId){
        productoCrudRepository.deleteById(productId); //Metodo propio de SpingData en el crudRepository
    }
}
