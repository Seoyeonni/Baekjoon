#include <stdio.h>

int N, M;
int arr[1000];
int check[1000];

void arrangement(int count)
{
    int i;

    if (count == M)
    {
        for (int i = 0; i < M; i++)
            printf("%d ", arr[i]);
        printf("\n");
    }
    else
    {
        for (i = 1; i <= N; i++)
        {
            if (check[i] == 0)
            {
                arr[count] = i;
                check[i] = 1;
                arrangement(count + 1);
                check[i] = 0;
            }
        }
    }
}

int main(void)
{
    scanf("%d %d", &N, &M);

    arrangement(0);

    return 0;
}