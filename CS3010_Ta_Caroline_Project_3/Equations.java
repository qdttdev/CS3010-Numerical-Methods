package com.company;

public class Equations
{
    static double Equation_(double x, int equation)
    {
        if(equation == 0)
        {
            return Equation0(x);
        }
        else if(equation == 100)
        {
            return Equation100(x);
        }
        else if(equation == 1)
        {
            return Equation1(x);
        }
        else if(equation == 101)
        {
            return Equation101(x);
        }
        else if(equation == 2)
        {
            return Equation2(x);
        }
        else if(equation == 102)
        {
            return Equation102(x);
        }
        else if(equation == 3)
        {
            return Equation3(x);
        }
        else if(equation == 103)
        {
            return Equation103(x);
        }
        else if(equation == 4)
        {
            return Equation4(x);
        }
        else if(equation == 104)
        {
            return Equation104(x);
        }
        else if(equation == 5)
        {
            return Equation5(x);
        }
        else
        {
            return 0;
        }
    }

    static double Equation0(double x)
    {
        // 9x^4 + 18x^3 + 38x^2 - 57x + 14
        return 9*Math.pow(x,4) + 18*Math.pow(x,3) + 38*Math.pow(x,2) - 57*x + 14;
    }

    static double Equation100(double x)
    {
        // 9x^4 + 18x^3 + 38x^2 - 57x + 14
        // 36x^3 + 54x^2 + 76x - 57
        return 36*Math.pow(x,3) + 54*Math.pow(x,2) + 76*x - 57;
    }

    static double Equation1(double x)
    {
        // 2x^3 - 11.7x^2 + 17.7x - 5
        return 2*Math.pow(x,3) - 11.7*Math.pow(x,2) + 17.7*x - 5;
    }

    static double Equation101(double x)
    {
        // 2x^3 - 11.7x^2 + 17.7x - 5
        // 6x^2 - 23.4x + 17.7
        return 6*Math.pow(x,2) - 23.4*x + 17.7;
    }

    static double Equation2(double x)
    {
        // x + 10 - xcosh(50/x)
        return x + 10 - x*Math.cosh(50/x);
    }

    static double Equation102(double x)
    {
        // x + 10 - xcosh(50/x)
        // (50sinh(50/x)/x - cosh(50/x) + 1
        return (50*Math.sinh(50/x))/x - Math.cosh(50/x) + 1;
    }

    static double Equation3(double x)
    {
        // x^4 + 2x^3 - 7x^2 + 3
        return Math.pow(x,4) + 2*Math.pow(x,3) - 7*Math.pow(x,2) + 3;
    }

    static double Equation103(double x)
    {
        // x^4 + 2x^3 - 7x^2 + 3
        // 4x^3 + 6x^2 - 14x
        return 4*Math.pow(x,3) + 6*Math.pow(x,2) - 14*x;
    }

    static double Equation4(double x)
    {
        // x^5 + x^3 + 3
        return Math.pow(x,5) + Math.pow(x,3) + 3;
    }

    static double Equation104(double x)
    {
        // x^5 + x^3 + 3
        // 5x^4 + 3x^2
        return 5*Math.pow(x,4) + 3*Math.pow(x,2);
    }

    static double Equation5(double x)
    {
        // e^-x - x
        // e = 2.718
        return Math.exp(-x) - x;
    }

    static double calculateC_Bisection(double a, double b)
    {
        // c = (a+b)/2
        return (a+b)/2;
    }

    static double calculateC_FalsePosition(double a, double b, double fa, double fb)
    {
        // c = (a.fb - b.fa) / (fb - fa)
        return (a*fb - b*fa) / (fb - fa);
    }

    static double calculateX_nPlus1_NR(double x_n, double fx_n, double fx_n_Not)
    {
        return x_n - (fx_n / fx_n_Not);
    }

    static double calculateX_nPlus1_Secant(double x_n, double fx_n, double x_nMinus1, double fx_nMinus1)
    {
        return x_n - ((x_n - x_nMinus1) / (fx_n - fx_nMinus1)) * fx_n;
    }

    static double calculateX_nPlus1_SecantModified(double x_n, double fx_n, double fdPlusx, double d)
    {
        return x_n - ( d * fx_n / (fdPlusx - fx_n)) ;
    }

    static double calculateError(double c_n, double c_nMinus1)
    {
        return Math.abs((c_n - c_nMinus1)/c_n);
    }
}
