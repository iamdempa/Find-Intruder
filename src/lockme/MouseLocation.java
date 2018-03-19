/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lockme;

import java.awt.MouseInfo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Last Kings
 */
public class MouseLocation {

    static int initalX, initialY, lastX, lastY;

    public static void main(String[] args) {
        
        new Thread() {
            public void run() {
                boolean running=true;
                while (running) {
                    initalX = MouseInfo.getPointerInfo().getLocation().x;
                    initialY = MouseInfo.getPointerInfo().getLocation().y;
                    try {
                        Thread.sleep(500);
                        
                        lastX = MouseInfo.getPointerInfo().getLocation().x;
                        lastY = MouseInfo.getPointerInfo().getLocation().y;
                        
                        if(initalX==lastX && initialY==lastY){
                            
                        }else{
                            System.out.println(lastX+" "+lastY);
                            running=false;
                            LockMain lock=new LockMain();
                            lock.setVisible(true);
                            
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MouseLocation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    
                    

                }
            }
        }.start();
        

    }
}
