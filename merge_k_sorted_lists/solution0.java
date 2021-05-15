/** 
 * Iterative Approach -VERY SLOW
 * 
 * 
 * This was very slow because I was iterating the array while making
 * comparisons in an interior loop to find the min elemet at the head 
 * positions of each liniked list. 
 * 
 * Once I found the min element in the inner loop, I would advance that linked
 * lists head to the next position.
 * 
 * The outer loop would break when all the heads in the array are null.
 */


class Solution
{
    /** 
    * Defintion for singly-linked list node
    */
    public class ListNode 
    {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * This function takes a array of sorted linked lists and returns
     * a single linked list which is also sorted
     */
    public ListNode mergeKLists(ListNode[] lists)
    {

        //Create a dummy head for the new merged list
        ListNode head = new ListNode(-1);
        ListNode curr = head; //set curr to head
        
        boolean allAreNull = false; //flag to indicate if all elements in lists[] are null
        while(!allAreNull)
        {

            int min = Integer.MAX_VALUE; //set min to max integer value
            int minPos = -1; //to keep track of which position in lists[] do we have the min value
            int nullCounter = 0; //to count how many elements in lists[] are null
            for(int i = 0; i < lists.length; i++) //iterate over lists[]
            {
                if(lists[i] == null) 
                {
                    //if head of the list at i is null increment null counter and proceed to next iteration
                    nullCounter++;
                    continue;
                }
                if(lists[i].val <= min) //check if there is a new min
                {
                    //new min value found
                    min = lists[i].val;
                    minPos = i; //record the postion of the new min value
                }
                
            }//end of inner for loop
            
            
        
            if(nullCounter == lists.length) //check if all lists are null [This is a sign that we are done with merging]
            {
                //if all the heads in lists[] are null we set the allAreNull flag [breaks the loop]
                allAreNull = true;
            }
            else // if not we must add the new min element to the merged list
            {
                curr.next = lists[minPos]; //set the merged list's next element as the new min element ListNode using the minPos value

                lists[minPos] = lists[minPos].next;// advance that list's head to the next element

                curr = curr.next; //advance curr to the newly added node with the min value

                //At this point we have extractred a min element added it to the new merged list. 
            }
            
            
        }//end of outer while loop
        

        return head.next; //remember we had a dummy node at the start? return the list without that node.

    } 


}