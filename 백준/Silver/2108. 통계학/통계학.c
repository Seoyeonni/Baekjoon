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

int arith(int arr[], int n)
{
	double sum = 0;

	for (int i = 0; i < n; i++)
	{
		sum += (arr[i]);
	}

	return round(sum / n); // 반올림
}

int median(int arr[], int n)
{
	return arr[(n + 1) / 2 - 1];
}

int mode(int arr[], int n)
{
	int ar[8001] = { 0 };
	int i, idx, max = 0, cnt = 0;

	// 최빈값 찾기
	for (i = 0; i < n; i++)
	{
		idx = arr[i] + 4000;
		ar[idx] += 1;

		if (ar[idx] > max)
			max = ar[idx];
	}

	// 최빈값이 여러개일 때
	for (i = 0, idx = 0; i < 8001; i++)
	{
		if (ar[i] == 0)
			continue;

		if (ar[i] == max)
		{
			if (cnt == 0)
			{
				idx = i;
				cnt += 1;
			}
			else if (cnt == 1)
			{
				idx = i;
				break;
			}
		}
	}

	return idx - 4000;
}

int range(int arr[], int n)
{
	int max = arr[n - 1];
	int min = arr[0];

	return max - min;
}

int main()
{
	int n, i;
	int* arr;

	scanf("%d", &n); // N개의 수 입력
	arr = (int*)calloc(n, sizeof(int));

	// N개의 정수 입력
	for (i = 0; i < n; i++) {
		scanf("%d", &arr[i]);
	}

	qsort(arr, n, sizeof(int), compare); // 오름차순 정렬

	printf("%d\n", arith(arr, n));
	printf("%d\n", median(arr, n));
	printf("%d\n", mode(arr, n));
	printf("%d\n", range(arr, n));

	return 0;
}