#include <iostream>
using namespace std;

int main()
{
	int n, m, i, j, k, sum = 0, temp = 0;
	int arr[100];

	cin >> n >> m;

	for (i = 0; i < n; i++) {
		cin >> arr[i];
	}

	for (i = 0; i < n - 2; i++) {
		for (j = i + 1; j < n - 1; j++) {
			for (k = j + 1; k < n; k++) {
				sum = arr[i] + arr[j] + arr[k];
				if (sum > temp && sum <= m) {
					temp = sum;
				}
			}
		}
	}

	cout << temp << "\n";

	return 0;
}