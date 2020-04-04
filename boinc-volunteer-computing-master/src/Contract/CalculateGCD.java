package Contract;

import java.io.Serializable;

public class CalculateGCD implements Task, Serializable {

    private long CalculateGCD(long a, long b) {
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

    }

    @Override
    public Object getResult() {
        return null;
    }
}
