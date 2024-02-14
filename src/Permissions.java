import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Permissions {
    private final Connection connection;
    private final Scanner sc;

    public Permissions(Connection connection, Scanner sc) {
        this.connection = connection;
        this.sc = sc;
    }

    public void curdPermission() throws Exception{
        System.out.println("Permissions");
        System.out.println("1- Insert New Permission\n2- Update Permission\n3- Get Permission\n4- Delete Permission");
        int operation = sc.nextInt();
        sc.nextLine();
        switch (operation){
            case 1:
                insertNewPermission();
                break;
            case 2:
                updatePermission();
                break;
            case 3:
                readPermission();
                break;
            case 4:
                deletePermission();
                break;
            default:
                System.out.println("Value Not Valid");
                break;
        }
    }
    private void deletePermission() throws Exception{
        System.out.println("Permission Delete");
        System.out.println("Please Enter Permission Name");
        String permissionName = sc.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from permission where perm_name = ?");
        preparedStatement.setString(1,permissionName);
        int nameResultSet = preparedStatement.executeUpdate();
        if (nameResultSet>0){
            System.out.println("Deleted Successfully");
        }else{
            System.out.println("Failed");
        }
    }

    private void readPermission() throws Exception{
        System.out.println("Read Roles");
        System.out.println("1- Read a Single Permission\n2- Read All Permission");
        int readMethod = sc.nextInt();
        sc.nextLine();
        switch (readMethod){
            case 1:
                readSinglePermission();
                break;
            case 2:
                readALlPermission();
                break;
            default:
                System.out.println("Please Enter a Valid Value");
                break;
        }
    }

    private void readALlPermission() throws Exception{
        PreparedStatement preparedStatement = connection.prepareStatement("select * from permission");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println("ID: "+resultSet.getString("perm_id")+"     Name:  "+resultSet.getString("perm_name")+"     Description:  "+resultSet.getString("perm_description"));
        }
    }

    private void readSinglePermission() throws Exception {
        System.out.println("Please Enter Permission Name");
        String permName = sc.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from permission where perm_name = ?");
        preparedStatement.setString(1,permName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println("ID: "+resultSet.getString("perm_id")+"     Name:  "+resultSet.getString("perm_name")+"     Description:  "+resultSet.getString("perm_description"));
        }
    }

    private void updatePermission() throws Exception{
        System.out.println("Permission Update");
        System.out.println("Please Enter Permission Name");
        String permName = sc.nextLine();
        System.out.println("Please Select a Field to Update");
        System.out.println("1- Update Name.\n2- Update Description.");
        int field = sc.nextInt();
        sc.nextLine();
        switch (field){
            case 1:
                System.out.println("Please Enter a new Name for the Permission");
                String permNewName = sc.nextLine();
                PreparedStatement preparedStatement = connection.prepareStatement("update permission set perm_name = ? where perm_name = ?");
                preparedStatement.setString(1,permNewName);
                preparedStatement.setString(2,permName);
                int nameResultSet = preparedStatement.executeUpdate();
                if (nameResultSet>0){
                    System.out.println("Updated Successfully");
                }else{
                    System.out.println("Failed");
                }
                break;
            case 2:
                System.out.println("Please Enter a new Description for the Permission");
                String newDescription = sc.nextLine();
                PreparedStatement emialPreparedStatement = connection.prepareStatement("update permission set perm_description = ? where perm_name = ?");
                emialPreparedStatement.setString(1,newDescription);
                emialPreparedStatement.setString(2,permName);
                int emailResultSet = emialPreparedStatement.executeUpdate();
                if (emailResultSet>0){
                    System.out.println("Updated Successfully");
                }else{
                    System.out.println("Failed");
                }
                break;
            default:
                System.out.println("Please Select A Valid Value");
                break;
        }
    }

    private void insertNewPermission() throws Exception{
        System.out.println("Permission Name:");
        String permissionName = sc.nextLine();
        System.out.println("Permission Description:");
        String permissionDescription = sc.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into permission(perm_name,perm_description) values(?,?)");
        preparedStatement.setString(1,permissionName);
        preparedStatement.setString(2,permissionDescription);
        int resultSet = preparedStatement.executeUpdate();
        if (resultSet>0){
            System.out.println("Added Successfully");
        }else{
            System.out.println("Failed");
        }
    }
}
