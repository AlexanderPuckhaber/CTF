package net.askarian.MisterErwin.CTF.threads;

import net.askarian.MisterErwin.CTF.MapManager;

import org.bukkit.block.Block;

public class BlockResetThread implements Runnable{

  private Block b;
	private MapManager mm;
	
	public BlockResetThread(Block block, MapManager mm) {
		b = block;
		mm = mm;
	}
	
	public BlockResetThread(Block block) {
		b = block;
	}

	@Override
	public void run() {
		b.setTypeId(0);
		if (mm != null)
			mm.ChangeBlock(b, 0, (byte) 0x00);
	}
}
