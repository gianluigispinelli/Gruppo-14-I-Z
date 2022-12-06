package drawingSoftware;

import java.util.Stack;

import drawingSoftware.Editor.EditorAbstractCommand;

public class CommandHistory {
    Stack<EditorAbstractCommand> history;

    public CommandHistory(){
        history = new Stack<>();
    }

    public void push(EditorAbstractCommand c){
        history.push(c);
    }

    public EditorAbstractCommand pop(){
        return history.pop();
    }
}
