public class DisqueDur {

    private String capacite;
    private CableSata SATA;

    public DisqueDur(String capacite, CableSata SATA) {
        this.capacite = capacite;
        this.SATA = SATA;
    }

    public String getCapacite(){
        return this.capacite;
    }

    public CableSata getSATA(){
        return this.SATA;
    }

    public void allumer() {
        System.out.println("HDD : Je m'allume");
    }
}
