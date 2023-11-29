package modelo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "arg_prog_cliente")
@Getter @Setter
public class Cliente extends EntidadId{

    @Column(length = 150)
    private String razonSocial;

    @Column(nullable = false, unique = true)
    private long cuit;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClienteServicio> clienteServicios;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReporteIncidencia> reportesIncidencia;//1 a N

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iddatoscontacto")
    private DatosContacto datosContacto;//1 a 1

    public Cliente(){}

    public Cliente (String razonSocial, Long cuit, DatosContacto datosContacto){
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.datosContacto = datosContacto;
    }

    public void addServicio(ClienteServicio clienteServicio) {

        if (this.clienteServicios == null)
            this.clienteServicios = new ArrayList<ClienteServicio>();
        this.clienteServicios.add(clienteServicio);

    }

}