public class Supermat {
    private int nl;
    private int nc;
    private double[][] ligne;
    
    public Supermat(int nl, int nc) 
    {
        this.nl = nl;
        this.nc = nc;
        this.ligne = new double[nl][nc];
    }
    
    // 1. Allocation de mémoire (Fonction allouerSupermat)
    public static Supermat allouerSupermat(int nl, int nc) {
        if (nl <= 0 || nc <= 0) {
            return null;
        }
        return new Supermat(nl, nc);
    }
    
    // 2. Accès aux éléments (Macro acces)
    public static double acces(Supermat a, int i, int j) {
        if (a == null || i < 0 || i >= a.nl || j < 0 || j >= a.nc) {
            throw new IndexOutOfBoundsException("Index hors limites");
        }
        return a.ligne[i][j];
    }
    
    public static void setAcces(Supermat a, int i, int j, double valeur) {
        if (a == null || i < 0 || i >= a.nl || j < 0 || j >= a.nc) {
            throw new IndexOutOfBoundsException("Index hors limites");
        }
        a.ligne[i][j] = valeur;
    }
    
    // 3. Produit matriciel (Fonction superProduit)
    public static Supermat superProduit(Supermat a, Supermat b) {
        if (a == null || b == null || a.nc != b.nl) {
            return null;
        }
        
        Supermat resultat = allouerSupermat(a.nl, b.nc);
        if (resultat == null) {
            return null;
        }
        
        for (int i = 0; i < a.nl; i++) {
            for (int j = 0; j < b.nc; j++) {
                double somme = 0;
                for (int k = 0; k < a.nc; k++) {
                    somme += acces(a, i, k) * acces(b, k, j);
                }
                setAcces(resultat, i, j, somme);
            }
        }
        
        return resultat;
    }
    
    // 4. Permutation de lignes (Fonction permuterLignes)
    public static void permuterLignes(Supermat a, int i, int j) {
        if (a == null || i < 0 || i >= a.nl || j < 0 || j >= a.nl) {
            throw new IndexOutOfBoundsException("Index de ligne invalide");
        }
        
        if (i != j) {
            double[] temp = a.ligne[i];
            a.ligne[i] = a.ligne[j];
            a.ligne[j] = temp;
        }
    }
    
    // 5. Sous-matrice (Fonction sousMatrice)
    public static Supermat sousMatrice(Supermat a, int l1, int l2, int c1, int c2) {
        if (a == null || l1 < 0 || l2 >= a.nl || c1 < 0 || c2 >= a.nc || l1 > l2 || c1 > c2) {
            throw new IllegalArgumentException("Paramètres de sous-matrice invalides");
        }

        int nouvNl = l2 - l1 + 1;
        int nouvNc = c2 - c1 + 1;

        Supermat resultat = allouerSupermat(nouvNl, nouvNc);
        if (resultat == null) {
            return null;
        }

        for (int i = 0; i < nouvNl; i++) {
            for (int j = 0; j < nouvNc; j++) {
                setAcces(resultat, i, j, acces(a, l1 + i, c1 + j));
            }
        }

        return resultat;
    }
    
    // 6. Conversion d'une matrice double en Supermat (Fonction matSupermat)
    public static Supermat matSupermat(double[][] m, int nld, int ncd, int nle, int nce) 
    {
        if (m == null || nle <= 0 || nce <= 0 || nle > nld || nce > ncd) 
        {
            return null;
        }
        
        Supermat resultat = allouerSupermat(nle, nce);
        if (resultat == null) {
            return null;
        }
        
        for (int i = 0; i < nle; i++) {
            for (int j = 0; j < nce; j++) {
                setAcces(resultat, i, j, m[i][j]);
            }
        }
        
        return resultat;
    }
    
    // 7. Conversion d'un Supermat en matrice double (Fonction supermatMat)
    public static void supermatMat(Supermat sm, double[][] m, int nld, int ncd) {
        if (sm == null || m == null) {
            throw new IllegalArgumentException("Paramètres null");
        }
        
        int maxI = Math.min(sm.nl, nld);
        int maxJ = Math.min(sm.nc, ncd);
        
        for (int i = 0; i < maxI; i++) {
            for (int j = 0; j < maxJ; j++) {
                m[i][j] = acces(sm, i, j);
            }
        }
    }
    
    // 8. Fonction de contiguïté (Fonction contiguité)
    public static int contiguité(Supermat a) {
        if (a == null || a.nl <= 1) {
            return 2; // Une seule ligne ou null est considéré comme ordonné
        }
        
        // Vérifier si les lignes sont contiguës
        boolean contigue = true;
        for (int i = 0; i < a.nl - 1; i++) {
            // En Java, les tableaux sont des objets distincts, donc on vérifie
            // si les données sont identiques (simulation de contiguïté)
            if (a.ligne[i] == null || a.ligne[i+1] == null) {
                contigue = false;
                break;
            }
        }
        
        if (!contigue) {
            return 0; // Pas contiguës
        }
        
        // Vérifier si elles sont dans l'ordre
        boolean enOrdre = true;
        for (int i = 0; i < a.nl - 1; i++) {
            // Simulation : on vérifie si les références sont dans l'ordre original
            // En pratique, cela dépend de l'implémentation spécifique
            if (System.identityHashCode(a.ligne[i]) > System.identityHashCode(a.ligne[i+1])) {
                enOrdre = false;
                break;
            }
        }
        
        return enOrdre ? 2 : 1;
    }
    
    // Version simplifiée de contiguïté pour Java
    public static int contigus(Supermat a) {
        if (a == null || a.nl <= 1) {
            return 2;
        }
        
        // En Java, on simule la contiguïté en vérifiant si les tableaux
        // sont des références distinctes ou non
        boolean toutesDistinctes = true;
        for (int i = 0; i < a.nl - 1; i++) {
            for (int j = i + 1; j < a.nl; j++) {
                if (a.ligne[i] == a.ligne[j]) {
                    toutesDistinctes = false;
                    break;
                }
            }
            if (!toutesDistinctes) break;
        }
        
        if (!toutesDistinctes) {
            return 0; // Cas particulier où certaines lignes partagent la même référence
        }
        
        // Vérifier l'ordre en comparant les hash codes (approximation)
        boolean enOrdre = true;
        for (int i = 0; i < a.nl - 1; i++) {
            if (System.identityHashCode(a.ligne[i]) > System.identityHashCode(a.ligne[i+1])) {
                enOrdre = false;
                break;
            }
        }
        
        return enOrdre ? 2 : 1;
    }
    
    // Méthodes utilitaires
    public int getNl() {
        return nl;
    }
    
    public int getNc() {
        return nc;
    }
    
    public void afficher() {
        if (this.ligne == null) {
            System.out.println("Matrice null");
            return;
        }
        
        for (int i = 0; i < nl; i++) {
            for (int j = 0; j < nc; j++) {
                System.out.printf("%.2f ", ligne[i][j]);
            }
            System.out.println();
        }
    }
    
    // Méthode pour remplir une matrice avec des valeurs de test
    public static void remplirMatrice(Supermat m) {
        if (m == null) return;
        
        for (int i = 0; i < m.nl; i++) {
            for (int j = 0; j < m.nc; j++) {
                setAcces(m, i, j, i * m.nc + j + 1);
            }
        }
    }
}
