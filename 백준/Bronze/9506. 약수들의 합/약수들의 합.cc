#include <iostream>
using namespace std;

int main()
{
	while (1) {
	int n, i, idx = 0, sum = 0, arr[50000];
		cin >> n;

		if (n == -1) {
			break;
		}

		for (i = 1; i < n; i++) {
			if (n % i == 0) {
				arr[idx++] = i;
				sum += i;
			}
		}

		if (sum != n) {
			cout << n << " is NOT perfect." << "\n";
		}
		else if (sum == n) {
			cout << n << " = ";

			for (i = 0; i < idx; i++) {
				if (i == idx - 1) {
					cout << arr[i] << "\n";
				}
				else {
					cout << arr[i] << " + ";
				}
				arr[i] = 0;
			}
		}
	}
}