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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nemesis
 */
public class Employee {

    /**
     * @return the currentEmpId
     */
    public static int getCurrentEmpId() {
        return currentEmpId;
    }

    /**
     * @param aCurrentEmpId the currentEmpId to set
     */
    public static void setCurrentEmpId(int aCurrentEmpId) {
        currentEmpId = aCurrentEmpId;
    }
    
    static Statement stmt;
    static ResultSet rs;
    
    
    static String sql;
    
    private static int currentEmpId;
    
    static class Job{

        /**
         * @return the job
         */
        public String getJob() {
            return job;
        }

        /**
         * @param job the job to set
         */
        public void setJob(String job) {
            this.job = job;
        }

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(int id) {
            this.id = id;
        }
        private int id;
        private String job;

        @Override
        public String toString() {
            return job;
        }
        
        
    }
    
    static class Role{

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * @return the role
         */
        public String getRole() {
            return role;
        }

        /**
         * @param role the role to set
         */
        public void setRole(String role) {
            this.role = role;
        }
        private int id;
        private String role;

        @Override
        public String toString() {
            return role;
        }
    }
    
    public static DefaultComboBoxModel getRole(){
        sql="select * from role;";
        
        DefaultComboBoxModel mod=new DefaultComboBoxModel();
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                do{
                    Job job=new Job();
                    job.setId(rs.getInt(1));
                    job.setJob(rs.getString(2));
                    
                    mod.addElement(job);
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return mod;
    }
    
   
    public static DefaultComboBoxModel getJob(){
        
        sql="select * from job;";
        
        DefaultComboBoxModel mod=new DefaultComboBoxModel();
         try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                do{
                    Job job=new Job();
                    job.setId(rs.getInt(1));
                    job.setJob(rs.getString(2));
                    
                    mod.addElement(job);
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
         return mod;
    }
    
    
    
    public static void getEmployeeList(DefaultTableModel modelEmployee){
        
        sql="select fName as 'First Name', lName 'Last Name', Gender, Job, dob 'Date of Birth', hiredDate 'Hired Date',salary, address 'Address', tel 'Phone', photo 'Photo' , Username,Role "
                + "from employee e join job j on e.jobId=j.id join user u on e.userId=u.id join role r on u.id=r.id order by e.id desc;";
        
        
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


