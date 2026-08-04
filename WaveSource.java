public class WaveSource
{
    private double wavelength; //wavelength of the wave (in nm)
    private double frequency; // Frequency of the wave (Hz)
    private double phaseDifference; //initial phase of this source (radians)
    private double amplitude; // Amplitude (maximum y value) of the wave
    private double xPosition; // x-coordinate of the source on the simulation grid
    private double yPosition; // y-coordinate of the source on the simulation grid
    private String waveType; //type of sinusoidal wave user required: Sine or Cosine

    public WaveSource(double wavelength, double frequency, double phaseDifference, double amplitude,
                      double xPosition, double yPosition, String waveType)
    {
        this.wavelength = wavelength;
        this.frequency = frequency;
        this.phaseDifference = phaseDifference;
        this.amplitude = amplitude;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.waveType = waveType;

    }
    public double calculateDisplacement(double observationX, double observationY)
    {
        //Displacement describes how far the wave oscillates from its equilibrium position at a point
        //Calculates the displacement from the wave source to a point on the screen
        // this displacement determines the path difference
        //Distance between 2 points equation is used for the calculations of displacement:
        double distance = Math.sqrt(Math.pow(observationX - xPosition, 2) + Math.pow(observationY - yPosition, 2));
        //the variable k represents how fast the phase changes with distance of the wave often called angularWavenumber:
        // shorter wavelength results in larger k and more rapid phase change
        // In physics terminology, k expresses the number of radians in a unit distance
        // using the relationship used in wave phenomena in physics: k = 2π/λ where λ is the wavelength in nm of the source
        double k = (2 * Math.PI)/wavelength;
        // below equation calculates the phase change due to the distance travelled
        //the phaseDifference accounts for initial phase offset between the sources
        double phase = k * distance + phaseDifference;
        //Uses sinusoidal wave model to calculate the instantaneous wave displacement
        //sin used if the model asked for is Sine; cos used if the model asked for is Cosine
        // Displacement(x, y) = A * sin(phase) where A is amplitude (formula used in physics)
        if(waveType.equalsIgnoreCase("COSINE")) //checks if the chosen wave is Cosine
        {
            return amplitude * Math.cos(phase);
        }
        else if (waveType.equalsIgnoreCase("-COSINE")) //checks if the chosen wave is negative Cosine
        {
            return -amplitude * Math.cos(phase);
        }
        else if (waveType.equalsIgnoreCase("SINE")) //checks if the chosen wave is Sine
        {
            return amplitude * Math.sin(phase);
        }
        else
        {
            return -amplitude * Math.sin(phase); //If the chosen wave is negative Sine, this is returned
        }
        //the entire method returns the phase displacement
    }

    public double calculateIntensity(double x, double y)
    {
        double displacement = calculateDisplacement(x,y);
        //Intensity in wave phenomena is the measure of energy per unit area carried by a wave
        //Hence it is given by the equation: Intensity ∝ Amplitude²
        //Since the displacement at a point already includes the effect of amplitude and phase, squaring it will also give local intensity:
        return displacement * displacement;
    }
    public double getWavelength() {
        return wavelength;
    }

    public void setWavelength(double wavelength) {
        this.wavelength = wavelength;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getPhaseDifference() {
        return phaseDifference;
    }

    public void setPhaseDifference(double phaseDifference) {
        this.phaseDifference = phaseDifference;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getxPosition() {
        return xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public String getWaveType() {
        return waveType;
    }

    public void setWaveType(String waveType) {
        this.waveType = waveType;
    }
}

