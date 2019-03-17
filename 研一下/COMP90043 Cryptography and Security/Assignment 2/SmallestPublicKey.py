def gcd(int1, int2):
	if int1 % int2 != 0:
		return gcd(int2, int1 % int2)
	return int2

def smallestPublicKey(integer):
	a = 2
	while gcd(integer, a) != 1:
		a += 1
	return a

a = smallestPublicKey(3447018795898540663933353891859333078116985288773547956464389519214137857142947840000)
print(a)
