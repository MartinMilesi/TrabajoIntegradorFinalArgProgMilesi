package modelo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "arg_prog_datos_contacto")
@Getter @Setter
public class DatosContacto  extends EntidadId{

    private long telefono;
    private long celular;
    @Column(length = 75)
    private String email;

    public DatosContacto(){}

    public DatosContacto(long telefono, long celular, String email) {
        this.telefono = telefono;
        this.celular = celular;
        this.email = email;
    }
}