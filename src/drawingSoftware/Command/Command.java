package drawingSoftware.Command;

import java.io.IOException;

/*

 * For the sake of avoiding that the GUI calls directly the methods of the business logic, the implementation of the following operation: 
 * [copy,paste,cut,delete,move,draw,edit,undo,saving, loading] 
 * are in specific class that, according to the Command design pattern, play the role of receivers.
 * For example, copy,paste,cut,delete and edit operation are methods of the class Editor, which is the receiver of the CopyCommand,
 * PasteCommmand, CutCommand, DeleteCommand and EditCommand classes. 
 * For example, when the method cutAShape() of the Controller is called, a new istance of the CutCommand is created and via the 
 * execute method is called, the cut() method of the Editor is executed. 
 * 
 */

public interface Command {    
    public boolean execute() throws IOException;
}
