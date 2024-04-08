import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
//how to know x/5 or what
//draw text
//need to remember names of objects?
//function namees?
import mickel.anim.Sprite;
import mickel.io.Key;

/** Represents a single, GUI-based controller for a color channel.
 *  It includes a palette showing the current channel color, a
 *  numeric display of the current color channel value, and buttons
 *  for increasing and/or decreasing the channel's value.
 */
public class Controller extends Sprite
{
	// FIELDS
	// ------------------------------------------------------------
	private final int STEP = 3;		// Amount that a single click of
									// a button increments/decrements
									// this channel's color value

	private int mySize;				// Overall width of the channel
	private int myX;				// Upper left corner of the channel
	private int myY;				// Upper left corner of the channel
	private String name; //pressure, moles, temp, or vol
	private int myButtonSize;		// Diameter of a button

	private Point myLeftButton;		// Center (x,y) of the left button
	private Point myRightButton;	// Center (x,y) of the right button
	private boolean changed;
	
	private boolean myLeftIsPressed;	// State of user clicking left button
	private boolean myRightIsPressed;	// State of user clicking right button

	private boolean isIsoVolumetric;	// State of user clicking left button
	private boolean isIsobaric;	// State of user clicking right button

	
	
	private int myShade;			// Magnitude of this channel (0 - 255)
	private Color myHue;				// RGBA.RED, RGBA.GREEN, RGBA.BLUE,
									// or RBGA.ALPHA

	// CONSTRUCTOR
	// ------------------------------------------------------------
	/** Initializes the properties of this color channel.
	 *
	 *  @param x		Upper left corner of the channel
	 *  @param y		Upper left corner of the channel
	 *  @param width	Overall width of the channel
	 *  @param c		Which channel this is (R, G, B, or A)
	 */
	public Controller(int x, int y, int width, Color c, String name)
	{
		mySize = width;
		myX = x;
		myY = y + width / 5;
		myHue = c;
		if (name.equals("Pressure(atm)")) {
			myShade = 416;
		}
		else if (name.equals("#Moles")) {
			myShade = 100;
		}
		else if (name.equals("Temp(K)")) {
			myShade = 20;
		}
		else if (name.equals("Vol(m3)")) {
			myShade = 40;
		}

		this.name = name;
		myLeftButton  = new Point(myX + mySize * 1 / 6, myY + mySize * 7 / 10);
		myRightButton = new Point(myX + mySize * 5 / 6, myY + mySize * 7 / 10);
		myButtonSize = (mySize * 5 / 6) / 4;
		changed = false;
	}

	// METHODS
	// ------------------------------------------------------------
	/** Determines if a given point (x, y) is located over the "left"
	 *  button of this color channel controller.
	 *
	 *  precondition: x and y are coordinates of a point in the Stage window.
	 *                The "left" button is centered on the point myLeftButton
	 *                and has a diameter of myButtonSize.
	 * postcondition: Returns true if point (x, y) is located within the bounds
	 *  s              of the "left" button for this color channel controller and
	 *                returns false if point (x, y) is not within the bounds.
	 *     algorithm: Declare an int called 'radius' and assign it the value of
	 *                  half of myButtonSize.
	 *                Declare a double called 'distance' and assign it the value
	 *                  of the distance between point (x, y) and the center
	 *                  of the left button (myLeftButton.x, myLeftButton.y).
	 *                  HINT: Use the "distance formula".
	 *                If distance is less then or equal to radius...
	 *                  ...return true.
	 *                Otherwise...
	 *                  ...return false.
	 *
	 *  @param x			The x coordinate of some point on the screen
	 *  @param y			The y coordinate of some point on the screen
	 */
	private boolean isOverLeftButton(int x, int y)
	{
		int radius = (int) (.5 * myButtonSize);
		double distance = Math.sqrt(Math.pow(x - myLeftButton.getX(), 2) + Math.pow(y - myLeftButton.getY(), 2));
		if (distance <= radius)
			return true;
			
		return false;	// TODO: Replace this with the appropriate implementation.
	}

