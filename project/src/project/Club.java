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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static project.Employee.stmt;

/**
 *
 * @author Nemesis
 */
public class Club {
    static String sql="";
    static Statement stmt;
    static ResultSet rs;

    static PreparedStatement preparedStmt;
    
    
    public static void getClubList(DefaultTableModel modelClub){
        
        sql="select club,nickname,leagueId,photo,clubId from club";
        
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
                    
                    modelClub.addRow(row);
                    autoNumber++;     
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    
}
