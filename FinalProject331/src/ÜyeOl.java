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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ÜyeOl extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSoyAdiniz;
	private JTextField textFieldAdiniz;
	private JTextField textFieldKullaniciAdiniz;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldTekrar;

	private ArrayList<String> kullanýcýBilgileri = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ÜyeOl frame = new ÜyeOl();
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
	public ÜyeOl() {
        try {
        	BufferedReader br = new BufferedReader(new FileReader("KullanýcýBilgileri.txt"));
            try {
                String str;
                while ( (str = br.readLine()) != null ) {
                	 String[] parts = str.split(",");

	                    for(int i = 0; i < parts.length; i++){
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
		setBounds(100, 100, 450, 350);

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

		JLabel lblUyeOl = new JLabel("\u00DCye Ol");
		lblUyeOl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblUyeOl.setBounds(169, 11, 94, 48);
		contentPane.add(lblUyeOl);

		JLabel lblSoyAdiniz = new JLabel("Soy Ad\u0131n\u0131z");
		lblSoyAdiniz.setBounds(109, 121, 66, 14);
		contentPane.add(lblSoyAdiniz);

		JLabel lblAdiniz = new JLabel("Ad\u0131n\u0131z");
		lblAdiniz.setBounds(109, 96, 46, 14);
		contentPane.add(lblAdiniz);

		JLabel lblSifre = new JLabel("\u015Eifre");
		lblSifre.setBounds(109, 171, 46, 14);
		contentPane.add(lblSifre);

		JLabel lblSifreTekrar = new JLabel("\u015Eifre(Tekrar)");
		lblSifreTekrar.setBounds(109, 196, 79, 14);
		contentPane.add(lblSifreTekrar);

		textFieldSoyAdiniz = new JTextField();
		textFieldSoyAdiniz.setBounds(219, 118, 86, 20);
		contentPane.add(textFieldSoyAdiniz);
		textFieldSoyAdiniz.setColumns(10);

		textFieldAdiniz = new JTextField();
		textFieldAdiniz.setBounds(219, 92, 86, 20);
		contentPane.add(textFieldAdiniz);
		textFieldAdiniz.setColumns(10);

		JButton btnUyeOl = new JButton("\u00DCye Ol");
		btnUyeOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				int x = 0;
				
				if (textFieldKullaniciAdiniz.getText().toString().equals("")){
					
				}else{	
					for (int i = 0; i < kullanýcýBilgileri.size() / 4; i++) {
						if (kullanýcýBilgileri.get((i * 4) + 2).equals(textFieldKullaniciAdiniz.getText().toString())) {
							x = 1;
						}

					}
				}
				
				if (textFieldSoyAdiniz.getText().toString().equals("") || textFieldAdiniz.getText().toString().equals("") || textFieldKullaniciAdiniz.getText().toString().equals("")
						|| passwordField.getText().toString().equals("") || passwordFieldTekrar.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Üye olmak için her yeri doldurmanýz gerekiyor.");
				}else if (x == 1) {
					JOptionPane.showMessageDialog(null, "Bu kullanýcý adý zaten kullanýlýyor.");
				} else if (!passwordField.getText().toString().equals(passwordFieldTekrar.getText().toString())) {
					JOptionPane.showMessageDialog(null, "Þifreler eþleþmiyor.");
				} else {
					kullanýcýBilgileri.add(textFieldAdiniz.getText().toString());
					kullanýcýBilgileri.add(textFieldSoyAdiniz.getText().toString());
					kullanýcýBilgileri.add(textFieldKullaniciAdiniz.getText().toString());
					kullanýcýBilgileri.add(passwordField.getText().toString());
					try {
						PrintWriter writer = new PrintWriter("KullanýcýBilgileri.txt", "UTF-8");
						for (int i = 0; i < kullanýcýBilgileri.size() / 4; i++) {
							writer.println(kullanýcýBilgileri.get((i * 4) + 0) + ","
									+ kullanýcýBilgileri.get((i * 4) + 1) + "," + kullanýcýBilgileri.get((i * 4) + 2)
									+ "," + kullanýcýBilgileri.get((i * 4) + 3));

						}

						writer.close();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, e1.toString());
					}

					JOptionPane.showMessageDialog(null,
							"Kayýt iþlemi baþarýyla tamamlandý." + '\n' + "Giriþ sayfasýna yönlendiriliyorsunuz.");
					dispose();
					GiriþSayfasý gs = new GiriþSayfasý();
					gs.setVisible(true);
				}

			}
		});
		btnUyeOl.setBounds(109, 250, 196, 23);
		contentPane.add(btnUyeOl);

		JLabel lblKullaniciAdiniz = new JLabel("Kullan\u0131c\u0131 Ad\u0131n\u0131z");
		lblKullaniciAdiniz.setBounds(109, 146, 89, 14);
		contentPane.add(lblKullaniciAdiniz);

		textFieldKullaniciAdiniz = new JTextField();
		textFieldKullaniciAdiniz.setBounds(219, 143, 86, 20);
		contentPane.add(textFieldKullaniciAdiniz);
		textFieldKullaniciAdiniz.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(219, 168, 86, 20);
		contentPane.add(passwordField);

		passwordFieldTekrar = new JPasswordField();
		passwordFieldTekrar.setBounds(219, 193, 86, 20);
		contentPane.add(passwordFieldTekrar);
	}

}
