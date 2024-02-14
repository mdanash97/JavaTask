import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Users {
    private final Connection connection;
    private final Scanner sc;

    public Users(Connection connection, Scanner sc) {
        this.connection = connection;
        this.sc = sc;
    }

    public void crudUser() throws Exception{
        System.out.println("Users");
        System.out.println("1- Insert New User\n2- Update User\n3- Get User\n4- Delete User");
        int operation = sc.nextInt();
        sc.nextLine();
        switch (operation){
            case 1:
                insertNewUser();
                break;
            case 2:
                updateUser();
                break;
            case 3:
                readUser();
                break;
            case 4:
                deleteUser();
                break;
            default:
                System.out.println("Value Not Valid");
                break;
        }
    }

    private void deleteUser() throws Exception {
        System.out.println("User Delete");
        System.out.println("1- Using Mobile Number.\n2- Using Email.");
        int delete_method = sc.nextInt();
        sc.nextLine();
        switch (delete_method) {
            case 1:
                deleteUsingMobile();
                break;
            case 2:
                deleteUsingEmail();
                break;
            default:
                System.out.println("Value Not Valid");
                break;
        }
    }

    private void deleteUsingMobile() throws Exception{
        System.out.println("Please Enter User Mobile");
        String userMobile = sc.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from users where user_mobile = ?");
        preparedStatement.setString(1,userMobile);
        int nameResultSet = preparedStatement.executeUpdate();
        if (nameResultSet>0){
            System.out.println("Deleted Successfully");
        }else{
            System.out.println("Failed");
        }
    }

    private void deleteUsingEmail() throws Exception{
        System.out.println("Please Enter User Email");
        String userEmail = sc.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from users where user_email = ?");
        preparedStatement.setString(1,userEmail);
        int nameResultSet = preparedStatement.executeUpdate();
        if (nameResultSet>0){
            System.out.println("Deleted Successfully");
        }else{
            System.out.println("Failed");
        }
    }

    private void readUser() throws Exception {
        System.out.println("Read Users");
        System.out.println("1- Read a Single User\n2- Read All Users");
        int readMethod = sc.nextInt();
        sc.nextLine();
        switch (readMethod){
            case 1:
                authenticateUser();
                break;
            case 2:
                readALlUser();
                break;
            default:
                System.out.println("Please Enter a Valid Value");
                break;
        }
    }

    private void readALlUser() throws Exception{
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println("ID: "+resultSet.getString("user_id")+"     Name:  "+resultSet.getString("user_name")+"     Email:  "+resultSet.getString("user_email")+"     Mobile:  "+resultSet.getString("user_mobile"));
        }

    }

    private void updateUser() throws Exception {
        System.out.println("User Update");
        System.out.println("1- Using Mobile Number.\n2- Using Email.");
        int update_method = sc.nextInt();
        sc.nextLine();
        switch (update_method) {
            case 1:
                updateUsingMobile();
                break;
            case 2:
                updateUsingEmail();
                break;
            default:
                System.out.println("Value Not Valid");
                break;
        }
    }

    private void updateUsingEmail() throws Exception {
        System.out.println("Please Enter User Email");
        String userEmail = sc.nextLine();
        System.out.println("Please Select a Field to Update");
        System.out.println("1- User Name\n2- User Email\n3-User Mobile");
        int field = sc.nextInt();
        sc.nextLine();
        switch (field){
            case 1:
                System.out.println("Please Enter a new Name for the User");
                String newName = sc.nextLine();
                PreparedStatement preparedStatement = connection.prepareStatement("update users set user_name = ? where user_email = ?");
                preparedStatement.setString(1,newName);
                preparedStatement.setString(2,userEmail);
                int nameResultSet = preparedStatement.executeUpdate();
                if (nameResultSet>0){
                    System.out.println("Updated Successfully");
                }else{
                    System.out.println("Failed");
                }
                break;
            case 2:
                System.out.println("Please Enter a new Email for the User");
                String newEmail = sc.nextLine();
                PreparedStatement emialPreparedStatement = connection.prepareStatement("update users set user_email = ? where user_email = ?");
                emialPreparedStatement.setString(1,newEmail);
                emialPreparedStatement.setString(2,userEmail);
                int emailResultSet = emialPreparedStatement.executeUpdate();
                if (emailResultSet>0){
                    System.out.println("Updated Successfully");
                }else{
                    System.out.println("Failed");
                }
                break;
            case 3:
                System.out.println("Please Enter a new Name for the User");
                String newMobile = sc.nextLine();
                PreparedStatement mobilePreparedStatement = connection.prepareStatement("update users set user_mobile = ? where user_email = ?");
                mobilePreparedStatement.setString(1,newMobile);
                mobilePreparedStatement.setString(2,userEmail);
                int resultSet = mobilePreparedStatement.executeUpdate();
                if (resultSet>0){
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

    private void updateUsingMobile() throws Exception{
        System.out.println("Please Enter User Mobile");
        String userMobile = sc.nextLine();
        System.out.println("Please Select a Field to Update");
        System.out.println("1- User Name\n2- User Email\n3-User Mobile");
        int field = sc.nextInt();
        sc.nextLine();
        switch (field){
            case 1:
                System.out.println("Please Enter a new Name for the User");
                String newName = sc.nextLine();
                PreparedStatement preparedStatement = connection.prepareStatement("update users set user_name = ? where user_mobile = ?");
                preparedStatement.setString(1,newName);
                preparedStatement.setString(2,userMobile);
                int nameResultSet = preparedStatement.executeUpdate();
                if (nameResultSet>0){
                    System.out.println("Updated Successfully");
                }else{
                    System.out.println("Failed");
                }
                break;
            case 2:
                System.out.println("Please Enter a new Email for the User");
                String newEmail = sc.nextLine();
                PreparedStatement emialPreparedStatement = connection.prepareStatement("update users set user_email = ? where user_mobile = ?");
                emialPreparedStatement.setString(1,newEmail);
                emialPreparedStatement.setString(2,userMobile);
                int emailResultSet = emialPreparedStatement.executeUpdate();
                if (emailResultSet>0){
                    System.out.println("Updated Successfully");
                }else{
                    System.out.println("Failed");
                }
                break;
            case 3:
                System.out.println("Please Enter a new Name for the User");
                String newMobile = sc.nextLine();
                PreparedStatement mobilePreparedStatement = connection.prepareStatement("update users set user_mobile = ? where user_mobile = ?");
                mobilePreparedStatement.setString(1,newMobile);
                mobilePreparedStatement.setString(2,userMobile);
                int resultSet = mobilePreparedStatement.executeUpdate();
                if (resultSet>0){
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

    private void insertNewUser() throws Exception {
        System.out.println("User Name:");
        String userName = sc.nextLine();
        System.out.println("User Email:");
        String userEmail = sc.nextLine();
        System.out.println("User Mobile:");
        String userNumber = sc.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(user_name,user_mobile,user_email) values(?,?,?)");
        preparedStatement.setString(1,userName);
        preparedStatement.setString(2,userNumber);
        preparedStatement.setString(3,userEmail);
        int resultSet = preparedStatement.executeUpdate();
        if (resultSet>0){
            System.out.println("Added Successfully");
        }else{
            System.out.println("Failed");
        }
    }

    public void authenticateUser() throws Exception{
        System.out.println("Get User");
        System.out.println("1- Using Mobile Number.\n2- Using Email.");
        int auth_method = sc.nextInt();
        sc.nextLine();
        switch (auth_method) {
            case 1:
                mobileAuthentication();
                break;
            case 2:
                emailAuthentication();
                break;
            default:
                System.out.println("Value Not Valid");
                break;
        }
    }


    private void emailAuthentication() throws Exception {
        System.out.println("Please Enter Email");
        String user_email = sc.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where user_email = ?");
        preparedStatement.setString(1, user_email);
        ResultSet ResultSet = preparedStatement.executeQuery();
        if(ResultSet.next()){
            System.out.println("ID: "+ResultSet.getString("user_id")+"     Name:  "+ResultSet.getString("user_name")+"     Email:  "+ResultSet.getString("user_email")+"     Mobile:  "+ResultSet.getString("user_mobile"));
        }else {
            System.out.println("Unknown User.");
        }
    }

    private void mobileAuthentication() throws Exception {
        System.out.println("Please Enter Mobile Number");
        String user_number = sc.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where user_mobile = ?");
        preparedStatement.setString(1, user_number);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            System.out.println("ID: "+resultSet.getString("user_id")+"     Name:  "+resultSet.getString("user_name")+"     Email:  "+resultSet.getString("user_email")+"     Mobile:  "+resultSet.getString("user_mobile"));
        }else {
            System.out.println("Unknown User.");
        }
    }
}
