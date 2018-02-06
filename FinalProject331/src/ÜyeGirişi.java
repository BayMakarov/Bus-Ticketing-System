import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class ÜyeGiriþi extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAd;
	private JPasswordField passwordField;

	private ArrayList<String> kullanýcýBilgileri = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ÜyeGiriþi frame = new ÜyeGiriþi();
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
	public ÜyeGiriþi() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("KullanýcýBilgileri.txt"));
			try {
				String str;
				while ((str = br.readLine()) != null) {
					String[] parts = str.split(",");

					for (int i = 0; i < parts.length; i++) {
						kullanýcýBilgileri.add(parts[i]);
					}
				}
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e2) {
			System.out.println(e2);
			e2.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 310);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenuItem mnýtmGeri = new JMenuItem("Geri");
		mnýtmGeri.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				GiriþSayfasý gs = new GiriþSayfasý();
				gs.setVisible(true);
			}
		});
		menuBar.add(mnýtmGeri);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUyeGirisi = new JLabel("\u00DCye Giri\u015Fi");
		lblUyeGirisi.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblUyeGirisi.setBounds(154, 11, 149, 44);
		contentPane.add(lblUyeGirisi);

		JLabel lblKullaniciAdiniz = new JLabel("Kullan\u0131c\u0131 Ad\u0131n\u0131z");
		lblKullaniciAdiniz.setBounds(121, 93, 86, 14);
		contentPane.add(lblKullaniciAdiniz);

		JLabel lblSifre = new JLabel("\u015Eifre");
		lblSifre.setBounds(121, 131, 46, 14);
		contentPane.add(lblSifre);

		textFieldAd = new JTextField();
		textFieldAd.setBounds(217, 90, 86, 20);
		contentPane.add(textFieldAd);
		textFieldAd.setColumns(10);

		JButton btnGiris = new JButton("Giri\u015F");
		btnGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = 0;
				for (int i = 0; i < kullanýcýBilgileri.size() / 4; i++) {
					if (kullanýcýBilgileri.get((i * 4) + 2).equals(textFieldAd.getText().toString())
							&& kullanýcýBilgileri.get((i * 4) + 3).equals(passwordField.getText().toString())) {
						JOptionPane.showMessageDialog(null,
								"Bilgileriniz onaylanmýþtýr." + '\n' + "Üye sayfasýna yönlendiriliyorsunuz.");
						dispose();
						ÜyeSayfasý us = new ÜyeSayfasý();
						us.setVisible(true);

						try {
							PrintWriter writer = new PrintWriter("GiriþYapan.txt", "UTF-8");
							writer.println(kullanýcýBilgileri.get((i * 4) + 2));
							writer.close();
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.toString());
						}
						x++;
					}

				}

				if (x == 0) {
					JOptionPane.showMessageDialog(null, "Bilgileriniz yanlýþtýr." + '\n' + "Lütfen tekrar deneyiniz.");
				}

			}
		});
		btnGiris.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGiris.setBounds(142, 198, 161, 31);
		contentPane.add(btnGiris);

		passwordField = new JPasswordField();
		passwordField.setBounds(217, 128, 86, 20);
		contentPane.add(passwordField);
	}
}
