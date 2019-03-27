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
import static project.League.preparedStmt;

/**
 *
 * @author Nemesis
 */
public class Club {

    /**
     * @return the modelCbLeague
     */
    public static DefaultComboBoxModel getModelCbLeague() {
        return modelCbLeague;
    }

    /**
     * @param aModelCbLeague the modelCbLeague to set
     */
    public static void setModelCbLeague(DefaultComboBoxModel aModelCbLeague) {
        modelCbLeague = aModelCbLeague;
    }
    static String sql="";
    static Statement stmt;
    static ResultSet rs;

    static PreparedStatement preparedStmt;
    
    private static DefaultComboBoxModel modelCbLeague=new DefaultComboBoxModel();
    
    
    static class IdAndName{

        
        
        private String id;
        private String name;
      
        /**
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name; 
        }

        public IdAndName() {
        
        }

        public IdAndName(String id, String name) {
            this.id = id;
            this.name = name;
        }
        
        
        
        
    }
    
    
    static class IdNameAndImage extends IdAndName{

        /**
         * @return the image
         */
        public String getImage() {
            return image;
        }

        /**
         * @param image the image to set
         */
        public void setImage(String image) {
            this.image = image;
        }
    
        private String image;

        @Override
        public String toString() {
            return this.image;
        }

        public IdNameAndImage() {
        }

        public IdNameAndImage(String id,String name,String image) {
            super(id,name);
            this.image = image;
        }
        
        
        
        
        
    }
    
    static class IdNameNoImage extends IdAndName{

        /**
         * @return the image
         */
        public String getImage() {
            return image;
        }

        /**
         * @param image the image to set
         */
        public void setImage(String image) {
            this.image = image;
        }
    
        private String image;

        @Override
        public String toString() {
            return super.name;
        }

        public IdNameNoImage() {
        }

        public IdNameNoImage(String id, String name,String image) {
            super(id, name);
            this.image = image;
        }
        
        
        
        
    }
    
    
    static boolean success=false;
    
    public static boolean insert(String... data){
        success=true;
        
        sql="insert into club(club,nickname,leagueId,photo) values(?,?,?,?)";
        
        try{
            preparedStmt=dataCon.getCon().prepareStatement(sql);
            for(int i=1;i<=data.length;i++){
                preparedStmt.setString(i, data[i-1]);
            }
            
            preparedStmt.execute();
        }catch(SQLException ex){
             JOptionPane.showMessageDialog(null, ex.getMessage());
             success=false;
        }
        
        
        
        
        
        return success;
        
    }
    
    public static boolean update(String clubId,String... data){
        success=true;
        
        sql="update club set club=?,nickname=?,leagueId=?,photo=? where clubId="+clubId+"";
        
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
    
    public static boolean delete(String id){
        success=true;
        
        sql="delete from club where clubId="+id+";";
        
        try{
            preparedStmt=dataCon.getCon().prepareStatement(sql);
           
            preparedStmt.execute();
        }catch(SQLException ex){
             JOptionPane.showMessageDialog(null, ex.getMessage());
             success=false;
        }
        
       
        
        return success;
    }
    
    
    
    
    
    public static void getClubList(DefaultTableModel modelClub){
        
        sql="select club,nickname,l.leagueId,l.league,l.photo ,c.photo,clubId from club c join league l on c.leagueId=l.leagueId;";
        
         try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            ResultSetMetaData rsmd = rs.getMetaData();
            
            
            
            if(rs.first()){
                
                Object []row=new Object [rsmd.getColumnCount()+1];
                int autoNumber=1;
                do{
                    
                    row[0]=autoNumber;
                    row[1]=rs.getObject(1)+"";
                    row[2]=rs.getObject(2)+"";
                    
                    IdNameAndImage obj=new IdNameAndImage();
                    obj.setId(rs.getObject(3)+"");
                    obj.setName(rs.getObject(4)+"");
                    obj.setImage(rs.getObject(5)+"");
                    
                    row[3]=obj;
                    
                    row[4]=rs.getObject(6)+"";
                    
                    row[5]=rs.getObject(7)+"";
                    
                    
                    
                    
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
