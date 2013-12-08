/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.dialogs;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;
import com.santiagolizardo.madcommander.util.io.FileUtil;


public class UnpackDialog extends AbstractDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5451158938079680268L;
	private JButton ok;
	private JButton browse;
    private JLabel fileText;
    private JTextField unpackOn;
    private DefaultListModel<String> model;
    private JList<String> list;
    private JScrollPane scroll;
    
    private String fileName;
    
	public UnpackDialog(String fileName) {
		super();

		
        File file = new File(fileName);
        File dir = new File(FileUtil.extractDirPart(file));

		this.fileName = fileName;
		
		setTitle(Translator._("Unpack..."));

        ok = new JButton("Ok");
        ok.requestFocus();
        ok.addActionListener(this);
        
        final JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setCurrentDirectory(dir);
		        
        fileText = new JLabel(fileName);
        unpackOn = new JTextField(20);
        unpackOn.setText(dir.getAbsolutePath());
        
        browse = new JButton("...");
        browse.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		int ret = chooser.showDialog((JComponent)event.getSource(), "Select directory...");
        		if(ret == JFileChooser.APPROVE_OPTION) {
        			File file = chooser.getSelectedFile();
        			unpackOn.setText(file.getAbsolutePath());
        		}
        	}
        });
        
        model = new DefaultListModel<String>();
        list = new JList<String>(model);
        scroll = new JScrollPane(list);
        Dimension scrollSize = new Dimension(350, 70);
        scroll.setMinimumSize(scrollSize);
        scroll.setPreferredSize(scrollSize);
        
        try {
        	ZipFile zipFile = new ZipFile(fileName);
        	Enumeration<?> e = zipFile.entries();
        	while(e.hasMoreElements()) {
        		ZipEntry entry = (ZipEntry)e.nextElement();
        		model.addElement(entry.getName());
        	}
        	zipFile.close();
        } catch (IOException io) {
        	io.printStackTrace();
        }
        
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        defineLayout();
		setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent event) {
		File file = new File(unpackOn.getText());
		if(!file.isDirectory()) {
			DialogFactory.showErrorMessage(this, "You must select a correct directory first.");
			return;
		}
        try {
        	ZipInputStream in = new ZipInputStream(new FileInputStream(fileName));
        	ZipEntry entry = in.getNextEntry();
        	while(entry != null) {
        		String newFileName = unpackOn.getText()+File.separator+entry.getName();
        		if(entry.isDirectory()) {
        			File dir = new File(newFileName);
        			dir.mkdirs();
        		} else {
	        		OutputStream os = new FileOutputStream(newFileName);
	        		byte[] buf = new byte[1024];
	        		int len = 0;
	        		while((len = in.read(buf)) > 0) {
	        			os.write(buf, 0, len);
	        		}
	        		os.close();
        		}
        		entry = in.getNextEntry();
        	}
        	in.close();
        } catch (IOException io) {
        	io.printStackTrace();
        }
		
		dispose();
	}
    
    private void defineLayout() {
        Container contentPane = getContentPane();
        SpringLayout layout = new SpringLayout();
        
        layout.putConstraint(SpringLayout.WEST, fileText, 5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, fileText, 5, SpringLayout.NORTH, contentPane);
        
        layout.putConstraint(SpringLayout.NORTH, ok, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, ok, 0, SpringLayout.EAST, scroll);

        layout.putConstraint(SpringLayout.WEST, unpackOn, 5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, unpackOn, 5, SpringLayout.SOUTH, fileText);

        layout.putConstraint(SpringLayout.WEST, browse, 5, SpringLayout.EAST, unpackOn);
        layout.putConstraint(SpringLayout.NORTH, browse, 0, SpringLayout.NORTH, unpackOn);
        
        layout.putConstraint(SpringLayout.WEST, scroll, 5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, scroll, 5, SpringLayout.SOUTH, unpackOn);
        layout.putConstraint(SpringLayout.EAST, contentPane, 5, SpringLayout.EAST, scroll);
        layout.putConstraint(SpringLayout.SOUTH, contentPane, 5, SpringLayout.SOUTH, scroll);
        
        setLayout(layout);
        
        add(ok);
        add(fileText);
        add(unpackOn);
        add(browse);
        add(scroll);
        
        pack();
    }
}
