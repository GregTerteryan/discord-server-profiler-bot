package me.gregterteryan;


import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.PermissionException;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main extends ListenerAdapter {
    public static JDA bot;
    public static String prefix = "^";
    public static void main(String[] args) throws LoginException {
        try {
            bot = JDABuilder.createDefault("OTYxMTA2MDI4NjY0MDg2NTI4.Yk0JvQ.XavYMz_1mgkPhUbiLwGzNqplM5Q")
                    .setActivity(Activity.playing("with the creator's sanity"))
                    .addEventListeners(new Commands())
                    .build();
            bot.awaitReady();
        }
        catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
