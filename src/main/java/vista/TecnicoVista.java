package vista;

import modelo.DatosContacto;
import modelo.Tecnico;
import modelo.TecnicoEspecialidad;

import java.util.List;
import java.util.Scanner;

public class TecnicoVista {

    public Tecnico cargarTecnicoNuevo(int legajo) {

        Tecnico tecnico = new Tecnico();

        System.out.println("Ingrese el apellido del Tecnico");
        tecnico.setApellido(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el nombre del tecnico");
        tecnico.setNombre(new Scanner(System.in).nextLine());

        tecnico.setLegajo(legajo);

        DatosContacto datosContacto = new DatosContacto();
        System.out.println("Ingrese el celular del Tecnico");
        datosContacto.setCelular(new Scanner(System.in).nextLong());
        System.out.println("Ingrese el Email del Tecnico");
        datosContacto.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el telefono del Tecnico");
        datosContacto.setTelefono(new Scanner(System.in).nextLong());

        tecnico.setDatosContacto(datosContacto);

        return tecnico;
    }

    public Tecnico modificarTecnico(Tecnico tecnico, int legajo) {

        System.out.println("Ingrese el apellido del Tecnico");
        tecnico.setApellido(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el nombre del Tecnico");
        tecnico.setNombre(new Scanner(System.in).nextLine());

        tecnico.setLegajo(legajo);

        DatosContacto datosContacto = new DatosContacto();
        System.out.println("Ingrese el celular del Tecnico");
        datosContacto.setCelular(new Scanner(System.in).nextLong());
        System.out.println("Ingrese el Email del Tecnico");
        datosContacto.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el telefono del Tecnico");
        datosContacto.setTelefono(new Scanner(System.in).nextLong());

        tecnico.setDatosContacto(datosContacto);

        return tecnico;
    }

    public void mostrarTecnicosConEspecialidades(List<Tecnico> tecnicos) {
        if (tecnicos.isEmpty()) {
            System.out.println("No hay técnicos disponibles.");
        } else {
            System.out.println("Seleccione un técnico:");

            int i = 1;
            for (Tecnico tecnico : tecnicos) {
                System.out.println(i + ". " + tecnico.getApellido() + ", " + tecnico.getNombre());
                mostrarEspecialidades(tecnico.getTecnicoEspecialidades());
                i++;
            }

            System.out.println("0. Volver");
        }
    }

    private void mostrarEspecialidades(List<TecnicoEspecialidad> especialidades) {
        System.out.println("   Especialidades:");
        for (TecnicoEspecialidad especialidad : especialidades) {
            System.out.println("      - " + especialidad.getEspecialidad().getDenominacion());
        }
    }

    public Tecnico seleccionarTecnico(List<Tecnico> tecnicos) {
        if (tecnicos.isEmpty()) {
            System.out.println("No hay técnicos disponibles.");
            return null;
        } else {
            System.out.println("Seleccione un técnico:");

            int i = 1;
            for (Tecnico tecnico : tecnicos) {
                System.out.println(i + ". " + tecnico.getApellido() + ", " + tecnico.getNombre());
                mostrarEspecialidades(tecnico.getTecnicoEspecialidades());
                i++;
            }

            System.out.println("0. Volver");

            Scanner scanner = new Scanner(System.in);
            int seleccion = scanner.nextInt();

            if (seleccion >= 1 && seleccion <= tecnicos.size()) {
                return tecnicos.get(seleccion - 1);
            } else {
                System.out.println("Selección no válida.");
                return null;
            }
        }
    }

}