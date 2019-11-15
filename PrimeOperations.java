import java.util.ArrayList; 
import java.math.BigInteger;

/*
 *  Desc: This class generates primes, twin primes, and hexagon crosses using BigInteger data types.
 */
public class PrimeOperations {
	
	// Pair class implementation.
	private class Pair<T> {
		T a,b;
	}
	
	// Member variables for containing out lists of integers, twin primes, hexagon crosses, and the pairs of twin primes that make up the hex crosses.
	ArrayList<BigInteger> primes = new ArrayList<BigInteger>();
	ArrayList<Pair> twinPrimes = new ArrayList<Pair>();
	ArrayList<Pair> hexCrosses = new ArrayList<Pair>();
	
	// Add a prime to the prime list if and only iff it is not already in the list. (ignore duplicates)
	public void addPrime(BigInteger x)
	{
		if (primes.size() == 0) {
			primes.add(x);
		}
		else {
			int i = 0;
			while (i < primes.size()) {
				if (x.compareTo(primes.get(i)) == 0) {
					break;
				}
				else {
					i++;
				}
			}
			if (i == primes.size()) {
				primes.add(x);
			}
		}
	}
	
	// Output the prime list. Each prime should be on a separate line and the total number of primes should be on the following line.
	public void printPrimes()
	{
		int i = 0;
		while (i < primes.size()) {
			System.out.println(primes.get(i));
			i++;
		}
		System.out.println("Total Primes: " + i);
	}
		
	// Output the twin prime list. Each twin prime should be on a separate line with a comma separating them, and the total number of twin primes should be on the following line.
	public void printTwins()
	{
		int i = 0;
		while (i < twinPrimes.size()) {
			System.out.println(twinPrimes.get(i).a + ", " + twinPrimes.get(i).b);
			i++;
		}
		System.out.println("Total Twins: " + i);
	}
		
	// Output the hexagon cross list. Each should be on a separate line listing the two twin primes and the corresponding hexagon cross, with the total number on the following line.
	public void printHexes()
	{
		int i = 0;
		while (i < hexCrosses.size()) {
			Pair<BigInteger> x = new Pair<BigInteger>();
			x =	hexCrosses.get(i);
			BigInteger a1 = x.a.subtract(BigInteger.valueOf(1));
			BigInteger a2 = x.a.add(BigInteger.valueOf(1));
			BigInteger b1 = x.b.subtract(BigInteger.valueOf(1));
			BigInteger b2 = x.b.add(BigInteger.valueOf(1));
			System.out.println("Prime Pairs: " + a1 + ", " + a2 + " and " + b1 + ", " + b2 + " separated by " + x.a + ", " + x.b);
			i++;
		}
		System.out.println("Total Hexes: " + i);
	}
		
	// Generate and store a list of primes.
	public void generatePrimes(int count)
	{
		int i = 0;
		BigInteger l,m,n,o;
		l = BigInteger.valueOf(2);
		m = BigInteger.valueOf(2);
		n = BigInteger.valueOf(0);
		o = BigInteger.valueOf(1);
		while (i < count) {
			while (l.compareTo(m) == 1) {
				if (l.mod(m) == n) {
					l = l.add(o);
					m = BigInteger.valueOf(2);
				} else {
					m = m.add(o);
				}
			}
			if (l.compareTo(m) == 0) {
				addPrime(l);
				l = l.add(o);
				m = BigInteger.valueOf(2);
				i++;
			}
		}
	}

				
	
	
	// Generate and store a list of twin primes.
	public void generateTwinPrimes()
	{
		int i = 1;
		while (i < primes.size()) {
			BigInteger x,y;
			x = primes.get(i).subtract(primes.get(i-1));
			y = BigInteger.valueOf(2);
			if (x.compareTo(y) == 0) {
				Pair<BigInteger> pair = new Pair<BigInteger>();
				pair.a = primes.get(i-1);
				pair.b = primes.get(i);
				twinPrimes.add(pair);
			}
			i++;
		}
	}
	
	// Generate and store the hexagon crosses, along with the two twin primes that generate the hexagon cross.
	public void generateHexPrimes()
	{
		int i = 0;
		while (i < twinPrimes.size()) {
			Pair<BigInteger> x = new Pair<BigInteger>();
			x =	twinPrimes.get(i);
			BigInteger y = x.a.add(BigInteger.valueOf(1));
			BigInteger z = y.multiply(BigInteger.valueOf(2));
			int j = i + 1;
			while (j < twinPrimes.size()) {
				Pair<BigInteger> k = new Pair<BigInteger>();
				k =	twinPrimes.get(j);
				BigInteger l = k.a;
				BigInteger m = z.subtract(BigInteger.valueOf(1));
				if (l.compareTo(m) == 0) {
					Pair<BigInteger> pair = new Pair<BigInteger>();
					pair.a = y;
					pair.b = z;
					hexCrosses.add(pair);
					//System.out.println(y);
				}
				j++;
			}
			i++;
		}
		
	}
}