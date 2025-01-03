/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Controllers.ClienteController;
import Controllers.EmpleadoController;
import Controllers.ProductoController;
import Controllers.ProveedorController;
import Exceptions.EntidadNoEncontradaException;
import Exceptions.TipoEmpleadoInvalidoException;
import Models.Empleados.Cajero;
import Models.Empleados.Empleado;
import Models.Empleados.Gerente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class GestionEmpleadosView extends javax.swing.JFrame {

    /**
     * Creates new form GestionEmpleadosView
     */
    
    ProductoController pc;
    EmpleadoController ec;
    ClienteController cc;
    ProveedorController pvC;
    Empleado empleado;
    
    public GestionEmpleadosView(ProductoController pc, EmpleadoController ec, ClienteController cc, ProveedorController pvC, Empleado empleado) {
        initComponents();
        setLocationRelativeTo(this);

        this.pc = pc == null? new ProductoController() : pc;
        this.ec = ec == null? new EmpleadoController() : ec;
        this.cc = cc == null? new ClienteController() : cc;
        this.pvC = pvC == null? new ProveedorController() : pvC;
        this.empleado = empleado;

        this.pintarBotones();
        this.alistarTabla();
        this.alistarBox();
        this.habilitarTurnoBonificacion();
    }

    public void alistarBox(){
        DefaultComboBoxModel<String> modeloTipoEmpleado = new DefaultComboBoxModel<>();
        modeloTipoEmpleado.addElement("Reponedor");
        modeloTipoEmpleado.addElement("Gerente");
        modeloTipoEmpleado.addElement("Cajero");
        cbxTipoEmpleado.setModel(modeloTipoEmpleado);

        DefaultComboBoxModel<String> modeloIdEmpleado = new DefaultComboBoxModel<>();
        for(Empleado empleado : this.ec.getEmpleados()){
            modeloIdEmpleado.addElement(String.valueOf(empleado.getIdEmpleado()));
        }
        cbxIdEmpleado.setModel(modeloIdEmpleado);
    }
    
    private void pintarBotones(){
        btnAgregar.setBackground(new Color(60, 179, 113));
        btnBuscar.setBackground(new Color(100, 149, 237));
        btnEditar.setBackground(new Color(255, 215, 0));
        btnEliminar.setBackground(new Color(255, 69, 0));
        btnRegresar.setBackground(new Color(230, 230, 250));
    }

    private void alistarTabla(){
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.setColumnIdentifiers(new Object[]{"Id", "Nombre", "Correo", "Salario", "Tipo", "Turno", "Bonificacion"});

        for (int i = 0; i < ec.getEmpleados().size(); i++) {
            Empleado empleado = ec.getEmpleados().get(i);
            modelo.addRow(new Object[]{
                    empleado.getIdEmpleado(),
                    empleado.getNombreCompleto(),
                    empleado.getCorreo(),
                    empleado.getSalarioMensual(),
                    empleado.getClass().getSimpleName().equals("Cajero") ? "Cajero" : empleado.getClass().getSimpleName().equals("Gerente") ? "Gerente" : "Reponedor",
                    empleado instanceof Cajero ? ((Cajero) empleado).getTurno() : "No aplica",
                    empleado instanceof Gerente ? ((Gerente) empleado).getBonificacion() : 0.0
            });
        }
        tabla.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jDialog1 = new javax.swing.JDialog();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreCompleto = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtSalarioMensual = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        cbxTipoEmpleado = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTurno = new javax.swing.JTextField();
        txtBonificacion = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        cbxIdEmpleado = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Salario");

        jLabel2.setText("Id del empleado");

        jLabel3.setText("Nombre completo");

        jLabel4.setText("Correo");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jLabel5.setText("Tipo");

        cbxTipoEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxTipoEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoEmpleadoActionPerformed(evt);
            }
        });

        jLabel6.setText("Turno");

        jLabel7.setText("Bonificación");

        txtTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTurnoActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        cbxIdEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTurno)
                            .addComponent(txtSalarioMensual, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBonificacion)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNombreCompleto)
                            .addComponent(cbxTipoEmpleado, javax.swing.GroupLayout.Alignment.TRAILING, 0, 533, Short.MAX_VALUE)
                            .addComponent(cbxIdEmpleado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregar)
                            .addComponent(btnEliminar)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbxTipoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnAgregar)
                    .addComponent(cbxIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtSalarioMensual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtBonificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegresar)
                .addGap(16, 16, 16))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegresar)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        ViewGerente vg = new ViewGerente(pc,ec,cc,pvC,this.empleado);
        vg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        try {
            String tipoEmpleado = (String) cbxTipoEmpleado.getSelectedItem();
            String nombreCompleto = txtNombreCompleto.getText();
            String correo = txtCorreo.getText();
            double salarioMensual = Double.parseDouble(txtSalarioMensual.getText());
            String turno = (!txtTurno.getText().isEmpty()) ? txtTurno.getText() : "";
            double bonificacion = (!txtBonificacion.getText().isEmpty()) ? Double.parseDouble(txtBonificacion.getText()) : 0.0;
            this.ec.agregarEmpleado(tipoEmpleado, nombreCompleto, correo, salarioMensual, turno, bonificacion);
            this.alistarTabla();
            this.limpiarCampos();
            this.alistarBox();
            JOptionPane.showMessageDialog(null, "¡EMPLEADO AGREGADO EXITOSAMENTE!");
        } catch (TipoEmpleadoInvalidoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void limpiarCampos(){
        txtNombreCompleto.setText("");
        txtCorreo.setText("");
        txtSalarioMensual.setText("");
        txtTurno.setText("");
        txtBonificacion.setText("");
    }
    
    private void habilitarTurnoBonificacion(){
        String tipoEmpleado = (String) cbxTipoEmpleado.getSelectedItem();
        if(tipoEmpleado.equals("Gerente")){
            txtTurno.setEnabled(false);
            txtBonificacion.setEnabled(true);
        } else if(tipoEmpleado.equals("Cajero")) {
            txtBonificacion.setEnabled(false);
            txtTurno.setEnabled(true);
        } else {
            txtTurno.setEnabled(false);
            txtBonificacion.setEnabled(false);
        }
    }

    private void cbxTipoEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoEmpleadoActionPerformed
        this.habilitarTurnoBonificacion();
    }//GEN-LAST:event_cbxTipoEmpleadoActionPerformed

    private void txtTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTurnoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            int idEmpleado = Integer.parseInt((String) cbxIdEmpleado.getSelectedItem());
            this.ec.eliminarEmpleado(idEmpleado);
            this.limpiarCampos();
            this.alistarTabla();
            this.alistarBox();
            JOptionPane.showMessageDialog(null, "¡EMPLEADO ELIMINADO CORRECTAMENTE!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número entero válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        }  catch (EntidadNoEncontradaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        try {
            String tipoEmpleado = (String) cbxTipoEmpleado.getSelectedItem();
            int idEmpleado = Integer.parseInt((String) cbxIdEmpleado.getSelectedItem());
            String nombreCompleto = txtNombreCompleto.getText();
            String correo = txtCorreo.getText();
            double salarioMensual = Double.parseDouble(txtSalarioMensual.getText());
            String turno = txtTurno.getText();
            double bonificacion = Double.parseDouble(txtBonificacion.getText());

            this.ec.editarEmpleado(tipoEmpleado, idEmpleado, nombreCompleto, correo, salarioMensual, turno, bonificacion);

            this.alistarTabla();
            this.limpiarCampos();
            this.alistarBox();
        } catch (TipoEmpleadoInvalidoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        int idEmpleado = Integer.parseInt((String) cbxIdEmpleado.getSelectedItem());
        Empleado empleado = this.ec.buscarEmpleado(idEmpleado);

        if(empleado instanceof Gerente){
            cbxTipoEmpleado.setSelectedItem("Gerente");
            txtTurno.setText("No aplica");
            txtBonificacion.setText(String.valueOf(((Gerente) empleado).getBonificacion()));
        } else if(empleado instanceof Cajero){
            cbxTipoEmpleado.setSelectedItem("Cajero");
            txtTurno.setText(((Cajero) empleado).getTurno());
            txtBonificacion.setText(String.valueOf((0.0)));
        } else {
            cbxTipoEmpleado.setSelectedItem("Reponedor");
            txtTurno.setText("No aplica");
            txtBonificacion.setText(String.valueOf((0.0)));
        }

        txtNombreCompleto.setText(empleado.getNombreCompleto());
        txtCorreo.setText(empleado.getCorreo());
        txtSalarioMensual.setText(String.valueOf(empleado.getSalarioMensual()));

    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbxIdEmpleado;
    private javax.swing.JComboBox<String> cbxTipoEmpleado;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBonificacion;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtSalarioMensual;
    private javax.swing.JTextField txtTurno;
    // End of variables declaration//GEN-END:variables
}
