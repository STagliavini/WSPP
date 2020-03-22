/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.service;

import Entity.Organismo;
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
@Path("entity.organismo")
public class OrganismoFacadeREST extends AbstractFacade<Organismo> {

    @PersistenceContext(unitName = "restfulPU")
    private EntityManager em;

    public OrganismoFacadeREST() {
        super(Organismo.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Organismo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Organismo entity) {
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
    public Organismo find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Organismo> findAll() {
        return super.findAll();
    }
    @POST
    @Path("listado_filtrado")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Organismo> findFilter(@FormParam("codigo_organismo") int codigo_organismo, @FormParam("nombre_organismo") String nombre_organismo) {
        String cadena = "select o from Organismo o where";
        List<Organismo> p = new ArrayList<>();
        Organismo org = new Organismo();
        org.setCodigoOrganismo(codigo_organismo);
        org.setNombreOrganismo(nombre_organismo);
        if (org.getCodigoOrganismo()==0) {
            cadena = cadena + " o.codigoOrganismo!=0";
        } else {
            cadena = cadena + " o.codigoOrganismo like '%" + org.getCodigoOrganismo()+"%'";
        }
        if ((org.getNombreOrganismo()== null || org.getNombreOrganismo().isEmpty())) {
            cadena = cadena + " and o.nombreOrganismo!=''";
        } else {
            cadena = cadena + " and o.nombreOrganismo like '%" + org.getNombreOrganismo()+"%'";
        }
        em.getEntityManagerFactory().getCache().evictAll();
        Query q = em.createQuery(cadena);
        p = (List<Organismo>) q.getResultList();
        return p;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Organismo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
