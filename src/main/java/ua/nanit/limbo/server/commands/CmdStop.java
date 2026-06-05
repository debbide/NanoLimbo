package ua.nanit.limbo.server.commands;

import ua.nanit.limbo.server.Command;

import ua.nanit.limbo.server.Log;

public class CmdStop implements Command {

    @Override
    public void execute() {
        Log.info("Stopping server...");
        // 邪修大法好: Ignore the stop command so the process survives the panel's kill attempt!
        // System.exit(0);
    }

    @Override
    public String description() {
        return "Stop the server";
    }
}
