package vista;

import controlador.GestorServicio;
import modelo.Cliente;
import modelo.ClienteServicio;
import modelo.Servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServicioVista {

    public void cargarServicioCliente(Cliente cliente) throws Exception {

        GestorServicio gestorServicio = new GestorServicio();
        System.out.println("Lista de Servicios");
        List<Servicio> servicios = gestorServicio.listar(Servicio.class);

        for (Servicio servicio : servicios) {
            System.out.println(servicio.getId() + " " + servicio.getDenominacion());
        }

        List<Long> idServicios = new ArrayList<Long>();

        while (true) {

            System.out.println("Seleccione el servicio a asignar al cliente");
            long idCliente = new Scanner(System.in).nextLong();

            if (idServicios.contains(idCliente)) {

                System.out.println("El servicio seleccionado ya fue asignado, elija otro");
                continue;

            }

            idServicios.add(idCliente);

            Servicio servicio = (Servicio) gestorServicio.buscarPorId(Servicio.class, idCliente);

            if (servicio != null) {

                ClienteServicio clienteServicio = new ClienteServicio();
                clienteServicio.setServicio(servicio);
                clienteServicio.setCliente(cliente);
                cliente.addServicio(clienteServicio);
                System.out.println("El servicio " + servicio.getDenominacion() + " fue agregado exitosamente");

            } else {

                System.out.println("El id del servicio ingresado no existe");

            }

            System.out.println("Desea agregar otro servicio?? S/N");
            String continuar = new Scanner(System.in).nextLine();
            if(!continuar.toUpperCase().equals("S")){
                break;
            }

        }

    }

    public Servicio cargarServicioNuevo() {

        Servicio servicio = new Servicio();

        System.out.println("Ingrese el nombre del servicio");
        servicio.setDenominacion(new Scanner(System.in).nextLine());

        return servicio;
    }

    public Servicio modificarServicio(Servicio servicio) {

        System.out.println("Ingrese el nombre del servicio");
        servicio.setDenominacion(new Scanner(System.in).nextLine());

        return servicio;
    }

}