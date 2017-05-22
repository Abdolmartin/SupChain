package productionEntities;

import java.util.ArrayList;

/**
 * Created by asus on 5/22/2017.
 */
public abstract class ProductElement {
    String name;
    boolean finalProduct;
    ArrayList<ProductElementItem> productInstances;

    ArrayList<ProductionProcess> getConsumerProcesses(ArrayList<ProductionProcess> allProcesses){
        ArrayList<ProductionProcess> consumers = new ArrayList<ProductionProcess>();
        for (int i=0;i<allProcesses.size();i++){
            ProductionProcess currentProcess = allProcesses.get(i);
            ArrayList<ProcessParticipant> currentInputs = currentProcess.getInputs();
            for (int j=0;j<currentInputs.size();j++){
                if (this.equals(currentInputs.get(j).getProductElement()))
                    consumers.add(currentProcess);
            }
        }
        return consumers;
    }
    ArrayList<ProductionProcess> getProducerProcesses(ArrayList<ProductionProcess> allProcesses){
        ArrayList<ProductionProcess> producers = new ArrayList<ProductionProcess>();
        for (int i=0;i<allProcesses.size();i++){
            ProductionProcess currentProcess = allProcesses.get(i);
            ArrayList<ProcessParticipant> currentOutputs = currentProcess.getOutputs();
            for (int j=0;j<currentOutputs.size();j++){
                if (this.equals(currentOutputs.get(j).getProductElement()))
                    producers.add(currentProcess);
            }
        }
        return producers;
    }

    boolean isFinalProduct(){
        return this.finalProduct;
    }
}
