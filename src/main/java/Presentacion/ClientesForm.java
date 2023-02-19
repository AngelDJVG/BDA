/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Excepciones.PersistenciaException;
import Utilidades.ConfiguracionPaginado;
import Utilidades.Validadores;
import dominio.Cliente;
import dominio.Direccion;
import interfaces.IClientesDAO;
import interfaces.IDireccionesDAO;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ÁNGEL, LUIS PABLO
 */
public class ClientesForm extends javax.swing.JFrame {

    private final IClientesDAO clientesDAO;
    private final IDireccionesDAO direccionesDAO;
    private final Validadores validadores = new Validadores();
    
    private static final Logger LOG = Logger.getLogger(ClientesForm.class.getName());

    /**
     * Creates new form ClientesForm
     */
    public ClientesForm(IClientesDAO clientesDAO, IDireccionesDAO direccionesDAO) {

        this.clientesDAO = clientesDAO;
        this.direccionesDAO = direccionesDAO;
        initComponents();
        
    }

    private void guardar() {
        /*
            #Validar
            #Enviar
         */
        try {
            if (validadores() == true) {
                Direccion direccion = extraerDatosFormularioDireccion();
                Direccion direccionGuardado = this.direccionesDAO.insertar(direccion);

                Cliente cliente = extraerDatosFormulario(direccionGuardado.getId());
                Cliente clienteGuardado = this.clientesDAO.insertar(cliente);

                this.mostrarMensajeClienteGuardado(clienteGuardado);
                new Interfaz().setVisible(true);
                dispose();
            }
        } catch (PersistenciaException ex) {
            this.mostrarErrorEnGuardado();
        }
    }

    public Cliente extraerDatosFormulario(int idDireccion) {
        String nombre = this.txtNombre.getText().trim();
        String apellidoPaterno = this.txtApellidoPaterno.getText().trim();
        String apellidoMaterno = this.txtApellidoMaterno.getText().trim();
        String contrasena = this.txtContrasena.getText().trim();
        String fechaNacimiento = this.dpFechaNacimiento.getDate().toString();

        return new Cliente(idDireccion, nombre, apellidoPaterno, apellidoMaterno, contrasena, fechaNacimiento);
    }

    public Direccion extraerDatosFormularioDireccion() {
        String calle = this.txtCalle.getText().trim();
        String colonia = this.txtColonia.getText().trim();
        String numeroCasa = this.txtNumeroCasa.getText().trim();

        return new Direccion(calle, colonia, numeroCasa);
    }

    private void mostrarMensajeClienteGuardado(Cliente clienteGuardado) {
        JOptionPane.showMessageDialog(this, "Tu numero de registro es: " + clienteGuardado.getId());
    }

    private void mostrarErrorEnGuardado() {
        JOptionPane.showMessageDialog(this, "No fue posible agregar el cliente");
    }

    private boolean validadores() {
        if (validadores.validaNombre(txtNombre.getText().trim())
                && validadores.validaContrasena(txtContrasena.getText().trim())
                && validadores.validaNombre(txtApellidoPaterno.getText().trim())
                && validadores.validaNombre(txtApellidoMaterno.getText().trim())
                && dpFechaNacimiento.getDate() != null
                && validadores.validaDatosDireccion(txtCalle.getText().trim())
                && validadores.validaDatosDireccion(txtColonia.getText().trim())
                && validadores.validaDatosDireccion(txtNumeroCasa.getText().trim())) {
            return true;
        } else {
            String mensaje = "Formato(s) invalido(s) ";
            if (!validadores.validaNombre(txtNombre.getText().trim())) {
                mensaje += "- Nombre de cliente ";
            }
            if (!validadores.validaNombre(txtApellidoPaterno.getText().trim())) {
                mensaje += "- Apellido paterno de cliente ";
            }
            if (!validadores.validaNombre(txtApellidoMaterno.getText().trim())) {
                mensaje += "- Apellido materno de cliente ";
            }
            if (!validadores.validaContrasena(txtContrasena.getText().trim())) {
                mensaje += "- Contraseña ";
            }
            if (dpFechaNacimiento.getDate() == null) {
                mensaje += "- Fecha de nacimiento ";
            }
            if (!validadores.validaDatosDireccion(txtCalle.getText().trim())) {
                mensaje += "- Calle ";
            }
            if (!validadores.validaDatosDireccion(txtColonia.getText().trim())) {
                mensaje += "- Colonia ";
            }
            if (!validadores.validaDatosDireccion(txtNumeroCasa.getText().trim())) {
                mensaje += "- Numero de casa ";
            }
            JOptionPane.showMessageDialog(this, mensaje);
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings(value = "unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFondo = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblApellidoPaterno = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellidoPaterno = new javax.swing.JTextField();
        lblApellidoMaterno = new javax.swing.JLabel();
        txtApellidoMaterno = new javax.swing.JTextField();
        lblContrasena = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblCalle = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        txtNumeroCasa = new javax.swing.JTextField();
        lblFechaNacimiento = new javax.swing.JLabel();
        lblColonia = new javax.swing.JLabel();
        lblNumeroCasa = new javax.swing.JLabel();
        txtColonia = new javax.swing.JTextField();
        txtContrasena = new javax.swing.JTextField();
        dpFechaNacimiento = new com.github.lgooddatepicker.components.DatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clientes");
        setBackground(new java.awt.Color(0, 204, 204));

        pnlFondo.setBackground(new java.awt.Color(0, 204, 204));
        pnlFondo.setForeground(new java.awt.Color(255, 255, 255));

        lblNombre.setText("            Nombre");
        lblNombre.setBackground(new java.awt.Color(0, 102, 102));
        lblNombre.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setOpaque(true);
        lblNombre.setToolTipText("");

        lblApellidoPaterno.setText("       Apellido Paterno");
        lblApellidoPaterno.setBackground(new java.awt.Color(0, 102, 102));
        lblApellidoPaterno.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblApellidoPaterno.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidoPaterno.setOpaque(true);

        txtNombre.setBackground(new java.awt.Color(255, 255, 204));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtApellidoPaterno.setBackground(new java.awt.Color(255, 255, 204));

        lblApellidoMaterno.setText("       Apellido Materno");
        lblApellidoMaterno.setBackground(new java.awt.Color(0, 102, 102));
        lblApellidoMaterno.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblApellidoMaterno.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidoMaterno.setOpaque(true);

        txtApellidoMaterno.setBackground(new java.awt.Color(255, 255, 204));
        txtApellidoMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoMaternoActionPerformed(evt);
            }
        });

