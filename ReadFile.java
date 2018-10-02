import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * This class holds the main method as well as readFile()
 * 
 * main() collects command line arguments and calls readFile()
 * with the input file location as String.
 * 
 * @input: arg[0] is output file location
 * @input: arg[>0] are input file location(s)
 * 
 * @end state: a file is written containing the results of the tests
 * to the location specified. Results are also printed to the screen
 */
public class ReadFile {

    String outFileLocation;

    /*
     * This method simply collects the command line args and passes the 
     * input file locations to readFile() to begin calculations.
     * 
     * @args: arg[0] is output file location 
     * @args: arg[>0] is input file location(s) It will loop through all 
     * input files and return results in one output file at the 
     * specified location.
     * 
     */
    public static void main(String[] args)
    {
        ReadFile readF = new ReadFile(); 
        readF.outFileLocation = args[0]; // where to write the file

        // begin program for all input files; n is number of inputs
        // n = 0 is output file, so skip that here
        for (int n = 1; n < args.length; n++) {
            readF.readFile(args[n]);
        }
    }

    /*
     * This method interprets the input file data
     * 
     * First, it collects one line at a time from input file and determine if
     * the input is part of a matrix, or if it is the order of a matrix.
     * It will make this categorization based on whether there is a " " in the
     * input. Matrix elements contain spaces while matrix orders do not.
     * 
     * Input data will be stored in a linked list of linked lists.
     * 
     * After collecting and storing the input for one matrix with its order
     * information, it will pass the matrix to getDeterminant in Determinant
     * class.
     * 
     * @args: inputFile location of input data
     * 
     * @output: print statement displaying the matrix order on screen and to
     * output file
     * 
     * @output: print statements for error checking
     * 
     * @output: print statement to display the final matrix determinant, 
     * returned from getDeterminant
     * 
     * @return: none
     * 
     */
    public void readFile(String inputFile)
    {
        WriteFile wr = new WriteFile(); // reference to class
        Determinant deter = new Determinant(); // reference to class
        int order = 0; // assigned from input data
        try {

            BufferedReader readFile = new BufferedReader(
                            new FileReader(new File(inputFile)));
            String nextInput = readFile.readLine();

            // for each matrix in one input file
            while ((nextInput != null)) {
                // order entries do not contain spaces in input files
                if (!nextInput.contains(" ")) {
                    order = Integer.parseInt(nextInput);
                    System.out.println("The order of this matrix is " + order
                                    + ".");
                    wr.writeFile(outFileLocation,
                                    "The order of this matrix is " + order
                                                    + ".");
                    nextInput = readFile.readLine();
                }

                // matrix elements are separated by spaces
                if (nextInput.contains(" ")) {
                    // set tempMatrix == nextInput, then loop through
                    // tempMatrix elements and add to tempMatrixHorizontal.
                    // Then add all horiz matrix rows to tempMatrixVertical as
                    // list of lists and print
                    LinkedNodeList tempMatrixVertical = new LinkedNodeList();
                    while (nextInput != null && nextInput.contains(" ")) {
                        LinkedClass tempMatrixHorizontal = new LinkedClass();
                        String tempArr[] = nextInput.split(" ");
                        try {
                            for (int index = 0; index < tempArr.length; 
                                            index++) {
                                tempMatrixHorizontal.addEnd(Integer
                                                .parseInt(tempArr[index]));
                            }
                            tempMatrixVertical.addLinkedNodeList(
                                            tempMatrixHorizontal);
                            System.out.println(nextInput);
                            wr.writeFile(outFileLocation, nextInput);
                            nextInput = readFile.readLine();
                        }

                        // Error checking:
                        catch (NumberFormatException nfex) {
                            System.out.println("At least one element in "
                                            + "the matrix is not a number");
                            wr.writeFile(outFileLocation,
                                            "At least one element in the"
                                            + " matrix is not a number");
                            while (nextInput.contains(" ")) {
                                nextInput = readFile.readLine();
                            }
                        }
                    }
                    // Error checking square matrix:
                    if (tempMatrixVertical.head.size != tempMatrixVertical.size) {
                        System.out.println("This is not a square matrix.\n");
                        wr.writeFile(outFileLocation,
                                        "This is not a square matrix.\n");
                    }
                    // Error checking actual size == stated input
                    else if (tempMatrixVertical.head.size != order) {
                        System.out.println(
                                        "The size of the matrix does not "
                                        + "match the stated input size.\n");
                        wr.writeFile(outFileLocation,
                                        "The size of the matrix does not "
                                        + "match the stated input size.\n");
                    }
                    else {
                        System.out.println(
                                        "The determinant of the above matrix "
                                        + "is: " + deter.getDeterminant(
                                                outFileLocation, order,
                                                tempMatrixVertical.head.head,
                                                tempMatrixVertical) + "\n");
                        wr.writeFile(outFileLocation,
                                        "The determinant of the above matrix is: "
                                        + deter.getDeterminant( outFileLocation,
                                        order, tempMatrixVertical.head.head,
                                        tempMatrixVertical) + "\n");
                    }
                    wr.writeFile(outFileLocation, "\n");
                }
            }
            readFile.close();
        } catch (ArrayIndexOutOfBoundsException aioobex) {
            aioobex.printStackTrace();
        } catch (NumberFormatException nfex) {
            System.out.println(
                        "At least one element in this matrix is not a number.");
            wr.writeFile(outFileLocation,
                        "At least one element in this matrix is not a number.");
        } catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }
}
