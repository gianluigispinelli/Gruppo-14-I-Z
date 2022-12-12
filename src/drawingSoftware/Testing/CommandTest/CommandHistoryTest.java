package drawingSoftware.Testing.CommandTest;

import org.junit.*;

import drawingSoftware.Model;
import drawingSoftware.Command.CommandHistory;
import drawingSoftware.Command.BackupCommand.BackupCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.CutCommand;
import drawingSoftware.Editor.Editor;

import static org.junit.Assert.*;

public class CommandHistoryTest {
    /*

     * Here we're assuring that the push and pop operations on the
     * stack of CommandHistoryTest works for saving backup of the 
     * different commands of the application.  
     */

    private CommandHistory commandHistory; 
    private BackupCommand backupCommand; 

    @Before
    public void setUp(){
        commandHistory = new CommandHistory(); 
        backupCommand = new CutCommand(new Editor(Model.getInstance()));
    }

    @Test
    public void testPush(){
        commandHistory.push(backupCommand);
        assertEquals(backupCommand, commandHistory.pop());        
    }

    @Test
    public void testPop(){
        commandHistory.push(backupCommand);
        assertEquals(backupCommand, commandHistory.pop());
    }

    @Test
    public void testIsEmpty(){
        assertTrue(commandHistory.isEmpty());   /* i don't have any commands in the stack, the value return by isEmpty() should return true */
        commandHistory.push(backupCommand);
        assertFalse(commandHistory.isEmpty());
    }
    
}
