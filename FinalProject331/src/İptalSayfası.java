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

public class ÝptalSayfasý extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBiletNo;
	
	private String musteri;
	private ArrayList<String> biletBilgileri = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ÝptalSayfasý frame = new ÝptalSayfasý();
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
	public ÝptalSayfasý() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("BiletBilgileri.txt"));
			try {
				String str;
				while ((str = br.readLine()) != null) {
					String[] parts = str.split(",");

					for (int i = 0; i < parts.length; i++) {
						biletBilgileri.add(parts[i]);
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
		setBounds(100, 100, 450, 262);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mnýtmGeri = new JMenuItem("Geri");
		mnýtmGeri.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				ÜyeSayfasý us = new ÜyeSayfasý();
				us.setVisible(true);
			}
		});
		menuBar.add(mnýtmGeri);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBiletIptali = new JLabel("Bilet \u0130ptali");
		lblBiletIptali.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBiletIptali.setBounds(155, 11, 134, 38);
		contentPane.add(lblBiletIptali);
		
		textFieldBiletNo = new JTextField();
		textFieldBiletNo.setBounds(202, 86, 86, 20);
		contentPane.add(textFieldBiletNo);
		textFieldBiletNo.setColumns(10);
		
		JLabel lblBiletNo = new JLabel("Bilet NO:");
		lblBiletNo.setBounds(128, 89, 64, 14);
		contentPane.add(lblBiletNo);
		
		JButton btnIptalEt = new JButton("\u0130ptal Et");
		btnIptalEt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedReader br = new BufferedReader(new FileReader("GiriþYapan.txt"));
					try {
						String str;
						while ((str = br.readLine()) != null) {
							musteri = str;
						}
						br.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} catch (FileNotFoundException e2) {
					System.out.println(e2);
					e2.printStackTrace();
				}
				int x = 0;
				
				for (int i = 0; i < biletBilgileri.size() / 6; i++) {
					if(musteri.equals(biletBilgileri.get((i * 6) + 0).toString()) && textFieldBiletNo.getText().equals(biletBilgileri.get((i * 6) + 1).toString())){
						
						biletBilgileri.remove((i * 6) + 0);
						biletBilgileri.remove((i * 6) + 0);
						biletBilgileri.remove((i * 6) + 0);
						biletBilgileri.remove((i * 6) + 0);
						biletBilgileri.remove((i * 6) + 0);
						biletBilgileri.remove((i * 6) + 0);
						
						
						try {
							PrintWriter writer = new PrintWriter("BiletBilgileri.txt", "UTF-8");
							for (int j = 0; j < biletBilgileri.size() / 6; j++) {
								writer.println(biletBilgileri.get((j * 6) + 0) + "," + biletBilgileri.get((j * 6) + 1) + ","
										+ biletBilgileri.get((j * 6) + 2) + "," + biletBilgileri.get((j * 6) + 3) + ","
										+ biletBilgileri.get((j * 6) + 4) + "," + biletBilgileri.get((j * 6) + 5));

							}

							writer.close();
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.toString());
						}

						JOptionPane.showMessageDialog(null, "Biletiniz iptal edilmiþtir.");
						
						x++;
					}
				}
				
				if(x == 0){
					JOptionPane.showMessageDialog(null, "Sizin olmayan veya Geçersiz bir numara girdiniz lütfen tekrar deneyin.");
				}
			}
		});
		btnIptalEt.setBounds(155, 165, 89, 23);
		contentPane.add(btnIptalEt);
	}

}
