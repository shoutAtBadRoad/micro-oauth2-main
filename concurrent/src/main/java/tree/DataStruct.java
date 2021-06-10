package tree;

public interface DataStruct<T> {

    /**
     * 插入t
     * 元素唯一
     * @param t
     */
    void insert(T t);


    boolean contain(T t);

    /**
     * >t -> 1
     * =t -> 0
     * <t -> -1
     * @param t
     * @return
     */
    int compare(T t);



}
