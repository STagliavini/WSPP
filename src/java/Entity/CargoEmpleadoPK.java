/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author santiagoat
 */
@Embeddable
public class CargoEmpleadoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_empleado")
    private int idEmpleado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_organismo")
    private int idOrganismo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cargo")
    private int idCargo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_categoria")
    private int idCategoria;

    public CargoEmpleadoPK() {
    }

    public CargoEmpleadoPK(int idEmpleado, int idOrganismo, int idCargo, int idCategoria) {
        this.idEmpleado = idEmpleado;
        this.idOrganismo = idOrganismo;
        this.idCargo = idCargo;
        this.idCategoria = idCategoria;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(int idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEmpleado;
        hash += (int) idOrganismo;
        hash += (int) idCargo;
        hash += (int) idCategoria;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargoEmpleadoPK)) {
            return false;
        }
        CargoEmpleadoPK other = (CargoEmpleadoPK) object;
        if (this.idEmpleado != other.idEmpleado) {
            return false;
        }
        if (this.idOrganismo != other.idOrganismo) {
            return false;
        }
        if (this.idCargo != other.idCargo) {
            return false;
        }
        if (this.idCategoria != other.idCategoria) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.CargoEmpleadoPK[ idEmpleado=" + idEmpleado + ", idOrganismo=" + idOrganismo + ", idCargo=" + idCargo + ", idCategoria=" + idCategoria + " ]";
    }
    
}
