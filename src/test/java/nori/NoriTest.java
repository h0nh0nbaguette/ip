package nori;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class NoriTest {
    @TempDir
    private Path temporaryDirectory;

    @Test
    void getResponse_addAndListTask_returnsExpectedResponses() {
        Nori nori = new Nori(temporaryDirectory.resolve("nori.txt"));

        assertEquals("Got it. I've added this task:\n"
                + "  [T][ ] read book\n"
                + "Now you have 1 task in the list.", nori.getResponse("todo read book"));
        assertEquals("Here are the tasks in your list:\n"
                + "1.[T][ ] read book", nori.getResponse("list"));
    }

    @Test
    void getResponse_unknownCommand_returnsErrorResponse() {
        Nori nori = new Nori(temporaryDirectory.resolve("nori.txt"));

        assertEquals("OOPS!!! I don't know that command.", nori.getResponse("hello"));
    }
}
