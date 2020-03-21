/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author santiagoat
 */
@Entity
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByCodigoEmpleado", query = "SELECT e FROM Empleado e WHERE e.codigoEmpleado = :codigoEmpleado")
    , @NamedQuery(name = "Empleado.findByApellidoEmpleado", query = "SELECT e FROM Empleado e WHERE e.apellidoEmpleado = :apellidoEmpleado")
    , @NamedQuery(name = "Empleado.findByNombreEmpleado", query = "SELECT e FROM Empleado e WHERE e.nombreEmpleado = :nombreEmpleado")
    , @NamedQuery(name = "Empleado.findByNacimientoEmpleado", query = "SELECT e FROM Empleado e WHERE e.nacimientoEmpleado = :nacimientoEmpleado")
    , @NamedQuery(name = "Empleado.findBySexoEmpleado", query = "SELECT e FROM Empleado e WHERE e.sexoEmpleado = :sexoEmpleado")
    , @NamedQuery(name = "Empleado.findByTelefonoEmpleado", query = "SELECT e FROM Empleado e WHERE e.telefonoEmpleado = :telefonoEmpleado")
    , @NamedQuery(name = "Empleado.findByDireccionEmpleado", query = "SELECT e FROM Empleado e WHERE e.direccionEmpleado = :direccionEmpleado")
    , @NamedQuery(name = "Empleado.findByMailEmpleado", query = "SELECT e FROM Empleado e WHERE e.mailEmpleado = :mailEmpleado")
    , @NamedQuery(name = "Empleado.findByEstadoEmpleado", query = "SELECT e FROM Empleado e WHERE e.estadoEmpleado = :estadoEmpleado")
    , @NamedQuery(name = "Empleado.findByDniEmpleado", query = "SELECT e FROM Empleado e WHERE e.dniEmpleado = :dniEmpleado")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_empleado")
    private Integer codigoEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "apellido_empleado")
    private String apellidoEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre_empleado")
    private String nombreEmpleado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nacimiento_empleado")
    @Temporal(TemporalType.DATE)
    private Date nacimientoEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "sexo_empleado")
    private String sexoEmpleado;
    @Size(max = 20)
    @Column(name = "telefono_empleado")
    private String telefonoEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "direccion_empleado")
    private String direccionEmpleado;
    @Size(max = 30)
    @Column(name = "mail_empleado")
    private String mailEmpleado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_empleado")
    private boolean estadoEmpleado;
    @Basic(optional = false)
    @Column(name = "dni_empleado")
    private long dniEmpleado;
    
    public Empleado() {
    }

    public Empleado(Integer codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public Empleado(Integer codigoEmpleado, String apellidoEmpleado, String nombreEmpleado, Date nacimientoEmpleado, String sexoEmpleado, String direccionEmpleado, boolean estadoEmpleado, long dniEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.nacimientoEmpleado = nacimientoEmpleado;
        this.sexoEmpleado = sexoEmpleado;
        this.direccionEmpleado = direccionEmpleado;
        this.estadoEmpleado = estadoEmpleado;
        this.dniEmpleado = dniEmpleado;
    }

    public Integer getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(Integer codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public Date getNacimientoEmpleado() {
        return nacimientoEmpleado;
    }

    public void setNacimientoEmpleado(Date nacimientoEmpleado) {
        this.nacimientoEmpleado = nacimientoEmpleado;
    }

    public String getSexoEmpleado() {
        return sexoEmpleado;
    }

    public void setSexoEmpleado(String sexoEmpleado) {
        this.sexoEmpleado = sexoEmpleado;
    }

    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }

    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }

    public String getDireccionEmpleado() {
        return direccionEmpleado;
    }

    public void setDireccionEmpleado(String direccionEmpleado) {
        this.direccionEmpleado = direccionEmpleado;
    }

    public String getMailEmpleado() {
        return mailEmpleado;
    }

    public void setMailEmpleado(String mailEmpleado) {
        this.mailEmpleado = mailEmpleado;
    }

    public boolean getEstadoEmpleado() {
        return estadoEmpleado;
    }

    public void setEstadoEmpleado(boolean estadoEmpleado) {
        this.estadoEmpleado = estadoEmpleado;
    }

    public long getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(long dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEmpleado != null ? codigoEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.codigoEmpleado == null && other.codigoEmpleado != null) || (this.codigoEmpleado != null && !this.codigoEmpleado.equals(other.codigoEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Empleado[ codigoEmpleado=" + codigoEmpleado + " ]";
    }
    
}
