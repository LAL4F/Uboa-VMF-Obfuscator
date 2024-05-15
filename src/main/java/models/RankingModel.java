package models;

import java.time.LocalDate;

public class RankingModel {
    private int id;
    private String username;
    private String mapname;
    private LocalDate dateObfuscated;
    private int timeElapsed;
    private int numEnts;
    private float fileSize;
    private String vmfContent;

    public RankingModel(int id, String username, String mapname, String vmfContent, int timeElapsed, int numEnts, float fileSize, LocalDate dateObfuscated) {
        this.id = id;
        this.username = username;
        this.mapname = mapname;
        this.vmfContent = vmfContent;
        this.fileSize = fileSize;
        this.timeElapsed = timeElapsed;
        this.numEnts = numEnts;
        this.dateObfuscated = dateObfuscated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMapname() {
        return mapname;
    }

    public void setMapname(String mapname) {
        this.mapname = mapname;
    }

    public String getVmfContent() {
        return vmfContent;
    }

    public void setVmfContent(String vmfContent) {
        this.vmfContent = vmfContent;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public int getNumEnts() {
        return numEnts;
    }

    public void setNumEnts(int numEnts) {
        this.numEnts = numEnts;
    }

    public float getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }


    public LocalDate getDateObfuscated() {
        return dateObfuscated;
    }

    public void setDateObfuscated(LocalDate dateObfuscated) {
        this.dateObfuscated = dateObfuscated;
    }
    
    public String[] toArray() {
        return new String[] {
            String.valueOf(id),
            username,
            mapname,
            dateObfuscated.toString(),
            String.valueOf(timeElapsed),
            String.valueOf(numEnts),
            String.valueOf(fileSize),
            vmfContent
        };
    }
    
    public static String[] tableHeaderArray() {
        return new String[] {
            "ID", 
            "Username", 
            "Map", 
            "Date", 
            "Time Elapsed (s)", 
            "Entities", 
            "Size (MB)", 
            "Download"};
    }
}
