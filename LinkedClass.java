/*
 * The LinkedClass holds lists of LinkedNodes. It adds head, tail, and
 * multi linking to the LinkedNodes.
 * 
 * Methods include addEnd and overloaded printAll 
 */

public class LinkedClass {
    int size;
    LinkedNode head;
    LinkedNode tail;
    LinkedClass up;
    LinkedClass down;

    
    /*
     * This method adds LinkedNodes to form a list.
     * @args: int value is the data value of the next node. 
     * It has special handling for the first node, and then adds all
     * subsequent nodes to the end of the specified list.
     */
    public void addEnd(int value)
    {
        LinkedNode addNode = new LinkedNode();
        addNode.data = value;
        if (head == null) // add first node
        {
            addNode.prev = null;
            head = addNode;
            addNode.next = null;
            tail = addNode;
            size++; //manage length
            return;
        }
        addNode.prev = tail;
        tail.next = addNode;
        tail = addNode;
        addNode.next = null;
        size++; //manage length
    }

    /*
     * This method prints all elements of a LinkedClass list of LinkedNodes.
     * @args: string outputSpot is the output file destination
     * @args: LinkedClass matrixRow is the list to be printed
     * @return: none
     * @output: matrixRow printed to screen and output file
     * @end state: unchanged
     */
    public void printAll ( String outputSpot, LinkedClass matrixRow )
    {
        WriteFile wr = new WriteFile();
        try {
            LinkedNode currNode = matrixRow.head;
            while (currNode != null)
            {
                System.out.print(currNode.data + " ");
                wr.writeFile(outputSpot, currNode.data + " ");
            }
        }
        catch (NullPointerException npex)
        {
            npex.printStackTrace();
        }
    }
    // @overload
    /*
     * This overloaded method prints all elements of a LinkedNodeList
     * of LinkedClass lists.
     * @args: string outputSpot is the output file destination
     * @args: LinkedNodeList tempMatrix is the list of lists to be printed
     * @return: none
     * @output: tempMatrix printed to screen and output file
     * @end state: unchanged
     */
    public void printAll(String outputSpot, LinkedNodeList tempMatrix)
    {
        WriteFile wr = new WriteFile();
        try {
            LinkedClass currLNLNode = tempMatrix.head;
            LinkedNode currNode = currLNLNode.head;
            while (currLNLNode != null) { // for each node in vertical list...
                currNode = currLNLNode.head;
                while (currNode != null) {
                    // for each element in each horizontal list...
                    System.out.print(currNode.data + " ");
                    wr.writeFile(outputSpot, currNode.data + " ");
                    currNode = currNode.next;
                }
                System.out.println();
                currLNLNode = currLNLNode.down;
            }
            System.out.println();
            wr.writeFile (outputSpot, "\n");
        } catch (NullPointerException npex) {
            npex.printStackTrace();
        }
    }
}
