/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author santiagoat
 */
@Entity
@Table(name = "puntos_empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PuntosEmpleado.findAll", query = "SELECT p FROM PuntosEmpleado p")
    , @NamedQuery(name = "PuntosEmpleado.findByIdPunto", query = "SELECT p FROM PuntosEmpleado p WHERE p.idPunto = :idPunto")
    , @NamedQuery(name = "PuntosEmpleado.findByLatPunto", query = "SELECT p FROM PuntosEmpleado p WHERE p.latPunto = :latPunto")
    , @NamedQuery(name = "PuntosEmpleado.findByLongPunto", query = "SELECT p FROM PuntosEmpleado p WHERE p.longPunto = :longPunto")})
public class PuntosEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_punto")
    private Integer idPunto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lat_punto")
    private float latPunto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "long_punto")
    private float longPunto;
    @Basic(optional = false)
    @Column(name = "dni_empleado")
    private long dniEmpleado;

    public PuntosEmpleado() {
    }

    public PuntosEmpleado(Integer idPunto) {
        this.idPunto = idPunto;
    }

    public PuntosEmpleado(Integer idPunto, float latPunto, float longPunto,long dni_empleado) {
        this.idPunto = idPunto;
        this.latPunto = latPunto;
        this.longPunto = longPunto;
        this.dniEmpleado=dni_empleado;
    }

    public Integer getIdPunto() {
        return idPunto;
    }

    public void setIdPunto(Integer idPunto) {
        this.idPunto = idPunto;
    }

    public float getLatPunto() {
        return latPunto;
    }

    public void setLatPunto(float latPunto) {
        this.latPunto = latPunto;
    }

    public float getLongPunto() {
        return longPunto;
    }

    public void setLongPunto(float longPunto) {
        this.longPunto = longPunto;
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
        hash += (idPunto != null ? idPunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuntosEmpleado)) {
            return false;
        }
        PuntosEmpleado other = (PuntosEmpleado) object;
        if ((this.idPunto == null && other.idPunto != null) || (this.idPunto != null && !this.idPunto.equals(other.idPunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.PuntosEmpleado[ idPunto=" + idPunto + " ]";
    }
    
}
