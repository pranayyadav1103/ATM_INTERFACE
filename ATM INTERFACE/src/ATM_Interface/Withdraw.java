package ATM_Interface;

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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Withdraw {

	private JFrame frame;
	private JTextField t1;
	private JPasswordField pwd;
	private int count;
	private int balance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Withdraw window = new Withdraw();
		window.getFrame().setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Withdraw() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.setBounds(100, 100, 780, 356);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btn3 = new JButton("Exit");
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ATM_Interface atm=new ATM_Interface();
				((Window) atm.getFrame()).setVisible(true);
				frame.setVisible(false);
				
			}
		});
		btn3.setBounds(340, 254, 116, 30);
		frame.getContentPane().add(btn3);
		
		JLabel lblNewLabel = new JLabel("Enter the amount you want to withdraw");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(45, 162, 340, 24);
		frame.getContentPane().add(lblNewLabel);
		
		t1 = new JTextField();
		t1.setBounds(418, 162, 174, 24);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		JButton btn2 = new JButton("withdraw");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mt=t1.getText();
				int am=Integer.parseInt(mt);
				
				if(count==1 && am<=balance) {
					
					String tran="Withdraw";
					
					try {
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","123456");
						String q="Insert into trans values('"+am+"','"+tran+"')";
						java.sql.Statement sta=con.createStatement();
						sta.executeUpdate(q);
						con.close();
						JOptionPane.showMessageDialog(btn2,am+"Collect your cash");
						count=0;
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(am>balance){
					JOptionPane.showMessageDialog(btn2,"Insufficient funds");
				}
				else {
					JOptionPane.showMessageDialog(btn2,"Enter Valid PinNo");
				}
			}
		});
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn2.setBounds(629, 160, 111, 30);
		frame.getContentPane().add(btn2);
		
		JLabel lblNewLabel_1 = new JLabel("Enter pin no:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(45, 24, 167, 24);
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
                        balance = res.getInt("balance");
                        JOptionPane.showMessageDialog(btn1, "Valid PINno\nYour balance is: " + balance);
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
		btn1.setBounds(418, 27, 85, 21);
		frame.getContentPane().add(btn1);
		
		pwd = new JPasswordField();
		pwd.setBounds(219, 24, 166, 25);
		frame.getContentPane().add(pwd);
	}

	public JFrame getFrame() {
		return frame;
	}
}
