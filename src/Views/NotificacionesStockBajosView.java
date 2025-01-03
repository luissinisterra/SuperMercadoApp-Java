/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Controllers.ClienteController;
import Controllers.EmpleadoController;
import Controllers.ProductoController;
import Controllers.ProveedorController;
import Models.Empleados.Empleado;
import Models.Productos.Producto;
import Models.Productos.ProductoNoPerecedero;
import Models.Productos.ProductoPerecedero;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class NotificacionesStockBajosView extends javax.swing.JFrame {
    ProductoController pc;
    EmpleadoController ec;
    ClienteController cc;
    ProveedorController pvC;
    Empleado empleado;
    
    public NotificacionesStockBajosView(ProductoController pc, EmpleadoController ec, ClienteController cc, ProveedorController pvC, Empleado empleado) {
        initComponents();
        setLocationRelativeTo(this);
        
        this.pc = pc;
        this.pvC = pvC;
        this.ec = ec;
        this.cc = cc;
        this.empleado = empleado;
        alistarTabla();
    }
    private void alistarTabla(){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[]{"Codigo","Proveedor", "Nombre", "Precio", "Stock", "Tipo", "Caducidad", "DuracionAlmacen"});
        
        for (int i = 0; i < pc.getProductos(0, true).size(); i++) {
            Producto pro = pc.getProductos(0, true).get(i);
            modelo.addRow(new Object[]{
                pro.getCodigoProducto(),
                pro.getIdProveedor(),
                pro.getNombreProducto(),
                pro.getPrecio(),
                pro.getStock(),
                pro.getClass().getSimpleName().equals("ProductoPerecedero")? "Perecedero":"NoPerecedero",
                pro instanceof ProductoPerecedero? ((ProductoPerecedero)pro).getFechaCaducidad() : "",
                pro instanceof ProductoNoPerecedero? ((ProductoNoPerecedero)pro).getDuracionAlmacen() : ""
            });
        }
        tablaStocksBajos.setModel(modelo);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaStocksBajos = new javax.swing.JTable();
        regresarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Notificaciones de Stocks Bajos");

        jLabel2.setText("Productos que tienen un stock por debajo de 15");

        tablaStocksBajos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaStocksBajos);

        regresarBtn.setText("Regresar");
        regresarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(regresarBtn)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(regresarBtn)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void regresarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarBtnActionPerformed
        new GestionProductosView(pc, ec, cc, pvC, empleado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_regresarBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton regresarBtn;
    private javax.swing.JTable tablaStocksBajos;
    // End of variables declaration//GEN-END:variables
}
