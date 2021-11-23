package com.union.find;

public class NumberOfIslands {

	public static void main(String[] args) {
		final NumberOfIslands i = new NumberOfIslands();

		char[][] grid = { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' },
				{ '0', '0', '0', '1', '1' } };
		System.out.println(i.numIslands(grid));

		grid = new char[][] { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' }, { '1', '1', '0', '0', '0' },
				{ '0', '0', '0', '0', '0' } };
		System.out.println(i.numIslands(grid));
	}

	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		final int row = grid.length;
		final int col = grid[0].length;

		final UnionFind island = new UnionFind(row, col, grid);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == '1') {
					final int p = i * col + j;
					// right
					if (j < col - 1 && grid[i][j + 1] == '1') {
						final int q = i * col + j + 1;
						if (!island.find(p, q)) {
							island.union(p, q);
						}
					}
					// down
					if (i < row - 1 && grid[i + 1][j] == '1') {
						final int q = (i + 1) * col + j;
						if (!island.find(p, q)) {
							island.union(p, q);
						}
					}
				}
			}
		}
		return island.size();
	}
}

class UnionFind {

	private final int[] id, size;
	private int count;

	public UnionFind(int row, int col, char[][] grid) {
		id = new int[row * col];
		size = new int[row * col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == '1') {
					this.count++;
				}
			}
		}

		for (int i = 0; i < row * col; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}

	public boolean find(int p, int q) {
		return root(p) == root(q);
	}

	private int root(int i) {
		while (i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}

	public int size() {
		return this.count;
	}

	public void union(int p, int q) {
		final int i = root(p);
		final int j = root(q);

		if (size[i] < size[j]) {
			id[i] = j;
			size[j] += size[i];
		} else {
			id[j] = i;
			size[i] -= size[j];
		}
		count--;
	}
}