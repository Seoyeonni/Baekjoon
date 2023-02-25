#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

int main() {
	int n, i, j;
	int arr[246913] = { 0 };
	arr[0] = arr[1] = 1;

	for (i = 2; i < 246913; i++) {
		if (!arr[i]) {
			for (j = i * 2; j < 246913; j += i) {
				arr[j] = 1;
			}
		}
	}

	while (1) {
		int cnt = 0;

		scanf("%d", &n);

		if (!n) {
			break;
		}

		for (i = n + 1; i <= 2 * n; i++) {
			if (!arr[i]) {
				cnt++;
			}
		}

		printf("%d\n", cnt);
	}

	return 0;
}