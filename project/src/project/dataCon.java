/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;





import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;





/**
 *
 * @author Nemesis
 */
public class dataCon {
    private static Connection Con;
    
    static Statement stmt;
    static ResultSet rs;



    
    
    public static void connectToDB()throws Exception{
        Class.forName("com.mysql.jdbc.Driver");  
        Con=DriverManager.getConnection("jdbc:mysql://localhost/staduim","root","");     
    }

    /**
     * @return the Con
     */
    public static Connection getCon() {
        return Con;
    }

    /**
     * @param aCon the Con to set
     */
    public static void setCon(Connection aCon) {
        Con = aCon;
    }
    
    public static void executeQry(String sql,DefaultTableModel mod){
        
        try {
            Statement stmt=Con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
           
            
            ResultSetMetaData rsmd = rs.getMetaData();
                        
            if(rs.first()){

                Object []row=new Object [rsmd.getColumnCount()];
                
                do{
                    
                    for(int i=0;i<rsmd.getColumnCount();i++){
                       row[i]=rs.getObject(i+1);
                    }
                    
                    mod.addRow(row);
                                      
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    
    
    
    public static boolean executeActionQry(List<String> sql){
        boolean error=false;
        try{
            
            Con.setAutoCommit(false);
             
            Statement stmt=Con.createStatement();
            
            
           for (String  s : sql) 
            { 
                stmt.addBatch(s);
            }
     
           
            stmt.executeBatch();          
            Con.commit();

            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
            try {
                Con.rollback();
            } catch (SQLException ex1) {
                
            }
            error=true;
        }
            
        try{
           Con.setAutoCommit(true);
           Con.close(); 
        }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, ex.getMessage());
        } 
        
      
        return error;
    }
    
    
    
    

    
    public static Object one_cell_value(String sql){
        
        Object result=new Object();
        
        try {
            Statement stmt=Con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                 result=rs.getObject(1);
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        }
        
        return result;
        
    }
}
    
    

    

    
   

    

    

    

