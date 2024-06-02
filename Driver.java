import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.io.File;

public class Driver {
    public static void main(String [] args) throws IOException {
        File f0 = new File("test.txt");
        Polynomial test = new Polynomial();

        test.saveToFile("fun.txt");

        File f1 = new File("test2.txt");
        Polynomial test2 = new Polynomial(f1);

        Polynomial test3 = test.add(test2);

        System.out.println(Arrays.toString(test3.non_zero_coefficients));
        System.out.println(Arrays.toString(test3.exponents));




        double [] non_zero_1 = {3.0, -2.0, 1.0};
        int [] exponent1 = {2, 0, 1};
        Polynomial p1 = new Polynomial(non_zero_1, exponent1);

        double [] non_zero_2 = {-1, -1};
        int [] exponent2 = {1, 0};
        Polynomial p2 = new Polynomial(non_zero_2, exponent2);

        Polynomial final1 = p1.add(p2);
       //


        //p1.saveToFile("fun.txt");
        System.out.println("Non Zero Coefficients: "+Arrays.toString(p1.non_zero_coefficients));
        System.out.println("Exponents: "+Arrays.toString(p1.exponents));



        System.out.println("Non Zero Coefficients: "+Arrays.toString(p2.non_zero_coefficients));
        System.out.println("Exponents: "+Arrays.toString(p2.exponents));


       // System.out.println("Non Zero Coefficients: "+Arrays.toString(s1.non_zero_coefficients));
        //System.out.println("Exponents: "+Arrays.toString(s1.exponents));

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
