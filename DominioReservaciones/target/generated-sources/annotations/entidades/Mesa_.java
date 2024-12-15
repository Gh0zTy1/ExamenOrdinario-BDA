package entidades;

import entidades.Restaurante;
import entidades.TipoMesa;
import entidades.UbicacionMesa;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-12-15T09:21:17", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Mesa.class)
public class Mesa_ { 

    public static volatile SingularAttribute<Mesa, String> codigo;
    public static volatile SingularAttribute<Mesa, UbicacionMesa> ubicacion;
    public static volatile SingularAttribute<Mesa, Restaurante> restaurante;
    public static volatile SingularAttribute<Mesa, Long> id;
    public static volatile SingularAttribute<Mesa, TipoMesa> tipoMesa;
    public static volatile SingularAttribute<Mesa, LocalDateTime> fechaNuevaDisponibilidad;

}