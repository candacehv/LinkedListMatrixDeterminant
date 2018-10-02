/*
 * This class creates a linked list that holds lists of LinkedNodes; 
 * It is the "vertical" list that holds the "horizontal" lists.
 * Then, if the new LinkedList being added is not the first, it links
 * the new list to the previous list "above" it, creating the multi linked 
 * aspect
 */
public class LinkedNodeList {
    int size = 0;
    LinkedClass head = null;
    LinkedClass tail = null;

    /*
     * This method adds one "horizontal" linked list of LinkedNodes to a list 
     * of LinkedClasses.
     * 
     * @args: LinkedClass newLNLClass is the LinkedClass to be added
     * @output: none
     * @end state: new LinkedClass is added to end of LinkedNodeList
     * @return: none
     */
    public void addLinkedNodeList(LinkedClass newLNLClass)
    {
        // new node of vertical linked list - holds rows of matrix
        LinkedClass newLNL = new LinkedClass();
        newLNL = newLNLClass; // "vertical" node now holds horizontal list
        //special handling for first node
        if (head == null) {
            newLNL.up = null;
            head = newLNL;
            newLNL.down = null;
            tail = newLNL;
            size++; //manage length
            return;
        }
        newLNL.up = tail;
        tail.down = newLNL;
        tail = newLNL;
        newLNL.down = null;
        size++; //manage length
        try {
            LinkedNode currNode = newLNL.head; // link down - new row
            LinkedNode aboveCurrNode = newLNL.up.head; // link up - prev row
            while (currNode != null && aboveCurrNode != null) {
                currNode.up = aboveCurrNode;
                aboveCurrNode.down = currNode;
                aboveCurrNode = aboveCurrNode.next;
                currNode = currNode.next;
            }
        } catch (NullPointerException npex) {
            npex.printStackTrace();
        }
    }
}
