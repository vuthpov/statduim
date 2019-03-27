/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import project.Club.IdNameNoImage;

/**
 *
 * @author Nemesis
 */
public class League {
    static String sql="";
    static Statement stmt;
    static ResultSet rs;

    static PreparedStatement preparedStmt;
    static boolean success;
    
    public static void getLeagueList(DefaultTableModel modelLeague){
        
        sql="select league,photo,leagueId from league;";
        
         try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            ResultSetMetaData rsmd = rs.getMetaData();
            
            
            
            if(rs.first()){
                
                Object []row=new Object [rsmd.getColumnCount()+1];
                int autoNumber=1;
                do{
                    
                    row[0]=autoNumber;
                    for(int i=0;i<rsmd.getColumnCount();i++){
                       row[i+1]=rs.getObject(i+1)+"";
                    }
                    
                    modelLeague.addRow(row);
                    autoNumber++;     
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    
    public static void getLeagueList(DefaultComboBoxModel modelLeague){
        
        
        sql="select league,leagueId,photo from league";
        
         try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                
                do{
                    
                    IdNameNoImage obj=new IdNameNoImage();
                    obj.setId(rs.getString(2));
                    
                    obj.setName(rs.getString(1));
                    
                    obj.setImage(rs.getString(3));
                    
                    modelLeague.addElement(obj);
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    
    public static boolean delete(int leagueId){
        success=true;
        
        sql="delete from league where leagueId="+leagueId+";";
        
        
        try{
            preparedStmt=dataCon.getCon().prepareStatement(sql);
            preparedStmt.execute();
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null,ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);
        }
        
       
        
        return success;
    }
    
    public static boolean add(String... data){
        
        success=true;
        
        sql="insert into league (league,photo) values(?,?);";
        
        
        try{
            preparedStmt=dataCon.getCon().prepareStatement(sql);
            
            
            for(int i=1;i<=data.length;i++){
                preparedStmt.setString(i, data[i-1]);
            }
            
            preparedStmt.execute();
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null,ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);
        }
        
       
        
        return success;
        
    }
    
    public static boolean update(int leagueId,String... data){
        
        success=true;
        sql="update league set league=?, photo=? where leagueId="+leagueId+";";
         
        try{
            preparedStmt=dataCon.getCon().prepareStatement(sql);
            
            
            for(int i=1;i<=data.length;i++){
                preparedStmt.setString(i, data[i-1]);
            }
            
            preparedStmt.execute();
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null,ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);
        }
        
        
        
        return success;
    }
    
    
    public static String getLastInsertId(){
        
        sql="select max(leagueId) from league";
        
        return dataCon.one_cell_value(sql)+"";
        
    }
}
