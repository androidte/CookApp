package com.zt8989.cookapp.Model;

/**
 * Created by zhou on 2015/5/10.
 */
public class CookSearchItem extends BaseCookItem{
    /*
    * content string
    description string
    id long
    img string
    keywords string
    name html string
    type string
    * */

    private String content;
    private String description;
    private String keywrods;
    private String type;



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywrods() {
        return keywrods;
    }

    public void setKeywrods(String keywrods) {
        this.keywrods = keywrods;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
