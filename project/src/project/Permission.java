/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javax.swing.JOptionPane;

/**
 *
 * @author Nemesis
 */
public class Permission {
     public static boolean denyAccess(String currentRole,String... roleToDeny){
        for(String st : roleToDeny){
            if(currentRole.equals(st)){
                JOptionPane.showMessageDialog(null, "Access Denied","",JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }
        return false;
    }
}
