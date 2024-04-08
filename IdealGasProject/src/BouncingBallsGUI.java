

import mickel.anim.Stage;
import mickel.image.ImageFile;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.*;
/** The primary GUI window of the BouncingBalls application.
 */
public class BouncingBallsGUI
{
	// FIELDS
	// ------------------------------------------------------------
	private static Stage myStage;			// The base window for the app.
	private int moles; 
	private int temp;
	private int vol;
	private int pressure;
	public static Controller tempControl;		// Palette for the BLUE channel
	public static Controller volControl;		// Palette for the BLUE channel
	public static Controller pressureControl;		// Palette for the BLUE channel
	public static Controller molesControl;		// Palette for the BLUE channel
	public static String process;
	public static ArrayList<Ball> balls;


	// CONSTRUCTORS
	// ------------------------------------------------------------
	/** Constructs and initializes each of the components for this
	 *  GUI window.
	 *
	 *  postcondition: The primary GUI window is initialized and
	 *                   its animation cycle is started.
	 *      algorithm: Initialize a new Stage to have a title of
	 *                   "Bouncing Balls", a width of 400, and a
	 *                   height of 400.
	 *                 Set the background of myStage to be a new
	 *                   Color of your choice, preferably with a
	 *                   low alpha value. For example, translucent
	 *                   orange would be new Color(200,100,0,66)
	 *                 Optionally, set the background of myStage
	 *                   to be an image (GIF, JPEG, or PNG)
	 *                   of your choice. For example, the Duke.png
	 *                   file has been provided for you. Construct
	 *                   it using new ImageFile("Duke.png")
	 *                 Set the location of myStage such that it
	 *                   will be centered on a 1280 x 1024 screen.
	 *                   HINT: Use a mathematical expression to
	 *                   automatically calculate the appropriate
	 *                   x and y coordinate the upper left corner
	 *                   of myStage.
	 *                 Invoke this object's addSprites() method.
	 *                 Tell myStage to open its window.
	 *                 Tell myStage to start its animation cycle.
	 */
	public BouncingBallsGUI()
	{
		myStage = new Stage("Ideal Gas Simulator", 400, 400);
		myStage.setBackground(new Color(200,200,200,66));
		myStage.setLocation(300/2, 300/2);
		this.moles = 100;
		this.temp = 50;
		process = "isovolumetric";
		balls = new ArrayList<Ball>();
		int vx;
		int vy;
		int posx;
		int posy;
		for (int i = 0; i < moles; i++) {
			vx = (int)(Math.random() * temp/20)+1;
			vy = (int)(Math.random() * temp/20)+1;
			posx = (int)(Math.random() * 300)+50;
			posy = (int)(Math.random() * 300)+50;
			balls.add(new Ball(posx, posy, 10, vx, vy));
		}
		tempControl = new Controller(40, 350, 50, Color.BLUE, "Temp(K)");
		volControl = new Controller(120, 350, 50, Color.BLUE, "Vol(m3)");
		pressureControl = new Controller(200, 350, 50, Color.BLUE, "Pressure(atm)");
		molesControl = new Controller(320, 350, 50, Color.BLUE, "#Moles");

		myStage.add(tempControl);
		myStage.add(volControl);
		myStage.add(pressureControl);
		myStage.add(molesControl);

		System.out.print(balls);
		this.addSprites();
		// Make the stage visible and start the animation
		myStage.openWindow();
		myStage.start();
	}


