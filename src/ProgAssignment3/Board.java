//  Weimin Ouyang, 7301005, ouyang@cs.ucsb.edu
//  Board.java
//  ProgAssignment3
//
//  Created by Tyler Weimin Ouyang on 11/23/14.
//  Copyright (c) 2014 Golden: Tyler Weimin Ouyang All rights reserved.
//

package ProgAssignment3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Board extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int[] Random, Human;
	/**
	 * Create the frame.
	 */
	public Board(int[] random, int[] human) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 300, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Random = random;
		Human = human;
	}
	public void paint(Graphics g){
		g.drawRect(25, 45, 250, 250);
		g.drawLine(25, 95, 275, 95);
		g.drawLine(25, 145, 275, 145);
		g.drawLine(25, 195, 275, 195);
		g.drawLine(25, 245, 275, 245);
		g.drawLine(75, 45, 75, 295);
		g.drawLine(125, 45, 125, 295);
		g.drawLine(175, 45, 175, 295);
		g.drawLine(225, 45, 225, 295);
		g.setColor(Color.blue);
		for (int i=0; i<75; i+=3){
			if (Random[i]==1){
				int x = 25+Random[i+2]*50;
				int y = 45+Random[i+1]*50;
				g.drawLine(x ,y, x+50, y+50 );
				g.drawLine(x+50 ,y, x, y+50 );
			}
		}
		g.setColor(Color.red);
		for (int i=0; i<75; i+=3){
			if (Human[i]==1){
				int x = 25+Human[i+2]*50;
				int y = 45+Human[i+1]*50;
				g.drawOval(x,y,50,50);
			}
		}
	}

}
