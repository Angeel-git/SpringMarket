package com.platzi.market.persitence.mapper;

import com.platzi.market.domain.Category;
import com.platzi.market.persitence.entity.Categoria;
import org.mapstruct.*;

@Mapper(componentModel = "spring") //Indicamos que es un mappeado para convertir
                                    // las categorias en categories y los productos en products
public interface CategoryMapper {
    @Mappings({ // Mapea los campos de la entidad a los campos del DTO
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")

    })
    Category toCategory(Categoria categoria); //Convertir categoria a category

    //Indicamos que le conversion es justo la contraria a la de arriba no hace falta volver a poner td los de Mappings
    @InheritInverseConfiguration
    //Como en category no tenemos el atributo productos(que si tenemos en Categoria) no la vamos a mapear, tenemos que decirle que lo ignore)
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);

}
