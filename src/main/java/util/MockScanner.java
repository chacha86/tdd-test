package util;

import java.util.Scanner;

public class MockScanner {
    private String[] inputs;
    private int index = 0;

    public void setInputs(String[] inputs) {
        this.inputs = inputs;
    }

    public String nextLine() {
        if(index >= inputs.length)
            return null;
        return inputs[index++];
    }


}
