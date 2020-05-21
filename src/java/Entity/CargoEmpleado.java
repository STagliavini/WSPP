/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author santiagoat
 */
@Entity
@Table(name = "cargo_empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CargoEmpleado.findAll", query = "SELECT c FROM CargoEmpleado c")
    , @NamedQuery(name = "CargoEmpleado.findByIdEmpleado", query = "SELECT c FROM CargoEmpleado c WHERE c.idEmpleado = :idEmpleado")
    , @NamedQuery(name = "CargoEmpleado.findByIdOrganismo", query = "SELECT c FROM CargoEmpleado c WHERE c.idOrganismo = :idOrganismo")
    , @NamedQuery(name = "CargoEmpleado.findByIdCargo", query = "SELECT c FROM CargoEmpleado c WHERE c.idCargo = :idCargo")
    , @NamedQuery(name = "CargoEmpleado.findByIdCategoria", query = "SELECT c FROM CargoEmpleado c WHERE c.idCategoria = :idCategoria")
    , @NamedQuery(name = "CargoEmpleado.findByFechaIngresoOrganismo", query = "SELECT c FROM CargoEmpleado c WHERE c.fechaIngresoOrganismo = :fechaIngresoOrganismo")
    , @NamedQuery(name = "CargoEmpleado.findByEstado", query = "SELECT c FROM CargoEmpleado c WHERE c.estado = :estado")})
public class CargoEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_empleado")
    private int idEmpleado;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_organismo")
    private int idOrganismo;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cargo")
    private int idCargo;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_categoria")
    private int idCategoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso_organismo")
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoOrganismo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cargo cargo;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Categoria categoria;
    @JoinColumn(name = "id_organismo", referencedColumnName = "id_organismo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Organismo organismo;
    @JoinColumn(name = "id_empleado", referencedColumnName = "codigo_empleado", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;

    public CargoEmpleado() {
    }

    public CargoEmpleado(CargoEmpleadoPK cargoEmpleadoPK) {
    
    }

    public CargoEmpleado(CargoEmpleadoPK cargoEmpleadoPK, Date fechaIngresoOrganismo, boolean estado) {
        this.fechaIngresoOrganismo = fechaIngresoOrganismo;
        this.estado = estado;
    }


    public Date getFechaIngresoOrganismo() {
        return fechaIngresoOrganismo;
    }

    public void setFechaIngresoOrganismo(Date fechaIngresoOrganismo) {
        this.fechaIngresoOrganismo = fechaIngresoOrganismo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Organismo getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Organismo organismo) {
        this.organismo = organismo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }


    /**
     * @return the idEmpleado
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado the idEmpleado to set
     */
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return the idOrganismo
     */
    public int getIdOrganismo() {
        return idOrganismo;
    }

    /**
     * @param idOrganismo the idOrganismo to set
     */
    public void setIdOrganismo(int idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    /**
     * @return the idCargo
     */
    public int getIdCargo() {
        return idCargo;
    }

    /**
     * @param idCargo the idCargo to set
     */
    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    /**
     * @return the idCategoria
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
}
