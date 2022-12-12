package drawingSoftware.Managers;

import drawingSoftware.Controller;
import drawingSoftware.Model;
import drawingSoftware.Command.BackupCommand.EditorCommand.CopyCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.CutCommand;
import drawingSoftware.Command.BackupCommand.EditorCommand.PasteCommand;
import drawingSoftware.Editor.Editor;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.Pane;

public class ContextMenuManager {
    private ContextMenu contextMenu; 
    private MenuItem copyItem ;
    private MenuItem pasteItem ;
    private MenuItem cutItem ;
    private Editor editor;
    private Controller controller;
    private Pane drawingWindow;
    private Model model;

    //Constructor
    public ContextMenuManager(Controller controller) {
        //Instantiate context menu
        this.contextMenu = new ContextMenu();
        //Set menu item
        this.copyItem = new MenuItem("Copy");
        this.pasteItem = new MenuItem("Paste");
        this.cutItem = new MenuItem("Cut");
        // Set controller, editor, drawingWindow and model
        this.controller =  controller;
        this.editor=controller.getEditor();
        this.drawingWindow = controller.getDrawingWindow();
        this.model = controller.getModel();
       

    }

    public void setContextMenu(){
        //Add items to the context Menu
        this.contextMenu.getItems().addAll(copyItem,pasteItem,cutItem);

        drawingWindow.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>(){

            @Override
            public void handle(ContextMenuEvent event) {
                // Show context menu in the point where the user has done right click with the mouse
                contextMenu.show(drawingWindow, event.getScreenX(), event.getScreenY());
                if(model.getSelectedShape() == null){
                    //Disable the copy and cut items if no shape is selected
                    copyItem.setDisable(true);
                    cutItem.setDisable(true);
                }
                else{
                    //Enable the copy and cut items if a shape has been selected
                    copyItem.setDisable(false);
                    cutItem.setDisable(false);
                }
                if(editor.getClipboard() == null){
                    // if no shape has been selected disable paste item otherwise enable it
                    pasteItem.setDisable(true);
                }else{
                    
                    pasteItem.setDisable(false);
                }
                
            }

        });

        // Copy,paste and cut command are delegated to the Editor because it does also the backup to save the previous state for supporting the undo.
        copyItem.setOnAction(e ->{
            //Delegate the copy command to the Editor so 
            CopyCommand copyCommand = new CopyCommand(editor);
            controller.executeCommand(copyCommand);
        });

        pasteItem.setOnAction(e ->{
            //Delegate the paste command to the Editor
            PasteCommand pasteCommand = new PasteCommand(editor);
            controller.executeCommand(pasteCommand);
            
        });        
        
        cutItem.setOnAction(e ->{
            //Delegate the paste command to the Editor
            CutCommand cutCommand = new CutCommand(editor);
            controller.executeCommand(cutCommand);
            
        });
    }

    
    
}
