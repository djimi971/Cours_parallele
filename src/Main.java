import java.util.Scanner;

public class Main {
    public static void main(String args[]){

        //exo1
        /*
        AfficheNFois a1 = new AfficheNFois (10, "Bonjour" );
        AfficheNFois a2 = new AfficheNFois (10, "Toto" );
        */

        //exo 2
        /*
        compte_a_rebours a1 = new compte_a_rebours(10);
        compte_a_rebours b1 = new compte_a_rebours(30);
        a1.start();
        b1.start();
        */

        //exo 3
        /*
        exo3_1 alpha1 = new exo3_1();
        exo3_2 nume1 = new exo3_2();
        alpha1.start();
        nume1.start();
        */

        //exo 4
        /*
        occcurence chaine1 = new occcurence("salut bienvenue",'e');
        chaine1.start();
        */


        //exo5 tableau de Thread pour séparer la recherche dans une longue chaine de caractère
        // check the number of processors available
        //System.out.println(""+Runtime.getRuntime().availableProcessors());
        int nbThreaddispo = Runtime.getRuntime().availableProcessors();

        System.out.println("saisir la chaine a recherché :");
        String chaine = new Scanner(System.in).nextLine();

        System.out.println("saisir le charactère a recherché :");
        char recherche = new Scanner(System.in).nextLine().charAt(0);

        int nbThread =0;
        while (nbThread<=0 || nbThread>nbThreaddispo) {
            System.out.println("saisir le nombre de Thread a utilisé : (maximum "+ nbThreaddispo +" Thread ");
            nbThread = Integer.parseInt(new Scanner(System.in).nextLine());
        }

        int taille = chaine.length();
        /* correction prof
        traitementTexte[] tab = new traitementTexte[nbThread];
        for(int i = 0; i<nbThread;i++){
                tab[i] = new traitementTexte(
                         chaine,
                         i*taille/nbThread,
                         (i != taille-1 ? (i+1)*taille/nbThreads : taille-1),c);
              }
         */
        StringAnalyst[] tab = new StringAnalyst[nbThread];
        for(int i=0;i<nbThread;i++){
            if(i!=nbThread-1){
                tab[i]= new StringAnalyst(chaine,i*(taille/nbThread),(1+i)*(taille/nbThread),recherche);
            }
            else{
                tab[i]= new StringAnalyst(chaine,i*(taille/nbThread),taille,recherche);
            }
        }
        //Lancement des threads et attente
        for(int i =0;i<nbThread;i++) {
            tab[i].start();
            tab[i].join();
        }


        //Sommes des résultats
        int somme = 0;
        for(int i = 0;i<nbThread; i++){
            somme += tab[i].nb;
        }
        System.out.println("Nombre d'occurrence de "+c+": "+somme);
    }
}