	// METHODS
	// ------------------------------------------------------------
	/** Constructs and adds multiple Ball objects to the Stage.
	 *
	 *  postcondition: Constructs and adds one Ball object for each
	 *                   of the 3 Ball constructors to the Stage.
	 *      algorithm: Declare a Ball variable called b1 and assign
	 *                   to it a new Ball object with no parameters.
	 *                 Declare a Ball variable called b2 and assign
	 *                   to it a new Ball object with 2 specific
	 *                   parameters (width and height of myStage).
	 *                 Declare a Ball variable called b3 and assign
	 *                   to it a new Ball object with 4 specific
	 *                   parameters (x-location, y-location, size,
	 *                   color) of your choice and add it to myStage.
	 */
	private void addSprites()
	{
 		for (int i =0; i <balls.size();i++) {
 			myStage.add(balls.get(i));
 		}
 	
	}
	public static void updateVars() {
		if (molesControl.changed()) {
			pressureControl.setShade((int)(tempControl.getShade()*8.314*molesControl.getShade()/volControl.getShade()));
			if ((molesControl.getShade()-balls.size()) > 0) {
				for (int i = 0; i < (molesControl.getShade()-balls.size()); i++){						
					System.out.print("hi");
					int vx, vy, posx, posy;
					vx = (int)(Math.random() * tempControl.getShade()/20)+1;
					vy = (int)(Math.random() * tempControl.getShade()/20)+1;
					posx = (int)(Math.random() * 300)+50;
					posy = (int)(Math.random() * 300)+50;
					Ball addedBall = new  Ball(posx, posy, 10, vx, vy);
					balls.add(addedBall);
					myStage.add(addedBall);
				}	
			}
			else {//fix later
				for (int i = 0; i < -1*(molesControl.getShade()-balls.size()); i++){						
					balls.remove(balls.size()-1);
		 			//myStage.remove(myStage.countComponents()-1);
				}
			}
		}
		if (tempControl.changed() && process.equals("isovolumetric")) {
			int oldPressure = (int)pressureControl.getShade();
			pressureControl.setShade((int)(tempControl.getShade()*8.314*molesControl.getShade()/volControl.getShade()));
			int vx;
			int vy;
			for (int i = 0; i <balls.size();i++) {
				vx = (int)(Math.random() * tempControl.getShade()/20)+1;
				vy = (int)(Math.random() * tempControl.getShade()/20)+1;
				balls.get(i).setVelocityX(vx);
				balls.get(i).setVelocityY(vy);
			} 
		}
		if (tempControl.changed() && process.equals("isobaric")) {
			volControl.setShade((int)(tempControl.getShade()*8.314*molesControl.getShade()/pressureControl.getShade()));
			for (Ball ball : balls) 
			{
				ball.setBounds(volControl.getShade()+10);
			} 
			int vx;
			int vy;
			for (int i = 0; i <balls.size();i++) {
				vx = (int)(Math.random() * tempControl.getShade()/20)+1;
				vy = (int)(Math.random() * tempControl.getShade()/20)+1;
				balls.get(i).setVelocityX(vx);
				balls.get(i).setVelocityY(vy);
			}
		}
		if (pressureControl.changed()) {
			int oldTemp = (int)tempControl.getShade();
			tempControl.setShade((int)(pressureControl.getShade()/8.314/molesControl.getShade()*volControl.getShade()));
			int vx;
			int vy;
			for (int i = 0; i <balls.size();i++) {
				vx = (int)(Math.random() * tempControl.getShade()/20)+1;
				vy = (int)(Math.random() * tempControl.getShade()/20)+1;
				balls.get(i).setVelocityX(vx);
				balls.get(i).setVelocityY(vy);
				/*int oldVX = (int) balls.get(i).velocityX();
				int oldVY = (int) balls.get(i).velocityY();
				int newVX = (int)(tempControl.getShade()/oldTemp*(oldVY));
				int newVY = (int)(tempControl.getShade()/oldTemp*(oldVY));
				if (newVX == 0) {newVX++;}
				if (newVY == 0) {newVY++;}
				balls.get(i).setVelocityX(newVX);
				balls.get(i).setVelocityY(newVY);				*/
			} 
		}
		if (volControl.changed()) {
			pressureControl.setShade((int)(molesControl.getShade()*8.314*tempControl.getShade()/volControl.getShade()));
			for (Ball ball : balls) 
			{
				ball.setBounds(volControl.getShade()+10);
			} 
		} 
		if (molesControl.changed()) {
			pressureControl.setShade((int)(tempControl.getShade()*8.314*molesControl.getShade()/volControl.getShade()));
		}
	}
	
	public static void drawRect(Graphics2D g, int x, int y, int width, int height)
	{
		Stroke stroke = g.getStroke();
		int w = Math.max(10, 4);
		g.setStroke(new BasicStroke(w));
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		g.setStroke(stroke);
	}
	public static void drawText(Graphics2D g, String text, int x, int y,
            Color c, int size, boolean centered)
	{
		g.setFont(new Font("Arial", Font.BOLD, size));
		FontMetrics metrics = g.getFontMetrics();
		if (centered)
		{
		x -= metrics.stringWidth(text) / 2;
		y += metrics.getAscent() / 2;
		}
		
		g.setColor(Color.BLACK);
		g.drawString(text, x + 1, y + 1);
		g.drawString(text, x + 1, y - 1);
		g.drawString(text, x - 1, y + 1);
		g.drawString(text, x - 1, y - 1);
		
		g.setColor(c);
		g.drawString(text, x, y);
		
		g.setColor(new Color(255, 255, 255, 127));
		g.drawString(text, x, y);
	}

	public static void drawDisc(Graphics2D g, int x, int y, Color c, int size)
	{
		Stroke stroke = g.getStroke();
		int w = Math.max(size / 15, 4);

		g.setColor(c);
		g.fillOval(x, y, size, size);

		g.setStroke(new BasicStroke(w));
		g.setColor(Color.BLACK);
		g.drawOval(x, y, size, size);

		g.setStroke(new BasicStroke(w / 2));
		g.setColor(Color.LIGHT_GRAY);
		g.drawOval(x, y, size, size);

		g.setStroke(stroke);
	}

}
