import java.util.Scanner;
public class UserInterface {
    private Scanner input = new Scanner(System.in);
    private int numberOfSources; //the number of light sources that the client would like to be tested
    private boolean showInterferenceGrid; //asking the user if he wants to view the interference pattern
    private double[] wavelengthInputs; //the inputted wavelength for the sources (nm)
    private double[] frequencyInputs; //the inputted frequency for the sources (Hz)
    private double[] phaseDifferenceInputs; //the initial phase of the sources to be inputted (radians)
    private String[] functionType; //the type of function that the client choses for that source

    public UserInterface( int numberOfSources, boolean showInterferenceGrid,
                         double wavelengthInputs[], double frequencyInputs[], double[] phaseDifferenceInputs, String[] functionType) {
        this.numberOfSources = numberOfSources;
        this.showInterferenceGrid = showInterferenceGrid;
        this.wavelengthInputs = wavelengthInputs;
        this.frequencyInputs = frequencyInputs;
        this.phaseDifferenceInputs = phaseDifferenceInputs;
        this.functionType = functionType;
    }

    public void inputData() {
        //Asking the client to input the number of sources which they would like to test
        System.out.print("Enter number of light sources you would like to test interference pattern with");
        numberOfSources = input.nextInt(); //client inputting the number of sources to be tested

        while (numberOfSources > 3 || numberOfSources < 2) //checking if the input is valid or not for IBDP HL Physics
        {
            //Error message displayed if the entered number of sources is more than or less than what the syllabus covers
            System.out.print("Invalid input, you need minimum 2 and maximum 3 light sources for HL Physics interference pattern");
            numberOfSources = input.nextInt(); //asks the client to enter the valid number of sources, will continue to ask till the time correct number not entered
        }
        wavelengthInputs = new double[numberOfSources]; //Creating an array of wavelengths to store the value of wavelengths for the respective sources
        frequencyInputs = new double[numberOfSources]; //Creating an array of frequency to store the value of frequency for the respective sources
        phaseDifferenceInputs = new double[numberOfSources]; //Creating an array for phases of each source to store the value of phase for the respective sources
        functionType = new String[numberOfSources]; //Creating an array for types of functions to be stored for the respective sources

        //for loop will be used to ask the client for the input of each parameter which will be stored in the array for each source
        for (int i = 0; i < numberOfSources; i++) {
            System.out.print("Source" + (i + 1));

            System.out.print("Enter wavelength value in nm: "); //asks for the wavelength value for Source1 in nm
            wavelengthInputs[i] = input.nextDouble();//client to input the wavelength value for Source1 in nm

            while (wavelengthInputs[i] < 0 || wavelengthInputs[i] == 0) {
                //Error message displayed if the entered wavelength is invalid which is 0nm or less
                System.out.print("Invalid input, wavelength cannot be negative or a zero value, please enter a greater than 0 value");
                wavelengthInputs[i] = input.nextInt(); //asks the client to enter the valid wavelengthInputs, will continue to ask till the time correct number not entered
            }

            System.out.print("Enter frequency value in Hz: "); //asks for the frequency value for Source1 in Hz
            frequencyInputs[i] = input.nextDouble(); //client to input the frequency value for Source1 in Hz

            while (frequencyInputs[i] < 0 || frequencyInputs[i] == 0) {
                //Error message displayed if the entered frequency is invalid which is 0 Hz or less
                System.out.print("Invalid input, Frequency cannot be negative or a zero value, please enter a greater than 0 value");
                frequencyInputs[i] = input.nextInt(); //asks the client to enter the valid frequencyInputs, will continue to ask till the time correct number not entered
            }
            //asks for the type of function which the client would like to test for that particular source
            //options will be presented to the client in order for him to chose between these function types
            System.out.print("Enter the number for the selected type of function to be tested for Source" + (i + 1) + ":");
            System.out.print("1. Cosine");
            System.out.print("2. -Cosine");
            System.out.print("3. Sine");
            System.out.print("4. -Sine");

            int choice = input.nextInt(); //client inputs the number for the chosen function they would like the source to represent

            while (choice < 1 || choice > 4) //validating that it is within the limits
            {
                //Error message to be displayed if it is not within the limit
                System.out.print("Invalid choice. For IB HL Physics, only these functions are required. Please select from 1-4");
                choice = input.nextInt(); //asking the client to input again, continued till the client inputs the correct value
            }
            //checks what choice the client has chosen and sets the functionType parameter to that type of function
            //useful when explaining the formula and to calculate the amplitude and type of interference generated from the sources
            if (choice == 1) {
                functionType[i] = "cos";
            } else if (choice == 2) {
                functionType[i] = "-cos";
            } else if (choice == 3) {
                functionType[i] = "sin";
            } else {
                functionType[i] = "-sin";
            }

            //asks the client for the phase shift to the function of the source chosen
            System.out.print("Enter phase shift of the source from its original function in radians ");
            phaseDifferenceInputs[i] = input.nextDouble();//client inputs the phase shift for their function of the source
            while (phaseDifferenceInputs[i] < -2*Math.PI || phaseDifferenceInputs[i] > 2 * Math.PI)
            {
                //Error message displayed if the entered phase shift is invalid which is less than -2π or more than 2π
                System.out.print("Invalid input, phase shift repeats itself after -2π and 2π, hence input a value in between these ranges");
                phaseDifferenceInputs[i] = input.nextDouble(); //asks the client to enter the valid phase shift, will continue to ask till the time correct number not entered
            }
        }
    }

    public void drawInterferenceGrid() {
        //asks the client if he would like to view the interference grid of the sources
        System.out.print("Draw the interference pattern between the sources selected? (true/false): ");
        showInterferenceGrid = input.nextBoolean(); //client chooses if he would like to view the grid or not
    }

    public boolean askViewFormulaExplanation() {
        System.out.print("View formula explanation? (true/false): ");
        return input.nextBoolean();
    }

    public int getNumberOfSources() {
        return numberOfSources;
    }

    public void setNumberOfSources(int numberOfSources) {
        this.numberOfSources = numberOfSources;
    }

    public boolean isShowInterferenceGrid() {
        return showInterferenceGrid;
    }

    public void setShowInterferenceGrid(boolean showInterferenceGrid) {
        this.showInterferenceGrid = showInterferenceGrid;
    }

    public double[] getWavelengthInputs() {
        return wavelengthInputs;
    }

    public void setWavelengthInputs(double[] wavelengthInputs) {
        this.wavelengthInputs = wavelengthInputs;
    }

    public double[] getFrequencyInputs() {
        return frequencyInputs;
    }

    public void setFrequencyInputs(double[] frequencyInputs) {
        this.frequencyInputs = frequencyInputs;
    }

    public double[] getPhaseDifferenceInputs() {
        return phaseDifferenceInputs;
    }

    public void setPhaseDifferenceInputs(double[] phaseDifferenceInputs) {
        this.phaseDifferenceInputs = phaseDifferenceInputs;
    }

    public String[] getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String[] functionType) {
        this.functionType = functionType;
    }
}
