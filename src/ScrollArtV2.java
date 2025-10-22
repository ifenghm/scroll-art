import java.util.Random;

public class ScrollArtV2 {
    static final int WIDTH = getTerminalWidth() - 1;
    static final int BUNNY_WIDTH = 8;
    static final int BUNNY_HEIGHT = 6;
    static final Random rand = new Random();
    // NO OVERLAPS
    // 1. Skip every w (width) characters instead of every column to reduce
    // horizontal overlap
    // 2. How do you reduce overlap vertically? Remember previous row at the
    // specific width

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int iterations = 0;
        char[][] nextRows = new char[15][WIDTH]; // store upcoming rows
        for (int i = 0; i < nextRows.length; i++) {
            nextRows[i] = emptyRow();
        }
        char[] prevRow = nextRows[0];

        while (true) {
            // At each lane now on the top row, 7% chance to add a new image
            for (int x = 0; x < WIDTH - 15; x += 15) {
                if (isBlank(prevRow, x) && rand.nextDouble() < 0.07) {
                    loadNextRowsWithImage(nextRows, x);
                }
            }
            // Print and remove the top row
            System.out.println(new String(nextRows[0]));
            prevRow = nextRows[0]; // update prev row
            // Shift all rows up
            shiftRowsUp(nextRows);
            Thread.sleep(40); // Delay in ms
            long time = System.currentTimeMillis() - startTime;
            iterations++;

            // System.err.println("average time per frame: " + (time / iterations) + " ms");
        }
    }

    // #2: checking if row is blank
    static boolean isBlank(char[] row, int x) {
        for (int i = x; i < x + 15; i++) {
            if (row[i] != ' ') {
                return false;
            }
        }
        return true;
    }

    private static void loadNextRowsWithImage(char[][] nextRows, int x) {
        // triple array version to better manage all images, it's getting a little bit out of hand!! and it is not like they are all the same size anyway.
        char[][][] images = {getBunny(), getCarrot(), getButterfly()};
        char[][] img = images[rand.nextInt(images.length)];
        for (int iy = 0; iy < img.length; iy++) { // height
            for (int ix = 0; ix < img[iy].length; ix++) { // width
                nextRows[iy][x + ix] = img[iy][ix];
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

    // Catherine's butterfly
     static char[][] getButterfly() {
        char[][]img=new char [15][15];
        for(int y=0; y<15; y++ ){
            for(int x=0;x<15;x++){
                img[y][x]=' ';
            }
        }
        //left wing
        img[1][5]='♥';
        img[1][6]='♥';
        img[1][10]='♥';
        img[1][11]='♥';

        img[2][4]='♥';
        img[2][7]='♥';
        img[2][9]='♥';
        img[2][12]='♥';

        img[3][4]='♥';
        img[3][6]='o';
        img [3][10]='o';
        img[3][12]='♥';
        img[3][8]='♥';

        img [4][5]='♥';
        img[4][8]='-';
        img[4][11]='♥';

        img[5][6]='♥';
        img[5][10]='♥';

        img [6][7]='♥';
        img [6][9]='♥';

        img[7][8]='♥';

        //right wing

        img[14][5]='♥';
        img[14][6]='♥';
        img[14][10]='♥';
        img[14][11]='♥';

        img[13][4]='♥';
        img[13][7]='♥';
        img[13][9]='♥';
        img[13][12]='♥';

        img[12][4]='♥';
        img[12][6]='o';
        img [12][10]='o';
        img[12][12]='♥';
        img[12][8]='♥';

        img [11][5]='♥';
        img[11][8]='-';
        img[11][11]='♥';

        img[10][6]='♥';
        img[10][10]='♥';

        img [9][7]='♥';
        img [9][9]='♥';

        img[8][8]='♥';

        return img;
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