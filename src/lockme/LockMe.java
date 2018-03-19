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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

class LockScreen {

    private JPanel substrate;
    private JPanel upperLayer;

    public LockScreen() {
        int width = 500;
        int height = 600;

        final JButton lockButton = new JButton("Lock");

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean lock = e.getSource() == lockButton;
                substrate.setVisible(lock);
                upperLayer.setVisible(lock);
            }
        };

        lockButton.addActionListener(actionListener);

        JPanel mainLayer = new JPanel();
        mainLayer.add(lockButton);
        mainLayer.add(new JButton("Button"));
        mainLayer.add(new JLabel("Label"));
        mainLayer.setBounds(0, 0, width, height);
        
        final Color transparentWhite = new Color(255, 255, 255, 127);
        substrate = new JPanel() {
            @Override
            public void paint(Graphics g) {
                g.setColor(transparentWhite);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        substrate.setOpaque(false);
        substrate.setBounds(0, 0, width, height);
        substrate.addMouseListener(new MouseAdapter() {
        });
        substrate.setVisible(false);

        JButton unlockButton = new JButton("Unlock");
        unlockButton.addActionListener(actionListener);

        upperLayer = new JPanel();
        upperLayer.setBounds(width / 2 - 50, height / 2 - 50, 100, 100);
        upperLayer.setBorder(new LineBorder(Color.BLACK, 1));
        upperLayer.add(unlockButton);
        upperLayer.setVisible(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setLayer(mainLayer, 0);
        layeredPane.add(mainLayer);
        layeredPane.setLayer(substrate, 1);
        layeredPane.add(substrate);
        layeredPane.setLayer(upperLayer, 2);
        layeredPane.add(upperLayer);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, width, height);
        frame.add(layeredPane);
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        new LockScreen();
    }
}
