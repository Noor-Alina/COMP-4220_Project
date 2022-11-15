package project.UI;

import java.sql.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import project.EmployeeManagement;



public class EmployeeManage extends JFrame {

	private JLabel appNameLbl;
	private int emp_id;
	private String emp_fname;
	private JTextField empIdtextField;
	private JTextField dateTxtField;
	private JTextField startHourTxtField;
	private JTextField endHourTxtField;
	private JTextField empIdTxtFieldView;
	private JTextField dateLblViewTxtField;


	public EmployeeManage(int id, String name) {
		this.emp_id = id;
		this.emp_fname = name;
		
		setBackground(new Color(217, 217, 217));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 800, 500);
		
		JPanel header = new JPanel();
		header.setFont(new Font("Tahoma", Font.PLAIN, 13));
		header.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(header, BorderLayout.NORTH);
		header.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		header.setLayout(new BorderLayout(5, 5));
		
		// App Name Label
		appNameLbl = new JLabel("App Name");
		appNameLbl.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		appNameLbl.setAlignmentX(Component.RIGHT_ALIGNMENT);
		appNameLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		appNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		appNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		header.add(appNameLbl, BorderLayout.WEST);
		
		// Horizontal Line
		JSeparator headerSeparator = new JSeparator();
		headerSeparator.setForeground(new Color(0, 0, 0));
		header.add(headerSeparator, BorderLayout.SOUTH);
		

		JSplitPane splitPane = new JSplitPane();
		splitPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		splitPane.setDividerSize(1);
		splitPane.setBorder(null);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		header.add(splitPane, BorderLayout.EAST);
		
		// Employee Name
		JLabel empNameLbl = new JLabel(emp_fname);
		empNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		empNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane.setLeftComponent(empNameLbl);
		
		// Logout Button
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setBackground(new Color(255, 255, 255));
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        Login tt = new Login();
				tt.setVisible(true);
				dispose();
			}
		});
		logoutBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		logoutBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		splitPane.setRightComponent(logoutBtn);
		
		// body panel
		JPanel body = new JPanel();
		getContentPane().add(body, BorderLayout.CENTER);
		body.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// add employee hours section
		Box addEmpHours = Box.createVerticalBox();
		body.add(addEmpHours);
		
		Component verticalGlue = Box.createVerticalGlue();
		addEmpHours.add(verticalGlue);
		
		// emp id
		JLabel empIdLbl = new JLabel("Employee ID");
		addEmpHours.add(empIdLbl);
		
		empIdtextField = new JTextField();
		addEmpHours.add(empIdtextField);
		empIdtextField.setColumns(10);
		
		// date
		JLabel dateLbl = new JLabel("Date");
		addEmpHours.add(dateLbl);
		
		dateTxtField = new JTextField();
		addEmpHours.add(dateTxtField);
		dateTxtField.setColumns(10);
		
		// start hour
		JLabel startHourLbl = new JLabel("Start Hour");
		addEmpHours.add(startHourLbl);
		
		startHourTxtField = new JTextField();
		addEmpHours.add(startHourTxtField);
		startHourTxtField.setColumns(10);
		
		// end hour
		JLabel endHourLbl = new JLabel("End Hour");
		addEmpHours.add(endHourLbl);
		
		endHourTxtField = new JTextField();
		addEmpHours.add(endHourTxtField);
		endHourTxtField.setColumns(10);
		
		// add hours btn
		JButton addHoursBtn = new JButton("Add Hours");
		addHoursBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EmployeeManagement em = new EmployeeManagement();
					String result = em.setHours(Integer.parseInt(empIdtextField.getText()), dateTxtField.getText(), startHourTxtField.getText(), endHourTxtField.getText());			
					JOptionPane.showMessageDialog(null, result);
					setVisible(false);
				}catch(Exception e1) {System.out.println(e1);}
			}
		});
		addEmpHours.add(addHoursBtn);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		body.add(horizontalGlue);
		
		
		// view employee hours section
		Box viewEmpHours = Box.createVerticalBox();
		body.add(viewEmpHours);
		
		//  emp id
		JLabel empIdLblView = new JLabel("Employee ID");
		viewEmpHours.add(empIdLblView);
		
		empIdTxtFieldView = new JTextField();
		empIdTxtFieldView.setColumns(10);
		viewEmpHours.add(empIdTxtFieldView);
		
		// date
		JLabel dateLblView = new JLabel("Date");
		viewEmpHours.add(dateLblView);
		
		dateLblViewTxtField = new JTextField();
		dateLblViewTxtField.setColumns(10);
		viewEmpHours.add(dateLblViewTxtField);
		
		// view hours button
		JButton btnNewButton = new JButton("View Hours");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EmployeeManagement em = new EmployeeManagement();
//					String result = em.viewHours(Integer.parseInt(empIdtextField.getText()), dateTxtField.getText());
					ArrayList<String> result = em.viewHours();
					JOptionPane.showMessageDialog(null, result.get(0));
					setVisible(false);
				}catch(Exception e1) {System.out.println(e1);}
			}
		});
		viewEmpHours.add(btnNewButton);
		
	}
	
}