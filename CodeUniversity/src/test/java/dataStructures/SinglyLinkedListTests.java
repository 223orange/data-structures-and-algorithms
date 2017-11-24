package dataStructures;

import com.dataStructures.SinglyLinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SinglyLinkedListTests {

    private static SinglyLinkedList<Object> list;

    @BeforeAll
    static void initAll(){
        list = new SinglyLinkedList<>();
    }

    @AfterEach
    void resetState(){
        list = new SinglyLinkedList<>();
    }

    @Test
    void returnToStringMethod(){
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.pushFront(4);
        assertEquals("[4, 3, 2, 1]", list.toString());
    }


    @Test
    void pushBackElement(){
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        assertEquals("[1, 2, 3]", list.toString());
    }


    @Test
    void increaseSizeWhenAddElementsInFront(){
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.pushFront(4);
        assertEquals(4, list.size());
    }

    @Test
    void increaseSizeWhenPushBack(){
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(4);
        assertEquals(4, list.size());
    }

    @Test
    void decreaseSizeWhenPopFront(){
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(4);
        list.popFront();
        assertEquals(3, list.size());
    }

    @Test
    void decreaseSizeWhenPopBack(){
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(4);
        list.popBack();
        assertEquals(3, list.size());
    }

    @Test
    void isEmpty(){
        assertTrue(list.empty());
    }

    @Test
    void returnElementFromTheFront(){
        list.pushFront(1);
        assertEquals(1, list.popFront());
        assertTrue(list.empty());

    }

    @Test
    void returnElementFromTheBack(){
        list.pushFront(1);
        assertEquals(1, list.popBack());
        assertTrue(list.empty());
    }


    @Test
    void returnFirstElementFromFront(){
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.pushFront(4);
        assertEquals(4, (int)list.front());
    }

    @Test
    void throwsExceptionWhenFrontOnEmptyList(){
        assertThrows(IndexOutOfBoundsException.class, () -> list.front());
    }

    @Test
    void backShouldReturnLastElement(){
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.pushFront(4);
        assertEquals(1, (int)list.back());
    }

    @Test
    void throwsExceptionWhenBackOnEmptyList(){
        assertThrows(IndexOutOfBoundsException.class, () -> list.back());
    }

    @Test
    void insertElementByIndex(){
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.insert(2, 4);
        assertEquals("[3, 2, 4, 1]", list.toString());
    }

    @Test
    void throwsExceptionWhenInsertElementWithIncorrectIndex(){
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert(3, 4));
    }

    @Test
    void throwsExceptionWhenEraseElementWithIncorrectIndex(){
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert(3, 4));
    }

    @Test
    void eraseElementByIndex(){
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.erase(1);
        assertEquals("[3, 1]", list.toString());
    }

    @Test
    void throwsExceptionWhenReverseEmptyList(){
        assertThrows(IndexOutOfBoundsException.class, () -> list.reverse());
    }

    @Test
    void reverseList(){
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.pushFront(4);
        list.reverse();
        assertEquals("[1, 2, 3, 4]", list.toString());
    }

}
