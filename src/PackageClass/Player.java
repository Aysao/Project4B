package PackageClass;

import PackageThreads.Menu;

/*
 * toute les fonction du joueur
 * interaction avec bloc et bordure interaction avec joueur Ennemi
 * 
 */
public class Player extends Entity {
	
	private int Vie = 3;
	private String pseudo;
	private Score scr = new Score();
	

	public Player(int x, int y) {
		super(x, y);
		Plateau.plateau[x][y] = this;
	}
	public Player(int x, int y,String str) {
		super(x, y);
		if(str.isEmpty()||str.isBlank())
		{
			setPseudo(str);
			scr.setName(str);
		}
		else
		{
			setPseudo(str);
			scr.setName(str);
		}	
	}
	
	
	public synchronized void Deplacement()
	{		
		
			if(this.getClass()==Player.class)
			{
				Player p = (Player)this;
				
				//System.out.println(p.getScr().getPoint());
				if((!(this.getPosX() == 1 && this.getOrientation() == WEST) || !(this.getPosX() == 13 && this.getOrientation() == EAST) ||
						!(this.getPosY() == 1 && this.getOrientation() == NORD) || !(this.getPosY() == 15 && this.getOrientation() == SOUTH)))
				{			
					switch (this.getOrientation()) {
						case SO: {						
						}break;

						case EAST: {
						
							//System.out.println("Class : " + Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass());
							if(Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass()==Bordure.class)
							{
								Bordure b = (Bordure)Plateau.plateau[this.getPosX()][this.getPosY()+1];
								Plateau.SwitchBorder(b);
							}
							else if(Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass() == String.class)
							{
								//System.out.println("this.getOrientation() : "+ this.getOrientation() );
								Plateau.refreshEntity(this); 
								this.setPosY(this.getPosY()+1) ;
								Plateau.refreshEntity(this);
							}
							//verif si bloc a bloc derrier lui 
							else if(Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass() == BlocN.class&&(Plateau.plateau[this.getPosX()][this.getPosY()+2].getClass() == BlocN.class||Plateau.plateau[this.getPosX()][this.getPosY()+2].getClass() == BlocSpe.class))
							{
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()+1) ;
								Plateau.refreshEntity(this);
								p.getScr().BlocDestroy();// modifi le score du joueur
							}
							else if(Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass() == BlocN.class&& (Plateau.plateau[this.getPosX()][this.getPosY()+2].getClass()== String.class || Plateau.plateau[this.getPosX()][this.getPosY()+2].getClass() == Ennemi.class))
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()][this.getPosY()+i].getClass()== String.class || Plateau.plateau[this.getPosX()][this.getPosY()+i].getClass() == Ennemi.class)
								{
									if(Plateau.plateau[this.getPosX()][this.getPosY()+i].getClass() == Ennemi.class)
									{
										Ennemi en = (Ennemi)Plateau.plateau[this.getPosX()][this.getPosY()+i];
										Menu.hmThreadE.get(en).stop();
										Menu.hmThreadE.remove(en);
										scr.mstKill();
										Plateau.plateau[this.getPosX()][this.getPosY()+i] = "0";
										Menu.newEnnemi();
									}
									i++;
								}
								i-=2;
								
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()+1) ;
								Plateau.plateau[this.getPosX()][this.getPosY()+i]=new BlocN(this.getPosX(),this.getPosY()+i);					
								Plateau.refreshEntity(this);								
							}
							else if(Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass() == BlocSpe.class&& (Plateau.plateau[this.getPosX()][this.getPosY()+2].getClass()== String.class || Plateau.plateau[this.getPosX()][this.getPosY()+2].getClass() == Ennemi.class))
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()][this.getPosY()+i].getClass()== String.class || Plateau.plateau[this.getPosX()][this.getPosY()+i].getClass() == Ennemi.class)
								{
									if(Plateau.plateau[this.getPosX()][this.getPosY()+i].getClass() == Ennemi.class)
									{
										Ennemi en = (Ennemi)Plateau.plateau[this.getPosX()][this.getPosY()+i];
										Menu.hmThreadE.get(en).stop();
										Menu.hmThreadE.remove(en);
										scr.mstKill();
										Plateau.plateau[this.getPosX()][this.getPosY()+i] = "0";
										Menu.newEnnemi();
									}
									i++;
								}
								i-=2;
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()+1) ;
								Plateau.plateau[this.getPosX()][this.getPosY()+i]=new BlocSpe(this.getPosX(),this.getPosY()+i);					
								Plateau.refreshEntity(this);								
							}
							else if(Plateau.plateau[this.getPosX()][this.getPosY()+1].getClass() == Ennemi.class)
							{
								Ennemi en = (Ennemi)Plateau.plateau[this.getPosX()][this.getPosY()+1];
								if(en.stun)
								{
									Plateau.refreshEntity(this);
									Plateau.refreshEntity(en);
									Menu.hmThreadE.get(en).stop();
									Menu.hmThreadE.remove(en);
									this.setPosY(this.getPosY()+1);
									Plateau.refreshEntity(en);
									Plateau.refreshEntity(this);
									this.getScr().mstKill();					
									Menu.newEnnemi();
								}
								else
								{
									Plateau.refreshEntity(this);
									if(Vie-1 >= 0)
									{
										Vie--;
										this.setPosX(7);
										this.setPosY(7);	
									}
									Plateau.refreshEntity(this);
								}									
							}
							
						}break;

						case WEST: {

							//System.out.println("Class : " + Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass());
							if(Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass()==Bordure.class)
							{
								Bordure b = (Bordure)Plateau.plateau[this.getPosX()][this.getPosY()-1];
								Plateau.SwitchBorder(b);
							}

							if(Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass() == String.class)
							{
								//System.out.println("this.getOrientation() : "+ this.getOrientation() );
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()-1) ;
								Plateau.refreshEntity(this);
							}
							//verif si bloc a bloc derrier lui 
							else if(Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass() == BlocN.class&&(Plateau.plateau[this.getPosX()][this.getPosY()-2].getClass() == BlocN.class||Plateau.plateau[this.getPosX()][this.getPosY()-2].getClass() == BlocSpe.class))
							{
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()-1) ;
								Plateau.refreshEntity(this);
								p.getScr().BlocDestroy();
							}
							else if(Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass() == BlocN.class&&(Plateau.plateau[this.getPosX()][this.getPosY()-2].getClass()== String.class|| Plateau.plateau[this.getPosX()][this.getPosY()-2].getClass() == Ennemi.class))
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()][this.getPosY()-i].getClass()== String.class || Plateau.plateau[this.getPosX()][this.getPosY()-i].getClass() == Ennemi.class)
								{
									if(Plateau.plateau[this.getPosX()][this.getPosY()-i].getClass() == Ennemi.class)
									{
										Ennemi en = (Ennemi)Plateau.plateau[this.getPosX()][this.getPosY()-i];
										Menu.hmThreadE.get(en).stop();
										Menu.hmThreadE.remove(en);
										scr.mstKill();
										Plateau.plateau[this.getPosX()][this.getPosY()-i] = "0";
										Menu.newEnnemi();
									}
									i++;
								}
								i-=2;
								
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()-1) ;
								Plateau.plateau[this.getPosX()][this.getPosY()-i]=new BlocN(this.getPosX(),this.getPosY()-i);					
								Plateau.refreshEntity(this);						
							}
							else if(Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass() == BlocSpe.class&& (Plateau.plateau[this.getPosX()][this.getPosY()-2].getClass()== String.class || Plateau.plateau[this.getPosX()][this.getPosY()-2].getClass() == Ennemi.class))
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()][this.getPosY()-i].getClass()== String.class || Plateau.plateau[this.getPosX()][this.getPosY()-i].getClass() == Ennemi.class)
								{
									if(Plateau.plateau[this.getPosX()][this.getPosY()-i].getClass() == Ennemi.class)
									{
										Ennemi en = (Ennemi)Plateau.plateau[this.getPosX()][this.getPosY()-i];
										Menu.hmThreadE.get(en).stop();
										Menu.hmThreadE.remove(en);
										scr.mstKill();
										Plateau.plateau[this.getPosX()][this.getPosY()-i] = "0";
										Menu.newEnnemi();
									}
									i++;
								}
								i-=2;
								
								Plateau.refreshEntity(this);
								this.setPosY(this.getPosY()-1) ;
								Plateau.plateau[this.getPosX()][this.getPosY()-i]=new BlocSpe(this.getPosX(),this.getPosY()-i);					
								Plateau.refreshEntity(this);						
							}
							else if(Plateau.plateau[this.getPosX()][this.getPosY()-1].getClass() == Ennemi.class)
							{
								Ennemi en = (Ennemi)Plateau.plateau[this.getPosX()][this.getPosY()-1];
								if(en.stun)
								{
									Plateau.refreshEntity(this);
									Plateau.refreshEntity(en);
									Menu.hmThreadE.get(en).stop();
									Menu.hmThreadE.remove(en);
									this.setPosY(this.getPosY()-1);
									Plateau.refreshEntity(en);
									Plateau.refreshEntity(this);
									this.getScr().mstKill();
									Menu.newEnnemi();
								}
								else
								{
									Plateau.refreshEntity(this);
									if(Vie-1 >= 0)
									{
										Vie--;
										this.setPosX(7);
										this.setPosY(7);	
									}
									Plateau.refreshEntity(this);
								}								
							}
							
						}break;

						case NORD: {
								
							if(Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass()==Bordure.class)
							{
								Bordure b = (Bordure)Plateau.plateau[this.getPosX()-1][this.getPosY()];
								Plateau.SwitchBorder(b);
							}
							if(Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass() == String.class)
							{
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()-1) ;
								Plateau.refreshEntity(this);
							}
							//verif si bloc a bloc derrier lui 
							else if(Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass() == BlocN.class&&(Plateau.plateau[this.getPosX()-2][this.getPosY()].getClass() == BlocN.class||Plateau.plateau[this.getPosX()-2][this.getPosY()].getClass() == BlocSpe.class))
							{
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()-1) ;
								Plateau.refreshEntity(this);
								p.getScr().BlocDestroy();
							}
							else if(Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass() == BlocN.class&& (Plateau.plateau[this.getPosX()-2][this.getPosY()].getClass()== String.class|| Plateau.plateau[this.getPosX()-2][this.getPosY()].getClass() == Ennemi.class))
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()-i][this.getPosY()].getClass()== String.class || Plateau.plateau[this.getPosX()-i][this.getPosY()].getClass() == Ennemi.class)
								{
									if(Plateau.plateau[this.getPosX()-i][this.getPosY()].getClass() == Ennemi.class)
									{
										Ennemi en = (Ennemi)Plateau.plateau[this.getPosX()-i][this.getPosY()];
										Menu.hmThreadE.get(en).stop();
										Menu.hmThreadE.remove(en);
										scr.mstKill();
										Plateau.plateau[this.getPosX()-i][this.getPosY()] = "0";
										Menu.newEnnemi();
									}
									i++;
								}
								i-=2;
								
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()-1) ;
								Plateau.plateau[this.getPosX()-i][this.getPosY()]=new BlocN(this.getPosX()-i,this.getPosY());					
								Plateau.refreshEntity(this);								
							}
							else if(Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass() == BlocSpe.class&& (Plateau.plateau[this.getPosX()-2][this.getPosY()].getClass()== String.class || Plateau.plateau[this.getPosX()-2][this.getPosY()].getClass() == Ennemi.class))
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()-i][this.getPosY()].getClass()== String.class || Plateau.plateau[this.getPosX()-i][this.getPosY()].getClass() == Ennemi.class)
								{
									if(Plateau.plateau[this.getPosX()-i][this.getPosY()].getClass() == Ennemi.class)
									{
										Ennemi en = (Ennemi)Plateau.plateau[this.getPosX()-i][this.getPosY()];
										Menu.hmThreadE.get(en).stop();
										Menu.hmThreadE.remove(en);
										scr.mstKill();
										Plateau.plateau[this.getPosX()-i][this.getPosY()] = "0";
										Menu.newEnnemi();
									}
									i++;
								}
								i-=2;
								
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()-1) ;
								Plateau.plateau[this.getPosX()-i][this.getPosY()]=new BlocSpe(this.getPosX()-i,this.getPosY());					
								Plateau.refreshEntity(this);								
							}
							else if(Plateau.plateau[this.getPosX()-1][this.getPosY()].getClass() == Ennemi.class)
							{
								Ennemi en = (Ennemi)Plateau.plateau[this.getPosX()-1][this.getPosY()];
								if(en.stun)
								{
									Plateau.refreshEntity(this);
									Plateau.refreshEntity(en);
									Menu.hmThreadE.get(en).stop();
									Menu.hmThreadE.remove(en);
									this.setPosX(this.getPosX()-1);
									Plateau.refreshEntity(en);
									Plateau.refreshEntity(this);
									this.getScr().mstKill();
									Menu.newEnnemi();
								}
								else
								{
									Plateau.refreshEntity(this);
									if(Vie-1 >= 0)
									{
										Vie--;
										this.setPosX(7);
										this.setPosY(7);	
									}
									Plateau.refreshEntity(this);
								}								
							}
							
						}break;

						case SOUTH: {	
							if(Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass()==Bordure.class)
							{
								Bordure b = (Bordure)Plateau.plateau[this.getPosX()+1][this.getPosY()];
								Plateau.SwitchBorder(b);
							}
							if(Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass() == String.class)
							{
								//System.out.println("this.getOrientation() : "+ this.getOrientation() );
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()+1) ;
								Plateau.refreshEntity(this);
							}
							//verif si bloc a bloc derrier lui 
							else if(Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass() == BlocN.class&&(Plateau.plateau[this.getPosX()+2][this.getPosY()].getClass() == BlocN.class||Plateau.plateau[this.getPosX()+2][this.getPosY()].getClass() == BlocSpe.class))
							{
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()+1) ;
								Plateau.refreshEntity(this);
								p.getScr().BlocDestroy();
							}
							else if(Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass() == BlocN.class&& (Plateau.plateau[this.getPosX()+2][this.getPosY()].getClass()== String.class|| Plateau.plateau[this.getPosX()+2][this.getPosY()].getClass() == Ennemi.class))
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()+i][this.getPosY()].getClass()== String.class || Plateau.plateau[this.getPosX()+i][this.getPosY()].getClass() == Ennemi.class)
								{
									if(Plateau.plateau[this.getPosX()+i][this.getPosY()].getClass() == Ennemi.class)
									{
										Ennemi en = (Ennemi)Plateau.plateau[this.getPosX()+i][this.getPosY()];
										Menu.hmThreadE.get(en).stop();
										Menu.hmThreadE.remove(en);
										scr.mstKill();
										Plateau.plateau[this.getPosX()+i][this.getPosY()] = "0";
										Menu.newEnnemi();
									}
									i++;
								}
								i-=2;							
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()+1) ;
								Plateau.plateau[this.getPosX()+i][this.getPosY()]=new BlocN(this.getPosX()+i,this.getPosY());					
								Plateau.refreshEntity(this);												
							}	
							else if(Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass() == BlocSpe.class&& (Plateau.plateau[this.getPosX()+2][this.getPosY()].getClass()== String.class || Plateau.plateau[this.getPosX()+2][this.getPosY()].getClass() == Ennemi.class))
							{
								int i = 2;
								while(Plateau.plateau[this.getPosX()+i][this.getPosY()].getClass()== String.class || Plateau.plateau[this.getPosX()+i][this.getPosY()].getClass() == Ennemi.class)
								{
									if(Plateau.plateau[this.getPosX()+i][this.getPosY()].getClass() == Ennemi.class)
									{
										Ennemi en = (Ennemi)Plateau.plateau[this.getPosX()+i][this.getPosY()];
										Menu.hmThreadE.get(en).stop();
										Menu.hmThreadE.remove(en);
										scr.mstKill();
										Plateau.plateau[this.getPosX()+i][this.getPosY()] = "0";
										Menu.newEnnemi();
									}
									i++;
								}
								i-=2;							
								Plateau.refreshEntity(this);
								this.setPosX(this.getPosX()+1);
								Plateau.plateau[this.getPosX()+i][this.getPosY()]=new BlocSpe(this.getPosX()+i,this.getPosY());					
								Plateau.refreshEntity(this);												
							}
							else if(Plateau.plateau[this.getPosX()+1][this.getPosY()].getClass() == Ennemi.class)
							{
								
								Ennemi en = (Ennemi)Plateau.plateau[this.getPosX()+1][this.getPosY()];
								if(en.stun)
								{				
									Plateau.refreshEntity(this);
									Plateau.refreshEntity(en);
									Menu.hmThreadE.get(en).stop();
									Menu.hmThreadE.remove(en);
									this.setPosX(this.getPosX()+1);
									Plateau.refreshEntity(en);
									Plateau.refreshEntity(this);
									this.getScr().mstKill();
									Menu.newEnnemi();
								}
								else
								{
									Plateau.refreshEntity(this);
									if(Vie-1 >= 0)
									{
										Vie--;
										this.setPosX(7);
										this.setPosY(7);	
									}
									Plateau.refreshEntity(this);
								}																
							}
						}break;					
					}				
				}
			}	
	}
	public boolean nextToEdge()
	{
		if(this.getPosX() == 1 && this.getOrientation() == EAST)
		{
			return true;
		}
		if(this.getPosX() == 13 && this.getOrientation() == WEST)
		{
			return true;
		}
		if(this.getPosY() == 1 && this.getOrientation() == NORD)
		{
			return true;
		}
		if(this.getPosY() == 15 && this.getOrientation() == SOUTH)
		{
			return true;
		}
		else 
		{
			return false;
		}
		
	}
	public void setVie(int vie) {
		Vie = vie;
	}
	
	public int getVie() {
		return Vie;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String ps) {
		this.pseudo = ps;
		scr.setName(ps);
	}
	public void setScr(Score scr) {
		this.scr = scr;
	}
	public Score getScr() {
		return scr;
	}
	
	 
	
} 
