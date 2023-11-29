package controlador;

import modelo.Especialidad;
import modelo.Tecnico;
import org.hibernate.Session;
import persistencia.ConfigHibernate;
import modelo.ReporteIncidencia;
import org.hibernate.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorReporteIncidencia extends Gestor {

    public GestorReporteIncidencia() {

        if (sesion == null || !sesion.isOpen()) {

            sesion = ConfigHibernate.openSession();

        }

    }

    public ReporteIncidencia getReporteIncidenciaPorId(Long idReporteIncidencia) {
        try {

            Query consulta = sesion.createQuery("FROM ReporteIncidencia WHERE id = :idReporteIncidencia");
            consulta.setParameter("idReporteIncidencia", idReporteIncidencia);

            return (ReporteIncidencia) consulta.uniqueResult();

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ReporteIncidencia> obtenerTodosLosReportes() {
        try {
            Query consulta = sesion.createQuery("FROM ReporteIncidencia");
            return consulta.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<ReporteIncidencia> obtenerReportesPorTecnico(Tecnico tecnico) {
        try {
            Query consulta = sesion.createQuery("FROM ReporteIncidencia R WHERE R.tecnico = :tecnico");
            consulta.setParameter("tecnico", tecnico);
            return consulta.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Date obtenerFechaHoraActualDesdeBD() {

        try {

            Query consulta = sesion.createQuery("SELECT CURRENT_TIMESTAMP()");
            Timestamp timestamp = (Timestamp) consulta.uniqueResult();

            return new Date(timestamp.getTime());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public long contarReportesResueltosPorTecnico(Tecnico tecnico) {
        try {
            String hql = "SELECT COUNT(R) FROM ReporteIncidencia R " +
                    "WHERE R.estado = 'Resuelto' AND R.tecnico = :tecnico";

            Query query = sesion.createQuery(hql);
            query.setParameter("tecnico", tecnico);

            return (long) query.uniqueResult();

        } catch (Exception e) {
            throw new RuntimeException("Error al contar los reportes resueltos por técnico", e);
        }
    }

    public Tecnico obtenerTecnicoMasEficienteEntreFechas(Date fechaInicio, Date fechaFin) {
        try {
            String hql = "SELECT R.tecnico, COUNT(R) as cantidad " +
                    "FROM ReporteIncidencia R " +
                    "WHERE R.estado = 'Resuelto' AND R.fechaAlta BETWEEN :fechaInicio AND :fechaFin " +
                    "GROUP BY R.tecnico " +
                    "ORDER BY cantidad DESC";

            Query query = sesion.createQuery(hql);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            query.setMaxResults(1); // Obtener solo el primer resultado (el más eficiente)

            Object[] resultado = (Object[]) query.uniqueResult();

            if (resultado != null && resultado.length == 2) {
                return (Tecnico) resultado[0];
            } else {
                return null; // No se encontraron resultados
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el técnico más eficiente entre fechas", e);
        }
    }

    public Tecnico obtenerTecnicoMenosEficienteEntreFechas(Date fechaInicio, Date fechaFin) {
        try {
            String hql = "SELECT R.tecnico, COUNT(R) as cantidad " +
                    "FROM ReporteIncidencia R " +
                    "WHERE R.estado = 'Resuelto' AND R.fechaAlta BETWEEN :fechaInicio AND :fechaFin " +
                    "GROUP BY R.tecnico " +
                    "ORDER BY cantidad ASC";

            Query query = sesion.createQuery(hql);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            query.setMaxResults(1); // Obtener solo el primer resultado (el más eficiente)

            Object[] resultado = (Object[]) query.uniqueResult();

            if (resultado != null && resultado.length == 2) {
                return (Tecnico) resultado[0];
            } else {
                return null; // No se encontraron resultados
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el técnico más eficiente entre fechas", e);
        }
    }

    public Tecnico obtenerTecnicoMenosEficiente() {
        try {
            String hql = "SELECT R.tecnico, COUNT(R) " +
                    "FROM ReporteIncidencia R " +
                    "WHERE R.estado = 'Resuelto' " +
                    "GROUP BY R.tecnico " +
                    "ORDER BY COUNT(R) ASC";  // Ordenar de menor a mayor eficiencia

            Query query = sesion.createQuery(hql);
            query.setMaxResults(1);  // Obtener solo el primero (el menos eficiente)

            List<Object[]> result = query.list();

            if (!result.isEmpty()) {
                Object[] row = result.get(0);
                Tecnico tecnicoMenosEficiente = (Tecnico) row[0];
                return tecnicoMenosEficiente;
            }

            return null;  // No se encontraron técnicos menos eficientes

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el técnico menos eficiente", e);
        }
    }

    public Tecnico obtenerTecnicoMasEficiente() {
        try {
            String hql = "SELECT R.tecnico, COUNT(R) " +
                    "FROM ReporteIncidencia R " +
                    "WHERE R.estado = 'Resuelto' " +
                    "GROUP BY R.tecnico " +
                    "ORDER BY COUNT(R) DESC";

            Query query = sesion.createQuery(hql);
            query.setMaxResults(1);  // Obtener solo el primero (el más eficiente)

            List<Object[]> result = query.list();

            if (!result.isEmpty()) {
                Object[] row = result.get(0);
                Tecnico tecnicoMasEficiente = (Tecnico) row[0];
                return tecnicoMasEficiente;
            }

            return null;  // No se encontraron técnicos eficientes

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el técnico más eficiente", e);
        }
    }

    public List<ReporteIncidencia> obtenerReportesResueltosPorEspecialidad(Especialidad especialidad) {
        try {
            String hql = "FROM ReporteIncidencia R WHERE R.estado = 'Resuelto' AND :especialidad MEMBER OF R.tecnico.tecnicoEspecialidades";
            Query query = sesion.createQuery(hql);
            query.setParameter("especialidad", especialidad);

            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener reportes resueltos por especialidad", e);
        }
    }

    public List<ReporteIncidencia> obtenerReportesPorTecnicoYRangoFechas(Tecnico tecnico, Date fechaInicio, Date fechaFin) {
        try {
            String hql = "FROM ReporteIncidencia R WHERE R.tecnico = :tecnico AND R.fechaAlta BETWEEN :fechaInicio AND :fechaFin";
            Query query = sesion.createQuery(hql);
            query.setParameter("tecnico", tecnico);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);

            return (List<ReporteIncidencia>) query.list();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener reportes por técnico y rango de fechas", e);
        }
    }

    public int calcularTiempoEstimadoResolucion(String tipoProblema) {
        int tiempoEstimado;

        switch (tipoProblema) {
            case "Básico":
                tiempoEstimado = 4; // Por ejemplo, 2 horas para problemas básicos.
                break;
            case "Intermedio":
                tiempoEstimado = 8; // Por ejemplo, 6 horas para problemas intermedios.
                break;
            case "Complejo":
                tiempoEstimado = 24; // Por ejemplo, 12 horas para problemas complejos.
                break;
            default:
                tiempoEstimado = 0; // Tipo de problema no reconocido
                break;
        }

        return tiempoEstimado;
    }

}
