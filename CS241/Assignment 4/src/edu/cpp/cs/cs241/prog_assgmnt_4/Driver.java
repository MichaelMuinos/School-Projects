package edu.cpp.cs.cs241.prog_assgmnt_4;

import java.util.Scanner;
/**
 * The following class acts as a GPS navigation system where you can find the direction between two locations using
 * the graph implementation class that was built.
 * @author Michael
 *
 */
public class Driver {
/**
 * Object in which represents a city.
 */
	private static GraphImplementation<Integer,String,String[]> graph = new GraphImplementation<Integer,String,String[]>();
/**
 * Object for user input.
 */
	private static Scanner sc = new Scanner(System.in);
/**
 * Keeps track of whether the user enters option 1 or 2.	
 */
	private static boolean change = false;
/**
 * Adds the graph nodes and lines to be used for directions.
 * @param args
 */
	public static void main(String[] args) {
		System.out.println("||||| GPS |||||");
		addGraphContent();
		menu();
	}
/**
 * User may choose between point to point directions or find directions from the source to a point.
 */
	private static void menu() {
		System.out.println("Choose a task:");
		System.out.println("\t1. Point-To-Point Navigation");
		System.out.println("\t2. Find Destination");
		
		int choice = sc.nextInt();
		if(choice == 1) {
			pointToPointChoice();
		} else if(choice == 2) {
			findDestinationChoice();
		} else {
			System.out.println("Try again.\n");
			menu();
		}
	}
/**
 * Adds all the graph nodes, edges, and weights.
 */
	private static void addGraphContent() {
		String[] cityHallKeywords = {"Landmark"};
		String[] saintStadiumKeywords = {"Recreation", "Landmark", "Sports"};
		String[] vinewoodBoulevardKeywords = {"Recreation", "Landmark"};
		String[] santosForumKeywords = {"Recreation", "Landmark", "Sports"};
		String[] publicLibraryKeywords = {"Library", "Recreation", "Landmark"};
		String[] theaterKeywords = {"Recreation", "Arts", "Landmark"};
		String[] hospitalKeywords = {"Hospital", "Healthcare"};
		String[] countryClubKeywords = {"Recreation"};
		String[] burgerShotKeywords = {"Dining", "Fast-food", "Restaurant"};
		String[] gymKeywords = {"Fitness", "Healthcare"};
		String[] cluckinBellKeywords = {"Dining", "Fast-food", "Restaurant"};
		String[] pimientoKeywords = {"Dining", "Restaurant"};
		
		Node<Integer,String,String[]> cityHall = new Node<Integer,String,String[]>(0,"Los Santos City Hall",cityHallKeywords);
		Node<Integer,String,String[]> saintStadium = new Node<Integer,String,String[]>(1,"Los Santos Saints' Stadium",saintStadiumKeywords);
		Node<Integer,String,String[]> vinewoodBoulevard = new Node<Integer,String,String[]>(2,"Vinewood Boulevard",vinewoodBoulevardKeywords);
		Node<Integer,String,String[]> santosForum = new Node<Integer,String,String[]>(3,"Los Santos Forum",santosForumKeywords);
		Node<Integer,String,String[]> publicLibrary = new Node<Integer,String,String[]>(4,"Los Santos Public Library",publicLibraryKeywords);
		Node<Integer,String,String[]> theater = new Node<Integer,String,String[]>(5,"Centennial Theater",theaterKeywords);
		Node<Integer,String,String[]> hospital = new Node<Integer,String,String[]>(6,"All Saints General Hospital",hospitalKeywords);
		Node<Integer,String,String[]> countryClub = new Node<Integer,String,String[]>(7,"Richman Country Club",countryClubKeywords);
		Node<Integer,String,String[]> burgerShot = new Node<Integer,String,String[]>(8,"Burgershot",burgerShotKeywords);
		Node<Integer,String,String[]> gym = new Node<Integer,String,String[]>(9,"Los Santos Gym",gymKeywords);
		Node<Integer,String,String[]> cluckinBell = new Node<Integer,String,String[]>(10,"Cluckin' Bell",cluckinBellKeywords);
		Node<Integer,String,String[]> pimiento = new Node<Integer,String,String[]>(11,"Pimiento's",pimientoKeywords);
		
		graph.addNode(cityHall);
		graph.addNode(saintStadium);
		graph.addNode(vinewoodBoulevard);
		graph.addNode(santosForum);
		graph.addNode(publicLibrary);
		graph.addNode(theater);
		graph.addNode(hospital);
		graph.addNode(countryClub);
		graph.addNode(burgerShot);
		graph.addNode(gym);
		graph.addNode(cluckinBell);
		graph.addNode(pimiento);
		
		graph.createEdge(saintStadium, 3, "on Main Ave. with 2nd St", publicLibrary, "East");
		graph.createEdge(vinewoodBoulevard, 2, "on Main Ave. with 3rd St", saintStadium, "East");
		graph.createEdge(santosForum, 5, "on Main Ave. with 4th St", vinewoodBoulevard, "East");
		graph.createEdge(cityHall, 5, "on 1st St. with Centennial Ave", publicLibrary, "South");
		graph.createEdge(theater, 1, "on 2nd St. with Centennial Ave", saintStadium, "South");
		graph.createEdge(theater, 4, "on 2nd St. with Centennial Ave", cityHall, "East");
		graph.createEdge(hospital, 6, "on 3rd St. with Centennial Ave", vinewoodBoulevard, "South");
		graph.createEdge(hospital, 7, "on 3rd St. with Centennial Ave", theater, "East");
		graph.createEdge(countryClub, 1, "on 4th St. with Centennial Ave", santosForum, "South");
		graph.createEdge(countryClub, 3, "on 4th St. with Centennial Ave", hospital, "East");
		graph.createEdge(burgerShot, 2, "on 2nd St. with General Ave", theater, "South");
		graph.createEdge(gym, 1, "on 3rd St. with General Ave", hospital, "South");
		graph.createEdge(gym, 1, "on 3rd St. with General Ave", burgerShot, "East");
		graph.createEdge(cluckinBell, 3, "on 4th St. with General Ave", countryClub, "South");
		graph.createEdge(cluckinBell, 1, "on 4th St. with General Ave", gym, "East");
		graph.createEdge(pimiento, 3, "on 4th St. with Food Alley", cluckinBell, "South");
		graph.createEdge(pimiento, 2, "on 4th St. with Food Alley", gym, "South-East");
	}
/**	
 * User may input two locations and find the distance between the two.
 */
	private static void pointToPointChoice() {
		double locationOneCost = 0;
		double locationTwoCost = 0;
		
		System.out.println("Enter Number Of First Location: ");
		printAllNames();
		int choice1 = sc.nextInt();
		
		System.out.println("Enter Number Of Second Location: ");
		printAllNames();
		int choice2 = sc.nextInt();
		
		locationOneCost = graph.dijkstrasShortestPath().get(choice1);
		locationTwoCost = graph.dijkstrasShortestPath().get(choice2);
		
		double total = locationTwoCost - locationOneCost;
		
		for(int i = 0; i < graph.getEdgeList().size(); i++) {
			Edge<?, ?, ?> tempEdge = graph.getEdgeList().get(i);
			int id = (int) tempEdge.getFrontNode().getID();
			int id2 = (int) tempEdge.getEndNode().getID();
			if(id == choice1 && id2 == choice2) {
				System.out.println("Directions: ");
				System.out.println("Go " + total + " mi " + tempEdge.getDirection() + " " + tempEdge.getStreetName() + ".");
				System.out.println("Arrive at " + tempEdge.getEndNode().getName() + ".");
			}
		}
	}
/**
 * Prints all the names of the nodes.	
 */
	private static void printAllNames() {
		System.out.println("\t0. Los Santos City Hall");
		System.out.println("\t1. Los Santos Saints' Stadium");
		System.out.println("\t2. Vinewood Boulevard");
		System.out.println("\t3. Los Santos Forum");
		System.out.println("\t4. Los Santos Public Library");
		System.out.println("\t5. Centennial Theater");
		System.out.println("\t6. All Saints General Hospital");
		System.out.println("\t7. Richman Country Club");
		System.out.println("\t8. BurgerShot");
		System.out.println("\t9. Los Santos Gym");
		System.out.println("\t10. Cluckin' Bell");
		System.out.println("\t11. Pimiento's");
	}
/**
 * User may search a location by name or keyword.	
 */
	private static void findDestinationChoice() {
		System.out.println("Choose An Option To Search By: ");
		System.out.println("\t1. Name");
		System.out.println("\t2. Keyword");
		
		int choice = sc.nextInt();
		
		if(choice == 1) {
			change = false;
		} else {
			change = true;
		}
		
		if(choice == 1) {
			searchByName();
		} else if(choice == 2) {
			searchByKeyword();
		} else {
			System.out.println("Try again.\n");
			findDestinationChoice();
		}
	}
/**
 * User may search by name of a location and find its distance from the source.	
 */
	private static void searchByName() {
		int name = 0;
		int keywordName = 0;
		if(change == false) {
			System.out.println("Enter Number Of Location Name: ");
			printAllNames();
			name = sc.nextInt();
			System.out.println();
			
			double cost = graph.dijkstrasShortestPath().get(name);
			
			for(int i = 0; i < graph.getEdgeList().size(); i++) {
				Edge<?,?,?> tempEdge = graph.getEdgeList().get(i);
				int id = (int) tempEdge.getFrontNode().getID();
				if(id == 0) {
					graph.getEdgeList().get(name);
					System.out.println("Directions: ");
					System.out.println("Go " + cost + " mi " + tempEdge.getDirection() 
							+ " " + tempEdge.getStreetName() + ".");
					System.out.println("Go " + graph.getEdgeList().get(name).getWeight() 
							+ " mi " + tempEdge.getDirection() + " " + graph.getEdgeList().get(name).getStreetName() + ".");
				}
			}
		} else {
			System.out.println("Enter Number Of Location Of Results: ");
			printAllNames();
			keywordName = sc.nextInt();
			
			double cost = graph.dijkstrasShortestPath().get(keywordName);
			
			for(int i = 0; i < graph.getEdgeList().size(); i++) {
				Edge<?,?,?> tempEdge = graph.getEdgeList().get(i);
				int id = (int) tempEdge.getFrontNode().getID();
				if(id == 0) {
					graph.getEdgeList().get(keywordName);
					System.out.println("Directions: ");
					System.out.println("Go " + cost + " mi " + tempEdge.getDirection() + " " 
							+ tempEdge.getStreetName() + ".");
					System.out.println("Go " + graph.getEdgeList().get(keywordName).getWeight() 
							+ " mi " + tempEdge.getDirection() + " " + graph.getEdgeList().get(keywordName).getStreetName() + ".");
				}
			}
		}
	}	
/**
 * User enters a keyword in which corresponds to a location, and from there, they may search from the following
 * results by name.	
 */
	private static void searchByKeyword() {
		System.out.println("Enter Keyword: \nLibrary, Recreation, Sports, Healthcare, Landmark, Arts, Hospital, Dining, Fast-food, Restaurant, Fitness");
		String keyword = sc.next();
		System.out.println();
		
		System.out.println("||||| RESULTS ||||||");
		
		for(int i = 0; i < graph.getNodesList().size(); i++) {
			Node<Integer,String,String[]> tempNode = graph.getNodesList().get(i);
			for(int j = 0; j < tempNode.getData().length; j++) {
				String[] tempStringArr = tempNode.getData();
				if(tempStringArr[j].equalsIgnoreCase(keyword)) {
					System.out.println(tempNode.getName());
				}
			}
		}
		System.out.println();
		searchByName();
	}

}
