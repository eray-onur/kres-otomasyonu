package meltem.services.data_access.concrete;

import meltem.enums.LogType;
import meltem.models.Branch;
import meltem.models.Classroom;
import meltem.models.User;
import meltem.services.data_access.PersistentDataService;
import meltem.services.logging.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BranchRepository extends PersistentDataService<Branch> {
    @Override
    public Branch fetchById(int id) {
        Branch[] branchList = new Branch[1];
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM branch_courses WHERE branch_id = %d", id);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                Branch branch = new Branch(
                        rs.getInt("branch_id"),
                        rs.getString("branch_name"),
                        rs.getInt("branch_teacher_id"),
                        rs.getInt("branch_capacity")
                );
                branchList[0] = branch;
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        return branchList[0];
    }

    @Override
    public List<Branch> fetchAll() {
        List<Branch> branchList = new ArrayList<Branch>();
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM branch_courses";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                Branch branch = new Branch (
                        rs.getInt("branch_id"),
                        rs.getString("branch_name"),
                        rs.getInt("branch_teacher_id"),
                        rs.getInt("branch_capacity")
                );
                branchList.add(branch);
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        return branchList;
    }

    @Override
    public void Add(Branch entity) {
        try {
            this.connect();
            String sql = "INSERT INTO branch_courses VALUES(?, ?, ?)";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getBranchCourseName());
            pst.setInt(2, entity.getBranchTeacher());
            pst.setInt(3, entity.getBranchCapacity());
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            this.close();
        }
        catch(Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    @Override
    public void UpdateById(Branch branch, int id) {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "UPDATE branch_courses SET branch_name = ?, branch_teacher_id = ?, branch_capacity = ? WHERE branch_id = ?";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, branch.getBranchCourseName());
            pst.setInt(2, branch.getBranchTeacher());
            pst.setInt(3, branch.getBranchCapacity());
            pst.setInt(4, id);
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "DELETE FROM branch_courses WHERE branch_id = ?";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setInt(1, id);
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }
}
