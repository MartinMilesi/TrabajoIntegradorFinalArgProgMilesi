package vista;

import modelo.DatosContacto;
import modelo.OperadorRecursos;

import java.util.Scanner;

public class OperadorRecursosVista {

    public OperadorRecursos cargarOperadorRecursosNuevo(int legajo) {

        OperadorRecursos operadorRecursos = new OperadorRecursos();

        System.out.println("Ingrese el apellido del Operador de Recursos Humanos");
        operadorRecursos.setApellido(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el nombre del Operador de Recursos Humanos");
        operadorRecursos.setNombre(new Scanner(System.in).nextLine());

        operadorRecursos.setLegajo(legajo);

        DatosContacto datosContacto = new DatosContacto();
        System.out.println("Ingrese el celular del Operador de Recursos Humanos");
        datosContacto.setCelular(new Scanner(System.in).nextLong());
        System.out.println("Ingrese el Email del Operador de Recursos Humanos");
        datosContacto.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el telefono del Operador de Recursos Humanos");
        datosContacto.setTelefono(new Scanner(System.in).nextLong());

        operadorRecursos.setDatosContacto(datosContacto);

        return operadorRecursos;
    }

    public OperadorRecursos modificarOperadorRecursos(OperadorRecursos operadorRecursos, int legajo) {

        System.out.println("Ingrese el apellido del Operador de Recursos Humanos");
        operadorRecursos.setApellido(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el nombre del Operador de Recursos Humanos");
        operadorRecursos.setNombre(new Scanner(System.in).nextLine());

        operadorRecursos.setLegajo(legajo);

        DatosContacto datosContacto = new DatosContacto();
        System.out.println("Ingrese el celular del Operador de Recursos Humanos");
        datosContacto.setCelular(new Scanner(System.in).nextLong());
        System.out.println("Ingrese el Email del Operador de Recursos Humanos");
        datosContacto.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el telefono del Operador de Recursos Humanos");
        datosContacto.setTelefono(new Scanner(System.in).nextLong());

        operadorRecursos.setDatosContacto(datosContacto);

        return operadorRecursos;
    }

}
