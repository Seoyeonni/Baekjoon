#define _CRT_SECURE_NO_WARNINGS
#include <math.h>
#include <stdio.h>
#include <string.h>

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

int isPalindrome(int n) {
    int i, l;
    char arr[10];

    sprintf(arr, "%d", n);
    l = strlen(arr);

    if (l < 2) return 1;

    for (i = 0; i < l / 2; i++) {
        if (arr[i] != arr[l - 1 - i]) {
            return 0;
        }
    }
    return 1;
}

int main() {
    int n;
    scanf("%d", &n);

    for (;;n++) {
        if (isPrime(n) && isPalindrome(n)) {
            printf("%d\n", n);
            return 0;
        }
    }
}