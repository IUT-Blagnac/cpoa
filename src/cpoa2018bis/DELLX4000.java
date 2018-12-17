import java.util.ArrayList;

public class DELLX4000{


    public CarteGraphique cg = new CarteGraphique("GTX 1080");
    public Alimentation al = new Alimentation("650W");
    public Processeur cpu = new Processeur("i5-4790k");
    public RAM ram = new RAM("1600MHz");
    ArrayList<DisqueDur> HDDs;
    public CarteMere cm = new CarteMere("MSI-315552GL44", cpu, ram, cg);

    public DELLX4000(){
        this.HDDs = new ArrayList<>();
        CableSata cable1 = new CableSata();
        CableSata cable2 = new CableSata();
        DisqueDur SSD = new DisqueDur("120GO", cable1);
        DisqueDur HDD = new DisqueDur("1To", cable2);
        this.HDDs.add(SSD);
        this.HDDs.add(HDD);
        for(DisqueDur e : HDDs) this.cm.AjouterDisqueDur(e);
    }

    public void AllumerPC(){
        System.out.println("Je m'allume");
        cm.allumer();
        cpu.allumer();
        ram.allumer();
        cg.allumer();
        for(DisqueDur dd : HDDs) dd.allumer();
        System.out.println("Je me suis bien allumé");
    }

    public void EteindrePC(){
        System.out.println("Je m'eteint");
    }


}
