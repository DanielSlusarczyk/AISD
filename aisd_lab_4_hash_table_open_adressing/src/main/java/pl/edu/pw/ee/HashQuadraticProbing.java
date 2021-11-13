package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {
    private double a = 1;
    private double b = 1;

    public HashQuadraticProbing() {
        super();
    }

    public HashQuadraticProbing(int size, double a, double b) {
        super(size);
        this.a = a;
        this.b = b;
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();
        return (int) ((key % m) + a * i + b * i * i) % m;
    }

    @Override
    public int getNumOfElems() {
        return getNElem();
    }
}
