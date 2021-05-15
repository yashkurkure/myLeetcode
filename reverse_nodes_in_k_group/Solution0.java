/**
 * Recursive Approach- 
 * 
 * reverseKGroup -> this is the function will will solve the problem statement
 *                  it is recursive and it first counts if there are 'k' nodes avaialble in
 *                  the list to be reversed. If there are it makes a call to reverse() which returns 
 *                  a reversed list, then we make a recursive call to process rest of the list. 
 *                  
 *                  If 'k' nodes are not avaialable then we go the the base case and append return 
 *                  the ramining list.
 * 
 * 
 * 
 */




class Solution 
{
    /*Definition for singly-linked list node*/
    public class ListNode 
    {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    /**
     * This function takes a singly linked list and a number "k" and then reverses each k nodes in the list
     * If there are remaining nodes in the end and their count is not a multiple of k, they are left as it is\
     * 
     * Downside: Not tail recursive (Although java doesnt have tail recursive optimization, stating this as
     * an improvement that can be done if it is written in another langauge like c++ or F#)
     * 
     * Example: Input: head = 1-> 2-> 3-> 4-> 5, k = 2
     *          Output: 2-> 1-> 4-> 3-> 5
     * 
     *          Input: head = 1-> 2-> 3-> 4-> 5-> 6-> 7-> 8, k = 3
     *          Output: 3-> 2-> 1-> 6-> 5-> 4-> 7-> 8
     * 
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        if(head == null) return null;
        
        ListNode curr = head;
        int count = 1; //we know head is not null, so count it and start from 1
        while(count!=k)
        {
            if(curr.next==null)
            {
                break;
            }
            curr = curr.next;
            count++;
        }
        
        //Base Case - IF there remaining nodes are not a multiple of k return remaining in same order
        if(count != k)
        {
            return head;
        }
        
        //Recursive Case - IF there are k nodes left in the list
        else
        {
            
            ListNode restOfTheList = curr.next; //get the next node [Remaining list for next recursion]
            //ListNode last = curr;
            curr.next = null; //set to null so that reverse() does not reverse the entire remaining list
            ListNode newLast = head; //Current head will be last after reversing
            head = reverse(head, null); //reverse the list from head to curr
    
            newLast.next = reverseKGroup(restOfTheList, k); //process remaining and append result
            
            return head; //return result
        }
      
    }
    
    /**
     * Reverses a singly linked list
     * 
     * Tail Recursive and compute as you go strategy
     * (Tail recursive usefull in other languages like C++, for compiler optimization)
     */
    public ListNode reverse(ListNode head, ListNode reversed)
    {
        //Base Case
        if(head== null) return reversed;
        
        //Get next node
        ListNode temp = head.next;

        //atach head to the reversed list
        head.next = reversed;

        //update head of reversed list
        reversed = head;

        return reverse(temp, reversed); //recursive call to reverse rest of the list

    }
}