package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entities.Tag;
import helpers.DatabaseHelper;
import testdriveClasses.Program;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.ArrayList;
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
	private ArrayList<Tag> tagList;
	private String[] searchResults;
	private JScrollPane jscrlPane;
	private JButton searchButton;
	private SpringLayout springLayout;
	boolean displayFileDetail = true;
	private JLabel labelNameConst;
	private JLabel labelSizeConst;
	private JComboBox<String> tagCombo;
	private JList searchResultList;
	EventListeners eventListeners;
	JButton runButton;
	JButton openLocationButton;

	public SearchGUI() {
		eventListeners = new EventListeners();
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				initialize();
				
				
			}
		});
		
	}

	private void initialize() {
		frame = new JFrame();
		springLayout = new SpringLayout();
		searchTerm = new JTextField();
		searchButton = new JButton("Search");
		tagCombo = new JComboBox <String> ();
		
		
		/*****FRAME*****/
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(springLayout);
		frame.setTitle(title);
		
		/*****SEARCH TEXTBOX*****/
		springLayout.putConstraint(SpringLayout.SOUTH, searchTerm, -476, SpringLayout.SOUTH, frame.getContentPane());
		searchTerm.setFont(new Font("Tahoma", Font.BOLD, 16));
		springLayout.putConstraint(SpringLayout.WEST, searchTerm, 22, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, searchTerm, -29, SpringLayout.EAST, frame.getContentPane());
		searchTerm.setToolTipText("Enter File Name Here");
		frame.getContentPane().add(searchTerm);
		searchTerm.setColumns(10);
		searchTerm.getDocument().addDocumentListener(eventListeners);
		/*****SEARCH BUTTON*****/
		springLayout.putConstraint(SpringLayout.NORTH, searchButton, 21, SpringLayout.SOUTH, searchTerm);
		springLayout.putConstraint(SpringLayout.SOUTH, searchButton, -426, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, searchButton, -177, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(searchButton);
		searchButton.addActionListener(eventListeners);
		
		/*****TAG COMBOBOX*****/
		springLayout.putConstraint(SpringLayout.WEST, searchButton, 154, SpringLayout.EAST, tagCombo);
		springLayout.putConstraint(SpringLayout.NORTH, tagCombo, 21, SpringLayout.SOUTH, searchTerm);
		springLayout.putConstraint(SpringLayout.SOUTH, tagCombo, -426, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, tagCombo, 0, SpringLayout.WEST, searchTerm);
		springLayout.putConstraint(SpringLayout.EAST, tagCombo, -468, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(tagCombo);
		tagList = Program.databaseHelper.getTagList();
		this.setTagComboBox(tagList);
		
		/*****MENU BAR*****/
		JMenuBar menuBar = new JMenuBar();
		springLayout.putConstraint(SpringLayout.NORTH, searchTerm, 22, SpringLayout.SOUTH, menuBar);
		menuBar.setBackground(new Color(128, 128, 128));
		springLayout.putConstraint(SpringLayout.NORTH, menuBar, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, menuBar, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, menuBar, 782, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(menuBar);
		
		/*****JSCROLL PANE WITH SEARCH RESULT*****/
		searchResultList = new JList();
		searchResultList.setFont(new Font("Tahoma", Font.ITALIC, 20));
		jscrlPane = new JScrollPane (searchResultList);
		jscrlPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jscrlPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		springLayout.putConstraint(SpringLayout.NORTH, jscrlPane, 21, SpringLayout.SOUTH, tagCombo);
		springLayout.putConstraint(SpringLayout.SOUTH, jscrlPane, -50, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST,jscrlPane,0,SpringLayout.WEST,tagCombo);
		springLayout.putConstraint(SpringLayout.EAST,jscrlPane,0,SpringLayout.EAST,tagCombo);
		frame.getContentPane().add(jscrlPane);
		
		
		/****Implement SetFileDetail() Before Run Button and Open Location button is set*****/
		/*****RUN BUTTON****//*
		runButton = new JButton("Run");
		springLayout.putConstraint(SpringLayout.NORTH, runButton, 51, SpringLayout.SOUTH, labelSizeConst);
		springLayout.putConstraint(SpringLayout.WEST, runButton, 154, SpringLayout.EAST, jscrlPane);
		springLayout.putConstraint(SpringLayout.SOUTH, runButton, -202, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, runButton, -219, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(runButton);
		
		*//*****OPEN LOCATION BUTTON*****//*
		openLocationButton = new JButton("Open Location");
		springLayout.putConstraint(SpringLayout.NORTH, openLocationButton, 0, SpringLayout.NORTH, runButton);
		springLayout.putConstraint(SpringLayout.WEST, openLocationButton, 42, SpringLayout.EAST, runButton);
		frame.getContentPane().add(openLocationButton);*/
		
		if (frame != null)
			frame.setVisible(true);
	}

	private boolean searchAction (){
		return true;
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
	
	private void setTagComboBox (ArrayList<Tag> tagArray){
		String[] tagNameArray= new String[tagArray.size()];
		int index = 0;
		tagCombo.removeAll();
		for (Tag i : tagArray){
			System.out.println("Adding Tag Items To TagComboBox");
			tagCombo.addItem(i.getTagName());
		}
		
	}
	
	private class EventListeners implements ActionListener, DocumentListener{

		String searchString = null;
		Object source;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			source = e.getSource();
			if (source==searchButton){
				System.out.println("Button Pressed :"+searchString);
			}
			else if (source==runButton){
				
			}
			else if (source==openLocationButton) {
				
			}
			
		}

		@Override
		public void changedUpdate(DocumentEvent e) {}

		@Override
		public void insertUpdate(DocumentEvent e) {
			searchString = searchTerm.getText();			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			searchString = searchTerm.getText();	
		}
		
	}
}
