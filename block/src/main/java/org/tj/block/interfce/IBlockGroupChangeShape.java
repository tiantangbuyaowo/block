package org.tj.block.interfce;

import java.util.List;

import org.tj.block.model.Block;

/**
 * 形状变形的接口
 * 
 * @author 唐靖
 *
 * @date 2018年6月16日下午9:39:32
 *
 */
public interface IBlockGroupChangeShape {
	/**
	 * 获取变形后的形状
	 * 
	 * @author 唐靖
	 * @return
	 *
	 * @date 2018年6月16日下午9:37:02
	 *
	 */
	public List<Block> changeShape();

	/**
	 * 获取这个形状的方块列表
	 * 
	 * @author 唐靖
	 *
	 * @date 2018年6月16日下午10:38:05
	 *
	 * @return
	 */
	public List<Block> getShapeBlockList();

	/**
	 * 变形
	 * 
	 * @author 唐靖
	 * @return
	 *
	 * @date 2018年6月16日下午9:37:02
	 *
	 */
	public void updateShape(List<Block> newBlock);
}
