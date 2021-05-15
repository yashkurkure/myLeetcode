/** 
 * Recursive Approach WITHOUT Divide and Conquer - tad bit faster than iterative
 * 
 * Here I am using 2 recrusive function.
 * 
 * mergeTwo() ->  takes two sorted lists and merges them into one sorted list
 * mergeAll() -> takes an array of k sorted lists and returns a merged sorted list of the k lists
 *               mergeAll makes calls to mergeTwo to merge two lists at a time in each recursive call to mergeAll.
 * 
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
        if(lists.length == 0) return null; //Return empty list if lists[] is empty
        return mergeAll(null, lists, 0); //Recursive function to merge all lists
    } 

    /**
     * This is tail recursive but the depth of recursion gets to large
     * 
     */
    public ListNode mergeAll(ListNode mergedList, ListNode[] lists, int index)
    {
        //Base Case: If the index reached is the last index, merge the last list with mergedList and return it.
        if(index == lists.length-1) 
            return mergeTwo(mergedList, lists[index]);
        
        //Controll reaches here if base case is not ture
        
        //Compute as you go and Tail recursive

        //Using the mergeTwo function we can merge two sorted lists
        mergedList = mergeTwo(mergedList, lists[index]); //merge mergedList and the list at index
        return mergeAll(mergedList, lists, index + 1); //make recursive call to merge next list
    }

    /**
     * This function takes two sorted lists and returns a merged sorted list
     * 
     * Tail recursive
     *(Although java doesnt have tail recursive optimization, stating this as an optimization useful in 
     * other languages like C++)
     */
    public ListNode mergeTwo(ListNode n1, ListNode n2)
    {
        //Base Cases: If one of the list list reaches null, return the remaining list
        //Since we know that the other list is already sorted we can return the whole list as it is, and the merged list will also be sorted
        if(n1 == null) return n2;
        if(n2 == null) return n1;
        
        //If else block to merge the minimum value node;
        if(n1.val < n2.val)
        {
            n1.next = mergeTwo(n1.next, n2);
            return n1;
        }
        else
        {
            n2.next = mergeTwo(n1, n2.next);
            return n2;
        }
    }


}