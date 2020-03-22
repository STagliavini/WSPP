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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author santiagoat
 */
@Entity
@Table(name = "puntos_organismo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PuntosOrganismo.findAll", query = "SELECT p FROM PuntosOrganismo p")
    , @NamedQuery(name = "PuntosOrganismo.findByIdPunto", query = "SELECT p FROM PuntosOrganismo p WHERE p.idPunto = :idPunto")
    , @NamedQuery(name = "PuntosOrganismo.findByLatPunto", query = "SELECT p FROM PuntosOrganismo p WHERE p.latPunto = :latPunto")
    , @NamedQuery(name = "PuntosOrganismo.findByLongPunto", query = "SELECT p FROM PuntosOrganismo p WHERE p.longPunto = :longPunto")})
public class PuntosOrganismo implements Serializable {

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
    @Column(name = "codigo_organismop")
    private int codigoOrganismop;

    public PuntosOrganismo() {
    }

    public PuntosOrganismo(Integer idPunto) {
        this.idPunto = idPunto;
    }

    public PuntosOrganismo(Integer idPunto, float latPunto, float longPunto) {
        this.idPunto = idPunto;
        this.latPunto = latPunto;
        this.longPunto = longPunto;
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

    public int getCodigoOrganismop() {
        return codigoOrganismop;
    }

    public void setCodigoOrganismop(int codigoOrganismop) {
        this.codigoOrganismop = codigoOrganismop;
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
        if (!(object instanceof PuntosOrganismo)) {
            return false;
        }
        PuntosOrganismo other = (PuntosOrganismo) object;
        if ((this.idPunto == null && other.idPunto != null) || (this.idPunto != null && !this.idPunto.equals(other.idPunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.PuntosOrganismo[ idPunto=" + idPunto + " ]";
    }
    
}
