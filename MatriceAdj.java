import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MatriceAdj {
    int[][] matrice;
    
    // Constructeur
    public MatriceAdj(int[][] matrice) {
        this.matrice = matrice;
    }
    
    // Getters et setters
    public int[][] getMatrice() {
        return matrice;
    }
    
    public void setMatrice(int[][] matrice) {
        this.matrice = matrice;
    }
    
    //1.fonction qui attend en entrée une représentation d’un graphe et retourne ’vrai’ si le graphe est orienté, ’faux ’ sinon.
    public static boolean estOriente(int[][] matrice) {
        int m = matrice.length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                // if pour vérifier si le graphe est orienté
                if (matrice[i][j] != matrice[j][i]) {
                    return true;
                }
            }
        }
        return false;
    }
    
    //2.fonction qui attend en entrée une matrice d’adjacence et retourne ’vrai’ si le graphe est valué, ’faux ’ sinon.
    public static boolean estValue(int[][] matrice) {
        int m = matrice.length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                // if pour vérifier si le graphe est valué
                if (matrice[i][j] > 1) {
                    return true;
                }
            }
        }
        return false;
    }
    
    //3.fonction qui étant donné un graphe et un sommet, retourne le degré sortant/entrant de ce sommet
    public static int degreeSortant(int[][] matrice, int sommet) {
        int deg = 0;
        int m = matrice.length;
        for (int j = 0; j < m; j++) {
            if (matrice[sommet][j] != 0) deg++;
        }
        return deg;
    }
    
    public static int degreeEntrant(int[][] matrice, int sommet) {
        int deg = 0;
        int m = matrice.length;
        for (int i = 0; i < m; i++) {
            if (matrice[i][sommet] != 0) deg++;
        }
        return deg;
    }

    // 4.fonction qui étant donné un graphe, retourne le degré sortant minimum/maximum d’un sommet du graphe.
    public static int degreeSortantMin(int[][] matrice) {
        int min = Integer.MAX_VALUE;
        int m = matrice.length;
        for (int i = 0; i < m; i++) {
            min = Math.min(min, degreeSortant(matrice, i));
        }
        return min;
    }
    
    public static int degreeSortantMax(int[][] matrice) {
        int max = 0;
        int m = matrice.length;
        for (int i = 0; i < m; i++) {
            max = Math.max(max, degreeSortant(matrice, i));
        }
        return max;
    }

    // 5.fonction qui étant donné un graphe et un sommet, retourne l’ensemble des successeurs/prédécesseurs du sommet.
    public static int[] successeurs(int[][] matrice, int sommet) {
        // Compter d'abord le nombre de successeurs
        int count = 0;
        for (int j = 0; j < matrice.length; j++) {
            if (matrice[sommet][j] != 0) count++;
        }
        
        // Créer le tableau de la bonne taille
        int[] result = new int[count];
        int index = 0;
        for (int j = 0; j < matrice.length; j++) {
            if (matrice[sommet][j] != 0) {
                result[index++] = j;
            }
        }
        return result;
    }

    public static int[] predecesseurs(int[][] matrice, int sommet) {
        // Compter d'abord le nombre de prédécesseurs
        int count = 0;
        for (int i = 0; i < matrice.length; i++) {
            if (matrice[i][sommet] != 0) count++;
        }
        
        // Créer le tableau de la bonne taille
        int[] result = new int[count];
        int index = 0;
        for (int i = 0; i < matrice.length; i++) {
            if (matrice[i][sommet] != 0) {
                result[index++] = i;
            }
        }
        return result;
    }
    
    //6.fonction qui étant donné un graphe et deux sommets, retourne ’vrai ’ si les deux sommets ont un voisin en commun, ’faux ’ sinon.
    public static boolean voisinCommun(int[][] matrice, int sommet1, int sommet2) {
        int n = matrice.length;
        
        // Vérifier les successeurs communs
        for (int i = 0; i < n; i++) {
            if (matrice[sommet1][i] != 0 && matrice[sommet2][i] != 0) {
                return true;
            }
        }
        
        // Vérifier les prédécesseurs communs
        for (int i = 0; i < n; i++) {
            if (matrice[i][sommet1] != 0 && matrice[i][sommet2] != 0) {
                return true;
            }
        }
        
        return false;
    }
    
    //7.fonction qui étant donné un graphe, retourne ’vrai ’ si il existe une entrée/sortie, ’faux ’ sinon.
    public static boolean entree(int[][] matrice) {
        int n = matrice.length;
        for (int j = 0; j < n; j++) {
            if (degreeEntrant(matrice, j) == 0) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean sortie(int[][] matrice) {
        int n = matrice.length;
        for (int i = 0; i < n; i++) {
            if (degreeSortant(matrice, i) == 0) {
                return true;
            }
        }
        return false;
    }
    
    //8.fonction qui étant donné un graphe, retourne ’vrai ’ si il existe un sommet universel, ’faux ’ sinon.
    public static boolean universel(int[][] matrice) {
        int n = matrice.length;
        
        for (int i = 0; i < n; i++) {
            // Compter les successeurs du sommet i
            int nbSuccesseurs = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && matrice[i][j] != 0) {
                    nbSuccesseurs++;
                }
            }
            
            // Si ce sommet a (n-1) successeurs, c'est un sommet universel
            if (nbSuccesseurs == n - 1) {
                return true;
            }
        }
        
        return false;
    }
    
    // 9.fonction qui attend en entrée un graphe et le chemin vers un fichier vers lequel sera exporté la représentation du graphe.
    public static void exporter(int[][] matrice, String fichier) {
        try {
            FileWriter monFichier = new FileWriter(fichier);
            
            // Première ligne : identifier le format
            monFichier.write("MatriceAdj\n");
            
            // Écrire chaque ligne de la matrice
            for (int i = 0; i < matrice.length; i++) {
                for (int j = 0; j < matrice[i].length; j++) {
                    monFichier.write(matrice[i][j] + "");
                    
                    // Ajouter un espace sauf après le dernier élément
                    if (j < matrice[i].length - 1) {
                        monFichier.write(" ");
                    }
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
    
    //10.fonction qui attend en entrée le chemin d’un fichier au format ’MatriceAdj ’ et retourne la matrice d’adjacence.
    public static int[][] importer(String fichier) {
        try {
            File file = new File(fichier);
            Scanner scanner = new Scanner(file);
            
            // Lire la première ligne pour vérifier le format
            String format = scanner.nextLine();
            if (!format.equals("MatriceAdj")) {
                System.out.println("Format de fichier incorrect : " + format);
                scanner.close();
                return null;
            }
            
            // Compter le nombre de lignes pour déterminer la taille
            int taille = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                taille++;
            }
            scanner.close();
            
            // Relire le fichier pour remplir la matrice
            scanner = new Scanner(file);
            scanner.nextLine(); // Sauter la première ligne
            
            int[][] matrice = new int[taille][taille];
            
            for (int i = 0; i < taille; i++) {
                String ligne = scanner.nextLine();
                String[] valeurs = ligne.split("\\s+");
                
                for (int j = 0; j < valeurs.length; j++) {
                    matrice[i][j] = Integer.parseInt(valeurs[j]);
                }
            }
            
            scanner.close();
            System.out.println("Import réussi depuis : " + fichier);
            return matrice;
            
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé : " + fichier);
            e.printStackTrace();
            return null;
        }
    }
}