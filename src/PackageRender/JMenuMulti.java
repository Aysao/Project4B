package PackageRender;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import PackageThreads.Client;
import PackageThreads.Serveur;

@SuppressWarnings("serial")
public class JMenuMulti extends JFrame {
	
	private JFrame parent;
	private JFrame me;
	private MButton coop;
	private MButton equipe;
	private MButton goennemi;
	private MButton goplayer;
	private MButton jouer;
	private MButton retour;
	private JPanel ppanel;
	private JPanel panelmode;
	private JPanel bpanel;
	private JPanel tabpanel;
	private JPanel tabjoueur;
	private JPanel tabennemi;
	private JTable joueur;
	private JTable ennemi;
	private JPanel switchentity;
	private String pseudohost = "";
	private String mypseudo = "";
	private boolean bequipe = true;
	private boolean pingoin = true; // on est du coté des pinguoin
	private ArrayList<String> playercollection;
	private ArrayList<String> ennemicollection;
	private Client c;
	private boolean host = false;
	private Serveur serv;
	public JMenuMulti(JFrame _parent,String s,boolean _host)
	{
		serv = new Serveur();
		playercollection = new ArrayList<String>();
		ennemicollection = new ArrayList<String>();
		initArray();
		playercollection.set(0, s);
		this.host = _host;
		pseudohost = s;
		mypseudo = s;
		me = this;
		parent = _parent;
		this.setSize(parent.getWidth(),parent.getHeight());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponent();
		coop.setEnabled(true);
		jouer.setEnabled(true);
		c = new Client(s);
	}
	public JMenuMulti(JFrame _parent,String s)
	{
		playercollection = new ArrayList<String>();
		ennemicollection = new ArrayList<String>();
		initArray();
		playercollection.set(0, s);
		mypseudo = s;
		me = this;
		parent = _parent;
		this.setSize(parent.getWidth(),parent.getHeight());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponent();		
		c = new Client(s);
	}
	
	private void initComponent()
	{
		initTable();
		
		switchentity = new JPanel();
		switchentity.setLayout(new GridLayout(2,1));
		
		ppanel = new JPanel();
		ppanel.setLayout(new BorderLayout());
		
		panelmode = new JPanel();
		panelmode.setLayout(new GridLayout(1,2));
		
		tabpanel = new JPanel();
		tabpanel.setLayout(new GridLayout(1,3));
		
		bpanel = new JPanel();
		bpanel.setLayout(new GridLayout(1,2));
		
		coop = new MButton("Image/GlaceBlock.png");
		equipe = new MButton("Image/GlaceBlock.png");
		goennemi = new MButton("Image/GlaceBlock.png");
		goplayer = new MButton("Image/GlaceBlock.png");
		jouer = new MButton("Image/GlaceBlock.png");
		retour = new MButton("Image/GlaceBlock.png");
		
		coop.setPressed("Image/click.png");
		equipe.setPressed("Image/click.png");
		goennemi.setPressed("Image/click.png");
		goplayer.setPressed("Image/click.png");
		jouer.setPressed("Image/click.png");
		retour.setPressed("Image/click.png");
		
		coop.setRollover("Image/blurGlaceBlock.png");
		equipe.setRollover("Image/blurGlaceBlock.png");
		goennemi.setRollover("Image/blurGlaceBlock.png");
		goplayer.setRollover("Image/blurGlaceBlock.png");
		jouer.setRollover("Image/blurGlaceBlock.png");
		retour.setRollover("Image/blurGlaceBlock.png");
		
		coop.setText("Coopération");
		equipe.setText("Equipe");
		goennemi.setText("-->");
		goplayer.setText("<--");
		jouer.setText("Demarrer");
		retour.setText("Retour");
		
		equipe.setEnabled(false);
		coop.setEnabled(false);
		jouer.setEnabled(false);
		
		panelmode.add(coop);
		panelmode.add(equipe);
		
		bpanel.add(retour);
		bpanel.add(jouer);
		
		switchentity.add(goplayer);
		switchentity.add(goennemi);
		
		tabpanel.add(tabjoueur);
		tabpanel.add(switchentity);
		tabpanel.add(tabennemi);
		
		ppanel.add(panelmode,BorderLayout.NORTH);
		ppanel.add(tabpanel,BorderLayout.CENTER);
		ppanel.add(bpanel,BorderLayout.SOUTH);
		
		this.getContentPane().add(ppanel);
		actionButton();
	}
	
