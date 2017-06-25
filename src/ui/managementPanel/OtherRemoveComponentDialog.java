package ui.managementPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class OtherRemoveComponentDialog extends AbstractAddOrRemoveDialog {
	private JTable itemsTable;
	
	public JTable getItemsTable(){
		return this.itemsTable;
	}

	public OtherRemoveComponentDialog(int userID, int productElementID) {
		super(userID, productElementID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(99, 99, 306, 273);
		getContentPane().add(scrollPane);
		
		itemsTable = new JTable();
		scrollPane.setViewportView(itemsTable);
		accept(new LoadAddRemoveDialogVisitor());
	}

	@Override
	public void accept(AddRemoveDialogVisitor v) {
		v.visitOtherRemoveComponentDialog(this);
	}
}
