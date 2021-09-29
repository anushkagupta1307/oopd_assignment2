package src.vaccine;

public class Vaccine {

    private int id;
    private String name;
    private int inject;
    private int effect;

    private int boost=10;
    private int durability;

    public int getInject() {
        return inject;
    }

    public void setInject(int inject) {
        this.inject = inject;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoost() {
        return boost;
    }

    public void setBoost(int boost) {
        this.boost = boost;
    }

    public int getDurability(int wave_number) {

        if(wave_number==1)
          return 100;
        else if(wave_number==2)
            return 150;
        else if(wave_number==3)
            return 200;
        else if(wave_number==4)
            return 250;
        return 0;
    }

    public void setDurability(int durability) {
       this.durability=durability;
    }

}
