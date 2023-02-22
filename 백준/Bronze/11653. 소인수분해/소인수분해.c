#define _CRT_SECURE_NO_WARNING
#include <stdio.h>

int main()
{
	int n, i, j;

	scanf("%d", &n);

	for (i = 2; i <= n; i++) {
		while (n % i == 0) {
			printf("%d\n", i);
			n /= i;
		}
	}

	return 0;
}