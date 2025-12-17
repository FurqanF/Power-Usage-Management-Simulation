public class MainApp {
    public static void main(String[] args) {
        try {
            // Initialize simulation with or without an initial file
            Simulation sim = new Simulation(); // now supports adding data interactively
            MenuSystem menu = new MenuSystem(sim);
            menu.displayMenu(); // launch the interactive console menu
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace(); // helpful for debugging
        }
    }
}
