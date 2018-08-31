import java.awt.event.MouseAdapter;

import javax.swing.JComponent;


public class Draglistener extends MouseAdapter {
	private boolean b=true;
	private int oldx,oldy,newx,newy;
	private int startx,starty;
	
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		if(b)
		{
			JComponent cp= (JComponent) e.getSource();
			startx=cp.getX();
			starty=cp.getY();
			oldx=e.getXOnScreen();
			oldy=e.getYOnScreen();
		}
		
	}
	
	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		if(b) {
			JComponent cp =(JComponent) e.getSource();
			newx=e.getXOnScreen();
			newy=e.getYOnScreen();
			
			cp.setBounds(startx+(newx-oldx),starty+(newy-oldy),cp.getWidth(),cp.getHeight());
		}
		
	}
	
	public boolean getb() {
		return b;
	}
	
	public void setb(boolean bb) {
		b=bb;
	}
}
