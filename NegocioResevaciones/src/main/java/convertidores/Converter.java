/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package convertidores;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Clase genérica que define las operaciones necesarias para la conversión de entidades a DTOs y viceversa.
 * Facilita la transformación de objetos entre las capas de negocio y de persistencia en el sistema.
 * Utiliza funciones de conversión especificadas para realizar las operaciones.
 *
 * @param <T> Tipo de datos DTO (Data Transfer Object) 
 * @param <U> Tipo de datos entidad
 * @author caarl
 */
public class Converter<T, U> {

    private final Function<T, U> fromDto;
    private final Function<U, T> fromEntity;

    /**
     * Constructor de la clase Converter.
     * 
     * @param fromDto función que define la conversión de DTO a entidad
     * @param fromEntity función que define la conversión de entidad a DTO
     */
    public Converter(final Function<T, U> fromDto, final Function<U, T> fromEntity) {
        this.fromDto = fromDto;
        this.fromEntity = fromEntity;
    }

    /**
     * Convierte un objeto DTO en una entidad utilizando la función fromDto.
     * 
     * @param dto el objeto DTO a convertir
     * @return el objeto convertido de tipo entidad
     */
    public final U convertFromDto(final T dto) {
        return fromDto.apply(dto);
    }

    /**
     * Convierte una entidad en un objeto DTO utilizando la función fromEntity.
     * 
     * @param entity la entidad a convertir
     * @return el objeto convertido de tipo DTO
     */
    public final T convertFromEntity(final U entity) {
        return fromEntity.apply(entity);
    }

    /**
     * Convierte una colección de DTOs en una lista de entidades.
     * 
     * @param dtos la colección de DTOs a convertir
     * @return una lista de entidades convertidas desde los DTOs
     */
    public final List<U> createFromDtos(final Collection<T> dtos) {
        return dtos.stream().map(this::convertFromDto).collect(Collectors.toList());
    }

    /**
     * Convierte una colección de entidades en una lista de DTOs.
     * 
     * @param entities la colección de entidades a convertir
     * @return una lista de DTOs convertidos desde las entidades
     */
    public final List<T> createFromEntities(final Collection<U> entities) {
        return entities.stream().map(this::convertFromEntity).collect(Collectors.toList());
    }
}
