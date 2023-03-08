package com.pokemonsound;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PokemonSoundPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(com.pokemonsound.PokemonSoundPlugin.class);
		RuneLite.main(args);
	}
}