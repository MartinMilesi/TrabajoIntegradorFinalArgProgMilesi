package vista;

import controlador.GestorCliente;
import controlador.GestorOperadorMesaAyuda;
import controlador.GestorReporteIncidencia;
import controlador.GestorTecnico;
import modelo.*;

import javax.swing.plaf.PanelUI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReporteIncidenciaVista {

    private final Scanner scanner;
    private final GestorCliente gestorCliente;
    private final GestorReporteIncidencia gestorReporteIncidencia;
    private final GestorOperadorMesaAyuda gestorOperadorMesaAyuda;
    private final GestorTecnico gestorTecnico;

    public ReporteIncidenciaVista() {
        this.scanner = new Scanner(System.in);
        this.gestorCliente = new GestorCliente();
        this.gestorReporteIncidencia = new GestorReporteIncidencia();
        this.gestorOperadorMesaAyuda = new GestorOperadorMesaAyuda();
        this.gestorTecnico = new GestorTecnico();
    }


    public void iniciarGestionReportes() throws Exception {
        int legajo = obtenerLegajo();
        Tecnico tecnico = obtenerTecnico(legajo);

        if (tecnico != null) {
            List<ReporteIncidencia> reportesTecnico = obtenerReportesTecnico(tecnico);
            mostrarReportesSeleccionables(reportesTecnico);

            int opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= reportesTecnico.size()) {
                ReporteIncidencia reporteSeleccionado = reportesTecnico.get(opcion - 1);
                gestionarReporte(reporteSeleccionado);
            } else if (opcion == 0) {
                System.out.println("Saliendo del programa.");
            } else {
                System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } else {
            System.out.println("No se encontró el técnico con el legajo proporcionado.");
        }
    }

    public void obtenerTecnicoMasEficienteEntreFechas() {

        Date fechaInicio = obtenerFecha("Ingrese la fecha de inicio (Formato: yyyy-MM-dd): ");
        Date fechaFin = obtenerFecha("Ingrese la fecha de fin (Formato: yyyy-MM-dd): ");
        Tecnico tecnico = gestorReporteIncidencia.obtenerTecnicoMasEficienteEntreFechas(fechaInicio, fechaFin);
        long numeroReportesResueltos = gestorReporteIncidencia.contarReportesResueltosPorTecnico(tecnico);

        System.out.println("Entre las fechas:" + fechaInicio.toString() + " y " + fechaFin.toString() + " El tecnico mas eficiente es: " + tecnico.getNombreCompleto() + ", Incidentes resueltos: " + numeroReportesResueltos);

    }

    public void obtenerTecnicoMenosEficienteEntreFechas() {

        Date fechaInicio = obtenerFecha("Ingrese la fecha de inicio (Formato: yyyy-MM-dd): ");
        Date fechaFin = obtenerFecha("Ingrese la fecha de fin (Formato: yyyy-MM-dd): ");
        Tecnico tecnico = gestorReporteIncidencia.obtenerTecnicoMenosEficienteEntreFechas(fechaInicio, fechaFin);
        long numeroReportesResueltos = gestorReporteIncidencia.contarReportesResueltosPorTecnico(tecnico);

        System.out.println("Entre las fechas:" + fechaInicio.toString() + " y " + fechaFin.toString() + " El tecnico menos eficiente es: " + tecnico.getNombreCompleto() + ", Incidentes resueltos: " + numeroReportesResueltos);

    }

    private int obtenerLegajo() {
        System.out.println("Ingrese su legajo: ");
        return scanner.nextInt();
    }

    private Tecnico obtenerTecnico(int legajo) {
        return gestorTecnico.getTecnicoXLegajo(legajo);
    }

    private List<ReporteIncidencia> obtenerReportesTecnico(Tecnico tecnico) {
        return gestorReporteIncidencia.obtenerReportesPorTecnico(tecnico);
    }

    private void mostrarReportesSeleccionables(List<ReporteIncidencia> reportes) {
        System.out.println("Seleccione el número del reporte para editar (o 0 para salir):");
        for (int i = 0; i < reportes.size(); i++) {
            System.out.println((i + 1) + "- " + obtenerInfoReporte(reportes.get(i)));
        }
    }

    public void reporteEstadisticoTecnicoMenosEficiente () {

        Tecnico tecnicoIneficiente = gestorReporteIncidencia.obtenerTecnicoMenosEficiente();
        long numeroReportesResueltos = gestorReporteIncidencia.contarReportesResueltosPorTecnico(tecnicoIneficiente);
        System.out.println("El tecnico menos eficiente es: " + tecnicoIneficiente.getNombreCompleto() + ", Incidentes resueltos: " + numeroReportesResueltos);

    }

    public void reporteEstadisticoTecnicoMasEficiente () {

        Tecnico tecnicoEficiente = gestorReporteIncidencia.obtenerTecnicoMasEficiente();
        long numeroReportesResueltos = gestorReporteIncidencia.contarReportesResueltosPorTecnico(tecnicoEficiente);
        System.out.println("El tecnico mas eficiente es: " + tecnicoEficiente.getNombreCompleto() + ", Incidentes resueltos: " + numeroReportesResueltos);

    }

    private String obtenerInfoReporte(ReporteIncidencia reporte) {
        return "Cliente: " + reporte.getCliente().getRazonSocial() +
                ", Descripcion: " + reporte.getDescripcionProblema() +
                ", Observaciones: " + reporte.getObservacionesTecnico() +
                ", Estado: " + reporte.getEstado();
    }

    private void gestionarReporte(ReporteIncidencia reporte) throws Exception {
        // Doy la opción de setear el estado de ese reporte a una de sus tres opciones
        System.out.println("Seleccione el nuevo estado del reporte:");
        System.out.println("1. Pendiente");
        System.out.println("2. En proceso");
        System.out.println("3. Resuelto");

        int opcionEstado = scanner.nextInt();

        switch (opcionEstado) {
            case 1:
                reporte.setEstado("Pendiente");
                break;
            case 2:
                reporte.setEstado("En proceso");
                break;
            case 3:
                reporte.setEstado("Resuelto");
                break;
            default:
                System.out.println("Opción no válida. Se mantendrá el estado actual.");
        }

        // Doy la opción de ingresar observaciones del técnico al reporte
        scanner.nextLine();  // Consumir el salto de línea pendiente
        System.out.println("Ingrese las observaciones del técnico para el reporte:");
        String observaciones = scanner.nextLine();
        reporte.setObservacionesTecnico(observaciones);

        // Guardar los cambios en la base de datos.
        gestorReporteIncidencia.guardar(reporte);
    }

    public void iniciarGestionReportesIncidenciaResueltosPorEspecialidad (Especialidad especialidadSeleccionada) {

       //Con la especialidadSeleccionada obtengo una lista de reportes de incidencia resueltos por especialidad.
        List<ReporteIncidencia> reportesIncidenciaResueltosPorEspecialidad = gestorReporteIncidencia.obtenerReportesResueltosPorEspecialidad(especialidadSeleccionada);
        //Imprimo esa lista en un menu seleccionable que permita mostrar los detalles de reporte de incidente seleccionado.
        mostrarMenuSeleccionReporte(reportesIncidenciaResueltosPorEspecialidad);

    }
    public void mostrarMenuSeleccionReporte(List<ReporteIncidencia> reportes) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione el número del informe para ver más detalles (o 0 para salir):");

        for (int i = 0; i < reportes.size(); i++) {
            System.out.println((i + 1) + "- " + "Cliente: " + reportes.get(i).getCliente().getRazonSocial() +
                    ", Descripcion: " + reportes.get(i).getDescripcionProblema() +
                    ", Observaciones: " + reportes.get(i).getObservacionesTecnico() +
                    ", Estado: " + reportes.get(i).getEstado());
        }

        int opcion = scanner.nextInt();

        if (opcion >= 1 && opcion <= reportes.size()) {
            ReporteIncidencia reporteSeleccionado = reportes.get(opcion - 1);
            mostrarDetallesDelReporte(reporteSeleccionado);
        } else if (opcion == 0) {
            System.out.println("Saliendo del programa.");
        } else {
            System.out.println("Opción no válida. Inténtelo de nuevo.");
        }
    }

    public ReporteIncidencia cargarReporteIncidenciaNuevo() {

        ReporteIncidencia reporteIncidencia = new ReporteIncidencia();

        //Seteamos la fecha de alta automaticamente.
        setFechaAlta(reporteIncidencia);
        //Seteamos el cliente que buscamos con el cuit.
        setCliente(reporteIncidencia);
        //Seleccionamos el servicio y lo seteamos.
        setServicio(reporteIncidencia);
        //Ingresamos la descripcion del problema y la seteamos.
        setDescripcionProblema(reporteIncidencia);
        //Seleccionamos el tipo de problema.
        setTipoProblema(reporteIncidencia);
        //Seteamos automaticamente el tiempo estimado segun el tipo de problema.
        setTiempoEstimado(reporteIncidencia);
        //Seteamos automaticamente la fecha de posible resolucion segun el tiempo estimado.
        setFechaPosibleResolucion(reporteIncidencia);
        //Ingresamos el legajo del operador de mesa (de nuevo). para setearlo
        setOperador(reporteIncidencia);
        //Seleccionamos el tecnico de la lista.
        setTecnico(reporteIncidencia);
        //El estado inicial es siempre pendiente cuando se crea el reporte.
        setEstadoInicialReporte(reporteIncidencia);
        //Es un campo en blanco, setear desde menu tecnico.
        setObservacionesTecnico(reporteIncidencia);

        return reporteIncidencia;
    }

    public ReporteIncidencia modificarReporteIncidencia (ReporteIncidencia reporteIncidencia) {

        setCliente(reporteIncidencia);
        setServicio(reporteIncidencia);
        setDescripcionProblema(reporteIncidencia);
        setTipoProblema(reporteIncidencia);
        setTiempoEstimado(reporteIncidencia);
        setFechaPosibleResolucion(reporteIncidencia);
        setOperador(reporteIncidencia);
        setTecnico(reporteIncidencia);

        return reporteIncidencia;
    }

    public void generarReportePorTecnicoPorDia() {

        Scanner scanner = new Scanner(System.in);
        GestorReporteIncidencia gestorReporteIncidencia = new GestorReporteIncidencia();
        GestorTecnico gestorTecnico = new GestorTecnico();
        TecnicoVista vistaTecnico = new TecnicoVista();

        System.out.println("Seleccione el técnico para generar el reporte:");
        List<Tecnico> tecnicos = gestorTecnico.obtenerTodosLosTecnicos();

        Tecnico tecnico = vistaTecnico.seleccionarTecnico(tecnicos);

        if (tecnico != null) {

            // Obtener el rango de fechas para el reporte
            Date fechaInicio = obtenerFecha("Ingrese la fecha de inicio para el reporte (Formato: yyyy-MM-dd): ");
            Date fechaFin = obtenerFecha("Ingrese la fecha de fin para el reporte (Formato: yyyy-MM-dd): ");

            // Obtener los reportes de incidencia para el técnico en el rango de fechas especificado
            List<ReporteIncidencia> reportes = gestorReporteIncidencia.obtenerReportesPorTecnicoYRangoFechas(tecnico, fechaInicio, fechaFin);

            // Mostrar el reporte
            //mostrarReporte(reportes);

            //Seleccionar reporte

            seleccionarReporte(reportes);

        }

    }

    public void modificarReporteIncidenciaOperadorMesa() {

        GestorReporteIncidencia gestorReporteIncidencia = new GestorReporteIncidencia();
        List<ReporteIncidencia> reportesIncidencias = gestorReporteIncidencia.obtenerTodosLosReportes();
        ReporteIncidencia reporteIncidencia = seleccionarReporteOperador(reportesIncidencias);
        if (reporteIncidencia != null) {

            modificarReporteIncidencia(reporteIncidencia);

        } else {

            System.out.println("El reporte es nulo");

        }
    }

    private static void mostrarReporte(List<ReporteIncidencia> reportes) {
        System.out.println("Informe de Incidentes Resueltos por Especialidad:");
        for (int i = 0; i < reportes.size(); i++) {
            ReporteIncidencia reporte = reportes.get(i);
            System.out.println((i + 1) + ". ID: " + reporte.getId() + ", Descripción del problema: " + reporte.getDescripcionProblema());
        }
    }

    public ReporteIncidencia seleccionarReporteOperador(List<ReporteIncidencia> reportes) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione el número del reporte para ver más detalles (o 0 para salir):");

        for (int i = 0; i < reportes.size(); i++) {
            System.out.println((i + 1) + "- " + "Cliente: " + reportes.get(i).getCliente().getRazonSocial() + ", Descipcion: " + reportes.get(i).getDescripcionProblema());
        }

        int opcion = scanner.nextInt();

        if (opcion >= 1 && opcion <= reportes.size()) {
            ReporteIncidencia reporteSeleccionado = reportes.get(opcion - 1);
            return reporteSeleccionado;
        } else if (opcion == 0) {
            System.out.println("Saliendo del programa.");
        } else {
            System.out.println("Opción no válida. Inténtelo de nuevo.");
            seleccionarReporte(reportes);
        }
        return null;
    }

    private static void seleccionarReporte(List<ReporteIncidencia> reportes) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione el número del informe para ver más detalles (o 0 para salir):");

        // Mostrar la lista de informes numerados
        for (int i = 0; i < reportes.size(); i++) {
            System.out.println((i + 1) + "- " + "Cliente: " + reportes.get(i).getCliente().getRazonSocial() + ", Descipcion: " + reportes.get(i).getDescripcionProblema());
        }

        int opcion = scanner.nextInt();

        if (opcion >= 1 && opcion <= reportes.size()) {
            // Obtener el informe seleccionado (restamos 1 al índice porque los números comienzan desde 1)
            ReporteIncidencia reporteSeleccionado = reportes.get(opcion - 1);
            mostrarDetallesDelReporte(reporteSeleccionado);
        } else if (opcion == 0) {
            System.out.println("Saliendo del programa.");
        } else {
            System.out.println("Opción no válida. Inténtelo de nuevo.");
            seleccionarReporte(reportes);
        }
    }

    private static void mostrarDetallesDelReporte(ReporteIncidencia reporte) {

        System.out.println("Detalles del Reporte:");
        System.out.println("ID: " + reporte.getId());
        System.out.println("Cliente: " + reporte.getCliente().getRazonSocial());
        System.out.println("Servicio: " + reporte.getServicio().getDenominacion());
        System.out.println("Técnico: " + reporte.getTecnico().getNombreCompleto());
        System.out.println("Operdador de mesa: " + reporte.getOperador().getNombreCompleto());
        System.out.println("Tipo de problema: " + reporte.getTipoProblema());
        System.out.println("Descripción del problema: " + reporte.getDescripcionProblema());
        System.out.println("Observaciones del tecnico: " + reporte.getObservacionesTecnico());
        System.out.println("Tiempo estimado de resolucion: " + reporte.getTiempoEstimadoResolucion() + "Hrs.");
        System.out.println("Fecha de alta: " + reporte.getFechaAlta());
        System.out.println("Fecha estimada de resolucion: " + reporte.getFechaPosibleResolucion());
        System.out.println("Estado del reporte: " + reporte.getEstado());
        System.out.println(" ");
        System.out.println("Ingrese cualquier tecla para salir.");
    }

    private static Date obtenerFecha(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            try {
                System.out.println(mensaje);
                String fechaStr = scanner.next();
                return dateFormat.parse(fechaStr);
            } catch (ParseException e) {
                System.out.println("Error al procesar la fecha. Asegúrese de ingresarla en el formato correcto.");
            }
        }
    }

    private void setFechaAlta(ReporteIncidencia reporteIncidencia) {

        reporteIncidencia.setFechaAlta(new Date());
        System.out.println("La fecha de alta del reporteIncidencia es: " + reporteIncidencia.getFechaAlta());

    }

    private void setCliente(ReporteIncidencia reporteIncidencia) {

        Cliente cliente = null;
        while (cliente == null) {

            System.out.println("Ingrese el CUIT del cliente: ");
            Long cuit = scanner.nextLong();
            cliente = gestorCliente.getClienteXCUIT(cuit);

            if (cliente == null) {

                System.out.println("Cliente no encontrado con el CUIT proporcionado. Intente de nuevo.");

            }
        }

        reporteIncidencia.setCliente(cliente);
        System.out.println("El cliente seteado es: " + cliente.getRazonSocial());

    }

    private void setServicio(ReporteIncidencia reporteIncidencia) {

        List<ClienteServicio> clienteServicios = reporteIncidencia.getCliente().getClienteServicios();

        if (clienteServicios != null && !clienteServicios.isEmpty()) {

            Servicio servicioSeleccionado = null;
            while (servicioSeleccionado == null) {

                System.out.println("Seleccione el servicio:");
                int index = 1;

                for (ClienteServicio clienteServicio : clienteServicios) {

                    System.out.println(index + ". " + clienteServicio.getServicio().getDenominacion());
                    index++;

                }

                int seleccion = scanner.nextInt();
                if (seleccion > 0 && seleccion <= clienteServicios.size()) {

                    servicioSeleccionado = clienteServicios.get(seleccion - 1).getServicio();
                    System.out.println("Servicio seleccionado: " + servicioSeleccionado.getDenominacion());

                } else {

                    System.out.println("Selección no válida. Por favor, ingrese un número de servicio válido.");

                }
            }

            reporteIncidencia.setServicio(servicioSeleccionado);
            System.out.println("El servicio seteado es: " + servicioSeleccionado.getDenominacion());

        } else {

            System.out.println("El cliente no tiene servicios asociados.");

        }
    }

    private void setDescripcionProblema(ReporteIncidencia reporteIncidencia) {

        String descripcionProblema;

        while (true) {

            System.out.println("Ingrese la descripción del problema:");
            scanner.nextLine();
            descripcionProblema = scanner.nextLine();

            if (!descripcionProblema.isEmpty()) {

                break;

            } else {

                System.out.println("La descripción del problema no puede estar vacía. Inténtelo de nuevo.");

            }
        }

        reporteIncidencia.setDescripcionProblema(descripcionProblema);

    }

    private void setTipoProblema(ReporteIncidencia reporteIncidencia) {
        String tipoProblema;
        while (true) {
            System.out.println("Seleccione el tipo de problema:");
            System.out.println("1. Básico");
            System.out.println("2. Intermedio");
            System.out.println("3. Complejo");

            int opcionTipoProblema = scanner.nextInt();

            switch (opcionTipoProblema) {
                case 1:
                    tipoProblema = "Básico";
                    break;
                case 2:
                    tipoProblema = "Intermedio";
                    break;
                case 3:
                    tipoProblema = "Complejo";
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    continue;
            }

            break;
        }

        reporteIncidencia.setTipoProblema(tipoProblema);
        System.out.println("Tipo de problema: " + tipoProblema);
    }

    private void setTiempoEstimado(ReporteIncidencia reporteIncidencia) {
        int tiempoEstimado = gestorReporteIncidencia.calcularTiempoEstimadoResolucion(reporteIncidencia.getTipoProblema());
        reporteIncidencia.setTiempoEstimadoResolucion(tiempoEstimado);
        System.out.println("El tiempo estimado de resolución es: " + reporteIncidencia.getTiempoEstimadoResolucion());
    }

    private void setFechaPosibleResolucion(ReporteIncidencia reporteIncidencia) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, reporteIncidencia.getTiempoEstimadoResolucion());
        Date fechaPosibleResolucion = calendar.getTime();
        reporteIncidencia.setFechaPosibleResolucion(fechaPosibleResolucion);
        System.out.println("La fecha de posible resolución es: " + fechaPosibleResolucion);
    }

    private void setOperador(ReporteIncidencia reporteIncidencia) {
        OperadorMesaAyuda operadorMesaAyuda = null;
        while (operadorMesaAyuda == null) {
            System.out.println("Ingrese su legajo para el reporte:");
            int legajoOperador = scanner.nextInt();
            operadorMesaAyuda = gestorOperadorMesaAyuda.getOperadorMesaAyudaXLegajo(legajoOperador);

            if (operadorMesaAyuda == null) {
                System.out.println("Legajo no válido. Intente nuevamente.");
            }
        }
        reporteIncidencia.setOperador(operadorMesaAyuda);
        System.out.println("El operador para el reporte es: " + reporteIncidencia.getOperador().getNombreCompleto());
    }

    private void setTecnico(ReporteIncidencia reporte) {
        List<Tecnico> tecnicos = gestorTecnico.obtenerTodosLosTecnicos();
        TecnicoVista tecnicoVista = new TecnicoVista();
        Tecnico tecnicoSeleccionado = tecnicoVista.seleccionarTecnico(tecnicos);

        if (tecnicoSeleccionado != null) {
            reporte.setTecnico(tecnicoSeleccionado);
            System.out.println("Técnico seleccionado: " + reporte.getTecnico().getNombreCompleto());
        } else {
            System.out.println("No se ha seleccionado ningún técnico.");
        }
    }

    private void setEstadoInicialReporte (ReporteIncidencia reporteIncidencia) {

        reporteIncidencia.setEstado("pendiente");
        System.out.println("El estado del reporte de incidencia es: " + reporteIncidencia.getEstado());

    }

    private void setObservacionesTecnico (ReporteIncidencia reporteIncidencia) {

        reporteIncidencia.setObservacionesTecnico(" ");
        System.out.println("Observaciones del tecnico pendiente...");

    }

    public ReporteIncidencia setEstadoReporte (ReporteIncidencia reporteIncidencia) {

        reporteIncidencia.setEstado("pendiente");

        return reporteIncidencia;
    }

}