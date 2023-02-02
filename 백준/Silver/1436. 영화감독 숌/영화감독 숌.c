#include <stdio.h>
#include <math.h>

int main()
{
	int n, i, cnt = 0, temp = 665;

	scanf("%d", &n); // n번째로 작은 종말의 숫자

	while (n != cnt) {
		temp++;
		for (i = 0; i < 10; i++) {
			if (temp / (int)pow(10, i) % 1000 == 666) {
				cnt++;
				break;
			}
		}
	}

	printf("%d\n", temp);

	return 0;
}