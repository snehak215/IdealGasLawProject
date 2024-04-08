


import mickel.anim.Sprite;
import mickel.io.Key;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

/** Represents a Ball that dynamically bounces around the interior
 *  of a rectangular Stage.
 */
public class Ball extends Sprite
{
	// FIELDS
	// ------------------------------------------------------------
	private int myPosX;		// X-coordinate of this Ball
	private int myPosY;		// Y-coordinate of this Ball
	//private static ArrayList<Ball> balls;
	private int myDirX;		// Horizontal speed of this Ball
	private int myDirY;		// Vertical speed of this Ball
	private double myVelocityX;
	private double myVelocityY;
	private int mySize;		// Diameter of this Ball
	private Color myColor;	// Color of this Ball
	private int boundsy;

	// CONSTRUCTORS
	// ------------------------------------------------------------
	
		/** Constructs a Ball that initially has the specified
	 *  location, size, and color.
	 *
	 *     algorithm: Assign mySize a value of size.
	 *                Assign myPosX the value of x.
	 *                Assign myPosY the value of y.
	 *                Assign myDirX the value of 3.
	 *                Assign myDirY the value of 3.
	 *                Assign myColor the value of GREEN.
	 *
	 * @param posx		The x-coordinate of this Ball's location
	 * @param posy		The y-coordinate of this Ball's location
	 * @param size	The diameter of this Ball
	 * @param c		The Color of this Ball
	 */
	public Ball(int posx, int posy, int size, int vx, int vy) {
		mySize = size;
		myPosX = posx;
		myPosY = posy;
		//myDirX = 3;
		//myDirY = 3;
		//myColor = new Color(rVal, gVal, bVal, aVal);	// TODO: Replace this with the appropriate implementation.
		myVelocityX = vx;
		myVelocityY = vy;
	}


	// METHODS
	// ------------------------------------------------------------
	/** Performs one frame of movement for this Ball object.
	 *
	 *  precondition:
	 * postcondition: The location of this Ball will be incremented
	 *                by one frame in its current direction of
	 *                movement. If the resulting location lies
	 *                beyond the boundaries of the Stage, this Ball's
	 *                direction will be adjusted accordingly to
	 *                reflect a "bounce".
	 *     algorithm: Increment myPosX by myDirX.
	 *                Increment myPosY by myDirY.
	 *                Declare an int variable w and initialize it
	 *                  with the value of the width of this Ball's
	 *                  Stage.
	 *                Declare an int variable h and initialize it
	 *                  with the value of the height of this Ball's
	 *                  Stage.
	 *                If the left edge of this Ball is less than 0
	 *                  and this Ball is moving left, negate the
	 *                  value of myDirX.
	 *                If the right edge of this Ball is greater than
	 *                  w and this Ball is moving right, negate the
	 *                  value of myDirX.
	 *                If the top edge of this Ball is less than 0
	 *                  and this Ball is moving up, negate the
	 *                  value of myDirY.
	 *                If the bottom edge of this Ball is greater than
	 *                  h and this Ball is moving down, negate the
	 *                  value of myDirY.
	 */
	public void act()
	{
		int w = 400; //image observer??
		int h = 300;
		/* (myPosX-myVelocityX < 50) {
			myPosX -= (myPosX-50);
		}
		else if (myPosX+myVelocityX > w-50)
		{
			myPosX += (w-50-myPosX);
		}
		else {}
		*/
		myPosX += myVelocityX;
		
		/*if (myPosY-myVelocityY < (100-boundsy)) {
			myPosY -= (myPosY-(100-boundsy));
		}
		else if (myPosY+myVelocityY > h)
		{
			myPosY += (h-myPosY);
		}
		else {}*/
		myPosY += myVelocityY;
		
		if ((myPosX <=50) && (myVelocityX <0)) {
			myVelocityX = -myVelocityX;
		}
		if ((myPosX >= 350) && (myVelocityX >0)) {
			myVelocityX = -myVelocityX;
		}
		
		if ((myPosY <=(100-boundsy)) && (myVelocityY <0)) {
			myVelocityY = -myVelocityY;
		}
		if ((myPosY >= h) && (myVelocityY >0)) {
			myVelocityY = -myVelocityY;
		}
		//System.out.print(BouncingBallsGUI.balls);
				/*
				 * //double distX = Math.abs(BouncingBallsGUI.balls.get(i).myPosX - BouncingBallsGUI.balls.get(j).myPosX);
				///double distY = Math.abs(BouncingBallsGUI.balls.get(i).myPosY - BouncingBallsGUI.balls.get(j).myPosY);
				double vxi = BouncingBallsGUI.balls.get(i).myVelocityX;
				double vyi = BouncingBallsGUI.balls.get(i).myVelocityY;
				double vxj = BouncingBallsGUI.balls.get(j).myVelocityX;
				double vyj = BouncingBallsGUI.balls.get(j).myVelocityY;

				//if (Math.sqrt(Math.pow((distX), 2) + Math.pow((distY),2)) < 50){
				if (BouncingBallsGUI.balls.get(i).intersects(BouncingBallsGUI.balls.get(j))) {
					BouncingBallsGUI.balls.get(i).collideWithBall(-1*vxi, -1*vyi);
					BouncingBallsGUI.balls.get(j).collideWithBall(-1*vxj, -1*vyj);
				}
				
					for (int i = 0; i < BouncingBallsGUI.balls.size(); i++) {
			if (BouncingBallsGUI.balls.get(i).intersects(this)){
				double vxi = BouncingBallsGUI.balls.get(i).myVelocityX;
				double vyi = BouncingBallsGUI.balls.get(i).myVelocityY;
				this.collideWithBall(-1*this.myVelocityX, -1*this.myVelocityY);
				BouncingBallsGUI.balls.get(i).collideWithBall(-1*vxi, -1*vyi);

			}	 */
	}
	public void setBounds(int upperBound){
		boundsy = upperBound;
		}

