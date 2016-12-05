package gui.view;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

// Extension de JTable
// Pour tracer simplement deux méthodes :
// - setmodel() => abonnement comme ModelListener au TableModel
// - tableChanged() => prévenu lorsque le TabaleModel fait un fireXxxx (équivalent à un notify)

public class MyVerySimpleJTable extends JTable {
	
	@Override
	public void setModel(TableModel dataModel) {
		
		System.out.println("call of : "+this.getClass().getSimpleName()+" - "+"setModel ("+dataModel+")");
		
		super.setModel(dataModel);
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		
		System.out.println("call of : "+this.getClass().getSimpleName()+" - "+"tableChanged ("+e+")");
		
		super.tableChanged(e);
	}
}
