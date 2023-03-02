#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <math.h>

int isPrime(int n) {
    int i;
    if (n < 2) return 0;
    if (n < 4) return 1;
    for (i = 2; i < (int)sqrt(n) + 1; i++) {
        if (n % i == 0) {
            return 0;
        }
    }
    return 1;
}

int main(void)
{
	int n, i, cnt = 0;
	int arr[100];

	scanf("%d", &n);

	for (i = 0; i < n; i++) {
		scanf("%d", &arr[i]);
	}

    for (i = 0; i < n; i++) {
        if (isPrime(arr[i]) == 1) {
            cnt++;
        }
    }
    printf("%d\n", cnt);


}