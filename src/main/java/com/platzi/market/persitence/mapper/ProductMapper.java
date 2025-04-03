package com.platzi.market.persitence.mapper;

import com.platzi.market.domain.Category;
import com.platzi.market.domain.Product;
import com.platzi.market.persitence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


//Indicamos que es un mappeado para convertir las categorias en categories y los productos en products
//El uses = {CategoryMapper.class}, es para que a la hora de mappear la categoria ->category utilice la clase
// CategoryMapper(donde ya tenemos definidos los mapeadores para esos objetos
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})

public interface ProductMapper {

    @Mappings({ // Mapea los campos de la entidad a los campos del DTO
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category")

    })
    Product toProduct(Producto producto);   //Conversion de producto a product
    List<Product> toProducts(List<Producto> productos); //conversion de una lista de productos a una lista de products

    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);//Conversion de product a producto
}
