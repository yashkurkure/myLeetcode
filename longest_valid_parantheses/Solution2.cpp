#include <iostream>
#include <stack>
#include<string>
using namespace std;

/**
 * Iterative Solution with Stacks -fast but not faster than solution 2
 * 
 * Here I am using 2 loops: One traverses the entirte string
 *                          Second looks at the final stack to return the longestValid valid substring
 * 
 * Using C++ insted of Java, good revision of C++ STL
 * 
 */


class Solution {
public:
    

    //Function takes an integer and returns its magnitude mod(x) = |x|
    int mod(int x)
    {
        if (x<0) return -x;
        return x;
    }
    
    
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
    int longestValidParentheses(string s) 
    {
        //Initialize a stack
        stack <int> stack;

        //We push something random becasue if there is a ')' at index 0, we need top pop something.
        stack.push(-1);

        int longestValid = 0; //longest valid counter
        for(int i = 0; i < s.length(); i++)
        {
            if(s[i] == '(') //See a opening brace
            {
                stack.push(i);
            }
            
            if(s[i] == ')') //See a closing brace
            {
                stack.pop(); //Pop off the first element in the stack

                if(!stack.empty()) //if the stack is still not empty
                {
                    //We probably have another opening brace index, which can be popped, so the string could still be valid.
                    //But for the time being we know that we have seen a valid "()" pair(s), hence calucualt the length of the substring from the top index of the stack
                    int len = i - stack.top(); //get the length of the sbustring using the indexes in the stack
                    longestValid = max(longestValid, len); //get the max between the the two 
                    
                }
                else
                {   //If the stack is empty we know the substring has become invalid, hence we push this onto the stack
                    //Push this possible closing braces after this have something to pop.
                    stack.push(i);
                }
                
                
            }
        }
        
        //Return the longest valid
        return longestValid;

       
    }
    
};