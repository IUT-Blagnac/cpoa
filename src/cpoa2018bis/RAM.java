public class RAM {

    private String frequence;

    public RAM(String frequence) {
        this.frequence = frequence;
    }

    public String getFrequence(){
        return this.frequence;
    }

    public void allumer() {
        System.out.println("RAM : Je m'allume");
    }
}
