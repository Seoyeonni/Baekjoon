#include <stdio.h>
#include <stdlib.h>

int arr[1000000] = { 0 };

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
	int n, i;

	scanf("%d", &n); // N개의 수 입력

	// N개의 정수 입력
	for (i = 0; i < n; i++) {
		scanf("%d", &arr[i]);
	}

	qsort(arr, n, sizeof(int), compare);// 오름차순 정렬

	// 결과 출력
	for (i = 0; i < n; i++) {
		printf("%d\n", arr[i]);
	}

	return 0;
}