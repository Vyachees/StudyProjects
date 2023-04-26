import java.util.NoSuchElementException;

public class ListIteratorCopyArrImpl<T> implements ListIterator<Object> {
    //Первая реализация итератора - обычный класс,
    //который копирует исходный массив и итерируется по копии из списка.
    private int index;
    private final ListImpl<T> listNew = new ListImpl<>();

    public ListIteratorCopyArrImpl(ListImpl<T> list) {
        index = 0;
        for (int i = 0; i < list.size(); i++) {
            listNew.add(list.get(i));
        }
    }


    @Override
    public boolean hasNext() {
        return index < listNew.size();
    }

    @Override
    public Object next() {
        if (hasNext()) {
            return listNew.get(index++);
        } else throw new NoSuchElementException("Array ended, no more elements");
    }
}
