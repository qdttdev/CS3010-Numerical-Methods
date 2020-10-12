package com.company;
import static com.company.Equations.*;

public class FindRootUsing
{
    public static void Bisection(double a_n, double b_n, int equation)
    {
        final int MAX_ITERATIONS = 100;
        double a, b, c, fa, fb, fc, e, cPrev;

        a = a_n;
        b = b_n;
        c = calculateC_Bisection(a,b);

        fa = Equation_(a,equation);
        fb = Equation_(b,equation);
        fc = Equation_(c,equation);

        System.out.println("****************************************************");
        System.out.println("                 BISECTION METHOD");
        System.out.println("****************************************************");
        System.out.println();

        System.out.printf("%3s%12s%12s%12s%12s%12s%12s%12s\n","n","a","b","c","f(a)","f(b)","f(c)","e");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("%3d", 0);
        System.out.printf("%12.4f", a);
        System.out.printf("%12.4f", b);
        System.out.printf("%12.4f", c);
        System.out.printf("%12.4f", fa);
        System.out.printf("%12.4f", fb);
        System.out.printf("%12.4f", fc);
        System.out.printf("%12s\n","N/A");

        for(int i = 0; i < MAX_ITERATIONS; i++)
        {
            if(fa * fc < 0)
            {
                b = c;
            }
            else if (fa * fc > 0)
            {
                a = c;
            }
            else
            {
                break;
            }

            cPrev = c;
            c = calculateC_Bisection(a,b);
            fa = Equation_(a,equation);
            fb = Equation_(b,equation);
            fc = Equation_(c,equation);
            e = calculateError(c, cPrev);

            System.out.printf("%3d", i+1);
            System.out.printf("%12.4f", a);
            System.out.printf("%12.4f", b);
            System.out.printf("%12.4f", c);
            System.out.printf("%12.4f", fa);
            System.out.printf("%12.4f", fb);
            System.out.printf("%12.4f", fc);
            System.out.printf("%12.4f\n", e);

            if(e < 0.01)
            {
                break;
            }
        }
    }

    public static void FalsePosition(double a_n, double b_n, int equation)
    {
        final int MAX_ITERATIONS = 100;
        double a, b, c, fa, fb, fc, e, cPrev;

        a = a_n;
        b = b_n;
        fa = Equation_(a,equation);
        fb = Equation_(b,equation);

        c = calculateC_FalsePosition(a,b,fa,fb);
        fc = Equation_(c,equation);

        System.out.println("****************************************************");
        System.out.println("               FALSE POSITION METHOD");
        System.out.println("****************************************************");
        System.out.println();

        System.out.printf("%3s%12s%12s%12s%12s%12s%12s%12s\n","n","a","b","f(a)","f(b)","c","f(c)","e");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("%3d", 0);
        System.out.printf("%12.4f", a);
        System.out.printf("%12.4f", b);
        System.out.printf("%12.4f", fa);
        System.out.printf("%12.4f", fb);
        System.out.printf("%12.4f", c);
        System.out.printf("%12.4f", fc);
        System.out.printf("%12s\n","N/A");

        for(int i = 0; i < MAX_ITERATIONS; i++)
        {
            if(fa * fc < 0)
            {
                b = c;
                fb = Equation_(b,equation);
            }
            else if (fa * fc > 0)
            {
                a = c;
                fa = Equation_(a,equation);
            }
            else
            {
                break;
            }

            cPrev = c;
            c = calculateC_FalsePosition(a,b,fa,fb);
            fc = Equation_(c,equation);

            e = calculateError(c, cPrev);

            System.out.printf("%3d", i+1);
            System.out.printf("%12.4f", a);
            System.out.printf("%12.4f", b);
            System.out.printf("%12.4f", fa);
            System.out.printf("%12.4f", fb);
            System.out.printf("%12.4f", c);
            System.out.printf("%12.4f", fc);
            System.out.printf("%12.4f\n", e);

            if(e < 0.01)
            {
                break;
            }
        }
    }

    public static void NewtonRaphson(double x_0, int equation)
    {
        final int MAX_ITERATIONS = 100;
        double x_n, fx_n, fx_n_Not;
        double x_nPlus1, fx_nPlus1, fx_nPlus1_Not;
        double e;

        x_n = x_0;
        fx_n = Equation_(x_n,equation);
        fx_n_Not = Equation_(x_n,equation+100);

        x_nPlus1 = calculateX_nPlus1_NR(x_n, fx_n, fx_n_Not);
        fx_nPlus1 = Equation_(x_nPlus1,equation);
        fx_nPlus1_Not = Equation_(x_nPlus1,equation+100);

        System.out.println("****************************************************");
        System.out.println("               NEWTON RAPHSON METHOD");
        System.out.println("****************************************************");
        System.out.println();

        System.out.printf("%3s%12s%12s%12s%12s%12s%12s%12s\n","n","x_n","f(x_n)","f'(x_n)","x_n+1","f(x_n+1)","f'(x_n+1)","e");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("%3d", 0);
        System.out.printf("%12.4f", x_n);
        System.out.printf("%12.4f", fx_n);
        System.out.printf("%12.4f", fx_n_Not);
        System.out.printf("%12.4f", x_nPlus1);
        System.out.printf("%12.4f", fx_nPlus1);
        System.out.printf("%12.4f", fx_nPlus1_Not);
        System.out.printf("%12s\n","N/A");

        for(int i = 0; i < MAX_ITERATIONS; i++)
        {
            x_n = x_nPlus1;
            fx_n = fx_nPlus1;
            fx_n_Not = fx_nPlus1_Not;

            x_nPlus1 = calculateX_nPlus1_NR(x_n, fx_n, fx_n_Not);
            fx_nPlus1 = Equation_(x_nPlus1,equation);
            fx_nPlus1_Not = Equation_(x_nPlus1,equation+100);

            e = calculateError(x_nPlus1, x_n);

            System.out.printf("%3d", i+1);
            System.out.printf("%12.4f", x_n);
            System.out.printf("%12.4f", fx_n);
            System.out.printf("%12.4f", fx_n_Not);
            System.out.printf("%12.4f", x_nPlus1);
            System.out.printf("%12.4f", fx_nPlus1);
            System.out.printf("%12.4f", fx_nPlus1_Not);
            System.out.printf("%12.4f\n", e);

            if(e < 0.01)
            {
                break;
            }
        }
    }

