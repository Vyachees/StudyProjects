public interface ListIterator<T> {

    /**
     * Есть ли следующий элемент?
     * @return true, если через {@code next} можно получить следующий элемент
     */
    boolean hasNext();

    /**
     * Получить следующий элемент
     * @return следующий элемент из итерируемого списка
     * @throws java.util.NoSuchElementException, если следующего элемента нет
     */
    T next();
}

