/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nemesis
 */
public class Employee {
    
    
    
    public static void getEmployeeList(DefaultTableModel modelEmployee){
        
        String sql="select fName as 'First Name', lName 'Last Name', Gender, Job, dob 'Date of Birth', hiredDate 'Hired Date', address 'Address', tel 'Phone', photo 'Photo' , Username,Role "
                + "from employee e join job j on e.jobId=j.id join user u on e.userId=u.id join role r on u.id=r.id order by e.id desc;";
        
        
         try {
            Statement stmt=dataCon.getCon().createStatement();
            ResultSet rs=stmt.executeQuery(sql);
           
            
            ResultSetMetaData rsmd = rs.getMetaData();
            
            
            
            if(rs.first()){
                
                Object []row=new Object [rsmd.getColumnCount()+1];
                int autoNumber=1;
                do{
                    
                    row[0]=autoNumber;
                    for(int i=0;i<rsmd.getColumnCount();i++){
                       row[i+1]=rs.getObject(i+1)+"";
                    }
                    
                    modelEmployee.addRow(row);
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


