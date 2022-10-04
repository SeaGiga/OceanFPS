package com.github.seagiga.oceanfps.manager;

import com.github.seagiga.oceanfps.command.FPSCommand;
import com.github.seagiga.oceanfps.util.CommandRegistry;
import org.bukkit.command.Command;

public class CommandManager {
    public CommandManager(){
        CommandRegistry.registerCommand((Command) new FPSCommand());
    }
}
