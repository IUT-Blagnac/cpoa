package gui.view;



import gui.model.SimpleTableModel;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;



public class SimpleFrame extends JFrame {

	// Déclaration du table model utilisé
	
	private SimpleTableModel stm; 
	
	private static final long serialVersionUID = 1L;

	// =======================================================================
	// Attributs graphiques (gérés par V.E.)
	// =======================================================================
	private JPanel    jContentPane = null;
	private JScrollPane jScrollPaneTable = null;

	private MyVerySimpleJTable simpleJTable = null;

	/**
	 * This is the default constructor
	 */
	public SimpleFrame() {
		super();
		this.initialize();
		
		// Création TableModel et lien au JTable 
		this.stm = new SimpleTableModel();
		this.getSimpleJTable().setModel(this.stm);
		
		this.setSize (200, 100);
	}
	
	// =======================================================================
	// Initialisation et accesseur des composants graphiques (géré par V.E.)
	// =======================================================================

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(this.getJContentPane());
		this.setTitle("Gestion des Personnes");
		// validation de l'état des composants (actifs ou non)
	}

	/**
	 * This method initializes jScrollPaneTable	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneTable() {
		if (this.jScrollPaneTable == null) {
			this.jScrollPaneTable = new JScrollPane();
			this.jScrollPaneTable.setViewportView(this.getSimpleJTable());
			this.jScrollPaneTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			this.jScrollPaneTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		}
		return this.jScrollPaneTable;
	}

	/**
	 * This method initializes tablePersonnes	
	 * 	
	 * @return MyVerySimpleJTable	
	 */
	private MyVerySimpleJTable getSimpleJTable() {
		if (this.simpleJTable == null) {
			this.simpleJTable = new MyVerySimpleJTable();
			this.simpleJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
		return this.simpleJTable;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (this.jContentPane == null) {
			this.jContentPane = new JPanel();
			this.jContentPane.setLayout(new BorderLayout());
			this.jContentPane.add(this.getJScrollPaneTable(), BorderLayout.CENTER);
			
		}
		return this.jContentPane;
	}
}