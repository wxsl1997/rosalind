package com.wxsl.rosalind.designpattern.behavioral.interpreter;

public class InstructionHandler {

    private Interpreter interpreter;

    private static final String DEFAULT_DELIMITER = " ";

    public void resolve(String instruction) {
        String[] words = instruction.split(DEFAULT_DELIMITER);
        int sentenceNum = (words.length + 1) / 4;
        for (int i = 0; i < sentenceNum; i++) {
            Interpreter direction = new DirectionInterpret(words[i * 4]);
            Interpreter action = new ActionInterpret(words[i * 4 + 1]);
            Interpreter time = new TimeInterpret(words[i * 4 + 2]);
            Interpreter sentence = new SentenceInterpret(direction, action, time);
            if (i == 0) {
                interpreter = sentence;
            } else {
                interpreter = new ConnectInterpret(interpreter, sentence);
            }
        }
    }

    public void explain() {
        System.out.println(interpreter.interpret());
    }
}
