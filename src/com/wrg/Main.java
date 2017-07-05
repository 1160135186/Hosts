package com.wrg;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.w3c.dom.ls.LSInput;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;

public class Main extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Main() throws Exception {
		ArrayList<String> list = new ArrayList<>();
		list = ServerUtils.readHostsProviderName();
		String initVersion = ServerUtils.getLatestVersionNumber(list.get(0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("下载Hosts文件");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hostsProviderName = (String) comboBox.getSelectedItem();
				try {
					ServerUtils.getHosts(hostsProviderName);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(239, 175, 172, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("恢复默认文件");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string = "";
				FileWriter fileWriter;
				try {
					fileWriter = new FileWriter(new File("C:\\Windows\\System32\\drivers\\etc\\hosts"));
					fileWriter.write(string);
					fileWriter.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
			}
		});
		btnNewButton_1.setBounds(239, 284, 172, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("应用Hosts");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String  line = null;
			    String content = "";
				File file = new File("E:\\hosts\\hosts.txt");
				FileReader fileReader = null;
				try {
					fileReader = new FileReader(file);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				try {
					while ( (line = bufferedReader.readLine()) != null ) {
						content += line + "\n";
					}
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				try {
					FileWriter fileWriter = new FileWriter(new File("C:\\Windows\\System32\\drivers\\etc\\hosts"));
					fileWriter.write(content);
					fileWriter.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(239, 229, 172, 23);
		contentPane.add(btnNewButton_2);
		
		comboBox = new JComboBox();
		comboBox.setBounds(239, 42, 172, 21);
		contentPane.add(comboBox);
		for ( String s : list ) {
			comboBox.addItem(s);
		}
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String s = (String) comboBox.getSelectedItem();	
				String version = null;
				try {
					version = ServerUtils.getLatestVersionNumber(s);
					lblNewLabel_1.setText("最新版本                " + version);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(version);
			}
		});
		
		lblNewLabel_1 = new JLabel("最新版本                " + initVersion);
		lblNewLabel_1.setBounds(239, 109, 172, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_3 = new JButton("测试一下");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				URI uri = URI.create("www.google.com");
				java.awt.Desktop dp = java.awt.Desktop.getDesktop() ;   
                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {  
                    // 获取系统默认浏览器打开链接   
                    try {
						dp.browse( uri ) ;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
                }  
			}
		});
		btnNewButton_3.setBounds(239, 346, 172, 23);
		contentPane.add(btnNewButton_3);
	}
}
