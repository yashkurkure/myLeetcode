#include <iostream>
#include <bits/stdc++.h>
using namespace std;

/**
 * Using Unordered Maps
 * 
 * Process:
 * ->Add all numbers from vector to unorderd map {Key = nums[i], Value = i}
 *    -> While adding check for duplicates, if the duplicates add up to target, return the result. Else dont add the duplicate.
 *    -> Once done adding we will have a map with only the unique elements
 * ->Check if target/2 if target is even, exists in the map, remove it of it does. We remove beacuse we already checked for duplicates,
 *      and thats how we know we dont have the other half of target in the nums vector to finish the problem.
 * ->Next itereate over all values of the map and check if for any number, (target-number) exists in the map. If it does then 
 *    add it to the result vector.
 * 
 * 
*/
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) 
    {
        
        vector<int> vec;//Vector to store our result
        
        //Using unordered_maps
        unordered_map <int,int> mp;
        
        //Traverse the input
        for(int i = 0; i < nums.size(); i++)
        {
            if(mp.find(nums[i]) == mp.end()) //number not in map
            {
                mp[nums[i]] = i; //add the number as the key and the index as the value
            }
            else // number already in map
            {
                //Now that we know we have duplicates, check if the target is the sum of duplicates
                if(nums[i] + nums[i] == target)
                {
                    vec.push_back(mp[nums[i]]);
                    vec.push_back(i);
                    return vec; //return result if target is sum of duplicates
                    
                }
                //if it is not, we dont need to care about the duplicate number so dont add it.
            }
        }
        
        //At this point we only have unique numbers in our map
        
        //Now find if there are numbers that are half of the target
        //Remove them because we know we dont have their other half, as we already tested for duplicates
        if(target%2 == 0 && mp.find(target/2) != mp.end())
        {
            mp.erase(target/2);
        }
        
        
        //Go through the map values 
        for(auto i : mp)
        {

            //For each number try and find if the complement of it exists, i.e, target-number
            if(mp.find(target-i.first) != mp.end())
            {
                //If it does push the current number onto the vector
                vec.push_back(i.second);
            }
        }//By the end of this loop[ we will have the vector loaded with both the index values for our numbers
        
        
    return vec;    
    }
};
