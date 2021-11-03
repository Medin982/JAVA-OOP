package WorkingwithAbstraction.Lab.Rhombus;

public class Rhombus {
    private final int n;

    public Rhombus(int n) {
        this.n = n;
    }

    public String getFigure() {
        return getTop() +
                getMiddle() +
                getBottom();
    }

    private String getBottom() {
        StringBuilder line = new StringBuilder();
        for (int r = 1; r < n; r++) {
            line.append(repeatString(r, " "))
                    .append(repeatString(n - r, "* "))
                    .append(System.lineSeparator());
        }
        return line.toString();
    }

    private String getMiddle() {
        return repeatString(n, "* ") + System.lineSeparator();
    }

    private String getTop() {
        StringBuilder line = new StringBuilder();
        for (int r = 1; r < n; r++) {
            line.append(repeatString(n - r, " "))
                    .append(repeatString(r, "* "))
                    .append(System.lineSeparator());
        }
        return line.toString();
    }

    private String repeatString(int count, String str) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < count; i++) {
            out.append(str);
        }
        return out.toString();
    }
}
