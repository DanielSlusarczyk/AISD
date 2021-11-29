package pl.edu.pw.ee;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    public HashDoubleHashing(){
        super();
    }

    public HashDoubleHashing(int size){
        super(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();
        int hash = ((key % m) + i * getValueOfSecondHash(key, m)) % m;
        hash = hash < 0 ? -hash : hash;
        return hash;
    }

    private int getValueOfSecondHash(int key, int m){
        return (1 + (key % (m-3)));
    }
}
