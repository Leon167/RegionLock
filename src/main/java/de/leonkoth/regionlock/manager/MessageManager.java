package de.leonkoth.regionlock.manager;

/*
 * Copyright (C) 2014 Leon167, XxChxppellxX, ScriptJunkie and CPx1989
 */

import de.leonkoth.regionlock.Locale;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class MessageManager {

	private Plugin p;

	public MessageManager(Plugin p)
	{
		this.p = p;
	}

	public String colorizeString(String input){
		return ChatColor.translateAlternateColorCodes('&', input);
	}
	
	
	public enum MessageType {
		INFO(ChatColor.GRAY, ""), ERROR(ChatColor.RED, "§cError: "), BAD(ChatColor.RED, "");

		private ChatColor color;
		private String prefix;

		private MessageType(ChatColor color, String prefix) {
			this.color = color;
			this.prefix = prefix;
		}

		public ChatColor getColor() {
			return this.color;
		}

		public String getPrefix() {
			return this.prefix;
		}
	}

	//private static MessageManager instance = new MessageManager();
	private String messagePrefix = Locale.PLUGIN_PREFIX + " " + ChatColor.RESET;

	/*public static MessageManager getInstance() {
		return instance;
	}**/

	public void log(String message) {
		System.out.println(message);
	}

	public void msg(CommandSender sender, MessageType type, String message) {
		sender.sendMessage(this.messagePrefix + type.getPrefix() + type.getColor() + message);
	}

	public void broadcast(MessageType type, String message) {
		this.p.getServer().broadcastMessage(this.messagePrefix + type.getPrefix() + type.getColor() + message);
	}

}