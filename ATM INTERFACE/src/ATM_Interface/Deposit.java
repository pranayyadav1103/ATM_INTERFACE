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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Deposit {

	private JFrame frame;
	private JTextField t1;
	private JPasswordField pwd;
	private int count;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deposit window = new Deposit();
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
	public Deposit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 722, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btn = new JButton("Enter");
		btn.addActionListener(new ActionListener() {
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
						JOptionPane.showMessageDialog(btn,"Valid PINno");
					}
					else {
						JOptionPane.showMessageDialog(btn,"Invalid PINno");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btn3 = new JButton("Exit");
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ATM_Interface atm=new ATM_Interface();
				((Window) atm.getFrame()).setVisible(true);
				frame.setVisible(false);
				
			}
		});
		btn3.setBounds(285, 272, 109, 31);
		frame.getContentPane().add(btn3);
		
		JButton btn2 = new JButton("Deposit");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(count==1) {
					String mt=t1.getText();
					int am=Integer.parseInt(mt);
					String tran="Deposit";
					
					try {
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","123456");
						String q="Insert into trans values('"+am+"','"+tran+"')";
						java.sql.Statement sta=con.createStatement();
						sta.executeUpdate(q);
						con.close();
						JOptionPane.showMessageDialog(btn2,"Money has been Deposited");
						count=0;
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(btn2,"Enter Valid PinNo");
				}
			}
		});
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn2.setBounds(574, 175, 109, 31);
		frame.getContentPane().add(btn2);
		
		JLabel lblNewLabel = new JLabel("Enter the amount you want to deposit");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(37, 168, 318, 43);
		frame.getContentPane().add(lblNewLabel);
		
		t1 = new JTextField();
		t1.setBounds(365, 177, 180, 31);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the PIN No:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(54, 30, 180, 31);
		frame.getContentPane().add(lblNewLabel_1);
		

		btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn.setBounds(445, 31, 96, 31);
		frame.getContentPane().add(btn);
		
		pwd = new JPasswordField();
		pwd.setBounds(240, 39, 154, 19);
		frame.getContentPane().add(pwd);
	}

	public Window getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}

}
