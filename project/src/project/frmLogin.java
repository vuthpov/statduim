/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static project.Employee.sql;

/**
 *
 * @author Nemesis
 */
public class frmLogin extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public frmLogin() {
        
        
        initComponents();
        Color color=new Color(34, 45, 49);
        getContentPane().setBackground(color);
        
        ComponentMover mover=new ComponentMover(this,pHeader);
        
        
        
        
        try {
            dataCon.connectToDB();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        File dir = new File("");
        projectDir = dir.getAbsolutePath()+"\\";
        
        

        
        try{
            File f=new File(projectDir+"src\\project\\files\\rmbMe.txt");
            
            if(!f.exists())
                return;
            
            FileInputStream inputStream=new FileInputStream(f);
            BufferedInputStream bufferInput=new BufferedInputStream(inputStream);
            
           
            Scanner scanner = new Scanner(bufferInput);
            
            cbRmbMe.setSelected(scanner.hasNext());
            
            if(scanner.hasNext())
                txtUsername.setText(scanner.nextLine());
            
            
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        

        
    }
    
    String projectDir="";

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUsername = new JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnLogin = new keeptoo.KButton();
        txtPassword = new javax.swing.JPasswordField();
        pHeader = new javax.swing.JPanel();
        lbClose = new javax.swing.JLabel();
        lbMinimise = new javax.swing.JLabel();
        cbRmbMe = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(34, 45, 49));
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Username");

        txtUsername.setBackground(new java.awt.Color(34, 45, 49));
        txtUsername.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(240, 240, 240));
        txtUsername.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Password");

        btnLogin.setBackground(new java.awt.Color(44, 59, 66));
        btnLogin.setBorder(null);
        btnLogin.setText("Login");
        btnLogin.setBorderPainted(false);
        btnLogin.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnLogin.setkAllowGradient(false);
        btnLogin.setkBackGroundColor(new java.awt.Color(44, 59, 66));
        btnLogin.setkEndColor(new java.awt.Color(25, 20, 14));
        btnLogin.setkHoverColor(new java.awt.Color(5, 60, 67));
        btnLogin.setkHoverEndColor(new java.awt.Color(9, 117, 171));
        btnLogin.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnLogin.setkHoverStartColor(new java.awt.Color(9, 117, 171));
        btnLogin.setkIndicatorThickness(0);
        btnLogin.setkPressedColor(new java.awt.Color(44, 59, 66));
        btnLogin.setkSelectedColor(new java.awt.Color(44, 59, 66));
        btnLogin.setkStartColor(new java.awt.Color(25, 20, 14));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        txtPassword.setBackground(new java.awt.Color(34, 45, 49));
        txtPassword.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(240, 240, 240));
        txtPassword.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        pHeader.setBackground(new java.awt.Color(34, 45, 49));

        lbClose.setBackground(new java.awt.Color(250, 250, 250));
        lbClose.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        lbClose.setForeground(new java.awt.Color(255, 255, 255));
        lbClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/Icon/close.png"))); // NOI18N
        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCloseMouseClicked(evt);
            }
        });

        lbMinimise.setBackground(new java.awt.Color(250, 250, 250));
        lbMinimise.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        lbMinimise.setForeground(new java.awt.Color(255, 255, 255));
        lbMinimise.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/Icon/minimise.png"))); // NOI18N
        lbMinimise.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMinimiseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pHeaderLayout = new javax.swing.GroupLayout(pHeader);
        pHeader.setLayout(pHeaderLayout);
        pHeaderLayout.setHorizontalGroup(
            pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbMinimise)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbClose)
                .addGap(16, 16, 16))
        );
        pHeaderLayout.setVerticalGroup(
            pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMinimise)
                    .addComponent(lbClose))
                .addContainerGap())
        );

        cbRmbMe.setForeground(new java.awt.Color(240, 240, 240));
        cbRmbMe.setText("Remember me");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbRmbMe)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                    .addComponent(txtPassword))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(cbRmbMe)
                .addGap(31, 31, 31)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    void CheckRmbMe(){
        String contentToWrite="";
        
        if(cbRmbMe.isSelected()){
            contentToWrite=txtUsername.getText();
        }
        
        try{
          

            FileWriter fw=new FileWriter(projectDir+"src\\project\\files\\rmbMe.txt");
            fw.write(contentToWrite);    
            fw.close();    

                
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username=txtUsername.getText();
        char [] ch=txtPassword.getPassword();
        String password="";
        for (char p : ch){
            password+=p;
        }
        
        
        sql="select e.empId,fname,lname,role,is_active from user u join employee e on e.empId=u.empId join role r on u.roleId=r.roleId where username='"+username+"' and password='"+password+"'";
        
        
        
         try {
            Statement stmt=dataCon.getCon().createStatement();
            ResultSet rs=stmt.executeQuery(sql);
           
            
            
            if(rs.first()){
                
                if(rs.getInt(5)==0){
                    JOptionPane.showMessageDialog(this, "User deactivated");
                    return;
                }
                
                Employee.setCurrentEmpId(rs.getInt(1));
                Employee.setCurrentfullName(rs.getString(2)+" "+rs.getString(3));
                Employee.setCurrentRole(rs.getString(4));
                
                frmMain main=new frmMain();
                main.setVisible(true);
                dispose();
                
                
                
               
            }else{
                JOptionPane.showMessageDialog(this, "Wrong username or password");
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
     
        
        
        CheckRmbMe();
        
    }//GEN-LAST:event_btnLoginActionPerformed

    private void lbCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseClicked
        dispose();
    }//GEN-LAST:event_lbCloseMouseClicked

    private void lbMinimiseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMinimiseMouseClicked
        setState(this.ICONIFIED);
    }//GEN-LAST:event_lbMinimiseMouseClicked

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
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            
            public void run() {
            
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.KButton btnLogin;
    private javax.swing.JCheckBox cbRmbMe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbMinimise;
    private javax.swing.JPanel pHeader;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}

