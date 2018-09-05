import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class datatypebox extends JPanel{

	JTextField Datatype=new JTextField(10);
	JTextField type1=new JTextField(10);
	JTextField type2=new JTextField(10);
	public datatypebox() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel jp1=new JPanel();
		JPanel jp2=new JPanel();
		jp1.setBorder(BorderFactory.createEtchedBorder());
		jp2.setBorder(BorderFactory.createEtchedBorder());
		//init jp1
		Datatype.addFocusListener(new JTextFieldHintListener(Datatype, "Datatype"));
		jp1.add(Datatype);
		this.add(jp1);
		
		//init jp2
		type1.addFocusListener(new JTextFieldHintListener(type1, "type1"));
		type2.addFocusListener(new JTextFieldHintListener(type2, "type2"));
		jp2.add(type1);
		jp2.add(type2);
		this.add(jp2);
		
	}
	
	public String getdatatype() {
		return Datatype.getText();
	}
	public String gettype1()
	{
		return type1.getText();
	}
	
	public String gettype2() {
		return type2.getText();
	}
}
