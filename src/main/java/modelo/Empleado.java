package modelo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@MappedSuperclass
@Getter @Setter
public abstract class Empleado  extends EntidadId{


    protected String apellido;
    protected String nombre;
    @Column(unique = true)
    protected int legajo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iddatoscontacto")
    protected DatosContacto datosContacto;// 1 a 1
    @Transient
    protected String nombreCompleto;

    public Empleado(){}

    public Empleado(String apellido, String nombre, int legajo, DatosContacto datosContacto) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.legajo = legajo;
        this.datosContacto = datosContacto;
    }

    public String getNombreCompleto(){
        return this.apellido + " " + this.nombre;
    }

}