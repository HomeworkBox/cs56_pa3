//  Weimin Ouyang, 7301005, ouyang@cs.ucsb.edu
//  PlayGame.java
//  ProgAssignment3
//
//  Created by Tyler Weimin Ouyang on 11/23/14.
//  Copyright (c) 2014 Golden: Tyler Weimin Ouyang All rights reserved.
//
package ProgAssignment3;

import java.util.Scanner;

import ProgAssignment3.TicTacToe.Cell;

public class PlayGame {
	public static void main( String args[]){
		TicTacToe test = new TicTacToe();
		Scanner input = new Scanner(System.in);
		test.showBoard();
		while (!test.gameEnd()){
			int x[] = test.moveByRandom();
			test.setCell(x[0], x[1], Cell.X);
			//test.showBoard();
			//System.out.println("your turn");
			if (test.gameEnd())
				break;
			x = test.moveByRandom(Cell.O);
			test.setCell(x[0], x[1], Cell.O);
			//while (!test.setCell(input.nextInt(), input.nextInt(), Cell.O)){}
		}
		Cell[][] testCell = test.getCell();
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				System.out.printf("%s ", testCell[i][j]);
			}
			System.out.println();
		}
		System.out.printf("(1, 1):%s \n", test.getCell(1,1));
		System.out.printf("(2, 1):%s \n", test.getCell(2,1));
		test.showBoard();
		
		System.out.printf("\nX scores: %d", test.scoreOfRandom());
		System.out.printf("\nO scores: %d", test.scoreOfHuman());
		System.out.println("\nWon");
		test.clearBoard();
		while (!test.gameEnd()){
			int x[] = test.moveByRandom();
			test.showBoard();
			System.out.println("your turn");
			if (test.gameEnd())
				break;
			//test.moveByRandom(Cell.O);
			while (!test.setCell(input.nextInt(), input.nextInt(), Cell.O)){}
		}
		testCell = test.getCell();
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				System.out.printf("%s ", testCell[i][j]);
			}
			System.out.println();
		}
		System.out.printf("(1, 1):%s \n", test.getCell(1,1));
		System.out.printf("(2, 1):%s \n", test.getCell(2,1));
		System.out.printf("\nX scores: %d", test.scoreOfRandom());
		System.out.printf("\nO scores: %d", test.scoreOfHuman());
		System.out.println("\nWon");
		test.showBoard();

	}
}
