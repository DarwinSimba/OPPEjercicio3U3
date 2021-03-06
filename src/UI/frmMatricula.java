/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BEU.Curso;
import BEU.Estudiante;
import BLL.GestionCursos;
import BLL.GestionEstudiante;
import BLL.GestionMatricula;
import SIMBA.Util;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author labctr
 */
public class frmMatricula extends javax.swing.JInternalFrame {

    private final String titulo = "Registro de Matrículas";
    //Servicio
    private GestionCursos cursosBLL = new GestionCursos();
    private List<Curso> cursos;
    private GestionEstudiante estudianteBLL = new GestionEstudiante();
    private List<Estudiante> estudiantes;
    private GestionMatricula matriculaBLL = new GestionMatricula();

    private void CrearMatricula() throws IOException {
        Curso cr = (Curso) this.cbxCurso.getSelectedItem();
        Estudiante est = (Estudiante) this.cbxEstudiante1.getSelectedItem();
        matriculaBLL.Crear();
        matriculaBLL.configurar(cr, est);
        matriculaBLL.archivar();
        

    }

    private boolean Validar() {
        Curso cr = (Curso) this.cbxCurso.getSelectedItem();
        if (cr.getTitulo().equals("--Seleccione--")) {
            VTNPrincipal.verMensaje("Curso no válido", titulo, JOptionPane.WARNING_MESSAGE);
            return false;
        }
        Estudiante est = (Estudiante) this.cbxEstudiante1.getSelectedItem();
        if (est.getNombre().equals("--Seleccione--")) {
            VTNPrincipal.verMensaje(" Estudiante no válido", titulo, JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;

    }

    private void leerCursos() {
        try {
            Curso ficticio = new Curso("--Seleccione--", 0.0f, " ");
            this.cbxCurso.addItem(ficticio);
            cursos = cursosBLL.getCursos();
            //Exprecion lamda
            cursos.forEach((c) -> {
                this.cbxCurso.addItem(c);
            });

        } catch (Exception e) {
            Util.imprimir("Error: " + e.toString());
            VTNPrincipal.verMensaje("Error al leer los cursos", titulo, JOptionPane.ERROR_MESSAGE);

        }

    }

    private void leerEstudiantes() {
        try {
            Estudiante ficticio = new Estudiante();
            ficticio.setNombre("--Seleccione--");
            ficticio.setApellido("");
            this.cbxEstudiante1.addItem(ficticio);
            estudiantes = estudianteBLL.leerEstudiantes();
            //Exprecion lamda
            estudiantes.forEach((es) -> {
                this.cbxEstudiante1.addItem(es);
            });

        } catch (Exception e) {
            Util.imprimir("Error: " + e.toString());
            VTNPrincipal.verMensaje("Error al leer los estudiantes", titulo, JOptionPane.ERROR_MESSAGE);

        }

    }

    public frmMatricula() {
        initComponents();
        leerCursos();
        this.leerEstudiantes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMatricula = new javax.swing.JLabel();
        lblEstudiante = new javax.swing.JLabel();
        lblCurso = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        cbxCurso = new javax.swing.JComboBox<>();
        cbxEstudiante1 = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        lblMatricula.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lblMatricula.setForeground(java.awt.Color.red);
        lblMatricula.setText("Registro de Matrícula");

        lblEstudiante.setText("Estudiante:");

        lblCurso.setText("Curso:");

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEstudiante)
                    .addComponent(lblCurso))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblMatricula)
                        .addGap(0, 105, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxCurso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxEstudiante1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(79, 79, 79))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblMatricula)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstudiante)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbxEstudiante1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCurso))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        try {
            if(this.Validar()){
                this.CrearMatricula();
                VTNPrincipal.verMensaje("Matricula creada Correctamente", titulo, JOptionPane.INFORMATION_MESSAGE);
            }
            
            

        } catch (Exception e) {
            Util.imprimir("Error: " + e.toString());
            VTNPrincipal.verMensaje("Error al crear la matrícula", titulo, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Curso> cbxCurso;
    private javax.swing.JComboBox<Estudiante> cbxEstudiante1;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblEstudiante;
    private javax.swing.JLabel lblMatricula;
    // End of variables declaration//GEN-END:variables
}
