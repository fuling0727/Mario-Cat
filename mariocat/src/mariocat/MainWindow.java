package mariocat;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.Rectangle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;



public class MainWindow extends JFrame{
	
	private static MainWindow frame = new MainWindow();
	private static JPanel screen;
	public static JLabel Mcat;

	public static JLabel[] Game1= new JLabel[19];
	public static JLabel[] Game2;
	public static JLabel coin2;
	public static JLabel pmushroom2;
	private static int flag ;
	private static int life ;
	// move
	public static int  nowx=0,nowy=550;
	public static int  jump_height = 30,move_length = 5, total_height = 0;
	
	public static int[] vector = new int[2];
	public static int[] g1 = new int[2], g2 = new int[3], isHit, g4 = new int[40];
	public static boolean right=false,left=false, jump=false,jump1 = false, land=true, door_flag = false;;
	public static int game2_count=0;
	public static int isBreak = 0,isCoin = 0,isMush = 0,isDie = 0;
	public static boolean machine = false,ghost = false,transition=false;
	public static int time =0 ,time1 =0;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
			final Timer timer = new Timer();
			
			vector[0]=0;
			vector[1]=0;
			frame.setVisible(true);
			frame.setFocusable(true);
			frame.setResizable(false);
			frame.addKeyListener( new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				    int key = e.getKeyCode();
				    //LEFT
				    if(key == KeyEvent.VK_LEFT && (flag==3|| flag==7||flag==12||flag==15||flag==18)) {
				    	if(vector[0]>=-15)
				    		vector[0]-=5;
				    	left=true;
				    }
				    //UP
				    else if(key == KeyEvent.VK_UP && (flag==3|| flag==7||flag==12||flag==15||flag==18)) {
				    	if(jump==false&&land==true) {
				    		jump= true;
				    		vector[1]=-25;
				    		land=false;
				    	}
				    	else if(jump1==false&&jump==true) {
				    		jump1 = true;
				    		vector[1]=-25;
				    	}
				    	
				    }
				    //RIGHT
				    else if(key == KeyEvent.VK_RIGHT && (flag==3|| flag==7||flag==12||flag==15||flag==18)) {
				    	if(vector[0]<=15)
					    	vector[0]+=5;
				    	right=true;
				    }
				    //ESC
				    if(key == 27){
				        frame.dispose();
				    }
				}
			
			
			public void keyReleased(KeyEvent e) {
				int code = e.getKeyCode();
			    if(code == KeyEvent.VK_UP) {
			    	jump = false;
			    	
			    }
			    if(code == KeyEvent.VK_RIGHT) {
			    	right = false;
			    	vector[0]=0;
			    }
			    if(code == KeyEvent.VK_LEFT) {
			    	left = false;
			    	vector[0]=0;
			    }
			}
			});
			flag =0;life =3;
			while(frame.isDisplayable()) {
				//show user's lives�面
				if(flag ==1) {
					System.out.println("WAIT HERE");
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					clearAll();
					flag=2;
//					flag=17;
				}
				//decorate scene�玩
				if(flag ==2) {
					System.out.println("Gaming");
					Game1();
					flag = 3;
					}
				//gamming
				if(flag == 3) {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					judgeg1();

					//System.out.println("flag == 3");
				}
				if(flag==4) {
					MainWindow.ready();
					flag=1;
				}
				if(flag==5) {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					clearAll();
					loading();
					flag=6;
				}
				if(flag==6) {
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					clearAll();
					Game2();
					flag=7;
				}
				if(flag==7) {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					judgeg2();
				}
				if(flag==8) {
					ready();
					flag=6;
				}
				if(flag==9) {
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					clearAll();
					flag=8;
				}
				if(flag==10) {
					loading();
					flag=11;
				}
				if(flag==11) {
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					clearAll();
					Game3();
					flag=12;
				}
				if(flag==12) {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					judgeg3();
				}
				if(flag==13) {
					clearAll();
					life-=1;
					ready();
					flag=11;
				}
				
				if(flag==14) {
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					clearAll();
					Game4();
					flag=15;
				}
				if(flag==15) {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					judgeg4();
				}
				if(flag==16) {
					clearAll();
					ready();
					flag=14;
				}
				if(flag==17) {
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					clearAll();
					Game5();
					flag=18;
				}
				if(flag==18) {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					judgeg5();
				}
				if(flag==19) {
					clearAll();
					ready();
					flag=17;
				}
				if(flag==20) {
					clearAll();
					flag=21;
				}
				if(flag==21) {
					finished();
					flag=22;
				}
				if(flag==22) {
					;
				}

				
			}
	}

	/**
	 * Create the frame.
	 */
	//main window
	public MainWindow() {
		System.out.println("mainwindow");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 30, 900, 700);
		setTitle("Mario Cat");
		// Set background
		ImageIcon I = new ImageIcon("purple.png");
		Image newI = I.getImage().getScaledInstance(900, 700, java.awt.Image.SCALE_SMOOTH);
		I = new ImageIcon(newI);
		JLabel background = new JLabel(I);
		background.setBounds(0, 0, 900, 700);
		// Title
		ImageIcon T = new ImageIcon("title.png");
		Image newt = T.getImage().getScaledInstance(600, 300, java.awt.Image.SCALE_SMOOTH);
		T = new ImageIcon(newt);
		JLabel title = new JLabel(T);
		title.setBounds(150, 100, 600, 300);
		//decoration
		ImageIcon Brick = new ImageIcon("brick.png");
	    Image newb = Brick.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    Brick = new ImageIcon(newb);
		JLabel[] floor = new JLabel[36];
	    for(int i = 0 ;i < 36; ++i) {
	    	floor[i] = new JLabel(Brick);
	    	floor[i].setBounds((i%18)*50, 660-50*((i/18)), 50, 50);
	    	this.add(floor[i]);
	    }
	    ImageIcon C = new ImageIcon("cat_r.png");
		Image newC = C.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		C = new ImageIcon(newC);
		JLabel cat = new JLabel(C);
		cat.setBounds(100,520,100,100);
		this.add(cat);
		ImageIcon t = new ImageIcon("tube.png");
		Image newT = t.getImage().getScaledInstance(250, 300, java.awt.Image.SCALE_SMOOTH);
		t = new ImageIcon(newT);
		JLabel tube = new JLabel(t);
		tube.setBounds(600,450,205,300);
		this.add(tube);
		// Start Button
		JPanel menu = new JPanel();
		JButton start = new JButton("START");
		start.setBackground(Color.yellow);; 
		ButtonListener sblistener = new ButtonListener(); 
		start.addActionListener(sblistener); 
		
		menu.setLayout(new GridLayout(1,1));
		menu.add(start);
		menu.setLocation(320, 450);
		menu.setSize(200,60);
		
		this.setLayout(null);
		this.add(menu);
		this.add(title);
		this.add(background);
		
		
	}

	//life
	public static void ready() {
		System.out.println("ready");
		ImageIcon I = new ImageIcon("black.png");
		Image newI = I.getImage().getScaledInstance(900, 700, java.awt.Image.SCALE_SMOOTH);
		I = new ImageIcon(newI);
		
		ImageIcon cat = new ImageIcon("cat.png");
		newI = cat.getImage().getScaledInstance(100, 200, java.awt.Image.SCALE_SMOOTH);
		cat = new ImageIcon(newI);
		
		JLabel lifetext = new JLabel(cat);
		lifetext.setBounds(300, 200, 100, 200);
		
		JLabel text = new JLabel("X "+ Integer.toString(life));
		text.setFont(new java.awt.Font("Dialog",1,100));
		text.setForeground(Color.white);
		text.setBounds(400, 145, 300, 300);
		JLabel background = new JLabel(I);
		background.setBounds(0, 0, 900, 700);
		
		frame.add(text);
		frame.add(lifetext);
		frame.add(background);

		frame.repaint();
	}
	
	//level1
	public static void Game1() {
			System.out.println("game1");
			
			screen = new JPanel();
			screen.setSize(900,700); 
			screen.setBounds(0, 0, 900, 700); 
			
			ImageIcon cat = new ImageIcon("cat_r.png");
			Image newI = cat.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
			cat = new ImageIcon(newI);
			Mcat = new JLabel(cat);
			Mcat.setBounds(0, 550, 50, 50);
			
			
			ImageIcon background = new ImageIcon("blue.png");
			Image newb = background.getImage().getScaledInstance(900, 700, java.awt.Image.SCALE_SMOOTH);
			background = new ImageIcon(newb);
		    JLabel BlueBackground = new JLabel(background);
		    BlueBackground.setBounds(0, 0, 900, 700);
		    
		    ImageIcon Brick = new ImageIcon("brick.png");
		    newb = Brick.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		    Brick = new ImageIcon(newb);
		    
		    for(int i = 0 ;i < 8; ++i) {
		    	Game1[i] = new JLabel(Brick);
		    	Game1[i].setBounds((i%4)*50, 650-50*((i/4)), 50, 50);
		    }
		    
		    for(int i = 8 ;i < 14; ++i) {
		    	Game1[i] = new JLabel(Brick);
		    	Game1[i].setBounds(((i-8)%3)*50+750, 650-50*(((i-8)/3)), 50, 50);
		    }
		    
		    ImageIcon cube = new ImageIcon("cube.png");
		    newb = cube.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		    cube = new ImageIcon(newb);
		    Game1[14]=new JLabel(cube);
		    Game1[14].setBounds(50, 450, 50, 50);
		    
		    ImageIcon fcube = new ImageIcon("fcube.png");
		    newb = fcube.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		    fcube = new ImageIcon(newb);
		    Game1[15]=new JLabel(fcube);
		    Game1[15].setBounds(0, 300, 50, 50);
		    Game1[15].setVisible(false);
		    
		    ImageIcon door = new ImageIcon("door.png");
		    newb = door.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		    door = new ImageIcon(newb);
		    Game1[17] = new JLabel(door);
		    Game1[17].setBounds(800, 550, 50, 50);
		    Game1[18] = new JLabel(door);
		    Game1[18].setBounds(0, 250, 50, 50);
		    Game1[18].setVisible(false);

		    
			Game1[16] = new JLabel("NOTHING CHANGED");
			Game1[16].setFont(new java.awt.Font("Dialog",1,50));
			Game1[16].setBounds(100, 50, 1000, 50);
			Game1[16].setVisible(false);
			
		    screen.add(Mcat);
		    for(int i=0;i<Game1.length;i++) {
		    	screen.add(Game1[i]);
		    }
		    screen.add(BlueBackground);
		    frame.add(screen);
		    frame.repaint();
	}

	//level2
	public static void Game2() {
		System.out.println("game2");
		screen = new JPanel();
		screen.setSize(900,700); 
		screen.setBounds(0, 0, 900, 700); 
		
		ImageIcon cat = new ImageIcon("cat_r.png");
		Image newI = cat.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		cat = new ImageIcon(newI);
		Mcat = new JLabel(cat);
		Mcat.setBounds(0, 550, 50, 50);
		
		ImageIcon background = new ImageIcon("blue.png");
		Image newb = background.getImage().getScaledInstance(900, 700, java.awt.Image.SCALE_SMOOTH);
		background = new ImageIcon(newb);
	    JLabel BlueBackground = new JLabel(background);
	    BlueBackground.setBounds(0, 0, 900, 700);
	    
	    ImageIcon Brick = new ImageIcon("brick.png");
	    newb = Brick.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    Brick = new ImageIcon(newb);
	    
	    Game2 = new JLabel[52];
	    
	    for(int i = 0 ;i < 36; ++i) {
	    	Game2[i] = new JLabel(Brick);
	    	Game2[i].setBounds((i%18)*50, 650-50*((i/18)), 50, 50);
	    }
		
	    for(int i = 36 ;i < 46; ++i) {
	    	Game2[i] = new JLabel(Brick);
	    	Game2[i].setBounds(((i-36)%2)*50+650, 550-50*(((i-36)/2)), 50, 50);
	    }
		
	    ImageIcon cube = new ImageIcon("cube.png");
	    newb = cube.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    cube = new ImageIcon(newb);
	    
	    for(int i=46; i<50;++i) {
	    	Game2[i] = new JLabel(cube);
	    	Game2[i].setBounds((i-46)*150+50, 450, 50, 50);
	    }
	    
	    ImageIcon door = new ImageIcon("door.png");
	    newb = door.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    door = new ImageIcon(newb);
	    Game2[50] = new JLabel(door);
	    Game2[50].setBounds(300, 300, 50, 50);
	    Game2[50].setVisible(false);
	    
	    Game2[51] = new JLabel("BYE BYE");
	    Game2[51].setFont(new java.awt.Font("Dialog",1,50));
		Game2[51].setBounds(100, 50, 1000, 100);
		Game2[51].setVisible(false);
		
		screen.add(Mcat);
		for(int i=0; i<Game2.length; i++) {
			screen.add(Game2[i]);
		}
		screen.add(BlueBackground);
		frame.add(screen);
		frame.repaint();
	}
	
	
	// Update direction
	public static void move() {
		Mcat.setBounds(Mcat.location().x+vector[0], Mcat.location().y+vector[1], 50, 50);
	}
	
	//LOADING
	public static void loading() {
		System.out.println("loading");
		
		JLabel text = new JLabel("NOW LOADING....");
		text.setFont(new java.awt.Font("Dialog",1,50));
		text.setForeground(Color.white);
		text.setBounds(200, 145, 500, 300);
		
		ImageIcon I = new ImageIcon("black.png");
		Image newI = I.getImage().getScaledInstance(900, 700, java.awt.Image.SCALE_SMOOTH);
		I = new ImageIcon(newI);
		JLabel background = new JLabel(I);
		background.setBounds(0, 0, 900, 700);
		
		frame.add(text);
		frame.add(background);
		frame.repaint();
	}
	
	//judge for g1
	public static void judgeg1() {
		if(Mcat.getLocation().y>700) {
			flag=4;
			life-=1;
			clearAll();
			return;
		}
		land =false;
		if(land ==false) {
			vector[1]+=3;
		}
		//check whether collide or not y axis
		Rectangle B = new Rectangle(Mcat.location().x,Mcat.location().y+vector[1],Mcat.getWidth(),Mcat.getHeight());
		for(int i=0;i<16;i++) {
			Rectangle A = new Rectangle(Game1[i].location().x+2,Game1[i].location().y,Game1[i].getWidth()-4,Game1[i].getHeight());
			if(B.intersects(A)&&Mcat.location().y<=Game1[i].location().y-49&&Game1[i].location().x+2-50<Mcat.location().x&&Game1[i].location().x+2+50>Mcat.location().x) {
				land=true;
				Mcat.setBounds(Mcat.location().x, Game1[i].location().y-49, 50, 50);
			}
			else if(B.intersects(A)&&Mcat.location().y>Game1[i].location().y+50&&Game1[i].location().x+2-50<Mcat.location().x&&Game1[i].location().x+2+50>Mcat.location().x) {
				Mcat.setBounds(Mcat.location().x, Game1[i].location().y+50, 50, 50);
				vector[1]=-vector[1];
				if(i==14&&g1[0]==0) {
					ImageIcon fcube = new ImageIcon("fcube.png");
				    Image newb = fcube.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
				    fcube = new ImageIcon(newb);
					Game1[14] = new JLabel(fcube);
					Game1[14].setBounds(50, 450, 50, 50);
					g1[0]=1;
					repaintGame1();
						
				}
				if(i==15&&g1[1]==0) {
					g1[1]=1;
					repaintGame1();
					
				}
			}
		}
		//check whether collide or not x axis
		B = new Rectangle(Mcat.location().x+vector[0],Mcat.location().y,Mcat.getWidth(),Mcat.getHeight());
		for(int i=0;i<16;i++) {
			Rectangle A = new Rectangle(Game1[i].location().x+2,Game1[i].location().y,Game1[i].getWidth()-4,Game1[i].getHeight());
			if(B.intersects(A)&&Mcat.location().x<=Game1[i].location().x+2-50&&Game1[i].location().y-48<Mcat.getLocation().y&&Game1[i].location().y+48>Mcat.getLocation().y) {
				Mcat.setBounds(Game1[i].location().x-50+2, Mcat.location().y, 50, 50);
				vector[0]=0;
			}
			else if(B.intersects(A)&&Mcat.location().x>=Game1[i].location().x+2+48&&Game1[i].location().y-48<Mcat.getLocation().y&&Game1[i].location().y+48>Mcat.getLocation().y) {
				Mcat.setBounds(Game1[i].location().x+48+2, Mcat.location().y, 50, 50);
				vector[0]=0;
			}
		}
		
		B = new Rectangle(Mcat.location().x,Mcat.location().y,Mcat.getWidth(),Mcat.getHeight());
		Rectangle A = new Rectangle(Game1[18].location().x+2,Game1[18].location().y,Game1[18].getWidth()-4,Game1[18].getHeight());
		if(B.intersects(A)&&g1[1]==1) {
			flag=5;
		}
		
		
		
		if(Mcat.location().x+vector[0]<=0) {
			vector[0]=0;
			Mcat.setBounds(0, Mcat.location().y, 50, 50);
		}
		
		if(land==true) {
			jump=false;
			jump1=false;
			vector[1]=0;
		}
		
		move();
	}

	//judge for g2
	public static void judgeg2() {
			if(Mcat.getLocation().y>700) {
				flag=8;
				life-=1;
				clearAll();
				return;
			}
			land =false;
			if(land ==false) {
				vector[1]+=3;
			}
			//check whether collide or not y axis
			Rectangle B = new Rectangle(Mcat.location().x,Mcat.location().y+vector[1],Mcat.getWidth(),Mcat.getHeight());
			for(int i=0;i<50;i++) {
				Rectangle A = new Rectangle(Game2[i].location().x+2,Game2[i].location().y,Game2[i].getWidth()-4,Game2[i].getHeight());
				if(B.intersects(A)&&Mcat.location().y<=Game2[i].location().y-49&&Game2[i].location().x+2-50<Mcat.location().x&&Game2[i].location().x+2+50>Mcat.location().x) {
					land=true;
					Mcat.setBounds(Mcat.location().x, Game2[i].location().y-49, 50, 50);
				}
				else if(B.intersects(A)&&Mcat.location().y>Game2[i].location().y+50&&Game2[i].location().x+2-50<Mcat.location().x&&Game2[i].location().x+2+50>Mcat.location().x) {
					Mcat.setBounds(Mcat.location().x, Game2[i].location().y+50, 50, 50);
					vector[1]=-vector[1];
					if(i==46 && g2[0]==0) {
						g2[0]=1;
						ImageIcon fcube = new ImageIcon("fcube.png");
					    Image newb = fcube.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
					    fcube = new ImageIcon(newb);
					    Game2[46] = new JLabel(fcube);
					    Game2[46].setBounds(50, 450, 50, 50);
					    repaintGame2();
					}
					if(i==47&&g2[1]==0) {
						g2[1]=1;
						ImageIcon fcube = new ImageIcon("fcube.png");
					    Image newb = fcube.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
					    fcube = new ImageIcon(newb);
					    Game2[47] = new JLabel(fcube);
					    Game2[47].setBounds(200, 450, 50, 50);
					    Game2[51].setText("Oops You're wrong");
					    Game2[51].setVisible(true);
					    repaintGame2();
					}
					if(i==48&&g2[2]==0) {
						g2[2]=1;
						ImageIcon fcube = new ImageIcon("fcube.png");
					    Image newb = fcube.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
					    fcube = new ImageIcon(newb);
					    Game2[48] = new JLabel(fcube);
					    Game2[48].setBounds(350, 450, 50, 50);
					    repaintGame2();
					}
					if(i==49) {
						ImageIcon hcube = new ImageIcon("hcube.png");
					    Image newb = hcube.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
					    hcube = new ImageIcon(newb);
						Game2[49] = new JLabel(hcube);
						Game2[49].setBounds(500,450,50,50);
						Mcat.setLocation(Mcat.location().x,475);
						repaintGame2();
						life-=1;
						flag=9;
					}
				}
			}
			//check whether collide or not x axis
			B = new Rectangle(Mcat.location().x+vector[0],Mcat.location().y,Mcat.getWidth(),Mcat.getHeight());
			for(int i=0;i<50;i++) {
				Rectangle A = new Rectangle(Game2[i].location().x+2,Game2[i].location().y,Game2[i].getWidth()-4,Game2[i].getHeight());
				if(B.intersects(A)&&Mcat.location().x<=Game2[i].location().x+2-50&&Game2[i].location().y-48<Mcat.getLocation().y&&Game2[i].location().y+48>Mcat.getLocation().y) {
					Mcat.setBounds(Game2[i].location().x-50+2, Mcat.location().y, 50, 50);
					vector[0]=0;
				}
				else if(B.intersects(A)&&Mcat.location().x>=Game2[i].location().x+2+48&&Game2[i].location().y-48<Mcat.getLocation().y&&Game2[i].location().y+48>Mcat.getLocation().y) {
					Mcat.setBounds(Game2[i].location().x+48+2, Mcat.location().y, 50, 50);
					vector[0]=0;
				}
			}
			
			if(g2[0]==1&&g2[1]==0&&g2[2]==1) {
				Game2[50].setVisible(true);
				Game2[51].setVisible(true);
				repaintGame2();
				g2[1]=2;
			}
			if(g2[1]==2) {
				Rectangle A = new Rectangle(Game2[50].location().x+2,Game2[50].location().y,Game2[50].getWidth()-4,Game2[50].getHeight());
				if(B.intersects(A)) {
					clearAll();
					flag=10;
				}
			}
			
			if(Mcat.location().x+vector[0]<=0) {
				vector[0]=0;
				Mcat.setBounds(0, Mcat.location().y, 50, 50);
			}
			
			if(land==true) {
				jump=false;
				jump1=false;
				vector[1]=0;
			}
			
			move();
		}
	
	//reload game1
	public static void repaintGame1() {
		screen.removeAll();
		if(g1[0]==1) {
			Game1[16].setVisible(true);
		}
		if(g1[1]==1) {
			Game1[15].setVisible(true);
			Game1[18].setVisible(true);
			Game1[17].setVisible(false);
		}
		ImageIcon background = new ImageIcon("blue.png");
		Image newb = background.getImage().getScaledInstance(900, 700, java.awt.Image.SCALE_SMOOTH);
		background = new ImageIcon(newb);
	    JLabel BlueBackground = new JLabel(background);
	    BlueBackground.setBounds(0, 0, 900, 700);
	    screen.add(Mcat);
	    for(int j=0;j<Game1.length;j++) {
	    	screen.add(Game1[j]);
	    }
	    screen.add(BlueBackground);
	    frame.repaint();
	}
	
	//reload game2
	public static void repaintGame2() {
		screen.removeAll();
		ImageIcon background = new ImageIcon("blue.png");
		Image newb = background.getImage().getScaledInstance(900, 700, java.awt.Image.SCALE_SMOOTH);
		background = new ImageIcon(newb);
	    JLabel BlueBackground = new JLabel(background);
	    BlueBackground.setBounds(0, 0, 900, 700);
	    screen.add(Mcat);
	    for(int j=0;j<Game2.length;j++) {
	    	screen.add(Game2[j]);
	    }
	    screen.add(BlueBackground);
	    frame.repaint();
	}
	

	//frame
	public static MainWindow getW() {
		return frame;
	}
	
	//receive direction
	public static void setF(int input) {
		flag = input;
	}
	
	public static int getF() {
		return flag;
	}
	
	//finished
	public static void finished() {
		
		ImageIcon background = new ImageIcon("black.png");
		Image newb = background.getImage().getScaledInstance(900, 700, java.awt.Image.SCALE_SMOOTH);
		background = new ImageIcon(newb);
	    JLabel BlackBackground = new JLabel(background);
	    
	    BlackBackground.setBounds(0, 0, 900, 700);
	    JLabel text = new JLabel("THANK YOU FOR PLAYING!!!");
	    text.setForeground(Color.white);
	    text.setFont(new java.awt.Font("Dialog",1,50));
		text.setBounds(100, 325, 1000, 50);
		
		frame.add(text);
		frame.add(BlackBackground);
	    frame.repaint();
	}
	
	//remove all object��
	public static void clearAll() {
		frame.getContentPane().removeAll();
		frame.getContentPane().repaint();
		vector[0]=0;vector[1]=0;
		game2_count=0;
		g2[0]=0;g2[1]=0;g2[2]=0;
		isBreak = 0;isCoin = 0;isMush = 0;isDie = 0;
		machine = false;ghost = false;transition=false;
		time =0 ;time1 =0;
		for(int i=0; i<40; i++)
			g4[i]=0;
		for(int i=0; i<g1.length; i++)
			g1[i]=0;
		door_flag=false;
	}
	
	
	public static void Game3() {
		isHit = new int[3];
		for(int i=0;i<3;i++)
			isHit[i] = 0;
		Game2 = new JLabel[45];
		//Game2_invisible = new JLabel[1];
		//int game2_count = 0;
		
		System.out.println("game3");
		screen = new JPanel();
		screen.setSize(900,700); 
		screen.setBounds(0, 0, 900, 700); 
		
		ImageIcon cat = new ImageIcon("cat_r.png");
		Image newI = cat.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		cat = new ImageIcon(newI);
		Mcat = new JLabel(cat);
		Mcat.setBounds(0, 550, 50, 50);
		
		
		ImageIcon background = new ImageIcon("blue.png");
		Image newb = background.getImage().getScaledInstance(900, 700, java.awt.Image.SCALE_SMOOTH);
		background = new ImageIcon(newb);
	    JLabel BlueBackground = new JLabel(background);
	    BlueBackground.setBounds(0, 0, 900, 700);
	    
	    ImageIcon Brick = new ImageIcon("brick.png");
	    newb = Brick.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    Brick = new ImageIcon(newb);
	    
	    ImageIcon fcube = new ImageIcon("fcube.png");
	    newb = fcube.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    fcube = new ImageIcon(newb);
	    
	    //JLabel[] floor = new JLabel[36];
	    int j = 0;
	    for(int i = 0 ;i < 36; ++i) {
	    	if(i != 12 && i != 30 && i != 6 && i != 24) {
		    	Game2[j] = new JLabel(Brick);
		    	Game2[j].setBounds((i%18)*50, 650-50*((i/18)), 50, 50);
		    	screen.add(Game2[j]);
		    	j++;
		    	game2_count++;
	    	}
	    }
	    ////index=25 brick will break
	    Addobstacle(39,game2_count++,Brick);
	    Addobstacle(57,game2_count++,Brick);
	    Addobstacle(43,game2_count++,Brick);
	    Addobstacle(61,game2_count++,Brick);
	    Addobstacle(50,game2_count++,Brick);
	    Addobstacle(68,game2_count++,Brick);//produce poison mushroom index == 37
	    Addobstacle(51,game2_count++,Brick); 
	    /*invisible cube*/
	    Addobstacle(76,game2_count++,fcube); //index 39
	    Game2[39].setVisible(false);
	    Addobstacle(77,game2_count++,fcube); //index 40
	    Game2[40].setVisible(false);
	    Addobstacle(78,game2_count++,fcube); //index 41
	    Game2[41].setVisible(false);
	    
	    //door index = 42
	    ImageIcon door = new ImageIcon("door.png");
	    newb = door.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    door = new ImageIcon(newb);
		Addobstacle(53,game2_count++,door); 
		//cube index = 43 44
		ImageIcon cube = new ImageIcon("cube.png");
	    newb = cube.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    cube = new ImageIcon(newb);
	    Addobstacle(82,game2_count++,cube); 
	    Addobstacle(73,game2_count++,cube); 
	    
	    screen.add(Mcat);
	    screen.add(BlueBackground);
	    frame.add(screen);
	    frame.repaint();
	}
	
	
	public static void Addobstacle(int x,int game2_count,ImageIcon Brick) {
		Game2[game2_count] = new JLabel(Brick);
	    Game2[game2_count].setBounds((x%18)*50, 650-50*((x/18)), 50, 50);
	    screen.add(Game2[game2_count]);
	}
	
	//judge
	public static void judgeg3() {
			ImageIcon Brick = new ImageIcon("fcube.png");
			Image a = Brick.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		    Brick = new ImageIcon(a);
		    ImageIcon coin = new ImageIcon("gold.png");
		    a = coin.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		    coin = new ImageIcon(a);
		    ImageIcon mush = new ImageIcon("mushroom.png");
		    a = mush.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		    mush = new ImageIcon(a);
			land =false;
			if(land ==false) {
				vector[1]+=3;
			}
			
			//check whether collide or not y axis
			Rectangle B = new Rectangle(Mcat.location().x,Mcat.location().y+vector[1],Mcat.getWidth(),Mcat.getHeight());
			
			for(int i=0;i<Game2.length;i++) {
				
				Rectangle A = new Rectangle(Game2[i].location().x+2,Game2[i].location().y,Game2[i].getWidth()-4,Game2[i].getHeight());
				 
				if(B.intersects(A)&&Mcat.location().y<=Game2[i].location().y-49&&Game2[i].location().x+2-50<Mcat.location().x&&Game2[i].location().x+2+50>Mcat.location().x) {
					if((i == 39 && isHit[0] == 0) || (i == 40 && isHit[1] == 0) || (i == 41 && isHit[2] == 0)) continue;
					else{
						if(i == 25 || i == 9) {
							isBreak = 1;
							Game2[25].setVisible(false);
							Game2[9].setVisible(false);
							repaintGame3();
						}
						else if(i == 42)
						{
							flag = 14;
							clearAll();
							loading();
						}
						else {
							if(i == 37) {
								System.out.println("mush");
								pmushroom2 = new JLabel(mush);
								pmushroom2.setBounds(750, 500, 50, 50);
								pmushroom2.setVisible(true);
								isMush = 1;
								repaintGame3();
							}
							land=true;
							Mcat.setBounds(Mcat.location().x, Game2[i].location().y-49, 50, 50);
						}
					}
					
				}
				else if(B.intersects(A)&&Mcat.location().y>Game2[i].location().y+50&&Game2[i].location().x+2-50<Mcat.location().x&&Game2[i].location().x+2+50>Mcat.location().x) {
					
					Mcat.setBounds(Mcat.location().x, Game2[i].location().y+50, 50, 50);
					vector[1]=-vector[1];
					if(i == 39) {
						isHit[0]=1;
						Game2[39].setVisible(true);
						repaintGame3();
					}
					if(i == 40) {
						isHit[1]=1;
						Game2[40].setVisible(true);
						repaintGame3();
					}
					if(i == 41) {
						isHit[2]=1;
						Game2[41].setVisible(true);
						repaintGame3();
					}
					if(i == 43) {
						//coin2 = new JLabel(coin);
						//coin2.setBounds(250, 100, 50, 50);
						//repaintGame2();
					}
					if(i == 44) {
						coin2 = new JLabel(coin);
						coin2.setBounds(50, 400, 50, 50);
						Addobstacle(73,44,Brick); 
						isCoin = 1;
						repaintGame3();
					}
				}
			}
			
			//check whether collide or not x axis
			B = new Rectangle(Mcat.location().x+vector[0],Mcat.location().y,Mcat.getWidth(),Mcat.getHeight());
			
			for(int i=0;i<Game2.length;i++) {
				
				Rectangle A = new Rectangle(Game2[i].location().x+2,Game2[i].location().y,Game2[i].getWidth()-4,Game2[i].getHeight());
				if(i == 39 && isHit[0] == 0 || (i == 40 && isHit[1] == 0) || (i == 41 && isHit[2] == 0)) continue;
				else {
					if(B.intersects(A)&&Mcat.location().x<=Game2[i].location().x+2-50&&Game2[i].location().y-48<Mcat.getLocation().y&&Game2[i].location().y+48>Mcat.getLocation().y) {
						if(i == 42)
						{
							flag = 13;
						}
						Mcat.setBounds(Game2[i].location().x-50+2, Mcat.location().y, 50, 50);
						vector[0]=0;
					}
					else if(B.intersects(A)&&Mcat.location().x>=Game2[i].location().x+2+50&&Game2[i].location().y-48<Mcat.getLocation().y&&Game2[i].location().y+48>Mcat.getLocation().y) {
						if(i == 42)
						{
							flag = 13;
						}
						Mcat.setBounds(Game2[i].location().x+50+2, Mcat.location().y, 50, 50);
						vector[0]=0;
					}
				}
			}
			if(isCoin == 1) {
				//System.out.println("in");
				Rectangle C = new Rectangle(coin2.getLocation().x,coin2.getLocation().y,coin2.getWidth(),coin2.getHeight());
				if(B.intersects(C)&&Mcat.location().x<=coin2.location().x+2-50&&coin2.location().y-48<Mcat.getLocation().y&&coin2.location().y+48>Mcat.getLocation().y) {
					System.out.println("cccc");
					coin2.setVisible(false);
					repaintGame3();
				}
				else if(B.intersects(C)&&Mcat.location().x>=coin2.location().x+2+50&&coin2.location().y-48<Mcat.getLocation().y&&coin2.location().y+48>Mcat.getLocation().y) {
					System.out.println("cccc");
					coin2.setVisible(false);
					repaintGame3();
				}
			}
			if(isMush == 1) {
				System.out.println("in");
				Rectangle C = new Rectangle(pmushroom2.getLocation().x,pmushroom2.getLocation().y,pmushroom2.getWidth(),pmushroom2.getHeight());
				if(B.intersects(C)&&Mcat.location().x<=pmushroom2.location().x+2-50&&pmushroom2.location().y-48<Mcat.getLocation().y&&pmushroom2.location().y+48>Mcat.getLocation().y) {
					///System.out.println("dddd");
					pmushroom2.setVisible(false);
					repaintGame3();
					isDie = 1;
				}
				else if(B.intersects(C)&&Mcat.location().x>=pmushroom2.location().x+2+50&&pmushroom2.location().y-48<Mcat.getLocation().y&&pmushroom2.location().y+48>Mcat.getLocation().y) {
					///System.out.println("dddd");
					pmushroom2.setVisible(false);
					repaintGame3();
					isDie = 1;
				}
				B = new Rectangle(Mcat.location().x,Mcat.location().y+vector[1],Mcat.getWidth(),Mcat.getHeight());
				if(B.intersects(C)&&Mcat.location().y<=pmushroom2.location().y-49&&pmushroom2.location().x+2-50<Mcat.location().x&&pmushroom2.location().x+2+50>Mcat.location().x) {
					///System.out.println("dddd");
					pmushroom2.setVisible(false);
					repaintGame3();
					isDie = 1;
				}
			}
			if(Mcat.location().x+vector[0]<=0) {
				vector[0]=0;
				Mcat.setBounds(0, Mcat.location().y, 50, 50);
			}
			if(Mcat.getLocation().y>=650) {
				isDie = 1;
				
			}
			if(land==true) {
				jump=false;
				jump1=false;
				vector[1]=0;
			}
			move();
			if(isDie == 1) {
				System.out.println("die!!!!");
				flag = 13;
			}
				
		}
	
	public static void repaintGame3() {
			screen.removeAll();
			ImageIcon background = new ImageIcon("blue.png");
			Image newb = background.getImage().getScaledInstance(900, 700, java.awt.Image.SCALE_SMOOTH);
			background = new ImageIcon(newb);
		    JLabel BlueBackground = new JLabel(background);
		    BlueBackground.setBounds(0, 0, 900, 700);
		    screen.add(Mcat);
		    for(int j=0;j<Game2.length;j++) {
		    	screen.add(Game2[j]);
		    }
		    if(isCoin == 1) {
		    	screen.add(coin2);
		    }
		    if(isMush == 1) {
		    	screen.add(pmushroom2);
		    }
		    screen.add(BlueBackground);
		    frame.repaint();
		}
		
	//game4
	public static void Game4() {
		System.out.println("game4");
		Game1 = new JLabel[40];
		screen = new JPanel();
		screen.setSize(900,700); 
		screen.setBounds(0, 0, 900, 700); 
		
		ImageIcon cat = new ImageIcon("cat_r.png");
		Image newI = cat.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		cat = new ImageIcon(newI);
		Mcat = new JLabel(cat);
		Mcat.setBounds(0, 550, 50, 50);
		
		
		
		ImageIcon background = new ImageIcon("blue.png");
		Image newb = background.getImage().getScaledInstance(900, 700, java.awt.Image.SCALE_SMOOTH);
		background = new ImageIcon(newb);
	    JLabel BlueBackground = new JLabel(background);
	    BlueBackground.setBounds(0, 0, 900, 700);
	    
	    ImageIcon Brick = new ImageIcon("brick.png");
	    newb = Brick.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    Brick = new ImageIcon(newb);
	    
	    for(int i = 0 ;i < 8; ++i) {
	    	Game1[i] = new JLabel(Brick);
	    	Game1[i].setBounds((i%4)*50, 650-50*((i/4)), 50, 50);
	    }
	    for(int i = 19 ;i < 40; ++i) {
	    	Game1[i] = new JLabel(Brick);
	    }
	    
	    for(int i = 8 ;i < 14; ++i) {
	    	Game1[i] = new JLabel(Brick);
	    	Game1[i].setBounds(((i-8)%3)*50+750, 650-50*(((i-8)/3)), 50, 50);
	    }
	    
	    ImageIcon cube = new ImageIcon("cube.png");
	    newb = cube.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    cube = new ImageIcon(newb);
	    Game1[14]=new JLabel(cube);
	    Game1[14].setBounds(50, 450, 50, 50);
	    
	    ImageIcon fcube = new ImageIcon("fcube.png");
	    newb = fcube.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    fcube = new ImageIcon(newb);
	    Game1[15]=new JLabel(fcube);
	    Game1[15].setBounds(0, 300, 50, 50);
	    Game1[15].setVisible(false);
	    
	    ImageIcon ghost = new ImageIcon("badcloud.png");
	    newb = ghost.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    ghost = new ImageIcon(newb);
	    Game1[27] = new JLabel(ghost);
	    Game1[34] = new JLabel(ghost);
	    
	    ImageIcon pipe = new ImageIcon("tube.png");
	    newb = pipe.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
	    pipe = new ImageIcon(newb);
	    Game1[35] = new JLabel(pipe);
	    Game1[35].setBounds(750, 260, 100, 100);
	    
	    ImageIcon door = new ImageIcon("door.png");
	    newb = door.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    door = new ImageIcon(newb);
	    Game1[16] = new JLabel(door);	    
	    Game1[17] = new JLabel(door);
	    Game1[17].setBounds(850, 550, 50, 50);
	    Game1[18] = new JLabel(door);
	    Game1[18].setBounds(0, 250, 50, 50);
	    Game1[18].setVisible(false);

		Game1[19].setBounds(250, 460, 50, 50);
		Game1[20].setBounds(250, 410, 50, 50);
		Game1[19].setVisible(false);
		Game1[20].setVisible(false);
		
		Game1[21].setBounds(300, 600, 50, 50);
		Game1[22].setBounds(500, 600, 50, 50);
		
		Game1[23].setBounds(400, 480, 50, 50);
		Game1[24].setBounds(350, 360, 50, 50);
		Game1[25].setBounds(500, 360, 50, 50);
		Game1[26].setBounds(550, 360, 50, 50);
		Game1[28].setBounds(600, 360, 50, 50);
		Game1[29].setBounds(650, 360, 50, 50);
		Game1[30].setBounds(700, 360, 50, 50);
		Game1[31].setBounds(750, 360, 50, 50);
		Game1[32].setBounds(800, 360, 50, 50);
		Game1[33].setBounds(850, 360, 50, 50);
		
		
		
//		Game1[16] = new JLabel("PRESS G TO LAUNCH");
//		Game1[16].setFont(new java.awt.Font("Dialog",1,50));
//		Game1[16].setBounds(100, 50, 1000, 50);
//		Game1[16].setVisible(true);
		
	    screen.add(Mcat);
	    for(int i=0;i<Game1.length;i++) {
	    	screen.add(Game1[i]);
	    }
	    screen.add(BlueBackground);
	    frame.add(screen);
	    frame.repaint();
	}
	
	
	public static void moveg4() {
		Mcat.setBounds(Mcat.location().x+vector[0], Mcat.location().y+vector[1], 50, 50);
		
		if(machine) {
			if(Game1[21].location().x == 450) {
				machine = false;
			}
			else {
				Mcat.setBounds(Mcat.location().x+10, Mcat.location().y, 50, 50);
				Game1[21].setBounds(Game1[21].location().x+10, Game1[21].location().y, 50, 50);
			}
		}
		if(ghost) {
			
			Game1[27].setBounds(Game1[27].location().x, Game1[27].location().y+10, 50, 50);
			Game1[34].setBounds(Game1[34].location().x, Game1[34].location().y+10, 50, 50);
			
			if(Game1[27].location().y >700 && Game1[34].location().y >700)ghost=false;
			
		}
		if(transition) {
			try {
				TimeUnit.SECONDS.sleep(1);
				Mcat.setBounds(750, 500, 50, 50);
				transition = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//judge
	public static void judgeg4() {
			if(Mcat.getLocation().y>700) {
				flag=16;
				life-=1;
				clearAll();
				return;
			}
			land =false;
			if(land ==false) {
				vector[1]+=3;
			}
			Rectangle B = new Rectangle(Mcat.location().x,Mcat.location().y,Mcat.getWidth(),Mcat.getHeight());
			Rectangle C = new Rectangle(Game1[17].location().x,Game1[17].location().y,Game1[17].getWidth(),Game1[17].getHeight());
			Rectangle D = new Rectangle(Game1[18].location().x,Game1[18].location().y,Game1[18].getWidth(),Game1[18].getHeight());
			
			if(B.intersects(C)) {
				flag=17;
				clearAll();
				loading();
				return;
			}
			if(B.intersects(D)) {
				flag =16;
				return;
			}
			
			//check whether collide or not y axis
			B = new Rectangle(Mcat.location().x,Mcat.location().y+vector[1],Mcat.getWidth(),Mcat.getHeight());
			for(int i=0;i<40;i++) {
				Rectangle A = new Rectangle(Game1[i].location().x+2,Game1[i].location().y,Game1[i].getWidth()-4,Game1[i].getHeight());
				if(B.intersects(A)&&Mcat.location().y<=Game1[i].location().y-49&&Game1[i].location().x+2-50<Mcat.location().x&&Game1[i].location().x+2+50>Mcat.location().x) {
					land=true;
					Mcat.setBounds(Mcat.location().x, Game1[i].location().y-49, 50, 50);
					if(i==21&&g4[21]==0) {
						machine = true;
						g4[21]=1;
					}
					if(i==25&&g4[25]==0) {
						Game1[27].setBounds(550, 50, 50, 50);
						Game1[34].setBounds(500, 50, 50, 50);
						
						ghost=true;
						g4[25]=1;
					}
					if(i==27 && g4[27] == 0) {
						System.out.print(1);
						flag=16;
						life-=1;
						g4[27]=1;
						ghost = false;
						clearAll();
						return;
					}
					if(i==34 && g4[34] == 0) {
						System.out.print(1);
						flag=16;
						life-=1;
						g4[34]=1;
						ghost = false;
						clearAll();
						return;
					}
					if(i==35 && g4[35] == 0) {
						transition=true;
						g4[35]=1;
					}
					
				}
				else if(B.intersects(A)&&Mcat.location().y>Game1[i].location().y+50&&Game1[i].location().x+2-50<Mcat.location().x&&Game1[i].location().x+2+50>Mcat.location().x) {
					Mcat.setBounds(Mcat.location().x, Game1[i].location().y+50, 50, 50);
					vector[1]=-vector[1];
					if(i==14&&g4[0]==0) {
						ImageIcon fcube = new ImageIcon("fcube.png");
					    Image newb = fcube.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
					    fcube = new ImageIcon(newb);
						Game1[14] = new JLabel(fcube);
						Game1[14].setBounds(50, 450, 50, 50);
						
						Game1[0].setBounds(Game1[0].getLocation().x, 800, 50, 50);
						Game1[1].setBounds(Game1[1].getLocation().x, 800, 50, 50);
						Game1[2].setBounds(Game1[2].getLocation().x, 800, 50, 50);
						Game1[4].setBounds(Game1[4].getLocation().x, 800, 50, 50);
						Game1[5].setBounds(Game1[5].getLocation().x, 800, 50, 50);
						Game1[6].setBounds(Game1[6].getLocation().x, 800, 50, 50);
						
						repaintGame4();
						g4[0]=1;
							
					}
					if(i==15&&g4[1]==0) {
						Game1[15].setVisible(true);
						Game1[18].setVisible(true);
						repaintGame4();
						g4[1]=1;
					}
					if(i==27 && g4[27] == 0) {
						flag=16;
						life-=1;
						g4[27]=1;
						ghost = false;
						
						clearAll();
						return;
					}
					if(i==34 && g4[34] == 0) {
						flag=16;
						life-=1;
						g4[34]=1;
						ghost = false;
						clearAll();
						return;
					}
					
					
				}
			}
			//check whether collide or not x axis
			B = new Rectangle(Mcat.location().x+vector[0],Mcat.location().y,Mcat.getWidth(),Mcat.getHeight());
			for(int i=0;i<40;i++) {
				Rectangle A = new Rectangle(Game1[i].location().x+2,Game1[i].location().y,Game1[i].getWidth()-4,Game1[i].getHeight());
				if(B.intersects(A)&&Mcat.location().x<=Game1[i].location().x+2-50&&Game1[i].location().y-48<Mcat.getLocation().y&&Game1[i].location().y+48>Mcat.getLocation().y) {
					Mcat.setBounds(Game1[i].location().x-50+2, Mcat.location().y, 50, 50);
					vector[0]=0;
					if(i==19&&g4[19]==0 || i==20&&g4[20]==0) {
						Game1[19].setVisible(true);
						Game1[20].setVisible(true);
						repaintGame4();
						g4[19]=1;
						g4[20]=1;
					}
					
				}
				else if(B.intersects(A)&&Mcat.location().x>=Game1[i].location().x+2+48&&Game1[i].location().y-48<Mcat.getLocation().y&&Game1[i].location().y+48>Mcat.getLocation().y) {
					Mcat.setBounds(Game1[i].location().x+48+2, Mcat.location().y, 50, 50);
					vector[0]=0;
					
				}
			}
			
			
			
			
			if(Mcat.location().x+vector[0]<=0) {
				vector[0]=0;
				Mcat.setBounds(0, Mcat.location().y, 50, 50);
			}
			
			if(land==true) {
				jump=false;
				jump1=false;
				vector[1]=0;
			}
			
			moveg4();
		}
	
	public static void repaintGame4() {
		screen.removeAll();
		ImageIcon background = new ImageIcon("blue.png");
		Image newb = background.getImage().getScaledInstance(900, 700, java.awt.Image.SCALE_SMOOTH);
		background = new ImageIcon(newb);
	    JLabel BlueBackground = new JLabel(background);
	    BlueBackground.setBounds(0, 0, 900, 700);
	    screen.add(Mcat);
	    for(int j=0;j<Game1.length;j++) {
	    	screen.add(Game1[j]);
	    }
	    screen.add(BlueBackground);
	    frame.repaint();
	}
	
	public static void Game5() {
		System.out.println("Game5");
		g1 = new int[12];
		Game1= new JLabel[58];
		screen = new JPanel();
		screen.setSize(900,700); 
		screen.setBounds(0, 0, 900, 700); 
		
		ImageIcon cat = new ImageIcon("cat_r.png");
		Image newI = cat.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		cat = new ImageIcon(newI);
		Mcat = new JLabel(cat);
		Mcat.setBounds(0, 550, 50, 50);
		
		
		ImageIcon background = new ImageIcon("blue.png");
		Image newb = background.getImage().getScaledInstance(900, 700, java.awt.Image.SCALE_SMOOTH);
		background = new ImageIcon(newb);
	    JLabel BlueBackground = new JLabel(background);
	    BlueBackground.setBounds(0, 0, 900, 700);
	        
	    background = new ImageIcon("tube.png");
	    newb = background.getImage().getScaledInstance(210, 130, java.awt.Image.SCALE_SMOOTH);
	    background = new ImageIcon(newb);
	    JLabel tube = new JLabel(background);
	    tube.setBounds(145, 485, 210, 130);
	    screen.add(tube);
	    //floor
	    ImageIcon Brick = new ImageIcon("brick.png");
	    newb = Brick.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    Brick = new ImageIcon(newb);
	    
	    for(int i = 0 ;i < 20; ++i) {
	    	Game1[i] = new JLabel(Brick);
	    	Game1[i].setBounds((i%10)*50, 650-50*((i/10)), 50, 50);
	    }
	    
	    for(int i = 20 ;i < 28; ++i) {
	    	Game1[i] = new JLabel(Brick);
	    	Game1[i].setBounds((i%4)*50+600, 900-50*((i/4)), 50, 50);

	    }
	    for(int i = 28 ;i < 37; ++i) {
	    	Game1[i] = new JLabel(Brick);
	    	Game1[i].setBounds((i%14)*50+800, 500-50*((i/14)), 50, 50);
	    }
	    Game1[38] = new JLabel(Brick);
	    Game1[38].setBounds(200,550,50,50);
	    Game1[39] = new JLabel(Brick);
	    Game1[39].setBounds(200,500,50,50);
	    Game1[40] = new JLabel(Brick);
	    Game1[40].setBounds(250,550,50,50);
	    Game1[41] = new JLabel(Brick);
	    Game1[41].setBounds(250,500,50,50);
	    for(int i = 49 ; i<52 ; ++i) {
	    	Game1[i] = new JLabel(Brick);
	    	Game1[i].setBounds((i%7)*50 + 250,250-((i%7)*50),50,50);
	    	Game1[i].setVisible(false);
	    }
	    
	    ImageIcon no = new ImageIcon("no.png");
	    newb = no.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    no = new ImageIcon(newb);
	    for(int i = 42 ; i<47 ; ++i) {
	    	Game1[i] = new JLabel(no);
	    	Game1[i].setBounds((i%6)*50 + 300,450,50,50);
	    	Game1[i].setVisible(false);
	    }
	    Game1[54] = new JLabel(no);
	    Game1[54].setBounds(600,450,50,50);
	    Game1[54].setVisible(false);
	    Game1[55] = new JLabel(no);
	    Game1[55].setBounds(500,150,50,50);
		JLabel no_brick = new JLabel(no);
		no_brick.setBounds(400,350,50,50);
		screen.add(no_brick);
		no_brick = new JLabel(no);
		no_brick.setBounds(350,350,50,50);
		screen.add(no_brick);	
		
		ImageIcon  cube = new ImageIcon("cube.png");
	    newb = cube.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    cube = new ImageIcon(newb);
		Game1[47] = new JLabel(cube);
		Game1[47].setBounds(50, 350, 50, 50);
		Game1[47].setVisible(false);
		Game1[53] = new JLabel(cube);
		Game1[53].setBounds(650, 450, 50, 50);
		Game1[53].setVisible(false);
		
		ImageIcon  gold = new ImageIcon("gold.png");
	    newb = gold.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    gold = new ImageIcon(newb);
		Game1[56] = new JLabel(gold);
		Game1[56].setBounds(600, 400, 50, 50);
		Game1[56].setVisible(false);
		
		ImageIcon  door1 = new ImageIcon("door.png");
	    newb = door1.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    door1 = new ImageIcon(newb);
		Game1[57] = new JLabel(door1);
		Game1[57].setBounds(600, 400, 50, 50);
		Game1[57].setVisible(false);
		
		
		ImageIcon  mush = new ImageIcon("poisoned_mushroom.png");
	    newb = mush.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    mush = new ImageIcon(newb);
		Game1[48] = new JLabel(mush);
		Game1[48].setBounds(50, 300, 50, 50);
		Game1[48].setVisible(false);
		
		mush = new ImageIcon("mushroom.png");
	    newb = mush.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    mush = new ImageIcon(newb);
		Game1[52] = new JLabel(mush);
		Game1[52].setBounds(50, 300, 50, 50);
		Game1[52].setVisible(false);
		
		ImageIcon  door = new ImageIcon("door.png");
	    newb = door.getImage().getScaledInstance(100, 200, java.awt.Image.SCALE_SMOOTH);
	    door = new ImageIcon(newb);
		JLabel d = new JLabel(door);
		d.setBounds(790,202,100, 200);
		screen.add(d);
		
//	    
	    ImageIcon c = new ImageIcon("mountain.png");
	    newb = c.getImage().getScaledInstance(120, 130, java.awt.Image.SCALE_SMOOTH);
	    c = new ImageIcon(newb);
	    JLabel grass = new JLabel(c);
	    grass.setBounds(50,470,120, 130);
	    screen.add(grass);
	    
	    c = new ImageIcon("cube.png");
	    newb = c.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    c = new ImageIcon(newb);
	    Game1[37] = new JLabel(c);
	    Game1[37].setBounds(0,650,50,50);
	   
//	    
//	    
	    //Cloud
	    ImageIcon m = new ImageIcon("cloud.png");
	    newb = m.getImage().getScaledInstance(200, 170, java.awt.Image.SCALE_SMOOTH);
	    m = new ImageIcon(newb);
	    JLabel cloud = new JLabel(m);
	    cloud.setBounds(425,100,200, 170);
	    screen.add(cloud);

	    //ADD
	    screen.add(Mcat);
	    for(int i=0;i<Game1.length;i++) {
	    	screen.add(Game1[i]);
	    }
	    
	    screen.add(BlueBackground);
	    frame.add(screen);
	    frame.repaint();
	}
	
	//judge
	public static void judgeg5() {
		if(Mcat.getLocation().y>700) {
			if(door_flag == true) {
				flag=20;
				clearAll();
				return;	
			}
			else {
				flag=19;
				life-=1;
				clearAll();
				return;
			}
			
		}
		land =false;
		if(land ==false) {
			vector[1]+=3;
		}
		//check whether collide or not y axis
		Rectangle B = new Rectangle(Mcat.location().x,Mcat.location().y+vector[1],Mcat.getWidth(),Mcat.getHeight());
		for(int i=0;i<Game1.length;i++) {
			Rectangle A = new Rectangle(Game1[i].location().x+2,Game1[i].location().y,Game1[i].getWidth()-4,Game1[i].getHeight());
			// Up collision
			if(B.intersects(A)&&Mcat.location().y<=Game1[i].location().y-49&&Game1[i].location().x+2-50<Mcat.location().x&&Game1[i].location().x+2+50>Mcat.location().x) {
				if(i==42 || i ==43 || i == 44 || i==45 || i == 46 && g1[0]==0) {
					land = false;
					break;
				}
				if(i == 56) {
					if(g1[10] == 1 && g1[11] == 0) {
						Game1[56].setBounds(0,650,50,50);
						Game1[56].setVisible(false);
						repaintGame5();
						flag=19;
						life-=1;
						clearAll();
						return;
					}
					else {
						land = false;
						break;
					}
				}
				if(i == 57) {
					if(g1[10] == 1 && g1[11] == 1) {
						Game1[57].setBounds(0,700,50,50);
						Game1[57].setVisible(false);
						repaintGame5();
						door_flag = true;
						land=true;
						Mcat.setBounds(Mcat.location().x, Game1[i].location().y-49, 50, 50);
						
					}
					else {
						land = false;
						break;
					}
				}
				if(i == 53) {
					if(g1[8] ==0) {
						land = false;
						break;
					}
					else {
						land=true;
						Mcat.setBounds(Mcat.location().x, Game1[i].location().y-49, 50, 50);
					}
					
				}
				if(i == 48 && g1[6] == 1) {
					flag=19;
					life-=1;
					clearAll();
					return;
				}
				else if(i==47 || i == 52) {
					 if(g1[5] == 1 && g1[7] == 1) {
						land=true;
						Mcat.setBounds(Mcat.location().x, Game1[i].location().y-49, 50, 50);
						
						Game1[49].setVisible(true);
						Game1[50].setVisible(true);
						Game1[51].setVisible(true);
						Game1[52].setBounds(0,650,50,50);
						Game1[52].setVisible(false);
						repaintGame5();
					}
					else {
						land = false;
						break;
					}
				}
				if(i == 50) {
					flag=19;
					life-=1;
					clearAll();
					return;
				}
				else if(i == 37) {
					land = false;
					return;
				}
				else {
					land=true;
					Mcat.setBounds(Mcat.location().x, Game1[i].location().y-49, 50, 50);
				}
				
			}
			// Down collision
			else if(B.intersects(A)&&Mcat.location().y>Game1[i].location().y+50&&Game1[i].location().x+2-50<Mcat.location().x&&Game1[i].location().x+2+50>Mcat.location().x) {
				Mcat.setBounds(Mcat.location().x, Game1[i].location().y+50, 50, 50);
				vector[1]=-vector[1];
				if(i == 54) {
					if(g1[9] == 0 && g1[10] == 0 && g1[11] == 0 ) {
						g1[9] = 1;
						Game1[54].setVisible(true);
						repaintGame5();
					}
					else if(g1[9] == 1 && g1[10] == 0 && g1[11] == 0) {
						g1[10] = 1;
						g1[11] = 0;
						Game1[56].setVisible(true);
						repaintGame5();
					}
					else if(g1[9] == 1 && g1[10] == 1 && g1[11] == 0) {
						g1[10] = 1;
						g1[11] = 1;
						Game1[56].setBounds(0,650,50,50);
						Game1[56].setVisible(false);
						Game1[57].setVisible(true);
						time1 += 1;
						if(time1 < 2)
							repaintGame5();
					}
				}
				if(i==53) {
					if(g1[8] == 0) {
						Game1[53].setVisible(true);
						repaintGame5();
						g1[8]=1;
					}
					else {
						flag = 19;
						life -=1;
						clearAll();
						return;
					}
				}
				if(i==42 && g1[0]==0) {
					Game1[42].setVisible(true);
					repaintGame5();
					g1[0]=1;
				}
				else if(i==43 && g1[1]==0) {
					Game1[43].setVisible(true);
					repaintGame5();
					g1[1]=1;
				}
				else if(i==44 && g1[2]==0) {
					Game1[44].setVisible(true);
					repaintGame5();
					g1[2]=1;
				}
				else if(i==45 && g1[3]==0) {
					Game1[45].setVisible(true);
					repaintGame5();
					g1[3]=1;
				}
				else if(i==46 && g1[4]==0) {
					Game1[46].setVisible(true);
					repaintGame5();
					g1[4]=1;
				}
				else if(i==47) {
					if(g1[6] == 1 && g1[5]==1 && g1[7] == 0) {
						Game1[48].setVisible(true);
						g1[7]=1;
						repaintGame5();
					}
					else if(g1[6] == 0 && g1[5]==0 && g1[7] == 0){
						Game1[47].setVisible(true);
						g1[5]=1;
						g1[6]=1;
						repaintGame5();
					}
					else if(g1[6] == 1 && g1[5]==1 && g1[7] == 1 ) {
						Game1[48].setBounds(0,650,50,50);
						Game1[48].setVisible(false);
						Game1[52].setVisible(true);
						time += 1;
						if(time < 2)
							repaintGame5();
					}
				}
			}

		}
		//check whether collide or not x axis
		B = new Rectangle(Mcat.location().x+vector[0],Mcat.location().y,Mcat.getWidth(),Mcat.getHeight());
		for(int i=0;i<Game1.length;i++) {
			Rectangle A = new Rectangle(Game1[i].location().x+2,Game1[i].location().y,Game1[i].getWidth()-4,Game1[i].getHeight());
			if(B.intersects(A)&&Mcat.location().x<=Game1[i].location().x+2-50&&Game1[i].location().y-48<Mcat.getLocation().y&&Game1[i].location().y+48>Mcat.getLocation().y) {
				if(i==42 || i ==43 || i == 44 || i==45 || i == 46 && g1[0]==0) {
					return;
				}
				 if(i == 47 && g1[5] == 0) {
					if(g1[5] == 1) {
						Mcat.setBounds(Game1[i].location().x-50+2, Mcat.location().y, 50, 50);
						vector[0]=0;
					}
					else {
						break;
					}
				}
				else if(i == 37) {
					return;
				}
				else {
					Mcat.setBounds(Game1[i].location().x-50+2, Mcat.location().y, 50, 50);
					vector[0]=0;
				}
			}
			//Right collision
			else if(B.intersects(A)&&Mcat.location().x>=Game1[i].location().x+2+48&&Game1[i].location().y-48<Mcat.getLocation().y&&Game1[i].location().y+48>Mcat.getLocation().y) {
				
				if(i == 37) {
					return;
				}
				if(i == 53) {
					if(g1[8] == 0) {
						return;
					}
					else {
						Mcat.setBounds(Game1[i].location().x+48+2, Mcat.location().y, 50, 50);
						vector[0]=0;
					}
				}
				if(i==56) {
					if(g1[10] == 1) {
						Game1[56].setBounds(0,650,50,50);
						Game1[56].setVisible(false);
						flag=19;
						life-=1;
						clearAll();
						return;
					}
					else {
						return;
					}
				}
				if(i==57) {
					if(g1[11] == 1) {
						Game1[57].setBounds(0,650,50,50);
						Game1[57].setVisible(false);
						repaintGame5();
						door_flag = true;
						break;
					}
					else {
						return;
					}
				}
				if(i==48) {
					if(g1[6] == 1) {
						Game1[48].setBounds(0,650,50,50);
						Game1[48].setVisible(false);
						flag=19;
						life-=1;
						clearAll();
						return;
					}
					else {
						return;
					}
				}
				if(i==52) {
					if(g1[7] == 1) {
						Game1[49].setVisible(true);
						Game1[50].setVisible(true);
						Game1[51].setVisible(true);
						Game1[52].setBounds(0,650,50,50);
						//Game1[52].setVisible(false);
						repaintGame5();
						break;
					}
					else {
						return;
					}
				}
				else {
					Mcat.setBounds(Game1[i].location().x+48+2, Mcat.location().y, 50, 50);
					vector[0]=0;
				}
			}
		}
		
		
		if(Mcat.location().x+vector[0]<=0) {
			vector[0]=0;
			Mcat.setBounds(0, Mcat.location().y, 50, 50);
		}
		if(Mcat.location().x+vector[0]>=850) {
			vector[0]=0;
			Mcat.setBounds(Mcat.location().x, Mcat.location().y, 50, 50);
		}
		if(land==true) {
			jump=false;
			jump1=false;
			vector[1]=0;
		}
		
		move();
	}
	
	public static void repaintGame5() {
		screen.removeAll();
		ImageIcon background = new ImageIcon("blue.png");
		Image newb = background.getImage().getScaledInstance(900, 700, java.awt.Image.SCALE_SMOOTH);
		background = new ImageIcon(newb);
	    JLabel BlueBackground = new JLabel(background);
	    BlueBackground.setBounds(0, 0, 900, 700);
	    screen.add(Mcat);
	    
	    ImageIcon c = new ImageIcon("mountain.png");
	    newb = c.getImage().getScaledInstance(120, 130, java.awt.Image.SCALE_SMOOTH);
	    c = new ImageIcon(newb);
	    JLabel grass = new JLabel(c);
	    grass.setBounds(50,470,120, 130);
	    screen.add(grass);
	    
	    background = new ImageIcon("tube.png");
	    newb = background.getImage().getScaledInstance(210, 130, java.awt.Image.SCALE_SMOOTH);
	    background = new ImageIcon(newb);
	    JLabel tube = new JLabel(background);
	    tube.setBounds(145, 485, 210, 130);
	    screen.add(tube);
	    
	    ImageIcon no = new ImageIcon("no.png");
	    newb = no.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	    no = new ImageIcon(newb);
	    JLabel no_brick = new JLabel(no);
		no_brick.setBounds(400,350,50,50);
		screen.add(no_brick);
		no_brick = new JLabel(no);
		no_brick.setBounds(350,350,50,50);
		screen.add(no_brick);
		
		ImageIcon  door = new ImageIcon("door.png");
	    newb = door.getImage().getScaledInstance(100, 200, java.awt.Image.SCALE_SMOOTH);
	    door = new ImageIcon(newb);
		JLabel d = new JLabel(door);
		d.setBounds(790,202,100, 200);
		screen.add(d);
	    ImageIcon m = new ImageIcon("cloud.png");
	    newb = m.getImage().getScaledInstance(200, 170, java.awt.Image.SCALE_SMOOTH);
	    m = new ImageIcon(newb);
	    JLabel cloud = new JLabel(m);
	    cloud.setBounds(425,100,200, 170);
	    screen.add(cloud);
	    for(int j=0;j<Game1.length;j++) {
	    	screen.add(Game1[j]);
	    }
	    screen.add(BlueBackground);
	    frame.repaint();
	}

}
