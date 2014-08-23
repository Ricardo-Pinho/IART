package GUI;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;

import Quadtria.AI;
import Quadtria.Board;
import Quadtria.Game;

import java.io.Serializable;
import java.util.Vector;

public class Interface extends Applet implements KeyListener, MouseListener, ItemListener, Serializable{

		//variáveis locais graficas
		private static final long serialVersionUID = 1L;
		private BackgroundPanel[] label;
		private JLabel labeltext[];
		private JDialog option1dialog, option2dialog,option3dialog,menudialog,endgamedialog;
		private JFrame frame; 
		private JPanel tools,mainmenupanel,msgpanel,plr1panel,plr2panel,optpanel,helppanel,helpbar;
		private BackgroundPanel  board;
		private int labelcolor[]= new int[22];
		private JButton ok,yes,no;
		private JTextField player1name,player2name;
		private JLabel title,subtitle,mainmenuoption1,mainmenuoption2,mainmenuoption3,message,helpmsg,togglehelp,plr1name,plr2name,options,plr1lastmove,plr1moveno,plr1score,plr2lastmove,plr2moveno,plr2score;
		private JCheckBox Facil, Medio,Dificil;
		private JCheckBox AINormal, AICortes,AIKiller;
		private Container c;
		javax.swing.Timer timercpu;
		private int mili_sec=100;
		
		//variáveis locais funcionais
		private static boolean fast=false, wrongMove=false, gameBegin=false, gameEnd=false,gameHelp=true, AImoving=false;
		private Game game;
		private int gameoption=0,dificulty=5,aioption=3;
		private int gCon = 0;
		private int pNo = 0;
		private int prevNo = -1;
		private int boxNo = -1;
		private AI a=null;
		private Integer[] aiMove;
		private String name1,name2;
		
		/**Função que corre o jogo
		 * @throws IOException */
		public static void main(String[] args) throws IOException {
			Interface f=new Interface();
			f.InitiateWindow();
			f.frame.pack();
			f.frame.setVisible(true);
		}
		
		
		/**Construtor da GUI
		 * @throws IOException */
		public Interface()
		{
			frame = new JFrame("Quadtria");
			frame.addKeyListener(this);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
			frame.setPreferredSize(new Dimension(700,700));	
			c = frame.getContentPane();
		}
		
		public void InitiateWindow() throws IOException{
			c.removeAll();
			c.setLayout(new BoxLayout(c,BoxLayout.X_AXIS));
			final Image image = ImageIO.read(new File("Quadtria/menu.jpg"));
			Image grayImage = GrayFilter.createDisabledImage(image);
		    BackgroundPanel backPane = new BackgroundPanel(grayImage);
		    backPane.setLayout(new BoxLayout(backPane,BoxLayout.Y_AXIS));
			tools = new JPanel();
		    GridLayout flow = new GridLayout(3,1);
		    flow.setVgap(35);
		    GridLayout flow2 = new GridLayout(2,1);
		    flow.setVgap(35);
			tools.setAlignmentY(CENTER_ALIGNMENT);
			JPanel titlepanel = new JPanel();
			titlepanel.setLayout(flow2);
			title = new JLabel("Quadtria",SwingConstants.CENTER);
		    title.setFont(new Font("Old English Text MT", Font.BOLD, 80));
		    subtitle = new JLabel("Escolha um modo de jogo:",SwingConstants.CENTER);
		    subtitle.setFont(new Font("Seriff", Font.BOLD, 36));
		    titlepanel.add(title);
		    titlepanel.add(subtitle);
		    backPane.add(titlepanel);
		    mainmenupanel = new JPanel(true);
		    Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		    Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		    Border compound = BorderFactory.createCompoundBorder(
                    raisedbevel, loweredbevel);
		    mainmenupanel.setBorder(compound);
		    mainmenupanel.setBackground(Color.cyan);
		    
		    mainmenupanel.setLayout(flow);
		    mainmenuoption1 = new JLabel("1 Jogador",SwingConstants.CENTER);
		    mainmenuoption1.addMouseListener ( this ) ;
		    mainmenuoption1.setFont(new Font("Impact", Font.BOLD, 36));
		    mainmenuoption1.setBackground(Color.cyan);
		    mainmenuoption1.setOpaque(true);
		    mainmenupanel.add(mainmenuoption1);
		    
		    mainmenuoption2 = new JLabel("2 Jogadores",SwingConstants.CENTER);
		    mainmenuoption2.addMouseListener ( this ) ;
		    mainmenuoption2.setFont(new Font("Impact", Font.BOLD, 36));
		    mainmenuoption2.setBackground(Color.cyan);
		    mainmenuoption2.setOpaque(true);
		    mainmenupanel.add(mainmenuoption2);
		    
		    mainmenuoption3 = new JLabel("CPUvsCPU",SwingConstants.CENTER);
		    mainmenuoption3.addMouseListener ( this ) ;
		    mainmenuoption3.setFont(new Font("Impact", Font.BOLD, 36));
		    mainmenuoption3.setBackground(Color.cyan);
		    mainmenuoption3.setOpaque(true);
		    mainmenupanel.add(mainmenuoption3);
		    tools.add(mainmenupanel);
		    backPane.add(tools);
		    backPane.setBorder(BorderFactory.createRaisedBevelBorder());
		    c.add(backPane);
		    frame.validate();
		}
		
