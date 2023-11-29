package vista;

import modelo.Cliente;
import modelo.DatosContacto;
import java.util.Scanner;

public class ClienteVista {

    public Cliente cargarClienteNuevo(){

        Cliente cliente = new Cliente();

        System.out.println("Ingrese el CUIT del Cliente");
        cliente.setCuit(new Scanner(System.in).nextLong());
        System.out.println("Ingrese la razon social del Cliente");
        cliente.setRazonSocial(new Scanner(System.in).nextLine());

        DatosContacto datosContacto = new DatosContacto();
        System.out.println("Ingrese el celular del Cliente");
        datosContacto.setCelular(new Scanner(System.in).nextLong());
        System.out.println("Ingrese el Email del Cliente");
        datosContacto.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el telefono del Cliente");
        datosContacto.setTelefono(new Scanner(System.in).nextLong());

        cliente.setDatosContacto(datosContacto);

        return cliente;
    }

    public Cliente modificarCliente(Cliente cliente){

        System.out.println("Ingrese el CUIT del Cliente");
        cliente.setCuit(new Scanner(System.in).nextLong());
        System.out.println("Ingrese la razon social del Cliente");
        cliente.setRazonSocial(new Scanner(System.in).nextLine());

        DatosContacto datosContacto = cliente.getDatosContacto();
        System.out.println("Ingrese el celular del Cliente");
        datosContacto.setCelular(new Scanner(System.in).nextLong());
        System.out.println("Ingrese el Email del Cliente");
        datosContacto.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el telefono del Cliente");
        datosContacto.setTelefono(new Scanner(System.in).nextLong());

        cliente.setDatosContacto(datosContacto);

        return cliente;

    }

}