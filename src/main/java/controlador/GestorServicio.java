package controlador;

import modelo.Servicio;
import persistencia.ConfigHibernate;
import org.hibernate.Query;

public class GestorServicio extends Gestor{

    public GestorServicio(){

        if (sesion == null || !sesion.isOpen()){
            sesion = ConfigHibernate.openSession();
        }

    }

    public Servicio getServicioXId(Long idServicio){

        try {

            Query consulta = sesion.createQuery("FROM Servicio WHERE id = :idServicio");
            consulta.setParameter("idServicio", idServicio);

            return (Servicio) consulta.uniqueResult();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}