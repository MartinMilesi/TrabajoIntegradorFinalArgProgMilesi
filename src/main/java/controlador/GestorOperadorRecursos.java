package controlador;

import modelo.OperadorMesaAyuda;
import modelo.OperadorRecursos;
import org.hibernate.Query;
import persistencia.ConfigHibernate;

public class GestorOperadorRecursos extends Gestor{

    public GestorOperadorRecursos() {

        if(sesion == null || !sesion.isOpen()) {
            sesion = ConfigHibernate.openSession();
        }

    }

    public OperadorRecursos getOperadorRecursosXLegajo(int legajo) {

        try{
            Query consulta = sesion.createQuery("SELECT operadorRecursos FROM OperadorRecursos operadorRecursos WHERE operadorRecursos.legajo = :legajo");
            consulta.setParameter("legajo", legajo);

            OperadorRecursos operadorRecursos = (OperadorRecursos) consulta.uniqueResult();
            return operadorRecursos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}