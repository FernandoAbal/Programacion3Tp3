package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import logica.AlgoritmoKruskal;
import logica.ConjuntoPersonas;
import logica.Persona;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Interfaz {

	private JFrame frame;
	private JTextField nombreTextField;
	private JTable table;
	private JTable tableGrupo1;
	private JTable tableGrupo2;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
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
	public Interfaz() {
		
		ConjuntoPersonas conjuntoPersonas = new ConjuntoPersonas();
		AlgoritmoKruskal ak = new AlgoritmoKruskal();
		
		initialize(conjuntoPersonas, ak);
	}

	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ConjuntoPersonas conjuntoPersonas, AlgoritmoKruskal ak) {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		nombreTextField = new JTextField();
		nombreTextField.setBounds(280, 80, 120, 25);
		frame.getContentPane().add(nombreTextField);
		nombreTextField.setColumns(10);
		
		JLabel nombrePersona = new JLabel("Ingrese el nombre");
		nombrePersona.setBounds(15, 80, 125, 30);
		frame.getContentPane().add(nombrePersona);
		
		JLabel lblIntersPorLos = new JLabel("Interés por los deportes");
		lblIntersPorLos.setBounds(15, 120, 152, 32);
		frame.getContentPane().add(lblIntersPorLos);
		
		JLabel lblIntersPorLa = new JLabel("Interés por la musica");
		lblIntersPorLa.setBounds(15, 160, 152, 32);
		frame.getContentPane().add(lblIntersPorLa);
		
		JLabel lblIntersPorLas = new JLabel("Interés por las noticias \r\ndel espectáculo");
		lblIntersPorLas.setBounds(15, 200, 192, 32);
		frame.getContentPane().add(lblIntersPorLas);
		
		JLabel lblIntersPorLa_1 = new JLabel("Interés por la ciencia");
		lblIntersPorLa_1.setBounds(15, 240, 192, 32);
		frame.getContentPane().add(lblIntersPorLa_1);
		
		JLabel titulo = new JLabel("App Clustering Humano");
		titulo.setFont(new Font("Arial", Font.BOLD, 26));
		titulo.setBounds(15, 0, 341, 38);
		frame.getContentPane().add(titulo);
		
		JComboBox interesDeportesComboBox = new JComboBox();
		interesDeportesComboBox.setBounds(280, 120, 120, 20);
		frame.getContentPane().add(interesDeportesComboBox);
		
		interesDeportesComboBox.setModel(new DefaultComboBoxModel(new String [] {"1","2","3","4","5"}));
		
		JComboBox interesMusicaComboBox = new JComboBox();
		interesMusicaComboBox.setBounds(280, 160, 120, 20);
		frame.getContentPane().add(interesMusicaComboBox);
		
		interesMusicaComboBox.setModel(new DefaultComboBoxModel(new String [] {"1", "2", "3", "4", "5"}));
		
		JComboBox interesEspectaculoComboBox = new JComboBox();
		interesEspectaculoComboBox.setBounds(280, 200, 120, 20);
		frame.getContentPane().add(interesEspectaculoComboBox);
		
		interesEspectaculoComboBox.setModel(new DefaultComboBoxModel(new String [] {"1", "2", "3", "4", "5"}));
		
		JComboBox interesCienciaComboBox = new JComboBox();
		interesCienciaComboBox.setBounds(280, 240, 120, 20);
		frame.getContentPane().add(interesCienciaComboBox);
		
		interesCienciaComboBox.setModel(new DefaultComboBoxModel(new String [] {"1", "2", "3", "4", "5"}));
		
		
		JScrollPane scrollPanePersonas = new JScrollPane();
		scrollPanePersonas.setBounds(600, 58, 350, 250);
		frame.getContentPane().add(scrollPanePersonas);
		
		table = new JTable();
		scrollPanePersonas.setViewportView(table);
		
		DefaultTableModel model = new DefaultTableModel();
		
		darNombreAColumnas(model);

		table.setModel(model);
		
		JButton enviarBoton = new JButton("ENVIAR");
		enviarBoton.setActionCommand("ENVIAR");
		enviarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				String nombre = nombreTextField.getText(); //Obtengo el nombre ingresado
				String deporteIndice = (String) interesDeportesComboBox.getSelectedItem();
				String musicaIndice = (String) interesMusicaComboBox.getSelectedItem();
				String espectaculoIndice = (String) interesEspectaculoComboBox.getSelectedItem();
				String cienciaIndice = (String) interesCienciaComboBox.getSelectedItem();
				
				conjuntoPersonas.crearPersona(nombre, deporteIndice, musicaIndice, espectaculoIndice, cienciaIndice);
				model.addRow(conjuntoPersonas.getUltimaPersona());

			}
		});
		enviarBoton.setBounds(280, 280, 85, 21);
		frame.getContentPane().add(enviarBoton);
		
		
		/*TABAL DEL GRUPO 1*/
		JScrollPane scrollPaneGrupo1 = new JScrollPane();
		scrollPaneGrupo1.setBounds(100, 450, 350, 250);
		frame.getContentPane().add(scrollPaneGrupo1);
		
		tableGrupo1 = new JTable();
		scrollPaneGrupo1.setViewportView(tableGrupo1);
		
		DefaultTableModel modelGrupo1 = new DefaultTableModel();
		
		darNombreAColumnas(modelGrupo1);
		
		tableGrupo1.setModel(modelGrupo1);
		
		/*TABLA DEL GRUPO 2*/
		JScrollPane scrollPaneGrupo2 = new JScrollPane();
		scrollPaneGrupo2.setBounds(600, 450, 350, 250);
		frame.getContentPane().add(scrollPaneGrupo2);
		
		tableGrupo2 = new JTable();
		scrollPaneGrupo2.setViewportView(tableGrupo2);
		
		DefaultTableModel modelGrupo2 = new DefaultTableModel();
		
		darNombreAColumnas(modelGrupo2);
		
		tableGrupo2.setModel(modelGrupo2);
		
		
		
		JButton botonCluster = new JButton("Clusterizar");
		botonCluster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limpiarTablaGrupoClustering(modelGrupo1);
				limpiarTablaGrupoClustering(modelGrupo2);
				
				ak.generarAGM(conjuntoPersonas.getPersonas());
				ak.generarDosGrupos();
				
				llenarTablaGrupoClustering(ak.obtenerGrupo1(), modelGrupo1);
				llenarTablaGrupoClustering(ak.obtenerGrupo2(), modelGrupo2);
	
			}
		});
		botonCluster.setBounds(725, 320, 100, 40);
		frame.getContentPane().add(botonCluster);
		
		JLabel lblNewLabel = new JLabel("GRUPO A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(100, 400, 350, 50);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblGrupoB = new JLabel("GRUPO B");
		lblGrupoB.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupoB.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGrupoB.setBounds(600, 400, 350, 50);
		frame.getContentPane().add(lblGrupoB);
		
		
		
	}
	
	
	public void darNombreAColumnas(DefaultTableModel model) {
		model.addColumn("Nombre");
		model.addColumn("Deportes i");
		model.addColumn("Musica i");
		model.addColumn("Noticias del espectaculo i");
		model.addColumn("Ciencia i");
	}
	
	public void limpiarTablaGrupoClustering(DefaultTableModel model) {
		int tamanio = model.getRowCount();
		
		for(int i = 0 ; i < tamanio; i++) {
			model.removeRow(0);
		}
	}
	
	public void llenarTablaGrupoClustering(LinkedList<Persona> listaGrupo, DefaultTableModel model) {
		for(Persona p : listaGrupo) {
			model.addRow(p.getDatosPersona());
		}
	}
}
