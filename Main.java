package com.nikhilaradya;

import java.lang.*;
import java.util.*;


public class Main {


    static int c = 0;
    static boolean done = false;
    static int choice = 1;
    static int  solutionNumber = 0;
    public static volatile boolean stop = false;
    public static long start=0;
    public static long end=0;
    public static boolean IsXSudoku = false;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        HashSet[][] sudoku = new HashSet[9][9];

        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                sudoku[i][j] = new HashSet<Integer>();
            }
        }

        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                sudoku[i][j].add(1);
                sudoku[i][j].add(2);
                sudoku[i][j].add(3);
                sudoku[i][j].add(4);
                sudoku[i][j].add(5);
                sudoku[i][j].add(6);
                sudoku[i][j].add(7);
                sudoku[i][j].add(8);
                sudoku[i][j].add(9);
            }

        }

        System.out.println("Input the entries below; Seperate each entry by a space and each row by a line. Put 0 for blank entries");

        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                try{
                int num = scan.nextInt();
                if (num == 0) continue;
                else if (num != 0) {
                    sudoku[i][j].clear();
                    sudoku[i][j].add(num);
                }}catch (Exception e){
                    System.out.println("\n\nPlease Enter valid input numbers"); System.exit(-1);
                }
            }
        }


//        System.out.println(isValid(sudoku));
//        System.exit(0);


        System.out.println("Press 1 if it's normal sudoku; Press 2 if it's \"X-sudoku\"");
        try {
            int ip = scan.nextInt();
            if (ip==1) IsXSudoku = false;
            else if (ip==2) IsXSudoku = true;
        }catch (Exception e){
            System.out.println("Bad input; Try again!"); System.exit(-1); ;
        }


        System.out.println("Press 1 to get only 1 solution; Press 2 to get multiple solutions (in case they exist)");
        try {
            int num1 = scan.nextInt();
            choice = num1;
        }catch (Exception e) {System.out.println("Bad input; Try again"); System.exit(-1);}

        if (!isIpValid(sudoku)){
            if (IsXSudoku) System.out.println("Invalid X-Sudoku!!");
            else System.out.println("Invalid Sudoku!!");
            System.exit(-1);
        }

        System.out.println("\t\tComputing...\n\n");

        HashSet[][] temporarySudoku = new HashSet[9][9];
        temporarySudoku = fn(sudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                sudoku[i][j] = temporarySudoku[i][j];
            }
        }


        HashSet[][] temporarySudoku1 = new HashSet[9][9];
        temporarySudoku1 = fn(sudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                sudoku[i][j] = temporarySudoku1[i][j];
            }
        }

        if (isValid(sudoku)) {StringBuilder sb = new StringBuilder("");
            System.out.println("Unique Solution!!\n");
            for (int i = 0; i <= 8; i++) {
                if (i%3==0 && i!=0) sb.append("_______________________________________________\n");
                for (int j = 0; j <= 8; j++) {
                    if (j%3==0 && j!=0){
                        sb.append("  |   ");
                    }
                    sb.append(sudoku[i][j]+" ");
                }//System.out.print(localSudoku2[i][j]);
                sb.append("\n"); //System.out.println();
            }
            System.out.println(sb.toString());
            System.out.println();
            done = true;
            System.out.println("Thanks:)"); return;
        }


        start = System.currentTimeMillis();



