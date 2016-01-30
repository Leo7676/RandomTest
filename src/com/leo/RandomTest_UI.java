package com.leo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class RandomTest_UI {

	private JFrame frame;
	public static JTextField textField_export_path;
	public static  JTextField textField_import_path;
	private JTextField textField_hm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RandomTest_UI window = new RandomTest_UI();
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
	public RandomTest_UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u968F\u673A\u8BD5\u9898\u751F\u6210\u7A0B\u5E8F");
		frame.setBounds(100, 100, 577, 316);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 561, 257);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u8BD5\u9898\u751F\u6210\u8DEF\u5F84\uFF1A");
		label.setBounds(51, 74, 86, 29);
		panel.add(label);
		
		textField_export_path = new JTextField();
		textField_export_path.setEditable(false);
		textField_export_path.setBounds(137, 78, 242, 21);
		textField_export_path.setColumns(10);
		panel.add(textField_export_path);

		
		
		JButton button_browser_export = new JButton("\u6D4F\u89C8");
		button_browser_export.setBounds(393, 77, 63, 23);
		panel.add(button_browser_export);
		button_browser_export.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jChooser = null;


			    jChooser = new JFileChooser();
//			    new File("D://Export")
		
			    jChooser.setCurrentDirectory(new File("D://"));
			    jChooser.setDialogTitle("请选择随机试题生成到那个目录下");
			    // 设置打开文件类型,此处设置成只能选择文件夹，不能选择文件
			    jChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// 只能打开文件夹

			    int index = jChooser.showDialog(null, "确定");
			    if (index == JFileChooser.APPROVE_OPTION) {
			     // 把获取到的文件的绝对路径显示在文本编辑框中
			    	textField_export_path.setText(jChooser.getSelectedFile()
			       .getAbsolutePath());

			    }
				
			}
			
		});
		
		JLabel label_1 = new JLabel("\u9898\u5E93\u6587\u4EF6\uFF1A");
		label_1.setBounds(78, 25, 69, 29);
		panel.add(label_1);
		
		textField_import_path = new JTextField();
		textField_import_path.setEditable(false);
		textField_import_path.setColumns(10);
		textField_import_path.setBounds(137, 29, 242, 21);
		panel.add(textField_import_path);
		
		JButton button_browser_import = new JButton("\u6D4F\u89C8");
		button_browser_import.setBounds(393, 28, 63, 23);
		panel.add(button_browser_import);
		button_browser_import.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jChooser = null;


			    jChooser = new JFileChooser();
//			    new File("D://Export")
		
			    jChooser.setCurrentDirectory(new File("D://"));
			    jChooser.setDialogTitle("请选择Excel题库");
			    // 设置打开文件类型,此处设置成只能选择文件夹，不能选择文件
			    jChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);// 只能打开文件夹

			    int index = jChooser.showDialog(null, "确定");
			    if (index == JFileChooser.APPROVE_OPTION) {
			     // 把获取到的文件的绝对路径显示在文本编辑框中
			    	textField_import_path.setText(jChooser.getSelectedFile()
			       .getAbsolutePath());

			    }
				
			}
			
		});
		
		textField_hm = new JTextField();
		textField_hm.setBounds(141, 117, 66, 21);
		textField_hm.setText("30");
		panel.add(textField_hm);
		textField_hm.setColumns(10);
		
		JLabel label_2 = new JLabel("\u751F\u6210\u4EFD\u6570\uFF1A");
		label_2.setBounds(78, 113, 69, 29);
		panel.add(label_2);
		
		JButton button_start = new JButton("\u751F\u6210\u8BD5\u5377");
		button_start.setFont(new Font("宋体", Font.PLAIN, 18));
		button_start.setBounds(216, 192, 125, 42);
		panel.add(button_start);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u6587\u4EF6");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u9000\u51FA");
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(1);
			}
			
		});
		
		JMenu menu_1 = new JMenu("\u5E2E\u52A9");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("\u5173\u4E8E\u8F6F\u4EF6");
		menu_1.add(menuItem_1);
		menuItem_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				AboutUs us=new AboutUs();
				
				us.frame.setVisible(true);
			}
			
		});
		button_start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if("".equals(textField_import_path.getText().trim())){
					JOptionPane.showMessageDialog(frame.getContentPane(),
							"请选择题库文件!!", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				}else if("".equals(textField_export_path.getText().trim())){
					JOptionPane.showMessageDialog(frame.getContentPane(),
							"请选择随机试卷生成的文件夹!!", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				}else{
					for(int i=1;i<Integer.parseInt(textField_hm.getText().trim())+1;i++){
						try {
							XwpfTest.SimpleWrite("第 "+i+" 随机试题");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(frame.getContentPane(),
									"程序执行过程中遇到严重的错误，请联系软件制作者，电话： 180 4111 5846 !", "系统信息", JOptionPane.ERROR_MESSAGE);
						}
					}
					JOptionPane.showMessageDialog(frame.getContentPane(),
							"试题生成成功，请到 "+textField_export_path.getText()+" 目录下查看！！感谢使用^_^", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
				
			}
			
		});
	}
}