    public static void Secant(double x_0, double x_1, int equation)
    {
        final int MAX_ITERATIONS = 100;
        double x_nMinus1, fx_nMinus1;
        double x_n, fx_n;
        double x_nPlus1, fx_nPlus1;
        double e;

        x_nMinus1 = x_0;
        fx_nMinus1 = Equation_(x_nMinus1,equation);

        x_n = x_1;
        fx_n = Equation_(x_n,equation);

        x_nPlus1 = calculateX_nPlus1_Secant(x_n, fx_n, x_nMinus1, fx_nMinus1);
        fx_nPlus1 = Equation_(x_nPlus1,equation);

        System.out.println("****************************************************");
        System.out.println("                   SECANT METHOD");
        System.out.println("****************************************************");
        System.out.println();

        System.out.printf("%3s%12s%12s%12s%12s%12s%12s%12s\n","n","x_n-1","f(x_n-1)","x_n","f(x_n)","x_n+1","f(x_n+1)","e");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("%3d", 0);
        System.out.printf("%12.4f", x_nMinus1);
        System.out.printf("%12.4f", fx_nMinus1);
        System.out.printf("%12.4f", x_n);
        System.out.printf("%12.4f", fx_n);
        System.out.printf("%12.4f", x_nPlus1);
        System.out.printf("%12.4f", fx_nPlus1);
        System.out.printf("%12s\n","N/A");

        for(int i = 0; i < MAX_ITERATIONS; i++)
        {
            x_nMinus1 = x_n;
            fx_nMinus1 = fx_n;

            x_n = x_nPlus1;
            fx_n = fx_nPlus1;

            x_nPlus1 = calculateX_nPlus1_Secant(x_n,fx_n,x_nMinus1,fx_nMinus1);
            fx_nPlus1 = Equation_(x_nPlus1,equation);

            e = calculateError(x_nPlus1, x_n);

            System.out.printf("%3d", i+1);
            System.out.printf("%12.4f", x_nMinus1);
            System.out.printf("%12.4f", fx_nMinus1);
            System.out.printf("%12.4f", x_n);
            System.out.printf("%12.4f", fx_n);
            System.out.printf("%12.4f", x_nPlus1);
            System.out.printf("%12.4f", fx_nPlus1);
            System.out.printf("%12.4f\n", e);

            if(e < 0.01)
            {
                break;
            }
        }
    }

    public static void SecantModified(double x, double delta, int equation)
    {
        final int MAX_ITERATIONS = 6;

        double x_n;
        double fx_n;
        double d;
        double dPlusx;
        double fdPlusx;
        double x_nPlus1;
        double fx_nPlus1;
        double e;
        double xPrev;

        x_n = x;
        fx_n = Equation_(x_n,equation);

        d = delta * x_n;
        dPlusx = d + x_n;

        fdPlusx = Equation_(dPlusx,equation);
        x_nPlus1 = calculateX_nPlus1_SecantModified(x_n,fx_n,fdPlusx,d);
        fx_nPlus1 = Equation_(x_nPlus1,equation);

        System.out.println("****************************************************");
        System.out.println("              MODIFIED SECANT METHOD");
        System.out.println("****************************************************");
        System.out.println();

        System.out.printf("%3s%12s%12s%12s%12s%12s%12s%12s\n","n","x_n","f(x_n)","d","d + x_n","f(d + x_n)","x_n+1","e");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("%3d", 0);
        System.out.printf("%12.4f", x_n);
        System.out.printf("%12.4f", fx_n);
        System.out.printf("%12.4f", d);
        System.out.printf("%12.4f", dPlusx);
        System.out.printf("%12.4f", fdPlusx);
        System.out.printf("%12.4f", x_nPlus1);
        System.out.printf("%12s\n","N/A");

        for(int i = 0; i < MAX_ITERATIONS; i++)
        {
            x_n = x_nPlus1;
            fx_n = fx_nPlus1;

            d = d * x_n;
            dPlusx = d + x_n;

            fdPlusx = Equation_(dPlusx,equation);
            x_nPlus1 = calculateX_nPlus1_SecantModified(x_n,fx_n,fdPlusx,d);
            fx_nPlus1 = Equation_(x_nPlus1,equation);

            e = calculateError(x_nPlus1, x_n);

            System.out.printf("%3d", i+1);
            System.out.printf("%12.4f", x_n);
            System.out.printf("%12.4f", fx_n);
            System.out.printf("%12.4f", d);
            System.out.printf("%12.4f", dPlusx);
            System.out.printf("%12.4f", fdPlusx);
            System.out.printf("%12.4f", x_nPlus1);
            System.out.printf("%12.4f\n", e);

            if(e < 0.01)
            {
                break;
            }
        }
    }
}
