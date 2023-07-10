public class Helpers {
    public static void Waiting (int sec){
        try {
            Thread.sleep(sec * 10000L); //если sec = 6, то ожидание 60000мс = 1 минута
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
