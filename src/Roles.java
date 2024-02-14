import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Roles {
    private final Connection connection;
    private final Scanner sc;

    public Roles(Connection connection, Scanner sc) {
        this.connection = connection;
        this.sc = sc;
    }
    public void curdRoles() throws Exception{
        System.out.println("Roles");
        System.out.println("1- Insert New Role\n2- Update Role\n3- Get Role\n4- Delete Role");
        int operation = sc.nextInt();
        sc.nextLine();
        switch (operation){
            case 1:
                insertNewRole();
                break;
            case 2:
                updateRole();
                break;
            case 3:
                readRole();
                break;
            case 4:
                deleteRole();
                break;
            default:
                System.out.println("Value Not Valid");
                break;
        }
    }

    private void deleteRole() throws Exception{
        System.out.println("Role Delete");
        System.out.println("Please Enter Role Name");
        String roleName = sc.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from role where role_name = ?");
        preparedStatement.setString(1,roleName);
        int nameResultSet = preparedStatement.executeUpdate();
        if (nameResultSet>0){
            System.out.println("Deleted Successfully");
        }else{
            System.out.println("Failed");
        }
    }

    private void readRole() throws Exception{
        System.out.println("Read Roles");
        System.out.println("1- Read a Single Role\n2- Read All Roles");
        int readMethod = sc.nextInt();
        sc.nextLine();
        switch (readMethod){
            case 1:
                readSingleRole();
                break;
            case 2:
                readALlRoles();
                break;
            default:
                System.out.println("Please Enter a Valid Value");
                break;
        }
    }

    private void readALlRoles() throws Exception{
        PreparedStatement preparedStatement = connection.prepareStatement("select * from role");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println("ID: "+resultSet.getString("role_id")+"     Name:  "+resultSet.getString("role_name"));
        }
    }

    private void readSingleRole() throws Exception{
        System.out.println("Please Enter Role Name");
        String roleName = sc.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from role where role_name = ?");
        preparedStatement.setString(1,roleName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println("ID: "+resultSet.getString("role_id")+"     Name:  "+resultSet.getString("role_name"));
        }
    }

    private void updateRole() throws Exception{
        System.out.println("Role Update");
        System.out.println("Please Enter Role Name");
        String roleName = sc.nextLine();
        System.out.println("Please Enter Role New Name");
        String roleNewName = sc.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement("update role set role_name = ? where role_name = ?");
        preparedStatement.setString(1,roleNewName);
        preparedStatement.setString(2,roleName);
        int nameResultSet = preparedStatement.executeUpdate();
        if (nameResultSet>0){
            System.out.println("Updated Successfully");
        }else{
            System.out.println("Failed");
        }
    }

    private void insertNewRole() throws Exception{
        System.out.println("Role Name:");
        String roleName = sc.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into role(role_name) values(?)");
        preparedStatement.setString(1,roleName);
        int resultSet = preparedStatement.executeUpdate();
        if (resultSet>0){
            System.out.println("Added Successfully");
        }else{
            System.out.println("Failed");
        }
    }

}