//////////////////////////////////////////////////////////////////////////////////////////
        if (choice==1){
            bf21(sudoku);
        }
        else if (choice==2){
            trd t = new trd();
            t.start();
            bf22(sudoku);
            }
        else {
            System.out.println("Bad input; Try again");
        }


        if (done == true) {
            System.out.println("Thanks:)"); System.exit(0);
        return;}

        else {
            if (IsXSudoku) System.out.println("Invalid X-Sudoku!!");
            else System.out.println("Invalid Sudoku!!");
                    System.exit(-1);
                    StringBuilder sb = new StringBuilder("");
                    for (int i = 0; i <= 8; i++) {
                        if (i%3==0 && i!=0) sb.append("_______________________________________________\n");
                        for (int j = 0; j <= 8; j++) {
                            if (j%3==0 && j!=0){
                                sb.append("  |   ");
                            }
                            sb.append(sudoku[i][j]+" ");
                        }
                        sb.append("\n");
                    }
                    System.out.println(sb.toString());
                    System.out.println();
        }

    }



    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static HashSet[][] row_compute(HashSet[][] sudokuNew) {
        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (localSudoku[i][j].size() == 1) {
                    for (int k = 0; k <= 8; k++) {
                        if (localSudoku[i][k].size() == 1) continue;
                        if (localSudoku[i][k].contains(localSudoku[i][j].toArray()[0])) {
                            localSudoku[i][k].remove((localSudoku[i][j].toArray()[0]));
                            HashSet[][] temporarySudoku = new HashSet[9][9];
                            temporarySudoku = fn(localSudoku);
                            for (int i1 = 0; i1 <= 8; i1++) {
                                for (int j1 = 0; j1 <= 8; j1++) {
                                    localSudoku[i1][j1] = (HashSet) temporarySudoku[i1][j1].clone();
                                    temporarySudoku[i1][j1].clear();
                                }
                            }
                        }
                    }
                }
            }
        }
        return localSudoku;
    }

    public static HashSet[][] row_compute2(HashSet[][] sudokuNew) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        for (int i = 0; i <= 8; i++) {
            for (int set = 1; set <= 9; set++) {
                int count = 0;
                int col = -1;
                for (int j = 0; j <= 8; j++) {
                    if ((localSudoku[i][j].size() > 1) && localSudoku[i][j].contains(set)) {
                        count++;
                        col = j;
                    }
                }
                if (count == 1) {
                    localSudoku[i][col].clear();
                    localSudoku[i][col].add(set);
                    HashSet[][] temporarySudoku = new HashSet[9][9];
                    temporarySudoku = fn(localSudoku);
                    for (int i1 = 0; i1 <= 8; i1++) {
                        for (int j1 = 0; j1 <= 8; j1++) {
                            localSudoku[i1][j1] = (HashSet) temporarySudoku[i1][j1].clone();
                            temporarySudoku[i1][j1].clear();
                        }
                    }
                }
            }
        }
        return localSudoku;
    }

    public static HashSet[][] col_compute(HashSet[][] sudokuNew) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (localSudoku[j][i].size() == 1) {
                    for (int k = 0; k <= 8; k++) {
                        if (localSudoku[k][i].size() == 1) continue;
                        if (localSudoku[k][i].contains(localSudoku[j][i].toArray()[0])) {
                            localSudoku[k][i].remove((localSudoku[j][i].toArray()[0]));
                            HashSet[][] temporarySudoku = new HashSet[9][9];
                            temporarySudoku = fn(localSudoku);
                            for (int i1 = 0; i1 <= 8; i1++) {
                                for (int j1 = 0; j1 <= 8; j1++) {
                                    localSudoku[i1][j1] = (HashSet) temporarySudoku[i1][j1].clone();
                                    temporarySudoku[i1][j1].clear();
                                }
                            }
                        }
                    }
                }
            }
        }
        return localSudoku;
    }

    public static HashSet[][] col_compute2(HashSet[][] sudokuNew) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        for (int i = 0; i <= 8; i++) {
            for (int set = 1; set <= 9; set++) {
                int count = 0;
                int row = -1;
                for (int j = 0; j <= 8; j++) {
                    if ((localSudoku[j][i].size() > 1) && localSudoku[j][i].contains(set)) {
                        count++;
                        row = j;
                    }
                }
                if (count == 1) {
                    localSudoku[row][i].clear();
                    localSudoku[row][i].add(set);
                    HashSet[][] temporarySudoku = new HashSet[9][9];
                    temporarySudoku = fn(localSudoku);
                    for (int i1 = 0; i1 <= 8; i1++) {
                        for (int j1 = 0; j1 <= 8; j1++) {
                            localSudoku[i1][j1] = (HashSet) temporarySudoku[i1][j1].clone();
                            temporarySudoku[i1][j1].clear();
                        }
                    }
                }
            }
        }
        return localSudoku;
    }

    public static HashSet[][] x_compute(HashSet[][] sudokuNew) {
        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        for (int i = 0; i <= 8; i++) {
            int j = i;
            if (localSudoku[i][j].size() == 1) {
                for (int ki = 0; ki <= 8; ki++) {
                    int kj = ki;
                    if (localSudoku[ki][kj].size() == 1) continue;
                    if (localSudoku[ki][kj].contains(localSudoku[i][j].toArray()[0])) {
                        localSudoku[ki][kj].remove((localSudoku[i][j].toArray()[0]));
                        HashSet[][] temporarySudoku = new HashSet[9][9];
                        temporarySudoku = fn(localSudoku);
                        for (int i1 = 0; i1 <= 8; i1++) {
                            for (int j1 = 0; j1 <= 8; j1++) {
                                localSudoku[i1][j1] = (HashSet) temporarySudoku[i1][j1].clone();
                                temporarySudoku[i1][j1].clear();
                            }
                        }
                    }
                }
            }
        }


        //////////////////////////////////////////////////////////////////////////////////////



        for (int i = 0; i <= 8; i++) {
            int j = 8-i;
            if (localSudoku[i][j].size() == 1) {
                for (int ki = 0; ki <= 8; ki++) {
                    int kj = 8-ki;
                    if (localSudoku[ki][kj].size() == 1) continue;
                    if (localSudoku[ki][kj].contains(localSudoku[i][j].toArray()[0])) {
                        localSudoku[ki][kj].remove((localSudoku[i][j].toArray()[0]));
                        HashSet[][] temporarySudoku = new HashSet[9][9];
                        temporarySudoku = fn(localSudoku);
                        for (int i1 = 0; i1 <= 8; i1++) {
                            for (int j1 = 0; j1 <= 8; j1++) {
                                localSudoku[i1][j1] = (HashSet) temporarySudoku[i1][j1].clone();
                                temporarySudoku[i1][j1].clear();
                            }
                        }
                    }
                }
            }
        }
        return localSudoku;
    }

    public static HashSet[][] box_compute(HashSet[][] sudokuNew) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }


        for (int k = 0; k <= 8; k++) {
            for (int i = 3 * (k / 3); i < (3 * (k / 3)) + 3; i++) {
                for (int j = (3 * (k % 3)); j < (3 * (k % 3)) + 3; j++) {
                    if (localSudoku[i][j].size() == 1) {
                        for (int z = (k / 3) * 3; z < ((k / 3) * 3) + 3; z++) {
                            for (int y = ((k % 3) * 3); y < ((k % 3) * 3) + 3; y++) {
                                if (localSudoku[z][y].size() == 1) continue;
                                if (localSudoku[z][y].size() != 1) {
                                    if (localSudoku[z][y].contains(localSudoku[i][j].toArray()[0])) {
                                        localSudoku[z][y].remove(localSudoku[i][j].toArray()[0]);
                                        HashSet[][] temporarySudoku = new HashSet[9][9];
                                        temporarySudoku = fn(localSudoku);
                                        for (int i1 = 0; i1 <= 8; i1++) {
                                            for (int j1 = 0; j1 <= 8; j1++) {
                                                localSudoku[i1][j1] = (HashSet) temporarySudoku[i1][j1].clone();
                                                temporarySudoku[i1][j1].clear();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return localSudoku;
    }

    public static HashSet[][] fn(HashSet[][] sudokuNew) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }
        HashSet[][] temporarySudoku = new HashSet[9][9];
        temporarySudoku = row_compute(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku[i][j].clone();
                temporarySudoku[i][j].clear();
            }
        }


        temporarySudoku = row_compute2(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku[i][j].clone();
                temporarySudoku[i][j].clear();
            }
        }


        temporarySudoku = col_compute(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku[i][j].clone();
                temporarySudoku[i][j].clear();
            }
        }

        temporarySudoku = col_compute2(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku[i][j].clone();
                temporarySudoku[i][j].clear();
            }
        }

        if (IsXSudoku) {
            temporarySudoku = x_compute(localSudoku);
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    localSudoku[i][j] = (HashSet) temporarySudoku[i][j].clone();
                    temporarySudoku[i][j].clear();
                }
            }
        }

        temporarySudoku = box_compute(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku[i][j].clone();
                temporarySudoku[i][j].clear();
            }
        }



        temporarySudoku = compare_row(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku[i][j].clone();
                temporarySudoku[i][j].clear();
            }
        }

        temporarySudoku = compare_col(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku[i][j].clone();
                temporarySudoku[i][j].clear();
            }
        }

        temporarySudoku = row_compute3(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku[i][j].clone();
                temporarySudoku[i][j].clear();
            }
        }

        temporarySudoku = col_compute3(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku[i][j].clone();
                temporarySudoku[i][j].clear();
            }
        }

