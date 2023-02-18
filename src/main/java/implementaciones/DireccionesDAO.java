/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import excepciones.PersistenciaException;
import utilidades.ConfiguracionPaginado;
import dominio.Cliente;
import dominio.Direccion;
import interfaces.IDireccionesDAO;
import interfaces.IConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author lv1013
 */
public class DireccionesDAO implements IDireccionesDAO {

    private final IConexionBD GENERADOR_CONEXIONES;
    private static final Logger LOG = Logger.getLogger(DireccionesDAO.class.getName());
    /*Recibirlo en el constructor, no crearlo aquí mismo en el constructor,
    de esta manera desde donde se cree el DAO se define a que bd va a apuntar
    */
    public DireccionesDAO(IConexionBD generadorConexiones){
        this.GENERADOR_CONEXIONES = generadorConexiones;
    }
    
    @Override
    public Direccion consultar(Integer idDireccion) {
        try{
        Connection conexion = this.GENERADOR_CONEXIONES.crearConexion();
        PreparedStatement comando = 
                conexion.prepareStatement("select calle, colonia, numeroCasa,id_direccion from direcciones WHERE id=?");
        comando.setInt(1, idDireccion);
        ResultSet resultado = comando.executeQuery();
        Direccion direccion = null;
        if(resultado.next()) {
            //POSIBLE ERROR EN ESTA PARTE POR NOMBRES DE VARIABLES
            String calle = resultado.getString("calle");
            String colonia = resultado.getString("colonia");
            String numeroCasa = resultado.getString("numeroCasa");
            Integer id_direccion = resultado.getInt("id_direccion");
            direccion = new Direccion(id_direccion, calle, colonia, numeroCasa);
        }
        conexion.close();
        return direccion;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    @Override
    public Direccion insertar(Direccion direccion) throws PersistenciaException{
        /*De esta forma en vez de cerrar las conexiones en un finally, las conexiones
        se cierran en automatico
        */
        try(
            Connection conexion = this.GENERADOR_CONEXIONES.crearConexion();
            PreparedStatement comando = conexion.prepareStatement("insert into direcciones(calle, "
            + "colonia,numerocasa)"
            + "values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ){
            comando.setString(1, direccion.getCalle());
            comando.setString(2, direccion.getColonia());
            comando.setString(3, direccion.getNumeroCasa());
            comando.executeUpdate();
            System.out.println("Direccion creada");
            ResultSet llaves = comando.getGeneratedKeys();
            if(llaves.next()){
                int posicionLlavePrimaria = 1;
                Integer id = llaves.getInt(posicionLlavePrimaria);
                direccion.setId(id);
                return direccion;
            }
            throw new PersistenciaException("No fue posible registrar la direccion");
        }catch(SQLException ex){
            LOG.log(Level.SEVERE, ex.getMessage());
            throw new PersistenciaException("No fue posible registrar la direccion");
        }
    }

    @Override
    public Direccion eliminar(Integer idDireccion) {
        Direccion direccionEliminada = null;
        try{
        Connection conexion = this.GENERADOR_CONEXIONES.crearConexion();
        PreparedStatement comando = 
                conexion.prepareStatement("delete from clientes where id=?");
        comando.setInt(1, idDireccion);
        direccionEliminada = this.consultar(idDireccion);
        int numeroClientesEliminados = comando.executeUpdate();
        
        if(direccionEliminada !=null && numeroClientesEliminados>0){
           System.out.println("Direccion Eliminada"); 
        }
        conexion.close();
        return direccionEliminada;
        }catch(SQLException ex){
            LOG.log(Level.SEVERE, ex.getMessage());
            return null;
        }
    }


    

    

}
