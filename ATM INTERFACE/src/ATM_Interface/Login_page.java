package ATM_Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login_page {

	private JFrame frame;
	private JTextField t1;
	private JPasswordField pswd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_page window = new Login_page();
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
	public Login_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.setBounds(100, 100, 905, 461);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to ATM");
		lblNewLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 24));
		lblNewLabel.setBounds(262, 49, 322, 69);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Card no");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(199, 128, 125, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Pin no");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(199, 180, 125, 30);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		t1 = new JTextField();
		t1.setBounds(325, 124, 185, 34);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		JButton btn = new JButton("Sign In");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n1=t1.getText();
				int nm=Integer.parseInt(n1);
				String p=pswd.getText();
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","123456");
					String q="Select count(*) from login where cardno=? and pinno=?";
					PreparedStatement pd=con.prepareStatement(q);
					pd.setInt(1, nm);
					pd.setString(2, p);
					ResultSet rs=pd.executeQuery();
					rs.next();
					int count=rs.getInt(1);
					if(count==1) {
						JOptionPane.showMessageDialog(btn,"Valid Credentials");
						ATM_Interface atm=new ATM_Interface();
						((Window) atm.getFrame()).setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(btn,"Invalid Credentials");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn.setBounds(239, 267, 101, 30);
		frame.getContentPane().add(btn);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signup atm=new Signup();
				((Window) atm.getFrame()).setVisible(true);
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSignUp.setBounds(409, 267, 101, 30);
		frame.getContentPane().add(btnSignUp);
		
		pswd = new JPasswordField();
		pswd.setBounds(325, 185, 185, 28);
		frame.getContentPane().add(pswd);
	}
}
