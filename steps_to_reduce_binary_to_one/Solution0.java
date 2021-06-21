class Solution {
    public int numSteps(String s) 
    {
        //Copy to array for faster access time
        char[] l = s.toCharArray();
        int size = l.length;
        
        //Return for these cases
        if(s.equals("")||s.equals("1")||s.equals("0")) return 0;
        
        int counter = 0;//track the steps
        boolean carry = false;//flag to indicate if there is a carry over 1 from previous step
        int ptr = size - 1; //start from the last
        while(ptr > 0)
        {
            //count this step
            counter++;
            
            //case: we see a zero
            if(l[ptr] == '0')
            {
                //if there is a carry
                if(carry)
                {
                    //having a carry means that this bit is now 0+1 = 1
                    l[ptr] = '1'; //update the bit
                    counter--; //do this because changing t6he bit is not exactly a step
                    carry = false; //we have incroporated the carry ,so we set the flag to flase
                    //dont deceremnt the index, becasue we are yet to process this position after carry incorporation
                }
                
                //no carry
                else
                {
                    //divide by 2, this is like doing a right shift
                    //here we decerment the index because right shift discards the last bit
                    ptr--;
                }
            }
            
            //case: we have a 1
            else
            {
                //we have a carry
                if(carry)
                {
                    //having a carry here means we have a situation like 1+1+1 = 0 and carry = 1
                    ptr--; //decement counter becasue after incorporating the carry we have a 0
                    //we dont set the carry flag to false yet because this step genereates another carry
                }
                //no carry
                else
                {
                    carry = true; //set carry becasue we add 1 to 1
                    counter++; //since adding is a separate step we increment
                    ptr--; //since adding 1 leaves us with a 0 we now divide by 1
                }
            }
        }
        if(carry) counter++; //incase we have a carry at the end of the loop, our number size has increased, this will only happen if the MSB was 1 in the last iteration of the loop, in this case we need an extra step to process the new MSB.
        return counter;
    }
}