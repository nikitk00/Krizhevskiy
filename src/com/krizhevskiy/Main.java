package com.krizhevskiy;

import java.util.*;
import java.io.*;
import java.io.IOException;

public class Main {

	PrintWriter pw;
	Scanner sc;

	public static void main(String[] args) {
		new Main().run();
	}

	public void run() {

		try {
			sc = new Scanner(new File("input.txt"));
			int[] mas = new int[ 50 ];
			int[] mas1 = new int[ 50 ];
			int i = 0;
			while (sc.hasNext()) {
				mas[ i ] = sc.nextInt();
				i++;
			}
			mas1 = quickSort(mas, i);
			pw = new PrintWriter(new File("output.txt"));

			for (int j = 0; j < i; j++) {
				pw.println(mas1[ j ]);
				System.out.println(mas1[ j ]);
			}
			pw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public int[] bubbleSort(int[] m, int ml) {
		int checks = 0;
		int actions = 0;
		for (int i = 0; i < ml; i++)
			for (int j = ml-1; j > 0; j--) {
		checks++;
				if (m[ j ] < m[ j - 1 ]) {
					int b = m[ j ];
					m[ j ] = m[ j - 1 ];
					m[ j - 1 ] = b;
					actions++;
				}
			}
		System.out.println("bubble sort" + "\n" + "checks: " + checks + "\n" + "actions: " + actions);
		return m;
	}
	public int[] selectionSort (int[] m, int ml){
		int min, temp;
		int checks = 0;
		int actions = 0;
		for (int i = 0; i < ml-1; i++){
			min = i;
			for (int j = i+1; j < ml; j++)
				if (m[j] < m[min])
					min = j;
			temp = m[min];
			m[min] = m[i];
			m[i] = temp;
		}
		System.out.println("selection sort" + "\n" + "checks: " + checks + "\n" + "actions: " + actions);
		return m;
	}
	public int[] shakerSort(int[] m, int ml){
		int checks = 0;
		int actions = 0;

		int left = 0; // левая граница
		int right = ml - 1; // правая граница

		do
		{
			//Сдвигаем к концу массива "тяжелые элементы"
			for (int i = left; i < right; i++)
			{
				if(m[i] > m[i+1])
				{
					m[i] ^= m[i+1];
					m[i+1] ^= m[i];
					m[i] ^= m[i+1];
					actions++;
				}
				checks++;
			}
			right--; // уменьшаем правую границу
			//Сдвигаем к началу массива "легкие элементы"
			for (int i = right; i > left ; i--)
			{
				if(m[i] < m[i-1])
				{
					m[i] ^= m[i-1];
					m[i-1] ^= m[i];
					m[i] ^= m[i-1];
					actions++;
				}
				checks++;
			}
			left++; // увеличиваем левую границу
		} while (left <= right);
		System.out.println("shaker sort" + "\n" + "checks: " + checks + "\n" + "actions: " + actions);
		//for (int i : m) System.out.print(i + " "); // вывод массива на экран
		return m;
	}
	public int[] quickSort(int[] m, int ml) {
		int checks = 0;
		int actions = 0;
		m = qs(m, 0, ml-1, checks, actions);
		System.out.println("quick sort" + "\n" + "checks: " + checks + "\n" + "actions: " + actions);
		return m;
	}
	public int[] qs(int[] m, int start, int end, int ch, int act){
		if (start >= end) {
			return m;
		}
		ch++;
		int i = start, j = end;
		int cur = i - (i - j) / 2;
		while (i < j) {
			while (i < cur && (m[i] <= m[cur])) {
				i++;
			}
			while (j > cur && (m[cur] <= m[j])) {
				j--;
			}
			if (i < j) {
				int t = m[i];
				m[i] = m[j];
				m[j] = t;
				act++;
				if (i == cur)
					cur = j;
				else if (j == cur)
					cur = i;
			}
		}
		qs(m, start, cur, ch, act);
		qs(m, cur+1, end, ch, act);
		return m;
	}
}
