class Main {
    public static void main(String[] args) {
        System.out.println("Test de toutes les fonctions de Supermat\n");
        
        System.out.println("Test d'allocation d'une supermatrice:");
        Supermat matrice1 = Supermat.allouerSupermat(3, 3);
        if (matrice1 != null) {
            System.out.println("L'allocation a réussi pour une matrice 3x3");
            System.out.println("Les dimensions sont: " + matrice1.getNl() + "x" + matrice1.getNc());
        }
        
        Supermat m_invalide = Supermat.allouerSupermat(-1, 3);
        System.out.println("L'allocation invalide retourne null normalement: " + (m_invalide == null));
        
        System.out.println("\nTest d'accès et de modification des éléments:");
        if (matrice1 != null) {
            try {
                Supermat.setAcces(matrice1, 0, 0, 1.5);
                Supermat.setAcces(matrice1, 1, 1, 2.5);
                Supermat.setAcces(matrice1, 2, 2, 3.5);
                
                System.out.println("Les valeurs ont été définies avec succès");
                System.out.println("L'élément [0,0] vaut " + Supermat.acces(matrice1, 0, 0));
                System.out.println("L'élément [1,1] vaut " + Supermat.acces(matrice1, 1, 1));
                System.out.println("L'élément [2,2] vaut " + Supermat.acces(matrice1, 2, 2));
            } catch (Exception e) {
                System.out.println("Une erreur s'est produite malheureusement : " + e.getMessage());
            }
        }
        
        System.out.println("\nTest du produit entre deux matrice A et B:");
        Supermat a = Supermat.allouerSupermat(2, 3);
        Supermat b = Supermat.allouerSupermat(3, 2);
        
        if (a != null && b != null) {
            Supermat.setAcces(a, 0, 0, 1); Supermat.setAcces(a, 0, 1, 2); Supermat.setAcces(a, 0, 2, 3);
            Supermat.setAcces(a, 1, 0, 4); Supermat.setAcces(a, 1, 1, 5); Supermat.setAcces(a, 1, 2, 6);
            
            Supermat.setAcces(b, 0, 0, 7); Supermat.setAcces(b, 0, 1, 8);
            Supermat.setAcces(b, 1, 0, 9); Supermat.setAcces(b, 1, 1, 10);
            Supermat.setAcces(b, 2, 0, 11); Supermat.setAcces(b, 2, 1, 12);
            
            System.out.println("La matrice A (2x3) est:");
            a.afficher();
            System.out.println("La matrice B (3x2) est:");
            b.afficher();
            
            Supermat produit = Supermat.superProduit(a, b);
            if (produit != null) {
                System.out.println("Le produit A×B (2x2) est:");
                produit.afficher();
            }
        }
        
        System.out.println("\nTest de la permutation de lignes:");
        Supermat matrice2 = Supermat.allouerSupermat(3, 3);
        if (matrice2 != null) {
            Supermat.remplirMatrice(matrice2);
            System.out.println("La matrice avant permutation est:");
            matrice2.afficher();
            
            Supermat.permuterLignes(matrice2, 0, 2);
            System.out.println("La matrice après permutation des lignes 0 et 2 est:");
            matrice2.afficher();
        }
        
        System.out.println("\nTest de l'extraction d'une matrice en sous-matrice':");
        Supermat matrice3 = Supermat.allouerSupermat(4, 4);
        if (matrice3 != null) {
            Supermat.remplirMatrice(matrice3);
            System.out.println("La matrice originale (4x4) est:");
            matrice3.afficher();
            
            Supermat sous = Supermat.sousMatrice(matrice3, 1, 2, 1, 2);
            if (sous != null) {
                System.out.println("La sous-matrice [1:2, 1:2] extraite est:");
                sous.afficher();
            }
        }
        
        System.out.println("\nTest de conversion d'une matrice en Supermatrice:");
        double[][] matrice = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        };
        
        Supermat matrice4 = Supermat.matSupermat(matrice, 3, 3, 3, 3);
        if (matrice4 != null) {
            System.out.println("La conversion de matrice en Supermat a donné:");
            matrice4.afficher();
        }
        
        System.out.println("\nTest de conversion d'un Supermatrice en matrice:");
        if (matrice4 != null) {
            double[][] resultat = new double[3][3];
            Supermat.supermatMat(matrice4, resultat, 3, 3);
            
            System.out.println("La conversion de Supermat en matrice a donné:");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.printf("%.2f ", resultat[i][j]);
                }
                System.out.println();
            }
        }
        
        System.out.println("\nTest de la fonction de contiguïté:");
        Supermat matrice6 = Supermat.allouerSupermat(3, 3);
        if (matrice6 != null) {
            Supermat.remplirMatrice(matrice6);
            System.out.println("La matrice normale est:");
            matrice6.afficher();
            
            int contig1 = Supermat.contiguité(matrice6);
            System.out.println("La fonction contiguïté retourne: " + contig1);
            
            Supermat.permuterLignes(matrice6, 0, 2);
            System.out.println("Après permutation, la fonction contiguïté retourne: " + Supermat.contiguité(matrice6));
        }
    }
}
