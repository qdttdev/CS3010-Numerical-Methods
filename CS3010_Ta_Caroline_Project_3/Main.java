/*******************************************************************************
 * Name:		Caroline Ta
 * ID:			014 939 652
 * Date:		10.11.2020
 * Class:		CS3010.01 - Numerical Methods
 * Assignment:	Programming Project 3 - Finding Roots with 5 Numerical Methods
 *******************************************************************************/
package com.company;

import static com.company.FindRootUsing.*;

public class Main {

    public static void main(String[] args)
    {
        final String BORDER = "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";
        final String EQ_0 = "Equation: f(x) = 9x^4 + 18x^3 + 38x^2 - 57x + 14";
        final String EQ_1 = "Equation: f(x) = 2x^3 - 11.7x^2 + 17.7x - 5";
        final String EQ_2 = "Equation: f(x) = x + 10 - xcosh(50/x)";
        final String EQ_3 = "Equation: f(x) = x^4 + 2x^3 - 7x^2 + 3";
        final String EQ_4 = "Equation: f(x) = x^5 + x^3 + 3";
        final String EQ_5 = "Equation: f(x) = e^-x - x";

        final double TRUE_ROOT_1_EQ_1 = 0.36509;
        final double TRUE_ROOT_2_EQ_1 = 1.92174;
        final double TRUE_ROOT_3_EQ_1 = 3.56316;
        final double TRUE_ROOT_EQ_2 = 126.632;

        System.out.println();
        System.out.println(BORDER);
        System.out.println("                        " + EQ_1);
        System.out.println(BORDER);
        System.out.println();

        System.out.println("\tConsider true root of this equation to be x_1 = " + TRUE_ROOT_1_EQ_1 + "\n");
        Bisection(0,1,1, TRUE_ROOT_1_EQ_1);
        System.out.println();
        FalsePosition(0,1,1, TRUE_ROOT_1_EQ_1);
        System.out.println();
        NewtonRaphson(0, 1, TRUE_ROOT_1_EQ_1);
        System.out.println();
        Secant(0,1,1, TRUE_ROOT_1_EQ_1);
        System.out.println();
        SecantModified(0.5,0.01,1, TRUE_ROOT_1_EQ_1);

        System.out.println();
        System.out.println("\tConsider true root of this equation to be x_2 = " + TRUE_ROOT_2_EQ_1 + "\n");
        Bisection(1.5,2,1, TRUE_ROOT_2_EQ_1);
        System.out.println();
        FalsePosition(1.5,2,1, TRUE_ROOT_2_EQ_1);
        System.out.println();
        NewtonRaphson(1.5, 1, TRUE_ROOT_2_EQ_1);
        System.out.println();
        Secant(1.5,2,1, TRUE_ROOT_2_EQ_1);
        System.out.println();
        SecantModified(1.5,0.01,1, TRUE_ROOT_2_EQ_1);

        System.out.println();
        System.out.println("\tConsider true root of this equation to be x_3 = " + TRUE_ROOT_3_EQ_1 + "\n");
        Bisection(3.5,4,1, TRUE_ROOT_3_EQ_1);
        System.out.println();
        FalsePosition(3.5,4,1, TRUE_ROOT_3_EQ_1);
        System.out.println();
        NewtonRaphson(3.5, 1, TRUE_ROOT_3_EQ_1);
        System.out.println();
        Secant(3.5,4,1, TRUE_ROOT_3_EQ_1);
        System.out.println();
        SecantModified(3.5,0.01,1, TRUE_ROOT_3_EQ_1);


        System.out.println();
        System.out.println(BORDER);
        System.out.println("                         " + EQ_2);
        System.out.println(BORDER);
        System.out.println();
        System.out.println("\tConsider true root of this equation to be: " + TRUE_ROOT_EQ_2 + "\n");
        Bisection(123,127,2, TRUE_ROOT_EQ_2);
        System.out.println();
        FalsePosition(123,127,2, TRUE_ROOT_EQ_2);
        System.out.println();
        NewtonRaphson(123,2, TRUE_ROOT_EQ_2);
        System.out.println();
        Secant(123,127,2, TRUE_ROOT_EQ_2);
        System.out.println();
        SecantModified(123,0.01,2, TRUE_ROOT_EQ_2);

//        System.out.println();
//        System.out.println(BORDER);
//        System.out.println("                        " + EQ_3);
//        System.out.println(BORDER);
//        System.out.println();
//        NewtonRaphson(1.5, 3);
//
//        System.out.println();
//        System.out.println(BORDER);
//        System.out.println("                            " + EQ_4);
//        System.out.println(BORDER);
//        System.out.println();
//        Secant(1,-1,4);
//
//        System.out.println();
//        System.out.println(BORDER);
//        System.out.println("                        " + EQ_5);
//        System.out.println(BORDER);
//        System.out.println();
//        SecantModified(1, 0.01, 5);
    }
}
