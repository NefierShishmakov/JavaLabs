package ru.nsu.ccfit.shishmakov.minesweeper.command;

public class AboutCommand implements Command {
    @Override
    public void execute() {
        System.out.println("""
                This game was developed by Ivan Shishmakov.
                If you want to support me, you can find me by the link in the description below.
                You can write me and I'll tell you how to send me money :)
                Enjoy the game.
                
                #############################
                https://vk.com/shishmakovivan
                #############################
                """);
    }
}
