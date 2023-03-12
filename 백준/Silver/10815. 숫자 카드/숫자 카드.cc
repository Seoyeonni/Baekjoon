#include <iostream>
#include <algorithm>
using namespace std;

int n_arr[500001];
int m_arr[500001];

bool binary_search(int n, int k)
{
	int start = 0, end = n - 1, mid;

	while (start <= end) {
		mid = (start + end) / 2;

		if (n_arr[mid] == k) {
			return 1;
		}
		else if (n_arr[mid] > k) {
			end = mid - 1;
		}
		else {
			start = mid + 1;
		}
	}

	return 0;
}

int main()
{
	int n, m, i;

	cin >> n;
	for (i = 0; i < n; i++) {
		cin >> n_arr[i];
	}

	cin >> m;
	for (i = 0; i < m; i++) {
		cin >> m_arr[i];
	}

	sort(n_arr, n_arr + n);

	for (i = 0; i < m; i++) {
		cout << binary_search(n, m_arr[i]) << " ";
	}
	cout << "\n";

}