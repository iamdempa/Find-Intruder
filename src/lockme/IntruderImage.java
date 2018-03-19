/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lockme;

/**
 *
 * @author Last Kings
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


/**
 *
 * @author Last Kings
 */
import java.io.IOException;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Detect motion.
 *
 * @author Bartosz Firyn (SarXos)
 */
public class IntruderImage implements WebcamMotionListener {

    int i = 0;
    Webcam webcam = Webcam.getDefault();
    WebcamMotionDetector detector = new WebcamMotionDetector(Webcam.getDefault());
    
    public IntruderImage() {
        webcam.setViewSize(new Dimension(640, 480));
        

        detector.setInterval(100); // one check per 100 ms
        detector.addMotionListener(this);
        detector.start();
        
    }
   

    @Override
    public void motionDetected(WebcamMotionEvent wme) {

        System.out.println("Detected");

        i++;
        takePic();

    }

    public static void main(String[] args) throws IOException {
        new IntruderImage();
        System.in.read(); // keep program open
    }

    private void takePic() {

        // get default webcam and open it
        if (i ==2) {
            webcam.close();
            
            detector.stop();
            System.out.println("CLOSING");
        } else {
            if (webcam.isOpen()) {
                System.out.println("Open");
             
                BufferedImage image = webcam.getImage();

                try {
                    // save image to PNG file
                    String picName = String.valueOf(System.currentTimeMillis() / 1000);
                    ImageIO.write(image, "PNG", new File("C:\\Users\\Core i3\\Desktop\\Lock Screen\\pic\\" + picName + ".PNG"));

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                
                System.out.println("Not Open");
                webcam.open();
                BufferedImage image = webcam.getImage();
                String picName = String.valueOf(System.currentTimeMillis() / 100000);
                try {
                    ImageIO.write(image, "PNG", new File("C:\\Users\\Core i3\\Desktop\\Lock Screen\\pic\\" + picName + ".PNG"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

            // get image
            BufferedImage image = webcam.getImage();

            try {
                // save image to PNG file
                String picName = String.valueOf(System.currentTimeMillis() / 100000);
                ImageIO.write(image, "PNG", new File("C:\\Users\\Core i3\\Desktop\\Lock Screen\\pic\\" + picName + ".PNG"));

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
