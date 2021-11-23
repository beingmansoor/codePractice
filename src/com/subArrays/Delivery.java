package com.subArrays;

import java.util.HashMap;

public class Delivery
{
	public static boolean checkDelivery(int[] packages, int k) {
        int currSum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        map.put(0, -1);
        for (int i = 0; i < packages.length; i++) {
            currSum += packages[i];
            if (k != 0)
                currSum = currSum % k;
            if (map.containsKey(currSum)) {
                if (i - map.get(currSum) > 1)
                    return true;
            } else
                map.put(currSum, i);
        }
        return false;
    }

    public static void main(String args[]){
        int[] packages = {58, 42, 46, 49, 331, 26, 6, 37, 3};
        int k = 10;
        System.out.println(checkDelivery(packages, k));
    }
}
