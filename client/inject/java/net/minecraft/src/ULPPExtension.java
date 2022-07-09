package net.minecraft.src;

public class ULPPExtension {
	private int version;
	private String name;
	
	public int getVersion() {
		return this.version;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ULPPExtension(String name, int version) {
		this.version = version;
		this.name = name;
	}
}
