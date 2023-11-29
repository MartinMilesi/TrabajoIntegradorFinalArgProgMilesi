package controlador;

import modelo.OperadorMesaAyuda;
import org.hibernate.Query;
import persistencia.ConfigHibernate;

public class GestorOperadorMesaAyuda extends Gestor{

    public GestorOperadorMesaAyuda(){

        if(sesion == null || !sesion.isOpen()) {
            sesion = ConfigHibernate.openSession();
        }

    }

    public OperadorMesaAyuda getOperadorMesaAyudaXLegajo(int legajo){

        try{
            Query consulta = sesion.createQuery("SELECT operadorMesaAyuda FROM OperadorMesaAyuda operadorMesaAyuda WHERE operadorMesaAyuda.legajo = :legajo");
            consulta.setParameter("legajo", legajo);

            OperadorMesaAyuda operadorMesaAyuda = (OperadorMesaAyuda) consulta.uniqueResult();
            return operadorMesaAyuda;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}