package org.tj.block.model;

import java.util.ArrayList;
import java.util.List;

import org.tj.block.interfce.IBlockGroupChangeShape;

/**
 * L形状的方块组
 * 
 * @author 唐靖
 *
 * @date 2018年6月16日下午9:47:12
 *
 */
public class LBlockGroup extends BlockGroup implements IBlockGroupChangeShape {

	/**
	 * 总共有几种形态
	 */
	private int totalShape = 8;

	public LBlockGroup(int startX) {
		super(startX);
		this.blockList = getNewShage(); // 初始化一种形态

	}

	@Override
	protected List<List<Block>> initShapePool() {
		List<List<Block>> pool = new ArrayList<List<Block>>();

		List<Block> blocks = new ArrayList<Block>();

		Block b = new Block();

		/// **
		/// *
		/// *
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = 0;
		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = -1;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = -2;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX + 1;
		b.bgArrayY = -2;

		blocks.add(b);
		pool.add(blocks);

		// *
		// *
		// **
		blocks = new ArrayList<Block>();

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = 0;
		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = -1;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = -2;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX + 1;
		b.bgArrayY = 0;

		blocks.add(b);
		pool.add(blocks);

		/// *
		/// *
		// **
		blocks = new ArrayList<Block>();
		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = 0;
		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX + 1;
		b.bgArrayY = 0;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX + 1;
		b.bgArrayY = -1;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX + 1;
		b.bgArrayY = -2;

		blocks.add(b);
		pool.add(blocks);

		// **
		/// *
		/// *
		blocks = new ArrayList<Block>();
		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = 0;
		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = -1;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = -2;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX - 1;
		b.bgArrayY = -2;

		blocks.add(b);
		pool.add(blocks);

		// *
		// ***
		blocks = new ArrayList<Block>();
		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = 0;
		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = -1;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX + 1;
		b.bgArrayY = 0;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX + 2;
		b.bgArrayY = 0;

		blocks.add(b);
		pool.add(blocks);
		// //*
		// ***
		blocks = new ArrayList<Block>();
		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = 0;
		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX + 1;
		b.bgArrayY = 0;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX + 2;
		b.bgArrayY = 0;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX + 2;
		b.bgArrayY = -1;

		blocks.add(b);
		pool.add(blocks);
		// ***
		// //*
		blocks = new ArrayList<Block>();
		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = 0;
		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX + 1;
		b.bgArrayY = 0;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX + 2;
		b.bgArrayY = 0;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX + 2;
		b.bgArrayY = 1;

		blocks.add(b);
		pool.add(blocks);
		// ***
		// *
		blocks = new ArrayList<Block>();
		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = 0;
		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX;
		b.bgArrayY = -1;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX + 1;
		b.bgArrayY = -1;

		blocks.add(b);

		b = new Block();
		b.color = this.groupBlockColor;
		b.bgArrayX = startX + 2;
		b.bgArrayY = -1;

		blocks.add(b);
		pool.add(blocks);

		return pool;

	}

	@Override
	public List<Block> changeShape() {
		List<Block> nb = getNewShage();

		int currentX = this.blockList.get(0).bgArrayX;
		int currentY = this.blockList.get(0).bgArrayY;
		int temp = currentX - startX;
		for (int i = 0; i < nb.size(); i++) {
			nb.get(i).bgArrayY = nb.get(i).bgArrayY + currentY;
			nb.get(i).bgArrayX = nb.get(i).bgArrayX + temp;
		}

		return nb;

	}

	private List<Block> getNewShage() {
		int type = (int) (Math.random() * totalShape);
		while (type == currentShapeIndex) { // 变形一定要和当前不一样
			type = (int) (Math.random() * totalShape);
		}

		this.currentShapeIndex = type;
		return super.getCloneShapePool().get(type);

	}

	@Override
	public List<Block> getShapeBlockList() {
		return this.blockList;
	}

	@Override
	public void updateShape(List<Block> newBlock) {
		this.blockList.clear();
		this.blockList.addAll(newBlock);

	}

}
