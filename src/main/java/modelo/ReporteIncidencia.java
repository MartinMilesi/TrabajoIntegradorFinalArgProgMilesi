package modelo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "arg_prog_reporte_incidencia")
@Getter @Setter
public class ReporteIncidencia  extends EntidadId {

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(nullable = false)
    private String descripcionProblema;
    @Column(nullable = false)
    private String tipoProblema;//basico, intermedio, complejo
    @ManyToOne
    @JoinColumn(name = "idservicio")
    private Servicio servicio;//N a 1
    @ManyToOne
    @JoinColumn(name = "idoperador")
    private OperadorMesaAyuda operador;//N a 1
    @ManyToOne
    @JoinColumn(name = "idcliente", nullable = false)
    private Cliente cliente;//N a 1
    @ManyToOne
    @JoinColumn(name = "idtecnico", nullable = false)
    private Tecnico tecnico;//N a 1
    private int tiempoEstimadoResolucion;//horas o minutos. //Segun el tipo de problema.
    @Temporal(TemporalType.DATE)
    private Date fechaPosibleResolucion;
    private String estado;//pendiente, en proceso, resuelto, anulado
    private String observacionesTecnico;

    public ReporteIncidencia(){}

    public ReporteIncidencia (OperadorMesaAyuda operadorMesaAyuda, Cliente cliente, Servicio servicio, Tecnico tecnico, String descripcionProblema, String tipoProblema, int tiempoEstimadoResolucion, Date fechaAlta, Date fechaPosibleResolucion, String observacionesTecnico, String estado) {
        this.operador = operadorMesaAyuda;
        this.cliente = cliente;
        this.servicio = servicio;
        this.tecnico = tecnico;
        this.descripcionProblema = descripcionProblema;
        this.tipoProblema = tipoProblema;
        this.tiempoEstimadoResolucion = tiempoEstimadoResolucion;
        this.fechaAlta = fechaAlta;
        this.fechaPosibleResolucion = fechaPosibleResolucion;
        this.observacionesTecnico = observacionesTecnico;
        this.estado = estado;
    }

}