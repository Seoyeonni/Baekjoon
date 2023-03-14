#include <iostream>
#include <algorithm>
using namespace std;

int arr[500001];

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);

	int n, m, i, tmp;

	cin >> n;
	for (i = 0; i < n; i++) {
		cin >> arr[i];
	}

	sort(arr, arr + n);

	cin >> m;
	for (i = 0; i < m; i++) {
		cin >> tmp;
		cout << upper_bound(arr, arr + n, tmp) - lower_bound(arr, arr + n, tmp) << " ";
	}

	cout << "\n";
}