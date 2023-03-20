#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main()
{
	int h, m;

	scanf("%d %d", &h, &m);

	if (m >= 45) { // h 신경 쓸 필요 없는 경우
		printf("%d %d\n", h, m - 45);
	}
	else {
		if (h == 0) {
			printf("23 %d\n", 60 + (m - 45));
		}
		else {
			printf("%d %d\n", h - 1, 60 - (-1) * (m - 45));
		}
	}

	return 0;
}