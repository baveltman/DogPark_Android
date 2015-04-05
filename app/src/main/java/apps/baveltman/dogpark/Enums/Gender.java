package apps.baveltman.dogpark.Enums;

import com.google.gson.annotations.SerializedName;

public enum Gender {

    @SerializedName("0")
    FEMALE (0),

    @SerializedName("1")
    MALE (1);

    private final int value;
    public int getValue() {
        return value;
    }

    private Gender(int value) {
        this.value = value;
    }

}
