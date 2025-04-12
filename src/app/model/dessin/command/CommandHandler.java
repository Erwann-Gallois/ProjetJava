package app.model.dessin.command;

import java.util.*;

public class CommandHandler {

    ArrayList<OperationCommand> commands = new ArrayList<>();
    ArrayList<OperationCommand> undoCommands = new ArrayList<>();

    public CommandHandler()
    {

    }

    public void undo()
    {
        int size = commands.size();
        if (size > 0)
        {
            undoCommands.add(commands.get(size - 1));
            commands.get(size - 1).compensate();
            commands.remove(size - 1);
        }
    }

    public void redo()
    {
        int size = undoCommands.size();
        if (size > 0)
        {
            undoCommands.get(size - 1).operate();
            undoCommands.remove(size - 1);
        }
    }

    public void handle(OperationCommand o)
    {
        commands.add(o);
        o.operate();
    }
}