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
	public static XtraSponge plugin;
	public lsBlockListener(XtraSponge instance){
		this.plugin = instance;
	}
	
	Logger logg = Logger.getLogger("Minecraft");
	
	public void onBlockFromTo(BlockFromToEvent e){
		World w = e.getBlock().getWorld(); // Världen som de händer i
		Block bf = e.getBlock(); // Blocket innan förändring
		Block bt = e.getToBlock(); // Blocket efter förändring
		int bx = bt.getX(); //bt X värde
		int by = bt.getY(); //bt Y värde
		int bz = bt.getZ(); //bt Z värde
		boolean il = bf.getTypeId() == 10 || bf.getTypeId() == 11;
		boolean iw = bf.getTypeId() == 8 || bf.getTypeId() == 9;
		if(plugin.enableLava && il || plugin.enableWater && iw){ //Kolla om lava/vatten är enabled, och om blocket är lava/vatten
			for(int nx = -2; nx <= 2; nx++){ // Loopa x axeln
				for(int ny = -2; ny <= 2; ny++){ // Loopa y axeln på alla x
					for(int nz = -2; nz <= 2; nz++){ // loopa z axeln på alla x och y
						if(w.getBlockTypeIdAt(nx+bx,ny+by,nz+bz) == 19){ //kollar om blocket är en sponge
							e.setCancelled(true); // avbryter block, så lava och vatten inte sprider sig till borttagna block
						}
					}
				}
			}
		}
	}
	public void onBlockPlace(BlockPlaceEvent e){
		Block b = e.getBlockPlaced(); // Vårat placerade block fångat av listenern
		Block btr = null; // blockvariabel för senare bruk
		World w = b.getWorld(); // världen som blocket placerades i
		if(b.getTypeId() == 19){ // om blockets ID är 19 (blocket är en sponge)
			int bx = b.getX(); //x värde för placerat block
			int by = b.getY(); //y värde för placerat block
			int bz = b.getZ(); //z värde för placerat block	 	
			int sR = plugin.radius;			
			for(int x = bx - sR; x <= bx + sR; x++){ // Loopa alla x som är blockets x värde - radien, fram till blockets x + radien
				for(int y = by - sR;y<= by + sR;y++){ // Loopa alla y i alla x inom radien
					for(int z = bz - sR; z<= bz + sR;z++){ // Loopa alla z inom alla x och y värden inom radien.
						int bid = w.getBlockTypeIdAt(x,y,z); //ny int med de loopade blockets värde
						if(plugin.enableWater && (bid == 8 || bid  == 9) || plugin.enableLava && (bid  == 10 || bid  == 11)){ //kolla om lava eller vatten är togglat samt om blocket är lava eller vatten
							w.getBlockAt(x,y,z).setTypeId(0); //byt ut blocket mot blockID 0 (Luft/air)
						}
					}
				}
			}
		}		
	}	
}
