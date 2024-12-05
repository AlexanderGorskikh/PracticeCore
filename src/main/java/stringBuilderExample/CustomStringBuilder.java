package stringBuilderExample;

import java.util.ArrayDeque;

public class CustomStringBuilder {
    private StringBuilder stringBuilder;
    private final ArrayDeque<String> history;

    public CustomStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.history = new ArrayDeque<>();
    }

    public CustomStringBuilder append(String str) {
        saveState();
        stringBuilder.append(str);
        return this;
    }

    public CustomStringBuilder append(char c) {
        saveState();
        stringBuilder.append(c);
        return this;
    }

    public CustomStringBuilder delete(int start, int end) {
        saveState();
        stringBuilder.delete(start, end);
        return this;
    }

    public CustomStringBuilder replace(int start, int end, String str) {
        saveState();
        stringBuilder.replace(start, end, str);
        return this;
    }

    public CustomStringBuilder insert(int offset, String str) {
        saveState();
        stringBuilder.insert(offset, str);
        return this;
    }

    public CustomStringBuilder clear() {
        saveState();
        stringBuilder.setLength(0);
        return this;
    }

    public int length() {
        return stringBuilder.length();
    }

    public char charAt(int index) {
        return stringBuilder.charAt(index);
    }

    public String substring(int start, int end) {
        return stringBuilder.substring(start, end);
    }

    public CustomStringBuilder undo() {
        if (!history.isEmpty()) {
            stringBuilder = new StringBuilder(history.pollLast());
        }
        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    private void saveState() {
        history.offerLast(stringBuilder.toString());
    }
}
