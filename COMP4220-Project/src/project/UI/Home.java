package project.UI;

import java.sql.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Home extends JFrame {
	
	private int emp_id;
	private String emp_fname;
	private JLabel appNameLbl;


	public Home(int id, String name) {
		this.emp_id = id;
		this.emp_fname = name;
		
		setBackground(new Color(217, 217, 217));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1000, 500);
        
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
		
		// Main menu
		JPanel mainMenu = new JPanel();
		getContentPane().add(mainMenu, BorderLayout.CENTER);
		GridBagLayout gbl_mainMenu = new GridBagLayout();
		gbl_mainMenu.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainMenu.rowHeights = new int[]{84, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainMenu.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_mainMenu.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainMenu.setLayout(gbl_mainMenu);
		
		// search button
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search s = new Search(emp_id, emp_fname);
				s.setVisible(true);
			}
		});
		GridBagConstraints gbc_searchBtn = new GridBagConstraints();
		gbc_searchBtn.weightx = 0.5;
		gbc_searchBtn.insets = new Insets(0, 0, 5, 5);
		gbc_searchBtn.gridx = 10;
		gbc_searchBtn.gridy = 3;
		mainMenu.add(searchBtn, gbc_searchBtn);
		
		// sell books button
		JButton sellBookBtn = new JButton("Sell a Book");
		sellBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellBooks s = new SellBooks(emp_id, emp_fname);
				s.setVisible(true);
			}
		});
		GridBagConstraints gbc_sellBookBtn = new GridBagConstraints();
		gbc_sellBookBtn.insets = new Insets(0, 0, 5, 5);
		gbc_sellBookBtn.gridx = 10;
		gbc_sellBookBtn.gridy = 4;
		mainMenu.add(sellBookBtn, gbc_sellBookBtn);
		
		// place order button
		JButton placeOrderMenuBtn = new JButton("Place Order");
		placeOrderMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlaceOrder p = new PlaceOrder(emp_id, emp_fname);
				p.setVisible(true);
			}
		});
		GridBagConstraints gbc_placeOrderMenuBtn = new GridBagConstraints();
		gbc_placeOrderMenuBtn.insets = new Insets(0, 0, 5, 5);
		gbc_placeOrderMenuBtn.gridx = 10;
		gbc_placeOrderMenuBtn.gridy = 5;
		mainMenu.add(placeOrderMenuBtn, gbc_placeOrderMenuBtn);
		
		// reserve book button
		JButton reserveBookBtn = new JButton("Reserve a Book");
		reserveBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReserveBook r = new ReserveBook(emp_id, emp_fname);
				r.setVisible(true);
			}
		});
		GridBagConstraints gbc_reserveBookBtn = new GridBagConstraints();
		gbc_reserveBookBtn.insets = new Insets(0, 0, 5, 5);
		gbc_reserveBookBtn.gridx = 10;
		gbc_reserveBookBtn.gridy = 6;
		mainMenu.add(reserveBookBtn, gbc_reserveBookBtn);
		
		// Update Inventory button
		JButton updateInvBookBtn = new JButton("Update Inventory");
		updateInvBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateInventory u = new UpdateInventory(emp_id, emp_fname);
				u.setVisible(true);
			}
		});
		GridBagConstraints gbc_updateInvBookBtn = new GridBagConstraints();
		gbc_updateInvBookBtn.insets = new Insets(0, 0, 5, 5);
		gbc_updateInvBookBtn.gridx = 10;
		gbc_updateInvBookBtn.gridy = 7;
		mainMenu.add(updateInvBookBtn, gbc_updateInvBookBtn);
		
		// employee management button
		JButton empMngmentBtn = new JButton("Employee Management");
		empMngmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeManage em = new EmployeeManage(emp_id, emp_fname);
				em.setVisible(true);
			}
		});
		GridBagConstraints gbc_empMngmentBtn = new GridBagConstraints();
		gbc_empMngmentBtn.insets = new Insets(0, 0, 5, 5);
		gbc_empMngmentBtn.gridx = 10;
		gbc_empMngmentBtn.gridy = 9;
		mainMenu.add(empMngmentBtn, gbc_empMngmentBtn);
		
	
		
	}

}