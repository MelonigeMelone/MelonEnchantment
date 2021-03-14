package de.melonigemelone.enchantment.model;

public enum Message {
    GUI_TITLE("§eVerzauberungstisch")
    ;

    private String message;


    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
