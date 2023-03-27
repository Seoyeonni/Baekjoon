#include <iostream>
using namespace std;

int main() {
    int arr[10];
    for(int i = 1; i < 10; i++) {
        cin >> arr[i];
    }
    
    int max = 0, idx = 0;
    for (int i = 1; i < 10; i++) {
        if (max < arr[i]) {
            max = arr[i];
            idx = i;
        }
    }
    cout << max << '\n' << idx << '\n';
}