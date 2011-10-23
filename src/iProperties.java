import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;


public final class iProperties {
	
	private static final Logger log = Logger.getLogger("Minecraft");
	private Properties properties;
	private String fn;
	
	public iProperties(String fn){
		this.fn = fn;
		properties = new Properties();
		File file = new File(fn);
		if(file.exists()){
			load();
		}else{
			save();
		}
	}
	public void load(){
		try{
			properties.load(new FileInputStream(fn));
		}
		catch(IOException ex){
			log.log(Level.SEVERE, (new StringBuilder("Unable to load ")).append(fn).toString(), ex);
		}
	}
	public void save(){
		try{
			properties.store(new FileOutputStream(fn), "Minecraft Properties File");
		}catch(IOException ex){
			log.log(Level.SEVERE, (new StringBuilder("Unable to save ")).append(fn).toString(),ex);
		}
	}
	public void removeKey(String k){
		properties.remove(k);
		save();
	}
	public boolean keyExists(String k){
		return properties.containsKey(k);
	}
	public String getString(String k){
		if(properties.containsKey(k)){
			return properties.getProperty(k);
		}else{
			return "";
		}
	}
	public String getString(String k, String v){
		if(properties.containsKey(k)){
			return properties.getProperty(k);
		}else{
			setString(k,v);
			return v;
		}
	}
	public void setString(String k, String v){
		properties.setProperty(k, v);
		save();
	}
	public int getInt(String k){
		if(properties.containsKey(k)){
			return Integer.parseInt(properties.getProperty(k));
		}else{
			return 0;
		}
	}
	public int getInt(String k, int v){
		if(properties.containsKey(k)){
			return Integer.parseInt(properties.getProperty(k));
		}else{
			setInt(k,v);
			return v;
		}
	}
	public void setInt(String k, int v){
		properties.setProperty(k, String.valueOf(v));
		save();
	}
	public double getDouble(String k){
		if(properties.containsKey(k)){
			return Double.parseDouble(properties.getProperty(k));
		}else{
			return 0.0D;
		}
	}
	public double getDouble(String k, double v){
		if(properties.containsKey(k)){
			return Double.parseDouble(properties.getProperty(k));
		}else{
			setDouble(k, v);
			return 0.0D;
		}
	}
	public void setDouble(String k, double v){
		properties.setProperty(k, String.valueOf(v));
		save();
	}
	public long getLong(String k){
		if(properties.contains(k)){
			return Long.parseLong(properties.getProperty(k));
		}else{
			return 0L;
		}
	}	
	public long getLong(String k, long v){
		if(properties.contains(k)){
			return Long.parseLong(properties.getProperty(k));
		}else{
			setLong(k, v);
			return 0L;
		}
	}
		public void setLong(String k, long v){
			properties.setProperty(k, String.valueOf(v));
			save();
		}
		public boolean getBoolean(String k){
			if(properties.contains(k)){
				return Boolean.parseBoolean(properties.getProperty(k));
			}else{
				return false;
			}
		}
		public boolean getBoolean(String k, boolean v){
			if(properties.contains(k)){
				return Boolean.parseBoolean(properties.getProperty(k));
			}else{
				setBoolean(k,v);
				return false;
			}
		}
		public void setBoolean(String k, boolean v){
			properties.setProperty(k, String.valueOf(v));
			save();
		}
}
