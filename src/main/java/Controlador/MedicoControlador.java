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
import Modelo.Medico;
import java.util.List;
import org.hibernate.Session;

public class MedicoControlador {

    private Session session;

    public MedicoControlador() {
    }

    public MedicoControlador(Session session) {
        this.session = session;
    }

    public List<Medico> listMedicos() {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            session.beginTransaction();

            List<Medico> listado = session.createQuery("FROM Medico", Medico.class).getResultList();

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

    public Medico findMedicoById(int id) {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            session.beginTransaction();

            Medico medicoEncontrado = session.createQuery("FROM Medico WHERE idMedico =:id", Medico.class).setParameter("id", id).getSingleResult();

            session.getTransaction().commit();
            if (medicoEncontrado != null) {
                return medicoEncontrado;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    public Medico findMedicoByApellido(String ape) {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            session.beginTransaction();

            Medico medicoEncontrado = session.createQuery("FROM Medico WHERE apellido =:ape", Medico.class).setParameter("ape", ape).getSingleResult();

            session.getTransaction().commit();
            if (medicoEncontrado != null) {
                return medicoEncontrado;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }

    public void updateMedico(Medico medicoUpdate) {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            session.beginTransaction();
            session.merge(medicoUpdate);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public Medico addMedico(Medico medicoAdd) {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            session.beginTransaction();
            session.persist(medicoAdd);
            session.getTransaction().commit();
            return medicoAdd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteMedico(int id) {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            session.beginTransaction();
            Medico medicoEliminar = session.createQuery("FROM Medico WHERE idMedico =:id", Medico.class).setParameter("id", id).getSingleResult();
            session.remove(medicoEliminar);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public List<Medico> listMedicosEspecialidad(String esp) {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            session.beginTransaction();

            List<Medico> listado = session.createQuery("FROM Medico WHERE especialidad =:esp", Medico.class).setParameter("esp", esp).getResultList();

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

    public List<Medico> listMedicosConsultorio(String cons) {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            session.beginTransaction();

            List<Medico> listado = session.createQuery("FROM Medico WHERE consultorio =:cons", Medico.class).setParameter("cons", cons).getResultList();

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
}
