package ui.salesPanel;

import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import ui.GeneralSearchDialog;
import ui.handler.SalesAndSupplyFacade;

public class FinalProductSearchDialog extends GeneralSearchDialog {

	public FinalProductSearchDialog(int userID) {
		super(userID);
		
		this.setBounds(500, 500, 500, 500);
		this.setVisible(true);
		
	}

	@Override
	protected void submitSearch() {
		String name = nameField.getText();
		double low, high;
		if (this.lowerPriceField.getText().equals(""))
			low = 0.0;
		else{
			try{
				low = Double.valueOf(this.lowerPriceField.getText());
			} catch (Exception e){
				JOptionPane.showMessageDialog(this, "مقدار فیلد باید عدد یا تهی باشد.");
				return;
			}
		}
		if (this.upperPriceField.getText().equals(""))
			high = Double.MAX_VALUE;
		else{
			try{
				high = Double.valueOf(this.upperPriceField.getText());
			} catch (Exception e){
				JOptionPane.showMessageDialog(this, "مقدار فیلد باید عدد یا تهی باشد.");
				return;
			}
		}
		SalesAndSupplyFacade salesAndSupplyFacade = new SalesAndSupplyFacade();
		List<JSONObject> results = salesAndSupplyFacade.finalProductSearch(name, low, high);
		this.setVisible(false);
		new FinalProductSearchResultDialog(userID, results);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

}
