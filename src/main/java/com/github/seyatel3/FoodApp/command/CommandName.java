package com.github.seyatel3.FoodApp.command;

/**
 * Enumeration for {@link Command}'s.
 */
public enum CommandName {

    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    STAT("/stat"),
    MENU("/menu"),
    MENU_ADD_ITEM("/menu_add_item"),
    ORDER("/order"),
    CANCEL_ORDER("/cancel_order"),
    LETS_GO("/lets_go"),
    MENU_UPDATE("/menu_update"),
    NO("nocommand");
    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

}

