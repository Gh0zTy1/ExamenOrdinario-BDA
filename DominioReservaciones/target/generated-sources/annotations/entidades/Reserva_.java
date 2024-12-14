package entidades;

import entidades.Cliente;
import entidades.Mesa;
import entidades.Restaurante;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-12-13T22:54:09", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, Cliente> cliente;
    public static volatile SingularAttribute<Reserva, Integer> numeroPersonas;
    public static volatile SingularAttribute<Reserva, String> estado;
    public static volatile SingularAttribute<Reserva, Double> costo;
    public static volatile SingularAttribute<Reserva, Mesa> mesa;
    public static volatile SingularAttribute<Reserva, Double> multa;
    public static volatile SingularAttribute<Reserva, Restaurante> restaurante;
    public static volatile SingularAttribute<Reserva, LocalDateTime> horaReserva;
    public static volatile SingularAttribute<Reserva, Long> id;
    public static volatile SingularAttribute<Reserva, LocalDateTime> fechaReserva;

}