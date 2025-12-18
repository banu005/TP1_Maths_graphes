public class ListeAdj {
        public int sommet;
        public ListeAdj suivant;

        //constructeur
        public ListeAdj( int sommet, ListeAdj suivant) {
            this.sommet = sommet;
            this.suivant = suivant;
        }

        //getter et setters
        public int getSommet() {
            return this.sommet;
        }
        public ListeAdj getSuivant() {
            return this.suivant;
}
  //fonction qui attend en entrée une représentation d’un graphe et retourne ’vrai’ si le graphe est orienté, ’faux ’ sinon.
   static boolean estOriente(ListeAdj[] listeAdj){

    while (listeAdj.length > 1) { 
         // on compare les sommets des deux premières listes d'adjacence, si ils sont différents, le graphe est orienté sinon non
        if(listeAdj[0].getSommet() != listeAdj[1].getSommet()){ 
       return true;
    }
   }
    return false;
}
}