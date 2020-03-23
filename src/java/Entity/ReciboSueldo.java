/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "recibo_sueldo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReciboSueldo.findAll", query = "SELECT r FROM ReciboSueldo r")
    , @NamedQuery(name = "ReciboSueldo.findByIdEmpleado", query = "SELECT r FROM ReciboSueldo r WHERE r.idEmpleado = :idEmpleado")
    , @NamedQuery(name = "ReciboSueldo.findByIdCargo", query = "SELECT r FROM ReciboSueldo r WHERE r.idCargo = :idCargo")
    , @NamedQuery(name = "ReciboSueldo.findByIdCategoria", query = "SELECT r FROM ReciboSueldo r WHERE r.idCategoria = :idCategoria")
    , @NamedQuery(name = "ReciboSueldo.findByIdOrganismo", query = "SELECT r FROM ReciboSueldo r WHERE r.idOrganismo = :idOrganismo")
    , @NamedQuery(name = "ReciboSueldo.findByFechaLiquidacion", query = "SELECT r FROM ReciboSueldo r WHERE r.fechaLiquidacion = :fechaLiquidacion")
    , @NamedQuery(name = "ReciboSueldo.findBySueldoBasico", query = "SELECT r FROM ReciboSueldo r WHERE r.sueldoBasico = :sueldoBasico")
    , @NamedQuery(name = "ReciboSueldo.findByMontoAntiguedad", query = "SELECT r FROM ReciboSueldo r WHERE r.montoAntiguedad = :montoAntiguedad")
    , @NamedQuery(name = "ReciboSueldo.findByJubilacion", query = "SELECT r FROM ReciboSueldo r WHERE r.jubilacion = :jubilacion")
    , @NamedQuery(name = "ReciboSueldo.findByObraSocial", query = "SELECT r FROM ReciboSueldo r WHERE r.obraSocial = :obraSocial")
    , @NamedQuery(name = "ReciboSueldo.findByTotalSueldo", query = "SELECT r FROM ReciboSueldo r WHERE r.totalSueldo = :totalSueldo")
    , @NamedQuery(name = "ReciboSueldo.findByAntiguedad", query = "SELECT r FROM ReciboSueldo r WHERE r.antiguedad = :antiguedad")})
public class ReciboSueldo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id_empleado")
    private int idEmpleado;
    @Id
    @NotNull
    @Column(name = "id_cargo")
    private int idCargo;
    @Id
    @NotNull
    @Column(name = "id_categoria")
    private int idCategoria;
    @Id
    @NotNull
    @Column(name = "id_organismo")
    private int idOrganismo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_liquidacion")
    private String fechaLiquidacion;
    @NotNull
    @Column(name = "sueldo_basico")
    private BigDecimal sueldoBasico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_antiguedad")
    private BigDecimal montoAntiguedad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jubilacion")
    private BigDecimal jubilacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "obra_social")
    private BigDecimal obraSocial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_sueldo")
    private BigDecimal totalSueldo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "antiguedad")
    private int antiguedad;
    public ReciboSueldo() {
    }
    public ReciboSueldo(ReciboSueldoPK reciboSueldoPK, BigDecimal sueldoBasico, BigDecimal montoAntiguedad, BigDecimal jubilacion, BigDecimal obraSocial, BigDecimal totalSueldo, int antiguedad) {
        this.sueldoBasico = sueldoBasico;
        this.montoAntiguedad = montoAntiguedad;
        this.jubilacion = jubilacion;
        this.obraSocial = obraSocial;
        this.totalSueldo = totalSueldo;
        this.antiguedad = antiguedad;
    }

    public BigDecimal getSueldoBasico() {
        return sueldoBasico;
    }

    public void setSueldoBasico(BigDecimal sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }

    public BigDecimal getMontoAntiguedad() {
        return montoAntiguedad;
    }

    public void setMontoAntiguedad(BigDecimal montoAntiguedad) {
        this.montoAntiguedad = montoAntiguedad;
    }

    public BigDecimal getJubilacion() {
        return jubilacion;
    }

    public void setJubilacion(BigDecimal jubilacion) {
        this.jubilacion = jubilacion;
    }

    public BigDecimal getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(BigDecimal obraSocial) {
        this.obraSocial = obraSocial;
    }

    public BigDecimal getTotalSueldo() {
        return totalSueldo;
    }

    public void setTotalSueldo(BigDecimal totalSueldo) {
        this.totalSueldo = totalSueldo;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
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
     * @return the fechaLiquidacion
     */
    public String getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    /**
     * @param fechaLiquidacion the fechaLiquidacion to set
     */
    public void setFechaLiquidacion(String fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    
}
