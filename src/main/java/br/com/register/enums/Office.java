package br.com.register.enums;

/**
 * Created by asampaio on 30/12/17.
 */
public enum Office {

    EMPLOYER("employer"),
    FINAL_USER("final_user");

    private String description;
    Office(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
