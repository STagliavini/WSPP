/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.service;

import Entity.Cargo;
import Entity.CargoEmpleado;
import Entity.Categoria;
import Entity.Empleado;
import Entity.Organismo;
import Entity.ReciboSueldo;
import Entity.ReciboSueldoPK;
import com.sun.faces.action.RequestMapping;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.view.JasperViewer;

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
        em.getEntityManagerFactory().getCache().evictAll();
        Query q = em.createQuery(cadena);
        p = (List<Object>) q.getResultList();
        return p;
    }
    @POST
    @Path("contar")
    @Produces({MediaType.APPLICATION_JSON})
    public int count(@FormParam("dni_empleado") long dni_empleado) {
        String cadena = "select r,e from ReciboSueldo r,Empleado e where "
                + "r.idEmpleado=e.codigoEmpleado and e.dniEmpleado="+dni_empleado;
        List<Object> p = new ArrayList<>();
        Query q = em.createQuery(cadena);
        p = (List<Object>) q.getResultList();
        return p.size();
    }
    @POST
    @Path("imprimir")
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    public Response imprimir(@FormParam("id_organismo") int id_organismo,@FormParam("id_cargo") int id_cargo,
            @FormParam("id_categoria") int id_categoria,@FormParam("fecha_liquidacion") String fecha_liquidacion,@FormParam("codigo_empleado") int codigo_empleado,
            @Context ServletContext context) throws IOException, ParseException, JRException {
        List<Organismo> org = new ArrayList<>();
        Query q = em.createQuery("Select org from Organismo org where org.idOrganismo=:idOrganismo");
        q.setParameter("idOrganismo", id_organismo);
        org = (List<Organismo>) q.getResultList();
        List<Cargo> car = new ArrayList<>();
        Query r = em.createQuery("Select car from Cargo car where car.idCargo=:idCargo");
        r.setParameter("idCargo", id_cargo);
        car = (List<Cargo>)r.getResultList();
        List<Categoria> cat = new ArrayList<>();
        Query s = em.createQuery("Select cat from Categoria cat where cat.idCategoria=:idCategoria");
        s.setParameter("idCategoria", id_categoria);
        cat = (List<Categoria>) s.getResultList();
        List<Empleado> emp = new ArrayList<>();
        Query t = em.createQuery("Select emp from Empleado emp where emp.codigoEmpleado=:codigoEmpleado");
        t.setParameter("codigoEmpleado", codigo_empleado);
        emp = (List<Empleado>) t.getResultList();
        List<ReciboSueldo> re = new ArrayList<>();
        Query u = em.createQuery("Select re from ReciboSueldo re where re.idCargo=:idCargo and "
                + "re.idCategoria=:idCategoria and re.idOrganismo=:idOrganismo"
                + " and re.idEmpleado=:codigoEmpleado and re.fechaLiquidacion=:fechaLiquidacion");
        u.setParameter("idCategoria", id_categoria);
        u.setParameter("idOrganismo", id_organismo);
        u.setParameter("idCargo", id_cargo);
        u.setParameter("codigoEmpleado", codigo_empleado);
        u.setParameter("fechaLiquidacion", fecha_liquidacion);
        re = (List<ReciboSueldo>) u.getResultList();
        Map<String,Object>parametros=new HashMap<>();
        parametros.put("nombre_cargo",car.get(0).getNombreCargo());
        parametros.put("sueldo_basico", re.get(0).getSueldoBasico());
        parametros.put("nombre_organismo",org.get(0).getNombreOrganismo().toUpperCase());
        parametros.put("direccion_organismo",org.get(0).getDireccionOrganismo().toUpperCase());
        parametros.put("codigo_organismo",org.get(0).getCodigoOrganismo());
        DateFormat dfe=new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_liq=dfe.parse(fecha_liquidacion);
        DateFormat formatoFecha = new SimpleDateFormat("MM");
        String mes=formatoFecha.format(fecha_liq);
        DateFormat formatoFecha2 = new SimpleDateFormat("yyyy");
        String anio=formatoFecha2.format(fecha_liq);
        parametros.put("periodo_liquidacion",
        mes+"/"+anio);
        parametros.put("descripcion_pago",devolverNombreMes(mes, anio));
        Calendar c=Calendar.getInstance();
        int diaa = c.get(Calendar.DAY_OF_MONTH);
        int mesa = c.get(Calendar.MONTH)+1;
        int anioa = c.get(Calendar.YEAR);
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        parametros.put("fecha_pago",diaa+"/"+mesa+"/"+anioa);
        parametros.put("codigo_empleado",re.get(0).getIdEmpleado());
        parametros.put("apellido_nombre_empleado",emp.get(0).getApellidoEmpleado()+", "+
                emp.get(0).getNombreEmpleado());
        parametros.put("dni_empleado",emp.get(0).getDniEmpleado());
        parametros.put("codigo_categoria",cat.get(0).getCodigoCategoria());
        DateFormat formatoFechaing = new SimpleDateFormat("dd/MM/yyyy");
        List<CargoEmpleado> ce = new ArrayList<>();
        Query v = em.createQuery("Select ce from CargoEmpleado ce where ce.idCargo=:idCargo and "
                + "ce.idCategoria=:idCategoria and ce.idOrganismo=:idOrganismo"
                + " and ce.idEmpleado=:codigoEmpleado");
        v.setParameter("idCategoria", id_categoria);
        v.setParameter("idOrganismo", id_organismo);
        v.setParameter("idCargo", id_cargo);
        v.setParameter("codigoEmpleado", codigo_empleado);
        ce = (List<CargoEmpleado>) v.getResultList();
        String fechaing=formatoFechaing.format(ce.get(0).getFechaIngresoOrganismo());
        parametros.put("fecha_ingreso",fechaing);
        parametros.put("porcentaje_antiguedad","%"+re.get(0).getAntiguedad()*2);
        parametros.put("monto_antiguedad",re.get(0).getMontoAntiguedad());
        parametros.put("porcentaje_jubilacion","%11");
        parametros.put("monto_jubilacion",re.get(0).getJubilacion());
        parametros.put("porcentaje_obra_social","%3");
        parametros.put("monto_obra_social",re.get(0).getObraSocial());
        parametros.put("total_sueldo",re.get(0).getTotalSueldo());
        parametros.put("total_recargo",re.get(0).getSueldoBasico().add(re.get(0).getMontoAntiguedad()));
        parametros.put("total_descuento",re.get(0).getJubilacion().add(re.get(0).getObraSocial()));
        JasperPrint jasperPrint=JasperFillManager.fillReport(context.getRealPath("/reportes/recibo.jasper"),parametros);
        File jasp=new File("/home/santiagoat/WSPP/web/recibos/"+emp.get(0).getCodigoEmpleado()+org.get(0).getIdOrganismo()
                +car.get(0).getIdCargo()+re.get(0).getIdCategoria()+mes+"_"+anio+".pdf");
        OutputStream stream=new FileOutputStream(jasp);
        Response.ResponseBuilder response=Response.ok(jasp.getPath());
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        response.header("Content-disposition", "filename="+emp.get(0).getCodigoEmpleado()+org.get(0).getIdOrganismo()
                +car.get(0).getIdCargo()+re.get(0).getIdCategoria()+mes+"_"+anio+".pdf");
        return response.build();
    }

    public String devolverNombreMes(String mes,String anio){
        int mess=Integer.parseInt(mes);
        String cadena="";
        switch(mess){
            case 1:
                cadena="Enero";
                break;
            case 2:
                cadena="Febrero";
                break;
            case 3:
                cadena="Marzo";
                break;
            case 4:
                cadena="Abril";
                break;
            case 5:
                cadena="Mayo";
                break;
            case 6:
                cadena="Junio";
                break;
            case 7:
                cadena="Julio";
                break;
            case 8:
                cadena="Agosto";
                break;
            case 9:
                cadena="Septiembre";
                break;
            case 10:
                cadena="Octubre";
                break;
            case 11:
                cadena="Noviembre";
                break;
            case 12:
                cadena="Diciembre";
                break;
        }
        cadena=cadena+" "+anio;
        return cadena;
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
