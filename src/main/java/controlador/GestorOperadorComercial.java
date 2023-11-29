package controlador;

import modelo.OperadorComercial;
import org.hibernate.Query;
import persistencia.ConfigHibernate;

public class GestorOperadorComercial extends Gestor{

    public GestorOperadorComercial() {

        if(sesion == null || !sesion.isOpen()) {
            sesion = ConfigHibernate.openSession();
        }

    }

    public OperadorComercial getOperadorComercialXLegajo(int legajo){

        try{
            Query consulta = sesion.createQuery("SELECT operadorComercial FROM OperadorComercial operadorComercial WHERE operadorComercial.legajo = :legajo");
            consulta.setParameter("legajo", legajo);

            OperadorComercial operadorComercial = (OperadorComercial) consulta.uniqueResult();
            return operadorComercial;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}