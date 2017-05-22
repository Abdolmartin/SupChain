package productionEntities;

import java.util.Date;

/**
 * Created by asus on 5/22/2017.
 */
public class ProductElementItem {
    ProductElement itemType;
    ElementItemStatus status;

    public ProductElementItem(ProductElement itemType, ElementItemStatus status) {
        this.itemType = itemType;
        this.status = status;
    }

    public ElementItemStatus getStatus() {
        return status;
    }

    public void setStatus(ElementItemStatus status) {
        //TODO checking compatibility between the type and the status
        this.status = status;
    }
}