//        temporarySudoku = x_wing_row(localSudoku);
//        for (int i = 0; i <= 8; i++) {
//            for (int j = 0; j <= 8; j++) {
//                localSudoku[i][j] = (HashSet) temporarySudoku[i][j].clone();
//                temporarySudoku[i][j].clear();
//            }
//        }
//
//        temporarySudoku = x_wing_col(localSudoku);
//        for (int i = 0; i <= 8; i++) {
//            for (int j = 0; j <= 8; j++) {
//                localSudoku[i][j] = (HashSet) temporarySudoku[i][j].clone();
//                temporarySudoku[i][j].clear();
//            }
//        }


        for (int kala = 2; kala <= 9; kala++) {

            temporarySudoku = row_pair2_2(localSudoku, kala);
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    localSudoku[i][j] = (HashSet) temporarySudoku[i][j].clone();
                    temporarySudoku[i][j].clear();
                }
            }
        }

        for (int kala = 2; kala <= 9; kala++) {

            temporarySudoku = col_pair2_2(localSudoku, kala);
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    localSudoku[i][j] = (HashSet) temporarySudoku[i][j].clone();
                    temporarySudoku[i][j].clear();
                }
            }
        }

        for (int kala = 2; kala <= 9; kala++) {

            temporarySudoku = box_pair2_2(localSudoku, kala);
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    localSudoku[i][j] = (HashSet) temporarySudoku[i][j].clone();
                    temporarySudoku[i][j].clear();
                }
            }
        }

        return localSudoku;
    }

    public static HashSet[][] compare_row(HashSet[][] sudokuNew) {
        // box by box same row -> delete from the row in next boxes

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }


        for (int k = 0; k <= 8; k++) {
            for (int set = 1; set <= 9; set++) {
                int count = 0;
                int[] r = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
                int itr = 0;
                for (int i = 3 * (k / 3); i < (3 * (k / 3)) + 3; i++) {
                    for (int j = (3 * (k % 3)); j < (3 * (k % 3)) + 3; j++) {
                        if (localSudoku[i][j].size() > 1 && localSudoku[i][j].contains(set)) {
                            count++;
                            r[itr++] = i;
                        }
                    }
                }

                if ((count == 2) && (r[0] == r[1]) && (r[2] == -1)) {
                    for (int niki1 = (k / 3) * 3; niki1 < ((k / 3) * 3) + 3; niki1++) {
                        if (niki1 == k) continue;

                        for (int j = (3 * (niki1 % 3)); j < (3 * (niki1 % 3)) + 3; j++) {
                            if ((localSudoku[r[0]][j].contains(set)) && localSudoku[r[0]][j].size() > 1) {
                                localSudoku[r[0]][j].remove(set);
                                HashSet[][] temporarySudoku = new HashSet[9][9];
                                temporarySudoku = fn(localSudoku);
                                for (int i1 = 0; i1 <= 8; i1++) {
                                    for (int j1 = 0; j1 <= 8; j1++) {
                                        localSudoku[i1][j1] = (HashSet) temporarySudoku[i1][j1].clone();
                                        temporarySudoku[i1][j1].clear();
                                    }
                                }
                            }
                        }
                    }
                }

                else if ((count == 3) && (r[0] == r[1]) && (r[1] == r[2])) {
                    for (int niki1 = (k / 3) * 3; niki1 < ((k / 3) * 3) + 3; niki1++) {
                        if (niki1 == k) continue;

                        for (int j = (3 * (niki1 % 3)); j < (3 * (niki1 % 3)) + 3; j++) {
                            if ((localSudoku[r[0]][j].contains(set)) && localSudoku[r[0]][j].size() > 1) {
                                localSudoku[r[0]][j].remove(set);
                                HashSet[][] temporarySudoku = new HashSet[9][9];
                                temporarySudoku = fn(localSudoku);
                                for (int i1 = 0; i1 <= 8; i1++) {
                                    for (int j1 = 0; j1 <= 8; j1++) {
                                        localSudoku[i1][j1] = (HashSet) temporarySudoku[i1][j1].clone();
                                        temporarySudoku[i1][j1].clear();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return localSudoku;
    }

    public static HashSet[][] compare_col(HashSet[][] sudokuNew) {
        // box by box same col -> delete from the col in next boxes

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }


        for (int k = 0; k <= 8; k++) {
            for (int set = 1; set <= 9; set++) {
                int count = 0;
                int[] c = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
                int itr = 0;
                for (int i = 3 * (k / 3); i < (3 * (k / 3)) + 3; i++) {
                    for (int j = (3 * (k % 3)); j < (3 * (k % 3)) + 3; j++) {
                        if (localSudoku[i][j].size() > 1 && localSudoku[i][j].contains(set)) {
                            count++;
                            c[itr++] = j;
                        }
                    }
                }

                if (count == 2 && c[0] == c[1] && c[2] == -1) {
                    for (int niki1 = (k % 3); niki1 <= (((k % 3)) + 6); niki1 += 3) {
                        if (niki1 == k) continue;

                        for (int i = (3 * (niki1 / 3)); i < (3 * (niki1 / 3)) + 3; i++) {
                            if ((localSudoku[i][c[0]].contains(set)) && localSudoku[i][c[0]].size() > 1) {
                                localSudoku[i][c[0]].remove(set);
                                HashSet[][] temporarySudoku = new HashSet[9][9];
                                temporarySudoku = fn(localSudoku);
                                for (int i1 = 0; i1 <= 8; i1++) {
                                    for (int j1 = 0; j1 <= 8; j1++) {
                                        localSudoku[i1][j1] = (HashSet) temporarySudoku[i1][j1].clone();
                                        temporarySudoku[i1][j1].clear();
                                    }
                                }
                            }
                        }
                    }
                }

                else if (count == 3 && c[0] == c[1] && c[1] == c[2]) {
                    for (int niki1 = (k % 3); niki1 <= ((k % 3) + 6); niki1 += 3) {
                        if (niki1 == k) continue;

                        for (int i = (3 * (niki1 / 3)); i < (3 * (niki1 / 3)) + 3; i++) {
                            if ((localSudoku[i][c[0]].contains(set)) && localSudoku[i][c[0]].size() > 1) {
                                localSudoku[i][c[0]].remove(set);
                                HashSet[][] temporarySudoku = new HashSet[9][9];
                                temporarySudoku = fn(localSudoku);
                                for (int i1 = 0; i1 <= 8; i1++) {
                                    for (int j1 = 0; j1 <= 8; j1++) {
                                        localSudoku[i1][j1] = (HashSet) temporarySudoku[i1][j1].clone();
                                        temporarySudoku[i1][j1].clear();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return localSudoku;
    }

    public static boolean isValid(HashSet[][] sudokuNew) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        boolean flag = true;

        // size
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (localSudoku[i][j].size() != 1) flag = false;
            }
        }
        if (flag == false) return false;

        // row count number
        for (int i = 0; i <= 8; i++) {
            for (int set = 1; set <= 9; set++) {
                int count = 0;
                for (int j = 0; j <= 8; j++) {
                    if (localSudoku[i][j].toArray()[0].equals(set)) count++;
                }
                if (count != 1) return false;
            }
        }

        // column count number
        for (int i = 0; i <= 8; i++) {
            for (int set = 1; set <= 9; set++) {
                int count = 0;
                for (int j = 0; j <= 8; j++) {
                    if (localSudoku[j][i].toArray()[0].equals(set)) count++;
                }
                if (count != 1) return false;
            }
        }

        // box count number
        for (int k = 0; k <= 8; k++) {
            for (int set = 1; set <= 9; set++) {
                int count = 0;
                for (int i = (3 * (k / 3)); i < ((3 * (k / 3)) + 3); i++) {
                    for (int j = (3 * (k % 3)); j < ((3 * (k % 3)) + 3); j++) {
                        if (localSudoku[i][j].toArray()[0].equals(set)) count++;
                    }
                }
                if (count != 1) return false;
            }
        }

        // x count number
        if (IsXSudoku) {
            for (int set = 1; set <= 9; set++) {
                int count = 0;
                for (int i = 0; i <= 8; i++) {
                    int j = i;
                    if (localSudoku[i][j].toArray()[0].equals(set)) count++;
                }
                if (count != 1) return false;
            }

            for (int set = 1; set <= 9; set++) {
                int count = 0;
                for (int i = 0; i <= 8; i++) {
                    int j = 8 - i;
                    if (localSudoku[i][j].toArray()[0].equals(set)) count++;
                }
                if (count != 1) return false;
            }
        }

        // domain of input
        for(int i=0; i<=8; i++){
            for (int j=0; j<=8; j++){
                if(((Integer) localSudoku[i][j].toArray()[0]<1) ||(((Integer) localSudoku[i][j].toArray()[0]>9))){
                    return false;
                }
            }
        }

        done = true;
        return true;
    }

    public static boolean isIpValid(HashSet[][] sudokuNew) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        boolean flag = true;

        for (int set = 1; set<=9; set++) {

            // row
            for (int i = 0; i <= 8; i++) {
                int count = 0;
                for (int j = 0; j <= 8; j++) {
                    if (localSudoku[i][j].size() == 1 && localSudoku[i][j].toArray()[0].equals(set)) count++;
                }
                if (count > 1) return false;
            }

            //col
            for (int j = 0; j <= 8; j++) {
                int count = 0;
                for (int i = 0; i <= 8; i++) {
                    if (localSudoku[i][j].size() == 1 && localSudoku[i][j].toArray()[0].equals(set)) count++;
                }
                if (count > 1) return false;
            }

            //box
            for (int k = 0; k <= 8; k++) {
                int count = 0;
                for (int i = (3 * (k / 3)); i < ((3 * (k / 3)) + 3); i++) {
                    for (int j = (3 * (k % 3)); j < ((3 * (k % 3)) + 3); j++) {
                        if (localSudoku[i][j].size() == 1 && localSudoku[i][j].toArray()[0].equals(set)) count++;
                    }
                }
                if (count > 1) return false;
            }


            if (IsXSudoku) {
                int count = 0;
                for (int i = 0; i <= 8; i++) {
                    int j = i;
                    if (localSudoku[i][j].size() == 1 && localSudoku[i][j].toArray()[0].equals(set)) count++;
                }
                if (count > 1) return false;

                count = 0;
                for (int i = 0; i <= 8; i++) {
                    int j = 8 - i;
                    if (localSudoku[i][j].size() == 1 && localSudoku[i][j].toArray()[0].equals(set)) count++;
                }
                if (count > 1) return false;
            }
        }
        return true;
    }

    public static HashSet[][] row_compute3(HashSet[][] sudokuNew) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        for (int i = 0; i <= 8; i++) {
            for (int set = 1; set <= 9; set++) {
                int count = 0;
                int col[] = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
                int itr = 0;
                for (int j = 0; j <= 8; j++) {
                    if (localSudoku[i][j].size() > 1) {
                        if (localSudoku[i][j].contains(set)) {
                            count++;
                            col[itr] = j;
                            itr++;
                        }
                    }
                }


                if (count == 2) {
                    if (((col[0]) / 3) == ((col[1]) / 3)) {
                        //do delete from box other rows
                        for (int i1 = (3 * (i / 3)); i1 < (3 * ((i / 3)) + 3); i1++) {
                            if (i1 == i) continue;
                            for (int j1 = (3 * ((col[0]) / 3)); j1 < (3 * ((col[0]) / 3) + 3); j1++) {

                                if (localSudoku[i1][j1].size() > 1 && localSudoku[i1][j1].contains(set)) {
                                    localSudoku[i1][j1].remove(set);
                                    HashSet[][] temporarySudokuniki = new HashSet[9][9];
                                    temporarySudokuniki = fn(localSudoku);
                                    for (int i2 = 0; i2 <= 8; i2++) {
                                        for (int j2 = 0; j2 <= 8; j2++) {
                                            localSudoku[i2][j2] = (HashSet) temporarySudokuniki[i2][j2].clone();
                                            temporarySudokuniki[i2][j2].clear();
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (count == 3) {
                    if ((((col[0]) / 3) == ((col[1]) / 3)) && (((col[0]) / 3) == ((col[2]) / 3))) {
                        //do delete from box other rows
                        for (int i1 = (3 * i / 3); i1 < ((3 * (i / 3)) + 3); i1++) {
                            if (i1 == i) continue;
                            for (int j1 = (3 * ((col[0]) / 3)); j1 < ((3 * ((col[0]) / 3)) + 3); j1++) {
                                if (localSudoku[i1][j1].size() > 1 && localSudoku[i1][j1].contains(set)) {
                                    localSudoku[i1][j1].remove(set);
                                    HashSet[][] temporarySudokuniki1 = new HashSet[9][9];
                                    temporarySudokuniki1 = fn(localSudoku);
                                    for (int i2 = 0; i2 <= 8; i2++) {
                                        for (int j2 = 0; j2 <= 8; j2++) {
                                            localSudoku[i2][j2] = (HashSet) temporarySudokuniki1[i2][j2].clone();
                                            temporarySudokuniki1[i2][j2].clear();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return localSudoku;
    }

    public static HashSet[][] col_compute3(HashSet[][] sudokuNew) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        for (int i = 0; i <= 8; i++) {
            for (int set = 1; set <= 9; set++) {
                int count = 0;
                int row[] = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
                int itr = 0;
                for (int j = 0; j <= 8; j++) {
                    if (localSudoku[j][i].size() > 1) {
                        if (localSudoku[j][i].contains(set)) {
                            count++;
                            row[itr] = j;
                            itr++;
                        }
                    }
                }

                if (count == 2) {
                    if (((row[0]) / 3) == ((row[1]) / 3)) {
                        //do delete from box other rows
                        for (int i1 = (3 * (i / 3)); i1 < (3 * ((i / 3)) + 3); i1++) {
                            if (i1 == i) continue;
                            for (int j1 = (3 * ((row[0]) / 3)); j1 < (3 * ((row[0]) / 3) + 3); j1++) {

                                if (localSudoku[j1][i1].size() > 1 && localSudoku[j1][i1].contains(set)) {
                                    localSudoku[j1][i1].remove(set);
                                    HashSet[][] temporarySudokuniki = new HashSet[9][9];
                                    temporarySudokuniki = fn(localSudoku);
                                    for (int i2 = 0; i2 <= 8; i2++) {
                                        for (int j2 = 0; j2 <= 8; j2++) {
                                            localSudoku[i2][j2] = (HashSet) temporarySudokuniki[i2][j2].clone();
                                            temporarySudokuniki[i2][j2].clear();
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (count == 3) {
                    if ((((row[0]) / 3) == ((row[1]) / 3)) && (((row[0]) / 3) == ((row[2]) / 3))) {
                        //do delete from box other rows
                        for (int i1 = (3 * (i / 3)); i1 < ((3 * ((i / 3))) + 3); i1++) {
                            if (i1 == i) continue;
                            for (int j1 = (3 * ((row[0]) / 3)); j1 < ((3 * ((row[0]) / 3)) + 3); j1++) {
                                if (localSudoku[j1][i1].size() > 1 && localSudoku[j1][i1].contains(set)) {
                                    localSudoku[j1][i1].remove(set);
                                    HashSet[][] temporarySudokuniki1 = new HashSet[9][9];
                                    temporarySudokuniki1 = fn(localSudoku);
                                    for (int i2 = 0; i2 <= 8; i2++) {
                                        for (int j2 = 0; j2 <= 8; j2++) {
                                            localSudoku[i2][j2] = (HashSet) temporarySudokuniki1[i2][j2].clone();
                                            temporarySudokuniki1[i2][j2].clear();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return localSudoku;
    }

    public static HashSet[][] x_wing_row(HashSet[][] sudokuNew) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }


        for (int set = 1; set<=9; set++){
            HashMap<Integer, HashSet> hashMap = new HashMap<Integer, HashSet>();

            for (int i = 0; i<=8; i++){
                HashSet hashSet = new HashSet();
                for (int j=0; j<=8; j++){
                    if (localSudoku[i][j].contains(set)) hashSet.add(j);
                }
                if (hashSet.size()==2) hashMap.put(i, hashSet);
            }

            Object[] arr= hashMap.keySet().toArray();

            for (int i1 = 0; i1<arr.length; i1++){
                for (int i2=i1+1; i2<arr.length; i2++){
                    if(hashMap.get((Integer) arr[i1]).equals(hashMap.get((Integer) arr[i2]))){
                        HashSet hashSet = hashMap.get((Integer) arr[i1]);
                        // delete in cols of hshset
                        Iterator iterator = hashSet.iterator();
                        while (iterator.hasNext()){
                            Integer col = (Integer) iterator.next();
                            for (int i = 0; i<=8; i++){
                                if (hashMap.keySet().contains(i)){
                                if (hashMap.get(i).equals(hashSet)) continue;
                                }
                                if (localSudoku[i][col].contains(set) && localSudoku[i][col].size()>1){
                                    if (i==i1 || i==i2) continue;
                                    localSudoku[i][col].remove(set);
                                    //
                                    HashSet[][] temporarySudoku = new HashSet[9][9];
                                    temporarySudoku = fn(localSudoku);
                                    for (int i3 = 0; i3 <= 8; i3++) {
                                        for (int j3 = 0; j3 <= 8; j3++) {
                                            localSudoku[i3][j3] = (HashSet) temporarySudoku[i3][j3].clone();
                                            temporarySudoku[i3][j3].clear();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

return localSudoku;
    }

    public static HashSet[][] x_wing_col(HashSet[][] sudokuNew) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }


        for (int set = 1; set<=9; set++){
            HashMap<Integer, HashSet> hashMap = new HashMap<Integer, HashSet>();

            for (int j = 0; j<=8; j++){
                HashSet hashSet = new HashSet();
                for (int i=0; i<=8; i++){
                    if (localSudoku[i][j].contains(set)) hashSet.add(i);
                }
                if (hashSet.size()==2) hashMap.put(j, hashSet);
            }

            Object[] arr= hashMap.keySet().toArray();

            for (int j1 = 0; j1<arr.length; j1++){
                for (int j2=j1+1; j2<arr.length; j2++){
                    if(hashMap.get((Integer) arr[j1]).equals(hashMap.get((Integer) arr[j2]))){
                        HashSet hashSet = hashMap.get((Integer) arr[j1]);
                        // delete in rows of hashset
                        Iterator iterator = hashMap.get((Integer) arr[j1]).iterator();
                        while (iterator.hasNext()){
                            Integer row = (Integer) iterator.next();
                            for (int j = 0; j<=8; j++){
                                if (hashMap.keySet().contains(j)){
                                    if (hashMap.get(j).equals(hashSet)) continue;
                                }
                                if (localSudoku[row][j].contains(set) && localSudoku[row][j].size()>1){
                                    if (j==j1 || j==j2) continue;
                                    localSudoku[row][j].remove(set);

                                    HashSet[][] temporarySudoku = new HashSet[9][9];
                                    temporarySudoku = fn(localSudoku);
                                    for (int i3 = 0; i3 <= 8; i3++) {
                                        for (int j3 = 0; j3 <= 8; j3++) {
                                            localSudoku[i3][j3] = (HashSet) temporarySudoku[i3][j3].clone();
                                            temporarySudoku[i3][j3].clear();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        return localSudoku;
    }

    public static HashSet[][] row_pair2(HashSet[][] sudokuNew, int kala) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        int count = 0;
        int probe = 0;
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (localSudoku[i][j].size() == kala) {
                    count++;
                }
            }

            if (count >= kala) {
                for (int k = 1; k <= count - 1; k++) {
                    probe = 0;
                    HashSet temporarySudoku = new HashSet();
                    for (int j = 0; j <= 8; j++) {
                        if (localSudoku[i][j].size() == kala) {
                            probe++;
                        }
                        if (probe == k && localSudoku[i][j].size() == kala) {
                            int count1 = 0;
                            temporarySudoku = (HashSet) localSudoku[i][j].clone();
                            for (int nik = 0; nik <= 8; nik++) {
                                if (localSudoku[i][nik].equals(temporarySudoku)) count1++;
                            }
                            if (count1 == kala) {
                                Iterator iterator = temporarySudoku.iterator();
                                for (int j2 = 0; j2 <= 8; j2++) {
                                    if ((!localSudoku[i][j2].equals(temporarySudoku)) && localSudoku[i][j2].size() > 1) {
                                        while (iterator.hasNext()) {
                                            Integer num = (Integer) iterator.next();
                                            if (localSudoku[i][j2].contains(num)) {
                                                localSudoku[i][j2].remove(num);
                                                HashSet[][] temporarySudokuniki1 = new HashSet[9][9];
                                                temporarySudokuniki1 = fn(localSudoku);
                                                for (int i3 = 0; i3 <= 8; i3++) {
                                                    for (int j3 = 0; j3 <= 8; j3++) {
                                                        localSudoku[i3][j3] = (HashSet) temporarySudokuniki1[i3][j3].clone();
                                                        temporarySudokuniki1[i3][j3].clear();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                // do delete from full row, the elements of pair
                            }
                        }
                    }


                }
            }
        }

        return localSudoku;
    }

    public static HashSet[][] col_pair2(HashSet[][] sudokuNew, int kala) {
        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        int count = 0;
        int probe = 0;
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (localSudoku[j][i].size() == kala) {
                    count++;
                }
            }

            if (count >= kala) {
                for (int k = 1; k <= count - 1; k++) {
                    probe = 0;
                    HashSet temporarySudoku = new HashSet();
                    for (int j = 0; j <= 8; j++) {
                        if (localSudoku[j][i].size() == kala) {
                            probe++;
                        }
                        if (probe == k && localSudoku[j][i].size() == kala) {
                            int count1 = 0;
                            temporarySudoku = (HashSet) localSudoku[j][i].clone();
                            for (int nik = 0; nik <= 8; nik++) {
                                if (localSudoku[nik][i].equals(temporarySudoku)) count1++;
                            }
                            if (count1 == kala) {
                                Iterator iterator = temporarySudoku.iterator();
                                for (int j2 = 0; j2 <= 8; j2++) {
                                    if ((!localSudoku[j2][i].equals(temporarySudoku)) && localSudoku[j2][i].size() > 1) {
                                        while (iterator.hasNext()) {
                                            Integer num = (Integer) iterator.next();
                                            if (localSudoku[j2][i].contains(num)) {
                                                localSudoku[j2][i].remove(num);
                                                HashSet[][] temporarySudokuniki1 = new HashSet[9][9];
                                                temporarySudokuniki1 = fn(localSudoku);
                                                for (int i3 = 0; i3 <= 8; i3++) {
                                                    for (int j3 = 0; j3 <= 8; j3++) {
                                                        localSudoku[i3][j3] = (HashSet) temporarySudokuniki1[i3][j3].clone();
                                                        temporarySudokuniki1[i3][j3].clear();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                // do delete from full row, the elements of pair
                            }
                        }
                    }


                }
            }
        }

        return localSudoku;
    }

    public static HashSet[][] box_pair2(HashSet[][] sudokuNew, int kala) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        for (int k = 0; k <= 8; k++) {
            int count = 0;
            int probe = 0;
            for (int i = (3 * (k / 3)); i < (3 * (k / 3)) + 3; i++) {
                for (int j = (3 * (k % 3)); j < (3 * (k % 3)) + 3; j++) {
                    if (localSudoku[i][j].size() == kala) count++;
                }
            }

            if (count >= kala) {
                for (int z = 1; z <= count - 1; z++) {
                    probe = 0;
                    HashSet temporarySudoku = new HashSet();
                    for (int i = (3 * (k / 3)); i < ((3 * (k / 3)) + 3); i++) {
                        for (int j = (3 * (k % 3)); j < ((3 * (k % 3)) + 3); j++) {
                            if (localSudoku[i][j].size() == kala) probe++;

                            if (z == probe && localSudoku[i][j].size() == kala) {
                                int count1 = 0;
                                temporarySudoku = (HashSet) localSudoku[i][j].clone();
                                for (int niki1 = (3 * (k / 3)); niki1 < ((3 * (k / 3)) + 3); niki1++) {
                                    for (int niki2 = (3 * (k % 3)); niki2 < ((3 * (k % 3)) + 3); niki2++) {
                                        if (localSudoku[niki1][niki2].equals(temporarySudoku)) count1++;
                                    }
                                }
                                if (count1 == kala) {
                                    Iterator iterator = temporarySudoku.iterator();
                                    for (int i2 = (3 * (k / 3)); i2 < (3 * (k / 3)) + 3; i2++) {
                                        for (int j2 = (3 * (k % 3)); j2 < (3 * (k % 3)) + 3; j2++) {
                                            if ((!localSudoku[i2][j2].equals(temporarySudoku)) && localSudoku[i2][j2].size() > 1) {

                                                while (iterator.hasNext()) {
                                                    Integer num = (Integer) iterator.next();
                                                    if (localSudoku[i2][j2].contains(num)) {
                                                        localSudoku[i2][j2].remove(num);
                                                        HashSet[][] temporarySudokuniki1 = new HashSet[9][9];
                                                        temporarySudokuniki1 = fn(localSudoku);
                                                        for (int i3 = 0; i3 <= 8; i3++) {
                                                            for (int j3 = 0; j3 <= 8; j3++) {
                                                                localSudoku[i3][j3] = (HashSet) temporarySudokuniki1[i3][j3].clone();
                                                                temporarySudokuniki1[i3][j3].clear();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }


                        }

                    }
                }
            }
        }

        return localSudoku;
    }

    public static HashSet[][] row_pair2_2(HashSet[][] sudokuNew, int num) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        for (int i = 0; i <= 8; i++) {

            TreeMap<Integer, HashSet> treemap = new TreeMap<Integer, HashSet>();
            for (int set = 1; set <= 9; set++) { HashSet hashSet = new HashSet();
                for (int j = 0; j <= 8; j++) {
                    if (localSudoku[i][j].contains(set)) {
                        hashSet.add(j);
                    }
                }
                treemap.put(set, hashSet);
            }

            Iterator iterator = treemap.keySet().iterator(); HashSet pairSet = new HashSet();
            while (iterator.hasNext()){
                Integer temp = (Integer) iterator.next();
                if (treemap.get(temp).size()==num){pairSet.add(temp);}
            }

            Iterator iteratorn1 = pairSet.iterator();
            while (iteratorn1.hasNext()) {

                int count = 0;
                HashSet temperoryHashSet = (HashSet) pairSet.clone();
                HashSet group = new HashSet();
                Integer temp = (Integer) iteratorn1.next();

                Iterator iterator2 = temperoryHashSet.iterator();

                while (iterator2.hasNext()) {
                    Integer temp2 = (Integer) iterator2.next();
                    if (treemap.get(temp).equals(treemap.get(temp2))) {
                        count++;
                        group.add(temp2);
                    }
                }
                if (count == num){
                    for (int j = 0; j <= 8; j++) {
                        if (treemap.get(temp).contains(j) && localSudoku[i][j].size()>num) {
                            localSudoku[i][j].clear();
                            localSudoku[i][j].addAll(group);
                            HashSet[][] temporarySudoku = new HashSet[9][9];
                            temporarySudoku = fn(localSudoku);
                            for (int i3 = 0; i3 <= 8; i3++) {
                                for (int j3 = 0; j3 <= 8; j3++) {
                                    localSudoku[i3][j3] = (HashSet) temporarySudoku[i3][j3].clone();
                                    temporarySudoku[i3][j3].clear();
                                }
                            }
                        }
                    }
                }
            }

        }
        return localSudoku;
    }

    public static HashSet[][] col_pair2_2(HashSet[][] sudokuNew, int num) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        for (int j = 0; j <= 8; j++) {
            TreeMap<Integer, HashSet> treemap = new TreeMap<Integer, HashSet>();
            for (int set = 1; set <= 9; set++) { HashSet hashSet = new HashSet();
                for (int i = 0; i <= 8; i++) {
                    if (localSudoku[i][j].contains(set)) {
                        hashSet.add(j);
                    }
                }
                treemap.put(set, hashSet);
            }

            Iterator iterator = treemap.keySet().iterator(); HashSet pairSet = new HashSet();
            while (iterator.hasNext()){
                Integer temp = (Integer) iterator.next();
                if (treemap.get(temp).size()==num){pairSet.add(temp);}
            }


            Iterator iteratorn1 = pairSet.iterator();
            while (iteratorn1.hasNext()) {
                int count = 0;
                HashSet temperoryHashSet = (HashSet) pairSet.clone();
                HashSet group = new HashSet();
                Integer temp = (Integer) iteratorn1.next();

                Iterator iterator2 = temperoryHashSet.iterator();
                while (iterator2.hasNext()) {
                    Integer temp2 = (Integer) iterator2.next();

                    if (treemap.get(temp).equals(treemap.get(temp2))) {
                        count++;
                        group.add(temp2);
                    }
                }
                if (count == num){

                    for (int i = 0; i <= 8; i++) {
                        if (treemap.get(temp).contains(i) && localSudoku[i][j].size()>num) {
                            localSudoku[i][j].clear();
                            localSudoku[i][j].addAll(group);
                            HashSet[][] temporarySudoku = new HashSet[9][9];
                            temporarySudoku = fn(localSudoku);
                            for (int i3 = 0; i3 <= 8; i3++) {
                                for (int j3 = 0; j3 <= 8; j3++) {
                                    localSudoku[i3][j3] = (HashSet) temporarySudoku[i3][j3].clone();
                                    temporarySudoku[i3][j3].clear();
                                }
                            }
                        }
                    }
                }
            }

        }
        return localSudoku;
    }

    public static HashSet[][] box_pair2_2(HashSet[][] sudokuNew, int num) {

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        for (int k=0; k<=8; k++){

            TreeMap<Integer, HashSet> treemap = new TreeMap<Integer, HashSet>();
            for (int set = 1; set <= 9; set++) { HashSet hashSet = new HashSet();
                for (int i = (3 * (k / 3)); i < ((3 * (k / 3)) + 3); i++) {
                    for (int j = (3 * (k % 3)); j < ((3 * (k % 3)) + 3); j++) {
                        if (localSudoku[i][j].contains(set)) {
                            hashSet.add(((9*i)+j));
                        }
                    }
                }
                treemap.put(set, hashSet);
            }

            Iterator iterator = treemap.keySet().iterator(); HashSet pairSet = new HashSet();
            while (iterator.hasNext()){
                Integer temp = (Integer) iterator.next();
                if (treemap.get(temp).size()==num){pairSet.add(temp);}
            }


            Iterator iteratorn1 = pairSet.iterator();
            while (iteratorn1.hasNext()) {

                int count = 0;
                HashSet temperoryHashSet = (HashSet) pairSet.clone();
                HashSet group = new HashSet();
                Integer temp = (Integer) iteratorn1.next();

                Iterator iterator2 = temperoryHashSet.iterator();
                while (iterator2.hasNext()) {
                    Integer temp2 = (Integer) iterator2.next();

                    if (treemap.get(temp).equals(treemap.get(temp2))) {
                        count++;
                        group.add(temp2);
                    }
                }
                if (count == num){
                    for (int i = (3 * (k / 3)); i < ((3 * (k / 3)) + 3); i++) {
                        for (int j = (3 * (k % 3)); j < ((3 * (k % 3)) + 3); j++) {
                            if ((treemap.get(temp).contains(((9 * i) + j))) && localSudoku[i][j].size()>num) {
                                localSudoku[i][j].clear();
                                localSudoku[i][j].addAll(group);
                                HashSet[][] temporarySudoku = new HashSet[9][9];
                                temporarySudoku = fn(localSudoku);
                                for (int i3 = 0; i3 <= 8; i3++) {
                                    for (int j3 = 0; j3 <= 8; j3++) {
                                        localSudoku[i3][j3] = (HashSet) temporarySudoku[i3][j3].clone();
                                        temporarySudoku[i3][j3].clear();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
//        System.out.println("return");
        return localSudoku;
    }

    public static void bf1(HashSet[][] sudokuNew) {

        end = System.currentTimeMillis();
        if (((end - start)>(20*1000)) &&(done == false) || (stop == true)){return;}
        c = 0;
        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (localSudoku[i][j].size() > 1) {
                    Iterator iterator = localSudoku[i][j].iterator();
                    while (iterator.hasNext()) {
                        Integer num = (Integer) iterator.next();
                        HashSet[][] temporarySudoku = new HashSet[9][9];
                        for (int i1 = 0; i1 <= 8; i1++) {
                            for (int j1 = 0; j1 <= 8; j1++) {
                                temporarySudoku[i1][j1] = (HashSet) localSudoku[i1][j1].clone();
                            }
                        }

                        temporarySudoku[i][j].clear();
                        temporarySudoku[i][j].add(num);
                        HashSet[][] temporarySudoku2 = new HashSet[9][9];
                        temporarySudoku2 = fn(temporarySudoku);
                        for (int i1 = 0; i1 <= 8; i1++) {
                            for (int j1 = 0; j1 <= 8; j1++) {
                                temporarySudoku[i1][j1] = (HashSet) temporarySudoku2[i1][j1].clone();
                            }
                        }
                        if (isValid(temporarySudoku)) {
                            done = true;
                            System.out.println("Solution No.:"+ ++solutionNumber);
                            for (int i1 = 0; i1 <= 8; i1++) {
                                for (int j1 = 0; j1 <= 8; j1++) System.out.print(temporarySudoku[i1][j1]);
                                System.out.println();
                            }
                            return;
                        } //else System.out.println(c++);
                    }
                }
            }
        }
    }

    public static void bf21(HashSet[][] sudokuNew) {

        end = System.currentTimeMillis();
        if (((end - start)>(60*1000)) &&(done == false) || (stop == true)){return;}

        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        HashSet temporarySudoku = new HashSet();
        int row = -1; int col = -1; boolean foundBigSudoku = false;
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (localSudoku[i][j].size() > 1) {
                    temporarySudoku = (HashSet) localSudoku[i][j].clone();
                    row = i; col = j;
                    foundBigSudoku = true;
                    break;
                }
            }
            if (foundBigSudoku == true) {break;}
        }

        Set temporarySudoku1 = newShuffledSet(temporarySudoku);
        Iterator iterator = temporarySudoku1.iterator();
//        System.out.println("Temp sudoku1:  "+temporarySudoku);

//        System.out.println("hii");
        while (iterator.hasNext()) {
            Integer num = (Integer) iterator.next();
            HashSet[][] localSudoku2 = new HashSet[9][9];
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    localSudoku2[i][j] = (HashSet) localSudoku[i][j].clone();
                }
            }
            localSudoku2[row][col].clear();
            localSudoku2[row][col].add(num);

            HashSet[][] localSudoku3 = new HashSet[9][9];
            localSudoku3 = fn(localSudoku2);
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    localSudoku2[i][j] = (HashSet) localSudoku3[i][j].clone();
                    localSudoku3[i][j].clear();
                }
            }

            if(isValid(localSudoku2)){
                System.out.println("Solution: ");
                StringBuilder sb = new StringBuilder("");
                for (int i = 0; i <= 8; i++) {
                    if (i%3==0 && i!=0) sb.append("_______________________________________________\n");
                    for (int j = 0; j <= 8; j++) {
                        if (j%3==0 && j!=0){
                            sb.append("  |   ");
                        }
                        sb.append(localSudoku2[i][j]+" ");
                    }//System.out.print(localSudoku2[i][j]);
                    sb.append("\n"); //System.out.println();
                }
                System.out.println(sb.toString());
                System.out.println();
                done = true; start = System.currentTimeMillis(); return;
            }
        }

            Iterator iterator1 = temporarySudoku1.iterator();
//        System.out.println("Temp sudoku2:  "+temporarySudoku);
            while (iterator1.hasNext()) {
//                System.out.println("ghjk");
                Integer num = (Integer) iterator1.next();
                HashSet[][] localSudoku2 = new HashSet[9][9];
                for (int i = 0; i <= 8; i++) {
                    for (int j = 0; j <= 8; j++) {
                        localSudoku2[i][j] = (HashSet) localSudoku[i][j].clone();
                    }
                }
                localSudoku2[row][col].clear();
                localSudoku2[row][col].add(num);

                HashSet[][] localSudoku3 = new HashSet[9][9];
                localSudoku3 = fn(localSudoku2);
                for (int i = 0; i <= 8; i++) {
                    for (int j = 0; j <= 8; j++) {
                        localSudoku2[i][j] = (HashSet) localSudoku3[i][j].clone();
                        localSudoku3[i][j].clear();
                    }
                }

                bf21(localSudoku2);
                if (done == true) {
                    return;
                }
            }
    }

    public static void bf22(HashSet[][] sudokuNew) {

        end = System.currentTimeMillis();
        if (((end - start) > (60 * 1000)) && (done == false) || (stop == true)) {
            return;
        }

        Scanner scanner = new Scanner(System.in);
        HashSet[][] localSudoku = new HashSet[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
            }
        }

        HashSet temporarySudoku = new HashSet();
        int row = -1;
        int col = -1;
        boolean foundBigSudoku = false;
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (localSudoku[i][j].size() > 1) {
                    temporarySudoku = (HashSet) localSudoku[i][j].clone();
                    row = i;
                    col = j;
                    foundBigSudoku = true;
                    break;
                }
            }
            if (foundBigSudoku == true) {
                break;
            }
        }

        Set temporarySudoku1 = newShuffledSet(temporarySudoku);
        Iterator iterator = temporarySudoku1.iterator();


        while (iterator.hasNext()) {
            Integer num = (Integer) iterator.next();
            HashSet[][] localSudoku2 = new HashSet[9][9];
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    localSudoku2[i][j] = (HashSet) localSudoku[i][j].clone();
                }
            }
            localSudoku2[row][col].clear();
            localSudoku2[row][col].add(num);

            HashSet[][] localSudoku3 = new HashSet[9][9];
            localSudoku3 = fn(localSudoku2);
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    localSudoku2[i][j] = (HashSet) localSudoku3[i][j].clone();
                    localSudoku3[i][j].clear();
                }
            }

            if (isValid(localSudoku2)) {
                System.out.println("Solution No.: " + ++solutionNumber);
                StringBuilder sb = new StringBuilder("");
                for (int i = 0; i <= 8; i++) {
                    if (i%3==0 && i!=0) sb.append("_______________________________________________\n");
                    for (int j = 0; j <= 8; j++) {
                        if (j%3==0 && j!=0){
                            sb.append("  |   ");
                        }
                        sb.append(localSudoku2[i][j]+" ");
                    }//System.out.print(localSudoku2[i][j]);
                    sb.append("\n"); //System.out.println();
                }
                System.out.println(sb.toString());
//                System.out.println();
                System.out.println("///////////////////////////////////////////////");
//                System.out.println("\n");
                done = true;
                start = System.currentTimeMillis();
                if (stop == true) {
                    return;
                }
                continue;
            }
        }

        iterator = temporarySudoku1.iterator();
        while (iterator.hasNext()) {
            Integer num = (Integer) iterator.next();


            HashSet[][] localSudoku2 = new HashSet[9][9];
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    localSudoku2[i][j] = (HashSet) localSudoku[i][j].clone();
                }
            }
            localSudoku2[row][col].clear();
            localSudoku2[row][col].add(num);

            HashSet[][] localSudoku3 = new HashSet[9][9];
            localSudoku3 = fn(localSudoku2);
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    localSudoku2[i][j] = (HashSet) localSudoku3[i][j].clone();
                    localSudoku3[i][j].clear();
                }
            }


            bf22(localSudoku2);


            if (done == true) {
                if (stop == true) {
                    return;
                }
                continue;
            }
        }
    }

    public static Set<Integer> newShuffledSet(Collection<Integer> hashsetNew){
        List<Integer> shuffleMe = new ArrayList<Integer>(hashsetNew);
        Collections.shuffle(shuffleMe);
        return new LinkedHashSet<Integer>(shuffleMe);
    }
}

class trd extends Thread {
    static Scanner sc = new Scanner(System.in);

    public void run() {
        System.out.println("Press Enter to stop");
        String str = sc.nextLine();
            if (str.isEmpty() || !str.isEmpty()) {
                Main.stop = true; return;
            }
        }
    }



