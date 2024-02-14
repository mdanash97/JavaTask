import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class UsersRoles {
    private final Connection connection;
    private final Scanner sc;

    public UsersRoles(Connection connection, Scanner sc) {
        this.connection = connection;
        this.sc = sc;
    }

    public void curdUsersRoles() throws Exception{
        System.out.println("Users Roles");
        System.out.println("1- Insert New User Role\n2- Get User Roles\n3- Delete User Role");
        int operation = sc.nextInt();
        sc.nextLine();
        switch (operation){
            case 1:
                insertNewUserRole();
                break;
            case 2:
                readUserRoles();
                break;
            case 3:
                deleteUserRoles();
                break;
            default:
                System.out.println("Value Not Valid");
                break;
        }
    }

    private void readUserRoles() throws Exception{
        System.out.println("Read User Roles");
        System.out.println("1- Read User All Roles\n2- Read Role All Users");
        int readMethod = sc.nextInt();
        sc.nextLine();
        switch (readMethod){
            case 1:
                readUserAllRoles();
                break;
            case 2:
                readRoleAllUsers();
                break;
            default:
                System.out.println("Please Enter a Valid Value");
                break;
        }
    }

    private void readUserAllRoles() throws Exception {
        System.out.println("Please Enter User Email");
        String userEmail = sc.nextLine();
        String query = "select role.role_id,role_name from users join user_role on users.user_id = user_role.user_id join role on role.role_id = user_role.role_id where users.user_email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,userEmail);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("Role ID: "+resultSet.getString("role_id")+"     Role Name:  "+resultSet.getString("role_name"));
        }
    }

    private void readRoleAllUsers() throws Exception{
        System.out.println("Please Enter Role Name");
        String roleName = sc.nextLine();
        String query = "select user_name,users.user_id,user_email from users join user_role on users.user_id = user_role.user_id join role on role.role_id = user_role.role_id where role.role_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,roleName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("User ID: "+resultSet.getString("user_id")+"     User Name:  "+resultSet.getString("user_name")+"     User Email:  "+resultSet.getString("user_email"));
        }
    }

    private void deleteUserRoles() throws Exception{
        String userID = "";
        String roleID = "";
        System.out.println("User Email:");
        String userEmail = sc.nextLine();
        System.out.println("User Role:");
        String userRole = sc.nextLine();
        PreparedStatement preparedStatementUser = connection.prepareStatement("select * from users where user_email = ?");
        preparedStatementUser.setString(1,userEmail);
        ResultSet resultSetUser = preparedStatementUser.executeQuery();
        while (resultSetUser.next()){
            userID = resultSetUser.getString("user_id");
        }
        PreparedStatement preparedStatementRole = connection.prepareStatement("select * from role where role_name = ?");
        preparedStatementRole.setString(1,userRole);
        ResultSet resultSetRole = preparedStatementRole.executeQuery();
        while (resultSetRole.next()){
            roleID = resultSetRole.getString("role_id");
        }
        PreparedStatement preparedStatement = connection.prepareStatement("delete from user_role where user_id = ? and role_id = ?");
        preparedStatement.setString(1,userID);
        preparedStatement.setString(2,roleID);
        int resultSet = preparedStatement.executeUpdate();
        if (resultSet>0){
            System.out.println("Deleted Successfully");
        }else{
            System.out.println("Failed");
        }
    }

    private void insertNewUserRole() throws Exception{
        String userID = "";
        String roleID = "";
        System.out.println("User Email:");
        String userEmail = sc.nextLine();
        System.out.println("User Role:");
        String userRole = sc.nextLine();
        PreparedStatement preparedStatementUser = connection.prepareStatement("select * from users where user_email = ?");
        preparedStatementUser.setString(1,userEmail);
        ResultSet resultSetUser = preparedStatementUser.executeQuery();
        while (resultSetUser.next()){
            userID = resultSetUser.getString("user_id");
        }
        PreparedStatement preparedStatementRole = connection.prepareStatement("select * from role where role_name = ?");
        preparedStatementRole.setString(1,userRole);
        ResultSet resultSetRole = preparedStatementRole.executeQuery();
        while (resultSetRole.next()){
            roleID = resultSetRole.getString("role_id");
        }
        PreparedStatement preparedStatement = connection.prepareStatement("insert into user_role(user_id,role_id) values(?,?)");
        preparedStatement.setString(1,userID);
        preparedStatement.setString(2,roleID);
        int resultSet = preparedStatement.executeUpdate();
        if (resultSet>0){
            System.out.println("Added Successfully");
        }else{
            System.out.println("Failed");
        }
    }
}
