package modelo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "arg_prog_servicio")
@Getter @Setter
public class Servicio  extends EntidadId{

    @Column(nullable = false)
    private String denominacion;
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClienteServicio> clientes;
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReporteIncidencia> reportesIncidencia;

    public Servicio(){}

    public Servicio(String denominacion) {
        this.denominacion = denominacion;
    }

}