/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.service;

import Entity.Cargo;
import Entity.Categoria;
import Entity.Empleado;
import Entity.Organismo;
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
    @Path("listado_organismos")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Organismo> findOrganismo(@FormParam("id_cargo")int id_cargo,@FormParam("dni_empleado") long dni_empleado,@FormParam("nombre_organismo")String nombre_organismo,@FormParam("codigo_categoria")String codigo_categoria) {
        String cadena = "select o from Organismo o,CargoEmpleado r,Empleado e where o.idOrganismo=r.idOrganismo and r.idEmpleado=e.codigoEmpleado";
        if(dni_empleado!=0){
            cadena=cadena+" and e.dniEmpleado like '%"+dni_empleado+"%'";
        }
        List<Organismo> p = new ArrayList<>();
        Query q = em.createQuery(cadena);
        p = (List<Organismo>) q.getResultList();
        return p;
    }
    @POST
    @Path("listado_cargos")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cargo> findCargos(@FormParam("id_cargo")int id_cargo,@FormParam("dni_empleado") long dni_empleado,@FormParam("nombre_organismo")String nombre_organismo,@FormParam("codigo_categoria")String codigo_categoria) {
        String cadena = "select c from Cargo c,CargoEmpleado r,Empleado e,Organismo o where c.idCargo=r.idCargo and r.idEmpleado=e.codigoEmpleado and o.idOrganismo=r.idOrganismo";
        if(dni_empleado!=0){
            cadena=cadena+" and e.dniEmpleado like '%"+dni_empleado+"%'";
        }
        if(!nombre_organismo.equals("Seleccionar un Organismo")){
            cadena=cadena+" and o.nombreOrganismo='"+nombre_organismo+"'";
        }
        List<Cargo> p = new ArrayList<>();
        Query q = em.createQuery(cadena);
        p = (List<Cargo>) q.getResultList();
        return p;
    }
    @POST
    @Path("listado_categorias")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Categoria> findCategorias(@FormParam("id_cargo")int id_cargo,@FormParam("dni_empleado") long dni_empleado,@FormParam("nombre_organismo")String nombre_organismo,@FormParam("codigo_categoria")String codigo_categoria,@FormParam("nombre_cargo")String nombre_cargo) {
        String cadena = "select ca from Categoria ca,CargoEmpleado r,Empleado e,Organismo o,Cargo c where ca.idCategoria=r.idCategoria and r.idEmpleado=e.codigoEmpleado and o.idOrganismo=r.idOrganismo and c.idCargo=r.idCargo";
        if(dni_empleado!=0){
            cadena=cadena+" and e.dniEmpleado like '%"+dni_empleado+"%'";
        }
        if(!nombre_organismo.equals("Seleccionar un Organismo")){
            cadena=cadena+" and o.nombreOrganismo='"+nombre_organismo+"'";
        }
        if(!nombre_cargo.equals("Seleccionar un Cargo")){
            cadena=cadena+" and c.nombreCargo='"+nombre_cargo+"'";
        }
        List<Categoria> p = new ArrayList<>();
        Query q = em.createQuery(cadena);
        p = (List<Categoria>) q.getResultList();
        return p;
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
    public List<Object> findFilter(@FormParam("nombre_empleado") String nombre_empleado, @FormParam("dni_empleado") long dni_empleado,@FormParam("apellido_empleado") String apellido_empleado,
            @FormParam("nombre_organismo") String nombre_organismo,
            @FormParam("codigo_categoria") String codigo_categoria,@FormParam("nombre_cargo") String nombre_cargo) {
//        String cadena = "select r,o,c,ca,e from Organismo o,Cargo c,Categoria ca,CargoEmpleado r,Empleado e where r.idEmpleado=e.codigoEmpleado and r.idOrganismo=o.idOrganismo and r.idCargo=c.idCargo and "
//                + "r.idCategoria=ca.idCategoria and ";
        String cadena="select r,o,c,ca,e from Empleado e left join CargoEmpleado r on r.idEmpleado=e.codigoEmpleado "
                + "left join Organismo o on o.idOrganismo=r.idOrganismo "
                + "left join Cargo c on c.idCargo=r.idCargo "
                + "left join Categoria ca on ca.idCategoria=r.idCategoria where";
        List<Object> p = new ArrayList<>();
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
        if ((nombre_organismo== null || nombre_organismo.isEmpty()||nombre_organismo.equals("Seleccionar un Organismo"))) {
            cadena = cadena + " and (o.nombreOrganismo!='' or o.nombreOrganismo is null)";
        } else {
            cadena = cadena + " and o.nombreOrganismo='" + nombre_organismo+"'";
        }
        if (codigo_categoria == null||codigo_categoria.isEmpty()||codigo_categoria.equals("Seleccionar una Categoria")) {
            cadena = cadena + " and (ca.codigoCategoria!=0 or ca.codigoCategoria is null)";
        } else {
            cadena = cadena + " and ca.codigoCategoria='"+codigo_categoria+"'";
        }
        if (nombre_cargo == null||nombre_cargo.isEmpty()||nombre_cargo.equals("Seleccionar un Cargo")) {
            cadena = cadena + " and (c.nombreCargo!='' or c.nombreCargo is null)";
        } else {
            cadena = cadena + " and c.nombreCargo='"+nombre_cargo+"'";
        }
        em.getEntityManagerFactory().getCache().evictAll();
        Query q = em.createQuery(cadena);
        p = (List<Object>) q.getResultList();
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
