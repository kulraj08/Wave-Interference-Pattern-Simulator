public class Main {
    public static void main(String[] args)
    {
        UserInterface ui = new UserInterface(0,false ,
                null, null, null, null);

        ui.inputData(); //collect the data inputted by the user



        int activeSources = ui.getNumberOfSources(); //Get the number of sources the user wants to test

        WaveSource[] sources = new WaveSource[activeSources]; //creates an array of length of the number of sources by the user
        for(int i=0; i < activeSources; i++)
        {
            //Getters used in order to fetch the inputs of the specific parameters from the user
            //Initialisation of 1.0, 0.0, 0.0, are the initialisation of amplitude, xPosition and yPosition parameters
            sources[i]= new WaveSource(ui.getWavelengthInputs()[i], ui.getFrequencyInputs()[i], ui.getPhaseDifferenceInputs()[i],
                    1.0, 0.0, 0.0, ui.getFunctionType()[i]);
        }


        if(ui.isShowInterferenceGrid())  //If the user says yes to view the interference grids
        {
            //creating a graph window (same sizing and spacing as the ShowIndividualGrids
            new GridSimulation("interference Pattern", sources, activeSources,1000,0.1);
        }


        //Creating the grid for the simulation calculations (intensity of each source and intensity of the interference wave)
        int gridWidth = 10; //Dont know if to keep or remove
        int gridHeight = 10; //same for this
        double[][] source1Grid = new double[gridWidth][gridHeight];
        double[][] source2Grid = new double[gridWidth][gridHeight];
        double[][] source3Grid = new double[gridWidth][gridHeight];
        double[][] interferenceGrid = new double[gridWidth][gridHeight];

        FormulaExplanation formulaExplanation = new FormulaExplanation( "", "", "", "");
        SimulationEngine engine = new SimulationEngine(gridWidth, gridHeight, sources, source1Grid, source2Grid, 0.001,
                            source3Grid, interferenceGrid, formulaExplanation, activeSources);

        //Run the simulation
        engine.updateInterferenceGrid();
        engine.updateFormulaExplanation();
        ui.drawInterferenceGrid();
        new GridSimulation("Wave Interference Simulation", sources, activeSources, 1000, 0.1);
        boolean viewExplanation = ui.askViewFormulaExplanation();
        FormulaExplanation formula = engine.getFormulaExplanation();
        System.out.print(" \n Formula Explanation:");
        System.out.print("Changed Parameter: " + formula.getChangedParameter());
        System.out.print("Base Formula: " + formula.getBaseFormula());
        System.out.print("Modified Formula: " + formula.getModifiedFormula());
        System.out.print("\nExplanation: " + formula.getExplanationText());

        // End of program
        System.out.print("Simulation completed successfully");
    }
}
