package org.tj.block;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.tj.block.model.MoveDirection;
import org.tj.block.ui.AlertBlackPanel;
import org.tj.block.ui.BlockPanel;
import org.tj.block.ui.SettingDialog;
import org.tj.block.util.BusUtil;
import org.tj.block.util.SoundTool;
import org.tj.block.util.TimeUtil;

import com.google.common.eventbus.Subscribe;

@SuppressWarnings("serial")
public class GameMain extends JFrame {

	private JPanel contentPane;
	private JTextField timeTextField;
	private JTextField scoreTextField;
	private JTextField levelTextField;
	private static BlockPanel rightPanel;
	private static GameMain frame;
	private static AlertBlackPanel alertPanel;
	private static Date startTime = new Date();

	/**
	 * 是否暂停
	 */
	public static boolean PAUSE = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.put("RootPane.setupButtonVisible", false);
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GameMain();
					frame.setVisible(true);
					frame.setFocusable(true);
					initEventBus();
					initListener();
					initMusic();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 初始化背景音乐
	 * 
	 * @author 唐靖
	 *
	 * @date 2018年6月22日下午11:06:16
	 *
	 */
	protected static void initMusic() {
		SoundTool.back();

	}

	/**
	 * 初始化eventbus
	 * 
	 * @author 唐靖
	 * @date 2016年10月9日下午10:30:32
	 */
	private static void initEventBus() {
		BusUtil.blockMoveBus.register(rightPanel);
		BusUtil.createNewMoveBlockBus.register(alertPanel);
		BusUtil.getScoreBus.register(frame);
	}

	/**
	 * Create the frame.
	 */
	public GameMain() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 582);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("菜单");
		menuBar.add(menu);

		JMenuItem mntmNewMenuItem = new JMenuItem("设置");
		menu.add(mntmNewMenuItem);

		mntmNewMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				Map<String, String> caipinMap = new HashMap<String, String>();
//				String yxrsKey = "diyidaocai";
//				String yxrsVal = "1";
//				String djjdKey = "dierdaocai";
//				String djjdVal = "65";
//				String fqjdKey = "disandaocai";
//				String fqjdVal = "123";
//				String abcdKey = "disidaocai";
//				String abcdVal = "7";
//				caipinMap.put(yxrsKey, yxrsVal);
//				caipinMap.put(djjdKey, djjdVal);
//				caipinMap.put(fqjdKey, fqjdVal);
//				caipinMap.put(abcdKey, abcdVal);
//
				SettingDialog dialog = new SettingDialog(frame, true);
				Point point = frame.getLocation();// 获得主窗体在屏幕的坐标
				dialog.setLocation(point.x + frame.getWidth() / 2 - dialog.getWidth() / 2,
						point.y + frame.getHeight() / 2 - dialog.getHeight() / 2);
				dialog.setVisible(true);

			}
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 0));

		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(BorderFactory.createTitledBorder("左")); // 设置面板边框，实现分组框的效果，此句代码为关键代码

		leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));// 设置面板边框颜色
		leftPanel.setPreferredSize(new Dimension(110, 10));
		contentPane.add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(null);

		JLabel timeLabel = new JLabel(" 时间:");
		timeLabel.setBounds(0, 161, 54, 15);
		leftPanel.add(timeLabel);

		timeTextField = new JTextField();
		timeTextField.setEditable(false);
		timeTextField.setBounds(34, 158, 66, 21);
		leftPanel.add(timeTextField);
		timeTextField.setColumns(10);

		JLabel scoreLabel = new JLabel(" 得分:");
		scoreLabel.setBounds(0, 189, 54, 15);
		leftPanel.add(scoreLabel);

		scoreTextField = new JTextField();
		scoreTextField.setText("0");
		scoreTextField.setEditable(false);
		scoreTextField.setBounds(34, 186, 66, 21);
		leftPanel.add(scoreTextField);
		scoreTextField.setColumns(10);

		alertPanel = new AlertBlackPanel();
		alertPanel.setBackground(Color.CYAN);
		alertPanel.setBounds(10, 51, 90, 83);
		leftPanel.add(alertPanel);

		JLabel levelLabel = new JLabel(" 难度:");
		levelLabel.setBounds(0, 217, 54, 15);
		leftPanel.add(levelLabel);

		levelTextField = new JTextField();
		levelTextField.setEditable(false);
		levelTextField.setBounds(34, 214, 66, 21);
		levelTextField.setText("初级");
		leftPanel.add(levelTextField);
		levelTextField.setColumns(10);

		final JButton pasueButton = new JButton("暂停");
		pasueButton.setBounds(12, 259, 83, 35);
		pasueButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));

		// 暂停按钮监听事件
		pasueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (PAUSE) { // 当前暂停
					PAUSE = false; // 开始
					pasueButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
					pasueButton.setText("暂停");

				} else { // 当前开始
					PAUSE = true; // 暂停
					pasueButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
					pasueButton.setText("开始");
				}
			}
		});

		leftPanel.add(pasueButton);

		JLabel titleLabel = new JLabel("俄罗斯方块");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.TOP);
		titleLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		contentPane.add(titleLabel, BorderLayout.NORTH);

		rightPanel = new BlockPanel();
		// rightPanel.setBackground(Color.red);
		contentPane.add(rightPanel, BorderLayout.CENTER);
		rightPanel.setBorder(BorderFactory.createTitledBorder("右")); // 设置面板边框，实现分组框的效果，此句代码为关键代码

		rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));// 设置面板边框颜色
		new Thread(rightPanel).start();
		/**
		 * 更新时间的线程
		 */
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					timeTextField.setText(
							TimeUtil.secToTime((int) (((new Date().getTime() - startTime.getTime()) / 1000))) + "");
				}

			}
		}).start();

	}

	@Subscribe
	private void addScore(Integer score) {
		// 更新积分
		scoreTextField.setText(Integer.valueOf(scoreTextField.getText()) + score + "");
		// 播放成功音乐
		SoundTool.scoreSound();

	}

	/**
	 * 初始化面板的监听事件
	 * 
	 * @author 唐靖
	 * @date 2016年10月9日下午10:32:31
	 */
	private static void initListener() {
		frame.addKeyListener(new KeyListener() { // 键盘监听事件
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) { // 判断事件
				case KeyEvent.VK_LEFT: // 左
					BusUtil.blockMoveBus.post(MoveDirection.LEFT);
					break;
				case KeyEvent.VK_RIGHT: // 右
					BusUtil.blockMoveBus.post(MoveDirection.RIGHT);
					break;
				case KeyEvent.VK_DOWN: // 下
					BusUtil.blockMoveBus.post(MoveDirection.DOWN);
					break;
				case KeyEvent.VK_UP: // 上
					BusUtil.blockMoveBus.post(MoveDirection.UP);
					break;
				default:
					break;
				}
			}
		});
		// 窗口关闭事件
		frame.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) { // 关闭事件
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
	}
}
