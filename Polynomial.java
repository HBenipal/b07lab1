import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Polynomial {

    double [] non_zero_coefficients;
    int [] exponents;

    public Polynomial(){
        this.non_zero_coefficients = new double [0];
        this.exponents = new int [0];
    }

    public Polynomial(double [] non_zero_coefficients, int [] exponents){

        //Have to check if the lengths are the same

        int len = non_zero_coefficients.length;
        this.non_zero_coefficients = new double [len];
        this.exponents = new int [len];
        for (int i = 0; i < len; i++){
            this.non_zero_coefficients[i] = non_zero_coefficients[i];
            this.exponents[i] = exponents[i];
        }
    }

    public Polynomial(File myfile)  throws FileNotFoundException {
        Scanner input = new Scanner(myfile);
        String [] arr = (input.nextLine()).split("[-+]+");
        int len = arr.length;
        this.non_zero_coefficients = new double [len];
        this.exponents = new int [len];

        for (int i = 0; i < len; i++){

            String [] arr1 = arr[i].split("x");
            this.non_zero_coefficients[i] = Double.parseDouble(arr1[0]);

            if (arr1.length == 1){
                this.exponents[i] = 0;
            } else {
                this.exponents[i] = Integer.parseInt(arr1[1]);
            }
        }
    }

    public void saveToFile(String name) throws IOException {
        FileWriter output = new FileWriter(new File(name));


        for (int i = 0; i < this.exponents.length; i++) {
            if (this.exponents[i] == 0){
                if (this.non_zero_coefficients[i] >= 0) {
                    if (i == 0){
                        output.write(Double.toString(this.non_zero_coefficients[i]));
                    } else {
                        output.write("+" + Double.toString(this.non_zero_coefficients[i]));
                    }
                } else {
                    output.write("-"+Double.toString(this.non_zero_coefficients[i]));
                }
            } else{
                if (this.non_zero_coefficients[i] >= 0) {
                    if (i == 0) {
                        output.write(Double.toString(this.non_zero_coefficients[i]) + "x" + Integer.toString(this.exponents[i]));
                    } else {
                        output.write("+" + Double.toString(this.non_zero_coefficients[i]) + "x" + Integer.toString(this.exponents[i]));
                    }
                } else {
                    output.write("-"+Double.toString(this.non_zero_coefficients[i])+"x"+Integer.toString(this.exponents[i]));
                }

            }
        }
        output.close();
    }

    public int array_contains(int l, int call_len, int arg_len, Polynomial equation){

        int a = 0;
        int b = 0;
        for (int i = 0; i < call_len; i++){
            if (this.exponents[i] == l) {
                a = 1;
                break;
            }
        }

        for (int i = 0; i < arg_len; i++){
            if (equation.exponents[i] == l) {
                b = 1;
                break;
            }
        }

        if (a == 1 && b == 1){
            return 1;
        } else if (a == 1) {
            return 2;
        } else if (b == 1){
            return 3;
        }

        return 0;

    }

    public int getIndex(int l, int length, int [] arr){
        for (int i = 0; i < length; i++){
            if (arr[i] == l){
                return i;
            }
        }
        return -1;
    }

    public int getMax(Polynomial equation){
        int max = 0;
        for (int i = 0; i < equation.exponents.length; i ++){
            if (equation.exponents[i] > max){
                max = equation.exponents[i];
            }
        }

        for (int i = 0; i < this.exponents.length; i++){
            if (this.exponents[i] > max){
                max = this .exponents[i];
            }
        }

        return max + 1;
    }
    public Polynomial add(Polynomial equation){
        int call_len = this.non_zero_coefficients.length;
        int arg_len = equation.non_zero_coefficients.length;

        int j = 0;
        int k = 0;
        int l = 0;


        double [] temp = new double [getMax(equation)];
        int num_occur = 0;
        while ((j < call_len) || (k < arg_len)) {
           // System.out.println(l);
            int val = array_contains(l, call_len, arg_len, equation);
            if (val == 1) {
                double q = this.non_zero_coefficients[getIndex(l, call_len, this.exponents)];
                double w = equation.non_zero_coefficients[getIndex(l, arg_len, equation.exponents)];
                if (q + w != 0){
                    num_occur += 1;
                }
                temp[l] = q + w;
                j += 1;
                k += 1;

            } else if (val == 2) {
                temp[l] = this.non_zero_coefficients[getIndex(l, call_len, this.exponents)];
                num_occur += 1;
                j += 1;
            } else if (val == 3){
                //System.out.println(l);
                temp[l] = equation.non_zero_coefficients[getIndex(l, arg_len, equation.exponents)];
                //System.out.println(temp[l]);
                num_occur += 1;
                k += 1;
            }
            l += 1;

        }


        double [] result_coefficients = new double [num_occur];
        int [] result_exponents = new int [num_occur];

        int z = 0;
        for (int i = 0; i < l; i++){
            //System.out.println(temp[i]);
            if (temp[i] != 0){
                //System.out.println(temp[i]);
                result_coefficients[z] = temp[i];
                result_exponents[z] = i;
                z+= 1;
            }
        }

        Polynomial ret = new Polynomial(result_coefficients, result_exponents);
        return ret;
    }

    public double evaluate(double x){
        double total = 0.0;

        for (int i = 0; i < this.non_zero_coefficients.length; i++){
            total += (Math.pow(x, this.exponents[i]) * this.non_zero_coefficients[i]);
        }

        return total;
    }

    public boolean hasRoot(double x) {
        return this.evaluate(x) == 0;
    }

    public boolean check_single(int a, int [] arr, int j){
        for (int i = j + 1; i < arr.length; i++){
            if (a == arr[i]){
                return true;
            }
        }
        return false;
    }
    public Polynomial multiply(Polynomial equation){

        int call_len = this.exponents.length;
        int arg_len = equation.exponents.length;

        double [] result_coefficients = new double[call_len*arg_len];
        int [] result_exponents = new int[call_len*arg_len];

        int k = 0;
        for (int i = 0; i < call_len; i++){
            for (int j = 0; j < arg_len; j++){
                result_coefficients[k] = this.non_zero_coefficients[i] * equation.non_zero_coefficients[j];
                result_exponents[k] = this.exponents[i] + equation.exponents[j];
                k+=1;
            }
        }

        //combine same coefficient ones

        int count = 0;
        for (int i = 0; i < k; i++){
            for (int j = i + 1; j < k; j++) {
                if (result_exponents[i] == result_exponents[j] && result_coefficients[i] != 0) {
                    result_coefficients[i] += result_coefficients[j];
                    result_coefficients[j] = 0;
                    count += 1;
                }
            }
        }

        double [] final_coefficients = new double[k - count];
        int [] final_exponents = new int[k - count];

        int m = 0;
        for (int i = 0; i < k; i++) {
            if (result_coefficients[i] != 0) {
                final_coefficients[m] = result_coefficients[i];
                final_exponents[m] = result_exponents[i];
                m+=1;
            }
        }
        Polynomial n = new Polynomial(final_coefficients, final_exponents);
        return n;
    }
}
