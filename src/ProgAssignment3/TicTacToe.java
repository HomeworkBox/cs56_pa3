//  Weimin Ouyang, 7301005, ouyang@cs.ucsb.edu
//  TicTacToe.java
//  ProgAssignment3
//
//  Created by Tyler Weimin Ouyang on 11/23/14.
//  Copyright (c) 2014 Golden: Tyler Weimin Ouyang All rights reserved.
//

package ProgAssignment3;

import java.util.Random;

public class TicTacToe {
	private Cell[][] board;
	private int counter;
	private int[] emptyCell;
	private Random randomNumbers;
	private boolean wonBy5;

	public enum Cell { 
		X, O, EMPTY;
	}
	public TicTacToe(){
		board = new Cell[5][5];
		emptyCell = new int[25];
		randomNumbers = new Random();
		wonBy5 = false;
		clearBoard();
	}

	public boolean setCell(int xPos, int yPos, Cell mark){
		if (xPos>4 || yPos >4 || xPos<0 || yPos<0){
			System.out.println("invalid position, reinput!");
			return false;
		}
		if (!isEmpty(xPos, yPos)){
			System.out.println("position already taken, reinput!");
			return false;
		}
		board[xPos][yPos] = mark;
		counter++;
		emptyCell[xPos*5 + yPos] = 1;
		return true;
	}

	public Cell getCell( int xPos, int yPos){
		if (xPos>4 || yPos >4 || xPos<0 || yPos<0){
			System.out.println("invalid position, reinput! return(0,0) by defaul");
			return board[0][0];
		}
		return board[xPos][yPos];
	}

	public Cell[][] getCell(){
		return board;
	}

	public boolean isEmpty(int xPos, int yPos){
		if (board[xPos][yPos] == Cell.EMPTY)
			return true;
		return false;
	}

	public void clearBoard(){
		for (int k=0; k<5; k++){
			for (int i=0; i < 5; i++){
				board[k][i] = Cell.EMPTY;
			}
		}
		counter = 0;
		wonBy5 = false;
		for (int i=0; i < 25; i++){
			emptyCell[i] = 0;
		}
	}

	public int[] moveByRandom(){
		int i = 0;
		if (counter == 24){
			for (; i<25; i++){
				if (emptyCell[i]==0){
					break;
				}
			}
		}
		else {
			int location = 1 + randomNumbers.nextInt(25 - counter);
			while (location != 0){
				if (emptyCell[i] == 0)
					location--;
				i++;
			}
			i--;
		}
		int x = i / 5;
		int y = i % 5;
		emptyCell[i] = 1;
		int[] result = {x, y};
		return result;
	}

	public boolean gameEnd(){
		boolean result = fiveInARow() || fiveInAColumn() || fiveInALine();
		if (counter == 25)
			return true;
		return result;
	}

	public int scoreOfRandom(){
		if(counter != 25 || wonBy5)
			return 0;
		return score(Cell.X);

	}
	public int scoreOfHuman(){
		if(counter != 25 || wonBy5)
			return 0;
		return score(Cell.O);

	}

	public void showBoard(){
		int[] human = new int[75];
		int[] random = new int[75];
		int x=0, y=0;
		for (int k=0; k<5; k++){
			for (int i=0; i < 5; i++){
				if (board[k][i] == Cell.X){
					random[x] = 1;
					random[x + 1] = k;
					random[x + 2] = i;
					x+=3;
				}
				else if (board[k][i] == Cell.O){
					human[y] = 1;
					human[y + 1] = k;
					human[y + 2] = i;
					y+=3;
				}
			}
		}
		try {
			Board frame = new Board(random, human);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean fiveInARow(){
		for (Cell[] row : board){
			for (int i=0; i < 4; i++){
				if (row[i] == Cell.EMPTY  || row[i] != row[i+1])
					break;
				if(i==3){
					wonBy5 = true;
					return true;
				}
			}
		}
		return false;
	}

	private boolean fiveInAColumn(){
		for (int k=0; k<5; k++){
			for (int i=0; i < 4; i++){
				if (board[i][k] == Cell.EMPTY  || board[i][k] != board[i+1][k])
					break;
				if(i==3){
					wonBy5 = true;
					return true;
				}
			}
		}
		return false;
	}

	private boolean fiveInALine(){
		for (int i=0; i < 4; i++){
			if (board[i][i] == Cell.EMPTY  || board[i][i] != board[i+1][i+1])
				break;
			if(i==3){
				wonBy5 = true;
				return true;
			}
		}
		for (int i=0; i < 4; i++){
			if (board[4-i][i] == Cell.EMPTY  || board[4-i][i] != board[3-i][i+1])
				break;
			if(i==3){
				wonBy5 = true;
				return true;
			}
		}

		return false;
	}

	private int score(Cell player){
		int count3 = 0;
		int count4 = 0;
		int[] record = new int[25];
		for (int k=0; k<5; k++){
			for (int i=0; i < 5; i++){
				if (board[k][i] == player)
					record[k*5 + i] = 1;
			}
		}
		int i=0;
		while (i < 23){
			if (record[i] != 1  ){
				i++;
				continue;
			}
			if (record[i] == record [i+1] && record[i] == record [i+2] ){
				if((i%5) > 2){
					i++;
					continue;
				}
				if((i%5) < 2 && record[i+3]==1 ){ //3 points
					count4++;
					i+=4;
					continue;
				}
				//1 points
				count3++;
				i+=3;
				continue;
			}
			i++;
		}
		//System.out.printf("\nrow: count3=%d, count4=%d", count3, count4);
		int k=0;
		while (k<5){
			for (int row=0; row<3; row++){
				if (record[k + row*5] != 1  ){
					continue;
				}
				if (record[k + row*5] == record [k + row*5 + 5] && record[k + row*5] == record [k + row*5 + 10] ){
					if(row < 2 && record[k + row*5 + 15]==1 ){ //3 points
						count4++;
						break;
					}
					//1 points
					count3++;
					break;
				}
			}
			k++;
		}
		//System.out.printf("\ncolumn: count3=%d, count4=%d", count3, count4);
		for (int row=0; row<3; row++){
			if (record[ row*6] != 1  ){
				continue;
			}
			if (record[row*6] == record [ row*6 + 6] && record[row*6] == record [row*6 + 12] ){
				if(row < 2 && record[ row*6 + 18]==1 ){ //3 points
					count4++;
					break;
				}
				//1 points
				count3++;
				break;
			}
		}
		for (int row=0; row<3; row++){
			if (record[4 + row*4] != 1  ){
				continue;
			}
			if (record[4 + row*4] == record [4 + row*4 + 4] && record[4 + row*4] == record [4 + row*4 + 8] ){
				if(row < 2 && record[4 + row*4 + 12]==1 ){ //3 points
					count4++;
					break;
				}
				//1 points
				count3++;
				break;
			}
		}
		return count4 * 3 + count3 ;
	}
	public int[] moveByRandom(Cell player){
		int i = 0;
		if (counter == 24){
			for (; i<25; i++){
				if (emptyCell[i]==0){
					break;
				}
			}
		}
		else {
			int location = 1 + randomNumbers.nextInt(25 - counter);
			while (location != 0){
				if (emptyCell[i] == 0)
					location--;
				i++;
			}
			i--;
		}
		int x = i / 5;
		int y = i % 5;
		emptyCell[i] = 1;
		int[] result = {x, y};
		return result;
	}
}
