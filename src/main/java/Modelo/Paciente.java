package Modelo;
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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "pacientes")
public class Paciente {

    @Id
    @Column(name = "dni")
    private Integer dni;
    private String apellido;
    private String nombre;
    private String osocial;
    private String socio;

    public Paciente() {
    }

    public Paciente(Integer dni, String apellido, String nombre, String osocial, String socio) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.osocial = osocial;
        this.socio = socio;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOsocial() {
        return osocial;
    }

    public void setOsocial(String osocial) {
        this.osocial = osocial;
    }

    public String getSocio() {
        return socio;
    }

    public void setSocio(String socio) {
        this.socio = socio;
    }

    @Override
    public String toString() {
        return "Paciente{" + "dni=" + dni + ", apellido=" + apellido + ", nombre=" + nombre + ", osocial=" + osocial + ", socio=" + socio + '}';
    }

}
