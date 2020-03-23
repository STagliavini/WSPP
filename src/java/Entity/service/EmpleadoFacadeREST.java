/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.service;

import Entity.Empleado;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@Path("entity.empleado")
public class EmpleadoFacadeREST extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "restfulPU")
    private EntityManager em;

    public EmpleadoFacadeREST() {
        super(Empleado.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Empleado entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Empleado entity) {
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
    public Empleado find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Empleado> findAll() {
        return super.findAll();
    }
    @POST
    @Path("modificar")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Empleado> modificar(@FormParam("dni_empleado") long dni_empleado,@FormParam("telefono_empleado") String telefono_empleado,
            @FormParam("mail_empleado") String mail_empleado,@FormParam("direccion_empleado") String direccion_empleado,
            @FormParam("nacimiento_empleado") String nacimiento_empleado) throws ParseException {
        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(nacimiento_empleado);
        String cadena = "select e from Empleado e where e.dniEmpleado="+dni_empleado;
        List<Empleado> p = new ArrayList<>();
        Empleado emp = new Empleado();
        Query q = em.createQuery(cadena);
        p = (List<Empleado>) q.getResultList();
        emp=p.get(0);
        emp.setTelefonoEmpleado(telefono_empleado);
        emp.setMailEmpleado(mail_empleado);
        emp.setDireccionEmpleado(direccion_empleado);
        emp.setNacimientoEmpleado(date);
        em.merge(emp);
        return p;
    }
    @POST
    @Path("listado_filtrado")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Empleado> findFilter(@FormParam("nombre_empleado") String nombre_empleado, @FormParam("dni_empleado") long dni_empleado,@FormParam("apellido_empleado") String apellido_empleado) {
        String cadena = "select e from Empleado e where";
        List<Empleado> p = new ArrayList<>();
        Empleado emp = new Empleado();
        emp.setNombreEmpleado(nombre_empleado);
        emp.setApellidoEmpleado(apellido_empleado);
        emp.setDniEmpleado(dni_empleado);
        if ((emp.getNombreEmpleado()== null || emp.getNombreEmpleado().isEmpty())) {
            cadena = cadena + " e.nombreEmpleado!=''";
        } else {
            cadena = cadena + " e.nombreEmpleado like '%" + emp.getNombreEmpleado()+"%'";
        }
        if ((emp.getApellidoEmpleado()== null || emp.getApellidoEmpleado().isEmpty())) {
            cadena = cadena + " and e.apellidoEmpleado!=''";
        } else {
            cadena = cadena + " and e.apellidoEmpleado like '%" + emp.getApellidoEmpleado()+"%'";
        }
        if (emp.getDniEmpleado() == 0) {
            cadena = cadena + " and e.dniEmpleado!=0";
        } else {
            cadena = cadena + " and e.dniEmpleado like '%"+emp.getDniEmpleado()+"%'";
        }
        em.getEntityManagerFactory().getCache().evictAll();
        Query q = em.createQuery(cadena);
        p = (List<Empleado>) q.getResultList();
        return p;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Empleado> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
