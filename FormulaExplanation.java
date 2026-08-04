public class FormulaExplanation
{
    private String changedParameter;
    private String baseFormula;
    private String modifiedFormula;
    private String explanationText;

    public FormulaExplanation(String changedParameter, String baseFormula, String modifiedFormula,
                              String explanationText)
    {
        this.changedParameter = changedParameter;
        this.baseFormula = baseFormula;
        this.modifiedFormula = modifiedFormula;
        this.explanationText = explanationText;
    }
    //Yet to comment the entire updateExplanation class since I might change the variable name to make it easier to understand
    public void updateExplanation(WaveSource[] sources, int activeSources)
    {
        // Base interference formula (used in all explanations)
        baseFormula = "I ∝ (A₁ sin(k₁x − ω₁t + φ₁) + A₂ sin(k₂x − ω₂t + φ₂))²"; //where φ is the phase shift of the wave (other variables stated in WaveSource class)

        if(activeSources == 3)
        {
            baseFormula =
                    "I ∝ (A₁ sin(k₁x − ω₁t + φ₁) + A₂ sin(k₂x − ω₂t + φ₂) + A₃ sin(k₃x − ω₃t + φ₃))²"; //need 3 different values hence different
        }

        boolean phaseDifferenceExists = false; //initialises the phse shift of the waves as 0
        double referencePhase = sources[0].getPhaseDifference(); //sets the referencePhase variable as the phase shift from the first source

        for(int i = 1; i < activeSources; i++)
        {
            if(Math.abs(sources[i].getPhaseDifference() - referencePhase) > Math.pow(10, -6)) //when the phase shift is not insignificant
            {
                phaseDifferenceExists = true; //there is a phase difference which exists
                break;
            }
        }

        if(phaseDifferenceExists)
        {
            changedParameter = "Phase Difference (φ)"; //the parameter which ahs been change

            modifiedFormula =
                    "Δφ ≠ 0 ⇒ path dependent superposition of waves"; //superposition is the same as inteference

            explanationText = //explains the entire relation of phase shift in a wave and its impact on the inteference pattern observed
                    "The wave sources have different phase differences, meaning the crests and troughs of " +
                            "the waves do not arrive at each point simultaneously. When the phase difference is close " +
                            "to 0 or multiples of 2π, constructive interference occurs, producing regions of maximum " +
                            "intensity. When the phase difference approaches π, destructive interference occurs, " +
                            "reducing or cancelling the resultant intensity. This explains the alternating bright " +
                            "and dark fringes observed in the interference pattern.";
            return;
        }

        boolean wavelengthDifferenceExists = false;
        double referenceLambda = sources[0].getWavelength();

        for(int i = 1; i < activeSources; i++)
        {
            if(Math.abs(sources[i].getWavelength() - referenceLambda) > 1e-6)
            {
                wavelengthDifferenceExists = true;
                break;
            }
        }

        if(wavelengthDifferenceExists)
        {
            changedParameter = "Wavelength (λ)";

            modifiedFormula =
                    "k = 2π / λ ⇒ changing λ alters spatial phase variation";

            explanationText =
                    "Different wavelengths result in different wave numbers (k = 2π/λ), which directly " +
                            "affect how rapidly the phase of each wave changes with position. As a result, the spacing " +
                            "between regions of constructive interference changes. Shorter wavelengths produce more " +
                            "closely spaced interference fringes, while longer wavelengths increase fringe spacing.";
            return;
        }

        boolean frequencyDifferenceExists = false;
        double referenceFrequency = sources[0].getFrequency();

        for(int i = 1; i < activeSources; i++)
        {
            if(Math.abs(sources[i].getFrequency() - referenceFrequency) > 1e-6)
            {
                frequencyDifferenceExists = true;
                break;
            }
        }

        if(frequencyDifferenceExists)
        {
            changedParameter = "Frequency (f)";

            modifiedFormula =
                    "ω = 2πf ⇒ different angular frequencies";

            explanationText =
                    "When wave sources have different frequencies, their angular frequencies differ, " +
                            "causing the relative phase between waves to vary with time. This leads to a time-dependent " +
                            "interference pattern where regions of constructive and destructive interference shift " +
                            "continuously, producing a dynamic or 'beating' effect in the observed intensity.";
            return;
        }
        boolean waveTypeDifferenceExists = false;
        String referenceType = sources[0].getWaveType();

        for(int i = 1; i < activeSources; i++)
        {
            if(!sources[i].getWaveType().equalsIgnoreCase(referenceType))
            {
                waveTypeDifferenceExists = true;
                break;
            }
        }

        if(waveTypeDifferenceExists)
        {
            changedParameter = "Wave Function Type";

            modifiedFormula =
                    "cos(θ) = sin(θ + π/2)";

            explanationText =
                    "A cosine wave is equivalent to a sine wave with a phase shift of π/2. Therefore, " +
                            "choosing different wave functions introduces an effective phase difference between " +
                            "sources. This alters the interference condition at each point, changing whether waves " +
                            "reinforce or cancel one another, even if all other parameters remain constant.";
            return;
        }

        changedParameter = "None";

        modifiedFormula =
                "Identical waves ⇒ stable constructive interference";

        explanationText =
                "All wave sources share identical parameters, including wavelength, frequency, phase " +
                        "difference, and wave function. As a result, the waves remain in phase across the grid, " +
                        "producing a stable interference pattern dominated by constructive interference and " +
                        "maximised intensity regions.";
    }

    public String getChangedParameter()
    {
        return changedParameter;
    }

    public void setChangedParameter(String changedParameter) {
        this.changedParameter = changedParameter;
    }

    public String getBaseFormula() {
        return baseFormula;
    }

    public void setBaseFormula(String baseFormula) {
        this.baseFormula = baseFormula;
    }

    public String getModifiedFormula() {
        return modifiedFormula;
    }

    public void setModifiedFormula(String modifiedFormula) {
        this.modifiedFormula = modifiedFormula;
    }

    public String getExplanationText() {
        return explanationText;
    }

    public void setExplanationText(String explanationText) {
        this.explanationText = explanationText;
    }
}
