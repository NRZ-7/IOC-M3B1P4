// ******************************************************************************************
// * This project is licensed under the GNU GPL v3.0
// * https://www.gnu.org/licenses/gpl-3.0.html
// ******************************************************************************************
//	@file Version: 4 beta 3
//	@file Name: P4_SanchezM_FabregasM.java
//	@file Authors: Juanma Sánchez & Jordi Fàbregas

package p4_sanchezm_fabregasm;

//Importem la biblioteca de l'scanner

import java.util.Scanner;

public class P4_SanchezM_FabregasM {

    // Definim les variables constants
    public static final int MIN_TIS = 100000, MAX_TIS = 999999;
    public static final int MIN_SINTOMA = 0, MAX_SINTOMA = 3;
    public static final int MIN_EXPLORACIO = 0, MAX_EXPLORACIO = 3;
    public static final int MIN_PRIORITAT = 0, MAX_PRIORITAT = 5;
    public static final int MIN_TEMP = 27, MAX_TEMP = 45;
    public static final int MAX_INTENTS = 3; // Nombre màxim del contador d'errors
    public static final int MAX_ENTRADES = 10; // Longitud dels arrays i nombre màxim de pacients a introduïr
    public static final int MIN_BOOLEA = 0, MAX_BOOLEA = 1;
    public static final int MAX_COLUMN = 5;

    public static final int COLUMNTIS = 0;
    public static final int COLUMNSIMPTOMA = 1;
    public static final int COLUMNEXPLORACIO = 2;
    public static final int COLUMPRIORITAT = 3;
    public static final int COLUMTEMPERATURA = 4;


    // Variables constants simptoma i exploració
    public static final int SI_0 = 0, SI_1 = 1, SI_2 = 2, SI_3 = 3;
    public static final int EX_0 = 0, EX_1 = 1, EX_2 = 2, EX_3 = 3;

    // Definim Variables
    // Variables booleanes que s'utilitzen per al control d'errors
    static boolean error = false; // G
    static boolean noInt = false; // G Es converteix a true quan l'entrada de l'usuari no es un nombre sencer
    static int contadorErrades = MAX_INTENTS; // G

    // Iniciem l'escanner
    static Scanner scanner = new Scanner(System.in);

    /////////////
    // MÈTODES //
    /////////////

    // Procediment que mostra un error en pantalla, comprova en nombre d'errors i surt quan es compleix la condició
    static void errorFnc(String errorString) {
        // Borrem el contingut de l'scanner
        // M'ha costat molt sol·lucionar aquest bug. No entenc perquè si és un nombre sencer necessito nextLine() i si no next()
        if (noInt) {
            scanner.next();
        } else {
            scanner.nextLine();
        }
        // Mostrem missatge d'error
        System.out.println("\n[ERROR] " + errorString);
        // Restem el contador d'errades
        contadorErrades--;
        System.out.println("\nEt queden " + contadorErrades + " intents per a introduïr una dada vàlida.");
        // Si el contador d'errades < 1, cancel·lem l'entrada de dades.
        if (contadorErrades < 1) {
            error = true;
        }
    }

