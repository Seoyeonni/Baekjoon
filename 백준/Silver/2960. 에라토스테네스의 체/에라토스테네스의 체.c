#include <stdio.h>
#include <stdlib.h>

int main()
{
	int n, k, i, j, idx = 0;

	scanf("%d %d", &n, &k);

	int* arr = (int*)malloc(sizeof(int) * (n + 1));

	for (i = 2; i <= n; i++) {
		arr[i] = i;
	}

	for (i = 2; i <= n; i++) {
		if (arr[i] == 0) {
			continue;
		}
		else {
			for (j = i; j <= n; j += i) {
				if (arr[j] != 0) {
					arr[j] = 0;
					idx++;
				}
				if (idx == k) {
					printf("%d\n", j);
					break;
				}
			}
		}
	}

	return 0;
}