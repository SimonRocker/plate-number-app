package simon.test;

/**
 * Created by admin on 11.10.2017.
 */

public class DbElement {


    private long id;


    private String plate_number;
    private String result;
    private int history_number;

    public DbElement(long id, String plate_number, String result, int history_number){
        this.id = id;
        this.plate_number = plate_number;
        this.result = result;
        this.history_number = history_number;
    }

    public DbElement(String plate_number, String result, int history_number) {
        this.plate_number = plate_number;
        this.result = result;
        this.history_number = history_number;
    }

    public DbElement (String plate_number, String result){
        this.plate_number = plate_number;
        this.result = result;
        this.history_number = -1;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getPlate_number() {
        return plate_number;
    }

    public int getHistory_number() {
        return history_number;
    }

    public void setHistory_number(int history_number) {
        this.history_number = history_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString(){
        return plate_number + " - " + result;
    }

}
