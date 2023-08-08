package ATM_Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Checkbalance {

	private JFrame frame;
	private JPasswordField pwd;
	private int count;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Checkbalance window = new Checkbalance();
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
	public Checkbalance() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 747, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btn2 = new JButton("Exit");
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ATM_Interface atm=new ATM_Interface();
				((Window) atm.getFrame()).setVisible(true);
				frame.setVisible(false);
				
			}
		});
		btn2.setBounds(293, 267, 117, 25);
		frame.getContentPane().add(btn2);
		
		JLabel lblNewLabel_1 = new JLabel("Enter your PIN NO:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(89, 60, 229, 37);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btn1 = new JButton("Enter");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pswd=pwd.getText();
				
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","123456");
					String q="Select count(*) from login where pinno=?";
					PreparedStatement pd=con.prepareStatement(q);
					pd.setString(1, pswd);
					ResultSet rs=pd.executeQuery();
					rs.next();
					
					count=rs.getInt(1);
					if(count==1) {
						String q1 = "SELECT SUM(CASE WHEN Status= 'deposit' THEN amount ELSE 0 END) - SUM(CASE WHEN Status = 'withdraw' THEN amount ELSE 0 END) AS balance FROM trans;";
                        PreparedStatement pds = con.prepareStatement(q1);
                        ResultSet res = pds.executeQuery();
                        res.next();
                        int balance = res.getInt("balance");
                        JOptionPane.showMessageDialog(btn1, "Valid PINno\nYour balance is: " + balance);
                        count=0;
					}
					else {
						JOptionPane.showMessageDialog(btn1,"Invalid PINno");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn1.setBounds(515, 67, 105, 25);
		frame.getContentPane().add(btn1);
		
		pwd = new JPasswordField();
		pwd.setBounds(293, 72, 169, 19);
		frame.getContentPane().add(pwd);
	}

	public Object getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}

}
