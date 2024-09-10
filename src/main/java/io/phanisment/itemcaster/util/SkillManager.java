package io.phanisment.itemcaster.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import de.tr7zw.changeme.nbtapi.NBTCompoundList;
import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;

import io.phanisment.itemcaster.ItemCaster;
import io.phanisment.itemcaster.MythicMobs;
import io.phanisment.itemcaster.util.SkillRunnable;

public class SkillManager {
	private Player player;
	private String event;
	
	public String SKILL;
	public String ACTION;
	public Integer TIMER;
	
	public SkillManager(Player player, String event) {
		this.player = player;
		this.event = event;
	}

	public SkillManager runSkill() {
		ItemStack item = player.getInventory().getItemInMainHand();
		NBTItem nbtItem = new NBTItem(item);
		NBTCompoundList abilities = nbtItem.getCompoundList("Abilities");
		if (abilities != null) {
			for (ReadWriteNBT ability : abilities) {
				this.SKILL = ability.getString("skill");
				this.ACTION = ability.getString("action");
				this.TIMER = ability.getInteger("timer");
			}
		}
		return this;
	}

	public void activeSkill() {
		if (this.event == this.ACTION && this.SKILL != null || this.ACTION != null || this.TIMER == null) {
			// Plans i want make is make a custom cooldown.
			MythicMobs.runSkill(this.SKILL, player);
		}
	}
	
	public void passiveSkill() {
		if (this.event == this.ACTION && this.SKILL != null || this.ACTION != null || this.TIMER != null) {
			new SkillRunnable(this.player, this.SKILL).runTaskLater(ItemCaster.getPlugin(ItemCaster.class), this.TIMER);
		}
	}
}