	/** Determines if a given point (x, y) is located over the "left"
	 *  button of this color channel controller.
	 *
	 *  precondition: x and y are coordinates of a point in the Stage window.
	 *                The "right" button is centered on the point myRightButton
	 *                and has a diameter of myButtonSize.
	 * postcondition: Returns true if point (x, y) is located within the bounds
	 *                of the "right" button for this color channel controller and
	 *                returns false if point (x, y) is not within the bounds.
	 *     algorithm: Declare an int called 'radius' and assign it the value of
	 *                  half of myButtonSize.
	 *                Declare a double called 'distance' and assign it the value
	 *                  of the distance between point (x, y) and the center
	 *                  of the right button (myRightButton.x, myRightButton.y).
	 *                  HINT: Use the "distance formula".
	 *                If distance is less then or equal to radius...
	 *                  ...return true.
	 *                Otherwise...
	 *                  ...return false.
	 *
	 *  @param x			The x coordinate of some point on the screen
	 *  @param y			The y coordinate of some point on the screen
	 */
	private boolean isOverRightButton(int x, int y)
	{
		int radius = (int) (.5 * myButtonSize);
		double distance = Math.sqrt(Math.pow(x - myRightButton.getX(), 2) + Math.pow(y - myRightButton.getY(), 2));
		if (distance <= radius)
			return true;
			

		return false;	// TODO: Replace this with the appropriate implementation.
	}
	private boolean isOverIsoVolumetric(int x, int y) {
		if (x < 155 && x > 50) {
			if (y >320 && y< 370) {
				return true;
			}
		}	
		return false;
	}
	private boolean isOverIsobaric(int x, int y) {
		if (x < 200 && x > 155) {
			if (y >320 && y< 370) {
				return true;
			}
		}	
		return false;
	}


	/** Event handler that responds to the pressing down of the mouse button.
	 *  Whenever the user presses the mouse button, this Controller object
	 *  will be notified so that it can respond in the manner described by
	 *  this method.
	 *
	 *  If the mouse is over one of this Controller object's buttons when this
	 *  method is invoked, it simply records the fact the fact that the
	 *  appropriate button has been clicked. Later, the act() method will use this
	 *  information to adjust the Controller's color magnitude (i.e., myShade).
	 *
	 *  precondition: x and y are coordinates of a point in the window.
	 * postcondition: Mouse clicks occurring on the "left" button will cause
	 *                  the value of myLeftIsPressed to be set to true and
	 *                  those occurring on the "right" button will cause the
	 *                  value of myRightIsPressed to be set to true.
	 *     algorithm: If the point located at (x, y) is over the "left" button...
	 *                  ...the value of myLeftIsPressed should be assigned a value
	 *                     of true.
	 *                If the point located at (x, y) is over the "right" button...
	 *                  ...the value of myRightIsPressed should be assigned a value
	 *                     of true.
	 *
	 *  @param x			The x coordinate of the mouse location
	 *  @param y			The y coordinate of the mouse location
	 */
	public void mousePressed(int x, int y)
	{
		if (isOverRightButton(x, y)) {
			myRightIsPressed = true;
		}
		if (isOverLeftButton(x, y)) {
			myLeftIsPressed = true;
		} 
		if (isOverIsoVolumetric(x,y)) {
			isIsoVolumetric = true;
		}
		if (isOverIsobaric(x,y)) {
			isIsobaric = true;
		}
	}

