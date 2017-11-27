package gui.model;


import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class SimpleTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	// Les datas : un tableau 2D d'entiers.
	
	private int [][] values;
	/**
	 * Constructeur.
	 * Initialisation des datas : 8 lignes de 3 colonnes 
	 */ 
	public SimpleTableModel(  ) {
		this.values =  new int[][] {
				{11, 12, 13},
				{21, 22, 23},
				{31, 32, 33},
				{41, 42, 43},
				{51, 52, 53},
				{61, 62, 63},
				{71, 72, 73},
				{81, 82, 83}
		};
	}
	
	// =======================================================================
	// Surcharges des méthodes abstraites de la classe AbstractTableModel
	// =======================================================================
	
	@Override
	public int getColumnCount() {
		System.out.println("call of : "+this.getClass().getSimpleName()+" - "+"getColumnCount()");
		return this.values[0].length;
	}
	
	@Override
	public int getRowCount() {
		System.out.println("call of : "+this.getClass().getSimpleName()+" - "+"getRowCount()");
		return this.values.length;
	}

	@Override
	public Object getValueAt(int lig, int col) {
		System.out.println("call of : "+this.getClass().getSimpleName()+" - "+"getValueAt("+lig+", "+col+")");
		return this.values [lig][col]; 
	}

	
	// =======================================================================
	// Surcharges des méthodes prédéfinies de la classe AbstractTableModel
	// =======================================================================

	@Override
	public String getColumnName(int columnIndex) {
		System.out.println("call of : "+this.getClass().getSimpleName()+" - "+"getColumnName("+columnIndex+")");
		if (columnIndex < this.values[0].length)
			return "Col : "+columnIndex;
		else
			return "?????";
	}
	
	@Override
	public boolean isCellEditable(int lig, int col) {
		System.out.println("call of : "+this.getClass().getSimpleName()+" - "+"isCellEditable("+lig+", "+col+")");
		return true;
	}

	@Override
	public void setValueAt(Object valeur, int lig, int col) {
		System.out.println("call of : "+this.getClass().getSimpleName()+" - "+"setValueAt("+valeur+", "+lig+", "+col+")");
		try {
			this.values[lig][col] = Integer.parseInt(valeur.toString());
		} catch (NumberFormatException nfe) {}
		// transmission de l'information de modification du modèle vers la vue
		this.fireTableCellUpdated(lig, col);	
	}
	
	@Override
	// Pour test des abonnements de JTable sur le TableModel
	
	public void addTableModelListener(TableModelListener l) {
		System.out.println("call of : "+this.getClass().getSimpleName()+" - "+"addTableModelListener("+l+")");
		
		super.addTableModelListener(l);
	}
}
