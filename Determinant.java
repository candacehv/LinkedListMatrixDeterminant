/*
 * This class is responsible for calculating the determinant of each 
 * matrix. It uses recursion to continually caclulate the determinant of 
 * matrix minors which are one 
 * degree smaller than the current matrix, until it reaches matrices 
 * that are of degree 1. It uses the Laplace formula to calculate
 * the determinant.
 */

public class Determinant {

    /*
     * getDeterminant calculates the determinant of each input matrix.
     * 
     * It uses recursion, with a stopping case of order == 1. In that case 
     * it will return the element of the 1 degree matrix. Otherwise, will 
     * call itself again with the determinant of its minor, which is the 
     * return of getMatrix. 
     * 
     * @input: string outputLocation is the destination of the output file
     * @input: is order is the input collect from the input file; it is 
     * altered through the call to getMatrix, which creates a submatrix
     * @input: LinkedNode mLvlPtr is the node of the "a" value in the Laplace 
     * formula - it is the multiplier for the minor. 
     * @input: LinkedNodeList matrix is the starting matrix sent from 
     * readFile() and later recursively from getDeterminant(); 
     * 
     * @output: 
     */

    public int getDeterminant(String outputLocation, int order,
                    LinkedNode mLvlPtr, LinkedNodeList matrix)
    {
        if (mLvlPtr.data == 0)
        {
            return 0; 
        }
        WriteFile wr = new WriteFile();
        int determinant = 0;
        try {
            // stopping case
            if (order == 1) {
                // Error checking
                determinant = matrix.head.head.data; // head of vertical list
                //of lists, head of horizontal linkedClass
                return determinant; // only element
            }

            // continue recursion
            else if (order > 1) {
                int sign = 1; // starts positive, but every loop mult by -1;
                for (LinkedNode currPtr = matrix.head.head; currPtr != null; 
                            currPtr = currPtr.next) {
                    if (currPtr.data == 0) {
                        continue; // 0 multiplier always results in 0
                    }
                    int newDeterminant = 0;
                    newDeterminant = sign * (currPtr.data * getDeterminant(
                                    outputLocation, order - 1, currPtr,
                                    getMatrix(matrix, currPtr)));
                    determinant += newDeterminant;
                    sign *= -1;
                }
            }
            else {
                System.out.println(
                                "The matrix cannot have a degree less than 1.");
                wr.writeFile(outputLocation,
                                "The matrix cannot have a degree less than 1.");
                return 000;
            }
        } catch (NullPointerException npex) {
            npex.printStackTrace();
        }
        return determinant;
    }

    /*
     * getMatrix returns the minors of the original requested by getDeterminant. It
     * uses the Laplace method to determine which elements to include in the minors.
     * 
     * @input: startingMatrix is the larger matrix sent by getDeterminant
     * 
     * @input: matrixColStart is the starting node and multiplier value
     * 
     * @variable: LinkedNode addNode is a pointer to the current node that will be
     * evaluated to determine if it is part of the minor
     * 
     * @variable: int placeholder is used to move down col so addNode can always
     * test whether it is in the "crossed out" column by testing whether addNode.up
     * == colPlcHolder
     * 
     * @order: the order of the new matrix being created. getDeterminant will call
     * itself recursively with order-1.
     * 
     * @column is the column that is currently being eliminated in the
     * startingMatrix
     * 
     * This method will take a larger startingMatrix, and loop through all elements,
     * adding them to newMatrix, only if their location is not in row 0 of
     * startingMatrix, and if their location is not in the column index passed as an
     * argument.
     */
    // order is the order of the matrix; column is the column of the current M
    // column always called by getDeterminant as 0, changed within getMatrix
    public LinkedNodeList getMatrix(LinkedNodeList startingMatrix,
                    LinkedNode matrixColStart)
    {
        LinkedNodeList newMatrixCol = new LinkedNodeList();
        int placeholder = 0;
        try {
            LinkedNode addNode;
            for (LinkedNode row = startingMatrix.head.head; row.down != null; row = row.down) {
                LinkedClass newMatrixRow = new LinkedClass();
                LinkedNode colPlcHolder;
                int index = 0;
                for (colPlcHolder = matrixColStart; index < placeholder
                                && colPlcHolder != null; index++) {
                    colPlcHolder = colPlcHolder.down;
                }
                for (addNode = row.down; addNode != null; addNode = addNode.next) {
                    if (addNode.up == colPlcHolder) {
                        continue; // crossed out column
                    }
                    else {
                        newMatrixRow.addEnd(addNode.data);//
                    }
                }
                newMatrixCol.addLinkedNodeList(newMatrixRow);
                // move this place holder down for every row so that
                // colPlcHolder is always right above addNode
                placeholder++;

            }
        } catch (NullPointerException npex) {
            npex.printStackTrace();
        }
        return newMatrixCol;
    }
}