		public void playGame() throws IOException
		{
			c.removeAll();
			GridLayout grid=new GridLayout(3,2);
			grid.setHgap(0);
			grid.setVgap(0);
			c.setLayout(new GridBagLayout());
			GridBagConstraints newgrid = new GridBagConstraints();
			final Image image1 = ImageIO.read(new File("Quadtria/wood.jpg"));
			BackgroundPanel titlepanel = new BackgroundPanel(image1);
			titlepanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			titlepanel.setLayout(new FlowLayout());
			title = new JLabel("Quadtria",SwingConstants.CENTER);
		    title.setFont(new Font("Old English Text MT", Font.BOLD, 60));
		    titlepanel.add(title);
		    newgrid.fill = GridBagConstraints.BOTH;
		    newgrid.gridwidth=2;
		    newgrid.ipady = 0;
		    newgrid.weightx = 50;
		    newgrid.weighty = 20;
		    newgrid.gridx = 0;
		    newgrid.gridy = 0;
		    c.add(titlepanel, newgrid);
		    
		    
			msgpanel = new JPanel();
			msgpanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			msgpanel.setLayout(new FlowLayout());
			message = new JLabel(" ",SwingConstants.CENTER);
		    message.setFont(new Font("Verdana", Font.BOLD, 20));
		    msgpanel.add(message);
		    msgpanel.setBackground(Color.red);
		    newgrid.fill = GridBagConstraints.BOTH;
		    newgrid.gridwidth=1;
		    newgrid.weightx = 50;
		    newgrid.weighty = 20;
		    newgrid.gridx = 0;
		    newgrid.gridy = 1;
		    
		    c.add(msgpanel, newgrid);		    
		    
			
			final Image image = ImageIO.read(new File("Quadtria/board.jpg"));
		    board = new BackgroundPanel(image);
		    board.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		    GridLayout backpanel = new GridLayout(8,8);
		    backpanel.setVgap(0);
		    backpanel.setHgap(0);
		    board.setLayout(new MigLayout());
			tools = new JPanel();
			label= new BackgroundPanel[22];
			board.setFocusable(false);
			//board.setBackground(Color.black);
			createBoard();
		    newgrid.fill = GridBagConstraints.BOTH;
		    //newgrid.ipady = 388;
		    newgrid.gridheight=4;
		    newgrid.weightx = 600;
		    newgrid.weighty = 600;
		    newgrid.gridx = 0;
		    newgrid.gridy = 2;
		    c.add(board, newgrid);
			
			optpanel = new JPanel();
			optpanel.setBorder(BorderFactory.createRaisedBevelBorder());
			optpanel.setLayout(new FlowLayout());
			options = new JLabel("MENU",SwingConstants.CENTER);
			options.setFont(new Font("Impact", Font.BOLD, 20));
		    optpanel.add(options);
		    optpanel.setBackground(Color.cyan);
		    newgrid.fill = GridBagConstraints.BOTH;
		    newgrid.gridheight=1;
		    newgrid.weightx = 50;
		    newgrid.weighty = 20;
		    newgrid.gridx = 1;
		    newgrid.gridy = 1;
		    optpanel.addMouseListener(this);
		    c.add(optpanel, newgrid);
		    
			plr1panel = new JPanel();
			plr1panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			plr1panel.setLayout(new BoxLayout(plr1panel,BoxLayout.Y_AXIS));
			if(game==null)
				plr1name = new JLabel("Jogador 1: ",SwingConstants.CENTER);
			else
				plr1name = new JLabel("Jogador 1: "+name1,SwingConstants.CENTER);
		    plr1name.setFont(new Font("Verdana", Font.BOLD, 16));
		    plr1moveno = new JLabel("Número de Jogadas: ",SwingConstants.CENTER);
		    plr1moveno.setFont(new Font("Verdana", Font.BOLD, 12));
		    plr1lastmove = new JLabel("Última Jogada: ",SwingConstants.CENTER);
		    plr1lastmove.setFont(new Font("Verdana", Font.BOLD, 12));
		    if(game==null)
		    	plr1score = new JLabel("Pontuação: 0",SwingConstants.CENTER);
		    else
		    	plr1score = new JLabel("Pontuação: "+game.Player1Score,SwingConstants.CENTER);
		    plr1score.setFont(new Font("Verdana", Font.BOLD, 12));
		    plr1panel.add(plr1name);
		    plr1panel.add(plr1moveno);
		    plr1panel.add(plr1lastmove);
		    plr1panel.add(plr1score);
		    plr1panel.setBackground(Color.red);
		    newgrid.fill = GridBagConstraints.BOTH;
			//newgrid.fill = GridBagConstraints.VERTICAL;
		    newgrid.gridheight=1;
		    newgrid.weightx = 50;
		    newgrid.weighty = 50;
		    newgrid.gridx = 1;
		    newgrid.gridy = 2;
		    c.add(plr1panel, newgrid);
		    
			plr2panel = new JPanel();
			plr2panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			plr2panel.setLayout(new BoxLayout(plr2panel,BoxLayout.Y_AXIS));
			if(game==null)
				plr2name = new JLabel("Jogador 2: ",SwingConstants.CENTER);
			else
				plr2name = new JLabel("Jogador 2: "+name2,SwingConstants.CENTER);
		    plr2name.setFont(new Font("Verdana", Font.BOLD, 16));
		    plr2name.setForeground(Color.white);
		    plr2moveno = new JLabel("Número de Jogadas: ",SwingConstants.CENTER);
		    plr2moveno.setFont(new Font("Verdana", Font.BOLD, 12));
		    plr2moveno.setForeground(Color.white);
		    plr2lastmove = new JLabel("Última Jogada: ",SwingConstants.CENTER);
		    plr2lastmove.setFont(new Font("Verdana", Font.BOLD, 12));
		    plr2lastmove.setForeground(Color.white);
		    if(game==null)
		    	plr2score = new JLabel("Pontuação: 0",SwingConstants.CENTER);
		    else
		    	plr2score = new JLabel("Pontuação: "+game.Player2Score,SwingConstants.CENTER);
		    plr2score.setFont(new Font("Verdana", Font.BOLD, 12));
		    plr2score.setForeground(Color.white);
		    plr2panel.add(plr2name);
		    plr2panel.add(plr2moveno);
		    plr2panel.add(plr2lastmove);
		    plr2panel.add(plr2score);
		    plr2panel.setBackground(Color.blue);
		    newgrid.fill = GridBagConstraints.BOTH;
		    newgrid.gridheight=1;
		    newgrid.weightx = 50;
		    newgrid.weighty = 50;
		    newgrid.gridx = 1;
		    newgrid.gridy = 3;
		    c.add(plr2panel, newgrid);
		    if(gameHelp){
			    helppanel = new JPanel();
				helppanel.setLayout(new FlowLayout());
			    togglehelp = new JLabel("DESATIVAR AJUDA",SwingConstants.CENTER);
			    togglehelp.setFont(new Font("Impact", Font.BOLD, 20));
			    helppanel.add(togglehelp);
			    helppanel.setBorder(BorderFactory.createRaisedBevelBorder());
			    helppanel.setBackground(Color.cyan);
			    newgrid.gridheight=1;
			    newgrid.weightx = 50;
			    newgrid.weighty = 1;
			    newgrid.gridx = 1;
			    newgrid.gridy = 4;
			    helppanel.addMouseListener(this);
			    c.add(helppanel, newgrid);
			    
				helpbar = new JPanel();
				helpbar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				helpbar.setLayout(new FlowLayout());
				helpmsg = new JLabel("Barra de Ajuda",SwingConstants.CENTER);
				helpmsg.setFont(new Font("Arial", Font.BOLD, 18));
			    helpbar.add(helpmsg);
			    helpbar.setBackground(Color.white);
			    newgrid.fill = GridBagConstraints.BOTH;
			    newgrid.gridwidth=2;
			    newgrid.ipady = 0;
			    newgrid.weightx = 50;
			    newgrid.weighty = 10;
			    newgrid.gridx = 0;
			    newgrid.gridy = 6;
			    c.add(helpbar, newgrid);
		    }
		    else{
			    helppanel = new JPanel();
				helppanel.setLayout(new FlowLayout());
			    togglehelp = new JLabel("ATIVAR AJUDA",SwingConstants.CENTER);
			    togglehelp.setFont(new Font("Impact", Font.BOLD, 20));
			    helppanel.add(togglehelp);
			    helppanel.setBorder(BorderFactory.createRaisedBevelBorder());
			    helppanel.setBackground(Color.cyan);
			    newgrid.gridheight=1;
			    newgrid.weightx = 50;
			    newgrid.weighty = 1;
			    newgrid.gridx = 1;
			    newgrid.gridy = 4;
			    helppanel.addMouseListener(this);
			    c.add(helppanel, newgrid);
			    
				helpbar = new JPanel();
				helpbar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				helpbar.setLayout(new FlowLayout());
				helpmsg = new JLabel("Barra de Ajuda",SwingConstants.CENTER);
				helpmsg.setFont(new Font("Arial", Font.BOLD, 18));
			    helpbar.add(helpmsg);
			    helpbar.setBackground(Color.white);
			    newgrid.fill = GridBagConstraints.BOTH;
			    newgrid.gridwidth=2;
			    newgrid.ipady = 0;
			    newgrid.weightx = 50;
			    newgrid.weighty = 10;
			    newgrid.gridx = 0;
			    newgrid.gridy = 6;
			    helpbar.setVisible(false);
			    c.add(helpbar, newgrid);
		    }
			pNo = 1;
			prevNo = -1;
			boxNo = -1;
			gameEnd=false;
			gameBegin=true;
			frame.validate();
}
		
