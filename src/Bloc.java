
/*
 * bloc abstract qui permet de donné les caractèristique similaire au bloc Spéciaux et Normal
 * elle sera un thread qui s'activera des que le joueur aura cliqué dessus
 * et se stoppera a chaque fois qu'elle rencontrera un autre bloc ou la bordure 
 * les bloc seront généré aleatoirement ? 
 * cependant un chemin devra etre possible pour la circulation du personnage
 */
public abstract class Bloc {
	
	private int etat;
	private int orientation;
	
	public void deplacementBloc() {
		
	}
}
