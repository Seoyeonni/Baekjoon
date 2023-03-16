#include <iostream>
#include <algorithm>
using namespace std;

int n_arr[100001];
int m_arr[100001];

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);

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
		cout << binary_search(n_arr, n_arr + n, m_arr[i]) << "\n";
	}
}