		private void cpuvscpu()
		{
			
			AImoving=true;
			timercpu = new javax.swing.Timer(mili_sec, new ActionListener() {
		          public void actionPerformed(ActionEvent e) {
						makeMove(aiMakeMove());
						frame.validate();
		          }
		       });
			timercpu.start();
		}
		
		private BackgroundPanel aiMakeMove()
		{
			if(gameEnd)
				return null;
			if(pNo==2)
			a = (AI) game.player2;
			else
			a = (AI) game.player1;
			aiMove = a.NextMove(game.gBoard,dificulty,aioption);
			prevNo=aiMove[0];
			boxNo=aiMove[1];
			for(int i=1;i<21;i++)
			{
				if(Integer.parseInt(label[i].getName())==boxNo)
					return label[i];
			}
			 return null;
		}
		
		private void makeMove(BackgroundPanel panel)
		{
			if(gameEnd)
				return;
			if(panel==null)
			{
				wrongMove=true;
				return;
			}
			Image image1 = null;
			try {
				image1 = ImageIO.read(new File("Quadtria/pecaAzul.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image image2 = null;
			try {
				image2 = ImageIO.read(new File("Quadtria/pecaVermelha.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image image3 = null;
			try {
				image3 = ImageIO.read(new File("Quadtria/empty.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			int firstpos=0;
			int lastpos=0;
			Vector<Integer> possibilities = game.gBoard.getPossibilities(pNo,prevNo);
			boxNo=Integer.parseInt(panel.getName());
			for(int j=1;j<21;j++)
			{
				if(possibilities.contains(Integer.parseInt(label[j].getName())))
					{
						if(Integer.parseInt(label[j].getName())==boxNo)
						{
							if(pNo==1){
								panel.setImage(image2);
								labelcolor[j]=1;
							}
							else{
								panel.setImage(image1);
								labelcolor[j]=2;
							}
							lastpos=Integer.parseInt(labeltext[j-1].getText());
						}
						else{
							label[j].setImage(image3);
							labelcolor[j]=0;
						}
					}
				if(Integer.parseInt(label[j].getName())==prevNo){
					label[j].setImage(image3);
					firstpos=Integer.parseInt(labeltext[j-1].getText());
					labelcolor[j]=0;
				}
			}
			
			/*if (game.gBoard.hipoMove(game.gBoard.getIntBoard(),
					prevNo, boxNo, pNo)) {*/
				gCon = game.Move(prevNo,boxNo);
				if (pNo == 1){
					if(game.gBoard.moveCount%2==0)
						plr1moveno.setText("Número de Jogadas: "+Integer.toString(game.gBoard.moveCount/2));
					else
						plr1moveno.setText("Número de Jogadas: "+Integer.toString(game.gBoard.moveCount/2+1));
					plr1lastmove.setText("Última Jogada: "+firstpos+"-"+lastpos);
					pNo = 2;
				}
				else{
					if(game.gBoard.moveCount%2==0)
						plr2moveno.setText("Número de Jogadas: "+Integer.toString(game.gBoard.moveCount/2));
					else
						plr2moveno.setText("Número de Jogadas: "+Integer.toString(game.gBoard.moveCount/2+1));
					plr2lastmove.setText("Última Jogada: "+firstpos+"-"+lastpos);
					pNo = 1;
				}
				if (gCon != 0) {
						 endGame();
					 }
				else{
					prevNo=-1;
					boxNo=-1;
				}
				
			if(game.gBoard.validCorner1)
					label[0].setImage(image2);
			
			if(game.gBoard.validCorner2)
					label[21].setImage(image1);
			
				refreshMessage();		
		}
		
		private void endGame(){
			gameEnd=true;	
			 for(int i=1;i<21;i++)
				{
							label[i].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			if(gameoption==3)
			{
				timercpu.stop();
				AImoving=false;
			}
			endGameDialog();
			endgamedialog.setVisible(true);
		}

		private void endGameDialog() {
			endgamedialog= new JDialog(frame,"Fim do Jogo");
			endgamedialog.setLocationRelativeTo(frame);
			JPanel p = new JPanel();
			p.setLayout(new GridBagLayout());
			GridBagConstraints newgrid = new GridBagConstraints();
			JLabel l = new JLabel("Deseja continuar a jogar?");
			newgrid.fill = GridBagConstraints.BOTH;
		    newgrid.gridwidth=2;
		    newgrid.weightx = 50;
		    newgrid.weighty = 50;
		    newgrid.gridx = 0;
		    newgrid.gridy = 0;
		    p.add(l, newgrid);
			createYesendButton();
		    newgrid.gridwidth=1;
		    newgrid.weightx = 50;
		    newgrid.weighty = 50;
		    newgrid.gridx = 0;
		    newgrid.gridy = 1;
			 p.add(yes, newgrid);
			createNoendButton();
		    newgrid.gridwidth=1;
		    newgrid.weightx = 50;
		    newgrid.weighty = 50;
		    newgrid.gridx = 1;
		    newgrid.gridy = 1;
			p.add(no, newgrid);
			endgamedialog.add(p);
			endgamedialog.pack();
			
		}

		private void createNoendButton() {
			no = new JButton("Não");
			
			no.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {	
					endgamedialog.setVisible(false);
					gameEnd=false;
					gCon = 0;
					try {
						InitiateWindow();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			});
			
		}

		private void createYesendButton() {
			yes = new JButton("Sim");
			
			yes.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
					endgamedialog.setVisible(false);
					gCon = 0;
					if(gameoption==1){
						if(game!=null){
							int p1s=game.Player1Score;
							int p2s=game.Player2Score;
							game = new Game(name1);
							game.Player1Score=p1s;
							game.Player2Score=p2s;
						}
						else
					    game = new Game(name1);
					}
					else if(gameoption==2){
						if(game!=null){
							int p1s=game.Player1Score;
							int p2s=game.Player2Score;
							game = new Game(name1,name2);
							game.Player1Score=p1s;
							game.Player2Score=p2s;
						}
						else
					    game = new Game(name1,name2);
					}
					else{
						if(game!=null){
							int p1s=game.Player1Score;
							int p2s=game.Player2Score;
							game = new Game();
							game.Player1Score=p1s;
							game.Player2Score=p2s;
						}
						else
					    game = new Game();
					}
					game.NewBoard();
					try {
						playGame();
						printBoard(game.gBoard);
						refreshMessage();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			});
			
		}

		private void refreshMessage() {
			message.setForeground(Color.black);
			 if (gCon == 1 || gCon == 2) {
				 message.setText(game.PlayerName(gCon) + " Ganhou!");
				 msgpanel.setBackground(Color.green);
				 game.Win(gCon);
				 helpmsg.setText("<html>Fim do jogo. Clique no botão menu para voltar ao menu<br> ou continue a jogar ao escolher continuar a jogar</html>");
				}
			 else if(wrongMove==true)
			 {
				 message.setText("Jogada Impossivel!");
				 msgpanel.setBackground(Color.white);
				 wrongMove=false;
			 }
			 else{
				if(pNo==1){
				message.setText(game.PlayerName(pNo) + " a Mover");
			    msgpanel.setBackground(Color.red);
				}
				else{
					message.setText(game.PlayerName(pNo) + " a Mover");
				    msgpanel.setBackground(Color.blue);
				    message.setForeground(Color.white);
				}
				if(gameoption==3){
					if (fast)
						helpmsg.setText("Aguarde que o jogo termine");
					else
						helpmsg.setText("Pressione BARRA DE ESPAÇOS para avançar a jogada");
				}
				else{
					if(prevNo==-1)
						helpmsg.setText("Clique numa peça sua para a mover");
					else
						helpmsg.setText("<html>Clique numa posição adjacente livre para a mover a peça seleccionada<br> ou clique na peça seleccionada para mover outra peça</html>");
				}
				}
		}


		private void createBoard() throws IOException {
				/*Primeiro Quadrante*/
				labeltext = new JLabel[20];
				/*Verificação*/
				final Image image = ImageIO.read(new File("Quadtria/empty.png"));
				label[0]= new BackgroundPanel(image);
				//label[0].setBorder(BorderFactory.createRaisedBevelBorder());
				board.add(label[0],"pos (visual.w*0.026) (visual.h*0.01) (visual.w*0.08) (visual.h*0.06)");
				
				/*0*/
				label[1]= new BackgroundPanel(image);
				//label[1].setBorder(BorderFactory.createRaisedBevelBorder());
				label[1].setName("0");
				label[1].addMouseListener ( this ) ;
				labeltext[0]=new JLabel("1",SwingConstants.CENTER);
				label[1].add(labeltext[0]);
				board.add(label[1],"pos (visual.w*0.09) (visual.h*0.07) (visual.w*0.17) (visual.h*0.14)");
				/*2*/
				label[2]= new BackgroundPanel(image);
				//label[2].setBorder(BorderFactory.createRaisedBevelBorder());
				label[2].setName("2");
				label[2].addMouseListener ( this ) ;
				labeltext[1]=new JLabel("2",SwingConstants.CENTER);
				label[2].add(labeltext[1]);
				board.add(label[2],"pos (visual.w*0.37) (visual.h*0.07) (visual.w*0.45) (visual.h*0.14)");
				/*8*/
				label[3]= new BackgroundPanel(image);
				//label[3].setBorder(BorderFactory.createRaisedBevelBorder());
				label[3].setName("8");
				label[3].addMouseListener ( this ) ;
				labeltext[2]=new JLabel("3",SwingConstants.CENTER);
				label[3].add(labeltext[2]);
				board.add(label[3],"pos (visual.w*0.223) (visual.h*0.208) (visual.w*0.303) (visual.h*0.276)");
				/*14*/
				label[4]= new BackgroundPanel(image);
				//label[4].setBorder(BorderFactory.createRaisedBevelBorder());
				label[4].setName("14");
				label[4].addMouseListener ( this ) ;
				labeltext[3]=new JLabel("4",SwingConstants.CENTER);
				label[4].add(labeltext[3]);
				board.add(label[4],"pos (visual.w*0.09) (visual.h*0.365) (visual.w*0.17) (visual.h*0.43)");
				/*16*/
				label[5]= new BackgroundPanel(image);
				//label[5].setBorder(BorderFactory.createRaisedBevelBorder());
				label[5].setName("16");
				label[5].addMouseListener ( this ) ;
				labeltext[4]=new JLabel("5",SwingConstants.CENTER);
				label[5].add(labeltext[4]);
				board.add(label[5],"pos (visual.w*0.37) (visual.h*0.365) (visual.w*0.45) (visual.h*0.43)");
				
				
				/*Segundo Quadrante*/
				/*4*/
				label[6]= new BackgroundPanel(image);
				//label[6].setBorder(BorderFactory.createRaisedBevelBorder());
				label[6].setName("4");
				label[6].addMouseListener ( this ) ;
				labeltext[5]=new JLabel("6",SwingConstants.CENTER);
				label[6].add(labeltext[5]);
				board.add(label[6],"pos (visual.w*0.568) (visual.h*0.07) (visual.w*0.648) (visual.h*0.14)");
				/*6*/
				label[7]= new BackgroundPanel(image);
				//label[7].setBorder(BorderFactory.createRaisedBevelBorder());
				label[7].setName("6");
				label[7].addMouseListener ( this ) ;
				labeltext[6]=new JLabel("7",SwingConstants.CENTER);
				label[7].add(labeltext[6]);
				board.add(label[7],"pos (visual.w*0.842) (visual.h*0.07) (visual.w*0.922) (visual.h*0.14)");
				/*12*/
				label[8]= new BackgroundPanel(image);
				//label[8].setBorder(BorderFactory.createRaisedBevelBorder());
				label[8].setName("12");
				label[8].addMouseListener ( this ) ;
				labeltext[7]=new JLabel("8",SwingConstants.CENTER);
				label[8].add(labeltext[7]);
				board.add(label[8],"pos (visual.w*0.705) (visual.h*0.208) (visual.w*0.790) (visual.h*0.276)");
				/*18*/
				label[9]= new BackgroundPanel(image);
				//label[9].setBorder(BorderFactory.createRaisedBevelBorder());
				label[9].setName("18");
				label[9].addMouseListener ( this ) ;
				labeltext[8]=new JLabel("9",SwingConstants.CENTER);
				label[9].add(labeltext[8]);
				board.add(label[9],"pos (visual.w*0.568) (visual.h*0.365) (visual.w*0.648) (visual.h*0.43)");
				/*20*/
				label[10]= new BackgroundPanel(image);
				//label[10].setBorder(BorderFactory.createRaisedBevelBorder());
				label[10].setName("20");
				label[10].addMouseListener ( this ) ;
				labeltext[9]=new JLabel("10",SwingConstants.CENTER);
				label[10].add(labeltext[9]);
				board.add(label[10],"pos (visual.w*0.842) (visual.h*0.365) (visual.w*0.922) (visual.h*0.43)");
				
				/*Terceiro Quadrante*/
				
				/*28*/
				label[11]= new BackgroundPanel(image);
				//label[11].setBorder(BorderFactory.createRaisedBevelBorder());
				label[11].setName("28");
				label[11].addMouseListener ( this ) ;
				labeltext[10]=new JLabel("11",SwingConstants.CENTER);
				label[11].add(labeltext[10]);
				board.add(label[11],"pos (visual.w*0.09) (visual.h*0.555) (visual.w*0.17) (visual.h*0.625)");
				/*30*/
				label[12]= new BackgroundPanel(image);
				//label[12].setBorder(BorderFactory.createRaisedBevelBorder());
				label[12].setName("30");
				label[12].addMouseListener ( this ) ;
				labeltext[11]=new JLabel("12",SwingConstants.CENTER);
				label[12].add(labeltext[11]);
				board.add(label[12],"pos (visual.w*0.37) (visual.h*0.555) (visual.w*0.45) (visual.h*0.625)");
				/*36*/
				label[13]= new BackgroundPanel(image);
				//label[13].setBorder(BorderFactory.createRaisedBevelBorder());
				label[13].setName("36");
				label[13].addMouseListener ( this ) ;
				labeltext[12]=new JLabel("13",SwingConstants.CENTER);
				label[13].add(labeltext[12]);
				board.add(label[13],"pos (visual.w*0.223) (visual.h*0.704) (visual.w*0.303) (visual.h*0.774)");
				/*42*/
				label[14]= new BackgroundPanel(image);
				//label[14].setBorder(BorderFactory.createRaisedBevelBorder());
				label[14].setName("42");
				label[14].addMouseListener ( this ) ;
				labeltext[13]=new JLabel("14",SwingConstants.CENTER);
				label[14].add(labeltext[13]);
			    board.add(label[14],"pos (visual.w*0.09) (visual.h*0.846) (visual.w*0.17) (visual.h*0.916)");
				/*44*/
				label[15]= new BackgroundPanel(image);
				//label[15].setBorder(BorderFactory.createRaisedBevelBorder());
				label[15].setName("44");
				label[15].addMouseListener ( this ) ;
				labeltext[14]=new JLabel("15",SwingConstants.CENTER);
				label[15].add(labeltext[14]);
			    board.add(label[15],"pos (visual.w*0.37) (visual.h*0.846) (visual.w*0.45) (visual.h*0.916)");
			    
			    /*Quarto Quadrante*/
			    /*32*/
				label[16]= new BackgroundPanel(image);
				//label[16].setBorder(BorderFactory.createRaisedBevelBorder());
				label[16].setName("32");
				label[16].addMouseListener ( this ) ;
				labeltext[15]=new JLabel("16",SwingConstants.CENTER);
				label[16].add(labeltext[15]);
				board.add(label[16],"pos (visual.w*0.568) (visual.h*0.555) (visual.w*0.648) (visual.h*0.625)");
				/*34*/
				label[17]= new BackgroundPanel(image);
				//label[17].setBorder(BorderFactory.createRaisedBevelBorder());
				label[17].setName("34");
				label[17].addMouseListener ( this ) ;
				labeltext[16]=new JLabel("17",SwingConstants.CENTER);
				label[17].add(labeltext[16]);
				board.add(label[17],"pos (visual.w*0.842) (visual.h*0.555) (visual.w*0.922) (visual.h*0.625)");
				/*40*/
				label[18]= new BackgroundPanel(image);
				//label[18].setBorder(BorderFactory.createRaisedBevelBorder());
				label[18].setName("40");
				label[18].addMouseListener ( this ) ;
				labeltext[17]=new JLabel("18",SwingConstants.CENTER);
				label[18].add(labeltext[17]);
				board.add(label[18],"pos (visual.w*0.705) (visual.h*0.704) (visual.w*0.790) (visual.h*0.774)");
				/*46*/
				label[19]= new BackgroundPanel(image);
				//label[19].setBorder(BorderFactory.createRaisedBevelBorder());
				label[19].setName("46");
				label[19].addMouseListener ( this ) ;
				labeltext[18]=new JLabel("19",SwingConstants.CENTER);
				label[19].add(labeltext[18]);
			    board.add(label[19],"pos (visual.w*0.568) (visual.h*0.846) (visual.w*0.648) (visual.h*0.916)");
				/*48*/
				label[20]= new BackgroundPanel(image);
				//label[20].setBorder(BorderFactory.createRaisedBevelBorder());
				label[20].setName("48");
				label[20].addMouseListener ( this ) ;
				labeltext[19]=new JLabel("20",SwingConstants.CENTER);
				label[20].add(labeltext[19]);
			    board.add(label[20],"pos (visual.w*0.842) (visual.h*0.846) (visual.w*0.922) (visual.h*0.916)");
			    
			    /*Verificação*/
			    label[21]= new BackgroundPanel(image);
				//label[21].setBorder(BorderFactory.createRaisedBevelBorder());
			    board.add(label[21],"pos (visual.w*0.95) (visual.h*0.913) (visual.w)+5 (visual.h*0.966)");

			    final Image image2 = ImageIO.read(new File("Quadtria/unblock.png"));
			    
			    label[0].setImage(image2);
			    label[21].setImage(image2);
			    
			    frame.validate();
			    
		}
		
		private void printBoard(Board board) throws IOException
		{
			//final Image image1 = ImageIO.read(new File("Quadtria/pecaescolhida.png"));
			final Image image2 = ImageIO.read(new File("Quadtria/empty.png"));
			final Image image3 = ImageIO.read(new File("Quadtria/pecaAzul.png"));
			final Image image4 = ImageIO.read(new File("Quadtria/pecaVermelha.png"));
			//final Image image5 = ImageIO.read(new File("Quadtria/possibleAzul.png"));
			//final Image image6 = ImageIO.read(new File("Quadtria/possibleVermelho.png"));
		
			
			int tempboard[] = board.getIntBoard();
			int pos=0;
			for(int i=1; i<21;i++)
			{
				pos = Integer.parseInt(label[i].getName());
				switch(tempboard[pos])
				{
				case 1: label[i].setImage(image4);labelcolor[i]=1;break;
				case 2: label[i].setImage(image3);labelcolor[i]=2;break;
				default: label[i].setImage(image2);labelcolor[i]=0;break;
				}
			}
		}



		/**Função do Keylistener. Quando uma tecla for pressionada*/
		public void keyPressed(KeyEvent e) {
		}

		/**Função do keylistener. Quando uma tecla for libertada*/
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_SPACE)
			{
				if(gameoption==3&&!fast)
				{
					AImoving=true;
					makeMove(aiMakeMove());
					frame.validate();
					AImoving=false;
				}
			}
		}

		/**Função do Keylistener.*/
		public void keyTyped(KeyEvent e) {}
		
		
		public void option1Dialog()
		{
			option1dialog= new JDialog(frame,"1 Jogador");
			option1dialog.setLocationRelativeTo(frame);
			JPanel p = new JPanel();
			p.setLayout(new GridBagLayout());
			GridBagConstraints newgrid = new GridBagConstraints();
			JLabel l = new JLabel("Escolha o seu nome: ");
			player1name = new JTextField();
			newgrid.fill=GridBagConstraints.BOTH;
			newgrid.gridheight=1;
			newgrid.gridwidth=1;
			newgrid.weightx=20;
		    newgrid.gridx = 0;
		    newgrid.gridy = 0;
			p.add(l,newgrid);
			newgrid.gridwidth=1;
		    newgrid.gridx = 1;
		    newgrid.gridy = 0;
		    newgrid.weightx=50;
			p.add(player1name,newgrid);
			JLabel l1 = new JLabel("Escolha uma Dificuldade:");
			newgrid.gridwidth=1;
		    newgrid.gridx = 0;
		    newgrid.gridy = 2;
		    newgrid.weightx=50;
			p.add(l1,newgrid);
		    Facil = new JCheckBox("Facil");
		    Facil.addItemListener(this);
		    Medio = new JCheckBox("Medio");
		    Medio.addItemListener(this);
		    Dificil = new JCheckBox("Dificil");
		    Dificil.addItemListener(this);
		    AINormal = new JCheckBox("AI s/ Cortes");
		    AINormal.addItemListener(this);
		    AICortes = new JCheckBox("AI c/ cortes");
		    AICortes.addItemListener(this);
		    AIKiller = new JCheckBox("AI c/ Killer heuristic");
		    AIKiller.addItemListener(this);
			newgrid.gridwidth=1;
		    newgrid.gridx = 0;
		    newgrid.gridy = 3;
		    p.add(Facil,newgrid);
			newgrid.gridwidth=1;
		    newgrid.gridx = 1;
		    newgrid.gridy = 3;
		    p.add(AINormal,newgrid);
			newgrid.gridwidth=1;
		    newgrid.gridx = 0;
		    newgrid.gridy = 4;
		    p.add(Medio,newgrid);
			newgrid.gridwidth=1;
		    newgrid.gridx = 1;
		    newgrid.gridy = 4;
		    p.add(AICortes,newgrid);
			newgrid.gridwidth=1;
		    newgrid.gridx = 0;
		    newgrid.gridy = 5;
		    p.add(Dificil,newgrid);
			newgrid.gridwidth=1;
		    newgrid.gridx = 1;
		    newgrid.gridy = 5;
		    p.add(AIKiller,newgrid);
			createOkButton1();
			newgrid.gridwidth=1;
		    newgrid.gridx = 0;
		    newgrid.gridy = 6;
			p.add(ok,newgrid);
			option1dialog.add(p);
			option1dialog.pack();
		}
		
		private void createOkButton1() {
			ok = new JButton("Ok");
			
			ok.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
					name1="";
					if(player1name.getText().isEmpty())
						name1="Player1";
					else
						name1=player1name.getText();
					name2="AI";
					plr2name.setText("Jogador 2: "+name2);
					
					option1dialog.setVisible(false);
					plr1name.setText("Jogador 1 : "+name1);
					if(game!=null){
						int p1s=game.Player1Score;
						int p2s=game.Player2Score;
						game = new Game(name1);
						game.Player1Score=p1s;
						game.Player2Score=p2s;
					}
					else
				    game = new Game(name1);
				    gameoption=1;
					game.NewBoard();
					refreshMessage();
					try {
						printBoard(game.gBoard);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			});
			
		}

		public void option2Dialog()
		{
			option2dialog= new JDialog(frame,"2 Jogadores");
			option2dialog.setLocationRelativeTo(frame);
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
			JLabel l = new JLabel("Escolha o nome do Jogador 1:");
			player1name = new JTextField();
			JLabel l2 = new JLabel("Escolha o nome do Jogador 2:");
			player2name = new JTextField();
			p.add(l);
			p.add(player1name);
			p.add(l2);
			p.add(player2name);
			createOkButton2();
			p.add(ok);
			option2dialog.add(p);
			option2dialog.pack();
		}
		
		private void createOkButton2() {
			ok = new JButton("Ok");
			
			ok.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
					name1="";
					name2="";
					if(player1name.getText().isEmpty())
						name1="Player1";
					else
						name1=player1name.getText();
					
					if(player1name.getText().isEmpty())
						name2="Player2";
					else
						name2=player2name.getText();
					
					plr1name.setText("Jogador 1 : "+name1);
					plr2name.setText("Jogador 2 : "+name2);
					option2dialog.setVisible(false);
					if(game!=null){
						int p1s=game.Player1Score;
						int p2s=game.Player2Score;
						game = new Game(name1,name2);
						game.Player1Score=p1s;
						game.Player2Score=p2s;
					}
					else
				    game = new Game(name1,name2);
				    gameoption=2;
					game.NewBoard();
					refreshMessage();
					try {
						printBoard(game.gBoard);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			});
			
		}

		public void option3Dialog()
		{
			option3dialog= new JDialog(frame,"CPUvsCPU");
			option3dialog.setLocationRelativeTo(frame);
			JPanel p = new JPanel();
			p.setLayout(new GridBagLayout());
			GridBagConstraints newgrid = new GridBagConstraints();
			JLabel l1 = new JLabel("Escolha uma Dificuldade:");
			newgrid.fill=GridBagConstraints.BOTH;
			newgrid.gridwidth=1;
		    newgrid.gridx = 0;
		    newgrid.gridy = 2;
			p.add(l1);
		    Facil = new JCheckBox("Facil");
		    Facil.addItemListener(this);
		    Medio = new JCheckBox("Medio");
		    Medio.addItemListener(this);
		    Dificil = new JCheckBox("Dificil");
		    Dificil.addItemListener(this);
		    AINormal = new JCheckBox("AI s/ Cortes");
		    AINormal.addItemListener(this);
		    AICortes = new JCheckBox("AI c/ cortes");
		    AICortes.addItemListener(this);
		    AIKiller = new JCheckBox("AI c/ Killer heuristic");
		    AIKiller.addItemListener(this);
			newgrid.gridwidth=1;
		    newgrid.gridx = 0;
		    newgrid.gridy = 3;
		    p.add(Facil,newgrid);
			newgrid.gridwidth=1;
		    newgrid.gridx = 1;
		    newgrid.gridy = 3;
		    p.add(AINormal,newgrid);
			newgrid.gridwidth=1;
		    newgrid.gridx = 0;
		    newgrid.gridy = 4;
		    p.add(Medio,newgrid);
			newgrid.gridwidth=1;
		    newgrid.gridx = 1;
		    newgrid.gridy = 4;
		    p.add(AICortes,newgrid);
			newgrid.gridwidth=1;
		    newgrid.gridx = 0;
		    newgrid.gridy = 5;
		    p.add(Dificil,newgrid);
			newgrid.gridwidth=1;
		    newgrid.gridx = 1;
		    newgrid.gridy = 5;
		    p.add(AIKiller,newgrid);
			newgrid.gridwidth=1;
		    newgrid.gridx = 0;
		    newgrid.gridy = 6;
			JLabel l = new JLabel("Quer correr o jogo em modo rápido?");
			newgrid.gridwidth=2;
		    newgrid.gridx = 0;
		    newgrid.gridy = 7;
		    p.add(l,newgrid);
			createYesButton();
			newgrid.gridwidth=1;
		    newgrid.gridx = 0;
		    newgrid.gridy = 8;
			 p.add(yes,newgrid);
			createNoButton();
			newgrid.gridwidth=1;
		    newgrid.gridx = 1;
		    newgrid.gridy = 8;
			 p.add(no,newgrid);
			option3dialog.add(p);
			option3dialog.pack();
		}
		
		
		private void createNoButton() {
			no = new JButton("Não");
			
			no.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
					fast=false;
					name1="AI1";
					name2="AI2";
					plr1name.setText("Jogador 1: "+name1);
					plr2name.setText("Jogador 2: "+name2);
					option3dialog.setVisible(false);
					if(game!=null){
						int p1s=game.Player1Score;
						int p2s=game.Player2Score;
						game = new Game();
						game.Player1Score=p1s;
						game.Player2Score=p2s;
					}
					else
					game= new Game();
					gameoption=3;
					game.NewBoard();
					refreshMessage();
					try {
						printBoard(game.gBoard);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			});
			
		}

		private void createYesButton() {
			yes = new JButton("Sim");
			
			yes.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fast=true;
					plr1name.setText("Jogador 1: AI1");
					plr2name.setText("Jogador 2: AI2");
					option3dialog.setVisible(false);
					if(game!=null){
						int p1s=game.Player1Score;
						int p2s=game.Player2Score;
						game = new Game();
						game.Player1Score=p1s;
						game.Player2Score=p2s;
					}
					else
				    game= new Game();
				    gameoption=3;
					game.NewBoard();
					refreshMessage();
					cpuvscpu();
					try {
						printBoard(game.gBoard);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			});
			
		}

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			Object source = arg0.getItemSelectable();

	        if (source == Facil) {
	        	Medio.setSelected(false);
	        	Dificil.setSelected(false);
	        	dificulty=4;
		        }
	        if (source == Medio) {
	        	Facil.setSelected(false);
	        	Dificil.setSelected(false);
	        	dificulty=5;
	        }
	        if (source == Dificil) {
	        	Medio.setSelected(false);
	        	Facil.setSelected(false);
	        	dificulty=6;
	        }
	        if (source == AINormal) {
	        	AICortes.setSelected(false);
	        	AIKiller.setSelected(false);
	        	aioption=1;
		        }
	        if (source == AICortes) {
	        	AINormal.setSelected(false);
	        	AIKiller.setSelected(false);
	        	aioption=2;
	        }
	        if (source == AIKiller) {
	        	AICortes.setSelected(false);
	        	AINormal.setSelected(false);
	        	aioption=3;
	        }
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			if(arg0.getSource().equals(mainmenuoption1))
				{
					mainmenuoption1.setBackground(Color.orange);
					mainmenuoption1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			else if(arg0.getSource().equals(mainmenuoption2))
				{
					mainmenuoption2.setBackground(Color.orange);
					mainmenuoption2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			else if(arg0.getSource().equals(mainmenuoption3))
				{
					mainmenuoption3.setBackground(Color.orange);
					mainmenuoption3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			else if(arg0.getSource().equals(optpanel))
			{
				optpanel.setBackground(Color.orange);
				optpanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			else if(arg0.getSource().equals(helppanel))
			{
				helppanel.setBackground(Color.orange);
				helppanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			else if(gameBegin&&!gameEnd&&!AImoving){
				for(int i=1;i<21;i++)
				{
					if(arg0.getSource().equals(label[i]))
					{
						if((labelcolor[i]==pNo&&prevNo==-1)||labelcolor[i]==3||Integer.parseInt(label[i].getName())==prevNo)
							label[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					}
				}
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			if(arg0.getSource().equals(mainmenuoption1))
				mainmenuoption1.setBackground(Color.cyan);
			else if(arg0.getSource().equals(mainmenuoption2))
				mainmenuoption2.setBackground(Color.cyan);
			else if(arg0.getSource().equals(mainmenuoption3))
				mainmenuoption3.setBackground(Color.cyan);
			else if(arg0.getSource().equals(optpanel))
				optpanel.setBackground(Color.cyan);
			else if(arg0.getSource().equals(helppanel))
				helppanel.setBackground(Color.cyan);
			
			else if(gameBegin){
				for(int i=1;i<21;i++)
				{
					if(arg0.getSource().equals(label[i]))
							label[i].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			/*if(n>=2){
			requestFocus();
			label[0].repaint();
			}
			n++;*/
			if(arg0.getSource().equals(optpanel)) {
				optpanel.setBorder(BorderFactory.createLoweredBevelBorder());
			}
			if(arg0.getSource().equals(helppanel)) {
				helppanel.setBorder(BorderFactory.createLoweredBevelBorder());
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if(arg0.getSource().equals(mainmenuoption1))
				try {
					option1Dialog();
					option1dialog.setVisible(true);
					playGame();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(arg0.getSource().equals(mainmenuoption2))
				try {
					option2Dialog();
					option2dialog.setVisible(true);
					playGame();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(arg0.getSource().equals(mainmenuoption3))
				try {
					option3Dialog();
					option3dialog.setVisible(true);
					playGame();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(arg0.getSource().equals(optpanel)) {
							optpanel.setBorder(BorderFactory.createRaisedBevelBorder());
							if(menudialog==null||!menudialog.isVisible())
								{
									menuDialog();
									menudialog.setVisible(true);
								}
						}
			else if(arg0.getSource().equals(helppanel)) {
				helppanel.setBorder(BorderFactory.createRaisedBevelBorder());
				if(helpbar.isVisible())
					{
						helpbar.setVisible(false);
						togglehelp.setText("ATIVAR AJUDA");
						gameHelp=false;
					}
				else
					{
						helpbar.setVisible(true);
						togglehelp.setText("DESATIVAR AJUDA");
						gameHelp=true;
					}
			}
			
			else if(gameBegin&&!gameEnd&&!AImoving){
				for(int i=1;i<21;i++)
				{
					if(arg0.getSource().equals(label[i]))
					{
						if(prevNo!=-1)
						{
							if(Integer.parseInt(label[i].getName())==prevNo)
							{
								resetprevPiece(label[i]);
								refreshMessage();
							}
							else
							if(labelcolor[i]==3)
							{
								makeMove(label[i]);
								if(gameoption==1)
									{
										AImoving=true;
										makeMove(aiMakeMove());
										AImoving=false;
									}
								
								
							}
						}
						else if(labelcolor[i]==pNo){
								chooseprevPiece(label[i]);
								refreshMessage();
						}
					}
				}
			}
		}

		private void chooseprevPiece(BackgroundPanel panel)
		{
			Image image1 = null;
			try {
				image1 = ImageIO.read(new File("Quadtria/pecaescolhidaAzul.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image image2 = null;
			try {
				image2 = ImageIO.read(new File("Quadtria/pecaescolhidaVermelha.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image image3 = null;
			try {
				image3 = ImageIO.read(new File("Quadtria/possibleAzul.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image image4 = null;
			try {
				image4 = ImageIO.read(new File("Quadtria/possibleVermelho.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(pNo==1)
				panel.setImage(image2);
			else
				panel.setImage(image1);
			prevNo=Integer.parseInt(panel.getName());
			Vector<Integer> possibilities = game.gBoard.getPossibilities(pNo,prevNo);
			
			for(int j=1;j<21;j++)
			{
				if(possibilities.contains(Integer.parseInt(label[j].getName())))
					{
						if(pNo==1)
							label[j].setImage(image4);
						else
							label[j].setImage(image3);
						labelcolor[j]=3;
					}
			}
		}
		
		private void resetprevPiece(BackgroundPanel panel)
		{
			Image image1 = null;
			try {
				image1 = ImageIO.read(new File("Quadtria/pecaAzul.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Image image2 = null;
			try {
				image2 = ImageIO.read(new File("Quadtria/pecaVermelha.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Image image3 = null;
			try {
				image3 = ImageIO.read(new File("Quadtria/empty.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(pNo==1)
				panel.setImage(image2);
			else
				panel.setImage(image1);
			Vector<Integer> possibilities = game.gBoard.getPossibilities(pNo,prevNo);
			prevNo=-1;
			for(int j=1;j<21;j++)
			{
				if(possibilities.contains(Integer.parseInt(label[j].getName())))
					{
						if(pNo==1){
							label[j].setImage(image3);
							labelcolor[j]=0;
						}
						else{
							label[j].setImage(image3);
							labelcolor[j]=0;
						}
					}
			}
		}

		private void menuDialog() {
			menudialog= new JDialog(frame,"Menu");
			menudialog.setLocationRelativeTo(frame);
			JPanel p = new JPanel();
			p.setLayout(new GridBagLayout());
			GridBagConstraints newgrid = new GridBagConstraints();
			JLabel l = new JLabel("Deseja desistir do jogo e voltar para o menu inicial?");
			newgrid.fill = GridBagConstraints.BOTH;
		    newgrid.gridwidth=2;
		    newgrid.weightx = 50;
		    newgrid.weighty = 50;
		    newgrid.gridx = 0;
		    newgrid.gridy = 0;
		    p.add(l, newgrid);
			createYesmenuButton();
		    newgrid.gridwidth=1;
		    newgrid.weightx = 50;
		    newgrid.weighty = 50;
		    newgrid.gridx = 0;
		    newgrid.gridy = 1;
			 p.add(yes, newgrid);
			createNomenuButton();
		    newgrid.gridwidth=1;
		    newgrid.weightx = 50;
		    newgrid.weighty = 50;
		    newgrid.gridx = 1;
		    newgrid.gridy = 1;
			 p.add(no, newgrid);
			menudialog.add(p);
			menudialog.pack();
			
		}

		private void createNomenuButton() {
			no = new JButton("Não");
			
			no.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {	
					menudialog.setVisible(false);
				}	
			});
			
		}

		private void createYesmenuButton() {
			yes = new JButton("Sim");
			
			yes.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
					menudialog.setVisible(false);
					try {
						if(timercpu!=null)
							timercpu.stop();
						gCon = 0;
						InitiateWindow();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			});
			
		}
}
