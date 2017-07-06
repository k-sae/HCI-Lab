// ADUN.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <string>
#include <string.h>
using namespace std;


int main()
{
	/* ADUN

	int x = 0, z;
	cin >> z;
	if (z > 0 && z < 2100000000) {
		x += z;
		cin >> z;
		if (z > 0 && z < 2100000000) {
			x += z;
			cout << x;
		}
	}
	*/


	int counter ,index ;
	string word;
	cin >> counter;
	while (counter>0)
	{
		cin >> index >> word;
		cout << word[2];


		counter--;
	}
	
    return 0;
}

