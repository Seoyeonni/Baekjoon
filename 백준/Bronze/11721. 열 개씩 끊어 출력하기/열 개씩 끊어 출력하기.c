#include <stdio.h>
#include <string.h>

int main()
{
	int i;
	char str[100];

	scanf("%s", str);

	for (i = 0; i < strlen(str); i++) {
		printf("%c", str[i]);
		if ((i + 1) % 10 == 0) {
			printf("\n");
		}
	}

	return 0;
}