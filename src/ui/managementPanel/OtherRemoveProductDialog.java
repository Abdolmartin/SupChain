package ui.managementPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class OtherRemoveProductDialog extends AbstractAddOrRemoveDialog {
	private JTable itemsTable;
	
	public JTable getItemsTable(){
		return this.itemsTable;
	}

	public OtherRemoveProductDialog(int userID, int productElementID) {
		super(userID, productElementID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 100, 350, 279);
		getContentPane().add(scrollPane);
		
		itemsTable = new JTable();
		scrollPane.setViewportView(itemsTable);
		accept(new LoadAddRemoveDialogVisitor());
	}

	@Override
	public void accept(AddRemoveDialogVisitor v) {
		v.visitOtherRemoveProductDialog(this);
	}

}
