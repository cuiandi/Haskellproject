import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;
 
public class Linklistener extends MouseAdapter {
	JComponent link1,link2;
	int count=0;
	private boolean b=false;
	public Linklistener() {
	}
 
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		switch (count) {
		case 0:
			link1= (JComponent) e.getSource();
			count++;
			break;
		case 1:
			link2= (JComponent) e.getSource();
			if (link1!=link2) {
				System.out.println("Link");
			}
			count++;
			break;
		}
	}
	
	public void setb(boolean bb) {
		b=bb;
	}
	
	public boolean getb()
	{
		return b;
	}
	
	public void setcount()
	{
		count=0;
	}
}