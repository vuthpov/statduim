/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.Font;
import java.util.List;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTable;

/**
 *
 * @author chano
 */
public class addcontrol {
    
    public static void popupmenu(JPopupMenu JP,List<String>Data){
        Data.forEach((String tm) -> {
            JP.add(tm);
            JSeparator sp=new JSeparator();
            JP.add(sp);
        });
    }
    public static void changefontHeader(JTable table,String FontName ,String FontWieght ,int FontSize){
        Font f=null;
        if(FontWieght.equalsIgnoreCase("BOLD")){
            f=new Font(FontName, Font.BOLD, FontSize);
        }else if (FontWieght.equalsIgnoreCase("ITALIC")){
            f=new Font(FontName, Font.ITALIC, FontSize);
        }else{
            f=new Font(FontName, Font.PLAIN, FontSize);
        }
        table.getTableHeader().setFont(f);
    }
    
}
