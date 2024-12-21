package phanisment.itemcaster.command;

import org.bukkit.command.CommandSender;
import java.util.Collections;
import java.util.List;

public interface SubCommand {
	void execute(CommandSender sender, String[] args);
	default List<String> tabComplete(CommandSender sender, String[] args) {
		return Collections.emptyList(); 
	}
}