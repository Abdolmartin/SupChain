package productionEntities;

/**
 * Created by asus on 5/22/2017.
 */
public class ProcessParticipant {
    ProductElement productElement;
    int count;

    public ProcessParticipant(ProductElement productElement, int count) {
        this.productElement = productElement;
        this.count = count;
    }

    public ProductElement getProductElement() {
        return productElement;
    }

    public int getCount() {
        return count;
    }
}
