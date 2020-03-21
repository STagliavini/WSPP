/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author santiagoat
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(Entity.service.CargoEmpleadoFacadeREST.class);
        resources.add(Entity.service.CargoFacadeREST.class);
        resources.add(Entity.service.CategoriaFacadeREST.class);
        resources.add(Entity.service.EmpleadoFacadeREST.class);
        resources.add(Entity.service.OrganismoFacadeREST.class);
        resources.add(Entity.service.PeriodoFacadeREST.class);
        resources.add(Entity.service.PuntosEmpleadoFacadeREST.class);
        resources.add(Entity.service.PuntosOrganismoFacadeREST.class);
        resources.add(Entity.service.ReciboSueldoFacadeREST.class);
        resources.add(Entity.service.UsuarioFacadeREST.class);
    }
    
}