    // Funció per a demanar un nombre sencer i emmagatzemar-la en una variable. (TIS, prioritat, símptoma, exploració, nouUsuari...)
    // Definim com es crida el mètode. Per exemple es cridaria: tis = introduirSencer("Introdueïx el codi TIS: ",MIN_TIS,MAX_TIS);
    static int introduirSencer(String textAMostrar, int valorMinim, int valorMaxim) {

        int sencerDEntrada = 0;
        boolean dadaCorrecta = false; // Variable per validar les dades d'entrada

        // Iniciem un bucle que es repetirà mentre la dada no sigui correcta i no s'hagi activat el trigger error.
        while (!dadaCorrecta && !error) {
            System.out.println();
            // Preguntem a l'usuari per les dades a introduir i li diem els valors que acceptem
            System.out.print/*ln*/(textAMostrar + " (" + valorMinim + " / " + valorMaxim + ") ");

            if (scanner.hasNextInt()) {
                sencerDEntrada = scanner.nextInt();
                // Si es enter comprovem si és entre els nombres acceptats
                if (sencerDEntrada >= valorMinim && sencerDEntrada <= valorMaxim) {
                    dadaCorrecta = true;
                } else {
                    // Si no passa la validació mostrem un error
                    errorFnc("La dada introduida no és vàlida");
                }
            } else {
                //Si no és un enter mostrem un error
                // M'ha costat molt sol·lucionar un bug, però per algun motiu cal executar unes ordres diferents i per això indico al mètode error que no és un nombre sencer
                noInt = true; // Indiquem a l'error que no és un nombre sencer
                errorFnc("No has introduit un nombre sencer");
                noInt = false;
            }
        } // Sortim del bucle

        // Quan arribem aquí se suposa que hem passat totes les validacions
        // Reiniciem el valor de dadaCorrecta
        dadaCorrecta = false;
        // Finalment establim que variable = sencerDEntrada.
        return sencerDEntrada;
    }

    // Mètode per unir tots els arrays en un array bidimensional
    public static int[][] crearArrayBidimensional(int[] tisArray, int[] simptomaArray, int[] exploracioArray, int[] prioritatArray, int[] temperturaArray) {

        int[][] dadesPacient = new int[MAX_ENTRADES][MAX_COLUMN];
        // copiem els arrys dintre del array bidimensional
        for (int i = 0; i < dadesPacient.length; i++) {

            dadesPacient[i][COLUMNTIS] = tisArray[i];
            dadesPacient[i][COLUMNSIMPTOMA] = simptomaArray[i];
            dadesPacient[i][COLUMNEXPLORACIO] = exploracioArray[i];
            dadesPacient[i][COLUMPRIORITAT] = prioritatArray[i];
            dadesPacient[i][COLUMTEMPERATURA] = temperturaArray[i];
        }
        return dadesPacient;
    }
    
    /*
    public static void llegirDadesPacients(int[][] pacientDades, int numPacients) {
        System.out.println("---------------------");
        System.out.println("Llista de Pacients");
        System.out.println("Tis\t\tSimptoma\texploracio\tprioritat\ttemperatura");
        for (int i = 0; i < numPacients; i++) {

            System.out.println(pacientDades[i][COLUMNTIS] + "\t\t" + pacientDades[i][COLUMNSIMPTOMA] + "\t\t\t" + pacientDades[i][COLUMNEXPLORACIO] + "\t\t\t"
                            + pacientDades[i][COLUMPRIORITAT] + "\t\t\t" + pacientDades[i][COLUMTEMPERATURA]);
        }
    }
    */

    //////////////////////
    // MÈTODE PRINCIPAL //
    //////////////////////

