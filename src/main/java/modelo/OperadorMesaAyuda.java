package modelo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity @Table(name = "arg_prog_operador_mesa_ayuda")
@Getter @Setter
public class OperadorMesaAyuda extends Empleado{

    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReporteIncidencia> reportesIncidencia;

    public OperadorMesaAyuda(){}

    public OperadorMesaAyuda(String apellido, String nombre, int legajo, DatosContacto datosContacto){

        super(apellido, nombre, legajo, datosContacto);

    }

}