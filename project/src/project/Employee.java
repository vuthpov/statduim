/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import controls.MyModel;
import java.sql.PreparedStatement;
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
     * @return the currentfullName
     */
    public static String getCurrentfullName() {
        return currentfullName;
    }

    /**
     * @param aCurrentfullName the currentfullName to set
     */
    public static void setCurrentfullName(String aCurrentfullName) {
        currentfullName = aCurrentfullName;
    }

    /**
     * @return the currentRole
     */
    public static String getCurrentRole() {
        return currentRole;
    }

    /**
     * @param aCurrentRole the currentRole to set
     */
    public static void setCurrentRole(String aCurrentRole) {
        currentRole = aCurrentRole;
    }

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
    
    static PreparedStatement preparedStmt;
    
    
    static String sql;
    
    
    static boolean success=false;
    private static int currentEmpId;
    private static String currentfullName;
    private static String currentRole;
    
    
    public static class Job{

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
    
    
    
    public static String getLastInsertUserId(){
        sql="select max(userId) from user;";
        return dataCon.one_cell_value(sql)+"";
    }
    
    
    public static class Role{

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
    
    public static String getLastInsertId(){
        sql="select max(empId) from employee";
        return dataCon.one_cell_value(sql)+"";
    }
    
    public static DefaultComboBoxModel getRole(){
        sql="select * from role;";
        
        DefaultComboBoxModel mod=new DefaultComboBoxModel();
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                do{
                    Role job=new Role();
                    job.setId(rs.getInt(1));
                    job.setRole(rs.getString(2));
                    
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
        
        
        
        sql="select fName 'First Name', lName 'Last Name', Gender, Job,date_format(dob,'%d/%m/%Y')  'Date of Birth', date_format(hiredDate,'%d/%m/%Y') 'Hired Date',format(salary,2)  'Salary', address 'Address', tel 'Phone',ifnull(Email,\"\"),ifnull(Username,'') as 'Username' ,ifnull(Role,'') 'Role',\n" +
" (case \n" +
"	when is_active=0 then 'No'\n" +
"    when is_active=1 then 'Yes'\n" +
"    else '' end) as 'Active',\n" +
" photo 'Photo' ,e.empid,ifnull(u.userId,'') from employee e join job j on e.jobId=j.jobid left join user u on e.empId=u.empId left join role r on u.roleId=r.roleid order by empid desc;";
        
        
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
    
    public static boolean updatePassword(String password){
        success=true;
        
        try{
            sql="update user set password=? where empId="+getCurrentEmpId()+";";
            preparedStmt=dataCon.getCon().prepareStatement(sql);
            
            preparedStmt.setString(1, password);
            preparedStmt.execute();
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null,ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);
        }
        
        success=closeConnection();
        
        return success;
    }
    
    
    public static boolean deleteUser(String userId){
        success=true;
        
        sql="delete from user where userId="+userId+";";
        
        try{
            preparedStmt=dataCon.getCon().prepareStatement(sql);
            preparedStmt.execute();
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null,ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);
        }
        
        
        success=closeConnection();
        
        return success;
    }
    
    public static boolean update(int empId,String[] user,String... emp){
        
        success=true;
        
        
        sql="update employee set fName=?,lName=?,gender=?,jobid=?,dob=?,hiredDate=?,salary=?,address=?,tel=?,email=?,photo=? where empId="+empId+";";
        
        try{
            dataCon.getCon().setAutoCommit(false);
            
            preparedStmt=dataCon.getCon().prepareStatement(sql);
            
            for(int i=1;i<=emp.length;i++){
                preparedStmt.setString(i, emp[i-1]);
            }
            
            preparedStmt.execute();
            

            sql="select count(*) from user where empId="+empId+"";
            Long countUserExist=(Long)dataCon.one_cell_value(sql);


            if(countUserExist==1){
                if(user[0].equals("")){
                    JOptionPane.showMessageDialog(null,"Username cannot be empty","",JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                sql="update user set username=?,password=?,roleId=? where empId="+empId+"";
            }
            else{
                if(user[0].equals("")){
                    
                    try {
                        dataCon.getCon().setAutoCommit(true);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);
                        success=false;
                    }

                    closeConnection();
                    
                    
                   return true; 
                }
                
                
                sql="insert into user (username,password,roleId,empId,is_active) values(?,?,?,?,?)";
                
            }

            preparedStmt=dataCon.getCon().prepareStatement(sql);
            for(int i=1;i<=user.length;i++){
                preparedStmt.setString(i, user[i-1]);
            }

            if(countUserExist==0){
                preparedStmt.setString(4, empId+"");
                preparedStmt.setString(5, "1");
            }
                    
            
            
            preparedStmt.execute();
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null,ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);
        }
        
        
        try {
            dataCon.getCon().setAutoCommit(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);
            success=false;
        }
        
        closeConnection();
        
        return success;
    }
    
    
    
    
    public static boolean delete(int id){
        success=true;
        
        try{
            
            preparedStmt=dataCon.getCon().prepareStatement("delete from employee where empid="+id);
            
            preparedStmt.execute();
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null,ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);
        }
        
        
        success=closeConnection();
        
        return success;
    }
    
    public static boolean setActivate(int id,int active){
        success=true;
        
        try{
            
            preparedStmt=dataCon.getCon().prepareStatement("update user set is_active="+active+" where userid="+id);
            
            preparedStmt.execute();
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null,ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);
        }
        
        return success;
    }
    
    public static boolean insert(String []user,String... data){
        
        success=true;
        
        try{
            dataCon.getCon().setAutoCommit(false);
            
            
            
            
            
            
            sql="insert into employee (fName,lName,gender,jobid,dob,hiredDate,salary,address,tel,email,photo) values(?,?,?,?,?,?,?,?,?,?,?);";
            
            preparedStmt=dataCon.getCon().prepareStatement(sql);
            
            
            for(int i=1;i<=data.length;i++){
                preparedStmt.setString(i,data[i-1]);
            }
            
            
            preparedStmt.execute();
            

            
            if(!user[0].equals("")){
                sql="select max(empId) from employee;";
                String empId=dataCon.one_cell_value(sql)+"";
                
                sql="insert into user (username,password,roleId,empId,is_active) values(?,?,?,?,?)";
                preparedStmt=dataCon.getCon().prepareStatement(sql);

                for(int i=1;i<=user.length;i++){
                    preparedStmt.setString(i,user[i-1]);
                }
                
                preparedStmt.setString(user.length+1, empId);
                preparedStmt.setString(user.length+2, "1");
                
                preparedStmt.execute();

            }
            
           
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);
            
            try {
                dataCon.getCon().rollback();
            } catch (SQLException ex1) {
                 JOptionPane.showMessageDialog(null,ex1.getMessage(),"",JOptionPane.ERROR_MESSAGE);
            }
            success=false;
        }
        
        
        try {
            dataCon.getCon().setAutoCommit(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);
            success=false;
        }
        
        closeConnection();
        
        
        return success;
    }
    
    static boolean closeConnection(){
        try{
           preparedStmt.close(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
}


