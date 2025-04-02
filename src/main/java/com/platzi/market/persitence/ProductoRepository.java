package com.platzi.market.persitence;

import com.platzi.market.persitence.crud.ProductoCrudRepository;
import com.platzi.market.persitence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //Indicamops que es una clase que trabaja ocn la bd directamente
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;


    //--------------------Metodo para recuperar tds los productos de la bd
    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll(); //Metodo propio de SpingData en el crudRepository
    }

    //-----------------------Metodo para recuperar tds los productos por categoria
    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    //Metodo para obtener los productos que se estan agotando(lessThan) y que se estan vendiendo actualmente(estado)
    public Optional<List<Producto>> getEscasos(int cantidad, boolean estado){
        return productoCrudRepository.findByCantidadEstockLessThanAndEstado(cantidad, estado);
    }


    //Metodo para consultar un producto en particular
    public Optional<Producto> getProducto(int idProducto){
        return productoCrudRepository.findById(idProducto);//Metodo propio de SpingData en el crudRepository
    }

    //Metodo para guardar un producto de mi api
    public Producto save(Producto producto){
        return productoCrudRepository.save(producto); //Metodo propio de SpingData en el crudRepository
    }

    //Metodo para eliminar un producto de mi api por el id
    public  void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto); //Metodo propio de SpingData en el crudRepository
    }
}
