package entidades;

import entidades.Reserva;
import entidades.Restaurante;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-12-13T22:54:09", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Mesa.class)
public class Mesa_ { 

    public static volatile SingularAttribute<Mesa, String> ubicacion;
    public static volatile SingularAttribute<Mesa, String> codigoMesa;
    public static volatile ListAttribute<Mesa, Reserva> reservas;
    public static volatile SingularAttribute<Mesa, Restaurante> restaurante;
    public static volatile SingularAttribute<Mesa, Long> id;
    public static volatile SingularAttribute<Mesa, String> tipoMesa;
    public static volatile SingularAttribute<Mesa, Integer> capacidadMaxima;
    public static volatile SingularAttribute<Mesa, Integer> capacidadMinima;

}