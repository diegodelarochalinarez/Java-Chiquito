
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
//import java.awt.*;
import javax.swing.*;

public class Aplicacion extends JFrame implements ActionListener {
	private static final long serialVersionUID = 8477793022822429026L;
	
	private JButton btnArchivos, btnALexico, btnASintactico;
	private File archivoElegido;
	
	public static void main(String[] args) {
		new Aplicacion();
	}
	public Aplicacion() {
		super("Analizador de lenguaje");
		
		hazInterfaz();
		hazEscuchas();
	}
	private void hazEscuchas() {
		btnArchivos.addActionListener(this);
		btnALexico.addActionListener(this);
	}
	private void hazInterfaz() {
		//this.setExtendedState(this.MAXIMIZED_BOTH);
				setSize(500, 720);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				this.setLayout(new GridLayout(3,1));
				
				btnArchivos = new JButton("Abrir archivo");
				this.add(btnArchivos);
				
				btnALexico = new JButton("Analisis léxico");
				this.add(btnALexico);
				
				btnASintactico = new JButton("Analisis sintactico");
				this.add(btnASintactico);
				btnASintactico.setEnabled(false); //WIP
				
				setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnArchivos)) { 
			JFileChooser chooser = new JFileChooser();
			int choice = chooser.showOpenDialog(this);
			if (choice != JFileChooser.APPROVE_OPTION) return;
				archivoElegido = chooser.getSelectedFile();
		}
		if(e.getSource().equals(btnALexico)) { 
			//Aqui va el codigo para el analisis lexico
			FileInputStream fis = null;
	        String resultado = "";

	        try {
	            fis = new FileInputStream(archivoElegido);
	            int contenido;
	            while ((contenido = fis.read()) != -1) {
	                // convert to char and display it
	                resultado += (char) contenido;
	            }

	            System.out.println("Archivo leido");
	            System.out.println(resultado);

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
	        
		}
	}
}
