package vista;

import controlador.GestorEspecialidad;
import controlador.GestorTecnico;
import modelo.Especialidad;
import modelo.Tecnico;
import modelo.TecnicoEspecialidad;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EspecialidadVista {

    private GestorEspecialidad gestorEspecialidad;

    public EspecialidadVista() {
        this.gestorEspecialidad = new GestorEspecialidad();
    }
    public void cargarEspecialidadesTecnico(Tecnico tecnico) throws Exception {

        System.out.println("Lista de Especialidades");
        List<Especialidad> especialidades = gestorEspecialidad.listar(Especialidad.class);
        for(Especialidad esp : especialidades){
            System.out.println(esp.getId() + " " + esp.getDenominacion());
        }

        List<Long> idEspecialidades = new ArrayList<Long>();
        while(true){
            System.out.println("Seleccione la especialidad a asignar al tecnico");
            long idTecnico = new Scanner(System.in).nextLong();
            if(idEspecialidades.contains(idTecnico)){
                System.out.println("La especialidad seleccionada ya fue asignada, elija otra");
                continue;
            }
            idEspecialidades.add(idTecnico);

            Especialidad especialidad = (Especialidad) gestorEspecialidad.buscarPorId(Especialidad.class, idTecnico);
            if(especialidad != null){
                TecnicoEspecialidad te = new TecnicoEspecialidad();
                te.setEspecialidad(especialidad);
                te.setTecnico(tecnico);
                tecnico.addEspecialidad(te);
                System.out.println("La especialidad " + especialidad.getDenominacion() + " fue agregada exitosamente");
            }else{
                System.out.println("El id de la especialidad ingresada no existe");
            }
            System.out.println("Desea agregar otra especialidad?? S/N");
            String continuar = new Scanner(System.in).nextLine();
            if(!continuar.toUpperCase().equals("S")){
                break;
            }
        }

    }

    public Especialidad cargarEspecialidadNueva() {

        Especialidad especialidad = new Especialidad();

        System.out.println("Ingrese el nombre de la especialidad");
        especialidad.setDenominacion(new Scanner(System.in).nextLine());

        return especialidad;
    }

    public Especialidad modificarEspecialidad(Especialidad especialidad) {

        System.out.println("Ingrese el nombre de la especialidad");
        especialidad.setDenominacion(new Scanner(System.in).nextLine());

        return especialidad;
    }

    public Especialidad mostrarMenuSeleccionEspecialidad() {
        Scanner scanner = new Scanner(System.in);

        List<Especialidad> especialidades = gestorEspecialidad.obtenerTodasLasEspecialidades();

        System.out.println("Seleccione una especialidad:");
        for (int i = 0; i < especialidades.size(); i++) {
            System.out.println((i + 1) + ". " + especialidades.get(i).getDenominacion());
        }

        int opcion = scanner.nextInt();

        if (opcion >= 1 && opcion <= especialidades.size()) {
            System.out.println("Especialidad seleccionada");
            return especialidades.get(opcion - 1);
        } else {
            System.out.println("Opción no válida. Inténtelo de nuevo.");
            return null;
        }
    }

}