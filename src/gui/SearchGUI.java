package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class SearchGUI {

	private JFrame frame;
	private JTextField searchTerm;
	private final String title = "Main GUI";
	private String [] tagList;
	private String [] searchResults;
	JScrollPane jscrlPane;
	
	SpringLayout springLayout;
	boolean displayFileDetail = true;
	JLabel labelNameConst;
	JLabel labelSizeConst;
	
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchGUI window = new SearchGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SearchGUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		frame.setTitle(title);
		
		searchTerm = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, searchTerm, -476, SpringLayout.SOUTH, frame.getContentPane());
		searchTerm.setFont(new Font("Tahoma", Font.BOLD, 16));
		springLayout.putConstraint(SpringLayout.WEST, searchTerm, 22, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, searchTerm, -29, SpringLayout.EAST, frame.getContentPane());
		searchTerm.setToolTipText("");
		frame.getContentPane().add(searchTerm);
		searchTerm.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		springLayout.putConstraint(SpringLayout.NORTH, searchButton, 21, SpringLayout.SOUTH, searchTerm);
		springLayout.putConstraint(SpringLayout.SOUTH, searchButton, -426, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, searchButton, -177, SpringLayout.EAST, frame.getContentPane());
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchAction();
			}
		});
		frame.getContentPane().add(searchButton);
		
		JComboBox tagCombo = new JComboBox ();
		springLayout.putConstraint(SpringLayout.WEST, searchButton, 154, SpringLayout.EAST, tagCombo);
		springLayout.putConstraint(SpringLayout.NORTH, tagCombo, 21, SpringLayout.SOUTH, searchTerm);
		springLayout.putConstraint(SpringLayout.SOUTH, tagCombo, -426, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, tagCombo, 0, SpringLayout.WEST, searchTerm);
		springLayout.putConstraint(SpringLayout.EAST, tagCombo, -468, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(tagCombo);
		
		JMenuBar menuBar = new JMenuBar();
		springLayout.putConstraint(SpringLayout.NORTH, searchTerm, 22, SpringLayout.SOUTH, menuBar);
		menuBar.setBackground(new Color(128, 128, 128));
		springLayout.putConstraint(SpringLayout.NORTH, menuBar, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, menuBar, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, menuBar, 782, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(menuBar);
		
		JList searchResultList = new JList(); //Add the array here 
		
		
		jscrlPane = new JScrollPane (searchResultList);
		jscrlPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jscrlPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		springLayout.putConstraint(SpringLayout.NORTH, jscrlPane, 21, SpringLayout.SOUTH, tagCombo);
		springLayout.putConstraint(SpringLayout.SOUTH, jscrlPane, -50, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST,jscrlPane,0,SpringLayout.WEST,tagCombo);
		springLayout.putConstraint(SpringLayout.EAST,jscrlPane,0,SpringLayout.EAST,tagCombo);
		frame.getContentPane().add(jscrlPane);
		
		searchResultList.setFont(new Font("Tahoma", Font.ITALIC, 20));
			
		
			setFileDetails("Prince Of Persia","C:\\ProgramFiles\\POP","8Gb");
		
			JButton btnOpen = new JButton("Run");
			springLayout.putConstraint(SpringLayout.NORTH, btnOpen, 51, SpringLayout.SOUTH, labelSizeConst);
			springLayout.putConstraint(SpringLayout.WEST, btnOpen, 154, SpringLayout.EAST, jscrlPane);
			springLayout.putConstraint(SpringLayout.SOUTH, btnOpen, -202, SpringLayout.SOUTH, frame.getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, btnOpen, -219, SpringLayout.EAST, frame.getContentPane());
			frame.getContentPane().add(btnOpen);
			
			JButton btnOpenLocation = new JButton("Open Location");
			springLayout.putConstraint(SpringLayout.NORTH, btnOpenLocation, 0, SpringLayout.NORTH, btnOpen);
			springLayout.putConstraint(SpringLayout.WEST, btnOpenLocation, 42, SpringLayout.EAST, btnOpen);
			frame.getContentPane().add(btnOpenLocation);		
	}
	
	/*
	 * DBMS Fetch here 
	 * return corresponding boolean to denote if search is successful 
	 * or not
	 */
	private boolean searchAction (){
		return true;
	}
	
	public void setTagArray(String [] tagList){
		this.tagList= tagList;
	}
	
	public void setFileDetails (String fileName,String filePath,String fileSize){

		labelNameConst = new JLabel("Name :");
		springLayout.putConstraint(SpringLayout.WEST, labelNameConst, 154, SpringLayout.EAST, jscrlPane);
		springLayout.putConstraint(SpringLayout.SOUTH, labelNameConst, -362, SpringLayout.SOUTH, frame.getContentPane());
		labelNameConst.setFont(new Font("Tahoma", Font.BOLD, 19));
		frame.getContentPane().add(labelNameConst);
		
		JLabel labelPathConst = new JLabel("Path   :");
		springLayout.putConstraint(SpringLayout.NORTH, labelPathConst, 19, SpringLayout.SOUTH, labelNameConst);
		springLayout.putConstraint(SpringLayout.WEST, labelPathConst, 0, SpringLayout.WEST, labelNameConst);
		labelPathConst.setFont(new Font("Tahoma", Font.BOLD, 19));
		frame.getContentPane().add(labelPathConst);
		
		 labelSizeConst = new JLabel("Size   :");
		springLayout.putConstraint(SpringLayout.NORTH, labelSizeConst, 19, SpringLayout.SOUTH, labelPathConst);
		springLayout.putConstraint(SpringLayout.WEST, labelSizeConst, 155, SpringLayout.EAST, jscrlPane);
		labelSizeConst.setFont(new Font("Tahoma", Font.BOLD, 19));
		frame.getContentPane().add(labelSizeConst);
		
		JLabel lblFileName = new JLabel(fileName);//
		lblFileName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		springLayout.putConstraint(SpringLayout.SOUTH, lblFileName, 0, SpringLayout.SOUTH, labelNameConst);
		springLayout.putConstraint(SpringLayout.WEST, lblFileName, 31, SpringLayout.EAST, labelNameConst);
		frame.getContentPane().add(lblFileName);
		
		JLabel lblClocalhost = new JLabel(filePath);//
		lblClocalhost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		springLayout.putConstraint(SpringLayout.SOUTH, lblClocalhost, 0, SpringLayout.SOUTH, labelPathConst);
		springLayout.putConstraint(SpringLayout.WEST, lblClocalhost, 31, SpringLayout.EAST, labelPathConst);
		frame.getContentPane().add(lblClocalhost);
		
		JLabel label = new JLabel(fileSize);//
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		springLayout.putConstraint(SpringLayout.SOUTH, label, 0, SpringLayout.SOUTH, labelSizeConst);
		springLayout.putConstraint(SpringLayout.WEST, label, 31, SpringLayout.EAST, labelSizeConst);
		frame.getContentPane().add(label);
		
	}
}
