package com.two.pointers;

import java.util.Stack;

public class TrappingRainWater {
	public static int trap(int[] height) {

		int water = 0;

		int minH = 0, l = 0, r = height.length - 1;

		while (l < r) {

			while (l < r && height[l] <= minH) {
				water += (minH - height[l++]);
			}
			while (l < r && height[r] <= minH) {
				water += (minH - height[r--]);
			}

			minH = Math.min(height[l], height[r]);
		}

		return water;
	}
	
	public int trap_stack(int[] height) {
        if (height == null || height.length < 2) return 0;
        
        Stack<Integer> stack = new Stack<>();
        int water = 0, i = 0;
        while (i < height.length) {
            if (stack.isEmpty() || height[i] <= height[stack.peek()]) {
                stack.push(i++);
            } else {
                int pre = stack.pop();
                if (!stack.isEmpty()) {
                    // find the smaller height between the two sides
                    int minHeight = Math.min(height[stack.peek()], height[i]);
                    // calculate the area
                    water += (minHeight - height[pre]) * (i - stack.peek() - 1);
                }
            }
        }
        return water;
    }

	public static void main(String[] args) {
		System.out.println(trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));
		System.out.println(trap(new int[] { 4, 2, 0, 3, 2, 5 }));
	}
}
