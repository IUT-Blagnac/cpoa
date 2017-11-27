package gui.model;


import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import data.model.Student;

public class StudentTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private List<Student> studentlist = null;

	/**
	 * Constructeur.
	 */ 
	public StudentTableModel(  ) {
		this.studentlist = new ArrayList<>();
	}
	
	// =======================================================================
	// Surcharges des méthodes abstraites de la classe AbstractTableModel
	// =======================================================================
	
	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		int nbRow = 0;
		if (this.studentlist!=null) {
			nbRow = this.studentlist.size();
		}
		return nbRow;
	}

	public Object getValueAt(int lig, int col) {
		if (this.studentlist==null) { return "!!!"; }

			switch (col) {
			case 0:
				return ""+this.studentlist.get(lig).id;
			case 1:
				return this.studentlist.get(lig).surname;
			case 2:
				return this.studentlist.get(lig).firstname;
			case 3:
				return ""+this.studentlist.get(lig).TPgroup;
			default:
				return "???";
			}

	}
	
	// =======================================================================
	// Surcharges des méthodes prédéfinies de la classe AbstractTableModel
	// =======================================================================

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "ID";
		case 1:
			return "Name";
		case 2:
			return "Firstname";
		case 3:
			return "Group";
		default:
			return "???";
		}
	}
	
	@Override
	public boolean isCellEditable(int lig, int col) {
		if (col>=1 && col<=3) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void setValueAt(Object valeur, int lig, int col) {
		if ( this.studentlist != null ) {

			if (this.studentlist.size()>lig) {
				Student stud = this.studentlist.get(lig);
				switch (col) {
				case 1:
					if (! valeur.toString().isEmpty())
						stud.surname = valeur.toString();
					break;
				case 2:
					if (! valeur.toString().isEmpty())
						stud.firstname = valeur.toString();
					break;
				case 3:
					if (valeur.toString().isEmpty())
						break;
					String s = valeur.toString().toUpperCase();
					if ( s.length() != 2 
							||  ! (""+s.charAt(0)).matches("1|2|3|4")
							||  ! (""+s.charAt(1)).matches("A|B")
						)
							break;
					stud.TPgroup = s;
					break;
				default:
					return ; // les autres colonnes ne sont pas éditables
				}
				this.fireTableCellUpdated(lig, col);
			}
		}
		// transmission de l'information de modification du modèle vers la vue
		this.fireTableCellUpdated(lig, col);
		
	}
	
	// =======================================================================
	// Méthodes spécifiques à la classe StudentTableModel
	// =======================================================================
	/**
	 * Lecture (ou relecture forcée) des données dans la base
	 * 
	 * @param listeDonnees	  ArrayList contenant les students à présenter dans la table
	 */
	public void loadDatas( List<Student> listeDonnees ) {
		this.studentlist = listeDonnees;
		this.fireTableDataChanged();
	}
	
	/**
	 * Ajouter un student dans le modèle et dans la table.
	 */
	public void addStudent( Student stud ) {
		if (this.studentlist == null) {
			this.studentlist = new ArrayList<Student>();
		}
		this.studentlist.add(stud);
		
		this.fireTableDataChanged();
	}
	
	/**
	 * Retourner la copie d'un élément de type Student représentant l'enregistrement de la ligne "lig".
	 * 
	 * @param lig numéro de la ligne (dans la table).
	 * 
	 * @return l'élément concerné ou NULL en cas d'erreur
	 */
	public Student getStudentAt(int lig) {
		Student stud = null;
		if ( this.studentlist != null ) {
			if (this.studentlist.size()>lig) {
				stud = new Student(this.studentlist.get(lig));
			}
		}
		return stud;
	}
	
	/**
	 * Supprimer de la base et du modèle le student situé à la ligne "lig"
	 * @param lig n° de la ligne a supprimée (ce n'est pas l'identifiant du champ)
	 * 
	 * @return true si la suppression a bien été réalisée
	 */
	public boolean removeStudentAt(int lig) {
		if ( this.studentlist != null ) {
			if (this.studentlist.size()>lig) {
				this.studentlist.remove(lig);
				this.fireTableRowsUpdated(lig, this.studentlist.size());
				return true;
			}
		}
		return false;
	}

	/**
	 * Modifier les données d'un student présenté dans le modèle.
	 * 
	 * @param studentUpated données du student à mettre a jour
	 * @param lig       n° de ligne dans le modèle.
	 *
	 * @return true si la modification a bien été réalisée.
	 */
	public boolean updateStudentAt( Student studentUpated, int lig ) {
		if ( this.studentlist != null ) {
			if (this.studentlist.size()>lig) {
				this.studentlist.set(lig, studentUpated);
				this.fireTableRowsUpdated(lig, lig);
				return true;
			}
		}
		return false;
	}

}
