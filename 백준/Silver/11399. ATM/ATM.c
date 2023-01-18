#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int compare(const void* a, const void* b)
{
	if (*(int*)a > *(int*)b)
		return 1;
	else if (*(int*)a < *(int*)b)
		return -1;
	else
		return 0;
}

int main()
{
	int n, i, j, sum = 0, time = 0;

	scanf("%d", &n); // n명의 사람

	int* arr = (int*)malloc(sizeof(int) * n);

	for (i = 0; i < n; i++) {
		scanf("%d", &arr[i]);
	}

	qsort(arr, n, sizeof(int), compare);

	for (i = 0; i < n; i++) {
		for (j = 0; j < i + 1; j++) {
			sum += arr[j];
		}
		time += sum;
		sum = 0;
	}

	printf("%d\n", time);

	return 0;
}