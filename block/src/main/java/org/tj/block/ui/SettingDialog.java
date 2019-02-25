package org.tj.block.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/***
 * 定义的Dialog对话框
 * 
 * @author WXW
 *
 */
public class SettingDialog extends JDialog implements ActionListener {
	public SettingDialog(JFrame parent, boolean modal) {
		super(parent, modal);
		getContentPane().setLayout(null);

		JLabel label = new JLabel("显示网格");
		label.setBounds(27, 12, 59, 18);
		getContentPane().add(label);

		JRadioButton radioButton = new JRadioButton("是");
		radioButton.setBounds(144, 6, 59, 30);
		getContentPane().add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("否");
		radioButton_1.setBounds(209, 6, 75, 30);
		getContentPane().add(radioButton_1);

		JLabel label_1 = new JLabel("背景音乐");
		label_1.setBounds(27, 54, 55, 18);
		getContentPane().add(label_1);

		JRadioButton radioButton_2 = new JRadioButton("是");
		radioButton_2.setBounds(144, 48, 59, 30);
		getContentPane().add(radioButton_2);

		JRadioButton radioButton_3 = new JRadioButton("否");
		radioButton_3.setBounds(209, 48, 75, 30);
		getContentPane().add(radioButton_3);

		JLabel label_2 = new JLabel("游戏难度");
		label_2.setBounds(27, 98, 55, 18);
		getContentPane().add(label_2);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(159, 94, 92, 26);
		getContentPane().add(comboBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
