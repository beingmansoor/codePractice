package com.binary.search;

import java.util.Arrays;

public class DividePosts
{
	 public static int dividePosts(int[] days, int k) {
	        int left = 1, right = Arrays.stream(days).sum() / k;

	        while (left < right) {
	            int mid = (left + right + 1) / 2;
	            
	            // This would denote the posts we currently have 
	            // as we are traversing over the array
	            int target = 0;
	            
	            // This would tell us how many days we would get after dividing 
	            // the array in `mid` amount of posts
	            int divisions = 0;
	            for (int posts : days) {
	                target += posts;
	                if (target >= mid) {
	                    divisions++;
	                    target = 0;
	                }
	            }
	            if (divisions >= k)
	                left = mid;
	            else
	                right = mid - 1;
	        }
	        return left;
	    }
	    public static void main( String args[] ) {
	        // Driver code
	        
	        int[] dayss = {1000,2000,3000,4000,5000};
	        int nodes = 3;
	        System.out.println("The master node was assigned " +  dividePosts(dayss, nodes) + " posts");
	    }
}
