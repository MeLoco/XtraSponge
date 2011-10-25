
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.PrintStream;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.WorldListener;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;


/* Hej granis :) Eller annan utvecklare */

public class XtraSponge extends JavaPlugin {
	public static XtraSponge plugin;
	boolean enableLava;
	boolean enableWater;
	int radius;
	public static iProperties Settings;
	public static String dir;
	Logger log = Logger.getLogger("Minecraft");
	private final lsBlockListener blockListener = new lsBlockListener(this);
	
	public void onEnable(){
		PluginDescriptionFile pdfFile = getDescription();
		System.out.println((new StringBuilder(String.valueOf(pdfFile.getName()))).append(" version ").append(pdfFile.getVersion()).append(" is enabled by Joshcvb!").toString());
        init();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_PLACE, this.blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_FROMTO, this.blockListener, Event.Priority.Normal, this);
		log.info("[0.1]XtraSponge turned on.");
	}
	/* init() skapar en fil för settings, och läser in variablerna */
	public void init(){
		(new File(dir)).mkdir();
		Settings = new iProperties((new StringBuilder(String.valueOf(dir))).append("config.yml").toString());
		enableWater = Settings.getBoolean("EnableWaterSponge", false); // EnableWaterSponge är variabeln i settingsfilen
		enableLava = Settings.getBoolean("EnableLavaSponge", false); // EnableLavaSponge är variabeln i settingsfilen
		radius = Math.max(1, Settings.getInt("SpongeRadius",3)) - 1; // SpongeRadius är variabeln i settingsfilen
	}
	static{
		dir = (new StringBuilder("Sponge")).append(File.separator).toString();
	}
	
	public void onDisable(){
		log.info("XtraSponge turned off.");		
	}
}
