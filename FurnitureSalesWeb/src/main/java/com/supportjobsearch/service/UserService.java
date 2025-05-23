package com.supportjobsearch.service;

import com.supportjobsearch.Bean.Role;
import com.supportjobsearch.Bean.User;
import com.supportjobsearch.DAO.iml.RoleDao;
import com.supportjobsearch.DAO.iml.UserDao;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserService extends ServiceBase {

    private UserDao userDao = new UserDao();
    private RoleDao roleDao = new RoleDao();

    public static UserService Instance;

    public UserService() {
        super();
    }

    public static UserService getInstance() {
        if (Instance == null) {
            Instance = new UserService();
        }
        return Instance;
    }

    @Override
    public void init() {
        log.info("UserService init...");
        if (userDao == null || roleDao == null) {
            userDao = new UserDao();
//            roleDao = new RoleDao();
        }
    }

    // Get user by session
    public User getUser(HttpSession session) {
        log.info("UserService getting user using session...");
        User user = (User) session.getAttribute("auth");
        return user;
    }

//    // Check if user whether is allowed to access advance feature
//    public Accessible isAccessible(HttpSession session) {
//
//        if (session == null) return Accessible.NOT_LOGGED_IN;
//
//        User user = getUser(session);
//
//        if (user == null) {
//            log.warn("User not logged in");
//            return Accessible.NOT_LOGGED_IN;
//        } else if (user.getRoleID() == RolePermission.CLIENT.getValue()) {
//            log.info("A customer logged in!");
//            return Accessible.CLIENT;
//        } else if (user.getRoleID() == RolePermission.EMPLOYEE.getValue()) {
//            log.info("User: " + user.getUsername() + " logged in!");
//            return Accessible.EMPLOYEE;
//        } else if (user.getRoleID() == RolePermission.MANAGER.getValue()) {
//            log.info("Manager: " + user.getUsername() + " logged in!");
//            return Accessible.MANAGER;
//        } else if (user.getRoleID() == RolePermission.ADMINISTRATOR.getValue()) {
//            log.info("Administrator: " + user.getUsername() + " logged in!");
//            return Accessible.ADMINISTRATOR;
//        } else return Accessible.NOT_LOGGED_IN;
//    }
//
//    public boolean updatePasswordById(int id, String pass) {
//        return userDao.updatePasswordById(id, pass);
//    }
//
//    public Accessible isAccessible(User user) {
//        if (user == null) {
//            log.warn("User not logged in");
//            return Accessible.NOT_LOGGED_IN;
//        } else if (user.getRoleID() == 0) {
//            log.info("A customer logged in!");
//            return Accessible.CLIENT;
//        } else if (user.getRoleID() == 1) {
//            log.info("User: " + user.getUsername() + " logged in!");
//            return Accessible.EMPLOYEE;
//        } else if (user.getRoleID() == 2) {
//            log.info("Manager: " + user.getUsername() + " logged in!");
//            return Accessible.MANAGER;
//        } else if (user.getRoleID() == 3) {
//            log.info("Administrator: " + user.getUsername() + " logged in!");
//            return Accessible.ADMINISTRATOR;
//        } else return Accessible.NOT_LOGGED_IN;
//    }
//
//
//    public void addUser(User user) {
//        log.info("UserService adding user...");
//        userDao.addUser(user);
//    }
//
//
    public User checkLogin(String username, String pass) {
        User u = userDao.findUser(username);
        if (u != null && pass != null && pass.equals(u.getPass())) {
            u.setPass(null);
            return u;
        }
        return null;
    }
//
//
//    public boolean verifyAccount(String email) {
//        IJavaMail emailService = new EmailService();
//        boolean emailFound = false;
//        var listUser = userDao.getAllUsers();
//        for (var list : listUser) {
//            if (list.getEmail().equals(email)) {
//                emailFound = true;
//                try {
////                    String to = String.valueOf(new InternetAddress(MailProperties.APP_EMAIL));
//                    User user = findIDUserByEmail(email);
//                    int userId = user.getId();
//                    String subject = "Xac thuc tai khoan. Thoi han 30 phut.";
//                    String messageContent = "Chon vao day : " + "http://localhost:8080/changepass?id=" + userId + "&email=" + email;
//                    log.info("Password reset email send to " + email);
//                    emailService.send(email, subject, messageContent);
//                    return true;
//                } catch (Exception e) {
//                    log.error("Error! Can not send email");
//                    e.printStackTrace();
//                    return false;
//                }
//            }
//        }
//        if (emailFound)
//            log.warn("Email does not exist in the system!" + email);
//        return false;
//
//    }
//
//    // Get all user
//    // @param : forceUpdate -> force update query
//    public List<User> getAllUser(boolean forceUpdate) {
//        log.info("UserService getAllUser...");
//        return userDao.getAllUsers(forceUpdate);
//    }
//
//    // Get total user count
//    // @param : forceUpdate -> force update query
//    public int getTotalUsers(boolean forceUpdate) {
//        log.info("UserService getTotalUsers...");
//        return userDao.getTotalUsers(forceUpdate);
//    }
//
//    // Get total user with role employee
//    // @param : forceUpdate -> force update query
//    public int getTotalEmployee(boolean forceUpdate) {
//        log.info("UserService getTotalEmployee...");
//        return userDao.getTotalEmployee(forceUpdate);
//    }
//
//    public int getTotalAdmin(boolean forceUpdate) {
//        log.info("UserService getTotalAdmin...");
//        return userDao.getTotalAdmin(forceUpdate);
//    }
//
//    public User findIDUserByEmail(String email) {
//        return userDao.findIDByEmail(email);
//    }
//
//    public List<User> getAllAdmin(boolean forceUpdate) {
//        log.info("UserService getAllAdmin");
//        return userDao.getAllAdmin(forceUpdate);
//    }
//
    public Map<Integer, Role> getRolesMap(boolean forceUpdate) {
        log.info("UserService getRolesMap...");
        return roleDao.getAllRoles(forceUpdate);
    }

    public boolean hasPermission(User user, String permission, boolean forceUpdate) {
        log.info("UserService hasPermission...");

        if (user == null || permission.equals("")) return false;

        var roles = getRolesMap(forceUpdate);
        Role userRole = roles.get(user.getRoleID());

        if (userRole.getPermission() == null) return false;

        return userRole.getPermission().contains(permission);
    }
//
//    public int getTotalAvailableUsers(boolean forceUpdate) {
//        log.info("UserService getTotalAvailableUsers...");
//        return userDao.getAvailableUsers(forceUpdate).size();
//    }
//
//    // get user list whose has "permission"
//    // @param : forceUpdate -> force update query
//    public int getTotalUsersWithModerator(boolean forceUpdate, String permission) {
//        log.info("UserService getTotalUsersWithModerator...");
//
//        var userList = userDao.getAllUsers(forceUpdate);
//        var roles = getRolesMap(forceUpdate);
//        List<User> list = new ArrayList<>();
//
//        userList.forEach(u -> {
//            if (hasPermission(u, permission, false)) {
//                list.add(u);
//            }
//        });
//
//        return list.size();
//    }
//
//    public User getUserByID(Integer savedID) {
//        return userDao.getUserById(savedID);
//    }
//
//    public boolean checkUserExists(String username) {
//        log.info("UserService checkUserExists...");
//        User u = null;
//
//        u = userDao.findUser(username);
//
//        if (u == null) return false;
//        else return true;
//    }
//
//    public boolean updateUser(User user) {
//        log.info("UserService updateUser");
//        return userDao.updateUser(user);
//    }
//
//    public void disableUser(int id) {
//        log.info("UserService disableUser");
//        User user = userDao.getUserById(id);
//        user.setStatusUser(StatusUser.DISABLE);
//        userDao.updateUser(user);
//    }
}
