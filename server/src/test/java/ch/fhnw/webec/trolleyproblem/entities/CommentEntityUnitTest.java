package ch.fhnw.webec.trolleyproblem.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommentEntityUnitTest {
    private CommentEntity commentEntity;

    @BeforeAll
    public void setUp() {
        commentEntity = new CommentEntity();
    }

    // getInitials

    @Test
    public void testGetInitials() {
        commentEntity.setAuthor("John Doe");
        assertEquals("JD", commentEntity.getInitials());
    }

    @Test
    public void testGetInitialsEmpty() {
        commentEntity.setAuthor("");
        assertEquals("", commentEntity.getInitials());
    }

    @Test
    public void testGetInitialsSingleCharacter() {
        commentEntity.setAuthor("A");
        assertEquals("A", commentEntity.getInitials());
    }

    @Test
    public void testGetInitialsThreeWords() {
        commentEntity.setAuthor("John Doe A");
        assertEquals("JA", commentEntity.getInitials());
    }

    @Test
    public void testGetInitialsSurrogatePair() {
        commentEntity.setAuthor("👨‍👩‍👧‍👦");
        assertEquals("👨‍👩‍👧‍👦", commentEntity.getInitials());
    }

    @Test
    public void testGetInitialsSurrogatePairWithSpace() {
        commentEntity.setAuthor("👨‍👩‍👧‍👦 ");
        assertEquals("👨‍👩‍👧‍👦", commentEntity.getInitials());
    }

    @Test
    public void testGetInitialsSurrogatePairWithSpaceAndText() {
        commentEntity.setAuthor("👨‍👩‍👧‍👦 John Doe");
        assertEquals("👨‍👩‍👧‍👦D", commentEntity.getInitials());
    }

    @Test
    public void testGetInitialsSurrogatePairWithSpaceAndTextAndSpaceAndText() {
        commentEntity.setAuthor("👨‍👩‍👧‍👦 John Doe A");
        assertEquals("👨‍👩‍👧‍👦A", commentEntity.getInitials());
    }

    // getAuthorColor

    @Test
    public void testGetAuthorColor() {
        commentEntity.setAuthor("John Doe");
        assertEquals("hsl(-147, 70%, 40%)", commentEntity.getAuthorColor());
    }

    @Test
    public void testGetAuthorColorEmpty() {
        commentEntity.setAuthor("");
        assertEquals("hsl(0, 70%, 40%)", commentEntity.getAuthorColor());
    }

    @Test
    public void testGetAuthorColorSingleCharacter() {
        commentEntity.setAuthor("A");
        assertEquals("hsl(65, 70%, 40%)", commentEntity.getAuthorColor());
    }

    @Test
    public void testSameAuthorSameColor() {
        commentEntity.setAuthor("John Doe");
        String color1 = commentEntity.getAuthorColor();
        commentEntity.setAuthor("John Doe");
        String color2 = commentEntity.getAuthorColor();
        assertEquals(color1, color2);
    }
}
