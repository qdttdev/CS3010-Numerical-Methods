# CS3010 Numerical Methods

### Project 1 implemented in C++
[CS3010_Ta_Caroline_Project_1](https://github.com/qdttdev/CS3010_Numerical_Methods/tree/master/CS3010_Ta_Caroline_Project_1) is a program that asks the user for the number of linear equations to solve, using Gaussian elimination with Scaled Partial Pivoting method.

Sample Run:
```
Would you like to input the matrix through command line or text file?
[0] - Exit the Program
[1] - Command Line
[2] - Text File

Enter choice: 2

Enter file name: testCase_1.txt

Enter the number of equations: 3

Scale vectors: s = [3, 2, 3]

Ratio: r = {0.67, 0.50, 1.00}
The largest ratio found is 1.00 so we choose R3 and swap with R1

The matrix after R1 <-> R3
3.00	0.00	2.00	9.00	
-1.00	2.00	-1.00	0.00	
2.00	3.00	0.00	8.00	

The matrix after scaled partial pivoting:
3.00	0.00	2.00	9.00	
0.00	2.00	-0.33	3.00	
0.00	3.00	-1.33	2.00	

Scale vectors: s = [3, 2, 3]

Ratio: r = {1.00, 1.00}
The largest ratio found is 1.00 so we choose R2 and swap with R2 (matrix stays the same)

The matrix after R2 <-> R2
3.00	0.00	2.00	9.00	
0.00	2.00	-0.33	3.00	
0.00	3.00	-1.33	2.00	

The matrix after scaled partial pivoting:
3.00	0.00	2.00	9.00	
0.00	2.00	-0.33	3.00	
0.00	0.00	-0.83	-2.50	

The solution of the matrix:
x1 = 1.00
x2 = 2.00
x3 = 3.00

Thank you for using the program!
```

### Project 2 implemented in C++
[CS3010_Ta_Caroline_Project_2](https://github.com/qdttdev/CS3010_Numerical_Methods/tree/master/CS3010_Ta_Caroline_Project_2) is a program that asks the user for the number of linear equations to solve, using Jacobi Iterative method and Gauss-Seidel method.

Sample Run:
```
Would you like to input the matrix through command line or text file?
[0] - Exit the Program
[1] - Command Line
[2] - Text File

Enter choice: 1

Enter the number of equations: 3

Enter the coefficients:
5 -1 0 7
-1 3 -1 4
0 -1 2 5

Enter the desired stopping error: 0.4

Enter the starting solution: 0 0 0

We have the following matrix:
 5	-1	 0	 7	
-1	 3	-1	 4	
 0	-1	 2	 5	

-----------------------------------------------------------------
                   JACOBI ITERATIVE METHOD
-----------------------------------------------------------------

Iteration #1:	[1.4  1.33333  2.5] T
Error: 1 < 0.4 (False)

Iteration #2:	[1.66667  2.63333  3.16667] T
Error: 0.484061 < 0.4 (False)

Iteration #3:	[1.92667  2.94444  3.81667] T
Error: 0.302472 < 0.4 (True)

-----------------------------------------------------------------
                     GAUSS-SEIDEL METHOD
-----------------------------------------------------------------

Iteration #1:	[1.4  1.8  3.4] T
Error: 1 < 0.4 (False)

Iteration #2:	[1.76  3.05333  4.02667] T
Error: 0.270368 < 0.4 (True)

Thank you for using the program!
```

### Project 3 implemented in Java
[CS3010_Ta_Caroline_Project_3](https://github.com/qdttdev/CS3010_Numerical_Methods/tree/master/CS3010_Ta_Caroline_Project_3) is a program that find roots using Bisection, Newton-Raphson, Secant, False-Position, and Modified Secant methods.

Sample Run:
```
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
Equation: f(x) = 2x^3 - 11.7x^2 + 17.7x - 5
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

Consider true root of this equation to be x_1 = 0.36509

****************************************************
BISECTION METHOD
****************************************************
n a_n b_n c_n f(a_n) f(b_n) f(c_n) approx e true e
---------------------------------------------------------------------------------------------------
0 0.0000 1.0000 0.5000 -5.0000 3.0000 1.1750 N/A 0.1349
1 0.0000 0.5000 0.2500 -5.0000 1.1750 -1.2750 1.0000 0.1151
2 0.2500 0.5000 0.3750 -1.2750 1.1750 0.0977 0.3333 0.0099
3 0.2500 0.3750 0.3125 -1.2750 0.0977 -0.5503 0.2000 0.0526
4 0.3125 0.3750 0.3438 -0.5503 0.0977 -0.2169 0.0909 0.0213
5 0.3438 0.3750 0.3594 -0.2169 0.0977 -0.0573 0.0435 0.0057
6 0.3594 0.3750 0.3672 -0.0573 0.0977 0.0208 0.0213 0.0021
7 0.3594 0.3672 0.3633 -0.0573 0.0208 -0.0181 0.0108 0.0018
8 0.3633 0.3672 0.3652 -0.0181 0.0208 0.0014 0.0053 0.0001
```

### Project 4 implemented in Java
[CS3010_Ta_Caroline_Project_4](https://github.com/qdttdev/CS3010_Numerical_Methods/tree/master/CS3010_Ta_Caroline_Project_4) is a program that creates a divided difference table from the given data in a text file and uses that to create the interpolating polynomial.

Sample Run:
```
********************************************************
               DIVIDED DIFFERENCE TABLE
********************************************************

x           f[]         f[,]        f[,,]       f[,,,]      f[.....]
--------------------------------------------------------------------
0.000     	1.000     	8.000     	3.000     	1.000     	-0.000
1.000     	9.000     	14.000    	7.000     	1.000
2.000     	23.000    	35.000    	12.000
4.000     	93.000    	83.000
6.000     	259.000

********************************************************
                INTERPOLATING POLYNOMIAL
********************************************************
1.000 + 8.000(x) + 3.000(x)(x-1.0) + 1.000(x)(x-1.0)(x-2.0) + -0.000(x)(x-1.0)(x-2.0)(x-4.0)

********************************************************
                  SIMPLIFIED POLYNOMIAL
********************************************************
1.000x^3 + 7.000x + 1.000
```
