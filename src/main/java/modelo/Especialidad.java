package modelo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "arg_prog_especialidad")
@Getter @Setter
public class Especialidad  extends EntidadId{

    @Column(length = 175, nullable = false)
    private String denominacion;

    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TecnicoEspecialidad> tecnicosEspecialidad;//1 a N

    public Especialidad(){}

    public Especialidad(String denominacion) {
        this.denominacion = denominacion;
    }
}