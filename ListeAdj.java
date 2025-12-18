import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ListeAdj {
    public int sommet;
    public int valeur;  // Pour les graphes valués
    public ListeAdj suivant;
    
    // Constructeur
    public ListeAdj(int sommet, ListeAdj suivant) {
        this.sommet = sommet;
        this.valeur = valeur = 1; // Valeur par défaut pour les graphes non valués
        this.suivant = suivant;
    }

       public ListeAdj(int sommet, int valeur, ListeAdj suivant) {
        this.sommet = sommet;
        this.valeur = valeur;
        this.suivant = suivant;
    }
    
    // Getters
    public int getSommet() {
        return this.sommet;
    }
    
    public int getValeur() {
        return this.valeur;
    }
    
    public ListeAdj getSuivant() {
        return this.suivant;
    }
    
    // Fonction pour vérifier si une liste contient un sommet
    static boolean contient(ListeAdj liste, int sommet) {
        ListeAdj current = liste;
        while (current != null) {
            if (current.getSommet() == sommet) {
                return true;
            }
            current = current.getSuivant();
        }
        return false;
    }

    //1.fonction qui attend en entrée une représentation d'un graphe et retourne 'vrai ' si le graphe est orienté, 'faux ' sinon.
    static boolean estOriente(ListeAdj[] listeAdj) {
        int n = listeAdj.length;
        
        // Pour chaque sommet i
        for (int i = 0; i < n; i++) {
            // Parcourir ses successeurs
            ListeAdj current = listeAdj[i];
            while (current != null) {
                int j = current.getSommet();
                
                // Vérifier si j a i comme successeur
                boolean jPointeVersI = contient(listeAdj[j], i);
                
                // Si i pointe vers j mais j ne pointe pas vers i
                if (!jPointeVersI) {
                    return true; // Graphe orienté
                }
                
                current = current.getSuivant();
            }
        }
        
        return false; // Graphe non orienté (symétrique)
    }

    //2.fonction pour savoir si le graphe est valué
    static boolean estValue(ListeAdj[] listeAdj) {
        // Parcourir toutes les listes
        for (int i = 0; i < listeAdj.length; i++) {
            ListeAdj current = listeAdj[i];
            
            // Parcourir les successeurs
            while (current != null) {
                // Si on trouve une valeur > 1, le graphe est valué
                if (current.getValeur() > 1) {
                    return true;
                }
                current = current.getSuivant();
            }
        }
        
        return false; // Aucune valeur > 1 trouvée
    }


    //3.fonction qui étant donné un graphe et un sommet, retourne le degré sortant/entrant de ce sommet
    static int degreeSortant(ListeAdj[] listeAdj, int sommet) {
        int deg = 0;
        ListeAdj current = listeAdj[sommet];
        
        while (current != null) {
            deg++;
            current = current.getSuivant();
        }
        
        return deg;
    }
    
    static int degreeEntrant(ListeAdj[] listeAdj, int sommet) {
        int deg = 0;
        
        // Parcourir tous les sommets
        for (int i = 0; i < listeAdj.length; i++) {
            // Vérifier si i a sommet comme successeur
            if (contient(listeAdj[i], sommet)) {
                deg++;
            }
        }
        
        return deg;
    }

    //4.fonction qui étant donné un graphe, retourne le degré sortant minimum/maximum d'un sommet du graphe.
    static int degreeSortantMin(ListeAdj[] listeAdj) {
        int min = Integer.MAX_VALUE;
        
        for (int i = 0; i < listeAdj.length; i++) {
            int deg = degreeSortant(listeAdj, i);
            min = Math.min(min, deg);
        }
        
        return min;
    }
    
    static int degreeSortantMax(ListeAdj[] listeAdj) {
        int max = 0;
        
        for (int i = 0; i < listeAdj.length; i++) {
            int deg = degreeSortant(listeAdj, i);
            max = Math.max(max, deg);
        }
        
        return max;
    }
    
    // 5.fonction qui étant donné un graphe et un sommet, retourne l'ensemble des successeurs/prédécesseurs du sommet.
    static int[] successeurs(ListeAdj[] listeAdj, int sommet) {
        // Compter le nombre de successeurs
        int count = degreeSortant(listeAdj, sommet);
        
        // Créer le tableau
        int[] result = new int[count];
        ListeAdj current = listeAdj[sommet];
        int index = 0;
        
        while (current != null) {
            result[index++] = current.getSommet();
            current = current.getSuivant();
        }
        
        return result;
    }
    
    static int[] predecesseurs(ListeAdj[] listeAdj, int sommet) {
        // Compter le nombre de prédécesseurs
        int count = degreeEntrant(listeAdj, sommet);
        
        // Créer le tableau
        int[] result = new int[count];
        int index = 0;
        
        for (int i = 0; i < listeAdj.length; i++) {
            if (contient(listeAdj[i], sommet)) {
                result[index++] = i;
            }
        }
        
        return result;
    }
    
    // 6.fonction qui étant donné un graphe et deux sommets, retourne 'vrai ' si les deux sommets ont un voisin en commun, 'faux ' sinon.
    static boolean voisinCommun(ListeAdj[] listeAdj, int sommet1, int sommet2) {
        // Vérifier les successeurs communs
        ListeAdj current1 = listeAdj[sommet1];
        while (current1 != null) {
            int succ = current1.getSommet();
            if (contient(listeAdj[sommet2], succ)) {
                return true;
            }
            current1 = current1.getSuivant();
        }
        
        // Vérifier les prédécesseurs communs
        for (int i = 0; i < listeAdj.length; i++) {
            if (contient(listeAdj[i], sommet1) && contient(listeAdj[i], sommet2)) {
                return true;
            }
        }
        
        return false;
    }
    
    //7.fonction qui étant donné un graphe, retourne 'vrai ' si il existe une entrée/sortie, 'faux ' sinon.
    static boolean entree(ListeAdj[] listeAdj) {
        for (int i = 0; i < listeAdj.length; i++) {
            if (degreeEntrant(listeAdj, i) == 0) {
                return true;
            }
        }
        return false;
    }
    
    static boolean sortie(ListeAdj[] listeAdj) {
        for (int i = 0; i < listeAdj.length; i++) {
            if (degreeSortant(listeAdj, i) == 0) {
                return true;
            }
        }
        return false;
    }

    //8.fonction qui étant donné un graphe, retourne 'vrai ' si il existe un sommet universel, 'faux ' sinon.
    static boolean universel(ListeAdj[] listeAdj) {
        int n = listeAdj.length;
        
        for (int i = 0; i < n; i++) {
            // Compter les successeurs
            int nbSuccesseurs = degreeSortant(listeAdj, i);
            
            // Un sommet universel doit avoir (n-1) successeurs
            if (nbSuccesseurs == n - 1) {
                return true;
            }
        }
        
        return false;
    }
    
   // 9.fonction qui attend en entrée un graphe et le chemin vers un fichier vers lequel sera exporté la représentation du graphe.
static void exporter(ListeAdj[] listeAdj, String fichier) {
    try {
        FileWriter monFichier = new FileWriter(fichier);
        
        // Première ligne : identifier le format
        monFichier.write("ListeAdj\n");
        
        // Écrire chaque liste d'adjacence
        for (int i = 0; i < listeAdj.length; i++) {
            ListeAdj current = listeAdj[i];
            
            // Écrire le numéro du sommet
            monFichier.write(i + "");
            
            // Écrire ses successeurs
            while (current != null) {
                monFichier.write(" " + current.getSommet());
                current = current.getSuivant();
            }
            
            monFichier.write("\n");
        }
        
        monFichier.close();
        System.out.println("Export réussi vers : " + fichier);
        
    } catch (IOException e) {
        System.out.println("Erreur lors de l'export : " + e.getMessage());
        e.printStackTrace();
    }
}

//10.fonction qui attend en entrée le chemin d'un fichier au format 'ListeAdj ' et retourne la liste d'adjacence.
static ListeAdj[] importer(String fichier) {
    try {
        File file = new File(fichier);
        Scanner scanner = new Scanner(file);
        
        // Lire la première ligne pour vérifier le format
        String format = scanner.nextLine();
        if (!format.equals("ListeAdj")) {
            System.out.println("Format de fichier incorrect : " + format);
            scanner.close();
            return null;
        }
        
        // Compter le nombre de lignes
        int taille = 0;
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            taille++;
        }
        scanner.close();
        
        // Relire le fichier pour construire les listes
        scanner = new Scanner(file);
        scanner.nextLine(); // Sauter la première ligne
        
        ListeAdj[] listeAdj = new ListeAdj[taille];
        
        for (int i = 0; i < taille; i++) {
            String ligne = scanner.nextLine();
            String[] parties = ligne.split("\\s+");
            
            // Le premier élément est l'index du sommet (on le saute)
            // Les suivants sont les successeurs
            if (parties.length > 1) {
                // Construire la liste chaînée des successeurs
                ListeAdj liste = null;
                
                // Parcourir de la fin vers le début pour construire la liste
                for (int j = parties.length - 1; j >= 1; j--) {
                    int successeur = Integer.parseInt(parties[j]);
                    liste = new ListeAdj(successeur, liste);
                }
                
                listeAdj[i] = liste;
            } else {
                // Pas de successeurs
                listeAdj[i] = null;
            }
        }
        
        scanner.close();
        System.out.println("Import réussi depuis : " + fichier);
        return listeAdj;
        
    } catch (FileNotFoundException e) {
        System.out.println("Fichier non trouvé : " + fichier);
        e.printStackTrace();
        return null;
    }
}
}