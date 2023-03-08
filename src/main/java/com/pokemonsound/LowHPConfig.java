package com.pokemonsound;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;


@ConfigGroup("LowHP")
public interface LowHPConfig extends Config {

		@ConfigItem(
				keyName = "hitpoints",
				name = "Hitpoints Threshold",
				description = "The amount of hitpoints to start sound",
				position = 6
		)

		default int getHitpointsThreshold() {
			return 0;
		}
	}


