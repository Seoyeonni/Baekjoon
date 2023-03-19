#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);

	string str[5];

	for (int i = 0; i < 5; i++) {
		cin >> str[i];
	}

	for (int i = 0; i < 15; i++) {
		for (int j = 0; j < 5; j++) {
			if (i < str[j].size()) {
				cout << str[j][i];
			}
		}
	}

	cout << endl;
}