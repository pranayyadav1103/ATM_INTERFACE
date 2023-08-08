package ATM_Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class ATM_Interface {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATM_Interface window = new ATM_Interface();
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
	public ATM_Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("CheckBox.light"));
		frame.getContentPane().setForeground(UIManager.getColor("Button.disabledShadow"));
		frame.setBounds(100, 100, 760, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to the ATM Interface");
		lblNewLabel.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 26));
		lblNewLabel.setForeground(UIManager.getColor("Button.disabledForeground"));
		lblNewLabel.setBounds(162, 53, 496, 65);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btn1 = new JButton("WITHDRAW");
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Withdraw withdrawFrame = new Withdraw();
				withdrawFrame.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		btn1.setBounds(118, 170, 160, 43);
		frame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("DEPOSIT");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Deposit dbt=new Deposit();
				dbt.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn2.setBounds(479, 170, 160, 43);
		frame.getContentPane().add(btn2);
		
		JButton btn3 = new JButton("CHECK BALANCE");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Checkbalance chkbal = new Checkbalance();
			((Window) chkbal.getFrame()).setVisible(true);
			frame.setVisible(false);
			}
		});
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn3.setBounds(118, 269, 160, 43);
		frame.getContentPane().add(btn3);
		
		JButton btn = new JButton("MINI STATEMENT");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mini_Statement mni = new Mini_Statement();
				((Window) mni.getFrame()).setVisible(true);
				frame.setVisible(false);
			}
		});
		btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn.setBounds(479, 269, 160, 43);
		frame.getContentPane().add(btn);
	}

	public Object getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}
}
