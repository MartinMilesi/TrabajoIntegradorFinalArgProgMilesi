package controlador;

import modelo.*;
import org.hibernate.Query;
import persistencia.ConfigHibernate;

public class GestorLogin extends Gestor{

    public GestorLogin(){

        if(sesion == null || !sesion.isOpen()) {
            sesion = ConfigHibernate.openSession();
        }

    }

    public Empleado getEmpleadoXLegajo(int legajo){

        try {

            String tipoEmpleado = determinarTipoEmpleadoXLegajo(legajo);
            
            if (tipoEmpleado != null){

                String hql = "FROM " + tipoEmpleado + " E WHERE E.legajo = :legajo";
                Query query = sesion.createQuery(hql);
                query.setParameter("legajo", legajo);

                Object result = query.uniqueResult();

                if (result instanceof Empleado) {

                    System.out.println("Se encontro el empleado es de tipo: " + tipoEmpleado);
                    return (Empleado) result;

                } else {

                    System.out.println("Error: No se encontró un empleado con el legajo proporcionado.");

                }

            } else {

                System.out.println("Error: Tipo de empleado no reconocido.");

            }

            } catch (Exception e) {

            throw new RuntimeException(e);

        }

        return null;
    }

    public String determinarTipoEmpleadoXLegajo(int legajo){

        String legajoString = String.valueOf(legajo);

        if (legajoString.startsWith("11")){

            return "Tecnico";

        } else if (legajoString.startsWith("22")) {

            return "OperadorMesaAyuda";

        } else if (legajoString.startsWith("33")) {

            return "OperadorRecursos";

        } else if (legajoString.startsWith("44")) {

            return "OperadorComercial";

        }

        return null;
    }

    public String determinarTipoEmpleadoPorClase(Empleado empleado) {
        if (empleado instanceof Tecnico) {
            return "Tecnico";
        } else if (empleado instanceof OperadorMesaAyuda) {
            return "OperadorMesaAyuda";
        } else if (empleado instanceof OperadorRecursos) {
            return "OperadorRecursos";
        } else if (empleado instanceof OperadorComercial) {
            return "OperadorComercial";
        } else {
            return "Desconocido";
        }
    }



}