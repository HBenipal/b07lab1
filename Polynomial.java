

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
        int call_len = this.coefficients.length;
        int arg_len = equation.coefficients.length;

        int max_len = Math.max(call_len, arg_len);
        
        double result [] = new double [max_len];

        for (int i=0; i<max_len; i++){  
            
            if (i<call_len && i<arg_len){   
                result[i] = this.coefficients[i] + equation.coefficients[i];
            } else if (i<call_len){   
                result[i] = this.coefficients[i];
            } else if (i<arg_len){    
                result[i] = equation.coefficients[i];
            }
        }

        Polynomial ret = new Polynomial(result);
        return ret;
    }

    public double evaluate(double x){
        double total = 0.0;

        for (int i = 0; i < this.coefficients.length; i++){
            total += (Math.pow(x, i) * this.coefficients[i]);
        }

        return total;
    }

    public boolean hasRoot(double x) {
        return this.evaluate(x) == 0;
    }
}
