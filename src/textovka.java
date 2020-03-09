import java.text.Normalizer;
import java.util.Scanner;


public class textovka {
	
	static Scanner s = new Scanner(System.in);
	static String[] smery = new String[]{"nikam :)","sev","vyc","jih","zap"};
//	static int[][] lokace = new int[][] {{0,0,0,0,0},{1,2,0,0,0},{2,5,0,1,3},{3,4,2,0,0},{4,0,5,3,0},{5,0,0,2,4}};
	static int[][] lokace = new int[][] {{0,0,0,0,0},{1,0,0,0,0},{2,5,0,1,3},{3,0,2,0,0},{4,0,5,3,0},{5,0,0,0,0}};
	static int kdeJsem = 1;

	static String[] itemy_nazvy1 = new String[]{"kol","dra","dve","sve","pak","dvi","pus","kli"};
	static String[] itemy_nazvy2 = new String[]{"kolo","drat","dvere","sverak","paklic","dvirka","pusku","klic"};
	static String[] itemy_nazvy3 = new String[]{"Kolo.","Drat.","Dvere.","Sverak.","Paklic.","Dvirka.","Puska.", "Klic."};
	
	// 1 - lokace, 2 - jde sebrat (0/1)
	static int[][] itemy = new int[][]{{1,0},{-1,1},{1,0},{3,0},{-1,1},{3,0},{4,1},{-1,1}};
	static String[] texty = {"Podle vseho jsi u sebe v inventari, jaks to dokázal?",
			"Jsi ve stare kulne. Je tu tma, prach a pavuciny, v rohu stoji stare kolo z kolobezky. Na sever vedou dvere.",
			"Stojis na malem rozbahnenem placku, na jih je kulna, kdes byl zavreny. Na sever jsou vrata a za nimi nekdo stoji, ale jeste te nevidel. Na zapade stoji u dilny stary ponk.",
			"Stojis u stareho ponku pred vybrakovanou dilnou. Na vychod je placek pred kulnou, na sever je zminovana dilna.",
			"Stojis ve vybrakovane dilne. Na nekolika kusech nepouzitelneho naradi jsou stopy od krve. Z dilny vedou dvoje dvere.",
			"Nachazis se pred vraty opustene samoty. Stoji tu jeden hlidac. Okamzite si te vsimne a saha do bundy, asi pro pistoli. Co ted?",
			"Dvere od kulny. Momentalne jsou zamcene. Klic je v zamku z druhe strany.",
			"Dvirka od dilny. Momentalne jsou zamcene. Klic neni nikde videt",
			""};
//	static int storyLine = 0;
	/* 0 - zacatek, 1 - prozkoumal dvere, 2 - prozkoumal kolo, 3 - prozkoumal kolo a sebral drat
	 * 4 - prozkoumal kolo a dvere, 5 - prozkoumal kolo a dvere a sebral drat, 6 - pouzil drat, 
	 * 7 - pouzil drat k sebrani klice, 8 - odemknul dvere, 9 - dal drat do sveraku,
	 * 10 - vyrobil sperhak, 11 - odemknul dvere dilny
	 *  */
	
