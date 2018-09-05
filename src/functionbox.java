import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class functionbox extends JPanel{
	private int i=0;
	private int j=-1;
	ArrayList<JTextField> namelist=new ArrayList<>();
	ArrayList<JTextField> typelist=new ArrayList<>();
	ArrayList<JTextField> conlist=new ArrayList<>();
	JTextField Fname=new JTextField(10);
	JTextField Ftype=new JTextField(10);
	JTextArea codearea=new JTextArea(4,20);
	public functionbox() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel jp1=new JPanel();
		JPanel jp2=new JPanel();
		JPanel jp3=new JPanel();
		JPanel jp4=new JPanel();
		jp1.setBorder(BorderFactory.createEtchedBorder());
		jp2.setBorder(BorderFactory.createEtchedBorder());
		jp3.setBorder(BorderFactory.createEtchedBorder());
		jp4.setBorder(BorderFactory.createEtchedBorder());
		//init jp1 (name)
		Fname.addFocusListener(new JTextFieldHintListener(Fname, "FunctionName"));
		Ftype.addFocusListener(new JTextFieldHintListener(Ftype, "Type"));
		jp1.add(Fname);
		jp1.add(Ftype);
		this.add(jp1);
		//init jp2 (parameter)
		jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
		JButton addbtn=new JButton("Add");
		JButton delbtn=new JButton("Delete");
		JPanel bJPanel= new JPanel();	
		bJPanel.add(addbtn,BorderLayout.CENTER);
		bJPanel.add(delbtn,BorderLayout.CENTER);
		jp2.add(bJPanel);
		VPanel v=new VPanel(i);
		namelist.add(v.getnametext());
		typelist.add(v.gettypetext());
		jp2.add(v);
		this.add(jp2);
		//init jp4(con)
		jp4.setLayout(new BoxLayout(jp4, BoxLayout.Y_AXIS));
		JLabel conl=new JLabel("Constraints");
		JButton addconbtn=new JButton("Add");
		JButton delconbtn=new JButton("Delete");
		JPanel bcJPanel= new JPanel();	
		bcJPanel.add(conl,BorderLayout.WEST);
		bcJPanel.add(addconbtn,BorderLayout.CENTER);
		bcJPanel.add(delconbtn,BorderLayout.CENTER);
		jp4.add(bcJPanel);
		this.add(jp4);
		
		
		//init jp3(code)
		jp3.setLayout(new BoxLayout(jp3, BoxLayout.Y_AXIS));
		JComboBox codetype=new JComboBox();
		codetype.addItem("Code");
		codetype.addItem("Code2");
		JScrollPane js=new JScrollPane(codearea);
		js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jp3.add(codetype);
		jp3.add(js);
		this.add(jp3);
		
		
		//button action
		addbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				i++;
				VPanel v=new VPanel(i);
				jp2.add(v);
				namelist.add(v.getnametext());
				typelist.add(v.gettypetext());
				jp2.revalidate();
				setSize(getSize().width,getSize().height+40);
			}
		});
		
		delbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(i>0)
				{
					jp2.remove(jp2.getComponentCount()-1);
					jp2.revalidate();
					namelist.remove(namelist.size()-1);
					typelist.remove(typelist.size()-1);
					setSize(getSize().width,getSize().height-40);
					i--;
				}
			}
		});
		
		//con btn action
		addconbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				j++;
				conText ct=new conText(j);
				jp4.add(ct);
				conlist.add(ct);
				jp4.revalidate();
				setSize(getSize().width,getSize().height+40);
			}
		});
		
		delconbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(j>-1)
				{
					jp4.remove(jp4.getComponentCount()-1);
					jp4.revalidate();
					conlist.remove(conlist.size()-1);
					setSize(getSize().width,getSize().height-40);
					j--;
				}
			}
		});
		
		
	}
	
	public String getfuncname() {
		return Fname.getText();
	}
	public String getfunctype()
	{
		return Ftype.getText();
	}
	
	public JTextArea getcodearea() {
		return codearea;
	}
	
	
}

class conText extends JTextField{
	public conText(int j) {
		this.addFocusListener(new JTextFieldHintListener(this, "Constraints"+j));
	}
	
}

class VPanel extends JPanel{
	JTextField Vname= new JTextField(10);
	JTextField Vtype= new JTextField(10);
	public VPanel(int i) {
		// TODO Auto-generated constructor stub
		Vname.addFocusListener(new JTextFieldHintListener(Vname, "VariableName"+i));
		Vtype.addFocusListener(new JTextFieldHintListener(Vtype, "VariableType"+i));
		add(Vname);
		add(Vtype);
	}
	
	
	public JTextField getnametext() {
		return Vname;
	}
	
	public JTextField gettypetext() {
		return Vtype;
	}
}