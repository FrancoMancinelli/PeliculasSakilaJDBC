package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import daos.ActoresDAO;
import daos.PeliculasDAO;
import models.Pelicula;
import javax.swing.JTextArea;

/**
 * Clase de interfaz gráfica (WindowsBuilder) que muestra el listado
 * de peliculas de la base de datos Sakila y permite recorrer y visualizar
 * toda la información de estas
 * @author anonymus
 *
 */
public class PeliculasView {

	private JFrame frame;
	private JLabel lblTitulo;
	private JLabel lblID;
	private JLabel lblName;
	private JLabel lblDescription;
	private JLabel lblYear;
	private JLabel lblLenght;
	private JLabel lblActors;
	private JTextField txfID;
	private JTextField txfDuration;
	private JTextField txfYear;
	private JTextField txfName;
	private JButton btnFirst;
	private JButton btnBack;
	private JButton btnNext;
	private JButton btnLast;
	private JButton btnNew;
	private JButton btnSave;
	private JButton btnCancel;
	private Pelicula pelicula;
	private PeliculasDAO pDAO;
	private ActoresDAO aDAO;
	private ResultSet rs;
	private JTable table;
	private DefaultTableModel model;
	private String[][] data;
	private String[] col;
	private JLabel lblActorsLastName;
	private JTextArea txaDescription;
	private JLabel lblActorsName;
	
	/**
	 * Crea la aplicación
	 */
	public PeliculasView() {
		pDAO = new PeliculasDAO();
		rs = pDAO.getPeliculas();
		
		pelicula = pDAO.getNext(rs);
		aDAO = new ActoresDAO();
		
		initialize();
		printMovie();
		setListeners();
	}

