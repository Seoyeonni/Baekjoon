#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

struct info

{
	int age;
	string name;
};

bool compare(const info& a, const info& b) {
	return a.age < b.age;
}

info person[100001];
int main(void)
{
	info person[100001] = { 0 };
	int n, i;

	cin >> n;

	for (i = 0; i < n; i++) {
		cin >> person[i].age >> person[i].name;
	}

	stable_sort(person, person + n, compare);

	for (i = 0; i < n; i++) {
		cout << person[i].age << " " << person[i].name << "\n";
	}
}