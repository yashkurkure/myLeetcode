/**
 * Iterative Solution: Slowest'
 * 
 * Here I am using 2 nested loops which makes this very slow
 * 
 * 
 * 
 */



class Solution
{
    /**
     * 
     * This function takes a string and returns the max length of the substring with 
     *  valid parantheses arrangement.
     * 
     * The input must be a string of '(' and  ')' ONLY.
     * 
     * Example: Input: ")()()("
     *          Output: 4
     * 
     *          Input: ")())(()())()"
     *          Output: 8
     * 
     */
    public int longestValidParentheses(String s) 
    {
        
        //Convert to char array becasue string function charAt() is slower than array access
        //Although we are copying the entire string, we save time by not using charAt() in below loop
        char[] arr = s.toCharArray();
        

        int longestValid = 0; //Variable holds the length of the longest parantheses valid subctring 
        for(int i = 0; i < arr.length; i++) //Outer loop looks for openeing braces
        {

            //If it is a closeing brace we know that the substring follwing this position is already invalid
            if(arr[i] == ')') continue; 
                

            //1 is added to the tracker vairable if we see and opening brace, else 1 is subtracted
            int tracker = 0; //this helps in tracking if the substring is valid. 
            int counter = 0; //book keeping of substring length
            int tempLongestValid = 0; //this is the longest valid in the current substring
            for(int j = i; j < arr.length; j++)
            {

                if(arr[j] == '(')
                {
                    tracker++; //opening brace add 1
                    counter++;
                }
                
                if(arr[j] == ')')
                {
                    tracker--; //closing brace subtract 1
                    counter++;
                }
                
                //If tracker is negative, we know we have seen more closing braces than opening, which makes the substring invalid
                if(tracker < 0) 
                {
                    //break once the sinstring becomes invalid
                    break;
                }
                
                //If tracker reaches 0, we know we have seen equal number of opening and closing braces
                if(tracker == 0 && counter != 0)
                {
                    //check if this is the longest substring we have seen yet
                    if(tempLongestValid <= counter)
                    {
                        tempLongestValid = counter; //update if longest
                    }
                }
            }//end of inner for loop
            
            //Now check if the substring above is the longest valid substring we have seen
            if(longestValid < tempLongestValid)
            {
                longestValid = tempLongestValid; //update longest valid

                i = i + tempLongestValid - 1; //skip the the end of that substring
                //we skip to the end becausse we dont want to check the substrings of the substring, since we already know its valid.
            }
        
        }//end of outer for loop
        
        //return longestValid substring
        return longestValid;
        
    }
}