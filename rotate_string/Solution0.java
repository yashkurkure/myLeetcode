//Using recursion

class Solution 
{
    
    //Call this function
    public boolean rotateString(String s, String goal) 
    {
        if(s.equals("") && goal.equals("")) return true;
        if( (s.equals("") && !goal.equals("")) || (!s.equals("") && goal.equals("")) ) return false;
        if(s.equals(goal)) return true;
        
        return _recurse(s,s,goal);
    }
    
    public boolean _recurse(String orignal, String curr, String goal)
    {
        //System.out.println(curr + ", " + orignal);
        curr = shift(curr);
        if(curr.equals(goal)) return true;
        if(curr.equals(orignal)) return false;
        
        return _recurse(orignal, curr, goal);
    }
    
    public String shift(String s)
    {
        
        return s.substring(1) + s.charAt(0);
    }
    
    
}