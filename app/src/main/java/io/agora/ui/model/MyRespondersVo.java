package io.agora.ui.model;

import java.util.List;

public class MyRespondersVo {
    private List<String> myResponders;

    public MyRespondersVo(List<String> myResponders) {
        this.myResponders = myResponders;
    }

    public List<String> getMyResponders() {
        return myResponders;
    }

    public void setMyResponders(List<String> myResponders) {
        this.myResponders = myResponders;
    }
}
