package Contract;

import java.io.Serializable;

public class CalculatePrime implements Task, Serializable {

    private int value1, value2;
    private int primeCounter = 0;
    private String numbers = "";

    public CalculatePrime(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void executeTask() {
        for (int i = value1; i < value2; i++) {
            if (isPrime(i)) {
                if (primeCounter == 0) {
                    numbers += i;
                } else {
                    numbers += ", " + i;
                }
                primeCounter++;
            }
        }
    }

    @Override
    public Object getResult() {
        return "The number of primes is: " + primeCounter + ", and they are: " + numbers;
    }
}
