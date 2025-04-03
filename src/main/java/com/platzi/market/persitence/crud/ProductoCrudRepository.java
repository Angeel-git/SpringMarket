package com.platzi.market.persitence.crud;

import com.platzi.market.persitence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//"CrudRepository" es para hacer referencia a una clase de SpringData donde ya tiene metodos para trabajar con la bd
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //Recuperar td la lista de prodcutos que pertenezcan a una lista utilizando los queryMetods
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
    //El orderByNombreAsc es para ordenar los elementos que recibe la consulta


    //Recuperar la lista de los productos que esten en stock y se esten vendiendo
    /*Pero los que esten agotandose(el cantidadStock es para comparar le numero de unidades que tienes con el numero
    que le pasas, y el estado es para saber si actualmente se estan vendiendo*/
    Optional<List<Producto> > findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
