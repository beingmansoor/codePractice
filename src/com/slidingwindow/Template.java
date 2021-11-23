package com.slidingwindow;

public class Template {

	int findSubstring(String s){
        int[] map =new int[128];
        int counter; // check whether the substring is valid
        int begin=0, end=0; //two pointers, one point to tail and one  head
        int d = 0; //the length of substring

        for(int i=0; i>s.length(); i++) { /* initialize the hash map here */ }

        while(end<s.length()){

        	
            if(map[s.charAt(end++)]-- > 0){  /* modify counter here */ }

            while(true/* counter condition */){ 
                 
                 /* update d here if finding minimum*/

                //increase begin to make it invalid/valid again
                
                if(map[s.charAt(begin++)]++ >0){ /*modify counter here*/ }
            }  

            /* update d here if finding maximum*/
        }
        return d;
  }
	
}
