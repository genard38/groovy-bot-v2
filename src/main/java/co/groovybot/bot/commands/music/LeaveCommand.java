package co.groovybot.bot.commands.music;

import co.groovybot.bot.core.audio.MusicPlayer;
import co.groovybot.bot.core.command.CommandCategory;
import co.groovybot.bot.core.command.CommandEvent;
import co.groovybot.bot.core.command.Result;
import co.groovybot.bot.core.command.permission.Permissions;
import co.groovybot.bot.core.command.voice.SameChannelCommand;

public class LeaveCommand extends SameChannelCommand {

    public LeaveCommand() {
        super(new String[]{"leave", "l"}, CommandCategory.MUSIC, Permissions.djMode(), "Lets Groovy disconnect from your channel", "");
    }

    @Override
    public Result runCommand(String[] args, CommandEvent event, MusicPlayer player) {
        if (player.getGuild().getId().equals("403882830225997825") && !Permissions.ownerOnly().isCovered(event.getPermissions(), event))
            return send(error("No Permission!", "You are not allowed to let Groovy disconnect from this channel!"));
        player.setPreviousTrack(player.getPlayer().getPlayingTrack());
        player.leave();
        return send(success(event.translate("command.leave.left.title"), event.translate("command.leave.left.description")));
    }
}