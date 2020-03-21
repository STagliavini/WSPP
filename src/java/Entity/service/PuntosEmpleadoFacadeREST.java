/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.service;

import Entity.Empleado;
import Entity.PuntosEmpleado;
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

/**
 *
 * @author santiagoat
 */
@Stateless
@Path("entity.puntosempleado")
public class PuntosEmpleadoFacadeREST extends AbstractFacade<PuntosEmpleado> {

    @PersistenceContext(unitName = "restfulPU")
    private EntityManager em;

    public PuntosEmpleadoFacadeREST() {
        super(PuntosEmpleado.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(PuntosEmpleado entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, PuntosEmpleado entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PuntosEmpleado find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PuntosEmpleado> findAll() {
        return super.findAll();
    }
    @POST
    @Path("agregar")
    @Produces({MediaType.APPLICATION_JSON,})
    public List<PuntosEmpleado> addPoint(@FormParam("dni_empleado") long dni_empleado,
            @FormParam("lat_punto") float lat_punto,@FormParam("long_punto") float long_punto) {
        String cadena="select e from PuntosEmpleado e where e.dniEmpleado="+dni_empleado;
        PuntosEmpleado pemp=new PuntosEmpleado();
        pemp.setDniEmpleado(dni_empleado);
        pemp.setLatPunto(lat_punto);
        pemp.setLongPunto(long_punto);
        Query q = em.createQuery(cadena);
        if(q.getResultList().size()>0){
            return (List<PuntosEmpleado>)q.getResultList();
        }
        else{
            create(pemp);
            List<PuntosEmpleado>emps=new ArrayList<>();
            return emps;
        }
    }
    @POST
    @Path("borrar")
    @Produces({MediaType.APPLICATION_JSON,})
    public List<PuntosEmpleado> removePoint(@FormParam("dni_empleado") long dni_empleado) {
        String cadena="select e from PuntosEmpleado e where e.dniEmpleado="+dni_empleado;
        PuntosEmpleado pemp=new PuntosEmpleado();
        pemp.setDniEmpleado(dni_empleado);
        Query q = em.createQuery(cadena);
        if(q.getResultList().size()>0){
            pemp=(PuntosEmpleado)q.getResultList().get(0);
            em.remove(em.merge(pemp));
            return (List<PuntosEmpleado>)q.getResultList();
        }
        return (List<PuntosEmpleado>)q.getResultList();
    }
    @POST
    @Path("listado")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PuntosEmpleado> findFilter(@FormParam("dni_empleado") long dni_empleado) {
        String cadena="select e from PuntosEmpleado e where";
        PuntosEmpleado pemp=new PuntosEmpleado();
        pemp.setDniEmpleado(dni_empleado);
        if(dni_empleado==0){
            cadena=cadena+" e.dniEmpleado!=0";
        }
        else{
            cadena=cadena+" e.dniEmpleado="+dni_empleado;
        }
        Query q = em.createQuery(cadena);
        return (List<PuntosEmpleado>)q.getResultList();
    }
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PuntosEmpleado> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
