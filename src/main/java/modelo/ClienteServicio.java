package modelo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity @Table(name = "arg_prog_cliente_servicio")
@Getter @Setter
public class ClienteServicio extends  EntidadId{

    public ClienteServicio(){}

    public ClienteServicio(Cliente cliente, Servicio servicio){

        this.cliente = cliente;
        this.servicio = servicio;

    }

    @ManyToOne @JoinColumn(name = "idcliente")
    private Cliente cliente;

    @ManyToOne @JoinColumn(name = "idservicio")
    private  Servicio servicio;

}