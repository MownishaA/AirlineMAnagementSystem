package airline;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

@SuppressWarnings("serial")
class BackgroundPanel extends Panel {
   
	private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        backgroundImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Mounith\\Desktop\\study\\oops assign\\air3.jpg");
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
