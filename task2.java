import java.lang.Math;

public class task2 {
	public static void main(String args[]) {
		double x1, x2, a, b, c;
		for(int i = 0; i < 10; i++) {
			a = Math.random() * 20 - 10;
			b = Math.random() * 20 - 10;
			c = Math.random() * 20 - 17;
			if(a != 0) {
				x1 = (-b+Math.sqrt(b*b - 4*a*c))/(2*a);
				x2 = (-b-Math.sqrt(b*b - 4*a*c))/(2*a);
				if(!(Double.isNaN(x1)) && !(Double.isNaN(x2))){
					System.out.printf("A: %f, B: %f, C: %f\n", a, b, c);
					System.out.printf("X1: %f\n", x1);
					System.out.printf("X2: %f\n", x2);
				} else
					continue;
			} else {
				System.out.printf("A: %f, B: %f, C: %f\n", a, b, c);
				x1 = -c - b;
				System.out.printf("X1: %f\n", x1);
			}
		}
	}
}
