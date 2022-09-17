package com.nikhilaradya;
import java.lang.*;
import java.util.*;


public class Main {

    static int c = 0;
    static boolean done = false;
    static boolean bf = true;
    static int globalInteger = 81;

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

        System.out.println("Enter entries row by row; enter 0 for blank");

        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                int num = scan.nextInt();
                if (num == 0) continue;
                else if (num != 0) {
                    sudoku[i][j].clear();
                    sudoku[i][j].add(num);
                }
            }
        }

        HashSet[][] temporarySudoku = new HashSet[9][9];
        temporarySudoku = fn(sudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                sudoku[i][j] = temporarySudoku[i][j];
            }
        }

//        sudoku[7][0].clear();
//        sudoku[7][0].add(4);
//        sudoku[8][4].clear();
//        sudoku[8][4].add(1);
//        sudoku[0][8].clear();
//        sudoku[0][8].add(9);
//        sudoku[0][7].clear();
//        sudoku[0][7].add(4);


        HashSet[][] temporarySudoku1 = new HashSet[9][9];
        temporarySudoku1 = fn(sudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                sudoku[i][j] = temporarySudoku1[i][j];
            }
        }

        System.out.println(isValid(sudoku) + "first time");
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) System.out.print(sudoku[i][j]);
            System.out.println();
        }

        System.out.println("Hi" + isValid(sudoku));
        if (isValid(sudoku)) return;


//        for (int i = 0; i <= 8; i++) {
//            for (int j = 0; j <= 8; j++) {
//                if (sudoku[i][j].size() != 1) {
//                    Iterator iterator = sudoku[i][j].iterator();
//                    while (iterator.hasNext()) {
//                        Integer num = (Integer) iterator.next();
//                        HashSet[][] temp1 = new HashSet[9][9];
//                        for (int i1 = 0; i1 <= 8; i1++) {
//                            for (int j1 = 0; j1 <= 8; j1++) {
//                                temp1[i1][j1] = (HashSet) sudoku[i1][j1].clone();
//                            }
//                        }
//
//                        temp1[i][j].clear();
//                        temp1[i][j].add(num);
//                        HashSet[][] temp2 = new HashSet[9][9];
//                        temp2 = fn(temp1);
//                        for (int i1 = 0; i1 <= 8; i1++) {
//                            for (int j1 = 0; j1 <= 8; j1++) {
//                                temp1[i1][j1] = (HashSet) temp2[i1][j1].clone();
//                            }
//                        }
//                        if (isValid(temp1)) {
//                            System.out.println(true);
//                            for (int i1 = 0; i1 <= 8; i1++) {
//                                for (int j1 = 0; j1 <= 8; j1++) System.out.print(temp1[i1][j1]);
//                                System.out.println();
//                            }
//                            return;
//                        } else System.out.println(c++);
//                    }
//                }
//            }
//        }



