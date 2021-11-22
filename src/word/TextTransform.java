package word;

import java.util.Stack;

public interface TextTransform {
    void invokeOn(StringBuilder text, int startIndex, int endIndex, Stack<String> memory);
}