        lblContrasena.setText("          Contraseña");
        lblContrasena.setBackground(new java.awt.Color(0, 102, 102));
        lblContrasena.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblContrasena.setForeground(new java.awt.Color(255, 255, 255));
        lblContrasena.setOpaque(true);

        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblTitulo.setText("                            Registro");
        lblTitulo.setBackground(new java.awt.Color(0, 102, 102));
        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setOpaque(true);

        lblCalle.setText("              Calle ");
        lblCalle.setBackground(new java.awt.Color(0, 102, 102));
        lblCalle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblCalle.setForeground(new java.awt.Color(255, 255, 255));
        lblCalle.setOpaque(true);

        txtCalle.setBackground(new java.awt.Color(255, 255, 204));
        txtCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCalleActionPerformed(evt);
            }
        });

        txtNumeroCasa.setBackground(new java.awt.Color(255, 255, 204));
        txtNumeroCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroCasaActionPerformed(evt);
            }
        });

        lblFechaNacimiento.setText("     Fecha de nacimiento");
        lblFechaNacimiento.setBackground(new java.awt.Color(0, 102, 102));
        lblFechaNacimiento.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblFechaNacimiento.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaNacimiento.setOpaque(true);

        lblColonia.setText("            Colonia");
        lblColonia.setBackground(new java.awt.Color(0, 102, 102));
        lblColonia.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblColonia.setForeground(new java.awt.Color(255, 255, 255));
        lblColonia.setOpaque(true);

        lblNumeroCasa.setText("      Número de casa");
        lblNumeroCasa.setBackground(new java.awt.Color(0, 102, 102));
        lblNumeroCasa.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblNumeroCasa.setForeground(new java.awt.Color(255, 255, 255));
        lblNumeroCasa.setOpaque(true);

        txtColonia.setBackground(new java.awt.Color(255, 255, 204));
        txtColonia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtColoniaActionPerformed(evt);
            }
        });

        txtContrasena.setBackground(new java.awt.Color(255, 255, 204));
        txtContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasenaActionPerformed(evt);
            }
        });

        dpFechaNacimiento.setBackground(new java.awt.Color(255, 255, 153));

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addComponent(lblFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(dpFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFondoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(211, 211, 211)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(215, 215, 215))
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumeroCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72)
                        .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFondoLayout.createSequentialGroup()
                                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlFondoLayout.createSequentialGroup()
                                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtContrasena, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumeroCasa, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFondoLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtColonia, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                                            .addComponent(txtCalle))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pnlFondoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblApellidoMaterno, lblApellidoPaterno, lblCalle, lblColonia, lblContrasena, lblFechaNacimiento, lblNombre, lblNumeroCasa});

        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumeroCasa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumeroCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(dpFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlFondoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblApellidoMaterno, lblApellidoPaterno, lblCalle, lblColonia, lblContrasena, lblFechaNacimiento, lblNombre, lblNumeroCasa});

        pnlFondoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtApellidoMaterno, txtApellidoPaterno, txtCalle, txtColonia, txtContrasena, txtNombre, txtNumeroCasa});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaActionPerformed

    private void txtColoniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtColoniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColoniaActionPerformed

    private void txtNumeroCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroCasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroCasaActionPerformed

    private void txtCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCalleActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        new Interfaz().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtApellidoMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoMaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoMaternoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private com.github.lgooddatepicker.components.DatePicker dpFechaNacimiento;
    private javax.swing.JLabel lblApellidoMaterno;
    private javax.swing.JLabel lblApellidoPaterno;
    private javax.swing.JLabel lblCalle;
    private javax.swing.JLabel lblColonia;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumeroCasa;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumeroCasa;
    // End of variables declaration//GEN-END:variables
}
