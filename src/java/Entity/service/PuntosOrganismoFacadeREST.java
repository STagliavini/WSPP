/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.service;

import Entity.PuntosOrganismo;
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
@Path("entity.puntosorganismo")
public class PuntosOrganismoFacadeREST extends AbstractFacade<PuntosOrganismo> {

    @PersistenceContext(unitName = "restfulPU")
    private EntityManager em;

    public PuntosOrganismoFacadeREST() {
        super(PuntosOrganismo.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(PuntosOrganismo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, PuntosOrganismo entity) {
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
    public PuntosOrganismo find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PuntosOrganismo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PuntosOrganismo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    @POST
    @Path("agregar")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PuntosOrganismo> addPoint(@FormParam("codigo_organismop") int codigo_organismo,
            @FormParam("lat_punto") float lat_punto,@FormParam("long_punto") float long_punto) {
        String cadena="select e from PuntosOrganismo e where e.codigoOrganismop="+codigo_organismo;
        PuntosOrganismo pemp=new PuntosOrganismo();
        pemp.setCodigoOrganismop(codigo_organismo);
        pemp.setLatPunto(lat_punto);
        pemp.setLongPunto(long_punto);
        Query q = em.createQuery(cadena);
        if(q.getResultList().size()>0){
            return (List<PuntosOrganismo>)q.getResultList();
        }
        else{
            create(pemp);
            List<PuntosOrganismo>pemps=new ArrayList<>();
            return pemps;
        }
    }
    @POST
    @Path("borrar")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PuntosOrganismo> removePoint(@FormParam("codigo_organismop") int codigo_organismo) {
        String cadena="select e from PuntosOrganismo e where e.codigoOrganismop="+codigo_organismo;
        PuntosOrganismo pemp=new PuntosOrganismo();
        pemp.setCodigoOrganismop(codigo_organismo);
        Query q = em.createQuery(cadena);
        if(q.getResultList().size()>0){
            pemp=(PuntosOrganismo)q.getResultList().get(0);
            em.remove(em.merge(pemp));
            return (List<PuntosOrganismo>)q.getResultList();
        }
        return (List<PuntosOrganismo>)q.getResultList();
    }
    @POST
    @Path("listado")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PuntosOrganismo> findFilter(@FormParam("codigo_organismop") int codigo_organismop) {
        String cadena="select e from PuntosOrganismo e where";
        PuntosOrganismo pemp=new PuntosOrganismo();
        pemp.setCodigoOrganismop(codigo_organismop);
        if(codigo_organismop==0){
            cadena=cadena+" e.codigoOrganismop!=0";
        }
        else{
            cadena=cadena+" e.codigoOrganismop="+codigo_organismop;
        }
        Query q = em.createQuery(cadena);
        return (List<PuntosOrganismo>)q.getResultList();
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
