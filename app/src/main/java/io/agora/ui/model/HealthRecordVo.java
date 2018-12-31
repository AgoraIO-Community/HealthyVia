package io.agora.ui.model;

import java.util.List;

public class HealthRecordVo {
    private List<String> healthConditions;

    public HealthRecordVo(List<String> healthConditions) {
        this.healthConditions = healthConditions;
    }

    public List<String> getHealthConditions() {
        return healthConditions;
    }

    public void setHealthConditions(List<String> healthConditions) {
        this.healthConditions = healthConditions;
    }
}