/*
///////////////////////////////////////////////////////////////////////////////////


        ArrayList<HashSet> ar = new ArrayList<HashSet>();
        HashMap<HashSet, rowcol> tm = new HashMap<HashSet, rowcol>();

        int temp = 0;
            for (int i=0; i<=8; i++){
                for (int j=0; j<=8; j++){
                    if(sudoku[i][j].size()!=1) {
                        HashSet<Integer> hs1 = (HashSet<Integer>) sudoku[i][j].clone();
                        ar.add(hs1);
                        rowcol temp1 = new rowcol(i,j);
                        tm.put((HashSet<Integer>) sudoku[i][j].clone(), temp1);
                        temp++;
                    }
                    if(temp>7) break;
                }
                if (temp>7) break;
            }

        System.out.println("Hi bro"+ ar.size());



//sudoku[0][1].clear();

//        System.out.println(ar.get(0).toString());
//        System.out.println(ar.get(1).toString());
//        System.out.println(ar.get(2).toString());
//        System.out.println(ar.get(3).toString());
//        System.out.println(ar.get(4).toString());
//        System.out.println(ar.get(5).toString());
//        System.out.println(ar.get(1).toArray().length);
//        System.out.println(ar.get(0).toArray().length);



        HashSet[][] sudoku1 = new HashSet[9][9];
        for (int i=0; i<=8; i++){
            for (int j=0; j<=8; j++){
                sudoku1[i][j] = (HashSet) sudoku[i][j].clone();
            }
        }

//sudoku1[0][0].clear(); sudoku1[0][1].clear();

        int row0 = tm.get(ar.get(0)).i; int col0 = tm.get(ar.get(0)).j;
        int row1 = tm.get(ar.get(1)).i; int col1 = tm.get(ar.get(1)).j;
        int row2 = tm.get(ar.get(2)).i; int col2 = tm.get(ar.get(2)).j;
        int row3 = tm.get(ar.get(3)).i; int col3 = tm.get(ar.get(3)).j;
        int row4 = tm.get(ar.get(4)).i; int col4 = tm.get(ar.get(4)).j;
        int row5 = tm.get(ar.get(5)).i; int col5 = tm.get(ar.get(5)).j;
        int row6 = tm.get(ar.get(6)).i; int col6 = tm.get(ar.get(6)).j;
        int row7 = tm.get(ar.get(7)).i; int col7 = tm.get(ar.get(7)).j;
//        sudoku1[0][0].remove(2);
//        sudoku1[0][1].remove(2);
//        sudoku1[4][2].remove(1);
        sudoku1 = fn(sudoku1);

        Iterator it0 = ar.get(0).iterator();
       while (it0.hasNext()) {
           int x0 = (int) it0.next();
//           System.out.println(x0);

           HashSet[][] sudoku10 = new HashSet[9][9];
           for(int i=0; i<=8; i++){
               for (int j=0; j<=8; j++){
                   sudoku10[i][j] = (HashSet) sudoku1[i][j].clone();
               }
           }
           sudoku10[row0][col0].clear();
           sudoku10[row0][col0].add(x0);
           sudoku10 = fn(sudoku10);
           if (isValid(sudoku10)) {
               for (int i = 0; i <= 8; i++) {
                   for (int j = 0; j <= 8; j++) System.out.print(sudoku10[i][j]);
                   System.out.println();
               }
               return;
           }

           Iterator it1 = ar.get(1).iterator();
           while (it1.hasNext()) {
                  int x1 = (int) it1.next();
//                  System.out.println(x1);
                  sudoku1[row1][col1].clear();
                  sudoku1[row1][col1].add(x1);
                  HashSet[][] sudoku3 = new HashSet[9][9];
               for(int i=0; i<=8; i++){
                   for (int j=0; j<=8; j++){
                       sudoku3[i][j] = (HashSet) sudoku10[i][j].clone();
                   }
               }
                  sudoku3 = fn(sudoku3);
//
//               System.out.println(sudoku1);
//               System.out.println(isValid(sudoku1));
                  if (isValid(sudoku3)) {
                      for (int i = 0; i <= 8; i++) {
                          for (int j = 0; j <= 8; j++) System.out.print(sudoku3[i][j]);
                          System.out.println();
                      }
                      return;
                  }

               Iterator it2 = ar.get(2).iterator();
                  while (it2.hasNext()) {
                      int x2 = (int) it2.next();
//                      System.out.println(x2);

                      HashSet[][] sudoku4 = new HashSet[9][9];
                      for(int i=0; i<=8; i++){
                          for (int j=0; j<=8; j++){
                              sudoku4[i][j] = (HashSet) sudoku3[i][j].clone();
                          }
                      }
                      sudoku4[row2][col2].clear();
                      sudoku4[row2][col2].add(x2);
                      sudoku4 = fn(sudoku4);
                      if (isValid(sudoku4)) {
                          for (int i = 0; i <= 8; i++) {
                              for (int j = 0; j <= 8; j++) System.out.print(sudoku4[i][j]);
                              System.out.println();
                          }
                          return;
                      }

                      Iterator it3 = ar.get(3).iterator();
                      while (it3.hasNext()) {
                          int x3 = (int) it3.next();
//                          System.out.println(x3);

                          HashSet[][] sudoku5 = new HashSet[9][9];
                          for(int i=0; i<=8; i++){
                              for (int j=0; j<=8; j++){
                                  sudoku5[i][j] = (HashSet) sudoku4[i][j].clone();
                              }
                          }
                          sudoku5[row3][col3].clear();
                          sudoku5[row3][col3].add(x3);
                          sudoku5 = fn(sudoku5);
                          if (isValid(sudoku5)) {
                              for (int i = 0; i <= 8; i++) {
                                  for (int j = 0; j <= 8; j++) System.out.print(sudoku5[i][j]);
                                  System.out.println();
                              }
                              return;
                          }

                          Iterator it4 = ar.get(4).iterator();
                          while (it4.hasNext()) {
                              int x4 = (int) it4.next();
//                              System.out.println(x4);
                              HashSet[][] sudoku6 = new HashSet[9][9];
                              for(int i=0; i<=8; i++){
                                  for (int j=0; j<=8; j++){
                                      sudoku6[i][j] = (HashSet) sudoku5[i][j].clone();
                                  }
                              }
                              sudoku6[row4][col4].clear();
                              sudoku6[row4][col4].add(x4);
                              sudoku6 = fn(sudoku6);
                              if (isValid(sudoku6)) {
                                  for (int i = 0; i <= 8; i++) {
                                      for (int j = 0; j <= 8; j++) System.out.print(sudoku6[i][j]);
                                      System.out.println();
                                  }
                                  return;
                              }

                              Iterator it5 = ar.get(5).iterator();
                              while (it5.hasNext()) {
                                  int x5 = (int) it5.next();
//                                  System.out.println(x5);
                                  HashSet[][] sudoku7 = new HashSet[9][9];
                                  for(int i=0; i<=8; i++){
                                      for (int j=0; j<=8; j++){
                                          sudoku7[i][j] = (HashSet) sudoku6[i][j].clone();
                                      }
                                  }
                                  sudoku7[row5][col5].clear();
                                  sudoku7[row5][col5].add(x5);
                                  sudoku7 = fn(sudoku7);
                                  if (isValid(sudoku7)) {
                                      for (int i = 0; i <= 8; i++) {
                                          for (int j = 0; j <= 8; j++) System.out.print(sudoku7[i][j]);
                                          System.out.println();
                                      }
                                      return;
                                  }

                                  Iterator it6 = ar.get(6).iterator();
                                  while (it6.hasNext()) {
                                      int x6 = (int) it6.next();
//                                  System.out.println(x5);
                                      HashSet[][] sudoku8 = new HashSet[9][9];
                                      for(int i=0; i<=8; i++){
                                          for (int j=0; j<=8; j++){
                                              sudoku8[i][j] = (HashSet) sudoku7[i][j].clone();
                                          }
                                      }
                                      sudoku8[row6][col6].clear();
                                      sudoku8[row6][col6].add(x6);
                                      sudoku8 = fn(sudoku8);
                                      if (isValid(sudoku8)) {
                                          for (int i = 0; i <= 8; i++) {
                                              for (int j = 0; j <= 8; j++) System.out.print(sudoku8[i][j]);
                                              System.out.println();
                                          }
                                          return;
                                      }

                                      Iterator it7 = ar.get(7).iterator();
                                      while (it7.hasNext()) {
                                          int x7 = (int) it7.next();
//                                  System.out.println(x5);
                                          HashSet[][] sudoku9 = new HashSet[9][9];
                                          for (int i = 0; i <= 8; i++) {
                                              for (int j = 0; j <= 8; j++) {
                                                  sudoku9[i][j] = (HashSet) sudoku8[i][j].clone();
                                              }
                                          }
                                          sudoku9[row7][col7].clear();
                                          sudoku9[row7][col7].add(x7);
                                          sudoku9 = fn(sudoku9);
                                          if (isValid(sudoku9)) {
                                              for (int i = 0; i <= 8; i++) {
                                                  for (int j = 0; j <= 8; j++) System.out.print(sudoku9[i][j]);
                                                  System.out.println();
                                              }
                                              return;
                                          }
                                      }
                                      sudoku1[row7][col7].clear();
                                      sudoku1[row7][col7] = (HashSet) sudoku[row7][col7].clone();


                                  }
                                  sudoku1[row6][col6].clear();
                                  sudoku1[row6][col6] = (HashSet) sudoku[row6][col6].clone();
                              }
                              sudoku1[row5][col5].clear();
                              sudoku1[row5][col5] = (HashSet) sudoku[row5][col5].clone();


                          }

                          sudoku1[row4][col4].clear();
                          sudoku1[row4][col4] = (HashSet) sudoku[row4][col4].clone();

                      }
                      sudoku1[row3][col3].clear();
                      sudoku1[row3][col3] = (HashSet) sudoku[row3][col3].clone();

                  }
               sudoku1[row2][col2].clear();
               sudoku1[row2][col2] = (HashSet) sudoku[row2][col2].clone();
           }
           sudoku1[row1][col1].clear();
           sudoku1[row1][col1] = (HashSet) sudoku[row1][col1].clone();

       }

        System.out.println("oi");
//       HashSet nikii = (HashSet) sudoku[0][1].clone();
//       sudoku1[0][1].clear();
//       sudoku1[0][1] = nikii;

//        sudoku1[row7][col7] = (HashSet) sudoku[row7][col7].clone();
//        sudoku1[row6][col6] = (HashSet) sudoku[row7][col7].clone();
//        sudoku1[row5][col5] = (HashSet) sudoku[row7][col7].clone();
//        sudoku1[row4][col4] = (HashSet) sudoku[row7][col7].clone();
//        sudoku1[row3][col3] = (HashSet) sudoku[row7][col7].clone();
//        sudoku1[row2][col2] = (HashSet) sudoku[row7][col7].clone();
//        sudoku1[row1][col1] = (HashSet) sudoku[row7][col7].clone();




//       sudoku1 = fn(sudoku1);

//        System.out.println(isValid(sudoku1));



//        for (int i = 0; i <= 8; i++) {
//            for (int j = 0; j <= 8; j++) System.out.print(sudoku1[i][j]);
//            System.out.println();
//        }
//
        // write your code here

 */


