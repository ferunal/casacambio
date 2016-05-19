/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.casacambiocliente.controlador;

import com.uniminuto.edu.casacambiocliente.controlador.wscln.CasaCambio;
import com.uniminuto.edu.casacambiocliente.controlador.wscln.CasaCambio_Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author fercris
 */
@WebServlet(name = "CleinteSrvlt", urlPatterns = {"/CleinteSrvlt"})
public class CleinteSrvlt extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/CasaCambio/CasaCambio.wsdl")
    CasaCambio_Service service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            try { // Call Web Service Operation
                Double monto = Double.parseDouble(request.getParameter("monto"));
                String conversion = request.getParameter("convertirDesde");
//              Double monto1 = Double.parseDouble(request.getParameter("monto1"));
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Convertidor Moneda</title>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">");
                out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js\"></script>");
                out.println("<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>");
                
                out.println("</head>");
                out.println("<body>");
                out.println("<div class=\"container\">");
                out.println("<div class=\"row\">");
                CasaCambio port = service.getCasaCambioPort();
                
                out.println("<div class=\"col-md-6\">");
                out.println("<h3><b>Dolares a Soles</b></h3>");
                
                double result = port.cambiaDolaresASoles(monto);
                out.println("<h3>" + result + "</h3>");
                out.println("</div>");
                
                out.println("<div class=\"col-md-6\">");
                out.println("<h3><b>Soles a Dolares</b></h3>");
                out.println("<h3>" + port.cambiarSolesADolares(monto) + "</h3>");
                out.println("</div>");
                
                out.println("</div>");
                out.println("</div>");
                
                out.println("valor yuan chino: " + port.cambiarAMonedaGenerica(monto, conversion));
                
            } catch (Exception ex) {
                System.out.println(ex);
            }

            //out.println("<h1>Servlet CleinteSrvlt at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
