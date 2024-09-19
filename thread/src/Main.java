import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Main {

    static final Random random = new Random();
    static final List<Integer> GRANULARITY
            = Arrays.asList(1, 10, 100, 1000, 10000);
    static final List<User> USER = IntStream.range(1, 20).mapToObj((i) -> {
        User user = new User();
        user.setId(i);
        user.setName("name" + i);
        user.setEmail("email" + i);
        user.setUserName("userName" + i);
        return user;
    }).toList();

    static final List<Group> GROUPS = IntStream.range(1, 21).mapToObj((i) -> {
        Group group = new Group();
        group.setGroupId(i);
        group.setUsers(IntStream.range(0, 3).mapToObj((j) -> new User(j, "name" + j, "email" + j, "userName" + j)).toList());
        return group;
    }).toList();

    static final List<ServicePrincipal> SERVICE_PRINCIPALS = IntStream.range(0, 5).mapToObj((i) -> {
        ServicePrincipal servicePrincipal = new ServicePrincipal();
        servicePrincipal.setResourceId(random());
        servicePrincipal.setPrincipalType(i % 2 == 0 ? Type.USER : Type.GROUP);
        return servicePrincipal;
    }).toList();

    public static Integer random() {
        return random.nextInt((20 - 1) + 1) + 1;
    }

    private static class Group {
        private Integer groupId;
        private List<User> users;

        public Integer getGroupId() {
            return groupId;
        }

        public void setGroupId(Integer groupId) {
            this.groupId = groupId;
        }

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }

        @Override
        public String toString() {
            return "Group{" +
                    "groupId=" + groupId +
                    ", users=" + users +
                    '}';
        }
    }

    private static class User {
        private Integer id;
        private String name;
        private String email;
        private String userName;

        public User() {
        }

        public User(Integer id, String name, String email, String userName) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.userName = userName;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", userName='" + userName + '\'' +
                    '}';
        }
    }

    public static enum Type {
        USER,
        GROUP
    }

    private static class ServicePrincipal {
        private Integer resourceId;
        private Type principalType;

        public Integer getResourceId() {
            return resourceId;
        }

        public void setResourceId(Integer resourceId) {
            this.resourceId = resourceId;
        }

        public Type getPrincipalType() {
            return principalType;
        }

        public void setPrincipalType(Type principalType) {
            this.principalType = principalType;
        }

        @Override
        public String toString() {
            return "ServicePrincipal{" +
                    "resourceId=" + resourceId +
                    ", principalType=" + principalType +
                    '}';
        }
    }

    // list service -> 2 luong 1 luong get list user
    // 1luong get group merge list user va group user and distinct

    public static CompletableFuture<User> getUser(Integer userId) {
        return CompletableFuture.supplyAsync(() -> {
            // call api
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            User user = getUserApi(userId);
            return user;
        });
    }

    public static CompletableFuture<List<User>> getUsersByGroup(Integer groupId) {
        return CompletableFuture.supplyAsync(() -> {
            // call api
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            List<User> users = getUsersByGroupApi(groupId);
            return users;
        });
    }

    public static User getUserApi(Integer userId) {
        Optional<User> optionalUser = Optional.of(
                        USER.stream()
                                .filter(user -> userId.equals(user.getId()))
                                .findFirst())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return optionalUser.orElseGet(User::new);
    }

    public static List<User> getUsersByGroupApi(Integer groupId) {
        Optional<Group> optionalList = Optional
                .of(
                        GROUPS.stream().filter(group -> groupId.equals(group.getGroupId())).findFirst()
                ).orElseThrow(() -> new RuntimeException("Group not found"));
        return optionalList.isPresent() ? optionalList.get().getUsers() : new ArrayList<>();
    }


    // scenario
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // hard
        System.out.println(SERVICE_PRINCIPALS);
        System.out.println(USER);
        System.out.println(GROUPS);
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<CompletableFuture<User>> userFutures = new ArrayList<>();
        List<CompletableFuture<List<User>>> groupFutures = new ArrayList<>();
        for (ServicePrincipal servicePrincipal : SERVICE_PRINCIPALS) {
            if (Type.USER.equals(servicePrincipal.getPrincipalType())) {
                userFutures.add(getUser(servicePrincipal.getResourceId()));
            } else {
                groupFutures.add(getUsersByGroup(servicePrincipal.getResourceId()));
            }
        }
        System.out.println("start ");
        CompletableFuture<List<User>> parser = CompletableFuture.allOf(userFutures.toArray(new CompletableFuture[0]))
                .thenApplyAsync((v) -> {
                    List<User> users = userFutures.stream().map(CompletableFuture::join).toList();
                    System.out.println("non-blocking ne");
                    return users;
                });
        groupFutures.add(parser);
        CompletableFuture<Void> result=CompletableFuture.allOf(groupFutures.toArray(new CompletableFuture[0]))
                .thenApplyAsync((v) -> {
                    return groupFutures.stream().map(CompletableFuture::join).flatMap(List::stream).toList();
                })
                .thenAcceptAsync((users) -> {
                        System.out.println("result ne -----------------------------");
                        for (User user : users) {
                            System.out.println(user);
                        }
                        System.out.println("-----------------------------");

                });
        System.out.println("end");
        while (!executor.isTerminated()){

        }
        executor.shutdown();
        System.out.println("Final");
    }
}
