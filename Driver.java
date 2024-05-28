import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.io.File;

public class Driver {
    public static void main(String [] args) throws IOException {

        File f0 = new File("test.txt");
        Polynomial test = new Polynomial(f0);
        System.out.println(Arrays.toString(test.non_zero_coefficients));
        System.out.println(Arrays.toString(test.exponents));

        test.saveToFile("fun.txt");

        double [] non_zero_1 = {1, 1};
        int [] exponent1 = {1, 0};
        Polynomial p1 = new Polynomial(non_zero_1, exponent1);

        System.out.println("Non Zero Coefficients: "+Arrays.toString(p1.non_zero_coefficients));
        System.out.println("Exponents: "+Arrays.toString(p1.exponents));


        double [] non_zero_2 = {1, 3};
        int [] exponent2 = {1, 0};
        Polynomial p2 = new Polynomial(non_zero_2, exponent2);

        System.out.println("Non Zero Coefficients: "+Arrays.toString(p2.non_zero_coefficients));
        System.out.println("Exponents: "+Arrays.toString(p2.exponents));

        Polynomial s1 = p1.multiply(p2);
        System.out.println("Non Zero Coefficients: "+Arrays.toString(s1.non_zero_coefficients));
        System.out.println("Exponents: "+Arrays.toString(s1.exponents));

        Polynomial s = p1.add(p2);
        System.out.println("Non Zero Coefficients: "+Arrays.toString(s.non_zero_coefficients));
        System.out.println("Exponents: "+Arrays.toString(s.exponents));


        System.out.println("s(2) = " + s.evaluate(0));
        if(s.hasRoot(0))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
    }
}
