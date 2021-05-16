
#include <iostream>
#include <stack>
#include<string>
using namespace std;

/**
 * Iterative Solution with Stacks -fast but not faster than solution 2
 * 
 * The idea of using stacks came from the concept of Push Down Automata (PDA), 
 *  where we use a stack to push an pop the characters of the string in each state and check validity based
 *  on the end condition of the stack.
 * 
 * 
 * Here I am using 2 loops: One traverses the entirte string
 *                          Second looks at the final stack to return the longest valid substring
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

        // Loop through the string
        for(int i = 0; i < s.length(); i++)
        {
            //NOTE: Negative numbers in the stack represent closing braces
            //Hence the stack cannot contain a zero because negative of zero is zero LOL
            //For this reason in the stack the pushed indexes start from 1 and go till string.length inclusive

            if(s[i] == '(') //see a opening brace
            {
                //Push index of opening brace [Starts from 1 remember? Hence add 1]
                stack.push(i+1);
            }
            
            if(s[i] == ')') //see a closing brace: If we ever push a closing brace index onto the stack it will be negative
            {
                //If the stack is not empty and we dont have a negative number at the top, we know the parantheses is still valid
                if(!stack.empty() && stack.top() > 0)
                {
                    stack.pop(); //pop the opening brace
                }
                else
                {
                    //Now, if we have a an epty stack we know the parantheses string is invalid becasue there is not opening brace index to pop
                    //If the top of the stack is a closing brace i.e. negative we cant pop it either

                    //Dont worry about something like (()), this is handled correctly because we always have something to pop at each closing brace occurrence

                    stack.push(-(i+1)); //push the closing brace index, but with a negative sign
                }
            }
        }
        
        //If the stack is empty, the entire string is valid
        if(stack.empty()) return s.length(); 
        


         //Imporvement Thougth: Can the below mechanism be incorporated in the the above loop itself? [Made this in Solution 2] 


        //Each number in the stack at this point represents the invalid points in the full stirng
        //Which means that the substrings between these indexes in the stack are the valid ones, we just need to now find the longest

        //To get the length of the longest valid string
        int longestValid = 0;
        int curr = s.length(); //since the stack is FILO, the indexes begining at the top are in reverse
        while(!stack.empty())
        {
            //Using the mod function here to return the indexes to positive values

            //Get the length between the current point and the next invalid point and check if its the longest
            if(curr - mod(stack.top()) > longestValid)
            {
                //Assign that to longest if it is the longest substring
                longestValid = curr - mod(stack.top());
            }
            
            //advance curr to next point in stack
            curr = mod(stack.top()) - 1;
            stack.pop(); //pop curr of the stack
        }
        
        //Once we reach here we check if the the 1st substring, i.e starting form the beginging of the string is the longest
        //When the above loop ends we are left with the last invalid index in the curr variable
        if(curr > longestValid)
        {
            //If yest, update longest valid
            longestValid = curr;
        }
        
        //Return longest valid
        return longestValid;
    }
    
};