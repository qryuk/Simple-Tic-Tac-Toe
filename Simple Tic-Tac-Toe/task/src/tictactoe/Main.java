package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //initial
        String cells="         ";

        //update the cells
        while (analysisCells(cells)=="Game not finished"){
            printCells(cells);
            cells=move(cells);
        }

        //output the cells
        printCells(cells);
        System.out.println(analysisCells(cells));

    }


    public static String move(String cells){
        while (true){
            System.out.println("Enter the coordinates: ");

            Scanner scanner=new Scanner(System.in);

            //x_coordinate
            int x_coordinate=0;
            if(scanner.hasNextInt()){
                x_coordinate=scanner.nextInt();
                if(x_coordinate<1 || x_coordinate>3){
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
            }else {
                System.out.println("You should enter numbers!");
                continue;
            }

            //y_coordinate
            int y_coordinate=0;
            if(scanner.hasNextInt()){
                y_coordinate=scanner.nextInt();
                if(y_coordinate<1 || y_coordinate>3){
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
            }else {
                System.out.println("You should enter numbers!");
                continue;
            }


            //update the cells
            int index=(x_coordinate-1)*3+y_coordinate-1;

            if (cells.charAt(index)=='X' || cells.charAt(index)=='O'){
                System.out.println("This cell is occupied! Choose another one!");// string of index has X or O
            }else {
                int count=0;
                for (int i=0;i<cells.length();i++){
                    if (cells.charAt(i)=='X' || cells.charAt(i)=='O')
                        count++;
                }

                if (count%2==0){
                    return cells.substring(0, index) + "X" + cells.substring(index + 1);
                }else {
                    return cells.substring(0, index) + "O" + cells.substring(index + 1);
                }
            }
        }
    }

    public static void printCells(String cells){
        System.out.println("---------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(cells.charAt(i * 3 + j) + " ");
            }
            System.out.println("| ");
        }

        System.out.println("---------");
    }

    public static boolean impossible(String cells) {
        int numberOfX = 0;
        int numberOfO = 0;

        for (int i = 0; i < cells.length(); i++) {
            if (cells.charAt(i) == 'X') {
                numberOfX += 1;
            }
            if (cells.charAt(i) == 'O') {
                numberOfO += 1;
            }
        }

        int count = numberOfX - numberOfO;
        return count != 0 && Math.abs(count) != 1 || isWinner(cells,'X') && isWinner(cells,'O');
    }

    public static boolean isWinner(String cells, char ch) {
        if (cells.charAt(4) == ch) {
            return cells.charAt(1) == ch && cells.charAt(7) == ch ||
                    cells.charAt(3) == ch && cells.charAt(5) == ch ||
                    cells.charAt(0) == ch && cells.charAt(8) == ch ||
                    cells.charAt(2) == ch && cells.charAt(6) == ch;
        }

        if(cells.charAt(0)==ch){
            return cells.charAt(1)==ch && cells.charAt(2)==ch ||
                    cells.charAt(3)==ch && cells.charAt(6)==ch;
        }

        if (cells.charAt(8)==ch){
            return cells.charAt(2)==ch && cells.charAt(5)==ch ||
                    cells.charAt(6)==ch && cells.charAt(7)==ch;
        }

        return false;
    }

    public static String analysisCells(String cells){
        if (impossible(cells)) {
            return "Impossible";
        }else if(isWinner(cells,'X')){
            return "X wins";
        }else if(isWinner(cells,'O')){
            return "O wins";
        }else {
            for (int i=0;i<cells.length();i++){
                if (cells.charAt(i)=='_' || cells.charAt(i)==' '){
                    return "Game not finished";
                }
            }
            return "Draw";
        }
    }
}

