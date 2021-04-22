package Q3a;

import java.util.*;

public class Solver
{
	public static int m, n;
	private static class Node{
		private final int[][] board;
		private final String move;
		private final Node prev;

		private final int startRow;
		private final int startColumn;

		public Node(int[][] board, String move, Node prev, int startRow, int startColumn){
			this.board = board;
			this.move = move;
			this.prev = prev;
			this.startRow = startRow;
			this.startColumn = startColumn;
		}
	}

	public static void printPath(int[][] grid) {
		m = grid.length;
		n = grid[0].length;

		Node initialNode = new Node(grid, null, null, 0, 0);
		boolean solved = false;
		Node solutionNode = null;

		Queue<Node> queue = new LinkedList<>();
		queue.add(initialNode);


		Byte b = 0;
		HashMap<String, Byte> visit = new HashMap<>();
		visit.put(oneD(grid), b);

		outerLoop:
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size > 0) {
				Node node = queue.poll();
				ArrayList<Node> childList = states(node);

				for(Node childNode: childList) {
					if(isSolved(childNode.board)) {
						solved = true;
						solutionNode = childNode;
						break outerLoop;
					}
					if(visit.get(oneD(childNode.board)) == null) {
						queue.add(childNode);
						visit.put(oneD(childNode.board), b);
					}
				}
				size--;
			}
		}


		Vector<String> moveSequence = new Vector<>();
		if(solved) {
			while(solutionNode.prev != null) {
				moveSequence.add(solutionNode.move);
				solutionNode = solutionNode.prev;
			}
		}
		else {
			System.out.println("No solution");
		}
		Collections.reverse(moveSequence);
		for (String s : moveSequence) {
			System.out.println(s);
		}
	}

	public static ArrayList<Node> states(Node nd) {
		ArrayList<Node> adjacent = new ArrayList<>();
		boolean clear;
		// down
		for(int l = 1; l < m; l++) {
			clear = true;
			// in bounds?
			if(nd.startRow + l < m) {
				for(int i = nd.startRow; i <= nd.startRow + l; i++) {
					if(nd.board[i][nd.startColumn] != 1 && nd.board[i][nd.startColumn] != -1) {
						clear = false;
						break;
					}
				}
				if(clear) {
					int[][] base = new int[m][n];
					for(int i = 0; i < m; i++) {
						if (n >= 0) System.arraycopy(nd.board[i], 0, base[i], 0, n);
					}

					for(int i = nd.startRow; i <= nd.startRow + l; i++) {
						base[i][nd.startColumn] = -1;
					}
					String action = "" + "D" + l;
					Node newNode = new Node(base, action, nd, nd.startRow + l, nd.startColumn);
					adjacent.add(newNode);
				}
			}
		}
		// up
		for(int l = 1; l < m; l++) {
			clear = true;
			// in bounds?
			if(nd.startRow - l >= 0) {
				for(int i = nd.startRow - l; i <= nd.startRow; i++) {
					if(nd.board[i][nd.startColumn] != 1 && nd.board[i][nd.startColumn] != -1) {
						clear = false;
						break;
					}
				}
				if(clear) {
					int[][] base = new int[m][n];

					for(int i = 0; i < m; i++) {
						if (n >= 0) System.arraycopy(nd.board[i], 0, base[i], 0, n);
					}

					for(int i = nd.startRow - l; i <= nd.startRow; i++) {
						base[i][nd.startColumn] = -1;
					}
					String action = "" + "U" + l;
					Node newNode = new Node(base, action, nd, nd.startRow - l, nd.startColumn);
					adjacent.add(newNode);
				}
			}
		}
		// right
		for(int l = 1; l <= n; l++) {
			clear = true;
			// in bounds?
			if(nd.startColumn + l < n) {
				for(int j = nd.startColumn; j <= nd.startColumn + l; j++) {
					if(nd.board[nd.startRow][j] != 1 && nd.board[nd.startRow][j] != -1) {
						clear = false;
						break;
					}
				}
				if(clear) {
					int[][] base = new int[m][n];

					for(int i = 0; i < m; i++) {
						if (n >= 0) System.arraycopy(nd.board[i], 0, base[i], 0, n);
					}

					for(int j = nd.startColumn; j <= nd.startColumn + l; j++) {
						base[nd.startRow][j] = -1;
					}

					String action = "" + "R" + l;
					Node newNode = new Node(base, action, nd, nd.startRow, nd.startColumn + l);
					adjacent.add(newNode);
				}
			}
		}
		// left
		for(int l = 1; l < n; l++) {
			clear = true;
			// in bounds?
			if(nd.startColumn - l >= 0) {
				for(int j = nd.startColumn; j >= nd.startColumn - l; j--) {
					if(nd.board[nd.startRow][j] != 1 && nd.board[nd.startRow][j] != -1) {
						clear = false;
						break;
					}
				}
				if(clear) {
					int[][] base = new int[m][n];

					for(int i = 0; i < m; i++) {
						if (n >= 0) System.arraycopy(nd.board[i], 0, base[i], 0, n);
					}
					for(int j = nd.startColumn; j >= nd.startColumn - l; j--) {
						base[nd.startRow][j] = -1;
					}
					String action = "" + "L" + l;
					Node newNode = new Node(base, action, nd, nd.startRow, nd.startColumn - l);
					adjacent.add(newNode);
				}
			}
		}
		return adjacent;
	}

	public static String oneD(int[][] grid) {
		StringBuilder convert = new StringBuilder();
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				convert.append(grid[i][j]);
			}
		}
		return convert.toString();
	}

	public static boolean isSolved(int[][] grid) {
		return grid[m - 1][n - 1] == -1;
	}
}