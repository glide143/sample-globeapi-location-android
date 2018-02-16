
package com.example.melchy.globle_location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobeLocation {

    @SerializedName("terminalLocationList")
    @Expose
    private TerminalLocationList terminalLocationList;

    public TerminalLocationList getTerminalLocationList() {
        return terminalLocationList;
    }

    public void setTerminalLocationList(TerminalLocationList terminalLocationList) {
        this.terminalLocationList = terminalLocationList;
    }

}
