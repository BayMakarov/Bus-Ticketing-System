import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;

import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import javax.swing.JRadioButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BiletGüncelleme extends JFrame {

	private JPanel contentPane;
	private JTextField textUserName;
	private JPasswordField passwordField;
	private JRadioButton rbA;
	private JRadioButton rbB;
	private JRadioButton rbC;
	private JRadioButton rbD;
	private ButtonGroup bGroup = new ButtonGroup();

	private String musteri;

	private ArrayList<String> biletBilgileri = new ArrayList<String>();

	private final JTextField textField = new JTextField();
	private final Action action = new SwingAction();
	private JTextField textFieldBiletNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BiletGüncelleme frame = new BiletGüncelleme();
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
	public BiletGüncelleme() {
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

		textField.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 429, 300);

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

		JLabel lblSeferler = new JLabel("Seferler");
		lblSeferler.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSeferler.setBounds(10, 52, 100, 14);
		contentPane.add(lblSeferler);

		JLabel lblKoltuklar = new JLabel("Koltuklar");
		lblKoltuklar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKoltuklar.setBounds(138, 52, 61, 14);
		contentPane.add(lblKoltuklar);

		JLabel lblZaman = new JLabel("Zaman");
		lblZaman.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblZaman.setBounds(269, 52, 61, 14);
		contentPane.add(lblZaman);

		JList listSeferler = new JList();
		listSeferler.setModel(new AbstractListModel() {
			String[] values = new String[] { "Istanbul-Ankara", "Ankara-Istanbul", "Izmir-Ankara", "Izmir-Istanbul",
					"Istanbul-Izmir", "Ankara-Izmir" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listSeferler.setSelectedIndex(0);
		listSeferler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listSeferler.setVisibleRowCount(18);
		listSeferler.setBounds(10, 80, 100, 142);
		contentPane.add(listSeferler);

		JComboBox comboBoxKoltuklar = new JComboBox();
		comboBoxKoltuklar.setToolTipText("Please Select Time");
		comboBoxKoltuklar.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBoxKoltuklar.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16" }));
		comboBoxKoltuklar.setBounds(120, 139, 46, 23);
		contentPane.add(comboBoxKoltuklar);

		JComboBox comboBoxZaman = new JComboBox();
		comboBoxZaman.setToolTipText("Please Select Time");
		comboBoxZaman.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBoxZaman.setModel(new DefaultComboBoxModel(new String[] { "00:00", "01:00", "02:00", "03:00", "04:00",
				"05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00" }));
		comboBoxZaman.setBounds(269, 77, 61, 23);
		contentPane.add(comboBoxZaman);

		JComboBox comboBoxAmPm = new JComboBox();
		comboBoxAmPm.setToolTipText("AM or PM please Select");
		comboBoxAmPm.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBoxAmPm.setModel(new DefaultComboBoxModel(new String[] { "AM", "PM" }));
		comboBoxAmPm.setBounds(340, 77, 46, 23);
		contentPane.add(comboBoxAmPm);

		JRadioButton rdbtnA = new JRadioButton("A Cam");
		rdbtnA.setSelected(true);
		rdbtnA.setBounds(116, 77, 68, 23);
		contentPane.add(rdbtnA);

		JRadioButton rdbtnB = new JRadioButton("B Koridor");
		rdbtnB.setBounds(181, 77, 82, 23);
		contentPane.add(rdbtnB);

		JRadioButton rdbtnC = new JRadioButton("C Koridor");
		rdbtnC.setBounds(181, 109, 82, 23);
		contentPane.add(rdbtnC);

		JRadioButton rdbtnD = new JRadioButton("D Cam");
		rdbtnD.setBounds(116, 109, 68, 23);
		contentPane.add(rdbtnD);

		bGroup.add(rdbtnA);
		bGroup.add(rdbtnB);
		bGroup.add(rdbtnC);
		bGroup.add(rdbtnD);
		rbA = rdbtnA;
		rbB = rdbtnB;
		rbC = rdbtnC;
		rbD = rdbtnD;

		JLabel lblBiletGuncelleme = new JLabel("Bilet G\u00FCncelleme");
		lblBiletGuncelleme.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBiletGuncelleme.setBounds(120, 11, 210, 30);
		contentPane.add(lblBiletGuncelleme);

		JLabel lblBiletNo = new JLabel("Bilet NO:");
		lblBiletNo.setBounds(253, 143, 61, 14);
		contentPane.add(lblBiletNo);

		textFieldBiletNo = new JTextField();
		textFieldBiletNo.setBounds(317, 140, 86, 20);
		contentPane.add(textFieldBiletNo);
		textFieldBiletNo.setColumns(10);

		JButton btnGuncelle = new JButton("G\u00FCncelle");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = 0;

				for (int i = 0; i < biletBilgileri.size() / 6; i++) {
					if (rbA.isSelected()&&listSeferler.getSelectedValue().toString().equals(biletBilgileri.get((i * 6) + 3).toString())
							&& ("A-" + comboBoxKoltuklar.getSelectedItem().toString()).toString()
									.equals(biletBilgileri.get((i * 6) + 4).toString())
							&& (comboBoxZaman.getSelectedItem().toString() + ":"
									+ comboBoxAmPm.getSelectedItem().toString()).toString()
											.equals(biletBilgileri.get((i * 6) + 5).toString())) {
						JOptionPane.showMessageDialog(null,
								"Almak isteðiniz koltuk doludur lütfen baþka bir koltuk seçiniz.");
						x++;
					} else if (rbB.isSelected()&&listSeferler.getSelectedValue().toString()
							.equals(biletBilgileri.get((i * 6) + 3).toString())
							&& ("B-" + comboBoxKoltuklar.getSelectedItem().toString()).toString()
									.equals(biletBilgileri.get((i * 6) + 4).toString())
							&& (comboBoxZaman.getSelectedItem().toString() + ":"
									+ comboBoxAmPm.getSelectedItem().toString()).toString()
											.equals(biletBilgileri.get((i * 6) + 5).toString())) {
						JOptionPane.showMessageDialog(null,
								"Almak isteðiniz koltuk doludur lütfen baþka bir koltuk seçiniz.");
						x++;
					} else if (rbC.isSelected()&&listSeferler.getSelectedValue().toString()
							.equals(biletBilgileri.get((i * 6) + 3).toString())
							&& ("C-" + comboBoxKoltuklar.getSelectedItem().toString()).toString()
									.equals(biletBilgileri.get((i * 6) + 4).toString())
							&& (comboBoxZaman.getSelectedItem().toString() + ":"
									+ comboBoxAmPm.getSelectedItem().toString()).toString()
											.equals(biletBilgileri.get((i * 6) + 5).toString())) {
						JOptionPane.showMessageDialog(null,
								"Almak isteðiniz koltuk doludur lütfen baþka bir koltuk seçiniz.");
						x++;
					} else if (rbD.isSelected()&&listSeferler.getSelectedValue().toString()
							.equals(biletBilgileri.get((i * 6) + 3).toString())
							&& ("D-" + comboBoxKoltuklar.getSelectedItem().toString()).toString()
									.equals(biletBilgileri.get((i * 6) + 4).toString())
							&& (comboBoxZaman.getSelectedItem().toString() + ":"
									+ comboBoxAmPm.getSelectedItem().toString()).toString()
											.equals(biletBilgileri.get((i * 6) + 5).toString())) {
						JOptionPane.showMessageDialog(null,
								"Almak isteðiniz koltuk doludur lütfen baþka bir koltuk seçiniz.");
						x++;
					}
				}

				if (x == 0) {
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
					int y = 0;

					for (int i = 0; i < biletBilgileri.size() / 6; i++) {
						if (musteri.equals(biletBilgileri.get((i * 6) + 0).toString())
								&& textFieldBiletNo.getText().equals(biletBilgileri.get((i * 6) + 1).toString())) {
							if (listSeferler.getSelectedIndex() == 0 || listSeferler.getSelectedIndex() == 1) {
								if (rbA.isSelected()) {
									biletBilgileri.set((i * 6) + 2, 40 + "");
									biletBilgileri.set((i * 6) + 3, listSeferler.getSelectedValue().toString());
									biletBilgileri.set((i * 6) + 4,
											"A-" + comboBoxKoltuklar.getSelectedItem().toString());
									biletBilgileri.set((i * 6) + 5, comboBoxZaman.getSelectedItem().toString() + ":"
											+ comboBoxAmPm.getSelectedItem().toString());
								} else if (rbB.isSelected()) {
									biletBilgileri.set((i * 6) + 2, 40 + "");
									biletBilgileri.set((i * 6) + 3, listSeferler.getSelectedValue().toString());
									biletBilgileri.set((i * 6) + 4,
											"B-" + comboBoxKoltuklar.getSelectedItem().toString());
									biletBilgileri.set((i * 6) + 5, comboBoxZaman.getSelectedItem().toString() + ":"
											+ comboBoxAmPm.getSelectedItem().toString());
								} else if (rbC.isSelected()) {
									biletBilgileri.set((i * 6) + 2, 40 + "");
									biletBilgileri.set((i * 6) + 3, listSeferler.getSelectedValue().toString());
									biletBilgileri.set((i * 6) + 4,
											"C-" + comboBoxKoltuklar.getSelectedItem().toString());
									biletBilgileri.set((i * 6) + 5, comboBoxZaman.getSelectedItem().toString() + ":"
											+ comboBoxAmPm.getSelectedItem().toString());
								} else {
									biletBilgileri.set((i * 6) + 2, 40 + "");
									biletBilgileri.set((i * 6) + 3, listSeferler.getSelectedValue().toString());
									biletBilgileri.set((i * 6) + 4,
											"D-" + comboBoxKoltuklar.getSelectedItem().toString());
									biletBilgileri.set((i * 6) + 5, comboBoxZaman.getSelectedItem().toString() + ":"
											+ comboBoxAmPm.getSelectedItem().toString());
								}
							} else if (listSeferler.getSelectedIndex() == 2 || listSeferler.getSelectedIndex() == 4) {
								if (rbA.isSelected()) {
									biletBilgileri.set((i * 6) + 2, 45 + "");
									biletBilgileri.set((i * 6) + 3, listSeferler.getSelectedValue().toString());
									biletBilgileri.set((i * 6) + 4,
											"A-" + comboBoxKoltuklar.getSelectedItem().toString());
									biletBilgileri.set((i * 6) + 5, comboBoxZaman.getSelectedItem().toString() + ":"
											+ comboBoxAmPm.getSelectedItem().toString());
								} else if (rbB.isSelected()) {
									biletBilgileri.set((i * 6) + 2, 45 + "");
									biletBilgileri.set((i * 6) + 3, listSeferler.getSelectedValue().toString());
									biletBilgileri.set((i * 6) + 4,
											"B-" + comboBoxKoltuklar.getSelectedItem().toString());
									biletBilgileri.set((i * 6) + 5, comboBoxZaman.getSelectedItem().toString() + ":"
											+ comboBoxAmPm.getSelectedItem().toString());
								} else if (rbC.isSelected()) {
									biletBilgileri.set((i * 6) + 2, 45 + "");
									biletBilgileri.set((i * 6) + 3, listSeferler.getSelectedValue().toString());
									biletBilgileri.set((i * 6) + 4,
											"C-" + comboBoxKoltuklar.getSelectedItem().toString());
									biletBilgileri.set((i * 6) + 5, comboBoxZaman.getSelectedItem().toString() + ":"
											+ comboBoxAmPm.getSelectedItem().toString());
								} else {
									biletBilgileri.set((i * 6) + 2, 45 + "");
									biletBilgileri.set((i * 6) + 3, listSeferler.getSelectedValue().toString());
									biletBilgileri.set((i * 6) + 4,
											"D-" + comboBoxKoltuklar.getSelectedItem().toString());
									biletBilgileri.set((i * 6) + 5, comboBoxZaman.getSelectedItem().toString() + ":"
											+ comboBoxAmPm.getSelectedItem().toString());
								}
							} else {
								if (rbA.isSelected()) {
									biletBilgileri.set((i * 6) + 2, 50 + "");
									biletBilgileri.set((i * 6) + 3, listSeferler.getSelectedValue().toString());
									biletBilgileri.set((i * 6) + 4,
											"A-" + comboBoxKoltuklar.getSelectedItem().toString());
									biletBilgileri.set((i * 6) + 5, comboBoxZaman.getSelectedItem().toString() + ":"
											+ comboBoxAmPm.getSelectedItem().toString());
								} else if (rbB.isSelected()) {
									biletBilgileri.set((i * 6) + 2, 50 + "");
									biletBilgileri.set((i * 6) + 3, listSeferler.getSelectedValue().toString());
									biletBilgileri.set((i * 6) + 4,
											"B-" + comboBoxKoltuklar.getSelectedItem().toString());
									biletBilgileri.set((i * 6) + 5, comboBoxZaman.getSelectedItem().toString() + ":"
											+ comboBoxAmPm.getSelectedItem().toString());
								} else if (rbC.isSelected()) {
									biletBilgileri.set((i * 6) + 2, 50 + "");
									biletBilgileri.set((i * 6) + 3, listSeferler.getSelectedValue().toString());
									biletBilgileri.set((i * 6) + 4,
											"C-" + comboBoxKoltuklar.getSelectedItem().toString());
									biletBilgileri.set((i * 6) + 5, comboBoxZaman.getSelectedItem().toString() + ":"
											+ comboBoxAmPm.getSelectedItem().toString());
								} else {
									biletBilgileri.set((i * 6) + 2, 50 + "");
									biletBilgileri.set((i * 6) + 3, listSeferler.getSelectedValue().toString());
									biletBilgileri.set((i * 6) + 4,
											"D-" + comboBoxKoltuklar.getSelectedItem().toString());
									biletBilgileri.set((i * 6) + 5, comboBoxZaman.getSelectedItem().toString() + ":"
											+ comboBoxAmPm.getSelectedItem().toString());
								}
							}

							try {
								PrintWriter writer = new PrintWriter("BiletBilgileri.txt", "UTF-8");
								for (int j = 0; j < biletBilgileri.size() / 6; j++) {
									writer.println(biletBilgileri.get((j * 6) + 0) + ","
											+ biletBilgileri.get((j * 6) + 1) + "," + biletBilgileri.get((j * 6) + 2)
											+ "," + biletBilgileri.get((j * 6) + 3) + ","
											+ biletBilgileri.get((j * 6) + 4) + "," + biletBilgileri.get((j * 6) + 5));

								}

								writer.close();
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(null, e1.toString());
							}

							JOptionPane.showMessageDialog(null, "Bilet bilgileriniz yenilemiþtir.");

							y++;
						}
					}

					if (y == 0) {
						JOptionPane.showMessageDialog(null,
								"Sizin olmayan veya Geçersiz bir numara girdiniz lütfen tekrar deneyin.");
					}

				}
			}
		});
		btnGuncelle.setBounds(269, 199, 117, 23);
		contentPane.add(btnGuncelle);

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
