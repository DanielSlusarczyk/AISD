package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {
    private double a = 3;
    private double b = 2;

    public HashQuadraticProbing() {
        super();
    }

    public HashQuadraticProbing(int size, double a, double b) {
        super(size);
        validateInput(a, b);
        this.a = a;
        this.b = b;
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();
        int hash = (int) ((key % m) + a * i + b * i * i) % m;
        hash = hash < 0 ? -hash : hash;
        return hash;
    }

    private void validateInput(double a, double b) {
        if (a == 0 || b == 0) {
            throw new IllegalArgumentException();
        }
    }
}
