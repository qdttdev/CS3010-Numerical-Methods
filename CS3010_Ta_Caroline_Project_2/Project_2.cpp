/*******************************************************************************
 * Name:		Caroline Ta
 * ID:			014 939 652
 * Date:		09.28.2020
 * Class:		CS3010.01 - Numerical Methods
 * Assignment:	Programming Project 2 - Jacobi & Gaussian-Seidel (Extra Credit)
 *******************************************************************************/

#include <iostream> // cout
#include <vector>	// vector
#include <cmath>	// abs
#include <iomanip>	// setprecision
#include <fstream>	// read/write from/to files
#include <ios> 		// used to get stream size
#include <limits> 	// used to get numeric limits
#include <cstdlib>
#include <ctime>
#include <chrono>

using namespace std;
using namespace std::chrono; // using namesapce std::chrono

//7 1 -1 2 3
//1 8 0 -2 -5
//-1 0 4 -1 4
//2 -2 -1 6 -3
// Stopping error = 0.4

int main()
{
	// Project 2 Variables
	const int	MAX_ROW = 100;
	const int	MAX_COL = 101;

	int			row = 0;
	int			col = 0;
	int			inputChoice = 0;
	int			iter = 0;
	double 		stopError = 0;
	double		approxError = 999;
	double		errorNumerator = 0;
	double		errorDenominator = 0;
	double 		input = 0;

	double		jacobiTimer = 0;
	double		gaussSeidelTimer = 0;
	double		gaussPartialTimer = 0;

	string 		fileName = "";
	fstream 	f;

	vector<double> rowVarJacobi (MAX_ROW, 0);
	vector<double> gaussJacobi (MAX_ROW, 0);

	vector<double> rowVarSeidel (MAX_ROW, 0);
	vector<double> gaussSeidel (MAX_ROW, 0);
	vector<double> gaussSeidelTemp (MAX_ROW, 0);

	vector<double> previous(MAX_ROW, 0);
	vector<double> current(MAX_ROW, 0);

	vector<vector<double>> matrix(MAX_ROW, vector<double>(MAX_COL, 0));

	// Project 1 Variables
	double pivot = 0;
	double temp = 0;
	double xMult = 0;
	double maxRatio = 0;
	int maxRatioPos = 0;

	vector<char> solVar = {'x','y','z','u','v','o','p'};
	vector<double> solution(MAX_ROW, 0);
	vector<int> maxOfRow(MAX_ROW, 0);
	vector<double> ratio(MAX_COL, 0);

	/***********
	 ** INPUT **
	 ***********/

	cout << "\n*******************************************************************************"
		 << "\n* Name:       Caroline Ta"
		 << "\n* Date:       09.28.2020"
		 << "\n* Class:      CS3010.01 - Numerical Methods"
		 << "\n* Assignment: Programming Project 2 - Jacobi & Gaussian-Seidel (Extra Credit)"
		 << "\n*******************************************************************************\n\n";

	cout << "Would you like to input the matrix through command line or text file?\n"
		 << "[0] - Exit the Program\n"
		 << "[1] - Command Line\n"
		 << "[2] - Text File\n"
		 << "\nEnter choice: ";
	cin  >> inputChoice;
	cin.ignore(numeric_limits<streamsize>::max(), '\n');

	if(inputChoice == 0) // Exit
	{
		cout << "\nThank you for using the program!\n";
		return 0;
	}
	else if(inputChoice == 1) // Command Line
	{
		cout << "\nEnter the number of equations: ";
		cin >> row;
		col = row + 1;

		cout << "\nEnter the coefficients:\n";

		// Get coefficients as inputs
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
			{
				cin >> input;
				matrix[i][j] = input;
			}
		}
	}
	else // Text File
	{
		cout << "\nEnter file name: ";
		getline(cin, fileName);

		cout << "\nEnter the number of equations: ";
		cin >> row;
		col = row + 1;

		f.open(fileName);

		while(!f.eof())
		{
			// Get coefficients as inputs
			for(int i = 0; i < row; i++)
			{
				for(int j = 0; j < col; j++)
				{
					f >> input;
					matrix[i][j] = input;
				}
			}
		}

		f.close();
	}

	cout << "\nEnter the desired stopping error: ";
	cin  >> stopError;

	cout << "\nEnter the starting solution: ";
	for(int i = 0; i < row; i++)
	{
		cin >> input;
		gaussJacobi[i] = input;
		gaussSeidel[i] = input;
		gaussSeidelTemp[i] = input;
	}

	/****************
	 ** PROCESSING **
	 ****************/

	cout << "\nWe have the following matrix:\n";
	for(int i = 0; i < row; i++)
	{
		for(int j = 0; j < col; j++)
		{
			if(matrix[i][j] >= 0)
			{
				cout << " ";
			}
			cout << matrix[i][j] << "\t";
		}
		cout << endl;
	}

	// a = 1/matrix[1][1] * (matrix[1][5] - matrix[1][4] - matrix[1][3] - matrix[1][2] - matrix[1][1] + matrix[1][1])
	// b = 1/matrix[2][2] * (matrix[2][5] - matrix[2][4] - matrix[2][3] - matrix[2][2] - matrix[2][1] + matrix[2][2])
	// c = 1/matrix[3][3] * (matrix[3][5] - matrix[3][4] - matrix[3][3] - matrix[3][2] - matrix[3][1] + matrix[3][3])
	// d = 1/matrix[4][4] * (matrix[4][5] - matrix[4][4] - matrix[4][3] - matrix[4][2] - matrix[4][1] + matrix[4][4])

	cout << "\n-----------------------------------------------------------------"
		 << "\n                   JACOBI ITERATIVE METHOD"
		 << "\n-----------------------------------------------------------------\n";

	high_resolution_clock::time_point t1 = high_resolution_clock::now();
	while(approxError >= stopError)
	{
		iter++;

		for(int i = 0; i < row; i++)
		{
			rowVarJacobi[i] = matrix[i][col-1];

			for(int j = col-2; j >= 0; j--)
			{
				rowVarJacobi[i] -= (matrix[i][j] * gaussJacobi[j]);
			}

			rowVarJacobi[i] += matrix[i][i] * gaussJacobi[i];
			rowVarJacobi[i] *= 1/matrix[i][i];
		}

		// Update Gaussian vector and output values
		cout << "\nIteration #" << iter << ":\t[";
		for(int i = 0; i < row; i++)
		{
			previous[i] = gaussJacobi[i];
			current[i] = rowVarJacobi[i];

			gaussJacobi[i] = rowVarJacobi[i];
			cout << gaussJacobi[i];

			if(i+1 < row)
			{
				cout << "  ";
			}
		}
		cout << "] T\n";


		// Find Error at each Iteration
		cout << "Error: ";
		for(int i = 0; i < row; i++)
		{
			errorNumerator += (current[i] - previous[i])*(current[i] - previous[i]);
			errorDenominator += (current[i]*current[i]);
		}

		errorNumerator = sqrt(errorNumerator);
		errorDenominator = sqrt(errorDenominator);

		approxError = errorNumerator/errorDenominator;

		cout << approxError << " < " << stopError;
		if(approxError < stopError)
		{
			cout << " (True)\n";
		}
		else
		{
			cout << " (False)\n";
		}

		if(iter == 50)
		{
			cout << "\n>>> The error was not reached.\n";
			break;
		}

	} // end while(approxError >= stopError)
	high_resolution_clock::time_point t2 = high_resolution_clock::now();

	auto durationJ = duration_cast<microseconds>(t2 - t1).count();

	jacobiTimer = durationJ * 0.000001;

	cout.unsetf(ios::fixed);

	cout << "\n[Press Enter to Continue: Gauss-Seidel Method]";
	cin.get();
	cin.ignore(numeric_limits<streamsize>::max(), '\n');

	cout << "\n-----------------------------------------------------------------"
		 << "\n                     GAUSS-SEIDEL METHOD"
		 << "\n-----------------------------------------------------------------\n";

	approxError = 999;
	iter = 0;

	high_resolution_clock::time_point t3 = high_resolution_clock::now();
	while(approxError >= stopError)
	{
		iter++;

		for(int i = 0; i < row; i++)
		{
			rowVarSeidel[i] = matrix[i][col-1];

			for(int j = col-2; j >= 0; j--)
			{
				rowVarSeidel[i] -= (matrix[i][j] * gaussSeidel[j]);
			}

			rowVarSeidel[i] += matrix[i][i] * gaussSeidel[i];
			rowVarSeidel[i] *= 1/matrix[i][i];

			// Update found value to use to find next
			gaussSeidelTemp[i] = gaussSeidel[i];
			gaussSeidel[i] = rowVarSeidel[i];
		}

		// Update Gaussian vector and output values
		cout << "\nIteration #" << iter << ":\t[";
		for(int i = 0; i < row; i++)
		{
			previous[i] = gaussSeidelTemp[i];
			current[i] = rowVarSeidel[i];

			gaussSeidel[i] = rowVarSeidel[i];
			cout << gaussSeidel[i];

			if(i+1 < row)
			{
				cout << "  ";
			}
		}
		cout << "] T\n";

		// Find Error at each Iteration
		errorNumerator = 0;
		errorDenominator = 0;
		cout << "Error: ";
		for(int i = 0; i < row; i++)
		{
			errorNumerator += (current[i] - previous[i])*(current[i] - previous[i]);
			errorDenominator += (current[i]*current[i]);
		}

		errorNumerator = sqrt(errorNumerator);
		errorDenominator = sqrt(errorDenominator);

		approxError = errorNumerator/errorDenominator;

		cout << approxError << " < " << stopError;
		if(approxError < stopError)
		{
			cout << " (True)\n";
		}
		else
		{
			cout << " (False)\n";
		}

		if(iter == 50)
		{
			cout << "\n>>> The error was not reached.\n";
			break;
		}

	} // end while(approxError >= stopError)
	high_resolution_clock::time_point t4 = high_resolution_clock::now();

	auto durationGS = duration_cast<microseconds>(t4 - t3).count();

	gaussSeidelTimer = durationGS * 0.000001;

	cout.unsetf(ios::fixed);

	cout << "\n[Press Enter to Continue: Gaussian Elimination with Scaled Partial Pivoting]";
	cin.get();

	cout << "\n-----------------------------------------------------------------"
		 << "\n          GAUSS ELIMINATION WITH SCALED PARTIAL PIVOTING"
		 << "\n-----------------------------------------------------------------\n";

	cout << setprecision(2) << fixed;

	high_resolution_clock::time_point t5 = high_resolution_clock::now();
	for(int k = 0; k < row-1; k++)
	{
		maxRatio = 0;

		if(k == 0) // Scale vectors stay the same
		{
			// Find scale vectors
			for(int i = 0; i < row; i++)
			{
				for(int j = 0; j < col-1; j++)
				{
					if(abs(matrix[i][j]) > maxOfRow[i])
					{
						maxOfRow[i] = abs(matrix[i][j]);
					}
				}
			}
		}

		// Output scale vectors
		cout << "\nScale vectors: s = [";

		for(int i = 0; i < row; i++)
		{
			cout << maxOfRow[i];

			if(i+1 < row)
			{
				cout << ", ";
			}
		}

		cout << "]\n";

		// Calculate and output the ratio
		cout << "\nRatio: r = {";

		for(int i = k; i < row; i++)
		{
			ratio[i] = abs(matrix[i][k] / double(maxOfRow[i]));
			cout << ratio[i];

			if(ratio[i] > maxRatio)
			{
				maxRatio = ratio[i];
				maxRatioPos = i;
			}

			if(i+1 < row)
			{
				cout << ", ";
			}
		}

		cout << "}\n";

		cout << "The largest ratio found is " << maxRatio
			 << " so we choose R" << maxRatioPos+1
			 << " and swap with R" << k+1;
		if(maxRatioPos == k)
		{
			cout << " (matrix stays the same)";
		}
		cout << endl;

		// Swapping R(k) and R(maxRatioPos)
		for(int j = 0; j < col; j++)
		{
			temp = matrix[k][j];
			matrix[k][j] = matrix[maxRatioPos][j];
			matrix[maxRatioPos][j] = temp;
		}

		// Since we're swapping rows, we also have to swap scale vectors
		temp = maxOfRow[k];
		maxOfRow[k] = maxOfRow[maxRatioPos];
		maxOfRow[maxRatioPos] = temp;

		// Output the matrix after swapping
		cout << "\nThe matrix after R" << k+1 << " <-> R" << maxRatioPos+1 << endl;
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
			{
				cout << matrix[i][j] << "\t";
			}

			cout << endl;
		}
		cout << endl;

		pivot = double(matrix[k][k]);

		// pivot * xMult + num = 0 --> xMult = -num / pivot
		for(int i = k+1; i < row; i++)
		{
			xMult = -matrix[i][k] / double(pivot);

			for(int j = 0; j < col; j++)
			{
				matrix[i][j] += double(matrix[k][j]) * xMult;
			}
		}

		// Output the matrix after partial scaling
		cout << "The matrix after scaled partial pivoting:" << endl;
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
			{
				cout << matrix[i][j] << "\t";
			}

			cout << endl;
		}

	} // End for loop of Gaussian partial pivoting

	// Back substitution
	for(int i = row-1; i >= 0; i--)
	{
		int c = 0;
		for(int j=i; j <= row-1; j++)
		{
			c = c + matrix[i][j] * solution[j];
		}

		solution[i]=(matrix[i][row]-c)/matrix[i][i];
	}
	high_resolution_clock::time_point t6 = high_resolution_clock::now();

	// Output the solutions
	cout << "\nThe solution of the matrix:" << endl;
	for(int i = 0; i < row; i++)
	{
		cout << "x" << i+1 << " = " << round(solution[i]) << endl;
	}

	auto durationGP = duration_cast<microseconds>(t6 - t5).count();

	gaussPartialTimer = durationGP * 0.000001;

	cout << setprecision(20) << fixed;

	cout << "\nIt took the program "  << jacobiTimer
		 << " seconds to execute Jacobi Iterative Method.\n";

	cout << "\nIt took the program " << gaussSeidelTimer
		 << " seconds to execute Gauss-Seidel Method.\n";

	cout << "\nIt took the program " << gaussPartialTimer
		 << " seconds to execute Gaussian Elimination with Scaled Partial Pivoting Method.\n";

	cout.unsetf(ios::fixed);

	cout << "\nThank you for using the program! [Press enter to Close the Program]\n";

	cin.get();

	return 0;
}
