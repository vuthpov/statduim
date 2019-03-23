/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import controls.MyModel;
import controls.WaterMarkedTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import project.Employee.Job;
import project.Employee.Role;

/**
 *
 * @author Nemesis
 */
public class pEmployee extends javax.swing.JPanel {

    /**
     * Creates new form pEmployee
     */
    
    FileFilter imageFilter = new FileNameExtensionFilter(
    "Image files", ImageIO.getReaderFileSuffixes());
    
    
    
    
    String originImagePath;
    public pEmployee(JDialog dialog,MyModel modelEmployee) {
        initComponents();
        
        preparedForm(dialog,modelEmployee);
    }
    
    public pEmployee(JDialog dialog,MyModel modelEmployee,int selectedRowIndex) {
        initComponents();
        
        preparedForm(dialog,modelEmployee);
        
        
        this.selectedRowIndex=selectedRowIndex;
        
        prepareValueForEdit(selectedRowIndex);
        
        
        
        
            
       
    }
    
    Date castStringToDate(String formatPattern,String st){
        Date d=null;
        try {
            d=new SimpleDateFormat(formatPattern).parse(st); 
            
        } catch (ParseException ex) {
            System.err.println();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return d;
    }
    
    
    boolean checkIfDataIsDifferent(){
        for(WaterMarkedTextField txt : allTxt){
            if(!txt.getThisText().equals(txt.getOriginValue())){
                return true;
            }
        }

        String gender=rMale.isSelected()?"Male":"Female";
        
        if(!gender.equals(modelEmployee.getValueAt(selectedRowIndex, 3))) return true;
        
        
        if(!dDob.getStringValue(dDob.getFormatPattern()).equals(modelEmployee.getValueAt(selectedRowIndex, 5)) || !dHiredDate.getStringValue(dDob.getFormatPattern()).equals(modelEmployee.getValueAt(selectedRowIndex, 6))){
            return true;
        }
        
        
        Job job=(Job)cbJob.getSelectedItem();
        
        
        
        if(!job.getJob().equals(modelEmployee.getValueAt(selectedRowIndex, 4)+"")) return true;
        
        Role role=(Role)cbRole.getSelectedItem();
        
        
        if(!modelEmployee.getValueAt(selectedRowIndex, 12).equals(""))
            if(!role.getRole().equals(modelEmployee.getValueAt(selectedRowIndex, 12))) return true;
        
        if(!(modelEmployee.getValueAt(selectedRowIndex, 14)+"").equals(pbImage.getIconAbsolutePath())){
            return true;
        }
        
        
        return false;
        
        
    }
    
    
    int selectedRowIndex=-1;
    
    void preparedForm(JDialog dialog,MyModel modelEmployee){
        
        cbJob.setModel(Employee.getJob());
        cbRole.setModel(Employee.getRole());
        
        
        MouseAdapter mouse=new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()){
                    popUpImage.show(pbImage,e.getX(),e.getY());
                }
            }
        };
        
        pbImage.addMouseListener(mouse);
        
        originImagePath=pbImage.getIconAbsolutePath();
        
        
        
       
        WaterMarkedTextField[] requiredTxt={txtFname,txtLname,txtAddress,txtPhone};
        this.requiredTxt=requiredTxt;
        
        WaterMarkedTextField[] allTxt={txtFname,txtLname,txtEmail,txtAddress,txtPhone,txtSalary,txtUsername,txtPassword};
        
        this.allTxt=allTxt;
        
        this.dialog=dialog;
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.addWindowListener(new WindowAdapter() {
                      
            public void windowClosing(WindowEvent we) {
                
                String []options={"Yes","No"};
                boolean showOptionPane=false;
                
                if(selectedRowIndex>=0){
                    showOptionPane=checkIfDataIsDifferent();
                }else{
                    showOptionPane=!checkIfEmpty(allTxt);
                }
                
                if(showOptionPane){
                    int n = JOptionPane.showOptionDialog(null,
                            "Data will be lost. Proceed?",
                            "",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[1]);

                        if(n!=0){
                            return;
                        }
                }
                
                    
                
                dialog.dispose();
            }

        });
        
        this.modelEmployee=modelEmployee;
        
        defaultIcon=pbImage.getIcon();
        
    }
    
    
    
    
    Icon defaultIcon=null;
    MyModel modelEmployee;
    JDialog dialog;
    WaterMarkedTextField[] requiredTxt;
    WaterMarkedTextField[] allTxt;
    
    boolean checkIfEmpty(WaterMarkedTextField... JTextField){
        for(WaterMarkedTextField txt : JTextField){
            
            if(!txt.isEmpty()){
                return false;
            }
        }
        
        return true;
    }
    
    
    
    boolean checkInputData(WaterMarkedTextField... JTextField){
        boolean hasError=false;
        for(WaterMarkedTextField txt : JTextField){
            if(txt.isEmpty()){
                txt.setCustomBorder(new Color(203,25,21));
                hasError=true;
            }else{
                txt.resetBorder();
            }
        }
        
        return hasError;
    }
    
    
    void prepareValueAfterEdit(int selectedRowIndex){
        txtFname.setOriginValue(modelEmployee.getValueAt(selectedRowIndex, 1)+"");
        txtLname.setOriginValue(modelEmployee.getValueAt(selectedRowIndex, 2)+"");
        
        
        txtSalary.setOriginValue(modelEmployee.getValueAt(selectedRowIndex,7)+"");

        txtAddress.setOriginValue(modelEmployee.getValueAt(selectedRowIndex,8)+"");

        txtPhone.setOriginValue(modelEmployee.getValueAt(selectedRowIndex,9)+"");

        txtEmail.setOriginValue(modelEmployee.getValueAt(selectedRowIndex,10)+"");

        txtUsername.setOriginValue(modelEmployee.getValueAt(selectedRowIndex,11)+"");

        txtPassword.setOriginValue(txtPassword.getThisText());
        
        prepareTheRest(selectedRowIndex);
    }
    
    
    void prepareTheRest(int selectedRowIndex){
                if(modelEmployee.getValueAt(selectedRowIndex, 3).equals("Female")) rFemale.setSelected(true);
        
        
        Object obj;
        
        for(int i=0;i<cbJob.getModel().getSize();i++){
            
            obj=cbJob.getModel().getElementAt(i);
            Job j=(Job)obj;
            
            if(j.getJob().equals(modelEmployee.getValueAt(selectedRowIndex, 4))){
                cbJob.setSelectedIndex(i);
                break;
            }
        }
        
        
        
        String formatPattern="dd/MM/yyyy";
        
        Date d=castStringToDate(formatPattern,modelEmployee.getValueAt(selectedRowIndex,5)+"") ;
        
        dDob.setValue(d);
        
        d=castStringToDate(formatPattern,modelEmployee.getValueAt(selectedRowIndex,6)+"");
        
        dHiredDate.setValue(d);
        
        if(!(modelEmployee.getValueAt(selectedRowIndex,12)+"").equals("")){
             for(int i=0;i<cbRole.getModel().getSize();i++){
                 obj=cbRole.getModel().getElementAt(i);
                 Role role=(Role)obj;
                 if(role.getRole().equals(modelEmployee.getValueAt(selectedRowIndex, 12))){
                     cbRole.setSelectedIndex(i);
                     break;
                 }
             }
        }
        
        
       
        
        File f=new File(modelEmployee.getValueAt(selectedRowIndex, 14)+"");
        if(f.exists()){
            ImageIcon icon=new ImageIcon(f.getAbsolutePath());
            pbImage.setIcon(icon);
        }
    }
    
    
    void prepareValueForEdit(int seletedRowIndex){
        txtFname.setOriginValueAndSetTxt(modelEmployee.getValueAt(selectedRowIndex, 1)+"");
        txtLname.setOriginValueAndSetTxt(modelEmployee.getValueAt(selectedRowIndex, 2)+"");
        
        
        txtSalary.setOriginValueAndSetTxt(modelEmployee.getValueAt(selectedRowIndex,7)+"");

        txtAddress.setOriginValueAndSetTxt(modelEmployee.getValueAt(selectedRowIndex,8)+"");

        txtPhone.setOriginValueAndSetTxt(modelEmployee.getValueAt(selectedRowIndex,9)+"");

        txtEmail.setOriginValueAndSetTxt(modelEmployee.getValueAt(selectedRowIndex,10)+"");

        txtUsername.setOriginValueAndSetTxt(modelEmployee.getValueAt(selectedRowIndex,11)+"");

        txtPassword.setOriginValue(txtPassword.getThisText());
        
        prepareTheRest(selectedRowIndex);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        popUpImage = new javax.swing.JPopupMenu();
        popBrowse = new javax.swing.JMenuItem();
        popRemove = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pFname = new javax.swing.JPanel();
        txtFname = new controls.WaterMarkedTextField("");
        jPictureBox1 = new controls.JPictureBox();
        jSeparator2 = new javax.swing.JSeparator();
        pFname1 = new javax.swing.JPanel();
        txtLname = new controls.WaterMarkedTextField("");
        jPictureBox2 = new controls.JPictureBox();
        jSeparator3 = new javax.swing.JSeparator();
        rMale = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        rFemale = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        cbJob = new javax.swing.JComboBox<>();
        dDob = new controls.JDateTimePicker();
        jLabel5 = new javax.swing.JLabel();
        dHiredDate = new controls.JDateTimePicker();
        jLabel6 = new javax.swing.JLabel();
        pbImage = new controls.JPictureBox();
        jLabel7 = new javax.swing.JLabel();
        pFname2 = new javax.swing.JPanel();
        txtAddress = new controls.WaterMarkedTextField("");
        jPictureBox4 = new controls.JPictureBox();
        jSeparator4 = new javax.swing.JSeparator();
        pFname3 = new javax.swing.JPanel();
        jPictureBox5 = new controls.JPictureBox();
        jSeparator5 = new javax.swing.JSeparator();
        txtPhone = new controls.SubJTextField();
        jLabel8 = new javax.swing.JLabel();
        pFname4 = new javax.swing.JPanel();
        txtEmail = new controls.WaterMarkedTextField("");
        jPictureBox6 = new controls.JPictureBox();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        pFname6 = new javax.swing.JPanel();
        txtPassword = new controls.WaterMarkedTextField("");
        jPictureBox8 = new controls.JPictureBox();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        pFname7 = new javax.swing.JPanel();
        txtUsername = new controls.WaterMarkedTextField("");
        jPictureBox9 = new controls.JPictureBox();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        cbRole = new javax.swing.JComboBox<>();
        btnConfirm = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        pFname5 = new javax.swing.JPanel();
        jPictureBox7 = new controls.JPictureBox();
        jSeparator8 = new javax.swing.JSeparator();
        txtSalary = new controls.SubJTextField();

        popBrowse.setText("Browse");
        popBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBrowseActionPerformed(evt);
            }
        });
        popUpImage.add(popBrowse);

        popRemove.setText("Remove");
        popRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popRemoveActionPerformed(evt);
            }
        });
        popUpImage.add(popRemove);

        setPreferredSize(new java.awt.Dimension(1053, 841));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setText("Personal Infomation");

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel2.setText("Name:");

        pFname.setBackground(new java.awt.Color(255, 255, 255));
        pFname.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 1, new java.awt.Color(176, 186, 190)));

        txtFname.setBackground(new java.awt.Color(255, 255, 255));
        txtFname.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtFname.setOriginBorder(txtFname.getBorder());
        txtFname.setText("First name");
        txtFname.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtFname.setPreferredSize(new java.awt.Dimension(0, 52));
        txtFname.setWatermarkedText("First name");

        jPictureBox1.setBackground(new java.awt.Color(255, 255, 255));
        jPictureBox1.setBorder(null);
        jPictureBox1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/Icon/user.png"))); // NOI18N
        jPictureBox1.setImageMode(controls.JPictureBox.mode.Center);
        jPictureBox1.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPictureBox1Layout = new javax.swing.GroupLayout(jPictureBox1);
        jPictureBox1.setLayout(jPictureBox1Layout);
        jPictureBox1Layout.setHorizontalGroup(
            jPictureBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPictureBox1Layout.setVerticalGroup(
            jPictureBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jSeparator2.setBackground(new java.awt.Color(176, 186, 190));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pFnameLayout = new javax.swing.GroupLayout(pFname);
        pFname.setLayout(pFnameLayout);
        pFnameLayout.setHorizontalGroup(
            pFnameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFnameLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtFname, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pFnameLayout.setVerticalGroup(
            pFnameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFnameLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pFnameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPictureBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        txtFname.getAccessibleContext().setAccessibleName("");

        pFname1.setBackground(new java.awt.Color(255, 255, 255));
        pFname1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 186, 190)));

        txtLname.setBackground(new java.awt.Color(255, 255, 255));
        txtLname.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtLname.setOriginBorder(txtLname.getBorder());
        txtLname.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtLname.setPreferredSize(new java.awt.Dimension(0, 52));
        txtLname.setWatermarkedText("Last name");

        jPictureBox2.setBackground(new java.awt.Color(255, 255, 255));
        jPictureBox2.setBorder(null);
        jPictureBox2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/Icon/user.png"))); // NOI18N
        jPictureBox2.setImageMode(controls.JPictureBox.mode.Center);
        jPictureBox2.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPictureBox2Layout = new javax.swing.GroupLayout(jPictureBox2);
        jPictureBox2.setLayout(jPictureBox2Layout);
        jPictureBox2Layout.setHorizontalGroup(
            jPictureBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPictureBox2Layout.setVerticalGroup(
            jPictureBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jSeparator3.setBackground(new java.awt.Color(176, 186, 190));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pFname1Layout = new javax.swing.GroupLayout(pFname1);
        pFname1.setLayout(pFname1Layout);
        pFname1Layout.setHorizontalGroup(
            pFname1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFname1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPictureBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtLname, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pFname1Layout.setVerticalGroup(
            pFname1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFname1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pFname1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPictureBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        txtLname.getAccessibleContext().setAccessibleName("");

        buttonGroup1.add(rMale);
        rMale.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        rMale.setSelected(true);
        rMale.setText("Male");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel3.setText("Gender:");

        buttonGroup1.add(rFemale);
        rFemale.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        rFemale.setText("Female");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel4.setText("Job:");

        cbJob.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbJob.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        cbJob.setPreferredSize(new java.awt.Dimension(340, 52));

        dDob.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        dDob.setFormatPattern("dd/MM/yyyy");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel5.setText("Date of Birth:");

        dHiredDate.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        dHiredDate.setFormatPattern("dd/MM/yyyy");

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel6.setText("Hired Date:");

        pbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/Icon/Employee.png"))); // NOI18N
        pbImage.setImageMode(controls.JPictureBox.mode.Zoom);

        javax.swing.GroupLayout pbImageLayout = new javax.swing.GroupLayout(pbImage);
        pbImage.setLayout(pbImageLayout);
        pbImageLayout.setHorizontalGroup(
            pbImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );
        pbImageLayout.setVerticalGroup(
            pbImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setText("Address:");

        pFname2.setBackground(new java.awt.Color(255, 255, 255));
        pFname2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 1, new java.awt.Color(176, 186, 190)));

        txtAddress.setBackground(new java.awt.Color(255, 255, 255));
        txtAddress.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtAddress.setOriginBorder(txtAddress.getBorder());
        txtAddress.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtAddress.setPreferredSize(new java.awt.Dimension(0, 52));
        txtAddress.setWatermarkedText("Address");

        jPictureBox4.setBackground(new java.awt.Color(255, 255, 255));
        jPictureBox4.setBorder(null);
        jPictureBox4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/Icon/home.png"))); // NOI18N
        jPictureBox4.setImageMode(controls.JPictureBox.mode.Center);
        jPictureBox4.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPictureBox4Layout = new javax.swing.GroupLayout(jPictureBox4);
        jPictureBox4.setLayout(jPictureBox4Layout);
        jPictureBox4Layout.setHorizontalGroup(
            jPictureBox4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPictureBox4Layout.setVerticalGroup(
            jPictureBox4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jSeparator4.setBackground(new java.awt.Color(176, 186, 190));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pFname2Layout = new javax.swing.GroupLayout(pFname2);
        pFname2.setLayout(pFname2Layout);
        pFname2Layout.setHorizontalGroup(
            pFname2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFname2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPictureBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pFname2Layout.setVerticalGroup(
            pFname2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFname2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pFname2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPictureBox4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        txtAddress.getAccessibleContext().setAccessibleName("");

        pFname3.setBackground(new java.awt.Color(255, 255, 255));
        pFname3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 186, 190)));

        jPictureBox5.setBackground(new java.awt.Color(255, 255, 255));
        jPictureBox5.setBorder(null);
        jPictureBox5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/Icon/phone.png"))); // NOI18N
        jPictureBox5.setImageMode(controls.JPictureBox.mode.Center);
        jPictureBox5.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPictureBox5Layout = new javax.swing.GroupLayout(jPictureBox5);
        jPictureBox5.setLayout(jPictureBox5Layout);
        jPictureBox5Layout.setHorizontalGroup(
            jPictureBox5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPictureBox5Layout.setVerticalGroup(
            jPictureBox5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jSeparator5.setBackground(new java.awt.Color(176, 186, 190));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txtPhone.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtPhone.setOriginBorder(txtPhone.getBorder());
        txtPhone.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtPhone.setInputType(controls.SubJTextField.type.Number);
        txtPhone.setWatermarkedText("Phone");

        javax.swing.GroupLayout pFname3Layout = new javax.swing.GroupLayout(pFname3);
        pFname3.setLayout(pFname3Layout);
        pFname3Layout.setHorizontalGroup(
            pFname3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFname3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPictureBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        pFname3Layout.setVerticalGroup(
            pFname3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFname3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pFname3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPictureBox5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel8.setText("Contact Infomation:");

        pFname4.setBackground(new java.awt.Color(255, 255, 255));
        pFname4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 1, new java.awt.Color(176, 186, 190)));

        txtEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtEmail.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtEmail.setPreferredSize(new java.awt.Dimension(0, 52));
        txtEmail.setWatermarkedText("Email");

        jPictureBox6.setBackground(new java.awt.Color(255, 255, 255));
        jPictureBox6.setBorder(null);
        jPictureBox6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/Icon/email.png"))); // NOI18N
        jPictureBox6.setImageMode(controls.JPictureBox.mode.Center);
        jPictureBox6.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPictureBox6Layout = new javax.swing.GroupLayout(jPictureBox6);
        jPictureBox6.setLayout(jPictureBox6Layout);
        jPictureBox6Layout.setHorizontalGroup(
            jPictureBox6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPictureBox6Layout.setVerticalGroup(
            jPictureBox6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jSeparator6.setBackground(new java.awt.Color(176, 186, 190));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pFname4Layout = new javax.swing.GroupLayout(pFname4);
        pFname4.setLayout(pFname4Layout);
        pFname4Layout.setHorizontalGroup(
            pFname4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFname4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPictureBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pFname4Layout.setVerticalGroup(
            pFname4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator6)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFname4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pFname4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPictureBox6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel9.setText("Additional Infomation");

        pFname6.setBackground(new java.awt.Color(255, 255, 255));
        pFname6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 186, 190)));

        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtPassword.setOriginBorder(txtPassword.getBorder());
        txtPassword.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtPassword.setPreferredSize(new java.awt.Dimension(0, 52));
        txtPassword.setWatermarkedText("Password");

        jPictureBox8.setBackground(new java.awt.Color(255, 255, 255));
        jPictureBox8.setBorder(null);
        jPictureBox8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/Icon/key.png"))); // NOI18N
        jPictureBox8.setImageMode(controls.JPictureBox.mode.Center);
        jPictureBox8.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPictureBox8Layout = new javax.swing.GroupLayout(jPictureBox8);
        jPictureBox8.setLayout(jPictureBox8Layout);
        jPictureBox8Layout.setHorizontalGroup(
            jPictureBox8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPictureBox8Layout.setVerticalGroup(
            jPictureBox8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jSeparator9.setBackground(new java.awt.Color(176, 186, 190));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pFname6Layout = new javax.swing.GroupLayout(pFname6);
        pFname6.setLayout(pFname6Layout);
        pFname6Layout.setHorizontalGroup(
            pFname6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFname6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPictureBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pFname6Layout.setVerticalGroup(
            pFname6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator9)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFname6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pFname6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPictureBox8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel11.setText("Name:");

        pFname7.setBackground(new java.awt.Color(255, 255, 255));
        pFname7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 1, new java.awt.Color(176, 186, 190)));

        txtUsername.setBackground(new java.awt.Color(255, 255, 255));
        txtUsername.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtUsername.setOriginBorder(txtUsername.getBorder());
        txtUsername.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtUsername.setPreferredSize(new java.awt.Dimension(0, 52));
        txtUsername.setWatermarkedText("Username");

        jPictureBox9.setBackground(new java.awt.Color(255, 255, 255));
        jPictureBox9.setBorder(null);
        jPictureBox9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/Icon/user.png"))); // NOI18N
        jPictureBox9.setImageMode(controls.JPictureBox.mode.Center);
        jPictureBox9.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPictureBox9Layout = new javax.swing.GroupLayout(jPictureBox9);
        jPictureBox9.setLayout(jPictureBox9Layout);
        jPictureBox9Layout.setHorizontalGroup(
            jPictureBox9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPictureBox9Layout.setVerticalGroup(
            jPictureBox9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jSeparator10.setBackground(new java.awt.Color(176, 186, 190));
        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pFname7Layout = new javax.swing.GroupLayout(pFname7);
        pFname7.setLayout(pFname7Layout);
        pFname7Layout.setHorizontalGroup(
            pFname7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFname7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPictureBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pFname7Layout.setVerticalGroup(
            pFname7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator10)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFname7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pFname7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPictureBox9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel10.setText("Role:");

        cbRole.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbRole.setPreferredSize(new java.awt.Dimension(340, 52));

        btnConfirm.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel12.setText("Salary:");

        pFname5.setBackground(new java.awt.Color(255, 255, 255));
        pFname5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 1, new java.awt.Color(176, 186, 190)));

        jPictureBox7.setBackground(new java.awt.Color(255, 255, 255));
        jPictureBox7.setBorder(null);
        jPictureBox7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project/Icon/dollar.png"))); // NOI18N
        jPictureBox7.setImageMode(controls.JPictureBox.mode.Center);
        jPictureBox7.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPictureBox7Layout = new javax.swing.GroupLayout(jPictureBox7);
        jPictureBox7.setLayout(jPictureBox7Layout);
        jPictureBox7Layout.setHorizontalGroup(
            jPictureBox7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPictureBox7Layout.setVerticalGroup(
            jPictureBox7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jSeparator8.setBackground(new java.awt.Color(176, 186, 190));
        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txtSalary.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtSalary.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtSalary.setInputType(controls.SubJTextField.type.FloatNumber);
        txtSalary.setLenght(7);
        txtSalary.setWatermarkedText("Salary");

        javax.swing.GroupLayout pFname5Layout = new javax.swing.GroupLayout(pFname5);
        pFname5.setLayout(pFname5Layout);
        pFname5Layout.setHorizontalGroup(
            pFname5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFname5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPictureBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        pFname5Layout.setVerticalGroup(
            pFname5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator8)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFname5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pFname5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPictureBox7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSalary, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 975, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(pFname7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pFname6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(40, 40, 40)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel10)
                                                .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(dHiredDate, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(pFname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(pFname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(27, 27, 27)
                                                .addComponent(rMale)
                                                .addGap(18, 18, 18)
                                                .addComponent(rFemale))
                                            .addComponent(jLabel5))
                                        .addComponent(dDob, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(pFname5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(pFname2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(pFname4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(pFname3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addGap(40, 40, 40)
                                            .addComponent(pbImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(cbJob, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 956, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pbImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pFname4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(pFname3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(10, 10, 10)
                                .addComponent(pFname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(pFname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pFname2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(rMale)
                                            .addComponent(jLabel3)
                                            .addComponent(rFemale))
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel5)
                                        .addGap(10, 10, 10)
                                        .addComponent(dDob, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6)))))
                        .addGap(12, 12, 12)
                        .addComponent(dHiredDate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pFname5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addComponent(cbJob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(10, 10, 10)
                                .addComponent(pFname7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(10, 10, 10)
                                .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addComponent(pFname6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnConfirm))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void popBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBrowseActionPerformed
        JFileChooser fc=new JFileChooser();
        fc.setFileFilter(imageFilter);
        
        if(fc.showDialog(this, "Open")== JFileChooser.APPROVE_OPTION){
            File f=fc.getSelectedFile();
            
            ImageIcon imageIcon=new ImageIcon(f.getAbsolutePath());
            pbImage.setIcon(imageIcon);
            
            
        }
    }//GEN-LAST:event_popBrowseActionPerformed

    private void popRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popRemoveActionPerformed
        ImageIcon imageIcon=new ImageIcon(originImagePath);
        pbImage.setIcon(imageIcon);

        
    }//GEN-LAST:event_popRemoveActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        boolean hasError=checkInputData(requiredTxt);
        
        
        String username=txtUsername.getThisText();
        String password=txtPassword.getThisText();
        
        
        
        
        Date dob=dDob.getValue();
        Date hiredDate=dHiredDate.getValue();
        
        
        Calendar c=Calendar.getInstance();
        c.setTime(dob);
        
        int yearDob=c.get(Calendar.YEAR);
        
        c.setTime(hiredDate);
        
        int yearHiredDate=c.get(Calendar.YEAR);
        
        int age=yearHiredDate-yearDob;
        
        
        if(age<18){
            JOptionPane.showMessageDialog(this, "Employee must be 18 years old or older","",JOptionPane.WARNING_MESSAGE);
            hasError=true;
        }
        
        
        
        if(hasError) return;
        
        
        
        String fName=txtFname.getText();
        String lName=txtLname.getText();
        String gender=rMale.isSelected()?"Male":"Female";
        
        String email=txtEmail.getThisText();
        String address=txtAddress.getText();
        String phone=txtPhone.getText();
        String job=((Employee.Job)cbJob.getModel().getSelectedItem()).getId()+"";
        String role=((Employee.Role)cbRole.getModel().getSelectedItem()).getId()+"";
        
        double salary=0;
        
        String imageUrl=pbImage.getIconAbsolutePath();
        
        
        
        if(!txtSalary.getThisText().equals("")){
            salary=Double.parseDouble(txtSalary.getThisText());
        }
        
        
        
        String []user={username,password,role};
        String formatPattern="yyyy-MM/dd";
        
        String []emp={fName, lName,gender,job, dDob.getStringValue(formatPattern), dHiredDate.getStringValue(formatPattern), salary+"", address,phone,email, imageUrl};
        
       
        
        if(selectedRowIndex==-1){
            if(Employee.insert(user, emp)){
            
            
                int number=modelEmployee.getRowCount()+1;

                String id=Employee.getLastInsertId();
                String userId=Employee.getLastInsertId();

                


                modelEmployee.addRow(number,fName,lName,gender,cbJob.getSelectedItem(),dDob.getStringValue(dDob.getFormatPattern()),dHiredDate.getStringValue(dHiredDate.getFormatPattern()),df.format(salary) ,address,phone,email,
                        username,username.equals("")?"":cbRole.getSelectedItem()+"",username.equals("")?"":"Yes",imageUrl,id,username.equals("")?"":userId+"");

                clearForm();
                JOptionPane.showMessageDialog(this, "Insert successful", "",JOptionPane.INFORMATION_MESSAGE);
            
            }
        }else{
            
            
            int id=Integer.parseInt(modelEmployee.getValueAt(selectedRowIndex, 15)+"");
            
            if(Employee.update(id, user, emp)){
                
                
                
                editModelEmployee(fName,lName,gender,cbJob.getSelectedItem()+"",dDob.getStringValue(dDob.getFormatPattern()),dHiredDate.getStringValue(dHiredDate.getFormatPattern()),df.format(salary) ,address,phone,email,
                        username,username.equals("")?"":cbRole.getSelectedItem()+"",username.equals("")?"":"Yes",imageUrl);
                
                
                prepareValueAfterEdit(selectedRowIndex);
                JOptionPane.showMessageDialog(this, "Update successful", "",JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
        
        
    
        
    }//GEN-LAST:event_btnConfirmActionPerformed
    
    
    void editModelEmployee(String... emp){
        for(int i=0;i<emp.length;i++){
            modelEmployee.setValueAt(emp[i], selectedRowIndex, i+1);
        }
    }
    
    
    DecimalFormat df=new DecimalFormat("#,###0.00");
    
    void clearForm(){
        for(WaterMarkedTextField txt : allTxt){
            txt.resetTextField();
        }
        
        cbJob.setSelectedIndex(0);
        cbRole.setSelectedIndex(0);
        
        Date d=new Date();
        dDob.setValue(d);
        
        pbImage.setIcon(defaultIcon);
        
        rMale.setSelected(true);
    }
    
   
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbJob;
    private javax.swing.JComboBox<String> cbRole;
    private controls.JDateTimePicker dDob;
    private controls.JDateTimePicker dHiredDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private controls.JPictureBox jPictureBox1;
    private controls.JPictureBox jPictureBox2;
    private controls.JPictureBox jPictureBox4;
    private controls.JPictureBox jPictureBox5;
    private controls.JPictureBox jPictureBox6;
    private controls.JPictureBox jPictureBox7;
    private controls.JPictureBox jPictureBox8;
    private controls.JPictureBox jPictureBox9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel pFname;
    private javax.swing.JPanel pFname1;
    private javax.swing.JPanel pFname2;
    private javax.swing.JPanel pFname3;
    private javax.swing.JPanel pFname4;
    private javax.swing.JPanel pFname5;
    private javax.swing.JPanel pFname6;
    private javax.swing.JPanel pFname7;
    private controls.JPictureBox pbImage;
    private javax.swing.JMenuItem popBrowse;
    private javax.swing.JMenuItem popRemove;
    private javax.swing.JPopupMenu popUpImage;
    private javax.swing.JRadioButton rFemale;
    private javax.swing.JRadioButton rMale;
    private controls.WaterMarkedTextField txtAddress;
    private controls.WaterMarkedTextField txtEmail;
    private controls.WaterMarkedTextField txtFname;
    private controls.WaterMarkedTextField txtLname;
    private controls.WaterMarkedTextField txtPassword;
    private controls.SubJTextField txtPhone;
    private controls.SubJTextField txtSalary;
    private controls.WaterMarkedTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    
}
