package gui;

import entities.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mysql.jdbc.Field;

import testdriveClasses.Program;

import javax.print.attribute.standard.PDLOverrideSupported;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class SearchGUI {

	private JFrame frame;
	private JTextField searchTerm;
	private final String title = "Track My Files";
	private ArrayList<Tag> tagList;
	private ArrayList<File> fileList;
	private JScrollPane jscrlPane;
	private JButton searchButton;
	private SpringLayout springLayout;
	private JLabel labelNameConst;
	private JLabel labelSizeConst;
	private JLabel labelPathConst;
	private JLabel labelFileName;
	private JLabel labelFilePath;
	private JLabel labelFileSize;
	private JComboBox<String> tagCombo;
	private JList<String> searchResultList;
	private EventListeners eventListeners;
	private JButton runButton;
	private JButton openLocationButton;
	private DefaultListModel<String> model;
	private File currentFile = null;

 	public SearchGUI() {
		eventListeners = new EventListeners();
		initialize();
		
	}

	private void initialize() {
		frame = new JFrame();
		springLayout = new SpringLayout();
		searchTerm = new JTextField();
		searchButton = new JButton("Search");
		tagCombo = new JComboBox <String> ();
		model = new DefaultListModel <String>();
		
		
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
		searchResultList = new JList<String>(model);
		searchResultList.addListSelectionListener(eventListeners);
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
		/*****RUN BUTTON****/
		
			
		runButton = new JButton("Run");
		springLayout.putConstraint(SpringLayout.NORTH, runButton, 400, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, runButton, 460, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(runButton);
		runButton.addActionListener(eventListeners);
		runButton.setVisible(false);
		
		/*****OPEN LOCATION BUTTON*****/
		openLocationButton = new JButton("Open Location");
		springLayout.putConstraint(SpringLayout.NORTH, openLocationButton, 0, SpringLayout.NORTH, runButton);
		springLayout.putConstraint(SpringLayout.WEST, openLocationButton, 42, SpringLayout.EAST, runButton);
		frame.getContentPane().add(openLocationButton);
		openLocationButton.addActionListener(eventListeners);
		openLocationButton.setVisible(false);
		
		labelNameConst = new JLabel("Name :");
		springLayout.putConstraint(SpringLayout.WEST, labelNameConst, 154, SpringLayout.EAST, jscrlPane);
		springLayout.putConstraint(SpringLayout.SOUTH, labelNameConst, -362, SpringLayout.SOUTH, frame.getContentPane());
		labelNameConst.setFont(new Font("Tahoma", Font.BOLD, 19));
		frame.getContentPane().add(labelNameConst);
		
		
		labelPathConst = new JLabel("Path   :");
		springLayout.putConstraint(SpringLayout.NORTH, labelPathConst, 19, SpringLayout.SOUTH, labelNameConst);
		springLayout.putConstraint(SpringLayout.WEST, labelPathConst, 0, SpringLayout.WEST, labelNameConst);
		labelPathConst.setFont(new Font("Tahoma", Font.BOLD, 19));
		frame.getContentPane().add(labelPathConst);
		
		
		labelSizeConst = new JLabel("Size   :");
		springLayout.putConstraint(SpringLayout.NORTH, labelSizeConst, 19, SpringLayout.SOUTH, labelPathConst);
		springLayout.putConstraint(SpringLayout.WEST, labelSizeConst, 155, SpringLayout.EAST, jscrlPane);
		labelSizeConst.setFont(new Font("Tahoma", Font.BOLD, 19));
		frame.getContentPane().add(labelSizeConst);
		
		labelFileName = new JLabel("");//
		labelFileName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		springLayout.putConstraint(SpringLayout.SOUTH, labelFileName, 0, SpringLayout.SOUTH, labelNameConst);
		springLayout.putConstraint(SpringLayout.WEST, labelFileName, 31, SpringLayout.EAST, labelNameConst);
		frame.getContentPane().add(labelFileName);
		
		labelFilePath = new JLabel("");//
		labelFilePath.setFont(new Font("Tahoma", Font.PLAIN, 16));
		springLayout.putConstraint(SpringLayout.SOUTH, labelFilePath, 0, SpringLayout.SOUTH, labelPathConst);
		springLayout.putConstraint(SpringLayout.WEST, labelFilePath, 31, SpringLayout.EAST, labelPathConst);
		frame.getContentPane().add(labelFilePath);
		
		labelFileSize = new JLabel("");//
		labelFileSize.setFont(new Font("Tahoma", Font.PLAIN, 16));
		springLayout.putConstraint(SpringLayout.SOUTH, labelFileSize, 0, SpringLayout.SOUTH, labelSizeConst);
		springLayout.putConstraint(SpringLayout.WEST, labelFileSize, 31, SpringLayout.EAST, labelSizeConst);
		frame.getContentPane().add(labelFileSize);
		
		fileDetailVisibility(false);
		
		
			frame.setVisible(true);
	}

	private void fileDetailVisibility (boolean visible){
		openLocationButton.setVisible(visible);
		runButton.setVisible(visible);
		labelNameConst.setVisible(visible);
		labelPathConst.setVisible(visible);
		labelSizeConst.setVisible(visible);
		labelFileName.setVisible(visible);
		labelFilePath.setVisible(visible);
		labelFileSize.setVisible(visible);
		
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		
	}
	
	public void addFileDetails (File file){
		
		String fileName = file.getFileName();
		String filePath = file.getPath();
		String fileSize = file.getSizeInString();
		
		labelFileName.setText(fileName);
		labelFilePath.setText(filePath);
		labelFileSize.setText(fileSize);
		//frame.getContentPane().validate();
		//frame.getContentPane().repaint();
		
	}
	
	private void setTagComboBox (ArrayList<Tag> tagArray){
		
		tagCombo.removeAll();
		for (Tag i : tagArray){
			System.out.println("Adding Tag Items To TagComboBox");
			tagCombo.addItem(i.getTagName());
		}
		
	}
	
	private class EventListeners implements ActionListener, DocumentListener,ListSelectionListener{

		String searchString = null;
		Object source;
		Tag tag;
		String tagName;
		boolean noFilter = false;
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			source = e.getSource();
			
			if (source==searchButton){
				model.removeAllElements();
				tagName = (String) tagCombo.getSelectedItem();
				noFilter=checkNoFilter (tagName);
				tag = new Tag(Program.databaseHelper.getTagId(tagName),tagName);
				fileList=null;
				System.err.println("Search Button pressed");
				searchResultList.clearSelection();
				fileDetailVisibility(false);
				
				if (searchString!=null){
					if (noFilter)
						fileList = Program.databaseHelper.getFile(searchString);
					else
						fileList = Program.databaseHelper.getFile(searchString,tag);
				}
				else {
					System.err.println("TAG NAME "+tag);
					fileList=Program.databaseHelper.getFile(tag);
					System.out.println("Ran tag");
				}
				

				for (File f : fileList){
					model.addElement(f.getFileName());
					System.out.println("Adding Model");
				}
			}
			else if (source==runButton){
				
				try {
					Desktop.getDesktop().open(new java.io.File(currentFile.getPath()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.out.println("Run Button");
			}
			else if (source==openLocationButton) {
				try {
					Desktop.getDesktop().open( new java.io.File (currentFile.getFolderPath()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			
		}

		/****SEARCH FIELD LISTENER METHODS****/
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
		
		
		/**** SEARCH RESULT LISTENERS ****/
		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			if (fileList!=null&&e.getValueIsAdjusting() == true){
			
			currentFile = fileList.get(searchResultList.getSelectedIndex());
			System.out.println("Folder Path : "+ currentFile.getFolderPath());
			addFileDetails(currentFile);
			fileDetailVisibility(true);
			
			
			}
			
		}
		
		
		/*************************************************/
		
		
		public boolean checkNoFilter (String tagName){
			System.out.println("TagName in check =="+tagName);
			if (tagName.equalsIgnoreCase("no filter"))
				return true;
			else
				return false;
			}	
	}
}
