package modelo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "arg_prog_operador_recursos")
@Getter @Setter
public class OperadorRecursos extends Empleado {

    public OperadorRecursos(){}

    public OperadorRecursos(String apellido, String nombre, int legajo, DatosContacto datosContacto) {

        super(apellido, nombre, legajo, datosContacto);

    }

}
