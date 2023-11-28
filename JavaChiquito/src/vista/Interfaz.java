package vista;

import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import modelo.AnalizadorSintactico;
import modelo.CompruebaLexico;

public class Interfaz extends JFrame implements ActionListener, KeyListener {
	private static final long serialVersionUID = 8477793022822429026L;
	
	private JButton btnArchivos, btnAnalisisLexico, btnAnalisisSintactico;
	private JPanel panelIzquierdo, panelDerecho, panelBotones, panelCodigoErrores;
	private JTable tablaSimbolos;
	private JLabel lblTablaSimbolos, lblCodigoFuente;
	private JTextArea txtCodigoFuente, txtAreaErrores, txtTokenizer;
	private File archivoElegido;
	private int cuentaIDs;
	private Vector<String> ids, todosLosTokens;
	private boolean bandErrorLexico;
	
	public Interfaz() {
		super("Analizador de lenguaje");
		
		cuentaIDs=0;
		ids=new Vector<String>();
		todosLosTokens=new Vector<String>();

		hazInterfaz();
		hazEscuchas();
	}
	private void hazEscuchas() {
		btnArchivos.addActionListener(this);
		btnAnalisisLexico.addActionListener(this);
		btnAnalisisSintactico.addActionListener(this);
		txtCodigoFuente.addKeyListener(this);
	}
	private void hazInterfaz() {
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,2));
		
		panelIzquierdo = new JPanel(new BorderLayout());
		panelBotones = new JPanel (new FlowLayout());
		//Panel izquierdo
		lblTablaSimbolos = new JLabel("Tabla de Simbolos y tokenizer");
		lblTablaSimbolos.setBorder(new EmptyBorder(10,10,10,0));

		tablaSimbolos = new JTable(100,2); //tabla de ids
		//tablaSimbolos.setShowGrid(false);
		tablaSimbolos.setEnabled(false);
		tablaSimbolos.getColumnModel().getColumn(0).setHeaderValue("Identificador");
		tablaSimbolos.getColumnModel().getColumn(1).setHeaderValue("Valor");

		JScrollPane scrollTabla = new JScrollPane(tablaSimbolos);
	
		JPanel panelaux = new JPanel(new GridLayout(0, 1));
		
		txtTokenizer=new JTextArea("");
		txtTokenizer.setEditable(false);
		JScrollPane scrollTokenizer = new JScrollPane(txtTokenizer);

		panelaux.add(scrollTabla);
		panelaux.add(scrollTokenizer);

		btnArchivos = new JButton("Abrir archivo");
		panelBotones.add(btnArchivos);
		
		btnAnalisisLexico = new JButton("Analisis léxico");
		panelBotones.add(btnAnalisisLexico);	
		
		btnAnalisisSintactico = new JButton("Analisis sintactico");
		panelBotones.add(btnAnalisisSintactico);
		btnAnalisisSintactico.setEnabled(false); //Work In Progress
		
		panelIzquierdo.add(lblTablaSimbolos, BorderLayout.NORTH);
		panelIzquierdo.add(panelBotones, BorderLayout.SOUTH);
		panelIzquierdo.add(panelaux, BorderLayout.CENTER); //esto es la tabla de simbolos y la estructura del programa
		
		//Panel izquierdo
		panelDerecho = new JPanel(new GridLayout(0, 1));
		
		

		txtCodigoFuente = new JTextArea();
		txtCodigoFuente.setBorder(new LineBorder(Color.black));
		JScrollPane scrollCodigo = new JScrollPane(txtCodigoFuente);	

		txtAreaErrores = new JTextArea();
		txtAreaErrores.setOpaque(false);
		txtAreaErrores.setEditable(false);
		txtAreaErrores.setBorder(new EmptyBorder(20,20,10,0));
		txtAreaErrores.setForeground(Color.red);
		JScrollPane scrollErrores = new JScrollPane(txtAreaErrores);	

		panelDerecho.add(scrollCodigo); //esto es el codigo fuente
		panelDerecho.add(scrollErrores); //esto es el area de errores
		
		panelCodigoErrores = new JPanel(new BorderLayout());
		lblCodigoFuente = new JLabel("Codigo Fuente");
		lblCodigoFuente.setBorder(new EmptyBorder(10,10,10,0));
		panelCodigoErrores.add(lblCodigoFuente, BorderLayout.NORTH);
		panelCodigoErrores.add(panelDerecho);
		
		this.add(panelIzquierdo);
		this.add(panelCodigoErrores);
		
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnArchivos)) { 
			JFileChooser chooser = new JFileChooser();
			int choice = chooser.showOpenDialog(this);
			if (choice != JFileChooser.APPROVE_OPTION) return;
				archivoElegido = chooser.getSelectedFile();
				FileInputStream fis = null;
	        String resultado = "";

	        try {
	            fis = new FileInputStream(archivoElegido);
	            int contenido;
	            while ((contenido = fis.read()) != -1) {
	                // convertir a char y añadirlo a la cadena de resultado
	                resultado += (char) contenido;
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (fis != null)
	                    fis.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
			
			txtCodigoFuente.setText(resultado);
			btnAnalisisSintactico.setEnabled(false);
			return;
		}

		if(e.getSource().equals(btnAnalisisLexico)) { 
			//Aqui va el codigo para el analisis lexico
			bandErrorLexico=false;
			txtAreaErrores.setText("");
			txtTokenizer.setText("");
			
			this.cuentaIDs=0;
			ids.removeAllElements();
			for(int i = 0; tablaSimbolos.getValueAt(i, 0)!=null; i++){
				tablaSimbolos.setValueAt(null, i, 0);
				tablaSimbolos.setValueAt(null, i, 1);
			}
			   
			
				if(!bandErrorLexico)
					this.btnAnalisisSintactico.setEnabled(true);
				//this.btnAnalisisLexico.setEnabled(false);
	        return;
		}

		if(e.getSource().equals(btnAnalisisSintactico)) { 
			//Aqui va el codigo para el analisis sintactico
			AnalizadorSintactico analizadorSintactico = new AnalizadorSintactico(todosLosTokens);
			analizadorSintactico.analizar();
			return;
		}

	}
	private String extracted(String linea) {
		linea=linea.replace("{", " { ");
		linea=linea.replace("}", " } ");
		linea=linea.replace("(", " ( ");
		linea=linea.replace(")", " ) ");
		linea=linea.replace(")", " ) ");
		linea=linea.replace("[", " [ ");
		linea=linea.replace("]", " ] ");
		linea=linea.replace("==", " == ");
		linea=linea.replace("!=", " != ");
		linea=linea.replace(">", " > ");
		linea=linea.replace("<", " < ");
		linea=linea.replace(">=", " >= ");
		linea=linea.replace("<=", " <= ");
		linea=linea.replace("+", " + ");
		linea=linea.replace("-", " - ");
		linea=linea.replace("*", " * ");
		linea=linea.replace("/", " / ");
		linea=linea.replace(";", " ; ");

		if(!linea.contains("=")) return linea;
		for(int i=0; i<linea.length(); i++){
			if(linea.charAt(i)!='=')
				continue;
			else if(linea.charAt(i+1)=='='){
				i++; continue;
			}
			else if(linea.charAt(i-1)=='<' || linea.charAt(i-1)=='>' || linea.charAt(i-1)=='!' || linea.charAt(i-1)=='='){
				continue;
			}else{
				linea = linea.substring(0,i)+" = "+linea.substring(i+1);
				i+=2;
			}
			
			
		}
		
		return linea;
	}
	private void meterATabla(String valor) {
		if(ids.contains(valor)) return; //no repetir ids
		tablaSimbolos.setValueAt(valor, cuentaIDs, 0);
		ids.add(valor);
		cuentaIDs++;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		btnAnalisisSintactico.setEnabled(false);
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
