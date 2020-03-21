/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author santiagoat
 */
@Entity
@Table(name = "organismo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organismo.findAll", query = "SELECT o FROM Organismo o")
    , @NamedQuery(name = "Organismo.findByCodigoOrganismo", query = "SELECT o FROM Organismo o WHERE o.codigoOrganismo = :codigoOrganismo")
    , @NamedQuery(name = "Organismo.findByNombreOrganismo", query = "SELECT o FROM Organismo o WHERE o.nombreOrganismo = :nombreOrganismo")
    , @NamedQuery(name = "Organismo.findByTelefonoOrganismo", query = "SELECT o FROM Organismo o WHERE o.telefonoOrganismo = :telefonoOrganismo")
    , @NamedQuery(name = "Organismo.findByDireccionOrganismo", query = "SELECT o FROM Organismo o WHERE o.direccionOrganismo = :direccionOrganismo")
    , @NamedQuery(name = "Organismo.findByMailOrganismo", query = "SELECT o FROM Organismo o WHERE o.mailOrganismo = :mailOrganismo")
    , @NamedQuery(name = "Organismo.findByIdOrganismo", query = "SELECT o FROM Organismo o WHERE o.idOrganismo = :idOrganismo")
    , @NamedQuery(name = "Organismo.findByEstadoOrganismo", query = "SELECT o FROM Organismo o WHERE o.estadoOrganismo = :estadoOrganismo")})
public class Organismo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_organismo")
    private int codigoOrganismo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre_organismo")
    private String nombreOrganismo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "telefono_organismo")
    private String telefonoOrganismo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "direccion_organismo")
    private String direccionOrganismo;
    @Size(max = 30)
    @Column(name = "mail_organismo")
    private String mailOrganismo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_organismo")
    private Integer idOrganismo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_organismo")
    private boolean estadoOrganismo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organismo")
    private Collection<CargoEmpleado> cargoEmpleadoCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "codigoOrganismop")
    private PuntosOrganismo puntosOrganismo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organismo")
    private Collection<ReciboSueldo> reciboSueldoCollection;

    public Organismo() {
    }

    public Organismo(Integer idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public Organismo(Integer idOrganismo, int codigoOrganismo, String nombreOrganismo, String telefonoOrganismo, String direccionOrganismo, boolean estadoOrganismo) {
        this.idOrganismo = idOrganismo;
        this.codigoOrganismo = codigoOrganismo;
        this.nombreOrganismo = nombreOrganismo;
        this.telefonoOrganismo = telefonoOrganismo;
        this.direccionOrganismo = direccionOrganismo;
        this.estadoOrganismo = estadoOrganismo;
    }

    public int getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(int codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getNombreOrganismo() {
        return nombreOrganismo;
    }

    public void setNombreOrganismo(String nombreOrganismo) {
        this.nombreOrganismo = nombreOrganismo;
    }

    public String getTelefonoOrganismo() {
        return telefonoOrganismo;
    }

    public void setTelefonoOrganismo(String telefonoOrganismo) {
        this.telefonoOrganismo = telefonoOrganismo;
    }

    public String getDireccionOrganismo() {
        return direccionOrganismo;
    }

    public void setDireccionOrganismo(String direccionOrganismo) {
        this.direccionOrganismo = direccionOrganismo;
    }

    public String getMailOrganismo() {
        return mailOrganismo;
    }

    public void setMailOrganismo(String mailOrganismo) {
        this.mailOrganismo = mailOrganismo;
    }

    public Integer getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(Integer idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public boolean getEstadoOrganismo() {
        return estadoOrganismo;
    }

    public void setEstadoOrganismo(boolean estadoOrganismo) {
        this.estadoOrganismo = estadoOrganismo;
    }

    @XmlTransient
    public Collection<CargoEmpleado> getCargoEmpleadoCollection() {
        return cargoEmpleadoCollection;
    }

    public void setCargoEmpleadoCollection(Collection<CargoEmpleado> cargoEmpleadoCollection) {
        this.cargoEmpleadoCollection = cargoEmpleadoCollection;
    }

    public PuntosOrganismo getPuntosOrganismo() {
        return puntosOrganismo;
    }

    public void setPuntosOrganismo(PuntosOrganismo puntosOrganismo) {
        this.puntosOrganismo = puntosOrganismo;
    }

    @XmlTransient
    public Collection<ReciboSueldo> getReciboSueldoCollection() {
        return reciboSueldoCollection;
    }

    public void setReciboSueldoCollection(Collection<ReciboSueldo> reciboSueldoCollection) {
        this.reciboSueldoCollection = reciboSueldoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrganismo != null ? idOrganismo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organismo)) {
            return false;
        }
        Organismo other = (Organismo) object;
        if ((this.idOrganismo == null && other.idOrganismo != null) || (this.idOrganismo != null && !this.idOrganismo.equals(other.idOrganismo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Organismo[ idOrganismo=" + idOrganismo + " ]";
    }
    
}
