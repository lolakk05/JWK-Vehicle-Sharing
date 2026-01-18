package obserwator;

import pojazd.*;

public class StatsControler implements Observer{
    private int numCars;
    private int numMotors;
    private int numTir;
    private int numScooters;
    private int numBikes;
    private int[] stats;

    public StatsControler() {
        this.numCars = 0;
        this.numMotors = 0;
        this.numTir = 0;
        this.numScooters = 0;
        this.numBikes = 0;
        this.stats = new int[5];
    }

    @Override
    public void update(Pojazd vehicle, int d) {
        if(vehicle instanceof SamochodOsobowy){
            setNumCars(getNumCars() + d);
            stats[0] = getNumCars();
        } else if(vehicle instanceof Motocykl){
            setNumMotors(getNumMotors() + d);
            stats[1] = getNumMotors();
        } else if(vehicle instanceof Ciezarowka){
            setNumBikes(getNumTir() + d);
            stats[2] = getNumTir();
        } else if(vehicle instanceof HulajnogaElektryczna){
            setNumScooters(getNumScooters() + d);
            stats[3] = getNumScooters();
        } else if(vehicle instanceof Rower){
            setNumBikes(getNumBikes() + d);
            stats[4] = getNumBikes();
        }
    }

    public int getNumCars() {
        return numCars;
    }

    public int getNumMotors() {
        return numMotors;
    }

    public int getNumTir() {
        return numTir;
    }

    public int getNumScooters() {
        return numScooters;
    }

    public int getNumBikes() {
        return numBikes;
    }

    public void setNumCars(int numCars) {
        this.numCars = numCars;
    }

    public void setNumMotors(int numMotors) {
        this.numMotors = numMotors;
    }

    public void setNumTir(int numTir) {
        this.numTir = numTir;
    }

    public void setNumScooters(int numScooters) {
        this.numScooters = numScooters;
    }

    public void setNumBikes(int numBikes) {
        this.numBikes = numBikes;
    }

    public int[] getStats() {
        return stats;
    }

    public void setStats(int[] stats) {
        this.stats = stats;
    }
}
