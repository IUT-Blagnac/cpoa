package gui.view;


import gui.model.StudentTableModel;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import data.model.Student;


public class StudentListFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	///  fenêtre d'édition d'un Student (créé à la première utilisation)
	private StudentDialog studentDialog = null;
	
	// TableModel utilisé
	private StudentTableModel studentTM = null;

	// =======================================================================
	// Attributs graphiques (gérés par V.E.)
	// =======================================================================
	private JPanel    jContentPane = null;
	private JPanel    topPanel = null;
	private JButton   butAjout = null;
	private JButton   butSuppr = null;
	private JButton   butModif = null;
	private JScrollPane jScrollPaneTable = null;

	private JTable studentTable = null;

	/**
	 * This is the default constructor
	 */
	public StudentListFrame() {
		super();
		this.initialize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.studentTM = new StudentTableModel();;
		this.getStudentTable().setModel(this.studentTM);
	}

	/**
	 * Variante du constructeur avec initialisation de la liste des students à traiter
	 */
	public StudentListFrame(List<Student> listeStudentsInitiale) {
		super();
		this.initialize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.studentTM = new StudentTableModel();;
		this.studentTM.loadDatas(listeStudentsInitiale);
		this.getStudentTable().setModel(this.studentTM);		
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
		this.setTitle("Gestion des Students");
	}

	/**
	 * This method initializes jScrollPaneTable	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneTable() {
		if (this.jScrollPaneTable == null) {
			this.jScrollPaneTable = new JScrollPane();
			this.jScrollPaneTable.setViewportView(this.getStudentTable());
			this.jScrollPaneTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			this.jScrollPaneTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		}
		return this.jScrollPaneTable;
	}

	/**
	 * This method initializes studentTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getStudentTable() {
		if (this.studentTable == null) {
			this.studentTable = new JTable();
			this.studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
		return this.studentTable;
	}

	
	/**
	 * Initialisation de la boite de dialog StudentDialog.
	 * 
	 * @return StudentDialog
	 */
	private StudentDialog getStudentDialog() {
		if (this.studentDialog==null) {
			this.studentDialog = new StudentDialog(this);
		}
		return this.studentDialog;
	}
	
	
	/**
	 * This method initializes topPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTopPanel() {
		if (this.topPanel == null) {
			this.topPanel = new JPanel();
			this.topPanel.setLayout(new BorderLayout());
			this.topPanel.add(this.getButAjout(), BorderLayout.WEST);
			this.topPanel.add(this.getButSuppr(), BorderLayout.EAST);
			this.topPanel.add(this.getButModif(), BorderLayout.CENTER);
		}
		return this.topPanel;
	}

	/**
	 * This method initializes butAjout	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButAjout() {
		if (this.butAjout == null) {
			this.butAjout = new JButton();
			this.butAjout.setText("Ajouter");
			this.butAjout.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					StudentListFrame.this.ajoutStudent();
				}
			});
		}
		return this.butAjout;
	}

	/**
	 * This method initializes butSuppr	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButSuppr() {
		if (this.butSuppr == null) {
			this.butSuppr = new JButton();
			this.butSuppr.setText("Supprimer");
			this.butSuppr.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					StudentListFrame.this.supprStudent();
				}
			});
		}
		return this.butSuppr;
	}

	/**
	 * This method initializes butModif	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButModif() {
		if (this.butModif == null) {
			this.butModif = new JButton();
			this.butModif.setText("Modifier");
			this.butModif.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					StudentListFrame.this.modifStudent();
				}
			});
		}
		return this.butModif;
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
			this.jContentPane.add(this.getTopPanel(), BorderLayout.NORTH);
			this.jContentPane.add(this.getJScrollPaneTable(), BorderLayout.CENTER);
			
		}
		return this.jContentPane;
	}

	// =======================================================================
	// Méthodes internes liées aux fonctionnalités du composant
	// =======================================================================
	
	
	/**
	 * Ajout d'un nouveau Student dans la base de données
	 */
	private void ajoutStudent() {

    	boolean popupResult;
		Student stud = new Student();

		// Création ou réutilisation de la boite de dialog "studentDialog"
		// ouverture en mode "ajout"
		// récupération du code de retour (true/false) et des données saisies (stud) 
		popupResult = this.getStudentDialog().ouvrirDialogue( stud, StudentDialog.ModeEdition.CREATION ); 
		
		if ( popupResult) {
			// action validée : "stud" contient les données sur le student à ajouter
			this.studentTM.addStudent(stud);

		} else {
			// action non validée
			JOptionPane.showMessageDialog(this,"ok on ajoute student");
		}
	}
	
	/**
	 * Modification d'un student de la base de données
	 */
	private void modifStudent() {

    	int selectedRow = this.getStudentTable().getSelectedRow();
    	    	
    	if ( selectedRow >= 0 ) {
    		Student stud = this.studentTM.getStudentAt( selectedRow );
    		if ( stud != null ) {

    			boolean  popupResult;

    			// Création ou réutilisation de la boite de dialog "studentDialog"
    			// ouverture en mode "ajout"
    			// récupération du code de retour (true/false) et des données saisies (stud) 
    			popupResult = this.getStudentDialog().ouvrirDialogue( stud, StudentDialog.ModeEdition.MODIFICATION ); 
    			
    			if ( popupResult) {
    				
    				this.studentTM.updateStudentAt(stud,selectedRow);
    				
    			} else {
    				// action non validée
    				JOptionPane.showMessageDialog(this,"ok on ne modifie rien");
    			}
    		}
    	} else {
    		JOptionPane.showMessageDialog(this,"Veuillez sélectionner la ligne à modifier");
    	}
	}

	/**
	 * Suppression d'un student de la base de données
	 */
	private void supprStudent() {

    	int selectedRow = this.getStudentTable().getSelectedRow();
    	
    	if ( selectedRow >= 0 ) {
    		Student stud = this.studentTM.getStudentAt( selectedRow );
    		if ( stud != null ) {

       			boolean  popupResult;
				// Création ou réutilisation de la boite de dialog "studentDialog"
				// ouverture en mode "ajout"
				// récupération du code de retour (true/false) et des données saisies (stud) 
				popupResult = this.getStudentDialog().ouvrirDialogue( stud, StudentDialog.ModeEdition.SUPPRESSION ); 
		
				if ( popupResult) {
					
					this.studentTM.removeStudentAt( selectedRow );
		
				} else {
					// action non validée
					JOptionPane.showMessageDialog(this,"ok on ne supprime student");
				}
    		}
    	}
	}
}