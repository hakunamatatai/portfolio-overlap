import com.example.portfolio.Enum.ErrorCode;
import com.example.portfolio.PortfolioApplication;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;


public class PortfolioApplicationTest {

    @Test
    void runPortfolio_noArgs_returnsUsage() {
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        System.setErr(new PrintStream(err));

        int code = PortfolioApplication.runPortfolio(new String[]{});
        assertEquals(ErrorCode.USAGE.getCode(), code);
        assertTrue(err.toString().contains("Usage"));
    }

    @Test
    void runPortfolio_badJsonPath_returnsResourceNotFound() {
        int code = PortfolioApplication.runPortfolio(new String[]{"nonexistent.txt"});
        assertEquals(ErrorCode.ERROR.getCode(), code);
    }

    @Test
    void runPortfolio_validInput_returnsZero() {
        String input = "sample_input/input1.txt";
        int code = PortfolioApplication.runPortfolio(new String[]{input});
        assertEquals(0, code);
    }
}
