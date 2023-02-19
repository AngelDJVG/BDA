/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author angel
 */
public class Transaccion {
    private int codigo;
    private String fechaOperacion;
    private String descripcion;
    private float monto;
    private String codigoCuentaOrigen;
    private String codigoCuentaDestino;

    public Transaccion(int codigo, String fechaOperacion, String descripcion, float monto, String codigoCuentaOrigen, String codigoCuentaDestino) {
        this.codigo = codigo;
        this.fechaOperacion = fechaOperacion;
        this.descripcion = descripcion;
        this.monto = monto;
        this.codigoCuentaOrigen = codigoCuentaOrigen;
        this.codigoCuentaDestino = codigoCuentaDestino;
    }

    public Transaccion(String descripcion, float monto, String codigoCuentaOrigen, String codigoCuentaDestino) {

        this.descripcion = descripcion;
        this.monto = monto;
        this.codigoCuentaOrigen = codigoCuentaOrigen;
        this.codigoCuentaDestino = codigoCuentaDestino;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(String fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getCodigoCuentaOrigen() {
        return codigoCuentaOrigen;
    }

    public void setCodigoCuentaOrigen(String codigoCuentaOrigen) {
        this.codigoCuentaOrigen = codigoCuentaOrigen;
    }

    public String getCodigoCuentaDestino() {
        return codigoCuentaDestino;
    }

    public void setCodigoCuentaDestino(String codigoCuentaDestino) {
        this.codigoCuentaDestino = codigoCuentaDestino;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transaccion other = (Transaccion) obj;
        return this.codigo == other.codigo;
    }
    
    
    
}
