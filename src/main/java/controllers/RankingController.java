package controllers;

import database.SQLDatabaseManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import models.RankingModel;

public class RankingController {
    private static Connection connection;

    private static boolean initDBConnection() {
        try {
            connection = SQLDatabaseManager.connect();
            return true;
        } catch (SQLException e) {
            System.err.println("");
        }
        return false;
    }

    private static boolean closeDBConnection() {
        try {
            SQLDatabaseManager.disconnect(connection);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexion");
        }
        return false;
    }
    
    private static LocalDate convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    }
    
    //Fuck dates
    public static ArrayList<RankingModel> getPersonList() {
        ArrayList<RankingModel> rankingList = null;
        if (!initDBConnection()) {
            return null;
        }

        try {
            String query = "SELECT *\n" +
                    "from persona\n" +
                    "ORDER BY id";

            PreparedStatement statement = connection.prepareStatement(query);

            var result = statement.executeQuery();
            rankingList = new ArrayList<RankingModel>();

            while (result.next()) {
                var ranking = new RankingModel(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("mapname"),
                        result.getString("vmfContent"),
                        result.getInt("timeElapsed"),
                        result.getInt("numEnts"),
                        result.getFloat("vmfLength"),
                        convertToLocalDateTimeViaInstant(result.getDate("dateObfuscated"))
            );

                rankingList.add(ranking);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return rankingList;
    }
    
    //Fuck dates again
    public static boolean createPerson(RankingModel ranking) {
        if (!initDBConnection()) {
            return false;
        }

        try {
            String query = "INSERT INTO ranking VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            CallableStatement callableStatement = connection.prepareCall(query);
            
            callableStatement.setInt(1, ranking.getId());
            callableStatement.setString(2, ranking.getUsername());
            callableStatement.setString(3, ranking.getMapname());
            callableStatement.setString(4, ranking.getVmfContent());
            callableStatement.setInt(5, ranking.getTimeElapsed());
            callableStatement.setInt(6, ranking.getNumEnts());
            callableStatement.setFloat(7, ranking.getFileSize());
            callableStatement.setDate(8, java.sql.Date.valueOf(ranking.getDateObfuscated()));
            
            callableStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        
        return false;
    }
    
    public static RankingModel findRankingById(int id) {
        RankingModel ranking;
        if (!initDBConnection()) {
            return null;
        }

        try {
            String query = "SELECT *\n" +
                    "FROM ranking WHERE id = ?\n" +
                    "ORDER BY id";

            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, id);
            
            var result = callableStatement.executeQuery();

            if (result.next()) {
                ranking = new RankingModel(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("mapname"),
                        result.getString("vmfContent"),
                        result.getInt("timeElapsed"),
                        result.getInt("numEnts"),
                        result.getFloat("vmfLength"),
                        convertToLocalDateTimeViaInstant(result.getDate("dateObfuscated"))
                );
                return ranking;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return null;
    }
    
    public static int findBestID() {
        int counter = 0;
        boolean foundBestID = false;
        if (!initDBConnection()) {
            return -1;
        }

        try {
            String query = "SELECT COUNT(*)\n" +
                    "from ranking;";

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                counter = rs.getInt(1);
                counter++;
            }
            
            RankingModel personExist;
            
             while (!foundBestID) {
                personExist = findRankingById(counter);
                if (personExist != null) {
                    System.out.println(counter + " exists, increasing");
                    counter++;
                } else {
                    System.out.println(counter + " found best id");
                    foundBestID = true;
                }
            }
            
            return counter;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDBConnection();
        }
        
        return counter;   
    } 
}
