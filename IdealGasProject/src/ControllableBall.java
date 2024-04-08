
import java.awt.Color;

import mickel.io.Key;

public class ControllableBall extends Ball
{
	// Fields
	
	// Constructors
	public ControllableBall() {
		//super();						// default invocation
		super(50, 350, 200, Color.MAGENTA);
		
		this.setMyDirX(3);
		this.setMyDirY(-5);
	}
	
	
	// Methods
	//*
	public void keyPressed(Key k) {
		if (k == Key.UP) {
			this.setMyDirX(0);
			this.setMyDirY(-5);
		}
		else if (k == Key.DOWN) {
			this.setMyDirX(0);
			this.setMyDirY(5);
		}
		else if (k == Key.LEFT) {
			this.setMyDirX(-5);
			this.setMyDirY(0);
		}
		else if (k == Key.RIGHT) {
			this.setMyDirX(5);
			this.setMyDirY(0);
		}

		super.keyPressed(k);		// invokes overridden method
	}
	//*/
	
	
	
	//*
	public void keyReleased (Key k) {
		super.keyReleased(k);
		this.setMySize(5);
	}
	//*/
}