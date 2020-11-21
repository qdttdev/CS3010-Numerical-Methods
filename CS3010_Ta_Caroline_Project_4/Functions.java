package com.company;

import java.util.ArrayList;

public class Functions
{
    // Generate Divided Difference Table
    static void dividedDiffTable(double[] x, double[][] y, int n)
    {
        for (int i = 1; i < n; i++)
        {
            for (int j = 0; j < n - i; j++)
            {
                y[j][i] = (y[j][i - 1] - y[j + 1][i - 1]) / (x[j] - x[i + j]);
            }
        }
    }

    // Print the Divided Difference Table
    static void printDiffTable(double[] x, double[][] y, int n)
    {
        System.out.println();
        System.out.println("--------------------------------------------------------");
        System.out.println("               DIVIDED DIFFERENCE TABLE");
        System.out.println("--------------------------------------------------------");
        for (int i = 0; i < n; i++)
        {
            System.out.printf("%-10.3f\t", x[i]);
            for (int j = 0; j < n - i; j++)
            {
                System.out.printf("%-10.3f\t", y[i][j]);
            }
            System.out.println();
        }
    }

    // Print the Interpolating Polynomial
    static void printIntPoly(double[] x, double[][] y, int n)
    {
        System.out.println();
        System.out.println("--------------------------------------------------------");
        System.out.println("                INTERPOLATING POLYNOMIAL");
        System.out.println("--------------------------------------------------------");
        for (int j = 0; j < n; j++)
        {
            System.out.printf("%.3f", y[0][j]);
            System.out.print(getMultiply(x,j));
            if(j + 1 < n)
            {
                System.out.print(" + ");
            }
        }
    }

    // Get the Multiplying terms
    static String getMultiply(double[] x, int index)
    {
        String mult = "";

        for(int i = 0; i < index; i++)
        {
            if(x[i] == 0)
            {
                mult += "(x)";
            }
            else
            {
                mult += "(x-" + x[i] + ")";
            }
        }

        return mult;
    }

    // Generate and Print the Simplified Polynomial
    static void printSimplifiedPoly(double[] x, double[][] y, int n)
    {
        Polynomial poly = new Polynomial();
        ArrayList<Double> result = new ArrayList<Double>();
        ArrayList<ArrayList<Double>> a = new ArrayList<ArrayList<Double>>();

        for(int i = 0; i < n; i++)
        {
            result.add(0.0);
        }

        result.add(0, y[0][0]);
        a.add(result);

        for(int i = 1; i < n; i++)
        {
            result = new ArrayList<Double>();
            double yValue = y[0][i];

            for(int j = 0; j < i; j++)
            {
                result.add(x[j]);
            }
            a.add(poly.polyFunction(yValue, result, n + 1));
        }

        result = poly.combineLikeTerms(a);

        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------------------------");
        System.out.println("                  SIMPLIFIED POLYNOMIAL");
        System.out.println("--------------------------------------------------------");
        System.out.println(printString(result));
    }

    // Print the polynomial as a string
    static String printString(ArrayList<Double> array)
    {
        String poly = "";
        String power = "";

        for(int i = array.size()-1; i >= 0; i--)
        {
            Double f = array.get(i);
            power = String.format("x^%d", i);

            if(i == 1)
            {
                power = "x";
            }

            if (f != 0)
            {
                if (i == 0)
                {
                    poly += String.format("%.3f", f);
                }
                else
                {
                    poly += String.format("%.3f%s + ", f, power);
                }
            }
        }

        return poly;
    }
}
