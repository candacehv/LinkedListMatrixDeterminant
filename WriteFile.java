import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * This class writes results of tests to a file at the location
 * specified as the first command line argument. 
 * It has one method for writing to a file, which takes two 
 * String args to write the output statement in human readable form 
 * 
 */
public class WriteFile {

    /*
     * This method takes inputs String, String to write the results statement to
     * file.
     * 
     * @input: outputSpot is the file name for the output file and is 
     * the first command line arg
     * 
     * @input: statement is the input string to be printed
     * @end state: the statement will be appended to file
     * @returns: none
     * 
     * end state: a file is written that contains the results for all test matrices
     * in all input files. This method appends to files if the already exist, and
     * creates a new one if it does not
     */
    public void writeFile(String outputSpot, String statement)
    {
        BufferedWriter writer = null;
        FileWriter newWriter;

        try {
            String outputRes = statement;
            File outFile = new File(outputSpot);

            // create file if doesn't already exist
            if (!outFile.exists()) {
                outFile.createNewFile();
            }

            newWriter = new FileWriter(outFile, true);
            writer = new BufferedWriter(newWriter);
            writer.write(outputRes);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }
}
