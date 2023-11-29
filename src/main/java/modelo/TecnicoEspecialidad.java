package modelo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "arg_prog_tecnico_especialidad")
@Getter @Setter
public class TecnicoEspecialidad extends EntidadId implements Comparable<TecnicoEspecialidad>{

    public TecnicoEspecialidad(){}

    public TecnicoEspecialidad(Tecnico tecnico, Especialidad especialidad) {
        this.tecnico = tecnico;
        this.especialidad = especialidad;
    }


    @ManyToOne
    @JoinColumn(name = "idtecnico")
    private Tecnico tecnico;
    @ManyToOne
    @JoinColumn(name = "idespecialidad")
    private Especialidad especialidad;

    @Override
    public int compareTo(TecnicoEspecialidad o) {
        return new Integer(this.tecnico.getLegajo()).compareTo(o.getTecnico().getLegajo());
    }

}