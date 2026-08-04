public class SimulationEngine
{
    private int gridWidth; //Number of points along the horizontal axis
    private int gridHeight; //Number of points along the vertical axis
    private double gridSpacing; //distance between adjacent grid points in metres
    private WaveSource[] sources; //Array of sources through the inputs from the user for each parameter
    private double[][] source1Grid; //2D grid storing intensity values contributed by source 1 alone
    private double[][] source2Grid; //2D grid storing intensity values contributed by source 2 alone
    private double[][] source3Grid; //2D grid storing intensity values contributed by source 3 alone
    private double[][] interferenceGrid; //2D grid storing the final interference intensity at each grid point
    private FormulaExplanation formulaExplanation; //calling the explanation method from the explanation class to
                                                   //make sure client understands the concepts
    private int activeSources; //number of sources which the client wants to test out

    public SimulationEngine(int gridWidth, int gridHeight, WaveSource[] sources, double[][] source1Grid, double[][] source2Grid,
                            double gridSpacing, double[][] source3Grid, double[][] interferenceGrid,
                            FormulaExplanation formulaExplanation, int activeSources)
    {
        this.gridWidth = 10;
        this.gridHeight = 10;
        this.gridSpacing = 0.1;
        this.sources = sources;
        this.source1Grid = source1Grid;
        this.source2Grid = source2Grid;
        this.source3Grid = source3Grid;
        this.interferenceGrid = interferenceGrid;
        this.formulaExplanation = formulaExplanation;
        this.activeSources = activeSources;
    }


    public void calculateIndividualGrids()
    {
        //iterating through every single grid point in the domain
        for(int xIndex = 0; xIndex < gridWidth; xIndex++)
        {
            for(int yIndex = 0; yIndex < gridHeight; yIndex++)
            {
                //converts the grid indices into actual physical coordinates
                double observationX = xIndex * gridSpacing;
                double observationY = yIndex * gridSpacing;
                //Calculating the intensity from source 1 for each of the points in the grid
                source1Grid[xIndex][yIndex] = sources[0].calculateIntensity(observationX, observationY);
                //Calculating the intensity from source 2 for each of the points in the grid
                source2Grid[xIndex][yIndex] = sources[1].calculateIntensity(observationX, observationY);

                if(activeSources == 3) //checks if there is an additional source
                {
                    //Calculating the intensity from source 3 for each of the points in the grid
                    source3Grid[xIndex][yIndex] = sources[2].calculateIntensity(observationX, observationY);
                }
            }
        }
    }

    public void calculateInterferencePattern()
    {
        //iterating through every single grid point in the domain
        for(int xIndex = 0; xIndex < gridWidth; xIndex++)
        {
            for(int yIndex = 0; yIndex < gridHeight; yIndex++)
            {
                //same procedure, converting indices to physical quantities
                double observationX = xIndex * gridSpacing;
                double observationY = yIndex * gridSpacing;

                double totalAmplitude = 0; //initialising the amplitude
                for(int s = 0; s < activeSources; s++)
                {
                    //calculates the totalAmplitude due to the sources present and their wave behaviour at these points
                    totalAmplitude = totalAmplitude + sources[s].calculateDisplacement(observationX, observationY);
                }
                //Intensity ∝ Amplitude² (explained in WaveSource class), hence below multiplication used
                interferenceGrid[xIndex][yIndex] = totalAmplitude * totalAmplitude;
            }
        }
    }

    public void updateInterferenceGrid()
    {
      calculateIndividualGrids();
      calculateInterferencePattern();
    }
    public void updateFormulaExplanation()
    {
        formulaExplanation.updateExplanation(sources, activeSources);
    }


    public int getGridWidth() {
        return gridWidth;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
    }

    public double getGridSpacing() {
        return gridSpacing;
    }

    public void setGridSpacing(double gridSpacing) {
        this.gridSpacing = gridSpacing;
    }

    public WaveSource[] getSources() {
        return sources;
    }

    public void setSources(WaveSource[] sources) {
        this.sources = sources;
    }

    public double[][] getSource1Grid() {
        return source1Grid;
    }

    public void setSource1Grid(double[][] source1Grid) {
        this.source1Grid = source1Grid;
    }

    public double[][] getSource2Grid() {
        return source2Grid;
    }

    public void setSource2Grid(double[][] source2Grid) {
        this.source2Grid = source2Grid;
    }

    public double[][] getInterferenceGrid() {
        return interferenceGrid;
    }

    public void setInterferenceGrid(double[][] interferenceGrid) {
        this.interferenceGrid = interferenceGrid;
    }

    public double[][] getSource3Grid() {
        return source3Grid;
    }

    public void setSource3Grid(double[][] source3Grid) {
        this.source3Grid = source3Grid;
    }

    public FormulaExplanation getFormulaExplanation() {
        return formulaExplanation;
    }

    public void setFormulaExplanation(FormulaExplanation formulaExplanation) {
        this.formulaExplanation = formulaExplanation;
    }

    public int getActiveSources() {
        return activeSources;
    }

    public void setActiveSources(int activeSources) {
        this.activeSources = activeSources;
    }
}
