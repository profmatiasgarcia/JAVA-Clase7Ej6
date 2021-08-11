package Test;
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
import Controlador.MedicoControlador;
import Controlador.PacienteControlador;
import Modelo.Medico;
import Modelo.Paciente;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Test {

    PacienteControlador pc = new PacienteControlador();
    MedicoControlador mc = new MedicoControlador();

    public static void main(String[] args) {
        Test testing = new Test();

        System.out.println("----SE CREA UN NUEVO PACIENTE----");
        Paciente pacienteNuevo = testing.createPaciente();

        System.out.println("----SE LISTAN TODOS LOS PACIENTES----");
        List<Paciente> listado = testing.getPacienteList();
        if (listado != null) {
            for (Paciente paciente : listado) {
                System.out.println(paciente);
            }
        }

        System.out.println("----SE MODIFICA UN PACIENTE----");
        testing.updatePaciente(pacienteNuevo.getDni());

        Paciente aux = testing.getPaciente(pacienteNuevo.getDni());
        if (aux != null) {
            System.out.println("Datos nuevos del Paciente : " + aux);
        }

        System.out.println("----SE ELIMINA UN PACIENTE----");
        testing.deletePaciente(pacienteNuevo.getDni());

        System.out.println("----SE LISTAN TODOS LOS PACIENTES----");
        List<Paciente> listado2 = testing.getPacienteList();
        System.out.println(listado2);

        System.out.println("---------------------------------------------------------");
        System.out.println("----SE CREA UN NUEVO MEDICO----");
        testing.createMedico();

        System.out.println("----SE LISTAN TODOS LOS MEDICOS----");
        testing.getMedicosList();

        System.out.println("----SE MODIFICA CONSULTORIO DE UN MEDICO----");
        testing.updateMedico();

        System.out.println("----BUSCAR UN MEDICO POR APELLIDO----");
        testing.getMedico();

        System.out.println("----BUSCAR UN MEDICO POR ESPECIALIDAD----");
        testing.getMedicosEspecialidad();

        System.out.println("----BUSCAR UN MEDICO POR CONSULTORIO----");
        testing.getMedicosConsultorio();
    }

    public Paciente createPaciente() {
        Paciente s = new Paciente();
        s.setDni(25536874);
        s.setApellido("Lopez");
        s.setNombre("Julio");
        s.setOsocial("OMINT");
        s.setSocio("A158974");
        pc.addPaciente(s);
        System.out.println("Paciente Agregado");
        return s;
    }

    public void updatePaciente(Integer id) {
        Paciente p = pc.findPacienteById(id);
        p.setNombre("Matias");
        p.setOsocial("Swiss");
        pc.updatePaciente(p);
        System.out.println("Paciente Modificado");
    }

    public void deletePaciente(Integer id) {
        pc.deletePaciente(id);
        System.out.println("Paciente Eliminado");
    }

    public List<Paciente> getPacienteList() {
        return pc.listPacientes();
    }

    public Paciente getPaciente(Integer id) {
        return pc.findPacienteById(id);
    }

    public void createMedico() {
        Medico m = new Medico();
        m.setApellido(JOptionPane.showInputDialog("Ingrese Apellido del Medico"));
        m.setNombre(JOptionPane.showInputDialog("Ingrese Nombre del Medico"));
        m.setEspecialidad(JOptionPane.showInputDialog("Ingrese Especialidad del Medico"));
        m.setConsultorio(JOptionPane.showInputDialog("Ingrese Consultorio"));
        mc.addMedico(m);
        System.out.println("Medico Agregado");
    }

    public void getMedicosList() {
        List<Medico> listado = mc.listMedicos();
        String lista = "ID  Apellido\tNombre\tEspecialidad\t\tConsultorio\n";
        if (listado != null) {
            for (Medico med : listado) {
                lista += med.getIdMedico() + "   " + med.getApellido() + "\t" + med.getNombre() + "\t" + med.getEspecialidad() + "\t\t" + med.getConsultorio() + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, new JTextArea(lista));
    }

    public void updateMedico() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese id del Medico a modificar"));
        Medico med = mc.findMedicoById(id);
        med.setConsultorio(JOptionPane.showInputDialog("Ingrese nuevo Consultorio"));
        mc.updateMedico(med);
        String lista = "ID  Apellido\tNombre\tEspecialidad\t\tConsultorio\n";
        lista += med.getIdMedico() + "   " + med.getApellido() + "\t" + med.getNombre() + "\t" + med.getEspecialidad() + "\t\t" + med.getConsultorio();
        JOptionPane.showMessageDialog(null, new JTextArea(lista));
    }

    public void getMedico() {
        String ape = JOptionPane.showInputDialog("Ingrese Apellido del Medico a buscar");
        Medico med = mc.findMedicoByApellido(ape);
        String lista = "ID  Apellido\tNombre\tEspecialidad\t\tConsultorio\n";
        if (med != null) {
            lista += med.getIdMedico() + "   " + med.getApellido() + "\t" + med.getNombre() + "\t" + med.getEspecialidad() + "\t\t" + med.getConsultorio();
        }
        JOptionPane.showMessageDialog(null, new JTextArea(lista));
    }

    public void getMedicosEspecialidad() {
        String esp = JOptionPane.showInputDialog("Ingrese Especialidad a listar Medicos");
        List<Medico> listado = mc.listMedicosEspecialidad(esp);
        String lista = "ID  Apellido\tNombre\tEspecialidad\t\tConsultorio\n";
        if (listado != null) {
            for (Medico med : listado) {
                lista += med.getIdMedico() + "   " + med.getApellido() + "\t" + med.getNombre() + "\t" + med.getEspecialidad() + "\t\t" + med.getConsultorio() + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, new JTextArea(lista));
    }

    public void getMedicosConsultorio() {
        String cons = JOptionPane.showInputDialog("Ingrese Consultorio");
        List<Medico> listado = mc.listMedicosConsultorio(cons);
        String lista = "ID  Apellido\tNombre\tEspecialidad\t\tConsultorio\n";
        if (listado != null) {
            for (Medico med : listado) {
                lista += med.getIdMedico() + "   " + med.getApellido() + "\t" + med.getNombre() + "\t" + med.getEspecialidad() + "\t\t" + med.getConsultorio() + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, new JTextArea(lista));
    }
}