	public static String nactiText(){
		System.out.print("?");
		String temp = s.nextLine();
//		System.out.println(temp);
		return temp;		
	}
	public static void welcome(){ napisl("Textovka\n========\n(*tady melo byt paradni intro, ale nebyl na nej cas*)Proste jsi udelal prusvih a nekolik typku te odvezlo na opustenou samotu. Ted jsi zavrenej v mistnosti, kam te soupli a zamkli, utec nez se vrati!\nPro pomoc s ovladanim napis POMOC nebo HELP");}
	public static void help(){
		napisl("Napoveda");
		napisl("========");
		napisl("No textovka, ovlada se psanim textu. Povely:");
		napisl("seber <vec>\t\tvezmi vec v okoli");
		napisl("poloz <vec>\t\tpoloz vec z inventare");
		napisl("pouzij <vec>\t\tpouzije vec v miste/inventari");
		napisl("prozkoumej <vec>\tprohlidne vec v okoli/inventari");
		napisl("lokace\t\t\tpopise okoli, kam lze jit, co vidis (TO JE VELMI DOBRA FUNKCE!)");
		napisl("inventar\t\tvypise co neses s sebou");
		napisl("help,pomoc\t\tvypise tenhle text");
		napisl("konec\t\t\tkonec hry/programu");
		napisl("<smer>/jdi <smer>/\njdi na <smer>\t\tpresun udanym smerem");		
		napisl("Vsechny prikazy lze zkratit na tri-pismennou zkratku.");		
	}
	public static void napis (String txt){ System.out.print(txt);}
	public static void napisl (String txt){ System.out.println(txt);}
	public static void napisl(){napisl("");};
	
public static int poleObsahuje(String[] tmp, String h){
	int temp=-1;
	for(int i=0;i<tmp.length;i++){ 
		if ( tmp[i] != null && tmp[i].equals( h ) ) {temp = i;}
	}
    return temp;
}
		
public static String[] zpracuj(String cl){
//	napisl(cl+"|");
	cl = cl.toLowerCase();
	cl = cl.trim();
	do{	cl = cl.replaceAll("  "," "); } while (cl.contains("  "));
//	napisl(cl+"|");
	cl = Normalizer.normalize(cl, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]","");
	String[] temp = cl.split(" ",3);
//	for(int i=0;i<temp.length;i++){	napis(temp[i]+".");}	napisl();
	for(int i=0;i<temp.length;i++){	temp[i]=temp[i].substring(0, Math.min(temp[i].length(), 3));}
	if (poleObsahuje(temp,"sev")>=0) {temp[0]="sev";}
	if (poleObsahuje(temp,"jih")>=0) {temp[0]="jih";}
	if (poleObsahuje(temp,"zap")>=0) {temp[0]="zap";}
	if (poleObsahuje(temp,"vyc")>=0) {temp[0]="vyc";}
//	for(int i=0;i<temp.length;i++){	napis(temp[i]+".");}	napisl();	
	return temp;
	}

public static void seber(String cl){
	int cislo=poleObsahuje(itemy_nazvy1, cl);
	if ((cislo>=0)&&(itemy[cislo][0]==kdeJsem))
		{ if(itemy[cislo][1]==0) {napisl("To nema cenu sbirat ani zkouset sebrat."); } 
		  else {napisl("OK, sebral jsi "+itemy_nazvy2[cislo]);itemy[cislo][0]=0;} }
	else {napisl("Nic takoveho tu neni.");};
}

public static void poloz(String cl){
	int cislo=poleObsahuje(itemy_nazvy1, cl);
	if ((cislo>=0)&&(itemy[cislo][0]==0))
		 {napisl("OK, polozil jsi "+itemy_nazvy2[cislo]);itemy[cislo][0]=kdeJsem;} 
	else {napisl("Nemuzes polozit neco co neneses.");};
}

public static void prozkoumej(String cl){
	int cislo=poleObsahuje(itemy_nazvy1, cl);
	if (((cislo>=0)&&(itemy[cislo][0]==kdeJsem))||((cislo>=0)&&(itemy[cislo][0]==0)))
	{
		switch ( cl ) {
	      case "kol" : if (itemy[1][0]==-1) { napisl("Kolecko z kolobezky. Je orezle a nekolik dratu z vypleteni je uvolnenych."); itemy[1][0]=1;} 
	      				else { napisl("Kolecko z kolobezky. Je orezle a nekolik dratu z vypleteni je uvolnenych.");} ;break;
	      case "dra": napisl("Drat z kola. Neni v nejlepsim stavu, ale i tak lepsi nez dratem do voka.");break;
	      case "dve" :napisl(texty[6]);break;
	      case "sve": napisl("Stary sverak, pripevneny k ponku. Je pekne zadelanej, jako by do nej nekdo nekomu zavrel ruku.");break;
	      case "pak" : napisl("Improvizovany paklic z dratu z kola. Snad bude k necemu.");break;
	      case "dvi": napisl(texty[7]);break;
	      case "pus": napisl("Stará lovecka brokovnice, sestnactka. Je trochu orezla, pokud obet nezabijou broky, dorazi ji tetanus. V hlavnich jsou naboje uzavrene voskem, asi domaci vyroba.");break;
	      case "kli": napisl("Klic od kulny - tvoje propustka na svobodu!");break;
              case "kle": napisl("Klec - je to k něčemu?");break;
		}
	}
	else {napisl("Nic takoveho nevidis.");
	}
}

public static void pouzij(String cl){
	int cislo=poleObsahuje(itemy_nazvy1, cl);
	if ((cislo>=0)&&(itemy[cislo][0]==0))
	{
		switch ( cl ) {
	      case "dra":if((kdeJsem==1)&&(texty[6].equals("Dvere od kulny. Momentalne jsou zamcene. Klic je v zamku z druhe strany."))) { 
	    	  napisl("Chvili se stouras dratem v zamku, dokud z nej nevypadne klic. Ten si pak dratem pode dvermi pritahnes.");itemy[7][0]=1;
	    	  texty[6]="Dvere od kulny. Momentalne jsou zamcene."; }
	      else 
	    	  if(kdeJsem==3) { 
		    	  napisl("Ohnes drat ve sveraku a vyrobis si improvizovany paklic.");itemy[4][0]=0;itemy[1][0]=-1;}
		      else 
	    	  if(texty[6].equals("Dvere od kulny. Momentalne jsou zamcene.")) { 
		    	  napisl("Chvili se stouras dratem v oku, ale docela to boli, tak toho nechas.");}
	    	  break;
	      case "pak" : if(kdeJsem==3){napisl("Po nekolika pokusech uspesne odemknes dvirka do dilny, ale zlomis pritom paklic.");lokace[3][1]=4;itemy[4][0]=-1;texty[7]="Dvirka od dilny. Momentalne jsou odemcena.";};break;
	      case "pus": napisl("Strcis si hlavne do pusy, ale nedosahnes tak na spoust, tak toho nechas.");break;
	      case "kli":  if(kdeJsem==1){napisl("Odemknul jsi dvere, no slava!");texty[6]="Dvere od kulny. Momentalne jsou odemcene.";lokace[1][1]=2;}else{napisl("Neni tu co odemknout..");};break;
		}//switch
	} //nesu predmet
	else if((cislo>=0)&&(itemy[cislo][0]==kdeJsem)) {
		switch ( cl ) {
	      case "kol" : napisl("Zkousis chvilku jezdit na jednom kolecku se spatnym vypletenim, az spadnes a rozbijes si hubu. Ale co se nestane: kdyz se chvilku nehybes, cakance krve vyblednou a jsi zase fit."); itemy[1][0]=1;break;
	      case "dve" :if (texty[6].equals("Dvere od kulny. Momentalne jsou zamcene. Klic je v zamku z druhe strany.")||texty[6].equals("Dvere od kulny. Momentalne jsou zamcene.")) {napisl("Dvere jsou zamcene a nejdou otevrit.");} else {napisl("Dvere jdou otevrit, nekolikrat to vyzkousis.");};break;
	      case "sve": napisl("Sverak funguje, ale asi budes muset pouzit neco jineho u nej, nez samotny sverak.");break;
	      case "dvi": if (texty[7].equals("Dvirka od dilny. Momentalne jsou zamcene. Klic neni nikde videt")) {napisl("Dvirka jsou zamcena a nejdou otevrit.");} else {napisl("Dvirka jdou otevrit, nekolikrat to vyzkousis.");};break;
	      
	      case "dra":  napisl("Na zemi je ti drat k nicemu.");  break;
	      case "pak" : napisl("Nejdriv ho seber.");break;
	      case "pus": napisl("Mozna by sis mel pusku radsi vzit sebou.");break;
	      case "kli":  napisl("Nejdriv ho zkus sebrat..");break;
	      	      	      
		}//switch
	}//jsem tam kde predmet
	else napisl("Nic takoveho u sebe nemas"); 
}


public static void kamMuzu(){
	napis("Muzes jit na:>");
	int jdeTo=0;
	String[] smerz = new String[]{"nikam :)","Sever.","Vychod.","Jih.","Zapad."};
	for (int i=1;i<smery.length;i++){
		jdeTo=lokace[kdeJsem][i];
		if (jdeTo>0) { napis(smerz[i]);}
	}
	napisl();
}	

public static void coTuLezi(){
	napis("Vidis:>");
	for (int i=0;i<itemy.length;i++){
		if (itemy[i][0]==kdeJsem){ napis(itemy_nazvy3[i]);}
	}
	napisl();
}	

public static void inventar(){
	napis("Mas u sebe:>");
	for (int i=0;i<itemy.length;i++){
		if (itemy[i][0]==0){ napis(itemy_nazvy3[i]);}
	}
	napisl();
}	

public static void situace(){
	napisl(texty[kdeJsem]);
	coTuLezi();
	kamMuzu();
}	

