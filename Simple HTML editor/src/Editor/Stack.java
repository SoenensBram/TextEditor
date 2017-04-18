package Editor;

/**
 * Satck interface
 *
 * @param <T> tpye of elements of the stack
 */
public interface Stack<T> {

    /**
     * @return treu if empty, otherwise false
     */
    boolean isEmpty();

    /**
     * @return number of elements on the stack
     */
    int size();

    /**
     * Pushes an element on the stack
     *
     * @param element
     */
    void push(T element);

    /**
     * @return the top element of the stack and removing it
     */
    T pop();

    /**
     * @return the top element of the stack without removing it
     */
    T top();
}
