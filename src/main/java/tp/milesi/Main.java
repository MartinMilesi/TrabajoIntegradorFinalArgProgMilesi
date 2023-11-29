package tp.milesi;

import controlador.*;
import modelo.*;
import vista.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {

            mostrarLogin();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarLogin() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido!");
        System.out.println("0- Salir");
        System.out.println("1- Logearse");

        int legajo;
        boolean legajoValido;
        int accion = scanner.nextInt();

        switch (accion) {

            case 0:
                System.exit(0);
                break;
            case 1:
                do {
                    System.out.println("Ingrese su legajo para identificarse: ");
                    System.out.println("Tecnicos: 11100 | 11101 | 11102 | 11103 | 11104 | 11105");
                    System.out.println("OperadoresMesaAyuda: 22200 | 22201 | 22202");
                    System.out.println("OperadoresRecursos: 33200");
                    System.out.println("OperadoresComerciales: 44200");
                    System.out.println("Admin: 00000000");

                    legajo = scanner.nextInt();
                    legajoValido = false;

                    if (legajo == 00000000) {
                        mostrarMenuAdmin();
                        legajoValido = true;
                    } else {
                        GestorLogin gestorLogin = new GestorLogin();
                        Empleado empleado = gestorLogin.getEmpleadoXLegajo(legajo);

                        if (empleado != null) {
                            String tipoEmpleado = gestorLogin.determinarTipoEmpleadoPorClase(empleado);

                            if ("OperadorMesaAyuda".equals(tipoEmpleado)) {
                                mostrarMenuOperadorMesaAyuda();
                            } else if ("OperadorRecursos".equals(tipoEmpleado)) {
                                mostrarMenuOperadorRecursos();
                            } else if ("OperadorComercial".equals(tipoEmpleado)) {
                                mostrarMenuOperadorComercial();
                            } else if ("Tecnico".equals(tipoEmpleado)) {
                                mostrarMenuTecnico();
                            } else {
                                System.out.println("Tipo de empleado no reconocido en login.");
                            }

                            legajoValido = true;

                        } else {
                            System.out.println("Legajo no reconocido. Intente de nuevo.");
                        }
                    }
                } while (!legajoValido);
        }

    }

    private static void mostrarMenuTecnico() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcionMenu;

        do{

            System.out.println("Seleccione la funcionalidad a ejecutar");
            System.out.println("0- Volver al Login");
            System.out.println("1- Gestionar Reportes de Incidencia");
            System.out.println("2- Seleccionar Metodo de notificacion");

            opcionMenu = scanner.nextInt();

            switch (opcionMenu) {
                case 0:
                    System.out.println("Saliendo del menú Tecnico.");
                    mostrarLogin();
                    break;
                case 1:
                    gestionarReportesTecnico();
                    break;
                case 2:
                    System.out.println("No implementado.");
                    System.out.println("Saliendo del menú Tecnico.");
                    //mostrarLogin();
                    break;
            }

        } while (opcionMenu != 0 && opcionMenu != 1 && opcionMenu != 2);


    }

    private static void gestionarReportesTecnico () throws Exception {

        ReporteIncidenciaVista reporteIncidenciaVista = new ReporteIncidenciaVista();
        reporteIncidenciaVista.iniciarGestionReportes();

    }

    private static void mostrarMenuOperadorRecursos() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcionMenu;

        do {
            System.out.println("Seleccione la funcionalidad a ejecutar");
            System.out.println("0- Volver al Login");
            System.out.println("1- Administrar tecnicos");
            System.out.println("2- Reportes de incidente por tecnico por dias");
            System.out.println("3- Reportes de incidente resueltos por especialidad");
            System.out.println("4- Mostrar tecnico mas eficiente");
            System.out.println("5- Mostrar tecnico mas eficiente entre fechas");
            System.out.println("6- Mostrar tecnico menos eficiente");
            System.out.println("7- Mostrar tecnico menos eficiente entre fechas");

            opcionMenu = scanner.nextInt();

            switch (opcionMenu) {
                case 1:
                    gestionarTecnicos();
                    break;
                case 2:
                    generarReporteTecnicoFecha();
                    break;
                case 3:
                    generarReportesResueltosPorEspecialidad();
                    break;
                case 4:
                    mostrarTecnicoMasEficiente();
                    break;
                case 5:
                    mostrarTecnicoMasEficienteEntreFechas();
                    break;
                case 6:
                    mostrarTecnicoMenosEficiente();
                    break;
                case 7:
                    mostrarTecnicoMenosEficienteEntreFechas();
                    break;
                case 0:
                    System.out.println("Saliendo del menú Operador de Recursos Humanos.");
                    mostrarLogin();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionMenu != 0 && opcionMenu != 1 && opcionMenu != 2 && opcionMenu != 3 && opcionMenu != 4 && opcionMenu != 5 && opcionMenu != 6 && opcionMenu != 7);


    }

    private static void mostrarMenuOperadorComercial() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcionMenu;

        do {
            System.out.println("Seleccione la funcionalidad a ejecutar");
            System.out.println("0- Volver al Login");
            System.out.println("1- Administrar Clientes");

            opcionMenu = scanner.nextInt();

            switch (opcionMenu) {
                case 1:
                    gestionarClientes();
                    break;
                case 0:
                    System.out.println("Saliendo del menú Operador Comercial.");
                    mostrarLogin();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionMenu != 0 && opcionMenu != 1);


    }

    private static void mostrarMenuOperadorMesaAyuda() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcionMenu;

        do {
            System.out.println("Seleccione la funcionalidad a ejecutar");
            System.out.println("0- Volver al Login");
            System.out.println("1- Cargar Reporte de Incidencia Nuevo");
            System.out.println("2- Modificar Reporte de Incidencia ");

            opcionMenu = scanner.nextInt();

            switch (opcionMenu) {
                case 1:
                    cargarReporteIncidenciaOperadorMesaAyuda();
                    break;
                case 2:
                    modificarReporteIncidenciaOperadorMesaAyuda();
                    break;
                case 0:
                    System.out.println("Saliendo del menú Operador Mesa Ayuda.");
                    mostrarLogin();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionMenu != 0 && opcionMenu != 1 && opcionMenu != 2);
    }

    //test



    private static void mostrarMenuAdmin() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcionMenu;

        do {
            System.out.println("Seleccione la funcionalidad a ejecutar");
            System.out.println("0- Volver al login");
            System.out.println("1- Administrar Clientes");
            System.out.println("2- Administrar Tecnicos");
            System.out.println("3- Administrar Servicios");
            System.out.println("4- Administrar Especialidades");
            System.out.println("5- Administrar Operadores");
            System.out.println("6- Administrar Reportes de Incidencia");
            System.out.println("7- Reportes de incidente por tecnico por dias ");
            System.out.println("8- Reportes de incidente resueltos por especialidad");
            System.out.println("9- Mostrar tecnico mas eficiente");
            System.out.println("10- Mostrar tecnico mas eficiente entre fechas");
            System.out.println("11- Mostrar tecnico menos eficiente");
            System.out.println("12- Mostrar tecnico menos eficiente entre fechas");
            System.out.println("13- Gestionar Reportes de Incidencia TECNICO");
            System.out.println("14- Cargar Reporte de Incidencia Nuevo OPERADORMESA");
            System.out.println("15- Modificar Reporte de Incidencia OPERDAROMESA");
            System.out.println("16- Administrar Operadores Recursos");
            System.out.println("17- Administrar Operadores Comerciales");

            opcionMenu = scanner.nextInt();

            switch (opcionMenu) {
                case 1:
                    gestionarClientes();
                    break;
                case 2:
                    gestionarTecnicos();
                    break;
                case 3:
                    gestionarServicios();
                    break;
                case 4:
                    gestionarEspecialidades();
                    break;
                case 5:
                    gestionarOperadores();
                    break;
                case 6:
                    gestionarReportesIncidencia();
                    break;
                case 7:
                    generarReporteTecnicoFecha();
                    break;
                case 8:
                    generarReportesResueltosPorEspecialidad();
                    break;
                case 9:
                    mostrarTecnicoMasEficiente();
                    break;
                case 10:
                    mostrarTecnicoMasEficienteEntreFechas();
                    break;
                case 11:
                    mostrarTecnicoMenosEficiente();
                    break;
                case 12:
                    mostrarTecnicoMenosEficienteEntreFechas();
                    break;
                case 13:
                    gestionarReportesTecnico();
                    break;
                case 14:
                    cargarReporteIncidenciaOperadorMesaAyuda();
                    break;
                case 15:
                    modificarReporteIncidenciaOperadorMesaAyuda();
                    break;
                case 16:
                    gestionarOperadoresRecursos();
                    break;
                case 17:
                    gestionarOperadoresComerciales();
                    break;
                case 0:
                    System.out.println("Saliendo del menú administrativo.");
                    mostrarLogin();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }


        } while (opcionMenu != 0 && opcionMenu != 1 && opcionMenu != 2 && opcionMenu != 3 && opcionMenu != 4 && opcionMenu != 5 && opcionMenu != 6 && opcionMenu != 7 && opcionMenu != 8 && opcionMenu != 9 && opcionMenu != 10 && opcionMenu != 11 && opcionMenu != 12 && opcionMenu != 13 && opcionMenu != 14 && opcionMenu != 15 && opcionMenu != 16 && opcionMenu != 17);

        String opcionMenu1 = scanner.next();
        System.out.println("Para regresar al menu de administrador presione cualquier tecla.");

        switch (opcionMenu1) {

            default ->
                mostrarMenuAdmin();
        }

        mostrarMenuAdmin();

    }

    private static void gestionarReportesIncidente() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcionMenu;
        opcionMenu = scanner.nextInt();

        System.out.println("1- Gestionar reportes de incidencia");
        System.out.println("2- Cargar reporte de incidencia nuevo - Operador Mesa");
        System.out.println("3- Modificar reporte de incidencia - Operador Mesa");
        switch (opcionMenu) {
            case 1:
                gestionarReportesIncidencia();
                break;
            case 2:
                cargarReporteIncidenciaOperadorMesaAyuda();
                break;
            case 3:
                modificarReporteIncidenciaOperadorMesaAyuda();
                break;
            default:
                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
        }

    }

    private static void mostrarEficienciaTecnicos() {
        Scanner scanner = new Scanner(System.in);
        int opcionMenu;
        opcionMenu = scanner.nextInt();

        System.out.println("1- Mostrar tecnico mas eficiente");
        System.out.println("2- Mostrar tecnico menos eficiente");

        switch (opcionMenu) {
            case 1:
                mostrarTecnicoMasEficiente();
                break;
            case 2:
                mostrarTecnicoMenosEficiente();
                break;
            default:
                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
        }

        mostrarTecnicoMasEficiente();

    }

    private static void mostrarTecnicoMenosEficiente() {

        ReporteIncidenciaVista vistaReporteIncidencia = new ReporteIncidenciaVista();
        vistaReporteIncidencia.reporteEstadisticoTecnicoMenosEficiente();

    }

    private static void mostrarTecnicoMasEficiente() {

        ReporteIncidenciaVista vistaReporteIncidencia = new ReporteIncidenciaVista();
        vistaReporteIncidencia.reporteEstadisticoTecnicoMasEficiente();

    }

    private static void mostrarTecnicoMasEficienteEntreFechas() {

        ReporteIncidenciaVista vistaReportrIncidencia = new ReporteIncidenciaVista();
        vistaReportrIncidencia.obtenerTecnicoMasEficienteEntreFechas();

    }

    private static void mostrarTecnicoMenosEficienteEntreFechas() {

        ReporteIncidenciaVista vistaReportrIncidencia = new ReporteIncidenciaVista();
        vistaReportrIncidencia.obtenerTecnicoMenosEficienteEntreFechas();

    }

    private static void generarReportesResueltosPorEspecialidad() {

        EspecialidadVista especialidadVista = new EspecialidadVista();
        Especialidad especialidadSeleccionada = especialidadVista.mostrarMenuSeleccionEspecialidad();

        ReporteIncidenciaVista reporteIncidenciaVista = new ReporteIncidenciaVista();
        reporteIncidenciaVista.iniciarGestionReportesIncidenciaResueltosPorEspecialidad(especialidadSeleccionada);

    }

    private static void generarReporteTecnicoFecha() {

        GestorReporteIncidencia gestorReporteIncidencia = new GestorReporteIncidencia();
        ReporteIncidenciaVista vistaReporteIncidencia = new ReporteIncidenciaVista();
        vistaReporteIncidencia.generarReportePorTecnicoPorDia();


    }

    private static void modificarReporteIncidenciaOperadorMesaAyuda() throws Exception {

        GestorReporteIncidencia gestorReporteIncidencia = new GestorReporteIncidencia();
        ReporteIncidenciaVista vistaReporteIncidencia = new ReporteIncidenciaVista();
        List<ReporteIncidencia> reportesIncidencias = gestorReporteIncidencia.obtenerTodosLosReportes();
        ReporteIncidencia reporteIncidencia = vistaReporteIncidencia.seleccionarReporteOperador(reportesIncidencias);
        ReporteIncidencia reporteIncidencia1 = vistaReporteIncidencia.modificarReporteIncidencia(reporteIncidencia);
        gestorReporteIncidencia.guardar(reporteIncidencia1);

    }

    private static void cargarReporteIncidenciaOperadorMesaAyuda() throws Exception {

        GestorReporteIncidencia gestorReporteIncidencia = new GestorReporteIncidencia();
        ReporteIncidenciaVista vistaReporteIncidencia = new ReporteIncidenciaVista();
        ReporteIncidencia reporteIncidencia = vistaReporteIncidencia.cargarReporteIncidenciaNuevo();
        gestorReporteIncidencia.guardar(reporteIncidencia);

        //Enviar notificacion al tecnico en el reporte para informarle sobre el incidente.

    }

    private static void gestionarReportesIncidencia() throws Exception {

        GestorReporteIncidencia gestorReporteIncidencia = new GestorReporteIncidencia();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el id del reporte de incidencia");
        Long id = scanner.nextLong();

        ReporteIncidencia reporteIncidencia = gestorReporteIncidencia.getReporteIncidenciaPorId(id);
        ReporteIncidenciaVista vistaReporteIncidencia = new ReporteIncidenciaVista();

        if (reporteIncidencia == null) {

            System.out.println("El reporte buscado no existe. Proceda a cargar uno nuevo.");
            cargarReporteIncidenciaOperadorMesaAyuda();
            System.out.println("Volviendo al Login");
            mostrarLogin();

        } else {

            System.out.println("Se encontro el reporte con id:  " + reporteIncidencia.getId() + " Para modificar ingrese U, Para eliminar E, Para volver al login presione cualquier otra tecla.");
            String accion = scanner.next();

            switch (accion.toUpperCase()) {

                case "U":
                    reporteIncidencia = vistaReporteIncidencia.modificarReporteIncidencia(reporteIncidencia);
                    break;
                case "E":
                    gestorReporteIncidencia.eliminar(reporteIncidencia);
                    break;
                default:
                    System.out.println("Opción no válida. Regresando al login.");
                    mostrarLogin();
            }

        }

    }

    private static void gestionarOperadoresRecursos() throws Exception {

        GestorOperadorRecursos gestorOperadorRecursos = new GestorOperadorRecursos();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el legajo del operador de Recursos Humanos");
        int legajo = scanner.nextInt();

        OperadorRecursos operadorRecursos = gestorOperadorRecursos.getOperadorRecursosXLegajo(legajo);
        OperadorRecursosVista vistaOperadorRecursos = new OperadorRecursosVista();

        if (operadorRecursos == null) {

            System.out.println("El operador de Recursos Humanos buscado no existe. Proceda a cargar uno nuevo");
            operadorRecursos = vistaOperadorRecursos.cargarOperadorRecursosNuevo(legajo);
            gestorOperadorRecursos.guardar(operadorRecursos);
            System.out.println("Volviendo al Login");
            mostrarLogin();

        } else {

            System.out.println("El operador de Recursos Humanos " + operadorRecursos.getNombreCompleto() + " ya existe. Para modificar ingrese U, Para eliminar ingrese E, Para volver al login ingrese cualquier otra tecla.");
            String accion = scanner.next();

            switch (accion.toUpperCase()) {

                case "U":
                    operadorRecursos = vistaOperadorRecursos.modificarOperadorRecursos(operadorRecursos, legajo);
                    gestorOperadorRecursos.guardar(operadorRecursos);
                    break;
                case "E":
                    gestorOperadorRecursos.eliminar(operadorRecursos);
                    break;
                default:
                    System.out.println("Opción no válida. Regresando al login.");
                    mostrarLogin();
            }

        }

    }

    private static void gestionarOperadoresComerciales() throws Exception {

        GestorOperadorComercial gestorOperadorComercial = new GestorOperadorComercial();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el legajo del operador Comercial");
        int legajo = scanner.nextInt();

        OperadorComercial operadorComercial = gestorOperadorComercial.getOperadorComercialXLegajo(legajo);
        OperadorComercialVista vistaOperadorComercial = new OperadorComercialVista();

        if (operadorComercial == null) {

            System.out.println("El operador Comercial buscado no existe. Proceda a cargar uno nuevo");
            operadorComercial = vistaOperadorComercial.cargarOperadorComercialNuevo(legajo);
            gestorOperadorComercial.guardar(operadorComercial);
            System.out.println("Volviendo al Login");
            mostrarLogin();

        } else {

            System.out.println("El operador Comercial " + operadorComercial.getNombreCompleto() + " ya existe. Para modificar ingrese U, Para eliminar ingrese E, Para volver al login ingrese cualquier otra tecla.");
            String accion = scanner.next();

            switch (accion.toUpperCase()) {

                case "U":
                    operadorComercial = vistaOperadorComercial.modificarOperadorComercial(operadorComercial, legajo);
                    gestorOperadorComercial.guardar(operadorComercial);
                    break;
                case "E":
                    gestorOperadorComercial.eliminar(operadorComercial);
                    break;
                default:
                    System.out.println("Opción no válida. Regresando al login.");
                    mostrarLogin();
            }

        }

    }

    private static void gestionarOperadores() throws Exception {

        GestorOperadorMesaAyuda gestorOperadorMesaAyuda = new GestorOperadorMesaAyuda();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el legajo del operador de mesa de ayuda");
        int legajo = scanner.nextInt();

        OperadorMesaAyuda operadorMesaAyuda = gestorOperadorMesaAyuda.getOperadorMesaAyudaXLegajo(legajo);
        OperadorMesaAyudaVista vistaOperadorMesaAyuda = new OperadorMesaAyudaVista();

        if (operadorMesaAyuda == null) {

            System.out.println("El operador de mesa de ayuda buscado no existe. Proceda a cargar uno nuevo");
            operadorMesaAyuda = vistaOperadorMesaAyuda.cargarOperadorMesaAyudaNuevo(legajo);
            gestorOperadorMesaAyuda.guardar(operadorMesaAyuda);
            System.out.println("Volviendo al Login");
            mostrarLogin();

        } else {

            System.out.println("El operador de mesa de ayuda " + operadorMesaAyuda.getNombreCompleto() + " ya existe. Para modificar ingrese U, Para eliminar ingrese E, Para volver al login ingrese cualquier otra tecla.");
            String accion = scanner.next();

            switch (accion.toUpperCase()) {

                case "U":
                    operadorMesaAyuda = vistaOperadorMesaAyuda.modificarOperadorMesaAyuda(operadorMesaAyuda, legajo);
                    gestorOperadorMesaAyuda.guardar(operadorMesaAyuda);
                    break;
                case "E":
                    gestorOperadorMesaAyuda.eliminar(operadorMesaAyuda);
                    break;
                default:
                    System.out.println("Opción no válida. Regresando al login.");
                    mostrarLogin();
            }

        }

    }

    private static void gestionarServicios() throws Exception {

        GestorServicio gestorServicio = new GestorServicio();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el id del servicio:");
        Long id = scanner.nextLong();

        Servicio servicio = gestorServicio.getServicioXId(id);
        ServicioVista vistaServicio = new ServicioVista();

        if (servicio == null) {

            System.out.println("El servicio buscado no existe. Proceda a cargar uno nuevo");
            servicio = vistaServicio.cargarServicioNuevo();
            gestorServicio.guardar(servicio);
            System.out.println("Volviendo al Login");
            mostrarLogin();

        } else {

            System.out.println("El servicio " + servicio.getDenominacion() + " " + servicio.getId() + " ya existe. Para modificar ingrese U, Para eliminar ingrese E, Para volver al login ingrese cualquier otra tecla.");
            String accion = scanner.next();

            switch (accion.toUpperCase()) {

                case "U":
                    servicio = vistaServicio.modificarServicio(servicio);
                    gestorServicio.guardar(servicio);
                    break;
                case "E":
                    gestorServicio.eliminar(servicio);
                    break;
                default:
                    System.out.println("Opcion no valida. Regresando al login");
                    mostrarLogin();
            }

        }

    }

    private static void gestionarEspecialidades() throws Exception {

        GestorEspecialidad gestorEspecialidad = new GestorEspecialidad();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el id de la especialidad:");
        Long id = scanner.nextLong();

        Especialidad especialidad = gestorEspecialidad.getEspecialidadXId(id);
        EspecialidadVista vistaEspecialidad = new EspecialidadVista();

        if (especialidad == null) {

            System.out.println("La especialidad buscada no existe. Proceda a cargar una nueva");
            especialidad = vistaEspecialidad.cargarEspecialidadNueva();
            gestorEspecialidad.guardar(especialidad);
            System.out.println("Volviendo al Login");
            mostrarLogin();

        } else {

            System.out.println("La especialidad " + especialidad.getDenominacion() + " " + especialidad.getId() + " ya existe. Para modificar ingrese U, Para eliminar ingrese E, Para volver al login ingrese cualquier otra tecla.");
            String accion = scanner.next();

            switch (accion.toUpperCase()) {

                case "U":
                    especialidad = vistaEspecialidad.modificarEspecialidad(especialidad);
                    gestorEspecialidad.guardar(especialidad);
                    break;
                case "E":
                    gestorEspecialidad.eliminar(especialidad);
                    break;
                default:
                    System.out.println("Opción no válida.");
                    mostrarLogin();
            }

        }

    }

    private static void gestionarClientes() throws Exception {
        GestorCliente gestorCliente = new GestorCliente();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el cuit del cliente:");
        Long cuit = scanner.nextLong();

        Cliente cliente = gestorCliente.getClienteXCUIT(cuit);
        ClienteVista vistaCliente = new ClienteVista();

        if (cliente == null) {
            System.out.println("El cliente buscado no existe. Proceda a cargar uno nuevo");
            cliente = vistaCliente.cargarClienteNuevo();
            cargarServiciosCliente(cliente);
            gestorCliente.guardar(cliente);
            System.out.println("Volviendo al Login");
            mostrarLogin();

        } else {

            System.out.println("El cliente " + cliente.getRazonSocial() + " ya existe. Para modificar ingrese U, si desea eliminar ingrese E, si desea agregar servicios ingrese I, Para volver al login ingrese cualquier otra tecla.");
            String accion = scanner.next();

            switch (accion.toUpperCase()) {

                case "U":
                    cliente = vistaCliente.modificarCliente(cliente);
                    gestorCliente.guardar(cliente);
                break;
                case "E":
                    gestorCliente.eliminar(cliente);
                break;
                case "I":
                    cargarServiciosCliente(cliente);
                    gestorCliente.guardar(cliente);
                default:
                    mostrarLogin();
            }
        }
    }

    private static void cargarServiciosCliente(Cliente cliente) throws Exception {

        GestorServicio gestorServicio = new GestorServicio();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Lista de Servicios");
        List<Servicio> servicios = gestorServicio.listar(Servicio.class);
        servicios.forEach(servicio -> System.out.println(servicio.getId() + " " + servicio.getDenominacion()));

        List<Long> idServicios = new ArrayList<>();

        while (true) {

            System.out.println("Seleccione el servicio a asignar al cliente");
            long idServicio = scanner.nextLong();

            if (idServicios.contains(idServicio)) {

                System.out.println("El servicio seleccionado ya fue asignado, elija otro");
                continue;

            }

            idServicios.add(idServicio);
            Servicio servicio = (Servicio) gestorServicio.buscarPorId(Servicio.class, idServicio);

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

            String continuar = scanner.next();

            if (!continuar.equalsIgnoreCase("S")) {
                break;
            }

        }

    }

    private static void gestionarTecnicos() throws Exception {

        GestorTecnico gestorTecnico = new GestorTecnico();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el legajo del técnico:");
        int legajo = scanner.nextInt();

        Tecnico tecnico = gestorTecnico.getTecnicoXLegajo(legajo);
        TecnicoVista vistaTecnico = new TecnicoVista();

        if (tecnico == null) {
            System.out.println("El técnico buscado no existe. Proceda a cargar uno nuevo");
            tecnico = vistaTecnico.cargarTecnicoNuevo(legajo);
            cargarEspecialidadesTecnico(tecnico);
            gestorTecnico.guardar(tecnico);
            System.out.println("Volviendo al Login");
            mostrarLogin();

        } else {
            System.out.println("El técnico " + tecnico.getApellido() + " " + tecnico.getNombre() + " ya existe. Para modificar ingrese U, Para agregar especialidad ingrese I, si desea eliminar ingrese E, Para volver al login ingrese cualquier otra tecla.");
            String accion = scanner.next();

            switch (accion.toUpperCase()) {
                case "U":
                    tecnico = vistaTecnico.modificarTecnico(tecnico, legajo);
                    gestorTecnico.guardar(tecnico);
                    break;
                case "E":
                    gestorTecnico.eliminar(tecnico);
                    break;
                case "I":
                    cargarEspecialidadesTecnico(tecnico);
                    gestorTecnico.guardar(tecnico);
                    break;
                default:
                    System.out.println("Opción no válida. Regresando al login.");
                    mostrarLogin();
            }
        }
    }

    private static void cargarEspecialidadesTecnico(Tecnico tecnico) throws Exception {
        GestorEspecialidad gestorEspecialidad = new GestorEspecialidad();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Lista de Especialidades");
        List<Especialidad> especialidades = gestorEspecialidad.listar(Especialidad.class);
        especialidades.forEach(esp -> System.out.println(esp.getId() + " " + esp.getDenominacion()));

        List<Long> idEspecialidades = new ArrayList<>();

        while (true) {
            System.out.println("Seleccione la especialidad a asignar al técnico");
            long idEspecialidad = scanner.nextLong();

            if (idEspecialidades.contains(idEspecialidad)) {
                System.out.println("La especialidad seleccionada ya fue asignada, elija otra");
                continue;
            }

            idEspecialidades.add(idEspecialidad);
            Especialidad especialidad = (Especialidad) gestorEspecialidad.buscarPorId(Especialidad.class, idEspecialidad);

            if (especialidad != null) {
                TecnicoEspecialidad te = new TecnicoEspecialidad();
                te.setEspecialidad(especialidad);
                te.setTecnico(tecnico);
                tecnico.addEspecialidad(te);
                System.out.println("La especialidad " + especialidad.getDenominacion() + " fue agregada exitosamente");
            } else {
                System.out.println("El id de la especialidad ingresada no existe");
            }

            System.out.println("Desea agregar otra especialidad?? S/N");

            String continuar = scanner.next();

            if (!continuar.equalsIgnoreCase("S")) {
                break;
            }
        }
    }

    public static void obtenerConexion() {
        Connection con = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");//mysql 5
            Class.forName("com.mysql.cj.jdbc.Driver");//mysql 8
            //jdbc:mysql://localhost:3306/database //mysql 5
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/argentina_programa?useTimezone=true&serverTimezone=UTC", "root", "123456");
            if (con != null) {
                System.out.println("CONECTADO");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generarSet() throws Exception {

        GestorServicio gestorServicio = new GestorServicio();

        Servicio servicioSoporteTecnicoHardware = new Servicio("SoporteTecnicoHardware");
        Servicio servicioSoporteTecnicoSoftware = new Servicio("SoporteTecnicoSoftware");
        Servicio servicioSoporteTecnicoWindows = new Servicio("SoporteTecnicoWindows");
        Servicio servicioSoporteTecnicoLinux = new Servicio("SoporteTecnicoLinux");
        Servicio servicioSoporteTecnicoMacOs = new Servicio("SoporteTecnicoMacOS");
        Servicio servicioSoporteTecnicoRedes = new Servicio("SoporteTecnicoRedes");



        List<Servicio> servicios = new ArrayList<Servicio>();
        servicios.add(servicioSoporteTecnicoHardware);
        servicios.add(servicioSoporteTecnicoSoftware);
        servicios.add(servicioSoporteTecnicoWindows);
        servicios.add(servicioSoporteTecnicoLinux);
        servicios.add(servicioSoporteTecnicoMacOs);
        servicios.add(servicioSoporteTecnicoRedes);

        GestorEspecialidad gestorEspecialidad = new GestorEspecialidad();

        Especialidad especialidadHardaware = new Especialidad("Hardware");
        Especialidad especialidadSoftware = new Especialidad("Software");
        Especialidad especialidadWindows = new Especialidad("Windows");
        Especialidad especialidadLinux = new Especialidad("Linux");
        Especialidad especialidadMacOs = new Especialidad("MacOs");
        Especialidad especialidadRedes = new Especialidad("Redes");



        List<Especialidad> especialidades = new ArrayList<Especialidad>();
        especialidades.add(especialidadHardaware);
        especialidades.add(especialidadSoftware);
        especialidades.add(especialidadWindows);
        especialidades.add(especialidadLinux);
        especialidades.add(especialidadMacOs);
        especialidades.add(especialidadRedes);

        DatosContacto datosContacto = new DatosContacto(444160, 15482910, "usuario@mail.com");
        GestorTecnico gestorTecnico = new GestorTecnico();





        Tecnico ramirezJuan = new Tecnico("Ramirez", "Juan", 11100, datosContacto);
        Tecnico vegaMilagros = new Tecnico("Vega", "Milagros", 11101, datosContacto);
        Tecnico lencinaNicolas = new Tecnico("Lencina", "Nicolas", 11102, datosContacto);
        Tecnico alonsoDavid = new Tecnico("Alonso", "David", 11103, datosContacto);
        Tecnico garciaPedro = new Tecnico("Garcia", "Pedro", 11104, datosContacto);
        Tecnico gonzalesJuan = new Tecnico("Gonzales", "Juan", 11105, datosContacto);


        List<TecnicoEspecialidad> tecnicoEspecialidades = new ArrayList<TecnicoEspecialidad>();
        TecnicoEspecialidad tecnicoEspecialidad = new TecnicoEspecialidad(ramirezJuan, especialidadHardaware);
        tecnicoEspecialidades.add(tecnicoEspecialidad);
        tecnicoEspecialidad = new TecnicoEspecialidad(ramirezJuan, especialidadSoftware);
        tecnicoEspecialidades.add(tecnicoEspecialidad);
        tecnicoEspecialidad = new TecnicoEspecialidad(ramirezJuan, especialidadRedes);
        tecnicoEspecialidades.add(tecnicoEspecialidad);
        tecnicoEspecialidad = new TecnicoEspecialidad(vegaMilagros, especialidadLinux);
        tecnicoEspecialidades.add(tecnicoEspecialidad);
        tecnicoEspecialidad = new TecnicoEspecialidad(vegaMilagros, especialidadWindows);
        tecnicoEspecialidades.add(tecnicoEspecialidad);
        tecnicoEspecialidad = new TecnicoEspecialidad(lencinaNicolas, especialidadMacOs);
        tecnicoEspecialidades.add(tecnicoEspecialidad);
        tecnicoEspecialidad = new TecnicoEspecialidad(alonsoDavid, especialidadSoftware);
        tecnicoEspecialidades.add(tecnicoEspecialidad);
        tecnicoEspecialidad = new TecnicoEspecialidad(garciaPedro, especialidadWindows);
        tecnicoEspecialidades.add(tecnicoEspecialidad);
        tecnicoEspecialidad = new TecnicoEspecialidad(gonzalesJuan, especialidadLinux);
        tecnicoEspecialidades.add(tecnicoEspecialidad);

        GestorCliente gestorCliente = new GestorCliente();


        Cliente ramirezJorge = new Cliente("Jorge Ramirez", 23232323L, datosContacto);
        Cliente martinezRicardo = new Cliente("Ricardo Martinez ", 24242424L, datosContacto);
        Cliente rodrigezMicaela = new Cliente("Micaela Rodrigez", 25252525L, datosContacto);
        Cliente ricardiLeandro = new Cliente("Leandro Ricardi", 26262626L, datosContacto);
        Cliente medinaRamon = new Cliente("Ramon Medina", 27272727L, datosContacto);
        Cliente shepardAna = new Cliente("Ana Shepard", 28282828L, datosContacto);

        List<ClienteServicio> clienteServicios = new ArrayList<ClienteServicio>();
        ClienteServicio clienteServicio = new ClienteServicio(ramirezJorge, servicioSoporteTecnicoHardware);
        clienteServicios.add(clienteServicio);
        clienteServicio =  new ClienteServicio(ramirezJorge, servicioSoporteTecnicoSoftware);
        clienteServicios.add(clienteServicio);
        clienteServicio =  new ClienteServicio(martinezRicardo, servicioSoporteTecnicoWindows);
        clienteServicios.add(clienteServicio);
        clienteServicio =  new ClienteServicio(martinezRicardo, servicioSoporteTecnicoSoftware);
        clienteServicios.add(clienteServicio);
        clienteServicio =  new ClienteServicio(rodrigezMicaela, servicioSoporteTecnicoLinux);
        clienteServicios.add(clienteServicio);
        clienteServicio =  new ClienteServicio(rodrigezMicaela, servicioSoporteTecnicoMacOs);
        clienteServicios.add(clienteServicio);
        clienteServicio =  new ClienteServicio(ricardiLeandro, servicioSoporteTecnicoRedes);
        clienteServicios.add(clienteServicio);
        clienteServicio =  new ClienteServicio(ricardiLeandro, servicioSoporteTecnicoWindows);
        clienteServicios.add(clienteServicio);
        clienteServicio =  new ClienteServicio(medinaRamon, servicioSoporteTecnicoHardware);
        clienteServicios.add(clienteServicio);
        clienteServicio =  new ClienteServicio(medinaRamon, servicioSoporteTecnicoSoftware);
        clienteServicios.add(clienteServicio);
        clienteServicio =  new ClienteServicio(shepardAna, servicioSoporteTecnicoHardware);
        clienteServicios.add(clienteServicio);
        clienteServicio =  new ClienteServicio(shepardAna, servicioSoporteTecnicoMacOs);
        clienteServicios.add(clienteServicio);

        GestorOperadorMesaAyuda gestorOperadorMesaAyuda = new GestorOperadorMesaAyuda();


        OperadorMesaAyuda perezCarlos = new OperadorMesaAyuda("Perez", "Carlos", 22200, datosContacto);

        OperadorMesaAyuda barrientosEduardo = new OperadorMesaAyuda("Barrientos", "Eduardo", 22201, datosContacto);
        OperadorMesaAyuda valdezCeleste = new OperadorMesaAyuda("Valdez", "Celeste", 22202, datosContacto);

        Calendar fechaAlta1 = new GregorianCalendar(2023, 9, 4);
        Calendar fechaAlta2 = new GregorianCalendar(2023, 9, 5);
        Calendar fechaAlta3 = new GregorianCalendar(2023, 9, 8);
        Calendar fechaAlta4 = new GregorianCalendar(2023, 9, 12);
        Calendar fechaAlta5 = new GregorianCalendar(2023, 9, 14);
        Calendar fechaAlta6 = new GregorianCalendar(2023, 9, 15);
        Calendar fechaAlta7 = new GregorianCalendar(2023, 9, 19);
        Calendar fechaAlta8 = new GregorianCalendar(2023, 9, 23);
        Calendar fechaAlta9 = new GregorianCalendar(2023, 9, 21);
        Calendar fechaAlta10 = new GregorianCalendar(2023, 9, 23);
        Calendar fechaAlta11 = new GregorianCalendar(2023, 9, 24);
        Calendar fechaAlta12 = new GregorianCalendar(2023, 9, 28);

        Calendar fechaPosibleResolucion1 = new GregorianCalendar(2023, 9, 5);
        Calendar fechaPosibleResolucion3 = new GregorianCalendar(2023, 9, 9);
        Calendar fechaPosibleResolucion6 = new GregorianCalendar(2023, 9, 16);
        Calendar fechaPosibleResolucion7 = new GregorianCalendar(2023, 9, 20);
        Calendar fechaPosibleResolucion8 = new GregorianCalendar(2023, 9, 24);

        GestorReporteIncidencia gestorReporteIncidencia = new GestorReporteIncidencia();


        ReporteIncidencia reporteIncidencia1 = new ReporteIncidencia(perezCarlos, shepardAna, servicioSoporteTecnicoHardware, ramirezJuan, "Placa de video no enciende.", "Complejo", 24, fechaAlta1.getTime(), fechaPosibleResolucion1.getTime(), "Test Observacion...", "Resuelto");
        ReporteIncidencia reporteIncidencia2 = new ReporteIncidencia(perezCarlos, shepardAna, servicioSoporteTecnicoMacOs, lencinaNicolas, "Sistema no inicia.", "Básico", 4, fechaAlta2.getTime(), fechaAlta2.getTime(), "Test Observacion...", "Resuelto");
        ReporteIncidencia reporteIncidencia3 = new ReporteIncidencia(perezCarlos, medinaRamon, servicioSoporteTecnicoHardware, ramirezJuan, "Placa de sonido no enciende.", "Complejo", 24, fechaAlta3.getTime(), fechaPosibleResolucion3.getTime(), "Test Observacion...", "Resuelto");
        ReporteIncidencia reporteIncidencia4 = new ReporteIncidencia(perezCarlos, medinaRamon, servicioSoporteTecnicoHardware, ramirezJuan, "PC no enciende.", "Intermedio", 8, fechaAlta4.getTime(), fechaAlta4.getTime(), "Test Observacion....", "Resuelto");
        ReporteIncidencia reporteIncidencia5 = new ReporteIncidencia(barrientosEduardo, ricardiLeandro, servicioSoporteTecnicoWindows, garciaPedro, "Sistema no inicia.", "Básico", 4, fechaAlta5.getTime(), fechaAlta5.getTime(), "Test Observacion...", "Resuelto");
        ReporteIncidencia reporteIncidencia6 = new ReporteIncidencia(barrientosEduardo, ricardiLeandro, servicioSoporteTecnicoRedes, ramirezJuan, "No funciona Switch.", "Intermedio", 8, fechaAlta6.getTime(), fechaPosibleResolucion6.getTime(), "Test Observacion...", "Resuelto");
        ReporteIncidencia reporteIncidencia7 = new ReporteIncidencia(barrientosEduardo, rodrigezMicaela, servicioSoporteTecnicoLinux, gonzalesJuan, "No inicia el sistema.", "Intermedio", 8, fechaAlta7.getTime(), fechaPosibleResolucion7.getTime(), "Test Observacion...", "Resuelto");
        ReporteIncidencia reporteIncidencia8 = new ReporteIncidencia(barrientosEduardo, rodrigezMicaela, servicioSoporteTecnicoMacOs, lencinaNicolas, "No actualiza el sistema.", "Básico", 4, fechaAlta8.getTime(), fechaPosibleResolucion8.getTime(), "Test Observacion...", "Resuelto");
        ReporteIncidencia reporteIncidencia9 = new ReporteIncidencia(valdezCeleste, martinezRicardo, servicioSoporteTecnicoWindows, vegaMilagros, "No actualiza el sistema.", "Básico", 4, fechaAlta9.getTime(), fechaAlta9.getTime(), "Test Observacion...", "Resuelto");
        ReporteIncidencia reporteIncidencia10 = new ReporteIncidencia(valdezCeleste, martinezRicardo, servicioSoporteTecnicoSoftware, alonsoDavid, "No inicia programa.", "Básico", 4, fechaAlta10.getTime(), fechaAlta10.getTime(), "Test Observacion...", "Resuelto");
        ReporteIncidencia reporteIncidencia11 = new ReporteIncidencia(valdezCeleste, ramirezJorge, servicioSoporteTecnicoHardware, ramirezJuan, "Disco duro no enciende.", "Intermedio", 8, fechaAlta11.getTime(), fechaAlta11.getTime(), "Test Observacion...", "Resuelto");
        ReporteIncidencia reporteIncidencia12 = new ReporteIncidencia(valdezCeleste, ramirezJorge, servicioSoporteTecnicoSoftware, alonsoDavid, "Programa no actualiza.", "Básico", 4, fechaAlta12.getTime(), fechaAlta12.getTime(), "Test Observacion...", "Resuelto");

        gestorServicio.guardar(servicioSoporteTecnicoHardware);
        gestorServicio.guardar(servicioSoporteTecnicoSoftware);
        gestorServicio.guardar(servicioSoporteTecnicoWindows);
        gestorServicio.guardar(servicioSoporteTecnicoLinux);
        gestorServicio.guardar(servicioSoporteTecnicoMacOs);
        gestorServicio.guardar(servicioSoporteTecnicoRedes);

        gestorEspecialidad.guardar(especialidadHardaware);
        gestorEspecialidad.guardar(especialidadSoftware);
        gestorEspecialidad.guardar(especialidadWindows);
        gestorEspecialidad.guardar(especialidadLinux);
        gestorEspecialidad.guardar(especialidadMacOs);
        gestorEspecialidad.guardar(especialidadRedes);

        gestorTecnico.guardar(ramirezJuan);
        gestorTecnico.guardar(vegaMilagros);
        gestorTecnico.guardar(lencinaNicolas);
        gestorTecnico.guardar(alonsoDavid);
        gestorTecnico.guardar(garciaPedro);
        gestorTecnico.guardar(gonzalesJuan);

        gestorCliente.guardar(ramirezJorge);
        gestorCliente.guardar(martinezRicardo);
        gestorCliente.guardar(shepardAna);
        gestorCliente.guardar(medinaRamon);
        gestorCliente.guardar(ricardiLeandro);
        gestorCliente.guardar(rodrigezMicaela);

        gestorOperadorMesaAyuda.guardar(perezCarlos);
        gestorOperadorMesaAyuda.guardar(barrientosEduardo);
        gestorOperadorMesaAyuda.guardar(valdezCeleste);

        gestorReporteIncidencia.guardar(reporteIncidencia1);
        gestorReporteIncidencia.guardar(reporteIncidencia2);
        gestorReporteIncidencia.guardar(reporteIncidencia3);
        gestorReporteIncidencia.guardar(reporteIncidencia4);
        gestorReporteIncidencia.guardar(reporteIncidencia5);
        gestorReporteIncidencia.guardar(reporteIncidencia6);
        gestorReporteIncidencia.guardar(reporteIncidencia7);
        gestorReporteIncidencia.guardar(reporteIncidencia8);
        gestorReporteIncidencia.guardar(reporteIncidencia9);
        gestorReporteIncidencia.guardar(reporteIncidencia10);
        gestorReporteIncidencia.guardar(reporteIncidencia11);
        gestorReporteIncidencia.guardar(reporteIncidencia12);

    }
}