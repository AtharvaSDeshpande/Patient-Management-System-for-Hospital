public class mysql {
    private static String url = ""; /*UPDATE URL HERE EG: "jdbc:mysql://localhost:3306/Hospital?autoReconnect=true&useSSL=false"*/
    private static String user = ""; /*UPDATE YOUR SQL USER HERE EG: ROOT*/
    private static String password = ""; /*UPDATE YOUR SQL PASSWORD HERE, KEEP IT AS IT IS IF NO PASSWORD EXISTS */
    public static String getUrl()
    {
        return url;
    }
    public static String getUser()
    {
        return user;
    }
    public static String getPassword()
    {
        return password;
    }
}
