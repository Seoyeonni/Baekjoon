#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

int main()
{
	int grade;

	scanf("%d", &grade);

	if (grade >= 90 && grade <= 100) {
		printf("A\n");
	}
	else if (grade >= 80 && grade < 90) {
		printf("B\n");
	}
	else if (grade >= 70 && grade < 80) {
		printf("C\n");
	}
	else if (grade >= 60 && grade < 70) {
		printf("D\n");
	}
	else {
		printf("F\n");
	}

	return 0;
}