#include <stdio.h>
#include <math.h>

int main()
{
	int T, x, y, dst = 0, root = 0;

	scanf("%d", &T);
	while (T--) {
		scanf("%d %d", &x, &y);

		dst = y - x;
		root = sqrt(dst);

		if (dst == pow(root, 2)) {
			printf("%d\n", 2 * root - 1);
		}
		else if (pow(root, 2) < dst && dst <= root + pow(root, 2)) {
			printf("%d\n", 2 * root);
		}
		else if (root + pow(root, 2) < dst && dst < pow((root + 1), 2)) {
			printf("%d\n", 2 * root + 1);
		}
	}

	return 0;
}