	/** Event handler that responds to the releasing of the mouse button.
	 *  Whenever the user releases the mouse button, this Controller object
	 *  will be notified so that it can respond in the manner described by
	 *  this method.
	 *
	 *  Regardless of where the mouse is when this method is invoked, this
	 *  Controller will record that neither of its buttons are currently being
	 *  clicked.
	 *
	 *  precondition: x and y are coordinates of a point in the window.
	 * postcondition: The values of myLeftIsPressed and myRightIsPressed are
	 *                  set to false.
	 *     algorithm: The value of myLeftIsPressed should be assigned a value
	 *                  of false.
	 *                The value of myRightIsPressed should be assigned a value
	 *                  of false.
	 *
	 *  @param x			The x coordinate of the mouse location
	 *  @param y			The y coordinate of the mouse location
	 */
	public void mouseReleased(int x, int y)
	{
		myLeftIsPressed = false;
		myRightIsPressed = false;

	}

	/** Every frame of animation, this modifier method is invoked so that this
	 *  controller can update it's current state.
	 *
	 *  Increases or decreases the color magnitude (i.e., myShade) of this
	 *  Controller whenever the user clicks on one of its 2 buttons.
	 *
	 *  precondition: myLeftIsPressed and myRightIsPressed store the current
	 *                  state of whether the user is clicking on either of
	 *                  this Controller's 2 buttons.
	 * postcondition: Mouse clicks occurring on the "left" button will cause
	 *                  the value of myShade to be decreased by STEP and
	 *                  those occurring on the "right" button will cause the
	 *                  value of myShade to be increased by STEP. The
	 *                  value of myShade should not exceed the range
	 *                  of valid color values (i.e., 0 through 255).
	 *     algorithm: If myLeftIsPressed is true and myShade - STEP is greater
	 *                  than or equal to 0...
	 *                  ...the value of myShade should be decreased by STEP.
	 *                If myRightIsPressed is true and myShade + STEP is less
	 *                  than or equal to 255...
	 *                  ...the value of myShade should be increased by STEP.
	 */
	public void act()
	{
		if ((myLeftIsPressed) & (myShade - STEP > 1)) {
			myShade -= STEP;
			changed = true;
		}
		if ((myRightIsPressed)) {
			myShade += STEP;
			changed = true;
		}
		if (isIsoVolumetric) {
			BouncingBallsGUI.process = "isovolumetric";
		}
		if (isIsobaric) {
			BouncingBallsGUI.process = "isobaric";
		}

	} 
	public boolean changed() {
		return changed;
		
	}

	/** Accessor method for retrieving the magnitude (0 - 255) of this
	 *  color channel.
	 *
	 *  precondition: myShade stores the magnitude of this color channel.
	 * postcondition: Returns the numerical value of this color channel.
	 *     algorithm: Return the value of myShade.
	 */
	public int getShade()
	{
		return myShade;	// TODO: Replace this with the appropriate implementation.
	}
	public int setShade(int shade)
	{
		myShade = shade;
		return myShade;// TODO: Replace this with the appropriate implementation.
	}


	/** Accessor method for retrieving the Color object associated with the
	 *  current shade for this color channel.
	 *
	 *  precondition: myShade stores the magnitude of this color channel.
	 * postcondition: Returns the Color object that corresponds to the current
	 *                 color value of this particular channel. For example, a
	 *                 hue of RGBA.GREEN should return:
	 *                           new Color(0, myShade, 0, 255)
	 *     algorithm: Declare four int values called 'rVal', 'gVal', 'bVal',
	 *                  and 'aVal'.
	 *                Initialize rVal, gVal, and bVal to 0.
	 *                Initialize aVal to 255.
	 *                If myHue is RGBA.RED...
	 *                  ...assign rVal a value of myShade.
	 *                Otherwise, if myHue is RGBA.GREEN...
	 *                  ...assign gVal a value of myShade.
	 *                Otherwise, if myHue is RGBA.BLUE...
	 *                  ...assign bVal a value of myShade.
	 *                Otherwise...
	 *                  ...assign aVal a value of myShade.
	 *                  ...assign rVal, gVal, and bVal values of 255.
	 *                Return a new Color object with a red value of rVal,
	 *                  a green value of gVal, a blue value of bVal, and
	 *                  an alpha value of aVal.
	 */

