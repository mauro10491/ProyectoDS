/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.sql.*;
import clases.Conexion;
import javax.swing.JOptionPane;

/**
 *
 * @author mauri
 */
public class Login extends javax.swing.JFrame {
    
    public static String user = "";
    String pass = "";

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        setSize(400,550);
        setResizable(false);
        setTitle("Acceso al sistema");
        setLocationRelativeTo(null);
        
        ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jlabel_wallpaper.getWidth(), jlabel_wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jlabel_wallpaper.setIcon(icono);
        this.repaint();
        
    }
    
    @Override
    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/icon.png"));
        return retValue;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlabel_Logo = new javax.swing.JLabel();
        txt_user = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        jbutton_acceder = new javax.swing.JButton();
        jlabel_footer = new javax.swing.JLabel();
        jlabel_wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jlabel_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 270, 270));

        txt_user.setBackground(new java.awt.Color(153, 153, 255));
        txt_user.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txt_user.setForeground(new java.awt.Color(255, 255, 255));
        txt_user.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_user.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 330, 210, -1));

        txt_password.setBackground(new java.awt.Color(153, 153, 255));
        txt_password.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txt_password.setForeground(new java.awt.Color(255, 255, 255));
        txt_password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_password.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 370, 210, -1));

        jbutton_acceder.setBackground(new java.awt.Color(153, 153, 255));
        jbutton_acceder.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jbutton_acceder.setForeground(new java.awt.Color(255, 255, 255));
        jbutton_acceder.setText("Acceder");
        jbutton_acceder.setBorder(null);
        jbutton_acceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutton_accederActionPerformed(evt);
            }
        });
        getContentPane().add(jbutton_acceder, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 420, 210, 35));

        jlabel_footer.setText("creado por Mauricio");
        getContentPane().add(jlabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, -1, -1));
        getContentPane().add(jlabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbutton_accederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutton_accederActionPerformed
        // TODO add your handling code here:
        
        user = txt_user.getText().trim();
        pass = txt_password.getText().trim();
        
        if(!user.equals("") || !pass.equals("")){
            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement("select tipo_nivel, estatus from usuarios where username = '" + user + "' and password = '" + pass + "'");
                
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    String tipo_nivel = rs.getString("tipo_nivel"); 
                    String estatus = rs.getString("estatus"); 
                    
                    if(tipo_nivel.equalsIgnoreCase("Administrador") && estatus.equalsIgnoreCase("Activo")){
                        dispose();
                        new Administrador().setVisible(true);
                    } else if(tipo_nivel.equalsIgnoreCase("Capturista") && estatus.equalsIgnoreCase("Activo")){
                        dispose();
                        new Capturista().setVisible(true);
                    } else if(tipo_nivel.equalsIgnoreCase("Tecnico") && estatus.equalsIgnoreCase("Activo")){
                        dispose();
                        new Tecnico().setVisible(true);  
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Datos de acceso incorrecto");
                    txt_user.setText("");
                    txt_password.setText("");
                }
                 
            } catch (SQLException e) {
                System.err.println("Error en el boton acceder " + e);
                JOptionPane.showMessageDialog(null, "Error al iniciar sesion, comunicate con el administrador");

            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes completar todos los campos");
        }
    }//GEN-LAST:event_jbutton_accederActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbutton_acceder;
    private javax.swing.JLabel jlabel_Logo;
    private javax.swing.JLabel jlabel_footer;
    private javax.swing.JLabel jlabel_wallpaper;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_user;
    // End of variables declaration//GEN-END:variables
}
