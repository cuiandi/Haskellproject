
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
 
public class PFocuslistener extends MouseAdapter {
	JComponent pp;
	JComponent cp;
	JComponent link1,link2;
	public PFocuslistener() {
	}
 
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		cp= (JComponent) e.getSource();
		if(pp==null)
		{
			pp=cp;
		}
		if(pp.equals(cp)) {
			cp.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		}
		else {
			pp.setBorder(null);
			cp.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
			pp=cp;
		}
		cp.revalidate();
	}

	public JComponent getcp() {
		return cp;
	}


	
	
}