package platform.model;

public class Codes {
    private String code;

    public Codes(String code){
        this.code = code;
    }

    //getters and setters

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //Overrides 'default' method in Object class
    @Override
    public String toString(){
        return code;
    }
}
