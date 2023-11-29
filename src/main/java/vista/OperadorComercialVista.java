package vista;

import modelo.DatosContacto;
import modelo.OperadorComercial;

import java.util.Scanner;

public class OperadorComercialVista {

    public OperadorComercial cargarOperadorComercialNuevo(int legajo) {

        OperadorComercial operadorComercial = new OperadorComercial();

        System.out.println("Ingrese el apellido del Operador Comercial");
        operadorComercial.setApellido(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el nombre del Operador Comercial");
        operadorComercial.setNombre(new Scanner(System.in).nextLine());

        operadorComercial.setLegajo(legajo);

        DatosContacto datosContacto = new DatosContacto();
        System.out.println("Ingrese el celular del Operador Comercial");
        datosContacto.setCelular(new Scanner(System.in).nextLong());
        System.out.println("Ingrese el Email del Operador Comercial");
        datosContacto.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el telefono del Operador Comercial");
        datosContacto.setTelefono(new Scanner(System.in).nextLong());

        operadorComercial.setDatosContacto(datosContacto);

        return operadorComercial;
    }

    public OperadorComercial modificarOperadorComercial(OperadorComercial operadorComercial, int legajo) {

        System.out.println("Ingrese el apellido del Operador Comercial");
        operadorComercial.setApellido(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el nombre del Operador Comercial");
        operadorComercial.setNombre(new Scanner(System.in).nextLine());

        operadorComercial.setLegajo(legajo);

        DatosContacto datosContacto = new DatosContacto();
        System.out.println("Ingrese el celular del Operador Comercial");
        datosContacto.setCelular(new Scanner(System.in).nextLong());
        System.out.println("Ingrese el Email del Operador Comercial");
        datosContacto.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el telefono del Operador Comercial");
        datosContacto.setTelefono(new Scanner(System.in).nextLong());

        operadorComercial.setDatosContacto(datosContacto);

        return operadorComercial;
    }

}
