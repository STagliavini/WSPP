/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.service;

import Entity.CargoEmpleado;
import Entity.CargoEmpleadoPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("entity.cargoempleado")
public class CargoEmpleadoFacadeREST extends AbstractFacade<CargoEmpleado> {

    @PersistenceContext(unitName = "restfulPU")
    private EntityManager em;

    private CargoEmpleadoPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idEmpleado=idEmpleadoValue;idOrganismo=idOrganismoValue;idCargo=idCargoValue;idCategoria=idCategoriaValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        Entity.CargoEmpleadoPK key = new Entity.CargoEmpleadoPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idEmpleado = map.get("idEmpleado");
        if (idEmpleado != null && !idEmpleado.isEmpty()) {
            key.setIdEmpleado(new java.lang.Integer(idEmpleado.get(0)));
        }
        java.util.List<String> idOrganismo = map.get("idOrganismo");
        if (idOrganismo != null && !idOrganismo.isEmpty()) {
            key.setIdOrganismo(new java.lang.Integer(idOrganismo.get(0)));
        }
        java.util.List<String> idCargo = map.get("idCargo");
        if (idCargo != null && !idCargo.isEmpty()) {
            key.setIdCargo(new java.lang.Integer(idCargo.get(0)));
        }
        java.util.List<String> idCategoria = map.get("idCategoria");
        if (idCategoria != null && !idCategoria.isEmpty()) {
            key.setIdCategoria(new java.lang.Integer(idCategoria.get(0)));
        }
        return key;
    }

    public CargoEmpleadoFacadeREST() {
        super(CargoEmpleado.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(CargoEmpleado entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, CargoEmpleado entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        Entity.CargoEmpleadoPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public CargoEmpleado find(@PathParam("id") PathSegment id) {
        Entity.CargoEmpleadoPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<CargoEmpleado> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<CargoEmpleado> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
