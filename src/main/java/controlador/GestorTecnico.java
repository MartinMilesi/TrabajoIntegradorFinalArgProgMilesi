package controlador;

import modelo.Tecnico;
import org.hibernate.Query;
import persistencia.ConfigHibernate;
import java.util.ArrayList;
import java.util.List;

public class GestorTecnico extends Gestor {

    public GestorTecnico() {
        if(sesion == null || !sesion.isOpen())
            sesion = ConfigHibernate.openSession();
    }

    public Tecnico getTecnicoXLegajo(int legajo){
        try {

            Query consulta = sesion.createQuery("SELECT tecnico FROM Tecnico tecnico WHERE tecnico.legajo = :legajo");
            consulta.setParameter("legajo", legajo);

            Tecnico tecnico = (Tecnico) consulta.uniqueResult();
            return tecnico;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Tecnico> obtenerTodosLosTecnicos() {
        try {
            Query consulta = sesion.createQuery("FROM Tecnico");
            return consulta.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}