/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import controls.MyModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author Nemesis
 */
public class function {
    public static void prepareDialog(JDialog dialog,JPanel panel,boolean isResizable){
        
        
        
        int[] centerCordinate=frmMain.getCenterCordinate(panel.getPreferredSize());
        
        dialog.setLocation(centerCordinate[0], centerCordinate[1]);
        dialog.setResizable(isResizable);
        dialog.setModal(true);
        dialog.add(panel);
        dialog.pack();
        dialog.setVisible(true);
    }
    
    
    public static void resetAutoNumber(int startRow,MyModel model){
        
        for(int i=startRow;i<model.getRowCount();i++){
            model.setValueAt(i+1, i, 0);
        }
        
    }
    
    public static void updateModel(int selectedRowIndex,MyModel model,String... data){
        
        for(int i=0;i<data.length;i++){
            model.setValueAt(data[i], selectedRowIndex, i+1);
        }
        
        
    }
    
    public static void addPopUpToControl(JPopupMenu popupMenu,JComponent component){
        
        
        MouseAdapter mouse=new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3){
                    popupMenu.show(component, e.getX(), e.getY());
                    
                }
            }
            
        };
        
        component.addMouseListener(mouse);
    }
    

    
}
