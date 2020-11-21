/*******************************************************************************
 * Name:       Caroline Ta
 * ID:         014 939 652
 * Date:       11.20.2020
 * Class:      CS3010.01 - Numerical Methods
 * Assignment: Programming Project 4 - Divided Difference Table
 *******************************************************************************/
package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static com.company.Functions.*;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        // CONSTANTS
        final int MAX_NODE = 50;
        final String INPUT_FILE_1 = "src\\com\\company\\input1.txt";
        final String INPUT_FILE_2 = "src\\com\\company\\input2.txt";
        final String INPUT_FILE_3 = "src\\com\\company\\input3.txt";

        // VARIABLES
        int size = 0;
        double[] x = new double[MAX_NODE];
        double[][] y = new double[MAX_NODE][MAX_NODE];
        String line = "";
        String[] lineAr;

        // PROCESSING
        File inputFile = new File(INPUT_FILE_3);
        Scanner scanner = new Scanner (inputFile);

        // Get the first line of input into x[], which contains x values
        line = scanner.nextLine();
        lineAr = line.split(" ");
        for(int i = 0; i < lineAr.length; i++)
        {
            x[i] = Double.parseDouble(lineAr[i]);
        }

        // Get the second line of input into y[][], which contains the y values
        line = scanner.nextLine();
        lineAr = line.split(" ");
        for(int i = 0; i < lineAr.length; i++)
        {
            y[i][0] = Double.parseDouble(lineAr[i]);
        }

        size = lineAr.length;

        // Generate the Divided Difference Table
        dividedDiffTable(x, y, size);
        // Print the Divided Difference Table
        printDiffTable(x, y, size);
        // Print the Interpolating Polynomial
        printIntPoly(x, y, size);
        // Print the Simplified Polynomial
        printSimplifiedPoly(x, y, size);

        System.out.println();
    }
}
