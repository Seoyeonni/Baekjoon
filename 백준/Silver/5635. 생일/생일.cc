#include <iostream>
#include <string>
using namespace std;

struct Student
{
	string name;
	int day;
	int month;
	int year;
};

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	Student* school = new Student[n];

	for (int i = 0; i < n; i++) {
		cin >> school[i].name >> school[i].day >> school[i].month >> school[i].year;
	}

	// 가장 나이가 적은 사람
	int idx, max_y = 0, max_m = 0, max_d = 0;
	for (int i = 0; i < n; i++) {
		if (max_y < school[i].year) {
			max_y = school[i].year;
			max_m = school[i].month;
			max_d = school[i].day;
			idx = i;
		}
		else if (max_y == school[i].year) {
			if (max_m < school[i].month) {
				max_y = school[i].year;
				max_m = school[i].month;
				max_d = school[i].day;
				idx = i;
			}
			else if (max_m == school[i].month) {
				if (max_d < school[i].day) {
					max_y = school[i].year;
					max_m = school[i].month;
					max_d = school[i].day;
					idx = i;
				}
			}
		}
	}
	cout << school[idx].name << endl;

	// 가장 나이가 많은 사람
	int min_y = 2011, min_m = 13, min_d = 32;
	idx = 0;
	for (int i = 0; i < n; i++) {
		if (min_y > school[i].year) {
			min_y = school[i].year;
			min_m = school[i].month;
			min_d = school[i].day;
			idx = i;
		}
		else if (min_y == school[i].year) {
			if (min_m > school[i].month) {
				min_y = school[i].year;
				min_m = school[i].month;
				min_d = school[i].day;
				idx = i;
			}
			else if (min_m == school[i].month) {
				if (min_d > school[i].day) {
					min_y = school[i].year;
					min_m = school[i].month;
					min_d = school[i].day;
					idx = i;
				}
			}
		}
	}
	cout << school[idx].name << endl;
}