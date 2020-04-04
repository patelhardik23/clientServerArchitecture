package Contract;

import java.io.Serializable;

public class CalculatePrime implements Task, Serializable {

    int value;
    boolean result;

    public CalculatePrime(int value) {
        this.value = value;
    }

    private boolean isPrime() {
        for (int i = 2; i < value; i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void executeTask() {
        result = isPrime();
    }

    @Override
    public Object getResult() {
        return result;
    }
}
