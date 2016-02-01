package com.leo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class AboutUs {

	public static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutUs window = new AboutUs();
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
	public AboutUs() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5173\u4E8E\u8F6F\u4EF6");
		frame.setBounds(100, 100, 288, 177);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 247, 122);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8F6F\u4EF6\u540D\u79F0\uFF1A");
		lblNewLabel.setBounds(30, 10, 67, 15);
		panel.add(lblNewLabel);
		
		JLabel lblv = new JLabel("\u8BD5\u9898\u968F\u673A\u751F\u6210\u7A0B\u5E8FV1.0");
		lblv.setBounds(93, 10, 154, 15);
		panel.add(lblv);
		
		JLabel label = new JLabel("\u5F00\u53D1\u8005\uFF1A");
		label.setBounds(41, 36, 56, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u674E\u8000\u534E");
		label_1.setBounds(93, 36, 154, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u8054\u7CFB\u90AE\u7BB1\uFF1A");
		label_2.setBounds(30, 61, 67, 15);
		panel.add(label_2);
		
		JLabel lblLeocom = new JLabel("397346332@qq.com");
		lblLeocom.setBounds(93, 61, 154, 15);
		panel.add(lblLeocom);
		
		JLabel label_3 = new JLabel("\u624B\u673A\uFF1A");
		label_3.setBounds(52, 86, 56, 15);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("18041115846");
		label_4.setBounds(93, 86, 154, 15);
		panel.add(label_4);
	}
}
