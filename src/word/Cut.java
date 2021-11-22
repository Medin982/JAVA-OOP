package word;

import java.util.Stack;

public class Cut implements TextTransform {

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex, Stack<String> memory) {
        memory.push(text.substring(startIndex, endIndex));
        text.replace(startIndex, endIndex, "");
    }

}
