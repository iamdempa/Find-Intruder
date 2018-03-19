/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lockme;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Last Kings
 */
public class HideInSystemTray {
static TrayIcon trayIcon;
static SystemTray tray;
    public static void main(String[] args) {
        hideMe();
    }
    public  static void hideMe(){
        if (SystemTray.isSupported()) {
//            System.out.println("system tray supported");
            tray = SystemTray.getSystemTray();

            Image image = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Core i3\\Desktop\\AlarmIcon.png");

            ActionListener openListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    tray.remove(trayIcon);
                    new LockOptions().setVisible(true);
                }
            };
            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem = new MenuItem("Open");
            defaultItem.addActionListener(openListener);
            popup.add(defaultItem);
            defaultItem = new MenuItem("Exit");
            defaultItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    System.out.println("Exiting....");

                    int option = JOptionPane.showConfirmDialog(null, "Do you really want to Exit Find Intruder - By jLab?", "Alarm Clock - By jLab", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
                    if (option == JOptionPane.YES_OPTION) {
                        System.exit(0);
                        tray.remove(trayIcon);
                    } else {
                        try {
                            tray.remove(trayIcon);
                            tray.add(trayIcon);
                        } catch (AWTException ex) {

                        }
                    }
                }
            });

            popup.add(defaultItem);
            trayIcon = new TrayIcon(image, "Find Intruder - By jLab", popup);
            trayIcon.setImageAutoSize(true);
        } else {
//            System.out.println("system tray not supported");
        }

        try {
            MouseListener ml;
            ml = new MouseListener() {
                public void mouseClicked(MouseEvent e) {

                    if (e.getClickCount() % 2 == 0) {
                        new LockOptions().setVisible(true);
                        tray.remove(trayIcon);
                    } else {

                    }
                }

                public void mouseEntered(MouseEvent e) {

                }

                public void mouseExited(MouseEvent e) {

                }

                public void mousePressed(MouseEvent e) {

                }

                public void mouseReleased(MouseEvent e) {

                }
            };

            tray.add(trayIcon);
            trayIcon.displayMessage("Find Intruder - By jLab", "The program is minimized to System Tray", TrayIcon.MessageType.INFO);
            trayIcon.addMouseListener(ml);
//            System.out.println("added to SystemTray");

        } catch (AWTException ex) {
//            System.out.println("unable to add to tray");
        }
    }

}
