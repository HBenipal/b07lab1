

public class Polynomial {

    double [] coefficients;

    public Polynomial(){
        coefficients = new double [1];
        coefficients[0] = 0;
    }

    public Polynomial(double [] coefficients){
        int len = coefficients.length;
        this.coefficients = new double [len];
        for (int i = 0; i < len; i++){
            this.coefficients[i] = coefficients[i];
        }
    }

    public Polynomial add(Polynomial equation){
        int len1 = this.coefficients.length;
        int len2 = equation.coefficients.length;

        int max_len = Math.max(len1, len2);
        int min_len = Math.min(len1, len2);

        double [] result = new double [max_len];

        for (int i=0; i < min_len; i++){
            result[i] = this.coefficients[i] + equation.coefficients[i];
        }

        if (max_len == len1) {
            for (int j = min_len; j < max_len; j++) {
                result[j] = this.coefficients[j];
            }
        } else {
            for (int j = min_len; j < max_len; j++) {
                result[j] = equation.coefficients[j];
            }
        }

        Polynomial addition = new Polynomial(result);
        return addition;
    }

    public double evaluate(double x){
        double total = 0;

        for (int i = 0; i < this.coefficients.length; i++){
            total = total + (Math.pow(x, i) * this.coefficients[i]);
        }

        return total;
    }

    public boolean hasRoot(double x) {
        if (evaluate(x) == 0) {
            return true;
        } else {
            return false;
        }
    }
}
