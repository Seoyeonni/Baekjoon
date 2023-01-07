#include <stdio.h>
#include <stdlib.h>
#include <limits.h> // 자료형의 최댓값과 최솟값이 정의된 헤더 파일

typedef struct {
	int num, idx;
} point;

int compare(const void* a, const void* b)
{
	if (*(int*)a > *(int*)b)
		return 1;
	else if (*(int*)a < *(int*)b)
		return -1;
	else
		return 0;
}

int main() {
	int n;

	scanf("%d", &n); // N입력

	point* arr = (point*)malloc(sizeof(point) * n);

	for (int i = 0; i < n; i++) {
		scanf("%d", &arr[i].num); // 좌표 num 저장
		arr[i].idx = i; // 좌표 num의 인덱스 저장
	}

	qsort(arr, n, sizeof(point), compare); // 오름차순 정렬

	int* result = (int*)malloc(sizeof(int) * n);
	int cnt = -1, min = INT_MIN;

	for (int i = 0; i < n; i++) {
		if (arr[i].num != min) {
			min = arr[i].num;
			cnt++;
		}
		result[arr[i].idx] = cnt;
	}

	for (int i = 0; i < n; i++) {
		printf("%d ", result[i]);
	}
}