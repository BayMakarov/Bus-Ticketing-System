import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Giri�Sayfas� extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Giri�Sayfas� frame = new Giri�Sayfas�();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Giri�Sayfas�() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGirisSayfasi = new JLabel("Giri\u015F Sayfas\u0131");
		lblGirisSayfasi.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblGirisSayfasi.setBounds(132, 11, 204, 49);
		contentPane.add(lblGirisSayfasi);
		
		JButton btnUyeOl = new JButton("\u00DCye Ol");
		btnUyeOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				�yeOl uo = new �yeOl();
				uo.setVisible(true);
			}
		});
		btnUyeOl.setBounds(165, 182, 89, 23);
		contentPane.add(btnUyeOl);
		
		JButton btnGirisYap = new JButton("Giri\u015F Yap");
		btnGirisYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				�yeGiri�i ug = new �yeGiri�i();
				ug.setVisible(true);
			}
		});
		btnGirisYap.setBounds(165, 130, 89, 23);
		contentPane.add(btnGirisYap);
	}
}