//        bf1(sudoku);
//
//        if (done == true) return;

        bf2(sudoku, 1);

        if (done == true) return;
        else {

            System.out.println(isValid(sudoku) + "last time");
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) System.out.print(sudoku[i][j]);
                System.out.println();
            }

            System.out.println("bro" + isValid(sudoku));
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
                        if (k == j || localSudoku[i][k].size() == 1) continue;
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
                    if ((localSudoku[i][j].size() != 1) && localSudoku[i][j].contains(set)) {
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
                        if (k == j || localSudoku[k][i].size() == 1) continue;
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
                    if ((localSudoku[j][i].size() != 1) && localSudoku[j][i].contains(set)) {
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
                                if ((i == z && j == y) || localSudoku[z][y].size() == 1) continue;
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
        HashSet[][] temporarySudoku1 = new HashSet[9][9];
        temporarySudoku1 = row_compute(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku1[i][j].clone();
            }
        }

        HashSet[][] temporarySudoku2 = new HashSet[9][9];
        temporarySudoku2 = row_compute2(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku2[i][j].clone();
            }
        }


        HashSet[][] temporarySudoku3 = new HashSet[9][9];
        temporarySudoku3 = col_compute(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku3[i][j].clone();
            }
        }

        HashSet[][] temporarySudoku4 = new HashSet[9][9];
        temporarySudoku4 = col_compute2(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku4[i][j].clone();
            }
        }

        HashSet[][] temporarySudoku5 = new HashSet[9][9];
        temporarySudoku5 = box_compute(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku5[i][j].clone();
            }
        }

        HashSet[][] temporarySudoku6 = new HashSet[9][9];
        temporarySudoku6 = compare_row(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku6[i][j].clone();
            }
        }

        HashSet[][] temporarySudoku7 = new HashSet[9][9];
        temporarySudoku7 = compare_col(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku7[i][j].clone();
            }
        }

        HashSet[][] temporarySudoku8 = new HashSet[9][9];
        temporarySudoku8 = row_compute3(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku8[i][j].clone();
            }
        }

        HashSet[][] temporarySudoku9 = new HashSet[9][9];
        temporarySudoku9 = col_compute3(localSudoku);
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                localSudoku[i][j] = (HashSet) temporarySudoku9[i][j].clone();
            }
        }


        for (int kala = 2; kala <= 9; kala++) {
            HashSet[][] temporarySudoku10 = new HashSet[9][9];
            temporarySudoku10 = row_pair2(localSudoku, kala);
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    localSudoku[i][j] = (HashSet) temporarySudoku10[i][j].clone();
                    temporarySudoku10[i][j].clear();
                }
            }
        }

        for (int kala = 2; kala <= 9; kala++) {
            HashSet[][] temporarySudoku11 = new HashSet[9][9];
            temporarySudoku11 = col_pair2(localSudoku, kala);
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    localSudoku[i][j] = (HashSet) temporarySudoku11[i][j].clone();
                    temporarySudoku11[i][j].clear();
                }
            }
        }

        for (int kala = 2; kala <= 9; kala++) {
            HashSet[][] temporarySudoku12 = new HashSet[9][9];
            temporarySudoku12 = box_pair2(localSudoku, kala);
            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    localSudoku[i][j] = (HashSet) temporarySudoku12[i][j].clone();
                    temporarySudoku12[i][j].clear();
                }
            }
        }
