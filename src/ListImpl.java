import java.util.Arrays;
import java.util.NoSuchElementException;

public class ListImpl <T> implements List<T> {

    private Object[] array = new Object[1];

    private int size=0;

    @Override
    public String toString() {
        return "ListImpl{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    @Override
    public void add(Object object) {
        size++;
        addSizeIfRequired();
        array[size-1]=object;

    }

    @Override
    public void insert(int pos, Object object) {
        if(pos<0 || pos>size-1){
            throw  new IllegalArgumentException("Invalid index for insert. <0 or >size-1");
        }
        size++;
        addSizeIfRequired();
        for(int i=size-1;i>=pos;i--){
            if(i==pos){
                array[i]=object;
                return;
            }
            array[i]=array[i-1];
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(Object object) {
        size--;
        int deleteIndex=-1;
        for(int i=0;i<=size;i++){
            assert array[i] != null;
            if(array[i].equals(object)){
                deleteIndex=i;
            }
            if(deleteIndex!=-1 && i<size){
                array[i]=array[i+1];
            }
            else if (deleteIndex != -1){
                    array[i]=null;
            }
        }


    }

    public void addSizeIfRequired(){
        if(array.length<=size-1){
            this.array= Arrays.copyOf(array,array.length*2);
        }
    }

    public Object get(int index){
        return array[index];
    }

    //Вторая реализация итератора - внутренний класс, связанный с массивом (не static),
    // который не копирует исходный массив, а только итерируется по нему.
    public class ListIteratorInnerClassImpl implements ListIterator<Object> {

        int index;

        public ListIteratorInnerClassImpl(){
            index =0;
        }
        @Override
        public boolean hasNext() {
            return index < array.length;
        }

        @Override
        public Object next() {
            if(hasNext()){
                return array[index++];}
            else throw new NoSuchElementException("Array ended, no more elements");
        }
    }


}
