import java.util.Random;

public class ScrollArtV1 {
    static final int WIDTH = getTerminalWidth() - 1;
    static final int BUNNY_WIDTH = 8;
    static final int BUNNY_HEIGHT = 6;
    static final Random rand = new Random();

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int iterations = 0;
        char[][] nextRows = new char[34][WIDTH]; // store upcoming rows
        for (int i = 0; i < nextRows.length; i++) {
            nextRows[i] = emptyRow();
        }

        char[] prevRow = nextRows[0];
        while (true) {
            // At each column on the top row, 1% chance to add a new image
            for (int x = 0; x < WIDTH - 34; x += 34) {
                if (isBlank(prevRow, x) && rand.nextDouble() < 0.08) {
                    loadNextRowsWithImage(nextRows, x);
                }
            }
            // Print and remove the top row
            System.out.println(new String(nextRows[0]));
            prevRow = nextRows[0];
            // Shift all rows up
            shiftRowsUp(nextRows);
            Thread.sleep(100); // Delay in ms
            long time = System.currentTimeMillis() - startTime;
            iterations++;
            // System.err.println("average time per frame: " + (time / iterations) + " ms");
        }
    }

    private static boolean isBlank(char[] prevRow, int x) {
        for (int i = x; i < x + 34; i++) {
            if (prevRow[i] != ' ') {
                return false;
            }
        }
        return true;
    }

    private static void loadNextRowsWithImage(char[][] nextRows, int x) {
        // char[][][] images;
        AsciiArt mouse = new AsciiArt(getMouse());
        mouse.switchRowsAndCols();
        String llama = """
                /^---^\\
                | . . |
                \\  `  /   MS
                /=====\\________
                /               \\[]
                \\               /
                ---------------
                | || |   | || |
                [|][|]   [|][|]
                """;
        AsciiArt l = new AsciiArt(llama);
        AsciiArt[] images = { new AsciiArt(getBunny()), new AsciiArt(getCarrot()),
                mouse, l };
        AsciiArt art = images[rand.nextInt(images.length)];
        AsciiArt widestArt = AsciiArt.getWidestArt(images);
        art.resize(widestArt.width);

        for (int iy = 0; iy < art.height; iy++) { // height
            for (int ix = 0; ix < art.width; ix++) { // widrth
                nextRows[iy][x + ix] = art.img[iy][ix];
            }
        }
    }

    static void shiftRowsUp(char[][] nextRows) {
        for (int i = 1; i < nextRows.length; i++) {
            nextRows[i - 1] = nextRows[i];
        }
        nextRows[nextRows.length - 1] = emptyRow();
    }

    static char[] emptyRow() {
        char[] row = new char[WIDTH];
        for (int i = 0; i < WIDTH; i++) {
            row[i] = ' ';
        }
        return row;
    }

    static char[][] getYeppi() {
        char[][] img = new char[32][34];
        // fill with empty space
        for (int y = 0; y < 32; y++) {
            for (int x = 0; x < 34; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        img[0][1] = '.';
        img[0][2] = '_';
        img[0][3] = '_';
        img[0][25] = '.';
        img[0][26] = '^';
        img[0][27] = '^';

        img[1][1] = '/';
        img[1][2] = '"';
        img[1][5] = '"';
        img[1][6] = ';';
        img[1][25] = '|';
        img[1][29] = '"';
        img[1][30] = ')';

        img[2][0] = '.';
        img[2][7] = '\\';
        img[2][24] = '/';
        img[2][25] = '"';
        img[2][28] = 'X';
        img[2][30] = '?';

        img[3][0] = '|';
        img[3][1] = '"';
        img[3][3] = 'X';
        img[3][7] = '\\';
        img[3][22] = '/';
        img[3][26] = 'X';
        img[3][27] = 'X';
        img[3][30] = '|';
        img[4][0] = '\\';
        img[4][2] = 'X';
        img[4][3] = 'X';
        img[4][4] = 'X';
        img[4][5] = '.';
        img[4][8] = '\\';
        img[4][9] = '+';
        img[4][10] = '-';
        img[4][11] = '_';
        img[4][12] = '.';
        img[4][13] = '_';
        img[4][14] = '.';
        img[4][15] = '+';
        img[4][16] = '-';
        img[4][17] = '.';
        img[4][18] = '.';
        img[4][19] = '-';
        img[4][20] = '#';
        img[4][21] = '/';
        img[4][22] = '-';
        img[4][25] = 'X';
        img[4][26] = 'X';
        img[4][27] = 'X';
        img[4][28] = 'X';
        img[4][31] = ')';

        img[5][0] = '\'';
        img[5][3] = '%';
        img[5][4] = '$';
        img[5][25] = '*';
        img[5][26] = '-';
        img[5][27] = '-';
        img[5][28] = '_';
        img[5][31] = '.';

        img[6][0] = '\\';
        img[6][32] = '|';

        img[7][0] = '.';
        img[7][1] = '|';
        img[7][6] = '_';
        img[7][7] = '_';
        img[7][23] = '_';
        img[7][24] = '_';
        img[7][25] = '_';
        img[7][32] = '\\';

        img[8][0] = '|';
        img[8][1] = '"';
        img[8][5] = '*';
        img[8][6] = '#';
        img[8][7] = '#';
        img[8][8] = '#';
        img[8][9] = 'x';
        img[8][10] = '*';
        img[8][22] = '#';
        img[8][23] = 'x';
        img[8][24] = '#';
        img[8][25] = '#';
        img[8][27] = '\\';
        img[8][33] = ':';

        img[9][0] = '\\';
        img[9][4] = '(';
        img[9][6] = '#';
        img[9][7] = '#';
        img[9][8] = '#';
        img[9][9] = 'x';
        img[9][10] = '@';
        img[9][21] = 'x';
        img[9][22] = '#';
        img[9][23] = '#';
        img[9][24] = '#';
        img[9][25] = '#';
        img[9][33] = '|';

        img[10][0] = '\'';
        img[10][1] = '.';
        img[10][15] = '_';
        img[10][16] = '.';
        img[10][17] = '.';
        img[10][18] = '_';
        img[10][32] = ')';
        img[10][33] = ':';

        img[11][1] = '\'';
        img[11][2] = '.';
        img[11][14] = '\\';
        img[11][15] = '=';
        img[11][18] = '=';
        img[11][19] = '/';
        img[11][32] = '/';

        img[12][2] = '\\';
        img[12][16] = '^';
        img[12][17] = '^';
        img[12][31] = '.';

        img[13][3] = '+';
        img[13][16] = '/';
        img[13][17] = '\\';
        img[13][30] = '?';
        img[13][31] = '"';

        img[14][4] = '"';
        img[14][13] = '*';
        img[14][14] = '-';
        img[14][15] = '"';
        img[14][18] = '"';
        img[14][19] = '-';
        img[14][20] = '*';
        img[14][28] = '/';
        img[14][29] = '"';

        img[15][8] = '"';
        img[15][9] = '+';
        img[15][10] = '=';
        img[15][11] = '.';
        img[15][12] = '_';
        img[15][13] = '_';
        img[15][14] = '_';
        img[15][15] = '\\';
        img[15][16] = '_';
        img[15][17] = '_';
        img[15][18] = '/';
        img[15][19] = '.';
        img[15][20] = '_';
        img[15][21] = '_';
        img[15][22] = '.';
        img[15][23] = '-';
        img[15][24] = '-';
        img[15][25] = '=';
        img[15][26] = '=';
        img[15][27] = '"';
        return img;
    }

    static char[][] getMouse() {

        char[][] mouse = new char[34][19];

        for (int y = 0; y < 19; y++) {
            for (int x = 0; x < 34; x++) {
                mouse[x][y] = ' ';
            }
        }

        mouse[5][3] = '@';
        mouse[6][3] = '@';
        mouse[7][3] = '@';
        mouse[8][3] = '@';
        mouse[22][3] = '@';
        mouse[23][3] = '@';
        mouse[24][3] = '@';
        mouse[27][3] = '@';
        mouse[28][3] = '@';
        mouse[29][3] = '@';
        mouse[24][2] = '@';
        mouse[25][2] = '@';
        mouse[26][2] = '@';
        mouse[27][2] = '@';
        mouse[4][4] = '@';
        mouse[5][4] = '@';
        mouse[9][4] = '@';
        mouse[10][4] = '@';
        mouse[21][4] = '@';
        mouse[24][4] = '.';
        mouse[25][4] = '.';
        mouse[26][4] = '.';
        mouse[28][4] = '@';
        mouse[29][4] = '@';
        mouse[5][5] = '@';
        mouse[11][5] = '@';
        mouse[12][5] = '@';
        mouse[14][5] = '-';
        mouse[15][5] = '-';
        mouse[16][5] = '-';
        mouse[17][5] = '-';
        mouse[18][5] = '-';
        mouse[19][5] = '-';
        mouse[20][5] = '-';
        mouse[21][5] = '@';
        mouse[22][5] = '@';
        mouse[24][5] = '.';
        mouse[26][5] = '.';
        mouse[27][5] = '@';
        mouse[28][5] = '@';
        mouse[3][6] = '@';
        mouse[6][6] = '.';
        mouse[7][6] = '.';
        mouse[8][6] = '.';
        mouse[9][6] = '.';
        mouse[12][6] = '@';
        mouse[13][6] = '@';
        mouse[21][6] = '-';
        mouse[22][6] = '@';
        mouse[23][6] = '-';
        mouse[24][6] = '-';
        mouse[25][6] = '-';
        mouse[26][6] = '-';
        mouse[27][6] = '@';
        mouse[3][7] = '@';
        mouse[6][7] = '.';
        mouse[9][7] = '.';
        mouse[10][7] = '.';
        mouse[27][7] = '@';
        mouse[28][7] = ')';
        mouse[3][8] = '@';
        mouse[6][8] = '.';
        mouse[28][8] = ')';
        mouse[29][8] = ')';
        mouse[3][9] = '@';
        mouse[7][9] = '.';
        mouse[29][9] = ')';
        mouse[3][10] = '@';
        mouse[8][10] = '.';
        mouse[9][10] = '.';
        mouse[10][10] = '.';
        mouse[15][10] = '[';
        mouse[17][10] = ']';
        mouse[22][10] = '[';
        mouse[24][10] = ']';
        mouse[19][10] = ')';
        mouse[20][10] = ')';
        mouse[4][11] = '@';
        mouse[5][11] = '@';
        mouse[15][11] = '|';
        mouse[17][11] = '►';
        mouse[22][11] = '|';
        mouse[24][11] = '►';
        mouse[30][11] = ')';
        mouse[6][12] = '@';
        mouse[7][12] = '@';
        mouse[8][12] = '@';
        mouse[10][12] = '!';
        mouse[15][12] = 'L';
        mouse[17][12] = ']';
        mouse[22][12] = 'L';
        mouse[24][12] = ']';
        mouse[20][12] = ')';
        mouse[9][13] = '@';
        mouse[10][13] = '!';
        mouse[28][13] = 'W';
        mouse[29][13] = 'W';
        mouse[30][13] = 'W';
        mouse[31][13] = 'W';
        mouse[32][13] = 'W';
        mouse[33][13] = 'W';
        mouse[10][14] = '!';
        mouse[17][14] = '-';
        mouse[18][14] = '-';
        mouse[19][14] = '-';
        mouse[20][14] = '-';
        mouse[29][14] = 'W';
        mouse[30][14] = '0';
        mouse[31][14] = 'W';
        mouse[32][14] = '0';
        mouse[33][14] = 'W';
        mouse[10][15] = '!';
        mouse[11][15] = '!';
        mouse[12][15] = '!';
        mouse[20][15] = '-';
        mouse[21][15] = '-';
        mouse[22][15] = ']';
        mouse[29][15] = '-';
        mouse[30][15] = 'W';
        mouse[31][15] = 'W';
        mouse[32][15] = 'W';
        mouse[12][16] = '1';
        mouse[13][16] = '!';
        mouse[22][16] = 'L';
        mouse[23][16] = '-';
        mouse[24][16] = '-';
        mouse[25][16] = '-';
        mouse[26][16] = '-';
        mouse[27][16] = '&';
        mouse[28][16] = '&';
        mouse[29][16] = '&';
        mouse[13][17] = '*';
        mouse[14][17] = '*';
        mouse[15][17] = '*';
        mouse[16][17] = '*';
        mouse[22][17] = '*';
        mouse[23][17] = '&';
        mouse[24][17] = '&';
        mouse[25][17] = '&';
        mouse[26][17] = '&';
        mouse[16][18] = '*';
        mouse[17][18] = '-';
        mouse[18][18] = '*';
        mouse[19][18] = '*';
        mouse[20][18] = '*';
        mouse[21][18] = '-';
        mouse[22][18] = '*';
        mouse[26][18] = 'G';
        mouse[27][18] = '.';
        mouse[28][18] = 'C';

        return mouse;
    }

    // rename your function here
    static char[][] getBunny() {
        char[][] img = new char[BUNNY_HEIGHT][BUNNY_WIDTH];
        // fill with empty space
        for (int y = 0; y < BUNNY_HEIGHT; y++) {
            for (int x = 0; x < BUNNY_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        img[0][0] = '(';
        img[0][1] = '\\';
        img[0][2] = '(';
        img[0][3] = '\\';
        img[1][0] = '(';
        img[1][1] = '-';
        img[1][2] = '.';
        img[1][3] = '-';
        img[1][4] = ')';
        img[2][0] = 'o';
        img[2][1] = '_';
        img[2][2] = '(';
        img[2][3] = '"';
        img[2][4] = ')';
        img[2][5] = '(';
        img[2][6] = '"';
        img[2][7] = ')';
        img[3][1] = '/';
        img[3][4] = '\\';
        img[4][1] = '\\';
        img[4][2] = '\\';
        img[4][3] = '/';
        img[4][4] = '/';
        img[5][2] = '\\';
        img[5][3] = '/';

        return img;
    }

    static char[][] getCarrot() {
        char[][] img = new char[BUNNY_HEIGHT][BUNNY_WIDTH];
        // fill with empty space
        for (int y = 0; y < BUNNY_HEIGHT; y++) {
            for (int x = 0; x < BUNNY_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        img[0][5] = '\\';
        img[0][6] = ')';
        img[0][7] = '/';
        img[1][4] = '-';
        img[1][5] = '-';
        img[1][6] = 'v';

        img[2][3] = '/';
        img[2][4] = 'r';
        img[2][6] = ')';

        img[3][2] = '>';
        img[3][3] = 'r';
        img[3][4] = '.';
        img[3][5] = '/';

        img[4][1] = '/';
        img[4][3] = '\'';

        img[5][0] = 'c';
        img[5][1] = '_';
        img[5][2] = '/';

        return img;
    }

    public static int getTerminalWidth() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return getUnixTerminalWidth();
        } else {
            return 80; // fallback for unknown OS
        }
    }

    private static int getUnixTerminalWidth() {
        try {
            // Try to get terminal size from environment variables first
            String columns = System.getenv("COLUMNS");
            if (columns != null && !columns.isEmpty()) {
                return Integer.parseInt(columns);
            }

            // Fallback to stty command
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "stty size </dev/tty");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            String output = reader.readLine();
            if (output != null && !output.isEmpty()) {
                String[] parts = output.trim().split(" ");
                return Integer.parseInt(parts[1]); // columns
            }
        } catch (Exception ignored) {
            // Silently ignore errors and fall back to default
        }
        return 80; // fallback
    }

}