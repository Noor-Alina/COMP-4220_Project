package project.UI;

import java.sql.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Login extends JFrame {

	private JLabel appNameLbl;
	private int emp_id;
	private String emp_fname;
	private JTextField usrnameTxtfield;
	private JPasswordField passwordField;


	public Login() {
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
		
		
		
		// Login Panel
		JPanel login = new JPanel();
		getContentPane().add(login, BorderLayout.CENTER);
		GridBagLayout gbl_login = new GridBagLayout();
		gbl_login.columnWidths = new int[]{225, 130, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_login.rowHeights = new int[]{84, 0, 0, 0, 0, 0, 0};
		gbl_login.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_login.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		login.setLayout(gbl_login);
		
		// Username Label
		JLabel usrnameLbl = new JLabel("Username");
		GridBagConstraints gbc_usrnameLbl = new GridBagConstraints();
		gbc_usrnameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_usrnameLbl.gridx = 1;
		gbc_usrnameLbl.gridy = 2;
		login.add(usrnameLbl, gbc_usrnameLbl);
	
		// Username Textfield
		usrnameTxtfield = new JTextField();
		GridBagConstraints gbc_usrnameTxtfield = new GridBagConstraints();
		gbc_usrnameTxtfield.insets = new Insets(0, 0, 5, 5);
		gbc_usrnameTxtfield.gridx = 3;
		gbc_usrnameTxtfield.gridy = 2;
		usrnameTxtfield.setColumns(10);
		login.add(usrnameTxtfield, gbc_usrnameTxtfield);
		
		// Password Label
		JLabel passwordLbl = new JLabel("Password");
		GridBagConstraints gbc_passwordLbl= new GridBagConstraints();
		gbc_passwordLbl.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLbl.gridx = 1;
		gbc_passwordLbl.gridy = 3;
		login.add(passwordLbl, gbc_passwordLbl);	
		
		// Password Textfield
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 3;
		passwordField.setColumns(10);
		login.add(passwordField, gbc_passwordField);
		
		// Login Button
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement","guest", "guest123");
					Statement stmt= Con.createStatement();
					String sql = "SELECT * FROM EmpLoginInfo WHERE username='"+usrnameTxtfield.getText()+"' AND password='"+passwordField.getText()+"'";
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()) {
						
						emp_fname = usrnameTxtfield.getText();
						emp_id = Integer.parseInt(rs.getString(1));
						
						JOptionPane.showMessageDialog(null,"Logged in Sucessfully");
						Home h = new Home(emp_id, emp_fname);
						h.setVisible(true);
					    setVisible(false);
					    }
					else {
						JOptionPane.showMessageDialog(null,"Incorrect username or password");}
					Con.close();
				}catch(Exception e1) {System.out.println(e1);}
			}
		});
		GridBagConstraints gbc_loginBtn = new GridBagConstraints();
		gbc_loginBtn.insets = new Insets(0, 0, 5, 5);
		gbc_loginBtn.gridx = 2;
		gbc_loginBtn.gridy = 4;		
		login.add(loginBtn, gbc_loginBtn);

		// Reset Button
		JButton resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usrnameTxtfield.setText(null);
				passwordField.setText(null);
			}
		});
		GridBagConstraints gbc_resetBtn = new GridBagConstraints();
		gbc_resetBtn.insets = new Insets(0, 0, 0, 5);
		gbc_resetBtn.gridx = 2;
		gbc_resetBtn.gridy = 5;
		login.add(resetBtn, gbc_resetBtn);
		
	}
	
	public int getEmpID() {
		return emp_id;
	}
	
	public String getEmpFname() {
		return emp_fname;
	}
	
}