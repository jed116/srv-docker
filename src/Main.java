import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args) {
    // ServerSocket
    // Client Server vs P2P (Peer-to-Peer)
    // IO - IOException - Checked
    try (
        ServerSocket serverSocket = new ServerSocket(9999); // ctrl + alt + v
    ) {
      while (true) { // цикл обработки запросов
        // socket для чтения и записи
        try (
            Socket socket = serverSocket.accept(); // пришёл клиент
        ) {
          // IO Stream'ов байт: текущие вперёд
          OutputStream outputStream = socket.getOutputStream();
          // Client <- Protocol -> Server
          String message = "Hello world";

          outputStream.write((
              "HTTP/1.1 200 OK\r\n" +
                  "Content-Type: text/plain\r\n" +
                  "Content-Length: " + message.length() + "\r\n" +
                  "Connection: close\r\n" +
                  "\r\n" +
                  message
          ).getBytes());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
