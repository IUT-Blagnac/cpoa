import java.util.ArrayList;

public class CarteMere {

    private String nomCM;
    private Boolean EtatDallumage;
    private Processeur CPU;
    private RAM ram;
    private CarteGraphique GPU;
    private ArrayList<DisqueDur> HDDs;
    private ArrayList<CableSata> cableSatas;

    public CarteMere(String nomCM, Processeur CPU, RAM ram, CarteGraphique GPU){
        this.nomCM = nomCM;
        this.CPU = CPU;
        this.ram = ram;
        this.GPU = GPU;
        this.HDDs = new ArrayList<>();
        this.cableSatas = new ArrayList<>();
    }

    public void BootUp(){
        this.EtatDallumage = !this.EtatDallumage;
    }

    public void Shutdown(){
        this.EtatDallumage = !this.EtatDallumage;
    }

    public void AjouterDisqueDur(DisqueDur HDD){
        this.HDDs.add(HDD);
        this.cableSatas.add(HDD.getSATA());
    }

    public void RetirerDisqueDur(DisqueDur HDD){
        this.HDDs.remove(HDD);
        this.cableSatas.remove(HDD.getSATA());
    }

    public void allumer() {
        System.out.println("Carte Mère : Je m'allume!");
    }
}
