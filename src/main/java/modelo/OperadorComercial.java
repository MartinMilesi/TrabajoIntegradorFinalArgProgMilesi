package modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "arg_prog_operador_comercial")
@Getter @Setter
public class OperadorComercial extends Empleado{

    public OperadorComercial(){}

    public OperadorComercial (String apellido, String nombre, int legajo, DatosContacto datosContacto){

        super(apellido, nombre, legajo, datosContacto);

    }

}
