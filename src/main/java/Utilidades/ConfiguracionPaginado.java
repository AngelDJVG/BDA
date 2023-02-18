/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

/**
 *
 * @author lv1013
 */
public class ConfiguracionPaginado {
    private int numeroPagina;
    private int elementosPorPagina;

    public ConfiguracionPaginado(int numeroPagina, int elementosPorPagina) {
        this.numeroPagina = numeroPagina;
        this.elementosPorPagina = elementosPorPagina;
    }

    public ConfiguracionPaginado() {
        this.numeroPagina = 0;
        this.elementosPorPagina = 5;
    }
    
    public void avanzarPagina()
    {
        this.numeroPagina++;
    }
    public void retrocederPagina()
    {
        if(numeroPagina>0)
        {
            this.numeroPagina--;
        }
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public int getElementosPorPagina() {
        return elementosPorPagina;
    }

    public void setElementosPorPagina(int elementosPorPagina) {
        this.elementosPorPagina = elementosPorPagina;
    }
    
    public int getElementosSaltar()
    {
        return this.numeroPagina*this.elementosPorPagina;
    }
}
