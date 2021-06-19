//Using Iteration

class Solution 
{
    
    //Call this function
    public boolean rotateString(String s, String goal) 
    {
        if(s.length() != goal.length())
        if(s.equals(""))
        {
            if(goal.equals("")) 
                return true;
            else
                return false;
        }
        
        for(int i = 0; i < s.length(); i++)
        {
            if(goal.equals(s.substring(i)  + s.substring(0,i)))
              return true;    
        }
        
        return false;
    }
}