package renderer;

/**
 * Pixel is a helper class. It is used for multi-threading in the renderer and
 * for follow up its progress.<br/>
 * There is a main follow-up object and several secondary objects - one in each
 * thread.
 *
 * @author Dan
 *
 */
class Pixel {
    private static int maxRows = 0;
    private static int maxCols = 0;
    private static long totalPixels = 0L;

    private static volatile int cRow = 0;
    private static volatile int cCol = -1;
    private static volatile long pixels = 0l;
    private static volatile long last = -1l;
    private static volatile int lastPrinted = -1;

    private static boolean print = false;
    private static long printInterval = 100l;
    private static final String PRINT_FORMAT = "%5.1f%%\r";
    private static Object mutexNext = new Object();
    private static Object mutexPixels = new Object();

    int row;
    int col;

    /**
     * Initialize pixel data for multi-threading
     *
     * @param maxRows  the amount of pixel rows
     * @param maxCols  the amount of pixel columns
     * @param interval print time interval in seconds, 0 if printing is not required
     */
    static void initialize(int maxRows, int maxCols, double interval) {
        Pixel.maxRows = maxRows;
        Pixel.maxCols = maxCols;
        Pixel.totalPixels = (long) maxRows * maxCols;
        cRow = 0;
        cCol = -1;
        pixels = 0;
        printInterval = (int) (interval * 1000);
        print = printInterval != 0;
    }

    public static void getrow() {
        maxRows=3;
    }

    /**
     * Function for thread-safe manipulating of main follow-up Pixel object - this
     * function is critical section for all the threads, and static data is the
     * shared data of this critical section.<br/>
     * The function provides next available pixel number each call.
     *
     * @return true if next pixel is allocated, false if there are no more pixels
     */
    public boolean nextPixel() {
        synchronized (mutexNext) {
            if (cRow == maxRows)
                return false;
            ++cCol;
            if (cCol < maxCols) {
                row = cRow;
                col = cCol;
                return true;
            }
            cCol = 0;
            ++cRow;
            if (cRow < maxRows) {
                row = cRow;
                col = cCol;
                return true;
            }
            return false;
        }
    }

    /**
     * Finish pixel processing
     */
    static void pixelDone() {
        synchronized (mutexPixels) {
            ++pixels;
        }
    }

    /**
     * Wait for all pixels to be done and print the progress percentage - must be
     * run from the main thread
     */
    public static void waitToFinish() {
        if (print)
            System.out.printf(PRINT_FORMAT, 0d);

        while (last < totalPixels) {
            printPixel();
            try {
                Thread.sleep(printInterval);
            } catch (InterruptedException ignore) {
                if (print)
                    System.out.print("");
            }
        }
        if (print)
            System.out.println("100.0%");
    }

    /**
     * Print pixel progress percentage
     */
    public static void printPixel() {
        long current = pixels;
        if (print && last != current) {
            int percentage = (int) (1000l * current / totalPixels);
            if (lastPrinted != percentage) {
                last = current;
                lastPrinted = percentage;
                System.out.printf(PRINT_FORMAT, percentage / 10d);
            }
        }
    }

    //   public static int getRow() {
    //    return row;
    //  }

    // public static int getCol() {
    //    return col;
    // }
}