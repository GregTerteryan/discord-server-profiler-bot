package me.gregterteryan;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.*;
import java.util.Scanner;

public class Commands extends ListenerAdapter{
    public Server server = new Server();
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String author = event.getAuthor().toString().substring(event.getAuthor().toString().indexOf(":") + 1, event.getAuthor().toString().indexOf("("));
        String file = " :(";
        try {
            file = event.getGuild().toString().substring(event.getGuild().toString().indexOf(":") + 1);
        }
        catch (IllegalStateException e) {

        }
        String message = event.getMessage().getContentRaw();
        String serverName = event.getAuthor().getAsMention();
        server.setServerName(file.substring(0, file.indexOf("(")));
        load(file);
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase("https://tenor.com/view/metal-gear-raiden-senator-armstrong-moneyintr-gif-15988527")) {
            event.getChannel().sendMessage("Standing here,").queue();
            event.getChannel().sendMessage("I realise").queue();
            event.getChannel().sendMessage("you were just like me").queue();
            event.getChannel().sendMessage("trying to make history.").queue();
            event.getChannel().sendMessage("But who's to judge").queue();
            event.getChannel().sendMessage("the right from wrong?").queue();
            event.getChannel().sendMessage("When our guard is down,").queue();
            event.getChannel().sendMessage("I think we'll both agree").queue();
            event.getChannel().sendMessage("that violence breeds violence").queue();
            event.getChannel().sendMessage("but in the end, it has to be this way.").queue();
        }
        else if (args[0].equalsIgnoreCase(Main.prefix + "help")) {
            event.getAuthor().openPrivateChannel().complete()
                    .sendMessage("Here are the commands: \n" +
                    Main.prefix + "profile: \n\t by itself: shows your profile\n\tnew: makes a new profile for you\n\tsetName: sets your name"
                    + "\n\tsetAge: sets your age\n\tsetStatus: sets your status\n\tsetAboutMe: sets your about me\n\tsetPFP: sets your profile picture (url)"
                    + "\n" + Main.prefix + "seeProfile (user's @ mention): shows the user's profile"
                    + "\nThis gif: https://tenor.com/view/metal-gear-raiden-senator-armstrong-moneyintr-gif-15988527").queue();
        }
        else if (args[0].equalsIgnoreCase(Main .prefix + "profile")) {
            if (args.length > 1) {
                if (args[1].equalsIgnoreCase("new")) {
                    if (findUserIndex(serverName) > -1) {
                        server.users.set(findUserIndex(serverName), new Profile(serverName));
                    } else {
                        server.users.add(new Profile(serverName));
                    }
                    save(file);
                }
            }
            if (args.length > 2) {
                if (args[1].equalsIgnoreCase("setName")) {
                    if (findUserIndex(serverName) > -1) {
                        String input = "";
                        for (int n = 2; n < args.length; n++) {
                            if (n == args.length - 1)
                                input += args[n];
                            else
                                input += args[n] + " ";
                        }
                        server.users.get(findUserIndex(serverName)).setName(input);
                        save(file);
                    } else {
                        event.getChannel().sendMessage("Please make a profile first.").queue();
                    }
                } else if (args[1].equalsIgnoreCase("setStatus")) {
                    if (findUserIndex(serverName) > -1) {
                        String input = "";
                        for (int n = 2; n < args.length; n++) {
                            if (n == args.length - 1)
                                input += args[n];
                            else
                                input += args[n] + " ";
                        }
                        server.users.get(findUserIndex(serverName)).setStatus(input);
                        save(file);
                    } else {
                        event.getChannel().sendMessage("Please make a profile first.").queue();
                    }
                } else if (args[1].equalsIgnoreCase("setAboutMe")) {
                    if (findUserIndex(serverName) > -1) {
                        String input = "";
                        for (int n = 2; n < args.length; n++) {
                            if (n == args.length - 1)
                                input += args[n];
                            else
                                input += args[n] + " ";
                        }
                        server.users.get(findUserIndex(serverName)).setAboutMe(input);
                        save(file);
                    } else {
                        event.getChannel().sendMessage("Please make a profile first.").queue();
                    }
                } else if (args[1].equalsIgnoreCase("setPFP")) {
                    if (findUserIndex(serverName) > -1) {
                        server.users.get(findUserIndex(serverName)).setPfp(args[2]);
                        save(file);
                    } else {
                        event.getChannel().sendMessage("Please make a profile first.").queue();
                    }
                } else if (args[1].equalsIgnoreCase("setAge")) {
                    if (findUserIndex(serverName) > -1) {
                        try {
                            int index = Integer.parseInt(args[2]);
                            server.users.get(findUserIndex(serverName)).setAge(index);
                            save(file);
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                            event.getChannel().sendMessage("Input an integer number after " + Main.prefix + "setAge.").queue();
                        }
                    }
                }
            }
            else {
                if (findUserIndex(serverName) > -1) {
                    event.getChannel().sendMessage(server.users.get(findUserIndex(serverName)).toString()).queue();
                }
                else {
                    event.getChannel().sendMessage("Please make a profile with " + Main.prefix + "profile new").queue();
                }
            }
        }
        else if (args[0].equalsIgnoreCase(Main.prefix + "seeProfile")) {
            if (findUserIndex(args[1]) > -1) {
                event.getChannel().sendMessage(server.users.get(findUserIndex(args[1])).toString()).queue();
            }
            else
                event.getChannel().sendMessage("user not found").queue();
        }
    }
    public void save(String serverName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(serverName + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(server);
            out.close();
            fileOut.close();
        }
        catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void load(String serverName) {
        server = null;
        try {
            FileInputStream fileIn = new FileInputStream(serverName + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            server = (Server) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            server = new Server();
        }
        catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
            return;
        }
    }
    public int findUserIndex(String servername){
        for (int j = 0; j < server.users.size(); j++) {
            if (server.users.get(j).getServerName().equals(servername)) {
                return j;
            }
        }
        return -1;
    }
}
