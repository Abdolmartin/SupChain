package productionEntities;
import sun.plugin.javascript.navig.Array;

import java.util.ArrayList;

/**
 * Created by asus on 5/22/2017.
 */
public class ProductionProcess {
    String name;
    ArrayList<String> collaboratingDepartments;

    ArrayList<ProcessParticipant> inputs;
    ArrayList<ProcessParticipant> outputs;

    public String getName() {
        return name;
    }

    public ArrayList<String> getCollaboratingDepartments() {
        return collaboratingDepartments;
    }

    public ArrayList<ProcessParticipant> getInputs() {
        return inputs;
    }

    public ArrayList<ProcessParticipant> getOutputs() {
        return outputs;
    }
}
