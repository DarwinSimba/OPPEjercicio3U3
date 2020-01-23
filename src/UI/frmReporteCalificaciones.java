/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BEU.Curso;
import BEU.Matricula;
import BLL.GestionCursos;
import BLL.GestionMatricula;
import SIMBA.Util;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author labctr
 */
public class frmReporteCalificaciones extends javax.swing.JInternalFrame {

    private final String titulo = "Reporte de Calificaciones";
    private GestionCursos cursosBll = new GestionCursos();
    private List<Curso> cursos;
    private final DefaultTableModel dtmCalificaciones = new DefaultTableModel();
    private List<Matricula> calificaciones;
    private GestionMatricula matriculaBLL = new GestionMatricula();

    private void leerCurso() {
        try {
            Curso ficticio = new Curso("-Seleccione-", 0.0f, "");
            this.cmbCursos.addItem(ficticio);
            cursos = cursosBll.getCursos();
            //Exprecion lambda
            cursos.forEach((c) -> {
                this.cmbCursos.addItem(c);
            });
        } catch (Exception e) {
            Util.imprimir("Error: " + e.toString() + "\n");
            VTNPrincipal.verMensaje("Error al leer cursos", titulo, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarModeloTabla() {
        String[] columnas = {"Nombre", "Promedio", "Estado"};
        this.dtmCalificaciones.setColumnIdentifiers(columnas);
        this.tblReporteCalificaciones.setModel(dtmCalificaciones);
                
    }

    private void BuscarMatriculas() throws IOException {
        Curso seleccionado = (Curso) this.cmbCursos.getSelectedItem();
        calificaciones = matriculaBLL.reportar(seleccionado.getTitulo());
        MostrarCalificaciones();
    }

    private void MostrarCalificaciones() {
        int lim = this.dtmCalificaciones.getRowCount() - 1;
        for (int i = lim; i >= 0; i--) {
            this.dtmCalificaciones.removeRow(i);
        }
        for (Matricula m : calificaciones) {
            Vector fila = new Vector();
            fila.addElement(m.getEstudiante());
            fila.addElement(m.getPromedio());
            fila.addElement(m.getEstado());
        }
        this.tblReporteCalificaciones.setModel(dtmCalificaciones);
    }

    

    public frmReporteCalificaciones() {
        initComponents();
        this.leerCurso();
        this.configurarModeloTabla();
    }

/**
 * This method is called from within the constructor to initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is always
 * regenerated by the Form Editor.
 */
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbCursos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporteCalificaciones = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        cmbCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCursosActionPerformed(evt);
            }
        });

        tblReporteCalificaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblReporteCalificaciones);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jLabel1.setText("Curso:");

        btnBuscar.setIcon(new javax.swing.ImageIcon("/home/labctr/Descargas/lupaEditada.png")); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addGap(31, 31, 31)
                        .addComponent(cmbCursos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            this.BuscarMatriculas();
            VTNPrincipal.verMensaje("Busqueda correcta ",titulo,JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            Util.imprimir("\nError" + e.toString() + "\n");
            VTNPrincipal.verMensaje("Error al buscar Matrícula ",titulo,JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cmbCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCursosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCursosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<Curso> cmbCursos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReporteCalificaciones;
    // End of variables declaration//GEN-END:variables
}
