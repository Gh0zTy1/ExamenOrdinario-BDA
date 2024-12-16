package entidades;

import entidades.Cliente;
import entidades.EstadoReservacion;
import entidades.Mesa;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-12-16T09:23:44", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Reservacion.class)
public class Reservacion_ { 

    public static volatile SingularAttribute<Reservacion, Cliente> cliente;
    public static volatile SingularAttribute<Reservacion, Integer> numeroPersonas;
    public static volatile SingularAttribute<Reservacion, EstadoReservacion> estado;
    public static volatile SingularAttribute<Reservacion, Mesa> mesa;
    public static volatile SingularAttribute<Reservacion, LocalDateTime> fechaHora;
    public static volatile SingularAttribute<Reservacion, Long> id;
    public static volatile SingularAttribute<Reservacion, LocalDateTime> fechaHoraRegistro;
    public static volatile SingularAttribute<Reservacion, Float> montoTotal;

}