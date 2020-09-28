/*******************************************************************************
 * Name:		Caroline Ta
 * ID:			014 939 652
 * Date:		09.25.2020
 * Class:		CS3010.01
 * Assignment:	Programming Project 1 - Gaussian Elimination Partial Pivoting
 *******************************************************************************/

#include <iostream> // cout
#include <vector>	// vector
#include <cmath>	// abs
#include <iomanip>	// setprecision
#include <fstream>	// read/write from/to files
#include <ios> 		// used to get stream size
#include <limits> 	// used to get numeric limits
using namespace std;

int main()
{
	const int MAX_ROW = 10;
	const int MAX_COL = 11;

	int row = 0;
	int col = 0;
	int inputChoice;
	string fileName = "";
	double input = 0;
	double pivot = 0;
	double temp = 0;
	double xMult = 0;
	double maxRatio = 0;
	int maxRatioPos = 0;
	fstream f;

	vector<char> solVar = {'x','y','z','u','v','o','p'};
	vector<double> solution(MAX_ROW, 0);
	vector<int> maxOfRow(MAX_ROW, 0);
	vector<double> ratio(MAX_COL, 0);
	vector<vector<double>> matrix(MAX_ROW, vector<double>(MAX_COL, 0));

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
	}

	cout << setprecision(2) << fixed;

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

	// Output the solutions
	cout << "\nThe solution of the matrix:" << endl;
	for(int i = 0; i < row; i++)
	{
		cout << solVar[i] << " = " << round(solution[i]) << endl;
	}

	cout << "\nThank you for using the program!\n";

	return 0;
}
