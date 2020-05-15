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
	
	private JMulti parent;
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
	private ArrayList<String> playercollection;
	private ArrayList<String> ennemicollection;
	private Client cme;
	private boolean host = false;
	public JMenuMulti(JMulti _parent,String s,boolean _host,Client c)
	{
		playercollection = new ArrayList<String>();
		ennemicollection = new ArrayList<String>();
		cme = c;
		initArray();
		this.host = _host;
		mypseudo = s;
		me = this;
		parent = _parent;
		this.setSize(parent.getWidth(),parent.getHeight());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponent();
		if(host)
		{
			setArray(playercollection,mypseudo);
			coop.setEnabled(true);
			jouer.setEnabled(true);
			pseudohost = s;
			refreshtable();
		}
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
	public void refreshtable()
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
	
	public void setArray(ArrayList<String> l,String s)
	{
		for(int i = 0;i < l.size();i++)
		{
			if(l.get(i).equals("----------")) {
				l.set(i, s);
				break;
			}
		}
	}
	

	public void setGoPlayer()
	{
		for(int i = 0;i < ennemicollection.size();i++)
		{
			if(ennemicollection.get(i).equals(mypseudo))
			{
				setArray(playercollection,mypseudo);
				removeArray(ennemicollection,mypseudo);
			}
			else if(!ennemicollection.get(i).equals("----------")) {
				setArray(playercollection,ennemicollection.get(i));
				removeArray(ennemicollection,ennemicollection.get(i));
			}
		}
	}
	
	public void removeArray(ArrayList<String> l,String s)
	{
		for(int i = 0;i < l.size();i++)
		{
			if(l.get(i).equals(s))
				l.set(i, "----------");
		}
	}
	
	public boolean placeArray(ArrayList<String> l)
	{
		boolean res = false;
		for(int i = 0;i < l.size();i++)
		{
			if(l.get(i).equals("----------"))
				res = true;
		}
		return res;
	}
	public boolean isInArray(ArrayList<String> l,String pse)
	{
		boolean res = false;
		for(int i = 0;i < l.size();i++)
		{
			if(l.get(i).equals(pse))
				res = true;
		}
		return res;
	}
	
	
	public int nbArray(ArrayList<String> l)
	{
		int res = 0;
		for(int i = 0;i < l.size();i++)
		{
			if(!l.get(i).equals("----------"))
				res++;
		}
		return res;
	}
	
	public void goennemi(String s)
	{
		if(!isInArray(ennemicollection,s) && placeArray(ennemicollection) && bequipe)
		{
			removeArray(playercollection,s);
			setArray(ennemicollection,s);
			refreshtable();		
			
		}
	}
	
	public void goplayer(String s)
	{
		if(!isInArray(playercollection,s)&& placeArray(playercollection))
		{
			removeArray(ennemicollection,s);
			setArray(playercollection,s);
			refreshtable();
			
		}
	}
	
	private void actionButton()
	{
		retour.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(host)
				{
					cme.setGame(false);
					cme.sendLine("stop");
					parent.getServ().close();
				}
				close();
				
			}
			
		});
		
		jouer.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(host)
				{
					cme.sendLine("Demarrer");
				}
			}
			
		});
		
		goennemi.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				goennemi(mypseudo);
				cme.sendLine("goennemi");
			}
		});
		goplayer.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				goplayer(mypseudo);
				cme.sendLine("goplayer");
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
					cme.sendLine("coop");
				}
			}
		});
		equipe.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!bequipe)
				{
					bequipe = true;
					coop.setEnabled(true);
					equipe.setEnabled(false);
					cme.sendLine("equipe");
				}
			}
		});
	}
	
	public void close()
	{
		me.dispose();
		parent.setVisible(true);
	}
	
	public void setTabpanel(JPanel tabpanel) {
		this.tabpanel = tabpanel;
	}
	public void setTabjoueur(JPanel tabjoueur) {
		this.tabjoueur = tabjoueur;
	}

	public JFrame getParent() {
		return parent;
	}

	public void setParent(JMulti parent) {
		this.parent = parent;
	}

	public JFrame getMe() {
		return me;
	}

	public void setMe(JFrame me) {
		this.me = me;
	}

	public MButton getCoop() {
		return coop;
	}

	public void setCoop(MButton coop) {
		this.coop = coop;
	}

	public MButton getEquipe() {
		return equipe;
	}

	public void setEquipe(MButton equipe) {
		this.equipe = equipe;
	}

	public MButton getGoennemi() {
		return goennemi;
	}

	public void setGoennemi(MButton goennemi) {
		this.goennemi = goennemi;
	}

	public MButton getGoplayer() {
		return goplayer;
	}

	public void setGoplayer(MButton goplayer) {
		this.goplayer = goplayer;
	}

	public MButton getJouer() {
		return jouer;
	}

	public void setJouer(MButton jouer) {
		this.jouer = jouer;
	}

	public MButton getRetour() {
		return retour;
	}

	public void setRetour(MButton retour) {
		this.retour = retour;
	}

	public JPanel getPpanel() {
		return ppanel;
	}

	public void setPpanel(JPanel ppanel) {
		this.ppanel = ppanel;
	}

	public JPanel getPanelmode() {
		return panelmode;
	}

	public void setPanelmode(JPanel panelmode) {
		this.panelmode = panelmode;
	}

	public JPanel getBpanel() {
		return bpanel;
	}

	public void setBpanel(JPanel bpanel) {
		this.bpanel = bpanel;
	}

	public JPanel getTabennemi() {
		return tabennemi;
	}

	public void setTabennemi(JPanel tabennemi) {
		this.tabennemi = tabennemi;
	}

	public JTable getJoueur() {
		return joueur;
	}

	public void setJoueur(JTable joueur) {
		this.joueur = joueur;
	}

	public JTable getEnnemi() {
		return ennemi;
	}

	public void setEnnemi(JTable ennemi) {
		this.ennemi = ennemi;
	}

	public JPanel getSwitchentity() {
		return switchentity;
	}

	public void setSwitchentity(JPanel switchentity) {
		this.switchentity = switchentity;
	}

	public String getPseudohost() {
		return pseudohost;
	}

	public void setPseudohost(String pseudohost) {
		this.pseudohost = pseudohost;
	}

	public String getMypseudo() {
		return mypseudo;
	}

	public void setMypseudo(String mypseudo) {
		this.mypseudo = mypseudo;
	}

	public boolean isBequipe() {
		return bequipe;
	}

	public void setBequipe(boolean bequipe) {
		this.bequipe = bequipe;
	}

	public ArrayList<String> getPlayercollection() {
		return playercollection;
	}

	public void setPlayercollection(ArrayList<String> playercollection) {
		this.playercollection = playercollection;
	}

	public ArrayList<String> getEnnemicollection() {
		return ennemicollection;
	}

	public void setEnnemicollection(ArrayList<String> ennemicollection) {
		this.ennemicollection = ennemicollection;
	}

	public Client getCme() {
		return cme;
	}

	public void setCme(Client cme) {
		this.cme = cme;
	}

	public boolean isHost() {
		return host;
	}

	public void setHost(boolean host) {
		this.host = host;
	}

	public JPanel getTabpanel() {
		return tabpanel;
	}

	public JPanel getTabjoueur() {
		return tabjoueur;
	}
	
}
