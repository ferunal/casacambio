/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.casacambioadm;

import com.uniminuto.edu.casacambioadm.modelo.Modenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author lchacon
 */
public abstract class ManejoBD {

    @Resource(name = "jdbcCasaCambio")
    protected DataSource jdbcCasaCambio;
    Connection con;

    protected boolean agregarMoneda(Modenda pMoneda) {

        try {
            con = jdbcCasaCambio.getConnection();
            String strSql = "INSERT INTO moneda(\n"
                    + "            simbolo, nombre, valor, factor)\n"
                    + "    VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(strSql);
            pst.setString(1, pMoneda.getSimbolo());
            pst.setString(2, pMoneda.getNombre());
            pst.setDouble(3, pMoneda.getValor());
            pst.setDouble(4, pMoneda.getFactor());
            Integer filas = pst.executeUpdate();
            return filas > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ManejoBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ManejoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Double convertirMoneda(String pSimboloOrg, Double pCantidad, String pSimbloDest) {
        try {
            StringBuilder strBSql = new StringBuilder("SELECT  simbolo, nombre, valor, factor FROM moneda WHERE simbolo IN ('");
            strBSql.append(pSimboloOrg);
            strBSql.append("','");
            strBSql.append(pSimboloOrg);
            strBSql.append("')");

            con = jdbcCasaCambio.getConnection();
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery(strBSql.toString());
            List<Modenda> lstModendas = new ArrayList<>();

            while (rs.next()) {
                Modenda m = new Modenda(rs.getString("simbolo"));
                m.setNombre(rs.getString("nombre"));
                m.setValor(rs.getDouble("valor"));
                m.setFactor(rs.getDouble("factor"));

                lstModendas.add(m);
            }
            Modenda modnedaOrg = lstModendas.get(lstModendas.indexOf(new Modenda(pSimboloOrg)));
            Modenda modendaDest = lstModendas.get(lstModendas.indexOf(new Modenda(pSimbloDest)));
            Double valorModenOrgRef = modnedaOrg.getFactor() * pCantidad * modnedaOrg.getValor();
            Double valorModenDestRef = modendaDest.getFactor() * valorModenOrgRef;
            return valorModenDestRef;
        } catch (SQLException ex) {
            Logger.getLogger(ManejoBD.class.getName()).log(Level.SEVERE, null, ex);
            return -1d;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ManejoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public List<Modenda> getLstModendas() {
        try {
            String strBSql = "SELECT  simbolo, nombre, valor, factor, referencia FROM moneda ORDER BY  simbolo";

            con = jdbcCasaCambio.getConnection();
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery(strBSql);
            List<Modenda> lstModendas = new ArrayList<>();

            while (rs.next()) {
                Modenda m = new Modenda(rs.getString("simbolo"));
                m.setNombre(rs.getString("nombre"));
                m.setValor(rs.getDouble("valor"));
                m.setFactor(rs.getDouble("factor"));

                lstModendas.add(m);
            }
            return lstModendas;
        } catch (SQLException ex) {
            Logger.getLogger(ManejoBD.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ManejoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Modenda getModendaXSimb(String pSimbolo) {
        try {
            String strBSql = "SELECT  simbolo, nombre, valor, factor, referencia FROM moneda WHERE   simbolo = ?";

            con = jdbcCasaCambio.getConnection();
            PreparedStatement smt = con.prepareStatement(strBSql);
            smt.setString(1, pSimbolo);
            ResultSet rs = smt.executeQuery(strBSql);

            Modenda m = new Modenda();
            while (rs.next()) {
                m.setSimbolo(rs.getString("simbolo"));
                m.setNombre(rs.getString("nombre"));
                m.setValor(rs.getDouble("valor"));
                m.setFactor(rs.getDouble("factor"));

            }
            return m;
        } catch (SQLException ex) {
            Logger.getLogger(ManejoBD.class.getName()).log(Level.SEVERE, null, ex);
            return new Modenda();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ManejoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean actualizarFactConv(String pSimbolo, Double pFactor) {
        try {
            String strBSql = "UPDATE moneda SET valor = ?, factor = ?  WHERE   simbolo = ?";
            String strBSqlRef = "SELECT  simbolo, nombre, valor, factor, referencia FROM moneda WHERE   referencia= ?";
            con = jdbcCasaCambio.getConnection();
            PreparedStatement smt = con.prepareStatement(strBSqlRef);
            smt.setString(1, pSimbolo);
            ResultSet rs = smt.executeQuery(strBSql);

            Modenda mref = new Modenda();
            while (rs.next()) {
                mref.setSimbolo(rs.getString("simbolo"));
                mref.setNombre(rs.getString("nombre"));
                mref.setValor(rs.getDouble("valor"));
                mref.setFactor(rs.getDouble("factor"));

            }

            smt = con.prepareStatement(strBSql);
            smt.setDouble(1, pFactor * mref.getValor());
            smt.setDouble(2, pFactor);
            smt.setString(3, pSimbolo);

            return smt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ManejoBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
}
