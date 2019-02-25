package org.tj.block.model.bus;

import java.util.List;

import org.tj.block.model.Block;

/**
 * 创建新的移动方块bus事件对应的model
 * 
 * @author 唐靖
 *
 * @date 2018年6月23日下午10:36:35
 *
 */
public class CreateNewMoveBlockBusModel {
	public List<Block> blocks;
	public int startX;

	public CreateNewMoveBlockBusModel(List<Block> blocks, int startX) {
		super();
		this.blocks = blocks;
		this.startX = startX;
	}

}
