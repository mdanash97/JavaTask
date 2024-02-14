import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class RolesPermissions {
    private final Connection connection;
    private final Scanner sc;

    public RolesPermissions(Connection connection, Scanner sc) {
        this.connection = connection;
        this.sc = sc;
    }

    public void curdRolesPermissions() throws Exception{
        System.out.println("Roles Permissions");
        System.out.println("1- Insert New Role Permission\n2- Get Roles Permissions\n3- Delete Role Permissions");
        int operation = sc.nextInt();
        sc.nextLine();
        switch (operation){
            case 1:
                insertNewRolePermission();
                break;
            case 2:
                readRolesPermissions();
                break;
            case 3:
                deleteRolesPermissions();
                break;
            default:
                System.out.println("Value Not Valid");
                break;
        }
    }

    private void readRolesPermissions() throws Exception{
        System.out.println("Read Roles Permission");
        System.out.println("1- Read Role All Permissions\n2- Read Permission All Roles");
        int readMethod = sc.nextInt();
        sc.nextLine();
        switch (readMethod){
            case 1:
                readRoleAllPermissions();
                break;
            case 2:
                readPermissionAllRoles();
                break;
            default:
                System.out.println("Please Enter a Valid Value");
                break;
        }
    }

    private void readRoleAllPermissions() throws Exception {
        System.out.println("Please Enter Role Name");
        String roleName = sc.nextLine();
        String query = "select permission.perm_id,perm_name,perm_description from permission join role_perm on permission.perm_id = role_perm.perm_id join role on role.role_id = role_perm.role_id where role_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,roleName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("Permission ID: "+resultSet.getString("perm_id")+"     Permission Name:  "+resultSet.getString("perm_name")+"     Permission Description:  "+resultSet.getString("perm_description"));
        }
    }

    private void readPermissionAllRoles() throws Exception{
        System.out.println("Please Enter Permission Name");
        String permissionName = sc.nextLine();
        String query = "select role.role_id,role_name from role join role_perm on role.role_id = role_perm.role_id join permission on permission.perm_id = role_perm.perm_id where perm_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,permissionName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("Role ID: "+resultSet.getString("role_id")+"     Role Name:  "+resultSet.getString("role_name"));
        }
    }

    private void deleteRolesPermissions() throws Exception{
        String roleID = "";
        String permID = "";
        System.out.println("Role Name:");
        String roleName = sc.nextLine();
        System.out.println("Role Permission:");
        String rolePermission = sc.nextLine();
        PreparedStatement preparedStatementUser = connection.prepareStatement("select * from role where role_name = ?");
        preparedStatementUser.setString(1,roleName);
        ResultSet resultSetUser = preparedStatementUser.executeQuery();
        while (resultSetUser.next()){
            roleID = resultSetUser.getString("role_id");
        }
        PreparedStatement preparedStatementRole = connection.prepareStatement("select * from permission where perm_name = ?");
        preparedStatementRole.setString(1,rolePermission);
        ResultSet resultSetRole = preparedStatementRole.executeQuery();
        while (resultSetRole.next()){
            permID = resultSetRole.getString("perm_id");
        }
        PreparedStatement preparedStatement = connection.prepareStatement("delete from role_perm where role_id = ? and perm_id = ?");
        preparedStatement.setString(1,roleID);
        preparedStatement.setString(2,permID);
        int resultSet = preparedStatement.executeUpdate();
        if (resultSet>0){
            System.out.println("Deleted Successfully");
        }else{
            System.out.println("Failed");
        }
    }

    private void insertNewRolePermission() throws Exception{
        String roleID = "";
        String permID = "";
        System.out.println("Role Name:");
        String roleName = sc.nextLine();
        System.out.println("Role Permission:");
        String rolePermission = sc.nextLine();
        PreparedStatement preparedStatementUser = connection.prepareStatement("select * from role where role_name = ?");
        preparedStatementUser.setString(1,roleName);
        ResultSet resultSetUser = preparedStatementUser.executeQuery();
        while (resultSetUser.next()){
            roleID = resultSetUser.getString("role_id");
        }
        PreparedStatement preparedStatementRole = connection.prepareStatement("select * from permission where perm_name = ?");
        preparedStatementRole.setString(1,rolePermission);
        ResultSet resultSetRole = preparedStatementRole.executeQuery();
        while (resultSetRole.next()){
            permID = resultSetRole.getString("perm_id");
        }
        PreparedStatement preparedStatement = connection.prepareStatement("insert into role_perm(role_id,perm_id) values(?,?)");
        preparedStatement.setString(1,roleID);
        preparedStatement.setString(2,permID);
        int resultSet = preparedStatement.executeUpdate();
        if (resultSet>0){
            System.out.println("Added Successfully");
        }else{
            System.out.println("Failed");
        }
    }
}
