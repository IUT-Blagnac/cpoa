package gui.model;


import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import data.model.Student;

/**
 * @author Andr� PENINOU
 * @author Fabrice PELLEAU
 */

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
	// Surcharges des m�thodes abstraites de la classe AbstractTableModel
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
	// M�thodes sp�cifiques � la classe StudentTableModel
	// =======================================================================
	/**
	 * Lecture (ou relecture forc�e) des donn�es dans la base
	 * 
	 * @param listeDonnees	  ArrayList contenant les students � pr�senter dans la table
	 */
	public void loadDatas( List<Student> listeDonnees ) {
		this.studentlist = listeDonnees;
		this.fireTableDataChanged();
	}
	
	
	/**
	 * Retourner la copie d'un �l�ment de type Student repr�sentant l'enregistrement de la ligne "lig".
	 * 
	 * @param lig num�ro de la ligne (dans la table).
	 * 
	 * @return l'�l�ment concern� ou NULL en cas d'erreur
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
}
