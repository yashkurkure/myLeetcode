/** 
 * Recursive Approach WITH Divide and Conquer - fastest I have written till now
 * 
 * The only differce between this and solution 1 is the mergeAll() function, here it uses divide and conquer.
 * 
 * Here I am using 2 recrusive function.
 * 
 * mergeTwo() ->  takes two sorted lists and merges them into one sorted list
 * mergeAll() -> takes an array of k sorted lists and returns a merged sorted list of the k lists
 *               mergeAll makes calls to mergeTwo to merge two lists, but It does so by divide and conquer.
 *               It will split the list[] at aprroximatley half every time.
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
        return mergeAll(lists, 0 , lists.length - 1); //Recursive function to merge all lists
    } 

    /**
     * In solution 1 I had a recursive function as well , but this one uses divide and conquer
     * 
     * The runtime imporved siginificantly
     */
    public ListNode mergeAll(ListNode[] lists, int start, int end)
    {
        if(start == end) return lists[start];
        else
        {
            //ListNode n1 = mergeAll(lists, start, ((start+end)/2));
            //ListNode n2 = mergeAll(lists,((start+end)/2) + 1, end);
            return mergeTwo(mergeAll(lists, start, ((start+end)/2)),mergeAll(lists,((start+end)/2) + 1, end));
        }
    }

    /**
     * This function takes two sorted lists and returns a merged sorted list
     * 
     * Tail recursive
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