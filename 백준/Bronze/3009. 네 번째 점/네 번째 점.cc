#include <iostream>
using namespace std;

int main() {
    int x, y, x1, y1, x2, y2, x3, y3;
    cin >> x1 >> y1 >> x2 >> y2 >> x3 >> y3;
    
    if (x1 == x2) {
        x = x3;
        if (y1 == y3) {
            y = y2;
        }
        else if (y2 == y3) {
            y = y1;
        }
    }
    else if (x1 == x3) {
        x = x2;
        if (y1 == y2) {
            y = y3;
        }
        else if (y2 == y3) {
            y = y1;
        }
    }
    else if (x2 == x3) {
        x = x1;
        if (y1 == y2) {
            y = y3;
        }
        if (y1 == y3) {
            y = y2;
        }
    }
    
    cout << x << ' ' << y;
}