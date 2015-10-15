package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import entities.Tag;

public class PopulatorGUI {
	private JFrame frame=null;
	private JTextField fileFeild=null;
	private File choosenFile=null;
	
	
	public void initialise(){
		frame=new JFrame("Track My Downloads");
		Container contentPane=frame.getContentPane();
		
		JLabel fileLabel=new JLabel("File Name:");
		//JLabel pathLabel=new JLabel("File Path:");
		//nameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		//pathLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		fileFeild= new JTextField(30);
		//JTextField pathFeild= new JTextField(30);
		
		JButton browseButton= new JButton("Browse");
		browseButton.addActionListener(new browseButtonListener());
				
		JLabel newTagLabel= new JLabel("New Tag Name:");
		JTextField newTagFeild= new JTextField(20);
		JButton addTagButton= new JButton("Add");
		
		JButton doneButton= new JButton("Done");
		
		String [] list={"abcd","abcd","asert","juit","list","korean","indian"};
		
		JList<String> tagList= new JList<String>(list);
		tagList.setVisibleRowCount(6);
		tagList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane tagListScroller= new JScrollPane(tagList);
		tagListScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tagListScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JList<String> addedTagList= new JList<String>(list);
		addedTagList.setVisibleRowCount(6);
		addedTagList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane addedTagScroller= new JScrollPane(addedTagList);
		addedTagScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		addedTagScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel tagsLabel=new JLabel("Tags:");
		JLabel addedTagsLabel=new JLabel("Added Tags:");

		JButton addIconButton= new JButton();
		JButton removeIconButton= new JButton();
		try{
			Image add= ImageIO.read(getClass().getResource("/icons/add_icon.png"));
			addIconButton.setIcon(new ImageIcon(add));
			
			Image rem=ImageIO.read(getClass().getResource("/icons/rem_icon.png"));
			removeIconButton.setIcon(new ImageIcon(rem));
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		
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
		
		JPanel southTopPanel= new JPanel();
		southTopPanel.setLayout(new BoxLayout(southTopPanel, BoxLayout.X_AXIS));
		southTopPanel.add(newTagLabel);
		southTopPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		southTopPanel.add(newTagFeild);
		southTopPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		southTopPanel.add(addTagButton);
		
		JPanel southBottomPanel= new JPanel();
		southBottomPanel.setLayout(new BoxLayout(southBottomPanel, BoxLayout.X_AXIS));
		southBottomPanel.add(Box.createHorizontalGlue());
		southBottomPanel.add(doneButton);
				
		JPanel southPanel= new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
		southPanel.add(southTopPanel);
		southPanel.add(southBottomPanel);
		
		JPanel centreLeftPanel= new JPanel();
		centreLeftPanel.setLayout(new BoxLayout(centreLeftPanel, BoxLayout.Y_AXIS));
		centreLeftPanel.add(tagsLabel);
		centreLeftPanel.add(Box.createRigidArea(new Dimension(0,5)));
		centreLeftPanel.add(tagListScroller);
		
		JPanel centreMiddlePanel= new JPanel();
		centreMiddlePanel.setLayout(new BoxLayout(centreMiddlePanel, BoxLayout.Y_AXIS));
		centreMiddlePanel.add(addIconButton);
		centreMiddlePanel.add(Box.createRigidArea(new Dimension(0,5)));
		centreMiddlePanel.add(removeIconButton);
		
		JPanel centreRightPanel= new JPanel();
		centreRightPanel.setLayout(new BoxLayout(centreRightPanel, BoxLayout.Y_AXIS));
		centreRightPanel.add(addedTagsLabel);
		centreRightPanel.add(Box.createRigidArea(new Dimension(0,5)));
		centreRightPanel.add(addedTagScroller);
				
		JPanel centrePanel= new JPanel();
		centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.X_AXIS));
		centrePanel.add(centreLeftPanel);
		centrePanel.add(Box.createRigidArea(new Dimension(5, 0)));
		centrePanel.add(centreMiddlePanel);
		centrePanel.add(Box.createRigidArea(new Dimension(5, 0)));
		centrePanel.add(centreRightPanel);
				
		contentPane.add(northPanel, BorderLayout.NORTH);
		contentPane.add(southPanel, BorderLayout.SOUTH);
		contentPane.add(centrePanel, BorderLayout.CENTER);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	class browseButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			JFileChooser fileChooser= new JFileChooser();
			fileChooser.showDialog(frame, "Choose");
			choosenFile= fileChooser.getSelectedFile();
			fileFeild.setText(choosenFile.getAbsolutePath());
		}
	}
	
}
