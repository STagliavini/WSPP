/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.service;

import Entity.Cargo;
import Entity.Categoria;
import Entity.Organismo;
import Entity.ReciboSueldo;
import Entity.ReciboSueldoPK;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author santiagoat
 */
@Stateless
@Path("entity.recibosueldo")
public class ReciboSueldoFacadeREST extends AbstractFacade<ReciboSueldo> {

    @PersistenceContext(unitName = "restfulPU")
    private EntityManager em;

    private ReciboSueldoPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idEmpleado=idEmpleadoValue;idCargo=idCargoValue;idCategoria=idCategoriaValue;idOrganismo=idOrganismoValue;fechaLiquidacion=fechaLiquidacionValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        Entity.ReciboSueldoPK key = new Entity.ReciboSueldoPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idEmpleado = map.get("idEmpleado");
        if (idEmpleado != null && !idEmpleado.isEmpty()) {
            key.setIdEmpleado(new java.lang.Integer(idEmpleado.get(0)));
        }
        java.util.List<String> idCargo = map.get("idCargo");
        if (idCargo != null && !idCargo.isEmpty()) {
            key.setIdCargo(new java.lang.Integer(idCargo.get(0)));
        }
        java.util.List<String> idCategoria = map.get("idCategoria");
        if (idCategoria != null && !idCategoria.isEmpty()) {
            key.setIdCategoria(new java.lang.Integer(idCategoria.get(0)));
        }
        java.util.List<String> idOrganismo = map.get("idOrganismo");
        if (idOrganismo != null && !idOrganismo.isEmpty()) {
            key.setIdOrganismo(new java.lang.Integer(idOrganismo.get(0)));
        }
        java.util.List<String> fechaLiquidacion = map.get("fechaLiquidacion");
        if (fechaLiquidacion != null && !fechaLiquidacion.isEmpty()) {
            key.setFechaLiquidacion(new java.util.Date(fechaLiquidacion.get(0)));
        }
        return key;
    }

    public ReciboSueldoFacadeREST() {
        super(ReciboSueldo.class);
    }
    @POST
    @Path("listado_organismos")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Organismo> findOrganismo(@FormParam("id_organimo")int id_organismo) {
        String cadena = "select o from Organismo o,ReciboSueldo r where o.idOrganismo=r.idOrganismo";
        List<Organismo> p = new ArrayList<>();
        Query q = em.createQuery(cadena);
        p = (List<Organismo>) q.getResultList();
        return p;
    }
    @POST
    @Path("listado_categorias")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Categoria> findCategoria(@FormParam("id_categoria")int id_categoria) {
        String cadena = "select c from Categoria c,ReciboSueldo r where c.idCategoria=r.idCategoria order by c.codigoCategoria";
        List<Categoria> p = new ArrayList<>();
        Query q = em.createQuery(cadena);
        p = (List<Categoria>) q.getResultList();
        return p;
    }
    @POST
    @Path("listado_cargos")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cargo> findCargos(@FormParam("id_cargo")int id_cargo) {
        String cadena = "select c from Cargo c,ReciboSueldo r where c.idCargo=r.idCargo";
        List<Cargo> p = new ArrayList<>();
        Query q = em.createQuery(cadena);
        p = (List<Cargo>) q.getResultList();
        return p;
    }
    @POST
    @Path("listado_filtrado")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Object> findFilter(@FormParam("dni_empleado") long dni_empleado, @FormParam("nombre_organismo") String nombre_organismo,
            @FormParam("codigo_categoria") String codigo_categoria,@FormParam("nombre_cargo") String nombre_cargo,
            @FormParam("fecha_inicial") String fecha_inicial,@FormParam("fecha_final") String fecha_final) {
        String cadena = "select r,o,c,ca,e from ReciboSueldo r,Organismo o,Cargo c,Categoria ca,Empleado e where "
                + "r.idEmpleado=e.codigoEmpleado and r.idOrganismo=o.idOrganismo and r.idCargo=c.idCargo and "
                + "r.idCategoria=ca.idCategoria and ";
        List<Object> p = new ArrayList<>();
        if ((dni_empleado==0)) {
            cadena = cadena + " e.dniEmpleado!=0";
        } else {
            cadena = cadena + " e.dniEmpleado like '%" +dni_empleado+"%'";
        }
        if ((nombre_organismo== null || nombre_organismo.isEmpty()||nombre_organismo.equals("Seleccionar un Organismo"))) {
            cadena = cadena + " and o.nombreOrganismo!=''";
        } else {
            cadena = cadena + " and o.nombreOrganismo like '%" + nombre_organismo+"%'";
        }
        if (codigo_categoria == null||codigo_categoria.isEmpty()||codigo_categoria.equals("Seleccionar una Categoria")) {
            cadena = cadena + " and ca.codigoCategoria!=0";
        } else {
            cadena = cadena + " and ca.codigoCategoria like '%"+codigo_categoria+"%'";
        }
        if (nombre_cargo == null||nombre_cargo.isEmpty()||nombre_cargo.equals("Seleccionar un Cargo")) {
            cadena = cadena + " and c.nombreCargo!=''";
        } else {
            cadena = cadena + " and c.nombreCargo like '%"+nombre_cargo+"%'";
        }
        if (fecha_inicial == null||fecha_inicial.isEmpty()) {
            cadena = cadena + " and r.fechaLiquidacion!=''";
        } else {
            cadena = cadena + " and r.fechaLiquidacion>='"+fecha_inicial+"'";
        }
        if (fecha_final == null||fecha_final.isEmpty()) {
            cadena = cadena + " and r.fechaLiquidacion!=''";
        } else {
            cadena = cadena + " and r.fechaLiquidacion<='"+fecha_final+"'";
        }
        Query q = em.createQuery(cadena);
        p = (List<Object>) q.getResultList();
        return p;
    }
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(ReciboSueldo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, ReciboSueldo entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        Entity.ReciboSueldoPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ReciboSueldo find(@PathParam("id") PathSegment id) {
        Entity.ReciboSueldoPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ReciboSueldo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ReciboSueldo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
