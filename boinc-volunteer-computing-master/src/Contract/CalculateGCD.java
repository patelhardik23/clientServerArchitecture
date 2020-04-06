package Contract;

import java.io.Serializable;

public class CalculateGCD implements Task, Serializable {

    long value1, value2, result;

    public CalculateGCD(long a, long b) {
        value1 = a;
        value2 = b;
    }

    private long calculate(long a, long b) {
        if (a == 0) {
            return b;
        } else {
            while (b != 0) {
                if (a > b) {
                    a = a - b;
                } else {
                    b = b - a;
                }
            }
            return a;
        }
    }

    @Override
    public void executeTask() {
        result = calculate(value1, value2);
    }

    @Override
    public Object getResult() {
        return "The Greatest Common Divisor of " + value1 + " and " + value2 + " is " + result;
    }
}
