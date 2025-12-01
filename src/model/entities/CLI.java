package model.entities;

public class CLI {
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void moveCursor(int row, int col) {
        System.out.print(String.format("\033[%d;%dH", row, col));
        System.out.flush();
    }

    public static void drawMenu(){

    }

   

    public static final String RESET = "\033[0m";
    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";

    public static void drawBox(int width, int height) {
        // Top border
        System.out.print("┌");
        for (int i = 0; i < width - 2; i++) {
            System.out.print("─");
        }
        System.out.println("┐");

        // Sides
        for (int i = 0; i < height - 2; i++) {
            System.out.print("│");
            for (int j = 0; j < width - 2; j++) {
                System.out.print(" ");
            }
            System.out.println("│");
        }

        // Bottom border
        System.out.print("└");
        for (int i = 0; i < width - 2; i++) {
            System.out.print("─");
        }
        System.out.println("┘");
    }
}