//
//        HashSet[][] temporarySudoku13 = new HashSet[9][9];
//        temporarySudoku13 = row_pair3(localSudoku);
//        for(int i=0; i<=8; i++){
//            for (int j=0; j<=8; j++){
//                localSudoku[i][j] = (HashSet) temporarySudoku13[i][j].clone();
//            }
//        }
//
//        HashSet[][] temporarySudoku14 = new HashSet[9][9];
//        temporarySudoku14 = col_pair3(localSudoku);
//        for(int i=0; i<=8; i++){
//            for (int j=0; j<=8; j++){
//                localSudoku[i][j] = (HashSet) temporarySudoku14[i][j].clone();
//            }
//        }
//
//        HashSet[][] temporarySudoku15 = new HashSet[9][9];
//        temporarySudoku15 = box_pair3(localSudoku);
//        for(int i=0; i<=8; i++){
//            for (int j=0; j<=8; j++){
//                localSudoku[i][j] = (HashSet) temporarySudoku15[i][j].clone();
//            }
//        }


        return localSudoku;
    }

    public static HashSet[][] compare_row(HashSet[][] sudokuNew) {
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
                        if (localSudoku[i][j].size() != 1 && localSudoku[i][j].contains(set)) {
                            count++;
                            r[itr++] = i;
                        }
                    }
                }

                if (count == 2 && r[0] == r[1] && r[2] == -1) {
                    for (int niki1 = (k / 3) * 3; niki1 < ((k / 3) * 3) + 3; niki1++) {
                        if (niki1 == k) continue;
//                        for (int i = 3 * (r[0] / 3); i < (3 * (r[0] / 3)) + 3; i++) {
//                            if (i != r[0]) continue;
                        for (int j = (3 * (niki1 % 3)); j < (3 * (niki1 % 3)) + 3; j++) {
                            if ((localSudoku[r[0]][j].contains(set)) && localSudoku[r[0]][j].size() != 1) {
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
//                        }
                    }
                }

                if (count == 3 && r[0] == r[1] && r[1] == r[2]) {
                    for (int niki1 = (k / 3) * 3; niki1 < ((k / 3) * 3) + 3; niki1++) {
                        if (niki1 == k) continue;
//                        for (int i = 3 * (r[0] / 3); i < (3 * (r[0] / 3)) + 3; i++) {
//                            if (i == r[0]) continue;
                        for (int j = (3 * (niki1 % 3)); j < (3 * (niki1 % 3)) + 3; j++) {
                            if ((localSudoku[r[0]][j].contains(set)) && localSudoku[r[0]][j].size() != 1) {
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
//                        }
                    }
                }
            }
        }
        return localSudoku;
    }


    public static HashSet[][] compare_col(HashSet[][] sudokuNew) {
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
                for (int i = 3 * (k % 3); i < (3 * (k % 3)) + 3; i++) {
                    for (int j = (3 * (k / 3)); j < (3 * (k / 3)) + 3; j++) {
                        if (localSudoku[j][i].size() != 1 && localSudoku[j][i].contains(set)) {
                            count++;
                            c[itr++] = i;
                        }

                    }
                }

                if (count == 2 && c[0] == c[1] && c[2] == -1) {
                    for (int niki1 = (k % 3); niki1 <= ((k % 3)) + 6; niki1 += 3) {
                        if (niki1 == k) continue;
//                        for (int i = 3 * (c[0] / 3); i < (3 * (c[0] / 3)) + 3; i++) {
//                            if (i == c[0]) continue;
                        for (int j = (3 * (niki1 / 3)); j < (3 * (niki1 / 3)) + 3; j++) {
                            if ((localSudoku[j][c[0]].contains(set)) && localSudoku[j][c[0]].size() != 1) {
                                localSudoku[j][c[0]].remove(set);
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
//                        }
                    }
                }

                if (count == 3 && c[0] == c[1] && c[1] == c[2]) {
                    for (int niki1 = (k % 3) * 3; niki1 < ((k % 3) * 3) + 3; niki1++) {
                        if (niki1 == k) continue;
//                        for (int i = 3 * (c[0] / 3); i < (3 * (c[0] / 3)) + 3; i++) {
//                            if (i == c[0]) continue;
                        for (int j = (3 * (niki1 / 3)); j < (3 * (niki1 / 3)) + 3; j++) {
                            if ((localSudoku[j][c[0]].contains(set)) && localSudoku[j][c[0]].size() != 1) {
                                localSudoku[j][c[0]].remove(set);
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
//                        }
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

        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (localSudoku[i][j].size() != 1) flag = false;
            }
        }
        if (flag == false) return false;

        for (int i = 0; i <= 8; i++) {
            for (int set = 1; set <= 9; set++) {
                int count = 0;
                for (int j = 0; j <= 8; j++) {
                    if (localSudoku[i][j].toArray()[0].equals(set)) count++;
                }
                if (count != 1) return false;
            }
        }

        for (int i = 0; i <= 8; i++) {
            for (int set = 1; set <= 9; set++) {
                int count = 0;
                for (int j = 0; j <= 8; j++) {
                    if (localSudoku[j][i].toArray()[0].equals(set)) count++;
                }
                if (count != 1) return false;
            }
        }

        for (int k = 0; k <= 8; k++) {
            for (int set = 1; set <= 9; set++) {
                int count = 0;
                for (int i = 3 * (k / 3); i < (3 * (k / 3)) + 3; i++) {
                    for (int j = (3 * (k % 3)); j < (3 * (k % 3)) + 3; j++) {
                        if (localSudoku[i][j].toArray()[0].equals(set)) count++;
                    }
                }
                if (count != 1) return false;
            }
        }

        done = true;
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
//                System.out.println(count);

                if (count == 2) {
                    if (((col[0]) / 3) == ((col[1]) / 3)) {
                        //do delete from box other rows
                        for (int i1 = (3 * (i / 3)); i1 < (3 * ((i / 3)) + 3); i1++) {
                            if (i1 == i) continue;
                            for (int j1 = (3 * ((col[0]) / 3)); j1 < (3 * ((col[0]) / 3) + 3); j1++) {
//                                System.out.println(i1+" "+j1);
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
                    if ((((col[0]) / 3) == ((col[1]) / 3)) && ((col[0]) / 3 == ((col[2]) / 3))) {
                        //do delete from box other rows
                        for (int i1 = (3 * i / 3); i1 < ((3 * (i / 3)) + 3); i1++) {
                            if (i1 == i) continue;
                            for (int j1 = 3 * (col[0]) / 3; j1 < ((3 * ((col[0]) / 3)) + 3); j1++) {
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
//                System.out.println(count);

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
                    if ((((row[0]) / 3) == ((row[1]) / 3)) && ((row[0]) / 3 == ((row[2]) / 3))) {
                        //do delete from box other rows
                        for (int i1 = (3 * i / 3); i1 < ((3 * (i / 3)) + 3); i1++) {
                            if (i1 == i) continue;
                            for (int j1 = 3 * (row[0]) / 3; j1 < ((3 * ((row[0]) / 3)) + 3); j1++) {
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
//                        System.out.println("Error here"+ i+" "+j);
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

//    public static HashSet[][] row_pair3(HashSet[][] sudokuNew) {
//        HashSet[][] localSudoku = new HashSet[9][9];
//        for (int i = 0; i <= 8; i++) {
//            for (int j = 0; j <= 8; j++) {
//                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
//            }
//        }
//
//        int count = 0;
//        int probe = 0;
//        for (int i = 0; i <= 8; i++) {
//            for (int j = 0; j <= 8; j++) {
//                if (localSudoku[i][j].size() == 3) {
//                    count++;
//                }
//            }
//
//            if (count >= 3) {
//                for (int k = 1; k <= count - 1; k++) {
//                    probe = 0;
//                    HashSet temporarySudoku = new HashSet();
//                    for (int j = 0; j <= 8; j++) {
//                        if (localSudoku[i][j].size() == 3) {
//                            probe++;
//                        }
//                        if (probe == k && localSudoku[i][j].size() == 3) { int count1 = 0;
//                            temporarySudoku = (HashSet) localSudoku[i][j].clone();
//                            for (int nik = 0; nik<=8; nik++){
//                                if (localSudoku[i][nik].equals(temporarySudoku)) count1++;
//                            }
//                            if (count1==3){
//                                Iterator iterator = temporarySudoku.iterator();
//                                for (int j2 = 0; j2 <= 8; j2++) {
//                                    if ((!localSudoku[i][j2].equals(temporarySudoku)) && localSudoku[i][j2].size() > 1) {
//                                        while (iterator.hasNext()) {
//                                            Integer num = (Integer) iterator.next();
//                                            if (localSudoku[i][j2].contains(num)) {
//                                                localSudoku[i][j2].remove(num);
//                                                HashSet[][] temporarySudokuniki1 = new HashSet[9][9];
//                                                temporarySudokuniki1 = fn(localSudoku);
//                                                for (int i3 = 0; i3 <= 8; i3++) {
//                                                    for (int j3 = 0; j3 <= 8; j3++) {
//                                                        localSudoku[i3][j3] = (HashSet) temporarySudokuniki1[i3][j3].clone();
//                                                        temporarySudokuniki1[i3][j3].clear();
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                                // do delete from full row, the elements of pair
//                            }
//                        }
//                    }
//
//
//                }
//            }
//        }
//
//        return localSudoku;
//    }
//
//    public static HashSet[][] col_pair3(HashSet[][] sudokuNew) {
//        HashSet[][] localSudoku = new HashSet[9][9];
//        for (int i = 0; i <= 8; i++) {
//            for (int j = 0; j <= 8; j++) {
//                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
//            }
//        }
//
//        int count = 0;
//        int probe = 0;
//        for (int i = 0; i <= 8; i++) {
//            for (int j = 0; j <= 8; j++) {
//                if (localSudoku[j][i].size() == 3) {
//                    count++;
//                }
//            }
//
//            if (count >= 3) {
//                for (int k = 1; k <= count - 1; k++) {
//                    probe = 0;
//                    HashSet temporarySudoku = new HashSet();
//                    for (int j = 0; j <= 8; j++) {
//                        if (localSudoku[j][i].size() == 3) {
//                            probe++;
//                        }
//                        if (probe == k && localSudoku[j][i].size() == 3) { int count1 = 0;
//                            temporarySudoku = (HashSet) localSudoku[j][i].clone();
//                            for (int nik = 0; nik<=8; nik++){
//                                if (localSudoku[nik][i].equals(temporarySudoku)) count1++;
//                            }
//                            if (count1==3){
//                                Iterator iterator = temporarySudoku.iterator();
//                                for (int j2 = 0; j2 <= 8; j2++) {
//                                    if ((!localSudoku[j2][i].equals(temporarySudoku)) && localSudoku[j2][i].size() > 1) {
//                                        while (iterator.hasNext()) {
//                                            Integer num = (Integer) iterator.next();
//                                            if (localSudoku[j2][i].contains(num)) {
//                                                localSudoku[j2][i].remove(num);
//                                                HashSet[][] temporarySudokuniki1 = new HashSet[9][9];
//                                                temporarySudokuniki1 = fn(localSudoku);
//                                                for (int i3 = 0; i3 <= 8; i3++) {
//                                                    for (int j3 = 0; j3 <= 8; j3++) {
//                                                        localSudoku[i3][j3] = (HashSet) temporarySudokuniki1[i3][j3].clone();
//                                                        temporarySudokuniki1[i3][j3].clear();
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                                // do delete from full row, the elements of pair
//                            }
//                        }
//                    }
//
//
//                }
//            }
//        }
//
//        return localSudoku;
//    }
//
//    public static HashSet[][] box_pair3(HashSet[][] sudokuNew) {
//        HashSet[][] localSudoku = new HashSet[9][9];
//        for (int i = 0; i <= 8; i++) {
//            for (int j = 0; j <= 8; j++) {
//                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
//            }
//        }
//
//        for (int k = 0; k <= 8; k++) {
//            int count = 0;
//            int probe = 0;
//            for (int i = (3 * (k / 3)); i < (3 * (k / 3)) + 3; i++) {
//                for (int j = (3 * (k % 3)); j < (3 * (k % 3)) + 3; j++) {
//                    if (localSudoku[i][j].size() == 3) count++;
//                }
//            }
//
//            if (count >= 3) {
//                for (int z = 1; z <= count - 1; z++) {
//                    probe = 0;
//                    HashSet temporarySudoku = new HashSet();
//                    for (int i = (3 * (k / 3)); i < ((3 * (k / 3)) + 3); i++) {
//                        for (int j = (3 * (k % 3)); j < ((3 * (k % 3)) + 3); j++) {
////                        System.out.println("Error here"+ i+" "+j);
//                            if (localSudoku[i][j].size() == 3) probe++;
//
//                            if (z == probe && localSudoku[i][j].size() == 3) { int count1 = 0;
//                                temporarySudoku = (HashSet) localSudoku[i][j].clone();
//                                for (int niki1 = (3 * (k / 3)); niki1 < ((3 * (k / 3)) + 3); niki1++) {
//                                    for (int niki2 = (3 * (k % 3)); niki2 < ((3 * (k % 3)) + 3); niki2++) {
//                                        if (localSudoku[niki1][niki2].equals(temporarySudoku)) count1++;
//                                    }
//                                }
//                                if (count1==3){
//                                    Iterator iterator = temporarySudoku.iterator();
//                                    for (int i2 = (3 * (k / 3)); i2 < (3 * (k / 3)) + 3; i2++) {
//                                        for (int j2 = (3 * (k % 3)); j2 < (3 * (k % 3)) + 3; j2++) {
//                                            if ((!localSudoku[i2][j2].equals(temporarySudoku)) && localSudoku[i2][j2].size() > 1) {
//
//                                                while (iterator.hasNext()) {
//                                                    Integer num = (Integer) iterator.next();
//                                                    if (localSudoku[i2][j2].contains(num)) {
//                                                        localSudoku[i2][j2].remove(num);
//                                                        HashSet[][] temporarySudokuniki1 = new HashSet[9][9];
//                                                        temporarySudokuniki1 = fn(localSudoku);
//                                                        for (int i3 = 0; i3 <= 8; i3++) {
//                                                            for (int j3 = 0; j3 <= 8; j3++) {
//                                                                localSudoku[i3][j3] = (HashSet) temporarySudokuniki1[i3][j3].clone();
//                                                                temporarySudokuniki1[i3][j3].clear();
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//
//
//                        }
//
//                    }
//                }
//            }
//        }
//
//        return localSudoku;
//    }


    public static void bf1(HashSet[][] sudokuNew) {
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
                            System.out.println(true);
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

//    public static void bf2(HashSet[][] sudokuNew) {
//        HashSet[][] localSudoku = new HashSet[9][9];
//        for (int i = 0; i <= 8; i++) {
//            for (int j = 0; j <= 8; j++) {
//                localSudoku[i][j] = (HashSet) sudokuNew[i][j].clone();
//            }
//        }
//
//        int count = 0;
//        Map<HashSet, rowcol> treemap = new HashMap<HashSet, rowcol>();
//        for (int i = 0; i <= 8; i++) {
//            for (int j = 0; j <= 8; j++) {
//                if (localSudoku[i][j].size() > 1) {
//                    rowcol r = new rowcol(i, j);
//                    HashSet t = (HashSet) localSudoku[i][j].clone();
//                    treemap.put(t, r);
//                    count++;
//                }
//            }
//        }
//
//        Iterator iterator = treemap.keySet().iterator();
//
//
//        HashSet temporarySudoku = (HashSet) iterator.next();
//        int row = treemap.get(temporarySudoku).i;
//        int col = treemap.get(temporarySudoku).j;
//        Iterator iterator2 = temporarySudoku.iterator();
//        while (iterator2.hasNext()) {
//            Integer num = (Integer) iterator2.next();
//            HashSet[][] localSudoku2 = new HashSet[9][9];
//            for (int i = 0; i <= 8; i++) {
//                for (int j = 0; j <= 8; j++) {
//                    localSudoku2[i][j] = (HashSet) localSudoku[i][j].clone();
//                }
//            }
//            localSudoku2[row][col].clear();
//            localSudoku2[row][col].add(num);
//
//            HashSet[][] localSudoku3 = new HashSet[9][9];
//            localSudoku3 = fn(localSudoku2);
//            for (int i = 0; i <= 8; i++) {
//                for (int j = 0; j <= 8; j++) {
//                    localSudoku2[i][j] = (HashSet) localSudoku3[i][j].clone();
//                    localSudoku3[i][j].clear();
//                }
//            }
//
//            bf1(localSudoku2);
//            if (done == true) return;
//        }
//
//        iterator2 = temporarySudoku.iterator();
//
//        if (bf == true) {
//            bf = false;
//            while (globalInteger <= 5) {
//                globalInteger++; iterator2 = temporarySudoku.iterator();
//                while (iterator2.hasNext()) {
//                    Integer num = (Integer) iterator2.next();
//                    HashSet[][] localSudoku2 = new HashSet[9][9];
//                    for (int i = 0; i <= 8; i++) {
//                        for (int j = 0; j <= 8; j++) {
//                            localSudoku2[i][j] = (HashSet) localSudoku[i][j].clone();
//                        }
//                    }
//                    localSudoku2[row][col].clear();
//                    localSudoku2[row][col].add(num);
//
//                    HashSet[][] localSudoku3 = new HashSet[9][9];
//                    localSudoku3 = fn(localSudoku2);
//                    for (int i = 0; i <= 8; i++) {
//                        for (int j = 0; j <= 8; j++) {
//                            localSudoku2[i][j] = (HashSet) localSudoku3[i][j].clone();
//                            localSudoku3[i][j].clear();
//                        }
//                    }
//
//                    bf2(localSudoku2);
//                    if (done == true) {
//                        return;
//                    }
//                    //else {globalInteger++;}
//                }
//
//            }
////            globalInteger+=2;
//        } else {
//            int localInteger = globalInteger;
//            while (localInteger >= 0) {
//                iterator2 = temporarySudoku.iterator();
//                localInteger--;
//                while (iterator2.hasNext()) {
//                    Integer num = (Integer) iterator2.next();
//                    HashSet[][] localSudoku2 = new HashSet[9][9];
//                    for (int i = 0; i <= 8; i++) {
//                        for (int j = 0; j <= 8; j++) {
//                            localSudoku2[i][j] = (HashSet) localSudoku[i][j].clone();
//                        }
//                    }
//                    localSudoku2[row][col].clear();
//                    localSudoku2[row][col].add(num);
//
//                    HashSet[][] localSudoku3 = new HashSet[9][9];
//                    localSudoku3 = fn(localSudoku2);
//                    for (int i = 0; i <= 8; i++) {
//                        for (int j = 0; j <= 8; j++) {
//                            localSudoku2[i][j] = (HashSet) localSudoku3[i][j].clone();
//                            localSudoku3[i][j].clear();
//                        }
//                    }
//
//                    bf2(localSudoku2);
//                    if (done == true) {
//                        return;
//                    }
//                    //else {globalInteger++;}
//                }
//
//
//            }
//        }
//    }

    public static void bf2(HashSet[][] sudokuNew, int call) {
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

//        System.out.println(row+" "+col);

        Iterator iterator = temporarySudoku.iterator();




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
                System.out.println(true);
                for (int i = 0; i <= 8; i++) {
                    for (int j = 0; j <= 8; j++) System.out.print(localSudoku2[i][j]);
                    System.out.println();
                }
                done = true; return;}
//            bf1(localSudoku2);
//            if (done == true) return;
        }

        iterator = temporarySudoku.iterator();
        int hi = 1;//call;
        if (bf == true) {
            bf = false;
//            System.out.println(row+" "+col);
//            while (hi < globalInteger) {
                iterator = temporarySudoku.iterator();
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
                        System.out.println(true);
                        for (int i = 0; i <= 8; i++) {
                            for (int j = 0; j <= 8; j++) System.out.print(localSudoku2[i][j]);
                            System.out.println();
                        }
                        done = true; return;}

                    bf2(localSudoku2, hi);
                    if (done == true) {
                        return;
                    }
                    //else {globalInteger++;}
                }
//                hi++;
//            }
//            globalInteger+=2;
        } else {
//            System.out.println(row+" "+col);
            int localInteger = hi;
            iterator = temporarySudoku.iterator();
//            int newnum = localInteger - 1;
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

//                    if (localInteger > newnum) {
                        bf2(localSudoku2, localInteger);
                        if (done == true) {
                            return;
                        }
                        //else {globalInteger++;}
//                    }
                }


        }
    }
}

class rowcol{
    int i; int j;
    rowcol(int i, int j){
        this.i = i;
        this.j = j;

    }
}