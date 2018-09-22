/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinechess;

/**
 *
 * @author dipta007
 */
public class Check {

    static int checkNouka(int sx, int sy, int dx, int dy, int[][] guti) {
        if (guti[dx][dy] != 0 && guti[dx][dy] < 10) {
            return 0;
        }
        if (sx != dx) {
            return 0;
        }
        if (sy != dy) {
            return 0;
        }
        if (sx == dx) {
            for (int i = sy + 1; i < dy; i++) {
                if (guti[sx][i] != 0) {
                    return 0;
                }
            }
        } else {
            for (int i = sx + 1; i < dx; i++) {
                if (guti[i][sy] != 0) {
                    return 0;
                }
            }
        }
        return 1;
    }

    static int checkGhora(int sx, int sy, int dx, int dy, int[][] guti) {
        if (guti[dx][dy] != 0 && guti[dx][dy] < 10) {
            return 0;
        }
        int[] dxx = {-2, -2, 2, 2, -1, -1, 1, 1};
        int[] dyy = {1, -1, -1, 1, 2, -2, -2, 2};

        for (int i = 0; i < 8; i++) {
            int X = sx + dxx[i];
            int Y = sy + dyy[i];
            if (X == dx && Y == dy) {
                return 1;
            }
        }
        return 0;
    }

    static int checkHati(int sx, int sy, int dx, int dy, int[][] guti, int row, int col) {
        if (guti[dx][dy] != 0 && guti[dx][dy] < 10) {
            return 0;
        }
        if (Math.abs(dx - sx) != Math.abs(dy - sy)) {
            return 0;
        }
        int flg = 0;
        for (int i = sx + 1, j = sy + 1; i < row && j < col; i++, j++) {
            if (i == dx && j == dy) {
                if (flg == 1) {
                    return 0;
                } else {
                    return 1;
                }
            }
            if (guti[i][j] != 0 && guti[i][j] > 0) {
                flg = 1;
            }
        }
        flg = 0;
        for (int i = sx + 1, j = sy - 1; i < row && j >= 0; i++, j--) {
            if (i == dx && j == dy) {
                if (flg == 1) {
                    return 0;
                } else {
                    return 1;
                }
            }
            if (guti[i][j] != 0 && guti[i][j] > 0) {
                flg = 1;
            }
        }
        flg = 0;
        for (int i = sx - 1, j = sy + 1; i >= 0 && j < col; i--, j++) {
            if (i == dx && j == dy) {
                if (flg == 1) {
                    return 0;
                } else {
                    return 1;
                }
            }
            if (guti[i][j] != 0 && guti[i][j] > 0) {
                flg = 1;
            }
        }
        flg = 0;
        for (int i = sx - 1, j = sy - 1; i >= 0 && j >= 0; i--, j--) {
            if (i == dx && j == dy) {
                if (flg == 1) {
                    return 0;
                } else {
                    return 1;
                }
            }
            if (guti[i][j] != 0 && guti[i][j] > 0) {
                flg = 1;
            }
        }
        return 0;
    }

    static int checkRani(int sx, int sy, int dx, int dy, int[][] guti) {
        if (guti[dx][dy] != 0 && guti[dx][dy] < 10) {
            return 0;
        }
        int dxx[] = {-1, 1, 0, 0, -1, -1, 1, 1};
        int dyy[] = {0, 0, -1, 1, -1, 1, 1, -1};
        for (int i = 0; i < 8; i++) {
            int X = sx + dxx[i];
            int Y = sy + dyy[i];
            if (X == dx && Y == dy) {
                return 1;
            }
        }
        return 0;
    }

    static int checkMontri(int sx, int sy, int dx, int dy, int[][] guti, int row, int col) {
        if (guti[dx][dy] != 0 && guti[dx][dy] < 10) {
            return 0;
        }
        int flg = 0;
        for (int i = sx + 1, j = sy; i < row; i++) {
            if (i == dx && j == dy) {
                if (flg == 1) {
                    return 0;
                } else {
                    return 1;
                }
            }
            if (guti[i][j] != 0 && guti[i][j] > 0) {
                flg = 1;
            }
        }
        flg = 0;
        for (int i = sx - 1, j = sy; i >= 0; i--) {
            if (i == dx && j == dy) {
                if (flg == 1) {
                    return 0;
                } else {
                    return 1;
                }
            }
            if (guti[i][j] != 0 && guti[i][j] > 0) {
                flg = 1;
            }
        }
        flg = 0;
        for (int i = sx, j = sy + 1; j < col; j++) {
            if (i == dx && j == dy) {
                if (flg == 1) {
                    return 0;
                } else {
                    return 1;
                }
            }
            if (guti[i][j] != 0 && guti[i][j] > 0) {
                flg = 1;
            }
        }
        flg = 0;
        for (int i = sx, j = sy - 1; j >= 0; j--) {
            if (i == dx && j == dy) {
                if (flg == 1) {
                    return 0;
                } else {
                    return 1;
                }
            }
            if (guti[i][j] != 0 && guti[i][j] > 0) {
                flg = 1;
            }
        }
        flg = 0;
        for (int i = sx + 1, j = sy + 1; i < row && j < col; i++, j++) {
            if (i == dx && j == dy) {
                if (flg == 1) {
                    return 0;
                } else {
                    return 1;
                }
            }
            if (guti[i][j] != 0 && guti[i][j] > 0) {
                flg = 1;
            }
        }
        flg = 0;
        for (int i = sx - 1, j = sy - 1; i >= 0 && j >= 0; i--, j--) {
            if (i == dx && j == dy) {
                if (flg == 1) {
                    return 0;
                } else {
                    return 1;
                }
            }
            if (guti[i][j] != 0 && guti[i][j] > 0) {
                flg = 1;
            }
        }
        flg = 0;
        for (int i = sx - 1, j = sy + 1; i >= 0 && j < col; i--, j++) {
            if (i == dx && j == dy) {
                if (flg == 1) {
                    return 0;
                } else {
                    return 1;
                }
            }
            if (guti[i][j] != 0 && guti[i][j] > 0) {
                flg = 1;
            }
        }
        flg = 0;
        for (int i = sx + 1, j = sy - 1; i < row && j >= 0; i++, j--) {
            if (i == dx && j == dy) {
                if (flg == 1) {
                    return 0;
                } else {
                    return 1;
                }
            }
            if (guti[i][j] != 0 && guti[i][j] > 0) {
                flg = 1;
            }
        }
        return 0;
    }

    static int checkSoinno(int sx, int sy, int dx, int dy, int[][] guti, int t) {
        if (guti[dx][dy] > 10) {
            if (t == 1) { //t=1 -> nijer chal....t=0 -> opponent er chal
                int X = sx - 1;
                int Y = sy - 1;
                if (X == dx && Y == dy) {
                    return 1;
                }

                X = sx - 1;
                Y = sy + 1;
                if (X == dx && Y == dy) {
                    return 1;
                }

            } 
            else {
                int X = sx + 1;
                int Y = sy - 1;
                if (X == dx && Y == dy) {
                    return 1;
                }

                X = sx + 1;
                Y = sy + 1;
                if (X == dx && Y == dy) {
                    return 1;
                }
            }
        } 
        else if (t == 1) {
            int X = sx - 1;
            int Y = sy;
            if (X == dx && Y == dy) {
                return 1;
            }
        } else {
            int X = sx + 1;
            int Y = sy;
            if (X == dx && Y == dy) {
                return 1;
            }
        }
        return 0;
    }
}
