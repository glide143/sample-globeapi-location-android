
package com.example.melchy.globle_location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TerminalLocationList {

    @SerializedName("terminalLocation")
    @Expose
    private TerminalLocation terminalLocation;

    public TerminalLocation getTerminalLocation() {
        return terminalLocation;
    }

    public void setTerminalLocation(TerminalLocation terminalLocation) {
        this.terminalLocation = terminalLocation;
    }

}
