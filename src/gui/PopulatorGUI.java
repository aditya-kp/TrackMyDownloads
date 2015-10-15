package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entities.Tag;
import helpers.PopulatorDatabaseHelper;

public class PopulatorGUI {
	private JFrame frame=null;
	private JTextField fileFeild=null;
	private File choosenFile=null;
	private PopulatorDatabaseHelper databaseHelper= null;
	//private ArrayList<Tag> tagArrayList=null;
	//private ArrayList<Tag> addedTagArrayList=null;
	private DefaultListModel<Tag> tagListModel=null;
	private DefaultListModel<Tag> addedTagListModel=null;
	private JTextField newTagFeild=null;
	private Tag selectedTag=null;
	private Tag selectedAddedTag=null;
	private JList<Tag> addedTagList=null;
	private JList<Tag> tagList=null;
	
	
	public void initialise(){
		try{
			databaseHelper=new PopulatorDatabaseHelper();
			databaseHelper.initialize();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		ArrayList<Tag>tagArrayList= databaseHelper.getTagList();
		tagListModel=new DefaultListModel<>();
		for(Tag t: tagArrayList){
			tagListModel.addElement(t);
		}
		addedTagListModel= new DefaultListModel<>();

		JLabel fileLabel=new JLabel("File Name:");
		//JLabel pathLabel=new JLabel("File Path:");
		//nameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		//pathLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		fileFeild= new JTextField(15);
		//JTextField pathFeild= new JTextField(20);
		
		JButton browseButton= new JButton("Browse");
		browseButton.addActionListener(new browseButtonListener());
				
		JLabel newTagLabel= new JLabel("New Tag Name:");
		newTagFeild= new JTextField(20);
		JButton addTagButton= new JButton("Add");
		addTagButton.addActionListener(new AddTagButtonListener());
		
		JButton doneButton= new JButton("Done");
		doneButton.addActionListener(new DoneButtonListener());
		
		tagList= new JList<>(tagListModel);
		tagList.setVisibleRowCount(6);
		tagList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tagList.addListSelectionListener(new TagListListener());
		
		JScrollPane tagListScroller= new JScrollPane(tagList);
		tagListScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tagListScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		addedTagList= new JList<>(addedTagListModel);
		addedTagList.setVisibleRowCount(6);
		addedTagList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addedTagList.addListSelectionListener(new AddedTagListListener());
		
		JScrollPane addedTagScroller= new JScrollPane(addedTagList);
		addedTagScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		addedTagScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JLabel tagsLabel=new JLabel("Tags:");
		JLabel addedTagsLabel=new JLabel("Added Tags:");

		Dimension scrollPaneSize=tagListScroller.getPreferredSize();
		scrollPaneSize.width=173;
		tagListScroller.setMaximumSize(scrollPaneSize);
		tagListScroller.setMinimumSize(scrollPaneSize);
		addedTagScroller.setMaximumSize(scrollPaneSize);
		addedTagScroller.setMinimumSize(scrollPaneSize);
		
		Font normalFont= fileFeild.getFont();
		tagList.setFont(normalFont);
		addedTagList.setFont(normalFont);
		

		JButton addIconButton= new JButton();
		JButton removeIconButton= new JButton();
		addIconButton.addActionListener(new AddIconButtonListener());
		removeIconButton.addActionListener(new RemoveIconButtonListener());
		try{
			Image add= ImageIO.read(getClass().getResource("/icons/add_icon.png"));
			addIconButton.setIcon(new ImageIcon(add));
			
			Image rem=ImageIO.read(getClass().getResource("/icons/rem_icon.png"));
			removeIconButton.setIcon(new ImageIcon(rem));
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		
		Dimension iconButtonSize= addIconButton.getPreferredSize();
		iconButtonSize.width=40;
		addIconButton.setMaximumSize(iconButtonSize);
		addIconButton.setMinimumSize(iconButtonSize);
		removeIconButton.setMaximumSize(iconButtonSize);
		removeIconButton.setMinimumSize(iconButtonSize);
		
		//JPanel northLeftPanel= new JPanel();
		//northLeftPanel.setLayout(new BoxLayout(northLeftPanel, BoxLayout.Y_AXIS));
		//northLeftPanel.add(nameLabel);
		//northLeftPanel.add(Box.createRigidArea(new Dimension(0,3)));
		//northLeftPanel.add(pathLabel);
				
		//JPanel northRightPanel= new JPanel();
		//northRightPanel.setLayout(new BoxLayout(northRightPanel, BoxLayout.Y_AXIS));
		//northRightPanel.add(nameFeild);
		//northRightPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		//northRightPanel.add(pathFeild);
		
		JPanel northPanel=new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		northPanel.add(fileLabel);
		northPanel.add(Box.createRigidArea(new Dimension(10,0)));
		northPanel.add(fileFeild);
		northPanel.add(Box.createRigidArea(new Dimension(10,0)));
		northPanel.add(browseButton);
		northPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
		
		JPanel southTopPanel= new JPanel();
		southTopPanel.setLayout(new BoxLayout(southTopPanel, BoxLayout.X_AXIS));
		southTopPanel.add(newTagLabel);
		southTopPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		southTopPanel.add(newTagFeild);
		southTopPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		southTopPanel.add(addTagButton);
		southTopPanel.setBorder(BorderFactory.createCompoundBorder(
								BorderFactory.createEmptyBorder(5,5,2,5),
								BorderFactory.createTitledBorder(
								  BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
								  "NewTag",TitledBorder.DEFAULT_POSITION,TitledBorder.TOP, normalFont)));
		
		JPanel southBottomPanel= new JPanel();
		southBottomPanel.setLayout(new BoxLayout(southBottomPanel, BoxLayout.X_AXIS));
		southBottomPanel.add(Box.createHorizontalGlue());
		southBottomPanel.add(doneButton);
		southBottomPanel.setBorder(BorderFactory.createEmptyBorder(0,5,4,8));
				
		JPanel southPanel= new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
		southPanel.add(southTopPanel);
		southPanel.add(southBottomPanel);
		
		JPanel centreLeftPanel= new JPanel();
		centreLeftPanel.setLayout(new BoxLayout(centreLeftPanel, BoxLayout.Y_AXIS));
		tagsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		tagListScroller.setAlignmentX(Component.LEFT_ALIGNMENT);
		centreLeftPanel.add(tagsLabel);
		centreLeftPanel.add(Box.createRigidArea(new Dimension(0,2)));
		centreLeftPanel.add(tagListScroller);
		
		JPanel centreMiddlePanel= new JPanel();
		centreMiddlePanel.setLayout(new BoxLayout(centreMiddlePanel, BoxLayout.Y_AXIS));
		centreMiddlePanel.add(addIconButton);
		centreMiddlePanel.add(Box.createRigidArea(new Dimension(0,5)));
		centreMiddlePanel.add(removeIconButton);
		
		JPanel centreRightPanel= new JPanel();
		centreRightPanel.setLayout(new BoxLayout(centreRightPanel, BoxLayout.Y_AXIS));
		addedTagsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		addedTagScroller.setAlignmentX(Component.LEFT_ALIGNMENT);
		centreRightPanel.add(addedTagsLabel);
		centreRightPanel.add(Box.createRigidArea(new Dimension(0,2)));
		centreRightPanel.add(addedTagScroller);
				
		JPanel centrePanel= new JPanel();
		centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.X_AXIS));
		centrePanel.add(centreLeftPanel);
		centrePanel.add(Box.createRigidArea(new Dimension(30, 0)));
		centrePanel.add(centreMiddlePanel);
		centrePanel.add(Box.createRigidArea(new Dimension(30, 0)));
		centrePanel.add(centreRightPanel);
		centrePanel.setBorder(BorderFactory.createCompoundBorder(
								BorderFactory.createEmptyBorder(5,5,5,5),
								BorderFactory.createTitledBorder(
								  BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
								  "TagFile",TitledBorder.DEFAULT_POSITION,TitledBorder.TOP, normalFont)));
		
				
		frame=new JFrame("Track My Downloads");
		Container contentPane=frame.getContentPane();
		frame.addWindowListener(new windowListener());
		
		contentPane.add(northPanel, BorderLayout.NORTH);
		contentPane.add(southPanel, BorderLayout.SOUTH);
		contentPane.add(centrePanel, BorderLayout.CENTER);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class browseButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae){
			JFileChooser fileChooser= new JFileChooser();
			fileChooser.showDialog(frame, "Choose");
			choosenFile= fileChooser.getSelectedFile();
			fileFeild.setText(choosenFile.getAbsolutePath());
		}
	}
	
	class windowListener implements WindowListener{

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
					}

		@Override
		public void windowClosed(WindowEvent e) {
			try{
				databaseHelper.terminate();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}

			System.exit(0);
		}

		@Override
		public void windowClosing(WindowEvent e) {
			frame.dispose();
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
		}
		
	}
	
	class TagListListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()){
				selectedTag=tagList.getSelectedValue();
			}
		}
		
	}
	
	class AddedTagListListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()){
				selectedAddedTag=addedTagList.getSelectedValue();
			}
		}
		
	}

	class AddIconButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println("in");
			if(selectedTag!=null){
				addedTagListModel.addElement(selectedTag);
				tagListModel.removeElement(selectedTag);
				
				//System.out.println(addedTagListModel.contains(selectedTag));
				//System.out.println(tagListModel.contains(selectedAddedTag));
			}
		}
		
	}
	
	class RemoveIconButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(selectedAddedTag!=null){
				tagListModel.addElement(selectedAddedTag);
				addedTagListModel.removeElement(selectedAddedTag);
				
				//System.out.println(addedTagListModel.contains(selectedTag));
				//System.out.println(tagListModel.contains(selectedAddedTag));
			}
		}
		
	}

	class AddTagButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String newTagName=newTagFeild.getText();
			Tag newTag=new Tag();
			if(!newTagName.isEmpty()){
				newTag.setTagName(newTagName);
				databaseHelper.insertTag(newTag);
				newTag.setTagId(databaseHelper.getTagId(newTagName));
				tagListModel.addElement(newTag);
				newTagFeild.setText("");
			}

		}
	}

	class DoneButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			entities.File newFile=null;
			Tag[] addedTags=null;
			File newIOFile=null;

			if(!fileFeild.getText().isEmpty())
			{
				newIOFile=new File(fileFeild.getText());

				if(newIOFile.exists())
				{
					newFile=new entities.File();
					newFile.setFileName(newIOFile.getName());
					newFile.setPath(newIOFile.getPath());
					newFile.setFrequency(0);
					databaseHelper.insertFile(newFile);

					newFile.setFileId(databaseHelper.getLastFileId());
					addedTags= new Tag[addedTagListModel.getSize()];
					addedTagListModel.copyInto(addedTags);
					for(Tag t: addedTags)
					{
						System.out.println(newFile.getFileid()+" "+t.getTagId());
						databaseHelper.insertTagging(newFile.getFileid(), t.getTagId());
						frame.dispose();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Invalid File");
					frame.dispose();
				}

			 }
			else{
				frame.dispose();
			}
		}

	}
}
