/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.casacambioadm;

import com.uniminuto.edu.casacambioadm.modelo.Modenda;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lchacon
 */
@Stateless
@Path("/admdatos")
public class AdmDatosCasaCambioWS extends ManejoBD{

 

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Modenda> getLstMonedas() {

        return new ArrayList<>();
    }

    @POST
    public String agregarFactorConv(@QueryParam("simbolo") String simbolo, @QueryParam("nombre") String nombre,
            @QueryParam("valor") double valor, @QueryParam("factor") double factor) {
        return "";
    }

    @PUT
    public String actualizarFactorConv(@QueryParam("simbolo") String simbolo, @QueryParam("nombre") String nombre,
            @QueryParam("valor") double valor) {
        return "";
    }
}
