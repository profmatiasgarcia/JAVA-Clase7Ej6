package Controlador;
/**
 * @author Prof Matias Garcia.
 * <p> Copyright (C) 2021 para <a href = "https://www.profmatiasgarcia.com.ar/"> www.profmatiasgarcia.com.ar </a>
 * - con licencia GNU GPL3.
 * <p> Este programa es software libre. Puede redistribuirlo y/o modificarlo bajo los términos de la
 * Licencia Pública General de GNU según es publicada por la Free Software Foundation, 
 * bien con la versión 3 de dicha Licencia o bien (según su elección) con cualquier versión posterior. 
 * Este programa se distribuye con la esperanza de que sea útil, pero SIN NINGUNA GARANTÍA, 
 * incluso sin la garantía MERCANTIL implícita o sin garantizar la CONVENIENCIA PARA UN PROPÓSITO
 * PARTICULAR. Véase la Licencia Pública General de GNU para más detalles.
 * Debería haber recibido una copia de la Licencia Pública General junto con este programa. 
 * Si no ha sido así ingrese a <a href = "http://www.gnu.org/licenses/"> GNU org </a>
 */
import Modelo.Paciente;
import java.util.List;
import org.hibernate.Session;

public class PacienteControlador {

    private Session session;

    public PacienteControlador() {
    }

    public PacienteControlador(Session session) {
        this.session = session;
    }

    public List<Paciente> listPacientes() {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            session.beginTransaction();

            List<Paciente> listado = session.createQuery("FROM pacientes", Paciente.class).getResultList();

            session.getTransaction().commit();

            if (listado != null && listado.isEmpty()) {
                return null;
            } else {
                return listado;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    public Paciente findPacienteById(int id) {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            session.beginTransaction();

            Paciente pacienteEncontrado = session.createQuery("FROM pacientes where dni =:id", Paciente.class).setParameter("id", id).getSingleResult();

            session.getTransaction().commit();

            return pacienteEncontrado;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    public void updatePaciente(Paciente pacienteUpdate) {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            session.beginTransaction();
            session.merge(pacienteUpdate);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public Paciente addPaciente(Paciente pacienteAgregar) {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            session.beginTransaction();
            session.persist(pacienteAgregar);
            session.getTransaction().commit();
            return pacienteAgregar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deletePaciente(int id) {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            session.beginTransaction();
            Paciente pacienteEliminar = session.createQuery("FROM pacientes where dni =:id", Paciente.class).setParameter("id", id).getSingleResult();
            session.remove(pacienteEliminar);
            session.getTransaction().commit();
            System.out.println("Se elimino el paciente");
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
