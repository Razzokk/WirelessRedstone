package rzk.wirelessredstone.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import rzk.wirelessredstone.misc.TranslationKeys;
import rzk.wirelessredstone.registry.ModBlocks;
import rzk.wirelessredstone.registry.ModItems;

public class DefaultLanguageGenerator extends FabricLanguageProvider
{
	protected DefaultLanguageGenerator(FabricDataOutput dataOutput)
	{
		super(dataOutput);
	}

	@Override
	public void generateTranslations(TranslationBuilder translations)
	{
		// Blocks
		translations.add(ModBlocks.redstoneTransmitter, "Redstone Transmitter");
		translations.add(ModBlocks.redstoneReceiver, "Redstone Receiver");
		translations.add(ModBlocks.p2pRedstoneTransmitter, "P2P Redstone Transmitter");
		translations.add(ModBlocks.p2pRedstoneReceiver, "P2P Redstone Receiver");

		// Items
		translations.add(ModItems.circuit, "Circuit");
		translations.add(ModItems.frequencyTool, "Frequency Tool");
		translations.add(ModItems.frequencySniffer, "Frequency Sniffer");
		translations.add(ModItems.remote, "Remote");
		translations.add(ModItems.linker, "Linker");

		// Guis
		translations.add(TranslationKeys.GUI_FREQUENCY_TITLE, "Frequency");
		translations.add(TranslationKeys.GUI_CONFIG_TITLE, "Wireless Redstone Config");
		translations.add(TranslationKeys.GUI_CONFIG_CATEGORY_GENERAL, "General");
		translations.add(TranslationKeys.GUI_CONFIG_CATEGORY_CLIENT, "Client");
		translations.add(TranslationKeys.GUI_CONFIG_SIGNAL_STRENGTH, "Signal strength");
		translations.add(TranslationKeys.GUI_CONFIG_STRONG_POWER, "Strong power");
		translations.add(TranslationKeys.GUI_CONFIG_DISPLAY_COLOR, "Display color");
		translations.add(TranslationKeys.GUI_CONFIG_HIGHLIGHT_COLOR, "Highlight color");
		translations.add(TranslationKeys.GUI_CONFIG_HIGHLIGHT_TIME, "Highlight time");
		translations.add(TranslationKeys.GUI_CONFIG_TARGET_COLOR, "Target highlight color");

		// Tooltips
		translations.add(TranslationKeys.TOOLTIP_FREQUENCY, "Frequency: %s");
		translations.add(TranslationKeys.TOOLTIP_STATE, "State: %s");
		translations.add(TranslationKeys.TOOLTIP_STATE_OFF, "Off");
		translations.add(TranslationKeys.TOOLTIP_STATE_ON, "On");
		translations.add(TranslationKeys.TOOLTIP_POSITION, "[x: %s, y: %s, z: %s]");
		translations.add(TranslationKeys.TOOLTIP_TARGET, "Target: %s");

		// Other
		translations.add(TranslationKeys.ITEM_GROUP_WIRELESS_REDSTONE, "Wireless Redstone");
		translations.add(TranslationKeys.MESSAGE_TRANSMITTERS_EMPTY, "No active transmitters on frequency %s");
		translations.add(TranslationKeys.MESSAGE_TRANSMITTERS_ACTIVE, "Active transmitters on frequency %s: %s");
		translations.add(TranslationKeys.MESSAGE_TELEPORT, "Teleport here");
		translations.add(TranslationKeys.MESSAGE_NO_FREQUENCY, "No frequency set");
		translations.add(TranslationKeys.NETWORKING_FAILED, "Networking error: %s");
		translations.add(TranslationKeys.MESSAGE_P2P_NO_RECEIVER, "Target is not a %s");
		translations.add(TranslationKeys.MESSAGE_P2P_LINKED, "Linked to target at: %s");
	}
}
