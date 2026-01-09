package com.wd.maven.aggregation;

public class MavenAggregationSettings {
    private String groupId = "com.example";
    private String artifactId = "demo";

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public MavenAggregationSettings() { }
}

