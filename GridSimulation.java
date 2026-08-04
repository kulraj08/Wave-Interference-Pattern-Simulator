import javax.swing.JFrame; //JFrame is part of the JFreechart import which allows the window to be created

//All the below imports are also from the JFreechart library which will allow in processing the calculated values into a graphical representation
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GridSimulation extends JFrame //in order to display the graph in another window instead of a terminal
{
    private WaveSource[] sources;
    private int activeSources;

    private int gridSize; //distance between adjacent grid points in metres
    private double spacing; //the spacing between consecutive points along the x axis (which will be the axis of phase in radians)

    public GridSimulation(String title,
                          WaveSource[] sources,
                          int activeSources,
                          int gridSize,
                          double spacing) {
        super(title); //Calls the JFrame constructor and sects the title for the graph

        this.sources = sources;
        this.activeSources = activeSources;
        this.gridSize = gridSize;
        this.spacing = spacing;

        JFreeChart chart = createWaveChart(); //creating a chart which will visualise the wave displacement
        ChartPanel panel = new ChartPanel(chart); // ChartPanel is a Swing component that allows a JFreeChart to be embedded inside a JFrame window
        panel.setPreferredSize(new java.awt.Dimension(1000, 1000)); //sets the size of the window

        setContentPane(panel); //Puts the chartPanel inside the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //basically to end the program once the window of this is closed
        pack(); //used to automatically size to fit the waves in case one is bigger or smaller and cant fit completely in the window
        setLocationRelativeTo(null); //centers the window on the screen once opened
        setVisible(true); //makes it visible to the user when running this program
    }

    private JFreeChart createWaveChart() {
        // XYSeriesCollection stores multiple XYSeries objects with each XYSeries representing one wave plotted on the same graph
        XYSeriesCollection dataset = new XYSeriesCollection();

        for (int s = 0; s < activeSources; s++) {
            XYSeries sourceSeries = new XYSeries("Source " + (s + 1));

            for (int i = 0; i < gridSize; i++) {
                //converting the index into a proper real physical distance
                double phase = (2 * Math.PI * i) / gridSize; //Converting the index into a proper real physical phase of max 2π (since its one revolution)
                double shiftedPhase = phase + sources[s].getPhaseDifference(); //using the sources phase shift with the phase from index calculations found

                double displacement; //using a displacement based intensity directly from the phase

                // Recreating wave using phase directly to get more accurate results
                if (sources[s].getWaveType().equalsIgnoreCase("COSINE"))
                    displacement = Math.cos(shiftedPhase);
                else if (sources[s].getWaveType().equalsIgnoreCase("-COSINE"))
                    displacement = -Math.cos(shiftedPhase);
                else if (sources[s].getWaveType().equalsIgnoreCase("SINE"))
                    displacement = Math.sin(shiftedPhase);
                else
                    displacement = -Math.sin(shiftedPhase);

                // Intensity ∝ displacement²
                double intensity = displacement * displacement; //calculates the intensity of the sources

                sourceSeries.add(phase, intensity); //adding the phase (x axis) and the intensity (y axis) of the sources
            }

            dataset.addSeries(sourceSeries);
        }

        XYSeries interferenceSeries = new XYSeries("Interference Wave");

        for (int i = 0; i < gridSize; i++) {
            double totalDisplacement = 0;
            double phase = (2 * Math.PI * i) / gridSize;
            for (int s = 0; s < activeSources; s++) {
                double shiftedPhase = phase + sources[s].getPhaseDifference(); //phaseDifference based on the first source
                double displacement; //initialises displacement as 0 (in order to keep adding to this value for the ineterference pattern curve)
                if (sources[s].getWaveType().equalsIgnoreCase("COSINE"))
                    displacement = Math.cos(shiftedPhase);
                else if (sources[s].getWaveType().equalsIgnoreCase("-COSINE"))
                    displacement = -Math.cos(shiftedPhase);
                else if (sources[s].getWaveType().equalsIgnoreCase("SINE"))
                    displacement = Math.sin(shiftedPhase);
                else
                    displacement = -Math.sin(shiftedPhase);

                totalDisplacement = totalDisplacement + displacement; //calculating the total displacement from the wavesources
            }
            double totalIntensity = totalDisplacement * totalDisplacement;
            interferenceSeries.add(phase, totalIntensity);
        }

        dataset.addSeries(interferenceSeries);

        return ChartFactory.createXYLineChart( //this is used in order to create a XY (like a scatter plot but without points) line chart to
                // represent the behaviour of wave
                "Wave Interference",
                "Phase (radians)",
                "Intensity",
                dataset,
                PlotOrientation.VERTICAL,
                true, //displays the legend by assigning each source and the inteference pattern line with a colour
                false,
                false
        );
    }
}
