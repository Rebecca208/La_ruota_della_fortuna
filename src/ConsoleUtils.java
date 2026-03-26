// java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUtils {
    private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    // Legge la prima lettera di una riga non vuota (richiede ENTER)
    public static char readCharFromConsole() throws IOException {
        java.io.Console console = System.console();
        if (console != null) {
            String line = console.readLine();
            if (line == null || line.isEmpty()) return '\n';
            return line.charAt(0);
        } else {
            String line;
            do {
                line = BR.readLine();
                if (line == null) return '\n';
            } while (line.isEmpty());
            return line.charAt(0);
        }
    }
}

