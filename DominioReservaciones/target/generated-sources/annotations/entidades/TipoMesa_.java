package entidades;

import entidades.Mesa;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-12-14T14:01:30", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(TipoMesa.class)
public class TipoMesa_ { 

    public static volatile SingularAttribute<TipoMesa, Integer> maximoPersonas;
    public static volatile SingularAttribute<TipoMesa, Float> precio;
    public static volatile ListAttribute<TipoMesa, Mesa> mesas;
    public static volatile SingularAttribute<TipoMesa, Integer> minimoPersonas;
    public static volatile SingularAttribute<TipoMesa, Long> id;
    public static volatile SingularAttribute<TipoMesa, String> nombre;

}