	public void collideWithBall(double d, double e){
		myVelocityY = d;
		myVelocityY = e; 
		}

	/** Draws a representation of the current state of this Ball
	 *  onto the graphics canvas, g.
	 *
	 *  precondition: g is the "canvas" of the Stage
	 * postcondition: Draws a representation of the current state
	 *                of this Ball onto the graphics canvas, g.
	 *     algorithm: Set the Color of g to be myColor.
	 *                Paint a filled oval onto g that is located at
	 *                  (myPosX, myPosY) with a width and height of
	 *                  mySize.
	 *
	 * @param g	The "canvas" on which this Ball is to be drawn.
	 */
	public void draw(Graphics2D g)
	{
 		int temp = BouncingBallsGUI.tempControl.getShade();
		int bAmt;
		int redAmt;
 		if (255-temp < 0) {
 			bAmt = 0;
 			redAmt = 255;
 		}
 		else {
 			 bAmt = 255-temp;
 			 redAmt = temp;
 		}
		g.setColor(new Color(redAmt, 3, bAmt, 255));

		g.fillOval(myPosX, myPosY, mySize, mySize);
 		BouncingBallsGUI.drawRect(g, 50, 100-boundsy, 300, 250-((100-boundsy)-50));
		
			//int width = myButtonSize / 2 ;
			//int thick = width / 4;
			//g.fillRect(myLeftButton.x - width / 2, myLeftButton.y - thick / 2, width, thick);
			//g.fillRect(myRightButton.x - width / 2, myRightButton.y - thick / 2, width, thick); 	}	
		// Color Value
		//BouncingBallsGUI.drawText(g, "Blue", 150, 400, Color.BLUE, 25, true);

		
		

	}


	/** Gets the boundaries of this Sprite.
	 *
	 * postcondition: Returns a Shape object that corresponds to the
	 *                characteristics used in the draw() method.
	 *     algorithm: Return a new Ellipse2D.Double object that uses
	 *                  myPosX and myPosY for the x and y parameters
	 *                  and mySize for the width and height parameters.
	 *
	 * @return   The Shape specifying the boundaries of this Sprite
	 */
	public Shape getShape()
	{
		Ellipse2D.Double shape = new Ellipse2D.Double(myPosX, myPosY, mySize, mySize); 
		return shape;
	}
	public double positionX()
	{
		return myPosX;
	}
	public double velocityY()
	{
		return myVelocityY;
	}
	public double velocityX()
	{
		return myVelocityX;
	}
	public double setVelocityX(int vx)
	{
		myVelocityX = vx;
		return myVelocityX;
	}
	public double setVelocityY(int vy)
	{
		myVelocityY = vy;
		return myVelocityY;
	}

	public void setMyDirX(int dir) {
		this.myDirX = dir;
	}
	
	public void setMyDirY(int dir) {
		this.myDirY = dir;
	}

	/* The following methods are event-handling methods that respond
	 * to keyboard events and mouse events. You may wish to experiment
	 * with these to add additional "user controls" for Ball objects.
	 */
	public void keyPressed (Key k) { /* TODO: Insert code */ }
	public void keyReleased(Key k) { /* TODO: Insert code */ }
	public void keyTyped   (Key k) { /* TODO: Insert code */  }

	public void mousePressed (int x, int y) { /* TODO: Insert code */ }
	public void mouseReleased(int x, int y) { /* TODO: Insert code */ }
	public void mouseClicked (int x, int y) { /* TODO: Insert code */ }
	public void mouseMoved   (int x, int y) { /* TODO: Insert code */ }
	public void mouseDragged (int x, int y) { /* TODO: Insert code */ }
	public void mouseEntered (int x, int y) { /* TODO: Insert code */ }
	public void mouseExited  (int x, int y) { /* TODO: Insert code */ }

}
