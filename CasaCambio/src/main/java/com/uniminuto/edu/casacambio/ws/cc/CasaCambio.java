/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.casacambio.ws.cc;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author fercris
 */
@WebService(serviceName = "CasaCambio")
@Stateless()
public class CasaCambio extends ManejoBD{

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "cambiaDolaresASoles")
    public double cambiaDolaresASoles(@WebParam(name = "monto") double monto) {
        return monto * 3.6;
    }

    @WebMethod(operationName = "cambiarSolesADolares")
    public double cambiarSolesADolares(@WebParam(name = "monto") double monto) {
        if (monto != 0d) {
            return 3.6 / monto;
        } else {
            return 0d;
        }

    }
    
    @WebMethod(operationName = "cambiarAMonedaGenerica")
    public double cambiarAMonedaGenerica(@WebParam(name = "monto") double monto, @WebParam(name = "conversion") String conversion) {
        if(conversion.equals("(CNY) Yuan chino"))
            return 5D;
        else return 10D;
    }
}