	public static boolean vyhodnot(String[] cl2){
		boolean temp = true;
		if (kdeJsem==5) {
			if ((cl2[0].equals("pou"))&&(cl2[1].equals("pus"))){napisl("**le great outro: good ending**\nOkamzite na panacu namiris pusku. Okamzite prestane blbnout a vzda se, hodi ti klicky od auta zaparkovaneho opodal a vypada, ze te radsi necha v klidku jit, nez se nechat strelit. Co udelas je uz jen na tobe..");}
			else {napisl("**le great outro: bad ending**\nZatimco zmatkujes co ted, v klidu te s pistoli donuti jit zpet do kamrliku, kde te opet zamkne. Tentokrat si ale da pozor, abys znovu neutekl.");}
			temp = false;
		}
		else
		{
		if (cl2[0].equals("kon")||cl2[0].equals("end")) {napisl("Game over, baby!");temp = false;} else
		if (cl2[0].equals("hel")||cl2[0].equals("pom")) {help();} else
			if (poleObsahuje(smery, cl2[0])>=0) {
		if (lokace[kdeJsem][poleObsahuje(smery, cl2[0])]>0) 
			 { 	napisl("Jdes na "+cl2[0].substring(0, 1)+".");
			 	kdeJsem=lokace[kdeJsem][poleObsahuje(smery, cl2[0])];
			 	situace();}
		else {napisl("Tudy nemůe");} } else
		if (cl2[0].equals("sit")||cl2[0].equals("lok")) {situace();} else
		if (cl2[0].equals("inv")) {inventar();} else
		if (cl2.length>1) {	
		if (cl2[0].equals("seb")) {seber(cl2[1]);} else
		if (cl2[0].equals("pol")) {poloz(cl2[1]);} else
		if (cl2[0].equals("pro")) {prozkoumej(cl2[1]);} else
		if (cl2[0].equals("pou")) {pouzij(cl2[1]);} }else
		{napisl("..nerozumim, zkus to jinak, nebo napis POMOC..");}}
		return temp;
	}
	
	
	public static void main (String[] args) {
		
		boolean itsNotOver = true;
		// init
		String cl;
		String[] cl2;
		welcome();
		situace();
			
// jedem		
		do {
		cl = nactiText();	
		cl2 = zpracuj(cl);	
		itsNotOver=vyhodnot(cl2);
		} // end loop
		while (itsNotOver);
		
		s.close();
	} //end main
	
} // end class
