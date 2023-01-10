package enums;

public enum SysMessage {

    PASSWORD_NOT_FOUND ("Passwort und Kennung stimmen nicht überein!"),
    USERNAME_NOT_FOUND ("Kennung nicht gefunden!");


    private final String name;

    SysMessage(String message){
        this.name = message;
    }


    public boolean equalsName(String text) {
        return this.name.equals(text);
    }

    public String toString() {
        return this.name;
    }

}