	/**
	 * Inicializa los contenidos del frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 620, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblTitulo = new JLabel("Peliculas Sakila");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 584, 46);
		frame.getContentPane().add(lblTitulo);

		lblID = new JLabel("ID:");
		lblID.setBounds(51, 132, 37, 14);
		frame.getContentPane().add(lblID);

		lblName = new JLabel("Titulo:");
		lblName.setBounds(174, 84, 46, 14);
		frame.getContentPane().add(lblName);

		lblDescription = new JLabel("Descripción:");
		lblDescription.setBounds(20, 195, 88, 14);
		frame.getContentPane().add(lblDescription);

		lblYear = new JLabel("Año Estreno:");
		lblYear.setBounds(390, 132, 106, 14);
		frame.getContentPane().add(lblYear);

		lblLenght = new JLabel("Duración:");
		lblLenght.setBounds(188, 132, 76, 14);
		frame.getContentPane().add(lblLenght);

		lblActors = new JLabel("Actores:");
		lblActors.setBounds(338, 204, 65, 14);
		frame.getContentPane().add(lblActors);
		
		lblActorsLastName = new JLabel("APELLIDOS");
		lblActorsLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblActorsLastName.setBounds(482, 182, 89, 14);
		frame.getContentPane().add(lblActorsLastName);
		
		lblActorsName = new JLabel("NOMBRE");
		lblActorsName.setHorizontalAlignment(SwingConstants.CENTER);
		lblActorsName.setBounds(387, 182, 89, 14);
		frame.getContentPane().add(lblActorsName);

		txfID = new JTextField();
		txfID.setFont(new Font("Verdana", Font.BOLD, 11));
		txfID.setHorizontalAlignment(SwingConstants.CENTER);
		txfID.setBounds(74, 129, 65, 20);
		frame.getContentPane().add(txfID);
		txfID.setEnabled(false);
		txfID.setColumns(10);

		txfDuration = new JTextField();
		txfDuration.setFont(new Font("Verdana", Font.BOLD, 11));
		txfDuration.setHorizontalAlignment(SwingConstants.CENTER);
		txfDuration.setBounds(245, 129, 112, 20);
		frame.getContentPane().add(txfDuration);
		txfDuration.setColumns(10);

		txfYear = new JTextField();
		txfYear.setFont(new Font("Verdana", Font.BOLD, 11));
		txfYear.setHorizontalAlignment(SwingConstants.CENTER);
		txfYear.setBounds(466, 129, 86, 20);
		frame.getContentPane().add(txfYear);
		txfYear.setColumns(10);

		txfName = new JTextField();
		txfName.setHorizontalAlignment(SwingConstants.CENTER);
		txfName.setFont(new Font("Verdana", Font.BOLD, 11));
		txfName.setBounds(215, 80, 202, 20);
		frame.getContentPane().add(txfName);
		txfName.setColumns(10);

		btnFirst = new JButton("Primero");
		btnFirst.setBounds(88, 417, 89, 23);
		frame.getContentPane().add(btnFirst);

		btnBack = new JButton("Anterior");
		btnBack.setBounds(197, 417, 89, 23);
		frame.getContentPane().add(btnBack);

		btnNext = new JButton("Siguiente");
		btnNext.setBounds(306, 417, 89, 23);
		frame.getContentPane().add(btnNext);

		btnLast = new JButton("Ultimo");
		btnLast.setBounds(416, 417, 89, 23);
		frame.getContentPane().add(btnLast);
		
		btnNew = new JButton("Nuevo");
		btnNew.setBounds(251, 456, 89, 23);
		frame.getContentPane().add(btnNew);
		
		btnSave = new JButton("Guardar");
		btnSave.setBounds(366, 456, 89, 23);
		frame.getContentPane().add(btnSave);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(135, 456, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		data = aDAO.getActorsName(aDAO.getActorsID(pelicula.getId()));
		col = new String[] {"Nombre", "Apellidos"};
		
		model = new DefaultTableModel(data,col);				
		table = new JTable(model);
		table.setBounds(391, 204, 182, 177);
		table.setEnabled(false);
		frame.getContentPane().add(table);
		
		txaDescription = new JTextArea();
		txaDescription.setBounds(20, 215, 301, 152);
		txaDescription.setLineWrap(true);
		frame.getContentPane().add(txaDescription);
				
		frame.setVisible(true);
		
	}

	/**
	 * Configura los Listeners de los distintos botones
	 */
	public void setListeners() {
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pelicula = pDAO.getFirst(rs);
				if (pelicula != null) {
					printMovie();
				} else {

				}
			}
		});

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pelicula = pDAO.getNext(rs);
				if (pelicula != null) {
					printMovie();
				} else {
					
				}
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pelicula = pDAO.getBack(rs);
				if (pelicula != null) {
					printMovie();
				} else {

				}
			}
		});
		
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pelicula = pDAO.getLast(rs);
				if (pelicula != null) {
					printMovie();
				} else {

				}
			}
		});
		
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printEmpty();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printMovie();
				changeEnabled(false);
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(newMovieEmpty()) {
					try {
						int duration = Integer.parseInt(txfDuration.getText());
						int year = Integer.parseInt(txfYear.getText());
						
						Pelicula newP = new Pelicula(0, duration, year, txfName.getText(), txaDescription.getText());
						pDAO.saveNewMovie(newP);
					
						printMovie();
						changeEnabled(false);
						
						frame.dispose();
						new PeliculasView();
						
						JOptionPane.showMessageDialog(btnSave, "Pelicula agregada con éxito");


					} catch(Exception ex) {
						JOptionPane.showMessageDialog(btnSave, "El tiempo de duración y el año de estreno deben ser números enteros");
					}

				} else {
					JOptionPane.showMessageDialog(btnSave, "Asegurese de rellenar todos los campos correctamente");
				}
			}

		});
	}
	
	/**
	 * Confirma si todos los campos TextField estan vacios o no
	 * @return True en caso de haber alguno vacio, False en caso contrario
	 */
	private boolean newMovieEmpty() {
		return  !txfDuration.getText().equals("") &&
				!txfYear.getText().equals("") &&
				!txfName.getText().equals("") &&
//				!txfDescription.getText().equals("");
				!txaDescription.getText().equals("");
	}

	/**
	 * Imprime en los campos de datos la información de la pelicula actual
	 */
	public void printMovie() {
		txfName.setText(pelicula.getName());
//		txfDescription.setText(pelicula.getDescription());
		txaDescription.setText(pelicula.getDescription());
		txfDuration.setText(String.valueOf(pelicula.getDuration())+" minutos");
		txfYear.setText(String.valueOf(pelicula.getYear()));
		txfID.setText(String.valueOf(pelicula.getId()));
		
		rePrintActors();
		
		changeEnabled(false);
	}
	
	/**
	 * Actualiza el listado de actores de la JTable para que correspondan
	 * con la pelicula actual
	 */
	public void rePrintActors() {

		model.setRowCount(0);
		model.setRowCount(10);
		
		String[][] data = aDAO.getActorsName(aDAO.getActorsID(pelicula.getId()));
		
		for(int i = 0; i < data.length; i++) {
			model.insertRow(i, data[i]);
		}
		
	}
	
	/**
	 * Vacia los campos de datos, para que el usuario pueda rellenar la información
	 */
	public void printEmpty() {
		txfName.setText("");
		txaDescription.setText("");
		txfDuration.setText("");
		txfYear.setText("");
		txfID.setText("");
		
		model.setRowCount(0);
		model.setRowCount(10);
		
		changeEnabled(true);
	}
	
	/**
	 * Actualiza el estado de los botones y campos de información
	 * Para el modo editable debe indicarse True como parametro
	 * Para el modo lectura debe indicarse False como parametro
	 * @param param True o False según el modo deseado
	 */
	public void changeEnabled(boolean param) {
		txfName.setEnabled(param);
		txfDuration.setEnabled(param);
		txfYear.setEnabled(param);
		btnCancel.setEnabled(param);
		btnNew.setEnabled(!param);
		btnFirst.setEnabled(!param);
		btnLast.setEnabled(!param);
		btnNext.setEnabled(!param);
		btnBack.setEnabled(!param);
		btnSave.setEnabled(param);
	}
}
