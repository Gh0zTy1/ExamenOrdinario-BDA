package entidades;

import entidades.Mesa;
import java.time.LocalTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-12-14T00:49:19", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Restaurante.class)
public class Restaurante_ { 

    public static volatile SingularAttribute<Restaurante, LocalTime> horaApertura;
    public static volatile ListAttribute<Restaurante, Mesa> mesas;
    public static volatile SingularAttribute<Restaurante, String> direccion;
    public static volatile SingularAttribute<Restaurante, Long> id;
    public static volatile SingularAttribute<Restaurante, String> telefono;
    public static volatile SingularAttribute<Restaurante, LocalTime> horaCierre;
    public static volatile SingularAttribute<Restaurante, String> nombre;

}