import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ÜyeSayfasý extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ÜyeSayfasý frame = new ÜyeSayfasý();
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
	public ÜyeSayfasý() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mnýtmGeri = new JMenuItem("Geri");
		mnýtmGeri.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				ÜyeGiriþi us = new ÜyeGiriþi();
				us.setVisible(true);
			}
		});
		menuBar.add(mnýtmGeri);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUyeSayfasi = new JLabel("\u00DCye Sayfas\u0131");
		lblUyeSayfasi.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblUyeSayfasi.setBounds(150, 11, 167, 51);
		contentPane.add(lblUyeSayfasi);
		
		JButton btnBiletAl = new JButton("Bilet Al");
		btnBiletAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BiletAl ba = new BiletAl();
				ba.setVisible(true);
			}
		});
		btnBiletAl.setBounds(128, 105, 184, 23);
		contentPane.add(btnBiletAl);
		
		JButton btnBiletBilgileriniGuncelle = new JButton("Bilet Bilgilerini G\u00FCncelle");
		btnBiletBilgileriniGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BiletGüncelleme bg = new BiletGüncelleme();
				bg.setVisible(true);
			}
		});
		btnBiletBilgileriniGuncelle.setBounds(128, 139, 184, 23);
		contentPane.add(btnBiletBilgileriniGuncelle);
		
		JButton btnBiletIptalEt = new JButton("Bilet \u0130ptal Et");
		btnBiletIptalEt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ÝptalSayfasý is = new ÝptalSayfasý();
				is.setVisible(true);
			}
		});
		btnBiletIptalEt.setBounds(128, 173, 185, 23);
		contentPane.add(btnBiletIptalEt);
	}
}
