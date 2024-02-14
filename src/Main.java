import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        Users users;
        Roles roles;
        Permissions permissions;
        UsersRoles usersRoles;
        RolesPermissions rolesPermissions;
        int user_select;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javataskdb", "root", "root");
            users = new Users(connection,sc);
            roles = new Roles(connection,sc);
            permissions = new Permissions(connection,sc);
            usersRoles = new UsersRoles(connection,sc);
            rolesPermissions = new RolesPermissions(connection,sc);


            do {
                printList();
                user_select = sc.nextInt();
                sc.nextLine();

                switch (user_select) {
                    case 1:
                        users.authenticateUser();
                        System.out.println("Press Any Button To Continue");
                        sc.nextLine();
                        break;
                    case 2:
                        updateDataBase(sc,users,roles,permissions,usersRoles,rolesPermissions);
                        break;
                    case 3:
                        writeDataInCVSFile(connection,sc);
                        break;
                    case 4:
                        System.out.println("Exit");
                        flag = false;
                        break;
                    default:
                        System.out.println("Please Enter Correct Choice");
                }

            } while (flag);
            connection.close();
        }catch (Exception e){
        e.printStackTrace();
        }


    }

    private static void writeDataInCVSFile(Connection connection,Scanner sc) throws Exception{
        System.out.println("Export Data in cvs file");
        System.out.println("1- Check Users Full Data\n2- Export Data");
        int userSelect = sc.nextInt();
        sc.nextLine();
        if(userSelect >0 && userSelect <= 2){
            printAllUsersData(connection,userSelect,sc);
        }else{
            System.out.println("Please Enter Correct Choice");
        }
    }

    private static void printAllUsersData(Connection connection,int selection,Scanner sc) throws Exception{
        String query = "select users.user_id as ID, user_name as Name, user_email as Email, user_mobile as Mobile, role_name As Role, perm_name AS Permission , perm_description as Description from users " +
                "left join user_role on users.user_id = user_role.user_id left join role on role.role_id = user_role.role_id left join role_perm on role.role_id = role_perm.role_id l" +
                "eft join permission on role_perm.perm_id = permission.perm_id";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(selection == 1){
            while(resultSet.next()){
                System.out.println("User ID: "+resultSet.getString("ID")+"     Name: "+resultSet.getString("Name")
                        +"     Email: "+resultSet.getString("Email")+"     Mobile: "+resultSet.getString("Mobile")
                        +"     Role: "+resultSet.getString("Role")+"     Permission: "+resultSet.getString("Permission")+"     Description: "
                        +resultSet.getString("Description"));
            }
        } else if (selection == 2) {
            writeResultSetToCSV(resultSet,sc);
        }
        System.out.println("Press Any Key To Continue");
        sc.nextLine();
    }
    private static void writeResultSetToCSV(ResultSet resultSet,Scanner sc) throws IOException, SQLException {
        System.out.println("Please Choice The File Name:");
        String fileName = sc.nextLine();

        try (FileWriter csvWriter = new FileWriter(fileName+".csv")) {
            csvWriter.append("ID,Name,Email,Mobile,Role,Permission,Description\n");
            while (resultSet.next()) {
                csvWriter.append(resultSet.getString("ID"))
                        .append(",")
                        .append(resultSet.getString("Name"))
                        .append(",")
                        .append(resultSet.getString("Email"))
                        .append(",")
                        .append(resultSet.getString("Mobile"))
                        .append(",")
                        .append(resultSet.getString("Role"))
                        .append(",")
                        .append(resultSet.getString("Permission"))
                        .append(",")
                        .append(resultSet.getString("Description"))
                        .append("\n");
            }
        }
        System.out.println("Write Success");
    }

    private static void updateDataBase(Scanner sc,Users users,Roles roles,Permissions permissions,UsersRoles usersRoles,RolesPermissions rolesPermissions) throws Exception {
        System.out.println("Modify DataBase");
        System.out.println("Please Select a Table To Modify:");
        System.out.println("1- Users\n2- Roles\n3- Permissions\n4- Users Roles\n5- Roles Permissions\n");
        int table_num = sc.nextInt();
        sc.nextLine();
        switch (table_num){
            case 1:
                users.crudUser();
                break;
            case 2:
                roles.curdRoles();
                break;
            case 3:
                permissions.curdPermission();
                break;
            case 4:
                usersRoles.curdUsersRoles();
                break;
            case 5:
                rolesPermissions.curdRolesPermissions();
                break;
            default:
                System.out.println("Value Not Valid");
                break;
        }
        System.out.println("Press Any Button To Continue");
        sc.nextLine();
    }


    private static void printList() {
        System.out.println("Please Select A Number From The List Below:");
        System.out.println("1- User Authentication.\n2- Add/Modify Existing Data\n3- Print All Users Data in csv file.\n4- Exit.\n");
    }

}