package com.platzi.market.persitence;

import com.platzi.market.persitence.crud.ProductoCrudRepository;
import com.platzi.market.persitence.entity.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;





    //--------------------Metodo para recuperar tds los productos de la bd
    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    //-----------------------Metodo para recuperar tds los productos
    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    //Metodo para obtener los productos que se estan agotando(lessThan) y que se estan vendiendo actualmente(estado)
    public Optional<List<Producto>> getEscasos(int cantidad, boolean estado){
        return productoCrudRepository.findByCantidadEstockLessThanAndEstado(cantidad, estado);
    }
}
