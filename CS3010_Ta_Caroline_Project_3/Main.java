package com.company;

import static com.company.Equations.*;
import static com.company.FindRootUsing.*;

public class Main {

    public static void main(String[] args)
    {
        final String BORDER = "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";
        final String EQ_0 = "Equation: f(x) = 9x^4 + 18x^3 + 38x^2 - 57x + 14";
        final String EQ_1 = "Equation: f(x) = 2x^3 - 11.7x^2 + 17.7x - 5";
        final String EQ_2 = "Equation: f(x) = x + 10 - xcosh(50/x)";
        final String EQ_3 = "Equation: f(x) = x^4 + 2x^3 - 7x^2 + 3";
        final String EQ_4 = "Equation: f(x) = x^5 + x^3 + 3";
        final String EQ_5 = "Equation: f(x) = e^-x - x";

//        System.out.println();
//        System.out.println(BORDER);
//        System.out.println("                     " + EQ_0);
//        System.out.println(BORDER);
//        System.out.println();
//        Bisection(0,0.5,0);
//        System.out.println();
//        FalsePosition(0,0.5,0);
//        System.out.println();
//        NewtonRaphson(0.5, 0);
//        System.out.println();
//        Secant(0.3,0.4,0);

        System.out.println();
        System.out.println(BORDER);
        System.out.println("                        " + EQ_1);
        System.out.println(BORDER);
        System.out.println();
        Bisection(0,1,1);
        System.out.println();
        FalsePosition(0,1,1);
        System.out.println();
        NewtonRaphson(0.5, 1);
        System.out.println();
        Secant(0,1,1);
        System.out.println();
        SecantModified(0.5,0.01,1);

        System.out.println();
        System.out.println(BORDER);
        System.out.println("                         " + EQ_2);
        System.out.println(BORDER);
        System.out.println();
        Bisection(126,127,2);
        System.out.println();
        FalsePosition(126,127,2);
        System.out.println();
        NewtonRaphson(126,2);
        System.out.println();
        Secant(126,127,2);
        System.out.println();
        SecantModified(126,0.01,2);

//        System.out.println();
//        System.out.println(BORDER);
//        System.out.println("                        " + EQ_3);
//        System.out.println(BORDER);
//        System.out.println();
//        NewtonRaphson(0.5, 3);

//        System.out.println();
//        System.out.println(BORDER);
//        System.out.println("                            " + EQ_4);
//        System.out.println(BORDER);
//        System.out.println();
//        Secant(1,-1,4);

//        System.out.println();
//        System.out.println(BORDER);
//        System.out.println("                        " + EQ_5);
//        System.out.println(BORDER);
//        System.out.println();
//        SecantModified(1, 0.01, 5);
    }
}
