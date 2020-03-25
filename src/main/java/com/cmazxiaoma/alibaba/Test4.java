package com.cmazxiaoma.alibaba;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2020/1/2 12:17
 */
public class Test4 {

    public static void main(String[] args) {
        User user = new User();
        user.setId("1");
        System.out.println(user);
        user(user);
        System.out.println(user);

        double sd1 = 0.00D;
        System.out.println(sd1 > 0.00D);
    }

    public static User user(User user) {
        User newUser = new User();
        newUser.setId("2");
        user = newUser;
        return null;
    }

    public static class User {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    '}';
        }
    }
}
