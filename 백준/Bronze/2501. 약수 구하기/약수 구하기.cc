#include<iostream>
using namespace std;

int main()
{
	int n, k, i, cnt = 0, ans = 0;

	cin >> n >> k;

	for (i = 1; i <= n; i++) {
		if (n % i == 0) {
			cnt++;
			ans = i;
		}
		if (cnt == k) {
			cout << ans;
			break;
		}
	}

	if (cnt < k) {
		cout << "0";
	}

	return 0;
}