    public static void main(String[] args) {

        // Variables
        int prioritat = 0, temperatura = 0, tis = 0, simptoma = 0, exploracio = 0, nouPacient = 0;
        String simptomaString = "";
        String exploracioString = "";

        int filtrarSimptoma = 0, simptomaSelleccionat = 0, estadistiques = 0;

        int contadorDolor = 0, contadorLesio = 0, contadorFebra = 0, contadorConfusio = 0;

        String exploracio0 = "", exploracio1 = "", exploracio2 = "", exploracio3 = "";
        int contadorPacients = 0;
        boolean sortir = false;

        // Definim els arrays
        int[] tisArray = new int[MAX_ENTRADES];
        int[] simptomaArray = new int[MAX_ENTRADES];
        int[] exploracioArray = new int[MAX_ENTRADES];
        int[] prioritatArray = new int[MAX_ENTRADES];
        int[] temperaturaArray = new int[MAX_ENTRADES];
        int[][] dadesPacients = new int[MAX_ENTRADES][MAX_COLUMN];

        int[] contadorPrior = {0, 0, 0, 0, 0, 0}; // Aquest array és a part. NO MULTIDIMENSIONAL

        // Definim un bucle a l'inici del programa que en arribar al final del mateix es repetirà a no ser que no es desitji sortir
        while (!sortir) {

            // reiniciem contador d'errades
            contadorErrades = MAX_INTENTS;

            // Cridem a la funció introduirSencer per a demanar el TIS
            // varibable = introduirSencer(textAMostrar,valorMínim,valorMàxim)
            tis = introduirSencer("Introdueixi el codi TIS del pacient:", MIN_TIS, MAX_TIS);

            // SIMPTOMA
            if (!error && !sortir) {
                System.out.println("\nLlistat de símptomes:");
                System.out.println();
                System.out.println("0- Dolor");
                System.out.println("1- Lesió traumàtica");
                System.out.println("2- Febra alta");
                System.out.println("3- Confusió o desorientació");

                simptoma = introduirSencer("Sel·leccioni un símptoma:", MIN_SINTOMA, MAX_SINTOMA);

            }

            if (!error && !sortir) {
                // Switch de exploració en funció del símptoma
                switch (simptoma) {
                    case SI_0: // Dolor 
                        // Definim el nom del símptoma
                        simptomaString = "Dolor";
                        // En funció del símptoma sel·leccionat, definim les variables que corresponen a les exploracions
                        exploracio0 = "Dolor toràcic";
                        exploracio1 = "Dolor abdominal";
                        exploracio2 = "Dolor cap";
                        exploracio3 = "Migranya";
                        break;
                    case SI_1: // Lesió traumàtica
                        simptomaString = "Lesió traumàtica";
                        exploracio0 = "Fractura òssia";
                        exploracio1 = "Ferida de bala";
                        exploracio2 = "Cremada";
                        exploracio3 = "Lesió cerebral traumàtica";
                        break;
                    case SI_2: // Febra alta
                        simptomaString = "Febra alta";
                        exploracio0 = "Pneumònia";
                        exploracio1 = "Meningitis";
                        exploracio2 = "Infecció viral";
                        exploracio3 = "Reacció al·lèrgica";
                        break;
                    case SI_3: // Confusió o desorientació
                        simptomaString = "Confusió o desorientació";
                        exploracio0 = "Intoxicació per drogues o alcohol";
                        exploracio1 = "Deshidratació severa";
                        exploracio2 = "Accident cerebrovascular";
                        exploracio3 = "Hipoglucèmia severa";
                        break;
                    default: // Error que no hauria de succeïr sota cap circumstància. Sortim del programa degut a que és un error descontrolat.
                        error = true; // Establim error a true perquè ja no segueixi demanant més dades.
                        errorFnc("Error no esperat, siusplau avisa a l'autor.");
                        sortir = true; // Establim sortir a true per sortir del bucle de demanar un nou usuari.
                }
            }

            // Si no s'ha produït un error, llistem les exploracions possibles
            if (!error && !sortir) {
                // Preguntem el tipus d'exploració
                System.out.println("\nLlistat d'exploracions possibles:");
                System.out.println();
                System.out.println("0- " + exploracio0);
                System.out.println("1- " + exploracio1);
                System.out.println("2- " + exploracio2);
                System.out.println("3- " + exploracio3);
            }

            exploracio = introduirSencer("Sel·leccioni una exploració:", MIN_EXPLORACIO, MAX_EXPLORACIO);

            if (!error && !sortir) {
                // en funció del valor sel·leccionat per l'usuari, establim el nom de la variable exploracioString
                switch (exploracio) {

                    case EX_0:
                        exploracioString = exploracio0;
                        break;
                    case EX_1:
                        exploracioString = exploracio1;
                        break;
                    case EX_2:
                        exploracioString = exploracio2;
                        break;
                    case EX_3:
                        exploracioString = exploracio3;
                        break;
                    // En el cas que no estigui entre els valors esperats, sortim mostrant un error 
                    default: // Error que no hauria de succeïr sota cap circumstància. Sortim del programa degut a que és un error descontrolat.
                        error = true; // Establim error a true perquè ja no segueixi demanant més dades.
                        errorFnc("Error no esperat, siusplau avisa a l'autor.");
                        sortir = true; // Establim sortir a true per sortir del bucle de demanar un nou usuari.
                }
            }

            // Demanar prioritat
            prioritat = introduirSencer("Introdueixi la prioritat:", MIN_PRIORITAT, MAX_PRIORITAT);

            // Demanar temperatura
            temperatura = introduirSencer("Introdueixi la temperatura corporal del pacient:", MIN_TEMP, MAX_TEMP);

            // Si no hi ha cap error fins aquí
            if (!error && !sortir) {
                contadorPacients++; // Contem un pacient més

                // Emmagatzamem les dades al seu corresponent array
                // el pacient 1, correspòn a la posiciò 0 del array, el pacient 2 a la posició 1, etc.
                tisArray[contadorPacients - 1] = tis;
                simptomaArray[contadorPacients - 1] = simptoma;
                exploracioArray[contadorPacients - 1] = exploracio;
                prioritatArray[contadorPacients - 1] = prioritat;
                temperaturaArray[contadorPacients - 1] = temperatura;

                dadesPacients = crearArrayBidimensional(tisArray, simptomaArray, exploracioArray, prioritatArray, temperaturaArray);

            }

            // imprimim el contador de pacients (independentment que hi hagi error o no)
            System.out.println();
            System.out.println("Has introduït " + contadorPacients + " pacients.");

            // reiniciem contador d'errades
            contadorErrades = MAX_INTENTS;

            // Si s'ha activat el trigger d'error, el desactivem per sortir del bucle.
            error = false;

            // Fora del bucle d'errors, preguntem si vol introduïr un nou pacient, de tal manera que entrem en un bucle nou de tres intents
            // (al arribar aquí error sempre serà fals i el contadorErrades estarà al màxim)

            if (contadorPacients < MAX_ENTRADES) { // Només s'executarà si el contador de pacients és inferior a la longitud del array
                nouPacient = introduirSencer("Vol introduïr un altre pacient?:", MIN_BOOLEA, MAX_BOOLEA);
            } else {
                System.out.println("\nS'ha arribat al límit de pacients que es poden introduïr.");
                nouPacient = 0;
            }

            // Si vol introduir un nou pacient (1) seguim en el bucle introduïr usuaris. Si desitja sortir (0) o introdueix 3 vegades un valor erroni, sortim.
            if (nouPacient == 1) {
                sortir = false;
            } else {
                System.out.println("\nFinalitzant l'entrada de pacients.");
                sortir = true;
            }
        } // Sortim del bucle de introduïr usuaris

        // Reinciem el contador d'errors
        contadorErrades = MAX_INTENTS;
        error = false;

        if (!error) {
            // RESUM
            System.out.println();
            // System.out.printf(*Format:...., +Args: -----)
            System.out.printf("%-15s %-30s %-35s %-25s %-20s\n", "TIS", "Símptoma", "Exploració", "Nivell prioritat", "Temperatura actual");

            for (int i = 0; i < contadorPacients; i++) {
                // Utiltizem la variable TIS per validar la linea. Descartant totes les que tinguin el valor per defecte.
                if (dadesPacients[i][COLUMNTIS] != 0) {
                    simptoma = dadesPacients[i][COLUMNSIMPTOMA];
                    exploracio = dadesPacients[i][COLUMNEXPLORACIO];

                    switch (simptoma) {
                        case SI_0: // Dolor 
                            // Definim el nom del símptoma
                            simptomaString = "Dolor";
                            // En funció del símptoma sel·leccionat, definim les variables que corresponen a les exploracions
                            exploracio0 = "Dolor toràcic";
                            exploracio1 = "Dolor abdominal";
                            exploracio2 = "Dolor cap";
                            exploracio3 = "Migranya";
                            break;
                        case SI_1: // Lesió traumàtica
                            simptomaString = "Lesió traumàtica";
                            exploracio0 = "Fractura òssia";
                            exploracio1 = "Ferida de bala";
                            exploracio2 = "Cremada";
                            exploracio3 = "Lesió cerebral traumàtica";
                            break;
                        case SI_2: // Febra alta
                            simptomaString = "Febra alta";
                            exploracio0 = "Pneumònia";
                            exploracio1 = "Meningitis";
                            exploracio2 = "Infecció viral";
                            exploracio3 = "Reacció al·lèrgica";
                            break;
                        case SI_3: // Confusió o desorientació
                            simptomaString = "Confusió o desorientació";
                            exploracio0 = "Intoxicació per drogues o alcohol";
                            exploracio1 = "Deshidratació severa";
                            exploracio2 = "Accident cerebrovascular";
                            exploracio3 = "Hipoglucèmia severa";
                            break;
                        default: // Error que no hauria de succeïr sota cap circumstància. Sortim del programa degut a que és un error descontrolat.
                            error = true; // Establim error a true perquè ja no segueixi demanant més dades.
                            errorFnc("Error no esperat, siusplau avisa a l'autor.");
                            sortir = true; // Establim sortir a true per sortir del bucle de demanar un nou usuari.
                    }

                    switch (exploracio) {

                        case EX_0:
                            exploracioString = exploracio0;
                            break;
                        case EX_1:
                            exploracioString = exploracio1;
                            break;
                        case EX_2:
                            exploracioString = exploracio2;
                            break;
                        case EX_3:
                            exploracioString = exploracio3;
                            break;
                        // En el cas que no estigui entre els valors esperats, sortim mostrant un error 
                        default: // Error que no hauria de succeïr sota cap circumstància. Sortim del programa degut a que és un error descontrolat.
                            error = true; // Establim error a true perquè ja no segueixi demanant més dades.
                            errorFnc("Error no esperat, siusplau avisa a l'autor.");
                            sortir = true; // Establim sortir a true per sortir del bucle de demanar un nou usuari.

                    }

                    //System.out.print(tisArray[i]+" "+simptomaString+" "+exploracioString+" "+prioritatArray[i]+" "+temperaturaArray[i] + "\n"); 
                    System.out.printf("%-15d %-30s %-35s %-25d %-20d\n", dadesPacients[i][COLUMNTIS], simptomaString, exploracioString, dadesPacients[i][COLUMPRIORITAT], dadesPacients[i][COLUMTEMPERATURA]);
                }
            } // Sortim del bucle de imprimir pacients
        }

        filtrarSimptoma = introduirSencer("Vol consultar per tipus de símptoma?", MIN_BOOLEA, MAX_BOOLEA);

        if (filtrarSimptoma == 1 && !error) {

            System.out.println("\nLlistat de símptomes:");
            System.out.println();
            System.out.println("0- Dolor");
            System.out.println("1- Lesió traumàtica");
            System.out.println("2- Febra alta");
            System.out.println("3- Confusió o desorientació");

            simptomaSelleccionat = introduirSencer("Sel·leccioni un símptoma:", MIN_SINTOMA, MAX_SINTOMA);
        }

        // Ordenació bombolla per prioritat
        int temp = 0; // Creem una variable temporal per intercanviar nombres
        // Definim un bucle que es repetirà per l'array de codis
        for (int i = 0; i < contadorPacients; i++) {
            // Es defineix un segon bucle
            for (int j = 1; j < (contadorPacients - i); j++) {
                //comprovem si la prioitat de l'element actual és més petit que el següent
                if (dadesPacients[j - 1][COLUMPRIORITAT] < dadesPacients[j][COLUMPRIORITAT]) {
                //if (prioritatArray[j - 1] < prioritatArray[j]) {

                    // Si és més petit, els intercanviem utilitzant la variable temporal.
                    temp = dadesPacients[j - 1][COLUMPRIORITAT];
                    dadesPacients[j - 1][COLUMPRIORITAT] = dadesPacients[j][COLUMPRIORITAT];
                    dadesPacients[j][COLUMPRIORITAT] = temp;

                    // Ordenem per prioritat, pero hem de canviar l'ordre a tots els arrays
                    temp = dadesPacients[j - 1][COLUMNTIS];
                    dadesPacients[j - 1][COLUMNTIS] = dadesPacients[j][COLUMNTIS];
                    dadesPacients[j][COLUMNTIS] = temp;

                    temp = dadesPacients[j - 1][COLUMNSIMPTOMA];
                    dadesPacients[j - 1][COLUMNSIMPTOMA] = dadesPacients[j][COLUMNSIMPTOMA];
                    dadesPacients[j][COLUMNSIMPTOMA] = temp;

                    temp = dadesPacients[j - 1][COLUMNEXPLORACIO];
                    dadesPacients[j - 1][COLUMNEXPLORACIO] = dadesPacients[j][COLUMNEXPLORACIO];
                    dadesPacients[j][COLUMNEXPLORACIO] = temp;

                    temp = dadesPacients[j - 1][COLUMTEMPERATURA];
                    dadesPacients[j - 1][COLUMTEMPERATURA] = dadesPacients[j][COLUMTEMPERATURA];
                    dadesPacients[j][COLUMTEMPERATURA] = temp;
                }

            }
        }

        if (filtrarSimptoma == 1 && !error) {
            // RESUM
            System.out.println();
            // System.out.printf(*Format:...., +Args: -----)
            System.out.printf("%-15s %-30s %-35s %-25s %-20s\n", "TIS", "Símptoma", "Exploració", "Nivell prioritat", "Temperatura actual");

            for (int i = 0; i < contadorPacients; i++) {
                // Utiltizem la variable TIS per validar la linea. Descartant totes les que tinguin el valor per defecte.
                if (dadesPacients[i][COLUMNTIS] != 0 && dadesPacients[i][COLUMNSIMPTOMA] == simptomaSelleccionat) {
                    simptoma = dadesPacients[i][COLUMNSIMPTOMA];
                    exploracio = dadesPacients[i][COLUMNEXPLORACIO];

                    switch (simptoma) {
                        case SI_0: // Dolor 
                            // Definim el nom del símptoma
                            simptomaString = "Dolor";
                            // En funció del símptoma sel·leccionat, definim les variables que corresponen a les exploracions
                            exploracio0 = "Dolor toràcic";
                            exploracio1 = "Dolor abdominal";
                            exploracio2 = "Dolor cap";
                            exploracio3 = "Migranya";
                            break;
                        case SI_1: // Lesió traumàtica
                            simptomaString = "Lesió traumàtica";
                            exploracio0 = "Fractura òssia";
                            exploracio1 = "Ferida de bala";
                            exploracio2 = "Cremada";
                            exploracio3 = "Lesió cerebral traumàtica";
                            break;
                        case SI_2: // Febra alta
                            simptomaString = "Febra alta";
                            exploracio0 = "Pneumònia";
                            exploracio1 = "Meningitis";
                            exploracio2 = "Infecció viral";
                            exploracio3 = "Reacció al·lèrgica";
                            break;
                        case SI_3: // Confusió o desorientació
                            simptomaString = "Confusió o desorientació";
                            exploracio0 = "Intoxicació per drogues o alcohol";
                            exploracio1 = "Deshidratació severa";
                            exploracio2 = "Accident cerebrovascular";
                            exploracio3 = "Hipoglucèmia severa";
                            break;
                        default: // Error que no hauria de succeïr sota cap circumstància. Sortim del programa degut a que és un error descontrolat.
                            error = true; // Establim error a true perquè ja no segueixi demanant més dades.
                            errorFnc("Error no esperat, siusplau avisa a l'autor.");
                            sortir = true; // Establim sortir a true per sortir del bucle de demanar un nou usuari.
                    }

                    switch (exploracio) {

                        case EX_0:
                            exploracioString = exploracio0;
                            break;
                        case EX_1:
                            exploracioString = exploracio1;
                            break;
                        case EX_2:
                            exploracioString = exploracio2;
                            break;
                        case EX_3:
                            exploracioString = exploracio3;
                            break;
                        // En el cas que no estigui entre els valors esperats, sortim mostrant un error 
                        default: // Error que no hauria de succeïr sota cap circumstància. Sortim del programa degut a que és un error descontrolat.
                            error = true; // Establim error a true perquè ja no segueixi demanant més dades.
                            errorFnc("Error no esperat, siusplau avisa a l'autor.");
                            sortir = true; // Establim sortir a true per sortir del bucle de demanar un nou usuari.

                    }

                    System.out.printf("%-15d %-30s %-35s %-25d %-20d\n", dadesPacients[i][COLUMNTIS], simptomaString, exploracioString, dadesPacients[i][COLUMPRIORITAT], dadesPacients[i][COLUMTEMPERATURA]);
                }
            } // Sortim del bucle de imprimir pacients
        }

        estadistiques = introduirSencer("Vols veure un resum estadístic de les dades?", MIN_BOOLEA, MAX_BOOLEA);

        // Si selecciona 1 mostrem estadístiques
        if (estadistiques == 1 && !error) {
            System.out.println("\nNúmero de pacients introduïts:" + contadorPacients);
            System.out.println("Número de pacients per símptomes:");

            // Recorrem l'array de simptoma i comptem
            for (int i = 0; i < contadorPacients; i++) {
                if (dadesPacients[i][COLUMNSIMPTOMA] == SI_0 && dadesPacients[i][COLUMNTIS] != 0) { // Utilitzem la variable tis per descartar els usuaris buits del array
                    contadorDolor++;
                }
                if (dadesPacients[i][COLUMNSIMPTOMA] == SI_1) {
                    contadorLesio++;
                }
                if (dadesPacients[i][COLUMNSIMPTOMA] == SI_2) {
                    contadorFebra++;
                }
                if (dadesPacients[i][COLUMNSIMPTOMA] == SI_3) {
                    contadorConfusio++;
                }

            }

            // Imprimim les estadístiques
            System.out.printf("%-2s %-10s %-20s %-15s %-20s\n", "", "Dolor:", "Lesió traumàtica:", "Febra alta:", "Confusió o desorientació");
            System.out.printf("%-2s %-10d %-20d %-15d %-20d\n", "", contadorDolor, contadorLesio, contadorFebra, contadorConfusio);

            System.out.println("Comptador de pacients per prioritat:");


            // Recorrem l'Array de prioritat i sumem les prioritats
            for (int i = 0; i < contadorPacients; i++) {

                if (dadesPacients[i][COLUMNTIS] != 0) {
                    contadorPrior[dadesPacients[i][COLUMPRIORITAT]]++;
                }
            }

            System.out.printf("%-2s %-17s %-17s %-17s %-17s %-17s %-17s\n", "", "Prioritat 0:", "Prioritat 1:", "Prioritat 2:", "Prioritat 3:", "Prioritat 4:", "Prioritat 5:");
            System.out.printf("%-2s %-17d %-17d %-17d %-17d %-17d %-17d\n", "", contadorPrior[0], contadorPrior[1], contadorPrior[2], contadorPrior[3], contadorPrior[4], contadorPrior[5]);

            // Tanquem l'scanner
            scanner.close();
        }
        /*/
        if (contadorPacients == 0) {
            System.out.println("No hi ha cap pacient en el registre");
        } else {
            llegirDadesPacients(dadesPacients, contadorPacients);
        }
        */

    }
}
