public int[] ExtendedEuclid (int a, int b) {
	int i = -1;
	int dividend = 0;
	int divisor = 0;
	int quotient = 0;
	int remainder = -1;
	int x1 = 1, x2 = 0;
	int y1 = 0, y2 = 1;
	int gcd = 0;


	if (a > b) {
		dividend = a;
		divisor = b;
	} else{
		dividend = b;
		divisor = a;
	}

	while (remainder != 0) {
		gcd = remainder;
		quotient = dividend/divisor;
		remainder = dividend%divisor;
		int temp1 = x1 - x2 * quotient;
		x1 = x2;
		x2 = temp1;
		int temp2 = y1 - y2 * quotient;
		y1 = y2;
		y2 = temp2;
		dividend = divisor;
		divisor = remainder;
	}

	if (a > b) {
			int[] answer = {x1,y1,gcd};
			return answer;
		} else {
			int[] answer = {y1,x1,gcd};
			return answer;
		}
}