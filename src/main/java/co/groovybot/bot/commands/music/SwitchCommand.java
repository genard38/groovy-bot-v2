package co.groovybot.bot.commands.music;

import co.groovybot.bot.core.audio.MusicPlayer;
import co.groovybot.bot.core.command.CommandCategory;
import co.groovybot.bot.core.command.CommandEvent;
import co.groovybot.bot.core.command.Result;
import co.groovybot.bot.core.command.permission.Permissions;
import co.groovybot.bot.core.command.voice.InChannelCommand;

public class SwitchCommand extends InChannelCommand {
    public SwitchCommand() {
        super(new String[]{"switch"}, CommandCategory.MUSIC, Permissions.djMode(), "Lets you to switch the text-channel or/and the voicechannel of Groovy", "");
    }

    @Override
    public Result execute(String[] args, CommandEvent event, MusicPlayer player) {
        if (player.getChannel() == event.getChannel())
            return send(error(event.translate("command.switch.already.title"), event.translate("command.switch.already.description")));

        player.setChannel(event.getChannel());

        if (event.getMember().getVoiceState().getChannel() != event.getGuild().getSelfMember().getVoiceState().getChannel()) {
            player.connect(event.getMember().getVoiceState().getChannel());
            return send(success(event.translate("command.switch.voice.title"), event.translate("command.switch.voice.description")));
        }

        return send(success(event.translate("command.switch.title"), event.translate("command.switch.description")));
    }
}