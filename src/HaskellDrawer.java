import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import com.sl.connector.*;
import com.sl.line.*;

public class HaskellDrawer{

	private JFrame frame;
	private ArrayList<functionbox> funclist=new ArrayList<>();
	private ArrayList<datatypebox> datlist=new ArrayList<>();
	PFocuslistener pf=new PFocuslistener();
	Draglistener d=new Draglistener();
	Linklistener l=new Linklistener();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HaskellDrawer window = new HaskellDrawer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HaskellDrawer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//main window
		frame = new JFrame();
		frame.setBounds(20, 20, 1280, 768);
		frame.setTitle("HaskellDrawer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = frame.getContentPane();
		//menu
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu menufile=new JMenu("File");
		JMenuItem itemopen,itemsave,itemload,itemexit;
		itemopen=new JMenuItem("New");
		itemsave=new JMenuItem("Save");
		itemload=new JMenuItem("Load");
		itemexit=new JMenuItem("Exit");
		menufile.add(itemopen);
		menufile.add(itemsave);
		menufile.add(itemload);
		menufile.add(itemexit);
		menuBar.add(menufile);
		//tool bar
		JToolBar toolBar=new JToolBar();
		toolBar.setLayout(new FlowLayout(0));
		//tool bar button
		JButton btnrun=new JButton("Go!");
		btnrun.setPreferredSize(new Dimension(40, 40));
		toolBar.add(btnrun);
		JButton btnlast=new JButton("last");
		btnlast.setPreferredSize(new Dimension(40, 40));
		toolBar.add(btnlast);
		JButton btnnext=new JButton("next");
		btnnext.setPreferredSize(new Dimension(40, 40));
		toolBar.add(btnnext);
		JButton btndel=new JButton("delete");
		btndel.setPreferredSize(new Dimension(40, 40));
		toolBar.add(btndel);
		JButton btncopy=new JButton("copy");
		btncopy.setPreferredSize(new Dimension(40, 40));
		toolBar.add(btncopy);
		JButton btnpaste=new JButton("paste");
		btnpaste.setPreferredSize(new Dimension(40, 40));
		toolBar.add(btnpaste);
		cp.add(toolBar,BorderLayout.NORTH);
		//side bar
		JPanel sidebar = new JPanel();
		sidebar.setBorder(BorderFactory.createEtchedBorder());
		sidebar.setLayout(new GridLayout(10,1));
		JButton fctn=new JButton("Function");
		JButton datp=new JButton("DataType");
		JToggleButton link=new JToggleButton("Link");
		sidebar.add(fctn);
		sidebar.add(datp);
		sidebar.add(link);
		cp.add(sidebar,BorderLayout.WEST);
		//main stage
		JTabbedPane jTabbedPane= new JTabbedPane();
		jTabbedPane.setBorder(BorderFactory.createEtchedBorder());
		JPanel drawstage=new JPanel();
		JPanel textstage=new JPanel();
		//draw stage
		drawstage.setBackground(new Color(255, 255, 255));
		drawstage.setLayout(null);
		JScrollPane js=new JScrollPane(drawstage);
		js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//text stage
		JTextArea jTextArea= new JTextArea();
		textstage.setLayout(new GridLayout(1, 1));
		textstage.add(jTextArea);
		//add stage
		jTabbedPane.addTab("drawstage", js);
		jTabbedPane.addTab("textstage", textstage);
		cp.add(jTabbedPane,BorderLayout.CENTER);
		
		
		//button action
		fctn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				functionbox functionbox=new functionbox();
				drawstage.add(functionbox);
				functionbox.setBounds(20, 20, 260, 250);
				functionbox.addMouseListener(d);
				functionbox.addMouseMotionListener(d);
				functionbox.addMouseListener(pf);
				functionbox.addMouseMotionListener(pf);
				functionbox.addMouseListener(l);
				functionbox.addMouseMotionListener(l);
				drawstage.revalidate();
				drawstage.repaint();
				funclist.add(functionbox);
			}
		});
		
		datp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				datatypebox dtb=new datatypebox();
				drawstage.add(dtb);
				dtb.setBounds(20, 20, 260, 80);
				dtb.addMouseListener(d);
				dtb.addMouseMotionListener(d);
				dtb.addMouseListener(pf);
				dtb.addMouseMotionListener(pf);
				dtb.addMouseListener(l);
				dtb.addMouseMotionListener(l);
				drawstage.revalidate();
				drawstage.repaint();
				datlist.add(dtb);
			}
		});
		
		link.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(link.isSelected())
				{
					d.setb(false);
					l.setb(true);
					if(l.count==2)
					{
						JConnector[] connector =new JConnector[1];
						connector[0] =new JConnector(l.link1, l.link2,ConnectLine.LINE_ARROW_BOTH,JConnector.CONNECT_LINE_TYPE_SIMPLE,Color.red);
						ConnectorContainer cc=new ConnectorContainer(connector);
						drawstage.add(cc);
						drawstage.revalidate();
						drawstage.repaint();
						link.setSelected(false);
						l.setcount();
						System.out.println(l.count);
					}
					
					
				}
				else {
					d.setb(true);
					l.setb(false);
				}
			}
		});
		
		//tool bar action
		
		btnrun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				codefactory cf=new codefactory(funclist,datlist);
				jTextArea.setText(cf.runcode().toString());
			}
		});
		
		btndel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(pf.getcp()!=null)
				{
					drawstage.remove(pf.getcp());
					funclist.remove(pf.getcp());
					drawstage.revalidate();
					drawstage.repaint();
				}
				
			}
		});
		
		
		
		
	}
	
	
	
	

}
