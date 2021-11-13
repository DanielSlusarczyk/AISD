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
        return ((key % m) + i * (1 + (key % (m-3)) % m));
    }

    @Override
    public int getNumOfElems() {
        return getNElem();
    }
}
