package ATM_Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Signup {

	private JFrame frame;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTextField t5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup window = new Signup();
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
	public Signup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Mongolian Baiti", Font.PLAIN, 10));
		frame.setBounds(100, 100, 679, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Applicant Details");
		lblNewLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 24));
		lblNewLabel.setBounds(193, 44, 245, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(80, 95, 168, 27);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Father Name");
		lblNewLabel_1_1.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(80, 142, 168, 27);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Age");
		lblNewLabel_1_2.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(80, 191, 168, 27);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Gender");
		lblNewLabel_1_3.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(80, 236, 168, 27);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		t1 = new JTextField();
		t1.setBounds(258, 90, 168, 32);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(258, 142, 168, 32);
		frame.getContentPane().add(t2);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(258, 191, 168, 32);
		frame.getContentPane().add(t3);
		
		JRadioButton r1 = new JRadioButton("Male");
		r1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		r1.setBounds(254, 241, 79, 21);
		frame.getContentPane().add(r1);
		
		JRadioButton r2 = new JRadioButton("Female");
		r2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		r2.setBounds(328, 241, 79, 21);
		frame.getContentPane().add(r2);
		
		JRadioButton r3 = new JRadioButton("Other");
		r3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		r3.setBounds(409, 241, 79, 21);
		frame.getContentPane().add(r3);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		bg.add(r3);
		
		JButton btn = new JButton("SUBMIT");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nm =t1.getText();
				String fnm =t2.getText();
				String g =t3.getText();
				int ag=Integer.parseInt(g);
				String gender;
				if(r1.isSelected()){
					gender="Male";
				}
				else if(r2.isSelected()){
					gender="Female";
				}
				else if(r3.isSelected()){
					gender="Other";
				}
				else {
					gender="Invalid";
				}
				String cn=t4.getText();
				int cdno=Integer.parseInt(cn);
				String pn=t5.getText();
				int pno=Integer.parseInt(pn);
				
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","123456");
					String q="Insert into login values('"+nm+"','"+fnm+"','"+g+"','"+gender+"','"+cdno+"','"+pno+"')";
					java.sql.Statement sta=con.createStatement();
					sta.executeUpdate(q);
					con.close();
					JOptionPane.showMessageDialog(btn,"Registration Done!\n"+"Card Number:"+cdno+"\nPin Number:"+pno);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn.setBounds(207, 375, 85, 21);
		frame.getContentPane().add(btn);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Registration no");
		lblNewLabel_1_3_1.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblNewLabel_1_3_1.setBounds(80, 273, 168, 27);
		frame.getContentPane().add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("PIN No");
		lblNewLabel_1_3_1_1.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblNewLabel_1_3_1_1.setBounds(80, 310, 141, 27);
		frame.getContentPane().add(lblNewLabel_1_3_1_1);
		
		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBounds(258, 268, 168, 32);
		frame.getContentPane().add(t4);
		
		t5 = new JTextField();
		t5.setColumns(10);
		t5.setBounds(258, 310, 168, 32);
		frame.getContentPane().add(t5);
	}

	public Window getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}
}
