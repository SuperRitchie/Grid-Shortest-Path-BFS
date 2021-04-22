package solveTesting;

import Q3a.Solver;

import java.io.*;
import java.util.Scanner;


public class TestSolve
{
	static int[][] board = {
			{1,1,1,1,1,1,0},
			{1,0,0,0,1,1,0},
			{1,0,1,1,1,1,0},
			{1,1,1,0,0,1,0},
			{1,1,1,1,1,1,0},
			{0,0,0,0,0,1,1},
			{0,0,0,0,0,1,1},
	};

	public static void main(String[] args) {
		Solver.printPath(board);
	}
}