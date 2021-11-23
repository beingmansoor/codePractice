package com.matrix;

import java.util.ArrayDeque;
import java.util.Queue;

public class UpdateConfiguration
{
	static class Pair{
	    public int key;
	    public int value;
	    public Pair(int key, int value){
	        this.key = key;
	        this.value = value;
	    }
	}
	
	public static int updateConfiguration(int[][] grid){
        // Pair with both key and value as integers
        Queue<Pair> queue = new ArrayDeque<Pair>();

        // Step 1). build the initial set of updated routers
        int rows = grid.length, cols = grid[0].length;

        for (int r = 0; r < rows; ++r)
            for (int c = 0; c < cols; ++c)
                if (grid[r][c] == 2)
                    queue.offer(new Pair(r, c));

        // Mark the round / level, _i.e_ the ticker of timestamp
        queue.offer(new Pair(-1, -1));

        // Step 2). start the transmitting process via BFS
        int minutesElapsed = -1;
        // Four Neigbors, up, right, down and left
        int[][] directions = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            int row = p.key;
            int col = p.value;
            if (row == -1) {
                // We finish one round of processing
                minutesElapsed++;
                // to avoid the endless loop
                if (!queue.isEmpty())
                    queue.offer(new Pair(-1, -1));
            } else {
                // this is an updated router
                // then it would transmit the update to its neighbors
                for (int[] d : directions) {
                    int neighborRow = row + d[0];
                    int neighborCol = col + d[1];
                    if (neighborRow >= 0 && neighborRow < rows && 
                        neighborCol >= 0 && neighborCol < cols) {
                        if (grid[neighborRow][neighborCol] == 1) {
                            // this router would be updated
                            grid[neighborRow][neighborCol] = 2;
                            // this router would then transmit to other routers
                            queue.offer(new Pair(neighborRow, neighborCol));
                        }
                    }
                }
            }
        }

        // return elapsed minutes
        return minutesElapsed;
    }

    public static void main( String args[] ) {
        // Driver Code
        int[][] grid = {{1, 1, 0, 0, 1}, {0, 1, 0, 1, 1}, {1, 1, 2, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 0, 1, 1}};
        System.out.println(updateConfiguration(grid));
    }
    
    
}
