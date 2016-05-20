/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.casacambioadmcln.cliente.adm.datos;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:AdmDatosCasaCambioWS
 * [/admdatos]<br>
 * USAGE:
 * <pre>
 *        ClienteAdmCasaCambio client = new ClienteAdmCasaCambio();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author lchacon
 */
public class ClienteAdmCasaCambio {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/CasaCambioAdm/webresources";

    public ClienteAdmCasaCambio() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("admdatos");
    }

    public String actualizarFactorConv() throws ClientErrorException {
        return webTarget.request().put(null, String.class);
    }

  

    public String agregarFactorConv() throws ClientErrorException {
        return webTarget.request().post(null, String.class);
    }

    public <T> T getLstMonedas(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("monedas");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
