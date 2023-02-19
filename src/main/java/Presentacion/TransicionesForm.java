/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Excepciones.PersistenciaException;
import Utilidades.ConfiguracionPaginado;
import dominio.Cliente;
import dominio.Cuenta;
import dominio.Transaccion;
import interfaces.IClientesDAO;
import interfaces.ICuentaDAO;
import interfaces.ITransaccionDAO;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lv1013
 */
public class TransicionesForm extends javax.swing.JFrame {
    private final ITransaccionDAO transaccionDAO;
    
    private ICuentaDAO cuentasDAO;
    private Cliente cliente;
    private List<Cuenta> listaCuentas;
    private static final Logger LOG = Logger.getLogger(TransicionesForm.class.getName());
    private ConfiguracionPaginado configPaginado;
    /**
     * Creates new form ClientesForm
     * @param transaccionDAO
     * @param cliente
     */
    public TransicionesForm(ITransaccionDAO transaccionDAO,ICuentaDAO cuentasDAO, Cliente cliente) {    
        this.transaccionDAO = transaccionDAO;
        this.cuentasDAO = cuentasDAO;
        initComponents();
        
        this.configPaginado = new ConfiguracionPaginado();
        this.cliente = cliente;
        asignarListaCuentas();
    }

    private void asignarListaCuentas(){
        try {
            listaCuentas = cuentasDAO.consultar(cliente.getId());
            asignarDatosComboBox(listaCuentas);
        } catch (PersistenciaException ex) {
            Logger.getLogger(TransicionesForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void asignarDatosComboBox(List listaCuentas){
        DefaultComboBoxModel comboBox = new DefaultComboBoxModel<>();
        comboBox.addAll(listaCuentas);
        cbxCuentaDestino.setModel(comboBox);
        cbxCuentaDestino.setSelectedIndex(0);
        System.out.println(listaCuentas);
    }
    private void guardar(){
        try{
            Transaccion transaccion = extraerDatosFormulario();
            Transaccion transaccionGuardada = this.transaccionDAO.insertar(transaccion);
//            this.actualizarTabla();
//            this.mostrarMensajeClienteGuardado(clienteGuardado);
        } catch (PersistenciaException ex) {
            this.mostrarErrorEnGuardado();
        }
    }
    
    private void transferencia(){
        
    }
    
    
//    private void actualizarTabla()
//    {
//        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblClientes.getModel();
//        modeloTabla.setRowCount(0);
//        this.cargarTablaClientes();
//    }
//    private void cargarTablaClientes()
//    {
//        try{
//            List<Cliente> listaClientes = this.clientesDAO.consultar(configPaginado);
//            DefaultTableModel modeloTabla = (DefaultTableModel) this.tblClientes.getModel();
//            modeloTabla.setRowCount(0);
//            for (Cliente listaCliente : listaClientes) 
//            {
//                Object[] fila = 
//                {
//                    listaCliente.getId(),
//                    listaCliente.getNombre(),
//                    listaCliente.getApellidoPaterno(),
//                    listaCliente.getApellidoMaterno(),
//                    listaCliente.getIdDireccion()
//                };
//                modeloTabla.addRow(fila);
//            }
//        }catch(PersistenciaException e)
//        {
//            LOG.log(Level.SEVERE,e.getMessage());
//        }
//    }
    public Transaccion extraerDatosFormulario()
    {
        String cuentaOrigen = (String)this.cbxCuentaDestino.getSelectedItem();
        float monto = (float)this.spnMonto.getValue();
        String descripcion = this.txtDescripcion.getText();
        String cuentaDestino = this.txtCuentaDestino.getText();
        return new Transaccion(descripcion, monto, cuentaOrigen, cuentaDestino);
    }
    private void mostrarTransaccionExitosa(Cliente clienteGuardado)
    {
        JOptionPane.showMessageDialog(this, "Se ha realizado exitosamente la transacción");
    }
    private void mostrarErrorEnGuardado()
    {
        JOptionPane.showMessageDialog(this, "No fue posible efectuar la transacción");
    }
//    private void avanzarPagina()
//    {
//        this.configPaginado.avanzarPagina();
//        actualizarTabla();
//    }
//    private void retrocederPagina()
//    {
//        this.configPaginado.retrocederPagina();
//        actualizarTabla();
//    }

    @SuppressWarnings(value = "unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        pnlFondo = new javax.swing.JPanel();
        lblDestino = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        spnMonto = new javax.swing.JSpinner();
        cbxCuentaDestino = new javax.swing.JComboBox<>();
        lblCuenta = new javax.swing.JLabel();
        txtCuentaDestino = new javax.swing.JTextField();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clientes");
        setBackground(new java.awt.Color(0, 204, 204));

        pnlFondo.setBackground(new java.awt.Color(0, 204, 204));
        pnlFondo.setForeground(new java.awt.Color(255, 255, 255));

        lblDestino.setBackground(new java.awt.Color(0, 102, 102));
        lblDestino.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblDestino.setForeground(new java.awt.Color(255, 255, 255));
        lblDestino.setText("Cuenta destino");
        lblDestino.setToolTipText("");
        lblDestino.setOpaque(true);

        lblMonto.setBackground(new java.awt.Color(0, 102, 102));
        lblMonto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblMonto.setForeground(new java.awt.Color(255, 255, 255));
        lblMonto.setText("Monto");
        lblMonto.setOpaque(true);

        lblDescripcion.setBackground(new java.awt.Color(0, 102, 102));
        lblDescripcion.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcion.setText("Descripcion");
        lblDescripcion.setOpaque(true);

        btnAceptar.setText("Aceptar");
        btnAceptar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTitulo.setBackground(new java.awt.Color(0, 102, 102));
        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Transacción");
        lblTitulo.setOpaque(true);

        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });

        spnMonto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        spnMonto.setModel(new javax.swing.SpinnerNumberModel(0.0f, null, null, 1.0f));

        lblCuenta.setBackground(new java.awt.Color(0, 102, 102));
        lblCuenta.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblCuenta.setForeground(new java.awt.Color(255, 255, 255));
        lblCuenta.setText("Tu cuenta");
        lblCuenta.setToolTipText("");
        lblCuenta.setOpaque(true);

        txtCuentaDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuentaDestinoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFondoLayout.createSequentialGroup()
                                .addComponent(lblDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(txtCuentaDestino))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFondoLayout.createSequentialGroup()
                                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblMonto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblDescripcion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(92, 92, 92)
                                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescripcion)
                                    .addComponent(spnMonto)
                                    .addComponent(cbxCuentaDestino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pnlFondoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 842, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(75, 75, 75))
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(163, 163, 163)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(245, 245, 245))))
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addComponent(cbxCuentaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMonto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spnMonto, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCuentaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void txtCuentaDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuentaDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuentaDestinoActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cbxCuentaDestino;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCuenta;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDestino;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JSpinner spnMonto;
    private javax.swing.JTextField txtCuentaDestino;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
