package dataStructures;

import com.dataStructures.ResizableArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class ResizableArrayTests {

    private static ResizableArray<Object> list;

    @BeforeAll
    static void initAll(){
        list = new ResizableArray<>();
    }

    @AfterEach
    void resetState(){
        list = new ResizableArray<>();
    }

}
