public interface List <T> {
    /**
     * Добавляет элемент в конец списка
     * @param object элемент для добавления
     */
    void add(T object);

    /**
     * Вставляет элемент в определенную позицию в списке
     * @param pos позиция, куда надо вставить элемент
     * @param object элемент для вставки
     * @throws IllegalArgumentException, если {@code pos} меньше 0 или больше длины списка
     */
    void insert(int pos, T object);

    /**
     * Получить длину списка
     * @return длина списка
     */
    int size();

    default void remove(T object){
        throw new UnsupportedOperationException("Remove not impemented");
    }


}
