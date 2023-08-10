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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

public class Mini_Statement {

	private JFrame frame;
	private JTable t1;
	private JPasswordField pswd;
	private int count;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mini_Statement window = new Mini_Statement();
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
	public Mini_Statement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.setBounds(100, 100, 829, 469);
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
		btn2.setBounds(349, 386, 95, 21);
		frame.getContentPane().add(btn2);
		
		t1 = new JTable();
		t1.setBounds(211, 110, 350, 249);
		frame.getContentPane().add(t1);
		JScrollPane scrollPane = new JScrollPane(t1);
        scrollPane.setBounds(211, 110, 350, 249); 
        frame.getContentPane().add(scrollPane); 
		
		JLabel lblNewLabel = new JLabel("Enter the pin No :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(83, 30, 153, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btn1 = new JButton("Enter");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pwd=pswd.getText();
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","123456");
					String q="Select count(*) from login where pinno=?";
					PreparedStatement pd=con.prepareStatement(q);
					pd.setString(1, pwd);
					ResultSet rs=pd.executeQuery();
					rs.next();
					count=rs.getInt(1);
					if(count==1) {
						JOptionPane.showMessageDialog(btn1,"Valid PINno");
					}
					else {
						JOptionPane.showMessageDialog(btn1,"Invalid PINno");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(count==1) {
					String[] colname = { "Amount", "Status" };
				    DefaultTableModel model = (DefaultTableModel) t1.getModel();
				    model.setColumnIdentifiers(colname);
					try {
				        Class.forName("com.mysql.cj.jdbc.Driver");
				        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "123456");
				        String q = "select * from trans";
				        Statement sta = con.createStatement();
				        ResultSet rs1 = sta.executeQuery(q);
				        ResultSetMetaData rsmd = rs1.getMetaData();
				        int cols = rsmd.getColumnCount();
				        for (int i = 0; i < cols; i++) { // Corrected the index to start from 0 and go up to cols - 1
				            colname[i] = rsmd.getColumnName(i + 1);
				        }
				        model.setColumnIdentifiers(colname);
				        String s, n;
				        while (rs1.next()) {
				            s = rs1.getString(1);
				            n = rs1.getString(2);
				            String[] row = { s, n };
				            model.addRow(row);
				        }
				        sta.close();
				        con.close();
				    } catch (ClassNotFoundException | SQLException e1) {
				        e1.printStackTrace();
				    }
				}
				}
		});
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn1.setBounds(564, 30, 85, 30);
		frame.getContentPane().add(btn1);
		
		pswd = new JPasswordField();
		pswd.setBounds(281, 37, 204, 21);
		frame.getContentPane().add(pswd);
	}
	public Object getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}
}
