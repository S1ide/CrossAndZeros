import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    public static int size = 3;
    private static char[][] board = new char[size][size];
    ;
    public static Scanner scanner = new Scanner(System.in);
    public static Player firstPlayer;
    public static Player secondPlayer;


    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        System.out.println("Привет, представляю тебе мою игру Крестики-нолики.\n" +
                "Крестики-нолики — это логическая игра между двумя противниками на квадратном поле 3 на 3 клетки или бо?льшего размера (вплоть до «бесконечного поля»).\n" +
                "Один из игроков играет «крестиками», второй — «ноликами».");
        System.out.println();
        System.out.println("Кто будет играть «крестиками»? Назови своё имя.");
        firstPlayer = new Player('X', scanner.next());
        System.out.println("Кто будет играть «ноликами»? Как зовут тебя?»");
        secondPlayer = new Player('0', scanner.next());
//        System.out.println("Какой размер поля выберете?");
//        while (true) {
//            try {
//                size = Integer.parseInt(scanner.next());
//                board = new char[size][size];
//                break;
//            } catch (Exception e) {
//                System.out.println("Кажется вы ввели что-то странное. Введите число.");
//            }
//        }
        game();
    }

    public static void game() {
        startBoard(size, board);
        printBoard();
        boolean flag = true;
        while (flag) {
            move(firstPlayer, board);
            printBoard();
            if (checkWinner()) {
                win(firstPlayer);
                break;
            }
            move(secondPlayer, board);
            printBoard();
            if (checkWinner()) {
                win(secondPlayer);
                break;
            }
        }
    }

    private static void win(Player player) {
        System.out.println(String.format("Поздравляю %s! Ты выйграл!", player.getName()));
    }

    public static void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j]);
                if (j != size - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }


    public static void startBoard(int size, char[][] board) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void move(Player player, char[][] board) {
        System.out.println(String.format("Игрок %s, твой ход. Укажи координаты через Enter. Вначале горизнталь, затем вертикаль", player.getName()));
        int i = Integer.parseInt(Game.scanner.next()) - 1;
        int j = Integer.parseInt(Game.scanner.next()) - 1;
        if (board[i][j] == ' ') {
            board[i][j] = player.getType();
        } else {
            System.out.println("Это поле уже занято.");
            Game.move(player, board);
        }
    }

    public static boolean checkWinner() {
        return checkWinnerHorizontal() ||
                checkWinnerVertical() ||
                checkWinnerDiagonals();
    }

    private static boolean checkWinnerHorizontal() {
        for (int i = 0; i < size; i++) {
            boolean res = true;
            for (int j = 1; j < size && res; j++)
                res = board[i][j] == board[i][0] && board[i][j] != ' ';
            if (res)
                return true;
        }
        return false;
    }

    private static boolean checkWinnerVertical() {
        for (int i = 0; i < size; i++) {
            boolean res = true;
            for (int j = 1; j < size && res; j++)
                res = board[j][i] == board[0][i] && board[j][i] != ' ';
            if (res)
                return true;
        }
        return false;
    }

    private static boolean checkWinnerDiagonals() {
        boolean res = true;
        for (int i = 1; i < size && res; i++)
            res = board[i][i] == board[0][0] && board[i][i] != ' ';
        if (res)
            return true;
        res = true;
        for (int i = 1; i < size && res; i++)
            res = board[size - i - 1][i] == board[size - 1][0] && board[size - 1][0] != ' ';
        return res;
    }
}