	/** Every frame of animation, this accessor method is invoked so that the
	 *  current state of this Controller can redrawn onto the graphics canvas.
	 *
	 *  Draws all of the elements of this controller (label, color
	 *  sample, and buttons) onto the Stage.
	 *
	 *  precondition: g is the "canvas" of the Stage
	 * postcondition: Draws all of the elements of this Controller
	 *                onto the graphics canvas, g.
	 *
	 * @param g		The "canvas" on which to draw this Swatch
	 */
	
	public void draw(Graphics2D g)
	{
		//dealing with changes
		if (changed) {
			BouncingBallsGUI.updateVars();
			changed = false;
		} 
		BouncingBallsGUI.drawText(g, "isovolumetric or ", 50, 340,
                Color.WHITE, 13, false);
		BouncingBallsGUI.drawText(g, "isobaric (note: this is a 1-way toggle)", 155, 340,
                Color.WHITE, 13, false);
		
		if (BouncingBallsGUI.process.equals("isovolumetric")) {
			BouncingBallsGUI.drawText(g, BouncingBallsGUI.process, 50, 340,
	                Color.GREEN, 13, false);
		}
		else if (BouncingBallsGUI.process.equals("isobaric")) {
			BouncingBallsGUI.drawText(g, BouncingBallsGUI.process, 155, 340,
	                Color.GREEN, 13, false);
		}
		
		// Color Channel Label
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, mySize * 2 / 15));
		BouncingBallsGUI.drawText(g, this.name, myX + mySize / 15, myY - mySize / 30,
				                Color.WHITE, mySize * 3 / 10, false);

		// Color Sample
		BouncingBallsGUI.drawDisc(g, myX + mySize / 11, myY, Color.BLUE, mySize * 5 / 6);

		// Buttons
		int leftX = myLeftButton.x - myButtonSize / 2;
		int leftY = myLeftButton.y - myButtonSize / 2;
		int rightX = myRightButton.x - myButtonSize / 2;
		int rightY = myRightButton.y - myButtonSize / 2;
		BouncingBallsGUI.drawDisc(g, leftX, leftY, Color.DARK_GRAY, myButtonSize);
		BouncingBallsGUI.drawDisc(g, rightX, rightY, Color.DARK_GRAY, myButtonSize);

		g.setColor(Color.LIGHT_GRAY);
		int width = myButtonSize / 2 ;
		int thick = width / 4;
		g.fillRect(myLeftButton.x - width / 2, myLeftButton.y - thick / 2, width, thick);
		g.fillRect(myRightButton.x - width / 2, myRightButton.y - thick / 2, width, thick);
		g.fillRect(myRightButton.x - thick / 2, myRightButton.y - width / 2, thick, width);

		// Color Value
		int x = (myX + mySize / 10) + (mySize * 5 / 6) / 2;
		int y = (myY) + (mySize * 5 / 6) / 2;
		BouncingBallsGUI.drawText(g, "" + myShade, x, y, Color.BLUE, mySize * 4 / 15, true);
	}


	/* The following methods are event-handling methods, similar to
	 *  the mousePressed() and mouseReleased() methods above, except that
	 *  they respond to keyboard events and other types of mouse events.
	 *  You may wish to experiment with these to add additional "user
	 *  controls" to your application.
	 */
	public void keyPressed (Key k) {  }
	public void keyReleased(Key k) {  }
	public void keyTyped   (Key k) {  }

//	public void mousePressed (int x, int y) {  }	// Already defined above
//	public void mouseReleased(int x, int y) {  }	// Already defined above
	public void mouseClicked (int x, int y) {  }
	public void mouseMoved   (int x, int y) {  }
	public void mouseDragged (int x, int y) {  }
	public void mouseEntered (int x, int y) {  }
	public void mouseExited  (int x, int y) {  }


}
