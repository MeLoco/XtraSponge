import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;


public class lsBlockListener extends BlockListener{
	public static lavasponge plugin;
	public lsBlockListener(lavasponge instance){
		this.plugin = instance;
	}
	
	Logger logg = Logger.getLogger("Minecraft");
	
	public void onBlockFromTo(BlockFromToEvent e){
		World w = e.getBlock().getWorld();
		Block bf = e.getBlock();
		Block bt = e.getToBlock();
		int bx = bt.getX();
		int by = bt.getY();
		int bz = bt.getZ();
		boolean il = bf.getTypeId() == 10 || bf.getTypeId() == 11;
		boolean iw = bf.getTypeId() == 8 || bf.getTypeId() == 9;
		if(plugin.enableLava && il || plugin.enableWater && iw){
			for(int nx = -2; nx <= 2; nx++){
				for(int ny = -2; ny <= 2; ny++){
					for(int nz = -2; nz <= 2; nz++){
						if(w.getBlockTypeIdAt(nx+bx,ny+by,nz+bz) == 19){
							e.setCancelled(true);
						}
					}
				}
			}
		}
	}
	public void onBlockPlace(BlockPlaceEvent e){
		Block b = e.getBlockPlaced();
		Block btr = null;
		World w = b.getWorld();
		if(b.getTypeId() == 19){
			int bx = b.getX();
			int by = b.getY();
			int bz = b.getZ();		
			int sR = plugin.radius;			
			for(int x = bx - sR; x <= bx + sR; x++){
				for(int y = by - sR;y<= by + sR;y++){
					for(int z = bz - sR; z<= bz + sR;z++){
						int bid = w.getBlockTypeIdAt(x,y,z);
						if(plugin.enableWater && (bid == 8 || bid  == 9) || plugin.enableLava && (bid  == 10 || bid  == 11)){
							w.getBlockAt(x,y,z).setTypeId(0);
						}
					}
				}
			}
		}		
	}	
}
