#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main()
{
	int n, result = 0, i;

	for (i = 0; i < 5; i++) {
		scanf("%d", &n);
		result += n * n;
	}

	printf("%d\n", result % 10);

	return 0;
}