#include <stdio.h>

int main()
{
	int n, k, i, cnt = 0;
	int arr[10] = { 0 };

	scanf("%d %d", &n, &k); // n개의 종류, k원

	for (i = 0; i < n; i++) {
		scanf("%d", &arr[i]);
	}
	/*arr[0] = 1;
	for (i = 0; i < n; i++) {
		if (i % 2 == 0) {
			printf("%d\n", arr[i]);
			arr[i + 1] = arr[i] * 5;
		}
		else {
			printf("%d\n", arr[i]);
			arr[i + 1] = arr[i] * 2;
		}
	}*/

	for (i = n - 1; i >= 0; i--) {
		if (k >= 1) {
			cnt += k / arr[i];
			k = k % arr[i];
		}
	}

	printf("%d\n", cnt);

	return 0;
}