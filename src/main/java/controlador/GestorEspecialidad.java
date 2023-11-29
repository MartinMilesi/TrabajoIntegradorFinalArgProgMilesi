package controlador;

import modelo.Especialidad;
import org.hibernate.Query;
import org.hibernate.Session;
import persistencia.ConfigHibernate;

import java.util.List;

public class GestorEspecialidad extends Gestor {

    public GestorEspecialidad() {
        if(sesion == null || !sesion.isOpen())
            sesion = ConfigHibernate.openSession();
    }

    public Especialidad getEspecialidadXId(Long idEspecialidad){
        try {

            Query consulta = sesion.createQuery("FROM Especialidad WHERE id = :idEspecialidad");
            consulta.setParameter("idEspecialidad", idEspecialidad);

            return (Especialidad) consulta.uniqueResult();

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Especialidad> obtenerTodasLasEspecialidades() {
        try {
            String hql = "FROM Especialidad";
            Query query = sesion.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todas las especialidades", e);
        }
    }

}