	private void initArray()
	{
		for(int i = 0;i < 4;i++)
		{
			playercollection.add("----------");
			ennemicollection.add("----------");
		}
	}
	
	private void initTable()
	{
		tabjoueur = new JPanel();
		tabjoueur.setLayout(new BorderLayout());
		tabennemi = new JPanel();
		tabennemi.setLayout(new BorderLayout());
		
		String[][] players = new String[4][1];
		String[][] enneminame = new String[4][1];
		initTableValue(players,enneminame);
		String[] teteplayer = {"Pingoin"};
		String[] teteennemi = {"Ennemi"};
		DefaultTableModel dtmplayer = new DefaultTableModel(players,teteplayer);
		DefaultTableModel dtmennemi = new DefaultTableModel(enneminame,teteennemi);
		joueur = new JTable(dtmplayer);
		ennemi = new JTable(dtmennemi);
		tabjoueur.add(joueur.getTableHeader(),BorderLayout.NORTH);
		tabjoueur.add(joueur,BorderLayout.CENTER);
		tabennemi.add(ennemi.getTableHeader(),BorderLayout.NORTH);
		tabennemi.add(ennemi,BorderLayout.CENTER);
		
	}
	private void initTableValue(String[][] splayer,String[][] sennemi)
	{
		for(int i = 0;i<4;i++)
		{
			splayer[i][0] = playercollection.get(i);
			sennemi[i][0] = ennemicollection.get(i);
		}
	}
	private void refreshtable()
	{
		for(int i = 0;i < playercollection.size();i++)
		{
			joueur.getModel().setValueAt(playercollection.get(i), i, 0);
		}
		for(int i = 0;i < ennemicollection.size();i++)
		{
			ennemi.getModel().setValueAt(ennemicollection.get(i), i, 0);
		}
	}
	
	private void setArray(ArrayList<String> l,String s)
	{
		for(int i = 0;i < l.size();i++)
		{
			if(l.get(i).equals("----------")) {
				l.set(i, s);
				break;
			}
		}
	}
	
	private void setGoPlayer()
	{
		for(int i = 0;i < ennemicollection.size();i++)
		{
			if(ennemicollection.get(i).equals(mypseudo))
			{
				setArray(playercollection,mypseudo);
				removeArray(ennemicollection,mypseudo);
				pingoin = true;
			}
			else if(!ennemicollection.get(i).equals("----------")) {
				setArray(playercollection,ennemicollection.get(i));
				removeArray(ennemicollection,ennemicollection.get(i));
			}
		}
	}
	
	private void removeArray(ArrayList<String> l,String s)
	{
		for(int i = 0;i < l.size();i++)
		{
			if(l.get(i).equals(s))
				l.set(i, "----------");
		}
	}
	
	private boolean placeArray(ArrayList<String> l)
	{
		boolean res = false;
		for(int i = 0;i < l.size();i++)
		{
			if(l.get(i).equals("----------"))
				res = true;
		}
		return res;
	}
	private int nbArray(ArrayList<String> l)
	{
		int res = 0;
		for(int i = 0;i < l.size();i++)
		{
			if(!l.get(i).equals("----------"))
				res++;
		}
		return res;
	}
	private void actionButton()
	{
		retour.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				me.dispose();
				parent.setVisible(true);
			}
			
		});
		goennemi.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pingoin && placeArray(ennemicollection) && bequipe)
				{
					removeArray(playercollection,mypseudo);
					setArray(ennemicollection,mypseudo);
					pingoin = false;
					refreshtable();					
				}
			}
		});
		goplayer.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!pingoin && placeArray(playercollection))
				{
					removeArray(ennemicollection,mypseudo);
					setArray(playercollection,mypseudo);
					pingoin = true;
					refreshtable();
				}
			}
		});
		coop.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(bequipe && nbArray(playercollection)+nbArray(ennemicollection) < 5)
				{
					setGoPlayer();
					bequipe = false;
					coop.setEnabled(false);
					equipe.setEnabled(true);
					refreshtable();
				}
			}
		});
		equipe.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!bequipe && nbArray(playercollection)+nbArray(ennemicollection) < 5)
				{
					bequipe = true;
					coop.setEnabled(true);
					equipe.setEnabled(false);
					//refreshtable();
				}
			}
		});
	}
}
