import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class TestPB {
    @Test
    public void testChildGeneration() {
        Num parent = new Num(2);
        List<Ilayout> children = parent.children();
        assertEquals(3, children.size()); // Check if three children are generated
    }

    @Test
    public void testIsGoal() {
        Num goal = new Num(10);
        assertTrue(goal.isGoal(new Num(10))); // Check that the isGoal method works correctly.
    }

    @Test
    public void testSolve() {
        IDAStar idaStar = new IDAStar();
        Num initial = new Num(1);
        Num goal = new Num(3);
        Iterator<IDAStar.State> solution = idaStar.solve(initial, goal);
        assertTrue(solution.hasNext()); // Check that there is a solution.
    }
}