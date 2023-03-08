package com.pokemonsound;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
		name = "Pokemon Low HP Sound",
		description = "plays the pokemon low hp sound when player health is below a certain threshold",
		tags = {"health", "hitpoints", "notifications","pokemon", "hp","sound"}
)
public class PokemonSoundPlugin extends Plugin
{
	@Inject
	private Notifier notifier;

	@Inject
	private Client client;

	@Inject
	private LowHPConfig config;

	private boolean notifyHitpoints = true;

	private boolean isLowHP = false;



	//private String soundFile = "src/main/java/com/pokemonsound/lowHPSound.wav";


	 PokemonSoundClip pokemonSoundClip = new PokemonSoundClip();

	@Override
	protected void startUp()
	{
		pokemonSoundClip.generateClip();
	}

	@Provides
	LowHPConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(LowHPConfig.class);
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		final Player local = client.getLocalPlayer();

		if (client.getGameState() != GameState.LOGGED_IN
				|| local == null)
		{
			return;
		}

		checkLowHitpoints();
		if (isLowHP)
			pokemonSoundClip.play();
		else
			pokemonSoundClip.stop();

	}



	private void checkLowHitpoints()
	{

		if (config.getHitpointsThreshold() == 0)
		{
			isLowHP =false;
		}
		if (client.getRealSkillLevel(Skill.HITPOINTS) > config.getHitpointsThreshold())
		{
			if (client.getBoostedSkillLevel(Skill.HITPOINTS) + client.getVarbitValue(Varbits.NMZ_ABSORPTION) <= config.getHitpointsThreshold())
			{
				if (!notifyHitpoints)
				{
					notifyHitpoints = true;
					isLowHP= true;
				}
			}
			else
			{
				notifyHitpoints = false;
				isLowHP = false;
			}
		}

	}


}