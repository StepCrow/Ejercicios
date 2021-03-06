package ejerciciosProglll;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.*;

/** Ejercicio de hilos con ventanas. Esta clase carga el texto del Quijote en un �rea de texto,
 * y permite navegar por el �rea con la scrollbar y con botones de p�gina arriba y p�gina abajo.
 * 1. Modificarlo para que al pulsar los botones el scroll se haga con una animaci�n 
 * a lo largo de un segundo, en lugar de en forma inmediata.
 * 2. Prueba a pulsar muy r�pido varias p�ginas abajo. �C�mo lo arreglar�as para que el scroll
 * en ese caso funcione bien y vaya bajando una p�gina tras otra pero las baje *completas*?
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class VentanaQuijote extends JFrame {

	private JTextArea taTexto;
	private JScrollPane spTexto;
	
	public VentanaQuijote() {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setTitle( "Don Quijote de la Mancha" );
		setSize( 800, 600 );
		setLocationRelativeTo( null );  // Pone la ventana relativa a la pantalla
		taTexto = new JTextArea();
		spTexto = new JScrollPane( taTexto );
		add( spTexto, BorderLayout.CENTER );
		JPanel pBotonera = new JPanel();
		JButton bPagArriba = new JButton( "^" );
		JButton bPagAbajo = new JButton( "v" );
		pBotonera.add( bPagArriba );
		pBotonera.add( bPagAbajo );
		add( pBotonera, BorderLayout.SOUTH );
		bPagArriba.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < 21; i++) {
					
					muevePagina( -(spTexto.getHeight()-1) );
					try {
						Thread.sleep(50);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
				
			}
		});
		bPagAbajo.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < 21; i++) {
					
					
					muevePagina( (spTexto.getHeight()-1) );
					 
					try {
						Thread.sleep(50);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
				
				
				
				
				
			}
		});
	}
	
	private void muevePagina( int pixelsVertical ) {
		
		// TODO Cambiar este comportamiento de acuerdo a los comentarios de la cabecera de clase
		
		JScrollBar bVertical = spTexto.getVerticalScrollBar();
		System.out.println( "Moviendo texto de " + bVertical.getValue() + " a " + (bVertical.getValue()+pixelsVertical) );
		bVertical.setValue( bVertical.getValue() + pixelsVertical );
	}
	
	private void cargaQuijote() {
		try {
			
			Scanner scanner = new Scanner( VentanaQuijote.class.getResourceAsStream( "DonQuijote.txt" ), "UTF-8" );
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				taTexto.append( linea + "\n" );
			}
			scanner.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog( this, "No se ha podido cargar el texto" );
		}
	}

	public static void main(String[] args) {
		VentanaQuijote v = new VentanaQuijote();
		v.setVisible( true );
		v.cargaQuijote();
	}

}