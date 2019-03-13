/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author Nemesis
 */

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Nemesis
 */
public class DropDown {

    /**
     * @return the heightPerDelay
     */
    public int getHeightPerDelay() {
        return heightPerDelay;
    }

    /**
     * @param heightPerDelay the heightPerDelay to set
     */
    public void setHeightPerDelay(int heightPerDelay) {
        this.heightPerDelay = heightPerDelay;
    }
    JButton btnDropDown;
    JPanel panelParent;
    
    Dimension dPanelParent;
    Thread thread;
    
    private int delay;
    private int heightPerDelay;

    public DropDown(JButton btnDropDown, JPanel panelParent,int heightPanelParent, int delay, int heightPerDelay) {
        this.btnDropDown = btnDropDown;
        this.panelParent = panelParent;
        this.delay = delay;
        this.heightPerDelay = heightPerDelay;
        
        dPanelParent=new Dimension(panelParent.getWidth(),heightPanelParent);
        
        Dimension dBtnDropDown=btnDropDown.getSize();
        Dimension desiredSize=new Dimension();
        
        MouseAdapter mouse=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int increment;
                if(dPanelParent.getHeight()!=panelParent.getHeight()){
                    increment=1;
                    desiredSize.setSize(dPanelParent);
                }else{
                    increment=-1;
                    desiredSize.setSize(dBtnDropDown);
                }
                
                renewThread(desiredSize,increment);
                thread.start();
            }
        };
        
         btnDropDown.addMouseListener(mouse);
    }
    
    
    void renewThread(Dimension desiredSize,int increment){
        thread=new Thread(()->{
            try{
                while(panelParent.getHeight()!=desiredSize.getHeight()){
                    
                    int extendedHeight=panelParent.getHeight()+getHeightPerDelay()*increment;
                    
                    if(increment==-1){
                        if(extendedHeight<desiredSize.getHeight()){
                            extendedHeight=(int)desiredSize.getHeight();
                        } 
                    }else{
                        if(extendedHeight>desiredSize.getHeight()){
                            extendedHeight=(int)desiredSize.getHeight();
                        } 
                    }
                    
                    
                    
                    panelParent.setSize(desiredSize.width,extendedHeight);
                    panelParent.repaint();
                    Thread.sleep(getDelay());
                }
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
           
        });
    }
    
    /**
     * @return the delay
     */
    public int getDelay() {
        return delay;
    }

    /**
     * @param delay the delay to set
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }
    
    
    
}

