/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.service;

import Entity.Usuario;
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
@Path("entity.usuario")
public class UsuarioFacadeREST extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "restfulPU")
    private EntityManager em;

    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Usuario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Usuario entity) {
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
    public Usuario find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findAll() {
        return super.findAll();
    }
    @POST
    @Path("listado_filtrado")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usuario> findFilter(@FormParam("nombre_usuario") String nombre_usuario,@FormParam("contrasenia_usuario") String contrasenia_usuario) {
        List<Usuario> p=new ArrayList<>();
        Usuario pemp=new Usuario();
        pemp.setNombreUsuario(nombre_usuario);
        pemp.setContraseniaUsuario(contrasenia_usuario);
        String cadena="select e from Usuario e where e.nombreUsuario='"+
                pemp.getNombreUsuario()+"' and e.contraseniaUsuario='"+pemp.getContraseniaUsuario()+"'";
        Query q = em.createQuery(cadena);
        p=(List<Usuario>) q.getResultList();
        return p;
    }
    @POST
    @Path("modificar")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usuario> update(@FormParam("nombre_usuario") String nombre_usuario,@FormParam("contrasenia_usuario") String contrasenia_usuario) {
        List<Usuario> p=new ArrayList<>();
        Usuario pemp=new Usuario();
        String cadena="select e from Usuario e where e.nombreUsuario='"+nombre_usuario+"'";
        Query q = em.createQuery(cadena);
        p=(List<Usuario>) q.getResultList();
        pemp=p.get(0);
        pemp.setContraseniaUsuario(contrasenia_usuario);
        em.merge(pemp);
        return p;
    }
    

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
