package com.company;
import static com.company.Equations.*;

public class FindRootUsing
{
    public static int MAX_ITERATIONS = 100;
    public static double ERROR = 0.01;
    public static String SMALL_BORDER = "****************************************************";
    public static String BORDER = "---------------------------------------------------------------------------------------------------";
    public static String BISECTION_METHOD = "                 BISECTION METHOD";
    public static String FALSE_POSITION_METHOD = "               FALSE POSITION METHOD";
    public static String NEWTON_RAPHSON_METHOD = "               NEWTON RAPHSON METHOD";
    public static String SECANT_METHOD = "                   SECANT METHOD";
    public static String MODIFIED_SECANT_METHOD = "              MODIFIED SECANT METHOD";
    public static String COL_FORMAT = "%3s%12s%12s%12s%12s%12s%12s%12s%12s\n";

    public static void Bisection(double a_n, double b_n, int equation, double trueRoot)
    {
        double a, b, c, fa, fb, fc, approxErr, trueErr, cPrev;

        a = a_n;
        b = b_n;
        c = calculateC_Bisection(a,b);

        fa = Equation_(a,equation);
        fb = Equation_(b,equation);
        fc = Equation_(c,equation);

        trueErr = calculateTrueError(trueRoot, c);

        System.out.println(SMALL_BORDER);
        System.out.println(BISECTION_METHOD);
        System.out.println(SMALL_BORDER);

        System.out.printf(COL_FORMAT,"n","a_n","b_n","c_n","f(a_n)","f(b_n)","f(c_n)","approx e","true e");
        System.out.println(BORDER);
        System.out.printf("%3d", 0);
        System.out.printf("%12.4f", a);
        System.out.printf("%12.4f", b);
        System.out.printf("%12.4f", c);
        System.out.printf("%12.4f", fa);
        System.out.printf("%12.4f", fb);
        System.out.printf("%12.4f", fc);
        System.out.printf("%12s","N/A");
        System.out.printf("%12.4f\n",trueErr);

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
            approxErr = calculateApproxError(c, cPrev);
            trueErr = calculateTrueError(trueRoot, c);

            System.out.printf("%3d", i+1);
            System.out.printf("%12.4f", a);
            System.out.printf("%12.4f", b);
            System.out.printf("%12.4f", c);
            System.out.printf("%12.4f", fa);
            System.out.printf("%12.4f", fb);
            System.out.printf("%12.4f", fc);
            System.out.printf("%12.4f", approxErr);
            System.out.printf("%12.4f\n", trueErr);

            if(approxErr < ERROR)
            {
                break;
            }
        }
    }

    public static void FalsePosition(double a_n, double b_n, int equation, double trueRoot)
    {
        double a, b, c, fa, fb, fc, approxErr, trueErr, cPrev;

        a = a_n;
        b = b_n;
        fa = Equation_(a,equation);
        fb = Equation_(b,equation);

        c = calculateC_FalsePosition(a,b,fa,fb);
        fc = Equation_(c,equation);

        trueErr = calculateTrueError(trueRoot, c);

        System.out.println(SMALL_BORDER);
        System.out.println(FALSE_POSITION_METHOD);
        System.out.println(SMALL_BORDER);

        System.out.printf(COL_FORMAT,"n","a_n","b_n","f(a_n)","f(b_n)","c_n","f(c_n)","approx e","true e");
        System.out.println(BORDER);
        System.out.printf("%3d", 0);
        System.out.printf("%12.4f", a);
        System.out.printf("%12.4f", b);
        System.out.printf("%12.4f", fa);
        System.out.printf("%12.4f", fb);
        System.out.printf("%12.4f", c);
        System.out.printf("%12.4f", fc);
        System.out.printf("%12s","N/A");
        System.out.printf("%12.4f\n", trueErr);

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

            approxErr = calculateApproxError(c, cPrev);
            trueErr = calculateTrueError(trueRoot, c);

            System.out.printf("%3d", i+1);
            System.out.printf("%12.4f", a);
            System.out.printf("%12.4f", b);
            System.out.printf("%12.4f", fa);
            System.out.printf("%12.4f", fb);
            System.out.printf("%12.4f", c);
            System.out.printf("%12.4f", fc);
            System.out.printf("%12.4f", approxErr);
            System.out.printf("%12.4f\n", trueErr);

            if(approxErr < ERROR)
            {
                break;
            }
        }
    }

    public static void NewtonRaphson(double x_0, int equation, double trueRoot)
    {
        double x_n, fx_n, fx_n_Not;
        double x_nPlus1, fx_nPlus1, fx_nPlus1_Not;
        double approxErr, trueErr;

        x_n = x_0;
        fx_n = Equation_(x_n,equation);
        fx_n_Not = Equation_(x_n,equation+100);

        x_nPlus1 = calculateX_nPlus1_NR(x_n, fx_n, fx_n_Not);
        fx_nPlus1 = Equation_(x_nPlus1,equation);
        fx_nPlus1_Not = Equation_(x_nPlus1,equation+100);

        approxErr = calculateApproxError(x_nPlus1, x_n);
        trueErr = calculateTrueError(trueRoot, x_nPlus1);

        System.out.println(SMALL_BORDER);
        System.out.println(NEWTON_RAPHSON_METHOD);
        System.out.println(SMALL_BORDER);

        System.out.printf(COL_FORMAT,"n","x_n","f(x_n)","f'(x_n)","x_n+1","f(x_n+1)","f'(x_n+1)","approx e","true e");
        System.out.println(BORDER);
        System.out.printf("%3d", 0);
        System.out.printf("%12.4f", x_n);
        System.out.printf("%12.4f", fx_n);
        System.out.printf("%12.4f", fx_n_Not);
        System.out.printf("%12.4f", x_nPlus1);
        System.out.printf("%12.4f", fx_nPlus1);
        System.out.printf("%12.4f", fx_nPlus1_Not);
        System.out.printf("%12.4f", approxErr);
        System.out.printf("%12.4f\n", trueErr);

        for(int i = 0; i < MAX_ITERATIONS; i++)
        {
            x_n = x_nPlus1;
            fx_n = fx_nPlus1;
            fx_n_Not = fx_nPlus1_Not;

            x_nPlus1 = calculateX_nPlus1_NR(x_n, fx_n, fx_n_Not);
            fx_nPlus1 = Equation_(x_nPlus1,equation);
            fx_nPlus1_Not = Equation_(x_nPlus1,equation+100);

            approxErr = calculateApproxError(x_nPlus1, x_n);
            trueErr = calculateTrueError(trueRoot, x_nPlus1);

            System.out.printf("%3d", i+1);
            System.out.printf("%12.4f", x_n);
            System.out.printf("%12.4f", fx_n);
            System.out.printf("%12.4f", fx_n_Not);
            System.out.printf("%12.4f", x_nPlus1);
            System.out.printf("%12.4f", fx_nPlus1);
            System.out.printf("%12.4f", fx_nPlus1_Not);
            System.out.printf("%12.4f", approxErr);
            System.out.printf("%12.4f\n", trueErr);

            if(approxErr < ERROR)
            {
                break;
            }
        }
    }

    public static void Secant(double x_0, double x_1, int equation, double trueRoot)
    {
        double x_nMinus1, fx_nMinus1;
        double x_n, fx_n;
        double x_nPlus1, fx_nPlus1;
        double approxErr, trueErr;

        x_nMinus1 = x_0;
        fx_nMinus1 = Equation_(x_nMinus1,equation);

        x_n = x_1;
        fx_n = Equation_(x_n,equation);

        x_nPlus1 = calculateX_nPlus1_Secant(x_n, fx_n, x_nMinus1, fx_nMinus1);
        fx_nPlus1 = Equation_(x_nPlus1,equation);

        approxErr = calculateApproxError(x_nPlus1, x_n);
        trueErr = calculateTrueError(trueRoot, x_nPlus1);

        System.out.println(SMALL_BORDER);
        System.out.println(SECANT_METHOD);
        System.out.println(SMALL_BORDER);

        System.out.printf(COL_FORMAT,"n","x_n-1","f(x_n-1)","x_n","f(x_n)","x_n+1","f(x_n+1)","approx e","true e");
        System.out.println(BORDER);
        System.out.printf("%3d", 0);
        System.out.printf("%12.4f", x_nMinus1);
        System.out.printf("%12.4f", fx_nMinus1);
        System.out.printf("%12.4f", x_n);
        System.out.printf("%12.4f", fx_n);
        System.out.printf("%12.4f", x_nPlus1);
        System.out.printf("%12.4f", fx_nPlus1);
        System.out.printf("%12.4f", approxErr);
        System.out.printf("%12.4f\n", trueErr);

        for(int i = 0; i < MAX_ITERATIONS; i++)
        {
            x_nMinus1 = x_n;
            fx_nMinus1 = fx_n;

            x_n = x_nPlus1;
            fx_n = fx_nPlus1;

            x_nPlus1 = calculateX_nPlus1_Secant(x_n,fx_n,x_nMinus1,fx_nMinus1);
            fx_nPlus1 = Equation_(x_nPlus1,equation);

            approxErr = calculateApproxError(x_nPlus1, x_n);
            trueErr = calculateTrueError(trueRoot, x_nPlus1);

            System.out.printf("%3d", i+1);
            System.out.printf("%12.4f", x_nMinus1);
            System.out.printf("%12.4f", fx_nMinus1);
            System.out.printf("%12.4f", x_n);
            System.out.printf("%12.4f", fx_n);
            System.out.printf("%12.4f", x_nPlus1);
            System.out.printf("%12.4f", fx_nPlus1);
            System.out.printf("%12.4f", approxErr);
            System.out.printf("%12.4f\n", trueErr);

            if(approxErr < ERROR)
            {
                break;
            }
        }
    }

    public static void SecantModified(double x, double delta, int equation, double trueRoot)
    {
        double x_n, fx_n;
        double d, dPlusx, fdPlusx;
        double x_nPlus1, fx_nPlus1;
        double approxErr, trueErr;

        x_n = x;
        fx_n = Equation_(x_n,equation);

        d = delta * x_n;
        dPlusx = d + x_n;

        fdPlusx = Equation_(dPlusx,equation);
        x_nPlus1 = calculateX_nPlus1_SecantModified(x_n,fx_n,fdPlusx,d);
        fx_nPlus1 = Equation_(x_nPlus1,equation);

        approxErr = calculateApproxError(x_nPlus1, x_n);
        trueErr = calculateTrueError(trueRoot, x_nPlus1);

        System.out.println(SMALL_BORDER);
        System.out.println(MODIFIED_SECANT_METHOD);
        System.out.println(SMALL_BORDER);

        System.out.printf(COL_FORMAT,"n","x_n","f(x_n)","d","d + x_n","f(d + x_n)","x_n+1","approx e","true e");
        System.out.println(BORDER);
        System.out.printf("%3d", 0);
        System.out.printf("%12.4f", x_n);
        System.out.printf("%12.4f", fx_n);
        System.out.printf("%12.4f", d);
        System.out.printf("%12.4f", dPlusx);
        System.out.printf("%12.4f", fdPlusx);
        System.out.printf("%12.4f", x_nPlus1);
        System.out.printf("%12.4f", approxErr);
        System.out.printf("%12.4f\n", trueErr);

        for(int i = 0; i < MAX_ITERATIONS; i++)
        {
            x_n = x_nPlus1;
            fx_n = fx_nPlus1;

            d = d * x_n;
            dPlusx = d + x_n;

            fdPlusx = Equation_(dPlusx,equation);
            x_nPlus1 = calculateX_nPlus1_SecantModified(x_n,fx_n,fdPlusx,d);
            fx_nPlus1 = Equation_(x_nPlus1,equation);

            approxErr = calculateApproxError(x_nPlus1, x_n);
            trueErr = calculateTrueError(trueRoot, x_nPlus1);

            System.out.printf("%3d", i+1);
            System.out.printf("%12.4f", x_n);
            System.out.printf("%12.4f", fx_n);
            System.out.printf("%12.4f", d);
            System.out.printf("%12.4f", dPlusx);
            System.out.printf("%12.4f", fdPlusx);
            System.out.printf("%12.4f", x_nPlus1);
            System.out.printf("%12.4f", approxErr);
            System.out.printf("%12.4f\n", trueErr);

            if(approxErr < ERROR)
            {
                break;
            }
        }
    }
}
