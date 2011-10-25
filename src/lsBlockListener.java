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
		World w = e.getBlock().getWorld(); // V�rlden som de h�nder i
		Block bf = e.getBlock(); // Blocket innan f�r�ndring
		Block bt = e.getToBlock(); // Blocket efter f�r�ndring
		int bx = bt.getX(); //bt X v�rde
		int by = bt.getY(); //bt Y v�rde
		int bz = bt.getZ(); //bt Z v�rde
		boolean il = bf.getTypeId() == 10 || bf.getTypeId() == 11;
		boolean iw = bf.getTypeId() == 8 || bf.getTypeId() == 9;
		if(plugin.enableLava && il || plugin.enableWater && iw){ //Kolla om lava/vatten �r enabled, och om blocket �r lava/vatten
			for(int nx = -2; nx <= 2; nx++){ // Loopa x axeln
				for(int ny = -2; ny <= 2; ny++){ // Loopa y axeln p� alla x
					for(int nz = -2; nz <= 2; nz++){ // loopa z axeln p� alla x och y
						if(w.getBlockTypeIdAt(nx+bx,ny+by,nz+bz) == 19){ //kollar om blocket �r en sponge
							e.setCancelled(true); // avbryter block, s� lava och vatten inte sprider sig till borttagna block
						}
					}
				}
			}
		}
	}
	public void onBlockPlace(BlockPlaceEvent e){
		Block b = e.getBlockPlaced(); // V�rat placerade block f�ngat av listenern
		Block btr = null; // blockvariabel f�r senare bruk
		World w = b.getWorld(); // v�rlden som blocket placerades i
		if(b.getTypeId() == 19){ // om blockets ID �r 19 (blocket �r en sponge)
			int bx = b.getX(); //x v�rde f�r placerat block
			int by = b.getY(); //y v�rde f�r placerat block
			int bz = b.getZ(); //z v�rde f�r placerat block	 	
			int sR = plugin.radius;			
			for(int x = bx - sR; x <= bx + sR; x++){ // Loopa alla x som �r blockets x v�rde - radien, fram till blockets x + radien
				for(int y = by - sR;y<= by + sR;y++){ // Loopa alla y i alla x inom radien
					for(int z = bz - sR; z<= bz + sR;z++){ // Loopa alla z inom alla x och y v�rden inom radien.
						int bid = w.getBlockTypeIdAt(x,y,z); //ny int med de loopade blockets v�rde
						if(plugin.enableWater && (bid == 8 || bid  == 9) || plugin.enableLava && (bid  == 10 || bid  == 11)){ //kolla om lava eller vatten �r togglat samt om blocket �r lava eller vatten
							w.getBlockAt(x,y,z).setTypeId(0); //byt ut blocket mot blockID 0 (Luft/air)
						}
					}
				}
			}
		}		
	}	
}
