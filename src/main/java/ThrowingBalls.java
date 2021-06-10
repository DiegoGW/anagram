import java.util.Random;

public class ThrowingBalls {

    private static int floorBroken = 0;

    /**
     * n = number of floors of the building
     * f = number of the floor that defines if a ball will get broken or not
     * bb = function that returns if a throwing a ball from a specific floor will get broken
     * To find "f" in ~lg n throws it could be applied a binarySearch considering
     * the height of the building = n floors and the function that returns true
     * if a ball gets broken from a sepecific floor
     *
     * @param args
     */

    public static void main(String args[]){
        int n = 1000;
        Random rand = new Random();
        floorBroken = rand.nextInt(n);

        System.out.println("The random floor F is: " + floorBroken);
        System.out.println("The found floor F is: " + findFloorF(n,0));
        System.out.println("Other way to find floor F is: " + findFloorFOtherWay());


    }

    public static boolean isBallBroken(int floor){
        return (floor >= floorBroken);
    }

    /**
     * Find floor F based on the N floors that has a building
     * Big O = lg N
     * Precondition: N >= F
     * @param maxFloor
     * @param minFloor
     * @return
     */
    public static int findFloorF(int maxFloor, int minFloor){
        //Base condition check
        if (maxFloor < minFloor){
            return -1;
        }
        if (maxFloor==0){
            return 0;
        }else if ((maxFloor==1) && (isBallBroken(maxFloor))){
            return 0;
        }else if ((maxFloor==1) && (!isBallBroken(maxFloor))){
            return 1;
        }else if ((maxFloor == minFloor+1) && (!isBallBroken(maxFloor))){
            return minFloor;
        }else if ((maxFloor == minFloor+1) && (isBallBroken(maxFloor))) {
            return maxFloor;
        }else{
            if (isBallBroken(Math.floorDiv(maxFloor+minFloor,2))){
                return (findFloorF(Math.floorDiv(maxFloor+minFloor,2), minFloor));
            }else{
                return (findFloorF(maxFloor, Math.floorDiv(maxFloor+minFloor,2)));
            }
        }
    }

    /**
     * Starting the search from the bottom 1,2,4,8...until the first ball gets broken at 2^(k+1)
     * Then do a binary search between 2^k and 2^(k+1)
     * Big O = 2lg F (lg F steps of the exponential search + lg F of the binarySearch)
     * @return
     */
    public static int findFloorFOtherWay(){
        int i = 0;
        while (!isBallBroken(2^i)){
            i++;
        }
        return findFloorF(2^i, 2^(i-1));
    }


}
