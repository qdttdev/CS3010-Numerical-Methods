/*******************************************************************************
 * Name:		Caroline Ta
 * ID:			014 939 652
 * Date:		09.28.2020
 * Class:		CS3010.01
 * Assignment:	Programming Project 2 - Jacobi & Gaussian-Seidel
 *******************************************************************************/

#include <iostream> // cout
#include <vector>	// vector
#include <cmath>	// abs
#include <iomanip>	// setprecision
#include <fstream>	// read/write from/to files
#include <ios> 		// used to get stream size
#include <limits> 	// used to get numeric limits
using namespace std;

//7 1 -1 2 3
//1 8 0 -2 -5
//-1 0 4 -1 4
//2 -2 -1 6 -3
// Stopping error = 0.4

int main()
{
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

	/***********
	 ** INPUT **
	 ***********/

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

	cout << "\n-----------------------------------------------------------------"
		 << "\n                     GAUSS-SEIDEL METHOD"
		 << "\n-----------------------------------------------------------------\n";

	approxError = 999;

	iter = 0;
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

	cout << "\nThank you for using the program!\n";

	return 0;
}
