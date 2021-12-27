/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processKiller;

import java.awt.Cursor;
import javax.swing.ImageIcon;
import processKiller.globalVars.GlobalVars;
import processKiller.gui.ProcessKillerGUI;

/**
 *
 * @author eliabdallah
 */
public class ProcessKiller {

    public static void main(String[] args) { 
        ProcessKillerGUI processKillerGUI = new ProcessKillerGUI();
        processKillerGUI.setTitle("Process Killer GUI");
        processKillerGUI.setLocationRelativeTo(null);
        processKillerGUI.setResizable(false);
//        ImageIcon img = new ImageIcon(GlobalVars.imageIconPath);
//        processKillerGUI.setIconImage(img.getImage());
        processKillerGUI.setCursor(new Cursor(Cursor.HAND_CURSOR));
        processKillerGUI.setVisible(true);
    }
}
